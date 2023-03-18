package com.example.contentproviderdivisas.BD

import androidx.room.Entity
import androidx.room.PrimaryKey

/*
* Este código define una clase de entidad llamada "Divisa" que
* se utiliza para mapear los datos de la divisa a una tabla en una base de
* datos SQLite utilizando la biblioteca de persistencia de datos Room.
* */
@Entity
data class Divisa(
    @PrimaryKey(autoGenerate = true)
    var _id: Int = 0,
    var baseCode: String,
    var nombreDivisa: String,
    var valor: Double,
    var fecha: String
)
