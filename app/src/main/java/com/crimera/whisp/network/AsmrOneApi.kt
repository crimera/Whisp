package com.crimera.whisp.network

import com.crimera.whisp.entity.AsmrOne
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get

open class AsmrOneApi(private val httpClient: HttpClient) {
    suspend fun getProduct(workNo: String): AsmrOne {
        return httpClient.get("${BASE_URL}workInfo/$workNo").body()
    }

    companion object {
        const val BASE_URL = "https://api.asmr-200.com/api/"
    }
}