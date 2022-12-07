package com.hutech.coroutines.view

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.hutech.coroutines.R
import com.hutech.coroutines.databinding.ActivityLoginBinding
import com.hutech.coroutines.viewmodel.LoginViewModel

class Login : AppCompatActivity() {
    private lateinit var databinding: ActivityLoginBinding
    private lateinit var viewModel: LoginViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
       databinding = DataBindingUtil.setContentView(this, R.layout.activity_login)

        viewModel.isvalid.observe(this, Observer {
            if (it == true){
                Toast.makeText(this, "Success", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Fail", Toast.LENGTH_SHORT).show()
            }
        })
    }


}