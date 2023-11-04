package com.crimera.whisp.network

import com.crimera.whisp.entity.Product
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get

open class DLsiteApi(private val httpClient: HttpClient) {
    suspend fun getProduct(workNo: String): Product {
        return httpClient.get("${BASE_URL}product.json?workno=$workNo&locale=en_US").body<List<Product>>()[0]
    }

    companion object {
        const val BASE_URL = "https://www.dlsite.com/home/api/=/"
    }
}