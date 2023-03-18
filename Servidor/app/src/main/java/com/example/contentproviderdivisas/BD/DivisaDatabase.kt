package com.example.contentproviderdivisas.BD

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import kotlinx.coroutines.CoroutineScope

@Database(entities = [Divisa::class], version = 1)
abstract class DivisaDatabase : RoomDatabase() {
    abstract fun divisaDao(): DivisaDao

    /* Codigo donde se obtiene la base de datos y se inicia la instancia */
    companion object {
        private var INSTANCE: DivisaDatabase? = null
        fun getDatabase(context: Context, scope: CoroutineScope): DivisaDatabase {

            if (INSTANCE == null) {
                synchronized(this) {
                    INSTANCE = Room.databaseBuilder(context, DivisaDatabase::class.java, "moneda")
                        .allowMainThreadQueries().build()
                }
            }
            return INSTANCE!!
        }
    }
}
