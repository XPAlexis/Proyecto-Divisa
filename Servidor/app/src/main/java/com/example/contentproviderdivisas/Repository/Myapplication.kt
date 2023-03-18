package com.example.contentproviderdivisas.Repository

import android.app.Application
import com.example.contentproviderdivisas.BD.DivisaDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob

class Myapplication : Application() {
    val applicationScope = CoroutineScope(SupervisorJob())

    val database by lazy { DivisaDatabase.getDatabase(this, applicationScope) }
    val repositoryMoneda by lazy { DivisaRepository(database.divisaDao()) }

}