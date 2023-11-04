package com.crimera.whisp.ui.home.components

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp

@Composable
fun Chip(text: String) {
    Surface(Modifier.clip(RoundedCornerShape(15.dp)), color = MaterialTheme.colorScheme.primary) {
        Text(modifier = Modifier.padding(vertical = 5.dp, horizontal = 10.dp), text=text, style = MaterialTheme.typography.labelLarge)
    }
}