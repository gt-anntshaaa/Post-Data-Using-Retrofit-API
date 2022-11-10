package com.example.postdatausingretrofit.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import android.view.View
import android.widget.Toast
import com.example.postdatausingretrofit.data.model.Users
import com.example.postdatausingretrofit.data.remote.APIClient
import com.example.postdatausingretrofit.databinding.ActivityMainBinding
import retrofit2.Call
import retrofit2.Response
import javax.security.auth.callback.Callback

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnSumbit.setOnClickListener {
            if (isSucces()){
                val name = binding.name.text.toString()
                val job = binding.job.text.toString()
                postData(name, job)
            }else{
                Toast.makeText(this, "Name can not empty !!!", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun postData(name: String, job: String) {
        val users = Users(name, job)
        val call: Call<Users> = APIClient.service.createUsers(users)

        call.enqueue(object: retrofit2.Callback<Users>{
            override fun onResponse(call: Call<Users>, response: Response<Users>) {
                val users = response.body()

                val nameUser = users?.name
                val jobUser = users?.job

                binding.viewSomething.visibility = View.VISIBLE
                binding.viewSomething.text = nameUser
            }

            override fun onFailure(call: Call<Users>, t: Throwable) {
                binding.viewSomething.text = t.message
            }
        })
    }

    private fun isSucces(): Boolean {
        return !TextUtils.isEmpty(binding.name.text)
    }
}