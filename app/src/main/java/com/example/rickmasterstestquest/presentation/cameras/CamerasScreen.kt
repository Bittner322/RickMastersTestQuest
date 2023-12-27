package com.example.rickmasterstestquest.presentation.cameras

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.rickmasterstestquest.R
import de.charlex.compose.RevealDirection
import de.charlex.compose.RevealSwipe

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun CamerasScreen(viewModel: CamerasScreenViewModel = hiltViewModel()) {
    val uiState by viewModel.uiState.collectAsState()
    val cameras by viewModel.camerasFlow.collectAsState()
    val refreshing by remember { mutableStateOf(false) }
    val refreshState = rememberPullRefreshState(refreshing, { viewModel.refreshCameras() })

    Box(modifier = Modifier.pullRefresh(refreshState)) {
        LazyColumn(
            modifier = Modifier
                .navigationBarsPadding()
                .fillMaxSize(),
            contentPadding = PaddingValues(
                horizontal = 16.dp,
                vertical = 16.dp
            ),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(cameras) { camera ->
                RevealSwipe(
                    modifier = Modifier
                        .fillMaxWidth(),
                    directions = setOf(
                        RevealDirection.EndToStart
                    ),
                    hiddenContentEnd = {
                        Image(
                            modifier = Modifier
                                .padding(horizontal = 20.dp)
                                .clip(CircleShape)
                                .clickable {
                                    viewModel.onCameraFavoriteChanged(
                                        camera = camera,
                                        isFavorite = !uiState.isFavorite
                                    )
                                },
                            painter = painterResource(id = R.drawable.camera_favorite),
                            contentDescription = null
                        )
                    },
                    backgroundCardEndColor = Color.White
                ) {
                    CameraWidget(
                        cameraImage = camera.snapshot,
                        cameraName = camera.name,
                        isFavorite = camera.favorites,
                        isRecording = camera.rec
                    )
                }
            }
        }
        PullRefreshIndicator(
            modifier = Modifier.align(Alignment.TopCenter),
            refreshing = refreshing,
            state = refreshState
        )
    }
}