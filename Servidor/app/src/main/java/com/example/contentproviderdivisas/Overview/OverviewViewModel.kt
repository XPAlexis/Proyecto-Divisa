package com.example.contentproviderdivisas.Overview

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.contentproviderdivisas.Internet.ExchangeApi
import com.example.contentproviderdivisas.Internet.Moneda
import kotlinx.coroutines.launch

class OverviewViewModel : ViewModel() {
    lateinit var mon: Moneda

    init {
        getMonedasValor()
    }

    fun getMonedasValor() {
        viewModelScope.launch {
            try {
                mon = ExchangeApi.retrofitService.getMonedas()

            } catch (_: Exception) {

            }
        }
    }
}
