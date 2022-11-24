package com.example.assignment7

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.assignment7.databinding.ActivityMain2Binding
import com.example.assignment7.extensions.setNetworkImage
import com.example.assignment7.network.RetrofitObject
import com.example.assignment7.network.models.User

class MainActivity2 : AppCompatActivity() {

    private lateinit var binding: ActivityMain2Binding
    private val network = RetrofitObject
    private lateinit var user: User

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMain2Binding.inflate(layoutInflater)
        setContentView(binding.root)
        getUser()
    }

    private fun getUser() {

        lifecycleScope.launchWhenStarted {
            val response = network.reqResService.getUser(1)
            if (response.isSuccessful) {
                user = response.body()?.data!!
            }
        }.invokeOnCompletion {
            setData()
        }

    }

    @SuppressLint("SetTextI18n")
    private fun setData() = with(binding.included) {
        imageView.setNetworkImage(user.avatar)
        fullNameTv.text = "${user.firstName} ${user.lastName}"
    }

}