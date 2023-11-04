package com.crimera.whisp.ui.home.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Done
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp

@Composable
fun SiteButton(painter: Painter, text: String,  onClick: () -> Unit = {},) {
    TextButton(onClick = onClick, colors = ButtonDefaults.textButtonColors(contentColor = MaterialTheme.colorScheme.onBackground)) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Image(modifier = Modifier.size(25.dp),painter = painter, contentDescription = "site")
            Spacer(Modifier.height(3.dp))
            Text(text, style = MaterialTheme.typography.labelSmall)
        }
    }
}