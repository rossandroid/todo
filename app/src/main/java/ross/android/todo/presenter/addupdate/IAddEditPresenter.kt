package ross.android.todo.presenter.addupdate

import ross.android.todo.repository.model.Todo

class IAddEditPresenter {
    interface Notifier {
        fun onTodoReady(todo: Todo)
        fun onError(error:String)
        fun onToDoAdded()
        fun onToDoUpdated()
    }
}