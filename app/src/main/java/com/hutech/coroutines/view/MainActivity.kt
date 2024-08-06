package com.hutech.coroutines.view

import android.content.ContentValues
import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.ktx.Firebase
import com.google.firebase.messaging.ktx.messaging
import com.hutech.coroutines.R
import com.hutech.coroutines.model.PostModel
import com.hutech.coroutines.notification_api33.notificationafterkilled.AutoStartHelper
import com.hutech.coroutines.viewmodel.HomeViewModel
import com.hutech.coroutines.viewmodel.adapter.HomeAdapter
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    private lateinit var vm: HomeViewModel
    private lateinit var adapter: HomeAdapter
    private lateinit var sharedpreferences:SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        vm = ViewModelProvider(this)[HomeViewModel::class.java]

        sharedpreferences = getSharedPreferences("MyPREFERENCES", Context.MODE_PRIVATE);
        val autoStart = sharedpreferences.getString("autoStart", "");
        if (autoStart.equals("")) {
            AutoStartHelper().getAutoStartPermission(this);
        }

        initAdapter()


        vm.fetchAllPosts()

        vm.postModelListLiveData?.observe(this, Observer {
            if (it!=null){
                rv_home.visibility = View.VISIBLE
                adapter.setData(it as ArrayList<PostModel>)
            } else {
                Toast.makeText(this, "Something went wrong", Toast.LENGTH_SHORT).show()
            }
            progress_home.visibility = View.GONE
        })
    }

    private fun initAdapter() {
        adapter = HomeAdapter()
        rv_home.layoutManager = LinearLayoutManager(this)
        rv_home.adapter = adapter
    }

    fun logRegToken() {
        // [START log_reg_token]
        Firebase.messaging.getToken().addOnCompleteListener { task ->
            if (!task.isSuccessful) {
                Log.w(ContentValues.TAG, "Fetching FCM registration token failed", task.exception)
                return@addOnCompleteListener
            }

            // Get new FCM registration token
            val token = task.result

            // Log and toast
            val msg = "FCM Registration token: $token"
            Log.d(ContentValues.TAG, msg)
            Toast.makeText(baseContext, msg, Toast.LENGTH_SHORT).show()
        }     // [END log_reg_token]
    }


}