package com.example.rickmasterstestquest.presentation.doors

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.rickmasterstestquest.R
import de.charlex.compose.RevealDirection
import de.charlex.compose.RevealSwipe

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun DoorsScreen(viewModel: DoorsViewModel = hiltViewModel()) {
    val uiState by viewModel.uiState.collectAsState()
    val doors by viewModel.doorsFlow.collectAsState()

    val refreshing by remember { mutableStateOf(false) }
    val refreshState = rememberPullRefreshState(refreshing, { viewModel.refreshDoors() })

    val openDialog = remember { mutableStateOf(false) }
    var newDoorName by remember { mutableStateOf("") }

    val chosenDoorId = remember { mutableStateOf(0) }

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
            items(doors) { door ->
                RevealSwipe(
                    modifier = Modifier
                        .fillMaxWidth(),
                    directions = setOf(
                        RevealDirection.EndToStart
                    ),
                    hiddenContentEnd = {
                        Row {
                            Image(
                                modifier = Modifier
                                    .padding(horizontal = 4.dp)
                                    .size(28.dp)
                                    .clip(CircleShape)
                                    .clickable {
                                        chosenDoorId.value = door.id
                                        openDialog.value = true
                                    },
                                painter = painterResource(id = R.drawable.door_edit),
                                contentDescription = null
                            )
                            Image(
                                modifier = Modifier
                                    .padding(horizontal = 4.dp)
                                    .size(28.dp)
                                    .clip(CircleShape)
                                    .clickable {
                                        viewModel.onCameraFavoriteChanged(
                                            door = door,
                                            isFavorite = !uiState.isFavorite
                                        )
                                    },
                                painter = painterResource(id = R.drawable.camera_favorite),
                                contentDescription = null
                            )
                        }
                    },
                    backgroundCardEndColor = Color.White
                ) {
                    DoorWidget(
                        doorImage = door.snapshot,
                        doorName = door.name,
                        isFavorite = door.favorites
                    )
                }
                if (openDialog.value) {
                    AlertDialog(
                        onDismissRequest = {
                            openDialog.value = false
                        },
                        title = {
                            Text(text = stringResource(id = R.string.alert_dialog_title))
                        },
                        text = {
                            TextField(
                                value = newDoorName,
                                onValueChange = { newDoorName = it }
                            )
                        },
                        buttons = {
                            Row(
                                modifier = Modifier.padding(all = 8.dp),
                                horizontalArrangement = Arrangement.Center
                            ) {
                                Button(
                                    modifier = Modifier.fillMaxWidth(),
                                    onClick = {
                                        viewModel.updateDoorName(
                                            id = chosenDoorId.value,
                                            name = newDoorName
                                        )
                                        openDialog.value = false
                                    }
                                ) {
                                    Text(text = stringResource(id = R.string.alert_dialog_save))
                                }
                            }
                        }
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