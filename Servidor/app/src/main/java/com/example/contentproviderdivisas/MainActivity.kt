package com.example.contentproviderdivisas

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.room.Room
import androidx.work.*
import com.example.contentproviderdivisas.BD.Divisa
import com.example.contentproviderdivisas.BD.DivisaDatabase
import com.example.contentproviderdivisas.Overview.OverviewViewModel
import com.example.contentproviderdivisas.WorkManager.MyWorker
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.time.LocalDate
import java.util.concurrent.TimeUnit

class MainActivity : AppCompatActivity() {

    private lateinit var overviewViewModel: OverviewViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val db = Room.databaseBuilder(
            applicationContext, DivisaDatabase::class.java, "moneda"
        ).build()
        val divisaDao = db.divisaDao()
        overviewViewModel = ViewModelProvider(this)[OverviewViewModel::class.java]

        // Programar tarea de actualización
        val constraints =
            Constraints.Builder().setRequiredNetworkType(NetworkType.CONNECTED).build()
        val actualizacionDivisasWork = PeriodicWorkRequestBuilder<MyWorker>(
            15, TimeUnit.MINUTES
        ).setConstraints(constraints).build()

        WorkManager.getInstance(this).enqueueUniquePeriodicWork(
            "MyWorker", ExistingPeriodicWorkPolicy.KEEP, actualizacionDivisasWork
        )

        val buscarButton = findViewById<Button>(R.id.btnBuscar)
        buscarButton.setOnClickListener {
            lifecycleScope.launch {
                while (true) {
                    val tasasCambio = overviewViewModel.mon
                    val f = LocalDate.now().toString()
                    for ((key, value) in tasasCambio.rates.entries) {
                        val divisa = Divisa(
                            baseCode = tasasCambio.baseCode,
                            nombreDivisa = key,
                            valor = value,
                            fecha = f
                        )
                        withContext(Dispatchers.IO) {
                            divisaDao.insertDivisa(divisa)
                        }
                    }
                    delay(600000) // Esperar 10 minuto
                }
            }
        }
    }
}
