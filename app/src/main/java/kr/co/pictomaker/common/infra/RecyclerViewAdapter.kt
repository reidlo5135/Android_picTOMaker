package kr.co.pictomaker.common.infra

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kr.co.pictomaker.R
import kr.co.pictomaker.common.model.Entity
import kr.co.pictomaker.common.model.MainViewModel

class RecyclerViewAdapter internal constructor(context: Context, var onDeleteListener : MainViewModel) : RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>() {
    private val inflater: LayoutInflater = LayoutInflater.from(context)
    private var users = emptyList<Entity>() // Cached copy of words

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val num1 = itemView.findViewById<TextView>(R.id.text)
        val deletebutton = itemView.findViewById<Button>(R.id.delete_button)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = inflater.inflate(R.layout.recycler_item, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val num = users[position]
        holder.num1.text = num.number1

        holder.deletebutton.setOnClickListener(View.OnClickListener {
            onDeleteListener.deleteAll(num)
            return@OnClickListener
        })
    }

    @SuppressLint("NotifyDataSetChanged")
    internal fun setUsers(users: List<Entity>) {
        this.users = users
        notifyDataSetChanged()
    }

    override fun getItemCount() = users.size
}