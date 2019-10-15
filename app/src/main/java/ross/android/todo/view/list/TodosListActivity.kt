package ross.android.todo.view.list

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import ross.android.todo.R
import ross.android.todo.repository.model.Todo
import ross.android.todo.presenter.list.TodosListPresenter
import ross.android.todo.view.addupdate.AddEditActivity
import ross.android.todo.view.list.adpter.CardAdapter
import ross.android.todo.view.list.adpter.ICardListener

class TodosListActivity : AppCompatActivity(), ITodosListActivity ,
    ICardListener {

    lateinit var presenter: TodosListPresenter;
    var mAdapter: CardAdapter? = null
    var num_page:Int=1
    var max_page:Int=5

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        presenter= TodosListPresenter(this)
        todo_list_view.layoutManager= LinearLayoutManager(this)
        todo_list_view.setHasFixedSize(true)
        todo_list_view.setItemAnimator(androidx.recyclerview.widget.DefaultItemAnimator())
        todo_list_view.setFitsSystemWindows(true)

        add_todo.setOnClickListener {
            addTodo()
        }

    }

    override fun onResume() {
        super.onResume()
        presenter.syncronizeTodo()
    }

    override fun showSpinner() {
        todo_list_view.visibility= View.GONE
        spinner.visibility= View.VISIBLE

    }

    override fun updateList(todos: List<Todo>) {
        mAdapter = CardAdapter(todos, this)
        todo_list_view.setAdapter(mAdapter);
        todo_list_view.scrollToPosition(todos.size-1);


    }

    override fun stopSpinner() {
        todo_list_view.visibility= View.VISIBLE
        spinner.visibility= View.GONE
    }

    override fun showError(error:String) {
        Toast.makeText(this, error, Toast.LENGTH_SHORT).show()
    }

    override fun getContext(): Context{
        return this.applicationContext
    }


    override fun onDeleteItem(id: Int) {
        presenter.deleteTodo(id);
    }

    override fun onEditItem(id: Int) {
        val intent = Intent(this, AddEditActivity::class.java)
        intent.putExtra("id", id)
        startActivity(intent)
    }

    fun addTodo (){
        val intent = Intent(this, AddEditActivity::class.java)
        startActivity(intent)
    }

    fun loadMore(view:View){
        num_page=num_page+1
        presenter.nextPage(num_page*max_page);
    }


    override fun refreshCurrent(){
        presenter.nextPage(num_page*max_page);
    }


    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_activity_list_todo, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_refresh -> {
                presenter.syncronizeTodo()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}
