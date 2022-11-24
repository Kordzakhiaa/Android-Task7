package com.example.assignment7

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.assignment7.adapter.UsersAdapter
import com.example.assignment7.databinding.ActivityMainBinding
import com.example.assignment7.network.RetrofitObject
import com.example.assignment7.network.models.User

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val network = RetrofitObject
    private lateinit var adapter: UsersAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        network.init()
        getUsers()
    }

    private fun getUsers() {
        val users = mutableListOf<User>()
        lifecycleScope.launchWhenStarted {
            var response = network.reqResService.getUsers(1)
            if (response.isSuccessful) {
                response.body()?.data?.let { users.addAll(it) }
            }
            response = network.reqResService.getUsers(2)
            if (response.isSuccessful) {
                response.body()?.data?.let { users.addAll(it) }
            }
        }.invokeOnCompletion {
            initRecyclerView(users)
        }

    }

    private fun initRecyclerView(users: List<User>) {
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        adapter = UsersAdapter(users, ::onClick)
        binding.recyclerView.adapter = adapter
    }

    private fun onClick(id: Long) {
        val intent = Intent(this, MainActivity2::class.java).apply {
            putExtra("id", id)
        }
        startActivity(intent)
    }

}
