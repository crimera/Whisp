package com.crimera.whisp.ui.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.crimera.whisp.others.Resource
import com.crimera.whisp.ui.HomeViewModel
import com.crimera.whisp.ui.home.components.CodeTextField
import com.crimera.whisp.ui.home.components.ProductView

@Composable
fun Home(viewModel: HomeViewModel = viewModel()) {
    val uiState by viewModel.homeViewState.collectAsState()
    var focus by remember { mutableStateOf(false) }
    val mutableInteractionSource = remember { MutableInteractionSource() }

    Box(
        Modifier
            .fillMaxSize()
            .clickable(
                indication = null,
                interactionSource = mutableInteractionSource
            ) { focus = true }
    ) {
            when (uiState.product) {
                is Resource.Loading -> {
                    Text(text = "Loading...")
                }

                is Resource.Error -> {
                    Text(text = "An error occured...")
                }

                is Resource.Success -> {
                    uiState.product.data?.let {
                        ProductView(product = it, uiState.asmrOne)
                    }
                }

                else -> {}
            }

        Surface(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .fillMaxWidth()
                .clip(RoundedCornerShape(10.dp)),
            color = CardDefaults.cardColors().containerColor
        ) {
            Row(modifier = Modifier.padding(10.dp), horizontalArrangement = Arrangement.Center) {

                CodeTextField(
                    uiState.input,
                    {viewModel.updateInput(it)},
                    focus,
                    {focus=false})

                Spacer(Modifier.width(10.dp))
                Button(onClick = {
                    viewModel.getProduct()
                    viewModel.checkAsmrOne()
                }) {
                    Text(text = "Hello")
                }
            }
        }
    }
}