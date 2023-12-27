package com.example.rickmasterstestquest.presentation.main_activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.sp
import androidx.core.view.WindowCompat
import com.example.rickmasterstestquest.R
import com.example.rickmasterstestquest.presentation.cameras.CamerasScreen
import com.example.rickmasterstestquest.presentation.doors.DoorsScreen
import com.example.rickmasterstestquest.presentation.ui.theme.RickMastersTestQuestTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)

        setContent {
            RickMastersTestQuestTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    var tabIndex by remember { mutableStateOf(0) }

                    val tabs = listOf(
                        stringResource(id = R.string.app_cameras_topbar_tab),
                        stringResource(id = R.string.app_doors_topbar_tab)
                    )

                    Column(modifier = Modifier.fillMaxWidth()) {
                        CenterAlignedTopAppBar(
                            title = {
                                Text(
                                    text = stringResource(id = R.string.app_topbar),
                                    fontSize = 28.sp
                                )
                            }
                        )

                        TabRow(selectedTabIndex = tabIndex) {
                            tabs.forEachIndexed { index, title ->
                                Tab(text = { Text(title) },
                                    selected = tabIndex == index,
                                    onClick = { tabIndex = index }
                                )
                            }
                        }
                        when (tabIndex) {
                            0 -> CamerasScreen()
                            1 -> DoorsScreen()
                        }
                    }
                }
            }
        }
    }
}