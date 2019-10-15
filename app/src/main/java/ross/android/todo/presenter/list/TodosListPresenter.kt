package ross.android.todo.presenter.list

import ross.android.todo.repository.Repository
import ross.android.todo.repository.model.Todo
import ross.android.todo.view.list.ITodosListActivity

class TodosListPresenter(var view: ITodosListActivity): ITodoListPresenter.Notifier {

    init{
        Repository.setTodoListNotifier(this, this.view.getContext())
    }

    override fun onSuccess(todos: List<Todo>) {
        this.view.updateList(todos)
        view.stopSpinner()
    }

    override fun onDataReady() {
        view.refreshCurrent()
    }

    override fun onToDoRemoved() {
        view.refreshCurrent()
    }

    override fun onError(error: String) {
        view.showError(error)
        view.stopSpinner()
    }

    fun syncronizeTodo() {
        view.showSpinner()
        Repository.syncronizeTodo()
    }

    fun nextPage(page:Int){
        view.showSpinner()
        Repository.readPage(page)
    }

    fun deleteTodo(pos:Int){
        Repository.deleteTodo(pos)
    }
}