package com.crimera.whisp.usecases

import android.content.Context
import android.content.Intent
import android.net.Uri

object UseCases {
    fun openLink(url: String, context: Context) {
        val browserIntent = Intent(
            Intent.ACTION_VIEW,
            Uri.parse(url))
            .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        context.startActivity(browserIntent)
    }
}