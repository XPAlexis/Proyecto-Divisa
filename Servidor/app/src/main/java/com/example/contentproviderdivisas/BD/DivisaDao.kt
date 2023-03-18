package com.example.contentproviderdivisas.BD

import android.database.Cursor
import androidx.room.*

/*
* Define los métodos necesarios para acceder y manipular datos de la entidad
* "Divisa" en una base de datos SQLite a través de la biblioteca de persistencia
* de datos Room en una aplicación de Android.
*/

@Dao
interface DivisaDao {

    @Insert
    fun insertDivisa(divisa: Divisa)

    @Update
    fun update(divisa: Divisa)

    @Query("SELECT * FROM Divisa WHERE baseCode = :code")
    fun getByCode(code: String) : Divisa

    @Query("select * from Divisa")
    fun getAllCursor(): Cursor

    @Query("select * from Divisa")
    fun getAll(): kotlinx.coroutines.flow.Flow<List<Divisa>>

    @Query("delete from Divisa")
    fun deleteAll()
}
