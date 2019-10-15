package ross.android.todo.view.list.adpter

import ross.android.todo.repository.model.Todo

interface ICardListener {
    fun onDeleteItem(id: Int)
    fun onEditItem(id: Int)
}