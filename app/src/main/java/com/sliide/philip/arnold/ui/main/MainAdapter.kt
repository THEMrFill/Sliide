package com.sliide.philip.arnold.ui.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sliide.philip.arnold.databinding.UserRowBinding
import com.sliide.philip.arnold.model.users.User

class MainAdapter(
    private val rowClick: (id: Int) -> Unit,
    ) : RecyclerView.Adapter<UserViewHolder>() {

    val users = ArrayList<User>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val binding = UserRowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return UserViewHolder(binding, rowClick)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        holder.bind(users[position])
    }

    override fun getItemCount(): Int = users.size

    fun setUsers(newUsers: List<User>) {
        with (users) {
            clear()
            addAll(newUsers)
        }
        notifyDataSetChanged()
    }
}

class UserViewHolder(
    private val binder: UserRowBinding,
    private val rowClick: (id: Int) -> Unit,
) : RecyclerView.ViewHolder(binder.root) {

    fun bind(sentUser: User) {
        with(binder) {
            user = sentUser
            root.setOnClickListener {
                rowClick(sentUser.id)
            }
        }
    }
}