package ross.android.todo.presenter.list

import ross.android.todo.repository.model.Todo

class ITodoListPresenter{

    interface Notifier {
        fun onSuccess(todos:List<Todo>)
        fun onError(error:String)
        fun onDataReady()
        fun onToDoRemoved()
    }
}