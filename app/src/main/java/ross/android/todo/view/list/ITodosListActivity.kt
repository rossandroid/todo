package ross.android.todo.view.list
import android.content.Context
import ross.android.todo.repository.model.Todo

interface ITodosListActivity {
    fun showSpinner()
    fun stopSpinner()
    fun updateList(todos: List<Todo>)
    fun showError(error:String)
    fun getContext():Context
    fun refreshCurrent()
}