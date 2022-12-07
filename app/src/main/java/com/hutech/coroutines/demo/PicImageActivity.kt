package com.hutech.coroutines.demo

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.firebase.database.FirebaseDatabase
import com.hutech.coroutines.databinding.ActivityPicImageBinding
import java.io.FileInputStream
import android.util.Base64
import android.util.Log
import androidx.core.content.ContextCompat
import com.google.firebase.database.DatabaseReference
import com.hutech.coroutines.utils.URIPathHelper
import java.io.File

class PicImageActivity : AppCompatActivity() {
    private lateinit var binding: ActivityPicImageBinding
    var filename: String = ""
    val REQUEST_CODE = 100
    private lateinit var database: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding =ActivityPicImageBinding.inflate(layoutInflater)

        binding.editImage.setOnClickListener {
            openGalleryForImage()
        }

        ContextCompat.checkSelfPermission(this,android.Manifest.permission.READ_EXTERNAL_STORAGE)


    }

    private fun openGalleryForImage() {


        val intent = Intent(Intent.ACTION_PICK)
        intent.type = "image/*"


        startActivityForResult(intent, REQUEST_CODE)
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK && requestCode == REQUEST_CODE) {

            if(data != null){
                if (data.data != null){
                    binding.editProfileImage.setImageURI(data?.data)
                    val uriPathHelper = URIPathHelper()

                    val filePath = uriPathHelper.getPath(this, data.data!!)// handle chosen image

                    val file = File(filePath!!)

                    filename = file.name

                    var s : String =  imageBase64(filename)
                    Log.d("Base 64 is: ",s)
                }

            }
        }
    }

    fun imageBase64(filepath: String): String {
        FileInputStream(filepath).use {
            val bytes = it.readBytes()
            return Base64.encodeToString(bytes, Base64.DEFAULT)
        }
    }

}