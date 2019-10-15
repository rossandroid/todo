package ross.android.todo.repository

import android.content.Context
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.functions.Function
import io.reactivex.schedulers.Schedulers
import ross.android.todo.presenter.addupdate.IAddEditPresenter
import ross.android.todo.repository.db.AppDatabase
import ross.android.todo.repository.net.NetService
import ross.android.todo.presenter.list.ITodoListPresenter
import ross.android.todo.repository.model.Todo
import java.util.*


object Repository {

    lateinit var todosListNotifier: ITodoListPresenter.Notifier
    lateinit var addEditNotifier: IAddEditPresenter.Notifier
    private var dataBase: AppDatabase? = null

    fun setTodoListNotifier(ls: ITodoListPresenter.Notifier, c: Context) {
        todosListNotifier=ls
        dataBase = AppDatabase.getInstance(c)
    }

    fun setAddEditNotifier(ae:IAddEditPresenter.Notifier,c: Context) {
        addEditNotifier=ae
        dataBase = AppDatabase.getInstance(c)
    }

    // Managing AddEditPresenter
    fun getTodo(id:Int){
        addEditNotifier!!.onTodoReady(dataBase!!.todoDao().get(id))
    }

    fun updateTodo(id:Int,name: String, status: String, expiry: String, description: String) {
        var _todo:Todo=Todo(id, null, null, name, status, description, expiry)
        NetService.todoApi.update(id,_todo)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    addEditNotifier!!.onToDoAdded()
                },
                {
                    dataBase!!.todoDao().insertOne(_todo)
                    addEditNotifier!!.onError(it.message.toString())
                })
    }

    fun addTodo(name: String, status: String, expiry: String, description: String) {
        var _todo=Todo(null, null, null, name, status, description, expiry)
        NetService.todoApi.write(_todo)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    addEditNotifier!!.onToDoAdded()
                },
                {
                    dataBase!!.todoDao().insertOne(_todo)
                    addEditNotifier!!.onError(it.message.toString())
                })
    }

    // Managing TodoListPresenter
    fun downloadTodos(){
        NetService.todoApi.read().subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
                .map ({
                    it.forEach{ t->
                        if(t.expiry_date==null){t.expiry_date="N/A" }
                        if(t.name==null){t.name="N/A" }
                        if(t.description==null){t.description="N/A" }
                        if(t.status==null){t.status="N/A" }
                    }
                    return@map it
                })
                .subscribe(
                {
                    dataBase!!.todoDao().deleteAll()
                    dataBase!!.todoDao().insertAllTodo(it)
                    todosListNotifier!!.onDataReady()
                },
                {
                    todosListNotifier!!.onError(it.message.toString())
                })
    }


    fun readPage(limit:Int) {
        todosListNotifier!!.onSuccess(dataBase!!.todoDao().getWithLimit(limit))
    }

    fun deleteTodo(id:Int) {
        NetService.todoApi.delete(id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    dataBase!!.todoDao().delete(id)
                    todosListNotifier!!.onToDoRemoved()
                },
                {
                    todosListNotifier!!.onError(it.message.toString())
                })
    }

    //Syncronisation Procress
    fun syncronizeTodo(){
        val pending = arrayListOf<Observable<Todo>>()

        var update_pending: List<Todo> = dataBase!!.todoDao().getPendingUpdatedTodo()
        var new_pending:List<Todo> = dataBase!!.todoDao().getPendingNewTodo()

        new_pending.forEach {
            pending.add(NetService.todoApi.write(it))
        }
        update_pending.forEach {
            pending.add(NetService.todoApi.update(it.id!!,it))
        }

        if(pending.size==0)
            downloadTodos()
        else
            Observable.zip(pending) { args -> Arrays.asList(args) }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    downloadTodos()
                },
                {
                    todosListNotifier!!.onError(it.message.toString())
                })

    }







}