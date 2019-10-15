package ross.android.todo.view.addupdate

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_add_update.*
import ross.android.todo.R
import ross.android.todo.presenter.addupdate.AddEditPresenter

class AddEditActivity : AppCompatActivity(),IAddEditActivity {

    lateinit var presenter: AddEditPresenter;
    var NOT_FOUND:Int=-1
    var id=NOT_FOUND

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_update)
        presenter= AddEditPresenter(this)

        id = getIntent().getIntExtra("id",NOT_FOUND)
        if(id!=NOT_FOUND){
            presenter.getValues(id)
            save_button.visibility=View.GONE
            update_button.visibility=View.VISIBLE
        }else
        {
            save_button.visibility=View.VISIBLE
            update_button.visibility=View.GONE
        }

    }

    override fun saveDone(){
        onBackPressed()
    }

    fun saveTodo(v: View){
        val name = name_new_edit_todo.editText!!.text.toString()
        val status = status_new_edit_todo.editText!!.text.toString()
        val expiry = expiry_date_new_edit_todo.editText!!.text.toString()
        val description = description_new_edit_todo.editText!!.text.toString()
        presenter.addTodo(name,status,expiry,description)
    }


    fun updateTodo(v: View){
        val name = name_new_edit_todo.editText!!.text.toString()
        val status = status_new_edit_todo.editText!!.text.toString()
        val expiry = expiry_date_new_edit_todo.editText!!.text.toString()
        val description = description_new_edit_todo.editText!!.text.toString()
        presenter.updateTodo(id,name,status,expiry,description)
    }

    override fun getContext(): Context{
        return this.applicationContext
    }

    override fun updateName(name:String){
        name_new_edit_todo.editText!!.setText(name)
    }
    override fun updateStatus(status:String){
        status_new_edit_todo.editText!!.setText(status)
    }
    override fun updateDescription(description:String){
        description_new_edit_todo.editText!!.setText(description)
    }
    override fun updateExpiry(expiry_date:String){
        expiry_date_new_edit_todo.editText!!.setText(expiry_date)
    }

    override fun saveDoneWithError(error: String) {
        Toast.makeText(this, error, Toast.LENGTH_SHORT).show()
    }
}
