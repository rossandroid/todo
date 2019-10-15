package ross.android.todo.presenter.addupdate

import ross.android.todo.repository.Repository
import ross.android.todo.repository.model.Todo
import ross.android.todo.view.addupdate.IAddEditActivity

class AddEditPresenter (var view: IAddEditActivity): IAddEditPresenter.Notifier {

    init {
        Repository.setAddEditNotifier(this, this.view.getContext())
    }

    fun getValues(id:Int){
        Repository.getTodo(id)
    }

    fun addTodo(name: String, status: String, expiry: String, description: String) {
        Repository.addTodo(name,status,expiry,description)
    }

    fun updateTodo(id:Int,name: String, status: String, expiry: String, description: String) {
        Repository.updateTodo(id,name,status,expiry,description)
    }

    override fun onToDoAdded() {
        view.saveDone();
    }

    override fun onToDoUpdated() {
        view.saveDone();
    }

    override fun onError(error: String) {
        view.saveDoneWithError(error);
    }

    override fun onTodoReady(todo: Todo) {
        view.updateName(todo.name)
        view.updateStatus(todo.status)
        view.updateDescription(todo.description)
        view.updateExpiry(todo.expiry_date)
    }



}