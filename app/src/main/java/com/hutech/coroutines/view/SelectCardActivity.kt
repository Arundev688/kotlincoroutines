package com.hutech.coroutines.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.hutech.coroutines.R
import kotlinx.android.synthetic.main.activity_select_card.*

class SelectCardActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_select_card)

        cardTajMahal.setOnClickListener{
            cardTajMahal.isChecked = !cardTajMahal.isChecked
            true
        }

        cardStatueOfUnity.setOnClickListener {
            cardStatueOfUnity.isChecked = !cardStatueOfUnity.isChecked
            true
        }

        cardLotusTemple.setOnClickListener {
            cardLotusTemple.isChecked = !cardLotusTemple.isChecked
            true
        }

     btnWhatsSelected.setOnClickListener {

     }


    }


}