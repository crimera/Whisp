package com.crimera.whisp.ui.home.components

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalFocusManager

@Composable
fun CodeTextField(
    value: String,
    onValueChange: (String) -> Unit,
    unFocus: Boolean,
    focusChanged: () -> Unit
) {
    val focusManager = LocalFocusManager.current
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        colors = OutlinedTextFieldDefaults.colors(
            focusedBorderColor = OutlinedTextFieldDefaults.colors().focusedContainerColor,
            unfocusedBorderColor = OutlinedTextFieldDefaults.colors().focusedContainerColor
        ),
        textStyle = MaterialTheme.typography.titleLarge
    )

    if (unFocus) {
        focusManager.clearFocus()
        focusChanged()
    }
}