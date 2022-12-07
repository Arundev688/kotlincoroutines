package com.hutech.coroutines.viewmodel

import androidx.databinding.Bindable
import androidx.databinding.Observable
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.database.*
import com.hutech.coroutines.model.User


class LoginViewModel : ViewModel(), Observable {

   // val toastMessage = MutableLiveData<String>()

    val isvalid = MutableLiveData<Boolean>()
    val firebasedatabase : FirebaseDatabase? = null
    val databasereference : DatabaseReference? = null
    val user : User? = null

    init {
        isvalid.value = false
    }

    @Bindable
    val userEmail = MutableLiveData<String>()

    val list = MutableLiveData<ArrayList<User>>()
    val arrayList = ArrayList<User>()


    @Bindable
    val userPassword = MutableLiveData<String>()

    fun onLoginClicked() {
        val email = userEmail.value!!
        val psw = userPassword.value!!
        if (email.isEmpty() || psw.isEmpty()) {
           println("Invalid data")
        } else {
            /*var user = User(email,psw)
            arrayList.add(user)
            list.value = arrayList*/
            addDatatoFirebase(email,psw)
        }
    }

    override fun addOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {

    }

    override fun removeOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {

    }

    fun addDatatoFirebase(email: String,psw : String){
       user?.setEmail(email)
       user?.setPassword(psw)

        databasereference?.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                // inside the method of on Data change we are setting
                // our object class to our database reference.
                // data base reference will sends data to firebase.
                databasereference.setValue(user)
            }

            override fun onCancelled(error: DatabaseError) {

            }
        })
    }


}