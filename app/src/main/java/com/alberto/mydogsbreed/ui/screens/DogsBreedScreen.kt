package com.alberto.mydogsbreed.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowForward
import androidx.compose.material.icons.rounded.Info
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun DogsBreedScreen(
    dogsName: String,
    onClick: () -> Unit,
) {
    Card(
        contentColor = MaterialTheme.colors.primary,
        elevation = 4.dp,
        modifier = Modifier
            .padding(vertical = 4.dp, horizontal = 8.dp)
            .fillMaxWidth()
            .clickable {
                onClick()
            }
    ) {
        Row(
            modifier = Modifier
                .height(56.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        )
        {
            Icon(
                Icons.Rounded.Info,
                modifier = Modifier.padding(start = 24.dp, top = 4.dp, bottom = 4.dp, end = 4.dp),
                contentDescription = null
            )
            Text(
                text = dogsName,
                modifier = Modifier.padding(top = 4.dp, bottom = 4.dp, end = 24.dp)
            )
            Spacer(
                Modifier
                    .weight(1f)
                    .fillMaxHeight()
            )
            Icon(
                Icons.Rounded.ArrowForward,
                modifier = Modifier.padding(start = 24.dp, top = 4.dp, bottom = 4.dp, end = 4.dp),
                contentDescription = null
            )
        }
    }
}