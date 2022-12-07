package com.hutech.coroutines.apilocalcachi


import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BankViewmodel @Inject constructor(
    repository: BankRepository
) : ViewModel() {
     val banks = repository.getBanks().asLiveData()

  /*    private val bankLiveData = MutableLiveData<List<bank>>()

    val banks : MutableLiveData<List<bank>> = bankLiveData

    init {
        viewModelScope.launch {
            val banks = api.getBanks()
             delay(4000)
            bankLiveData.value = banks
        }
    }*/
}