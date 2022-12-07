package com.hutech.coroutines.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.hutech.coroutines.model.HomeRepository
import com.hutech.coroutines.model.PostModel

class HomeViewModel(application: Application): AndroidViewModel(application){

    private var homeRepository: HomeRepository?=null
    var postModelListLiveData : LiveData<List<PostModel>>?=null
    var createPostLiveData:LiveData<PostModel>?=null

    init {
        homeRepository = HomeRepository()
        postModelListLiveData = MutableLiveData()
        createPostLiveData = MutableLiveData()
    }

    fun fetchAllPosts(){
        postModelListLiveData = homeRepository?.fetchAllPost()
    }

}
