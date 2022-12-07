package com.hutech.coroutines.model


class User(email: String?, password: String?) {

    private var email: String? = null
    private var password: String? = null

    fun setEmail(email: String?) {
        this.email = email
    }


    fun getEmail(): String? {
        return email
    }

    fun setPassword(password: String?) {
        this.password = password
    }


    fun getPassword(): String? {
        return password
    }


}