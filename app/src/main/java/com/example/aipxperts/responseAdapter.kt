package com.example.aipxperts

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.aipxperts.room.User

class responseAdapter(context: Context, userList: List<User>):
    RecyclerView.Adapter<responseAdapter.ViewHolder>() {

    var context = context
    var list = userList

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_response, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.postId.text = "post ID : "+list.get(position).postId.toString()
        holder.id.text = "ID : "+list.get(position).id.toString()
        holder.name.text = "Name : "+list.get(position).name.toString()
        holder.email.text = "Email : "+list.get(position).email.toString()
        holder.body.text = list.get(position).body.toString()
    }

    override fun getItemCount(): Int {
        return list.size
    }

    class ViewHolder internal constructor(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var postId: TextView
        var id: TextView
        var name: TextView
        var email: TextView
        var body: TextView

        init {
            postId = itemView.findViewById(R.id.post_id)
            id = itemView.findViewById(R.id.id)
            name = itemView.findViewById(R.id.name)
            email = itemView.findViewById(R.id.email)
            body = itemView.findViewById(R.id.body)
        }
    }
}