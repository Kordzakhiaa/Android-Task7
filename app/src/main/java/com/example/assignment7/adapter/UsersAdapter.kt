package com.example.assignment7.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.assignment7.databinding.UserLayoutBinding
import com.example.assignment7.extensions.setNetworkImage
import com.example.assignment7.network.models.User

class UsersAdapter(private val users: List<User>, private val onClick: (id: Long) -> Unit) :
    RecyclerView.Adapter<UsersAdapter.ViewHolder>() {

    inner class ViewHolder(private val binding: UserLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("SetTextI18n")
        fun onBind(user: User) = with(binding) {
            imageView.setNetworkImage(user.avatar)
            fullNameTv.text = "${user.firstName} ${user.lastName}"
            root.setOnClickListener {
                onClick(user.id)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(
        UserLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.onBind(users[position])
    }

    override fun getItemCount() = users.size

}