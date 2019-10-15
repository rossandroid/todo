package ross.android.todo.view.list.adpter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.card_adapter.view.*
import ross.android.todo.R
import ross.android.todo.repository.model.Todo
import androidx.recyclerview.widget.RecyclerView.ViewHolder


class CardAdapter constructor(var todos: List<Todo>, var listener: ICardListener) : androidx.recyclerview.widget.RecyclerView.Adapter<ViewHolder>() {

    override fun getItemCount(): Int = todos.size

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): androidx.recyclerview.widget.RecyclerView.ViewHolder =
        ViewHolder(
            LayoutInflater.from(p0.context).inflate(
                R.layout.card_adapter,
                p0,
                false
            )
        )


    override fun onBindViewHolder(holder: androidx.recyclerview.widget.RecyclerView.ViewHolder, pos: Int) {
        val current = todos[pos]
        holder.itemView.name_todo.text= current.name
        holder.itemView.status_todo.text= current.status
        holder.itemView.expiry_date_todo.text= current.expiry_date
        holder.itemView.description_todo.text= current.description
        holder.itemView.remove_button.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                listener.onDeleteItem(current.id!!)// not-null value
            }
        })
        holder.itemView.update_button.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                listener.onEditItem(current.id!!) // not-null value
            }
        })
        holder.itemView.material_card.setOnTouchListener(OnSwipeListener());

    }

    internal class ViewHolder(view: View) : androidx.recyclerview.widget.RecyclerView.ViewHolder(view)

    interface CardLi

}
