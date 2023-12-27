package com.example.rickmasterstestquest.presentation.doors

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.rickmasterstestquest.R

@Composable
fun DoorWidget(
    doorImage: String?,
    doorName: String,
    modifier: Modifier = Modifier,
    isFavorite: Boolean = false
) {
    Surface(
        modifier = modifier,
        shape = RoundedCornerShape(12.dp),
        elevation = 6.dp
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White)
        ) {
            Box(modifier = Modifier.fillMaxWidth()) {
                if (doorImage != null) {
                    AsyncImage(
                        modifier = Modifier.fillMaxWidth(),
                        model = doorImage,
                        contentDescription = null,
                        contentScale = ContentScale.FillWidth
                    )
                }
            }
            Row {
                Text(
                    modifier = Modifier.padding(
                        start = 16.dp,
                        top = 16.dp,
                        bottom = 16.dp
                    ),
                    text = doorName,
                    color = Color.Black
                )
                Spacer(modifier = Modifier.weight(1f))
                androidx.compose.animation.AnimatedVisibility(
                    modifier = Modifier.align(Alignment.CenterVertically),
                    visible = isFavorite
                ) {
                    Icon(
                        modifier = Modifier
                            .padding(
                                end = 8.dp,
                                top = 8.dp
                            ),
                        painter = painterResource(id = R.drawable.favorite),
                        contentDescription = null,
                        tint = Color.Yellow
                    )
                }
                Icon(
                    modifier = Modifier.padding(
                        end = 16.dp,
                        top = 16.dp,
                        bottom = 16.dp
                    ),
                    painter = painterResource(id = R.drawable.doors_lock),
                    contentDescription = null
                )
            }
        }
    }
}