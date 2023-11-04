package com.crimera.whisp.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.crimera.whisp.entity.AsmrOne
import com.crimera.whisp.entity.Product
import com.crimera.whisp.network.AsmrOneApi
import com.crimera.whisp.network.DLsiteApi
import com.crimera.whisp.others.Resource
import io.ktor.client.HttpClient
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.serialization.kotlinx.json.json
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.serialization.json.Json

data class HomeViewState(
    val product: Resource<Product> = Resource.Empty(),
    val asmrOne: AsmrOne? = null,
    val loading: Boolean = false,
    val input: String = ""
)

class HomeViewModel: ViewModel() {
    private val httpClient = HttpClient {
        install(ContentNegotiation) {
            json(Json {
                ignoreUnknownKeys = true
                useAlternativeNames = false
            })
        }
    }
    private val api = DLsiteApi(httpClient)
    private val asmrOneApi = AsmrOneApi(httpClient)

    private val state = MutableStateFlow(HomeViewState())
    val homeViewState = state.asStateFlow()

    fun updateInput(text: String) {
        state.update {
            it.copy(input = text)
        }
    }

    fun checkAsmrOne() {
        viewModelScope.launch {
            state.update {
                it.copy(
                    asmrOne = asmrOneApi.getProduct(state.value.input.removePrefix("RJ"))
                )
            }
        }
    }

    fun getProduct() {
        viewModelScope.launch {
            state.update { it.copy(product = Resource.Loading()) }
            try {
                state.update {
                    it.copy(
                        product = Resource.Success(api.getProduct(it.input))
                    )
                }
            } catch (e: Exception) {
                state.update {
                    it.copy(
                        product = Resource.Error(e.message)
                    )
                }
            }
        }
    }
}