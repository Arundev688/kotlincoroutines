package com.hutech.coroutines.apilocalcachi

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.hutech.coroutines.apilocalcachi.features.BankAdapter
import com.hutech.coroutines.databinding.ActivityApiCatchBinding
import com.hutech.coroutines.demo.PicImageActivity
import com.hutech.coroutines.utils.Resource
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ApiCatchActivity : AppCompatActivity() {
    private val viewmodel:BankViewmodel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityApiCatchBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val bankAdapter = BankAdapter()

        binding.apply {
            recyclerView.apply {
                adapter = bankAdapter
                layoutManager = LinearLayoutManager(this@ApiCatchActivity)
            }

            viewmodel.banks.observe(this@ApiCatchActivity){
                banks->
                bankAdapter.submitList(banks.data)

                progressBar.isVisible = banks is Resource.Loading && banks.data.isNullOrEmpty()
                textViewError.isVisible = banks is Resource.Error && banks.data.isNullOrEmpty()
                textViewError.text = banks.error?.localizedMessage
            }
        }



    }
}