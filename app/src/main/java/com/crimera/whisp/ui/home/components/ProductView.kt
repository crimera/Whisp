package com.crimera.whisp.ui.home.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImagePainter
import coil.compose.SubcomposeAsyncImage
import coil.compose.SubcomposeAsyncImageContent
import com.crimera.whisp.R
import com.crimera.whisp.entity.AsmrOne
import com.crimera.whisp.entity.Product
import com.crimera.whisp.usecases.UseCases

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun ProductView(product: Product, asmrOne: AsmrOne?) {
    val context = LocalContext.current.applicationContext

    Box {
        Column(
            modifier = Modifier.padding(top = 10.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            SubcomposeAsyncImage(
                model = "https://${product.imageMain?.url}",
                contentDescription = null,
            ) {
                when (painter.state) {
                    is AsyncImagePainter.State.Loading -> {
                        Box(Modifier.height(100.dp)) {
                            CircularProgressIndicator(Modifier.align(Alignment.Center))
                        }
                    }

                    else -> {
                        SubcomposeAsyncImageContent(modifier = Modifier.clip(MaterialTheme.shapes.medium))
                    }
                }
            }

            Spacer(Modifier.height(10.dp))

            Column(
                Modifier
                    .padding(horizontal = 10.dp)
                    .fillMaxWidth(), verticalArrangement = Arrangement.spacedBy(10.dp),
            ) {
                Row(
                    Modifier
                        .height(60.dp)
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    SiteButton(painterResource(R.drawable.dlsite), "DLsite") {
                        UseCases.openLink("https://www.dlsite.com/home/work/=/product_id/${product.workno}.html", context)
                    }
                    VerticalDivider(modifier = Modifier.padding(10.dp),thickness = 1.dp)
                    SiteButton(painterResource(R.drawable.japaneseasmr), "JapaneseAsmr") {
                        UseCases.openLink("https://japaneseasmr.com/?s=${product.workno}", context)
                    }
                    if (asmrOne?.error == null) {
                        VerticalDivider(modifier = Modifier.padding(10.dp),thickness = 1.dp)
                        SiteButton(rememberVectorPainter(image = Icons.Default.Add), text = "Kikoeru") {
                            UseCases.openLink("https://asmr.one/work/${product.workno}", context)
                        }
                    }
                }

                Text(
                    text = product.workName,
                    style = MaterialTheme.typography.titleMedium,
                )
                Text(text = product.makerName, style = MaterialTheme.typography.titleSmall)

                OutlinedCard(Modifier.fillMaxWidth()) {
                    Box(Modifier.padding(10.dp)) {
                        Text(text = "Voices: ")
                    }
                }
                product.genres?.let {
                    Genres(genres = it)
                }
            }
        }
    }
}
