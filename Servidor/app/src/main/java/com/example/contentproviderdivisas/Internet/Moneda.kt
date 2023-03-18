package com.example.contentproviderdivisas.Internet

import com.squareup.moshi.Json

data class Moneda(
    val result: String,
    val provider: String,
    val documentation: String,
    @Json(name = "terms_of_use") val termsOfUse: String,
    @Json(name = "time_last_update_unix") val lastUpdateTime: Long,
    @Json(name = "time_last_update_utc") val lastUpdateUtc: String,
    @Json(name = "time_next_update_unix") val nextUpdateTime: Long,
    @Json(name = "time_next_update_utc") val nextUpdateUtc: String,
    @Json(name = "time_eol_unix") val endOfLifeUnix: Long,
    @Json(name = "base_code") val baseCode: String,
    val rates: Map<String, Double>


)