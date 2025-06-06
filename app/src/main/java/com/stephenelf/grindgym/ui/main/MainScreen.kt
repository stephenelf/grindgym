package com.stephenelf.grindgym.ui.main

import android.Manifest
import android.annotation.SuppressLint
import androidx.annotation.RequiresPermission
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.TileMode
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.google.android.gms.location.LocationServices
import com.stephenelf.grindgym.R
import com.stephenelf.grindgym.data.model.Gym
import com.stephenelf.grindgym.ui.util.GymSwipableCards
import com.stephenelf.grindgym.ui.util.PermissionBox
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await

@SuppressLint("MissingPermission")
@OptIn(ExperimentalLayoutApi::class)
@Composable
fun MainScreen(
    modifier: Modifier = Modifier,
    viewModel: MainScreenViewModel = hiltViewModel(),
    snackbarHostState: SnackbarHostState = remember { SnackbarHostState() }
) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        snackbarHost = { SnackbarHost(snackbarHostState) }
    ) { paddingValues ->
        val uiState by viewModel.uiState.collectAsStateWithLifecycle()
        val permissions = listOf(
            Manifest.permission.ACCESS_COARSE_LOCATION,
            Manifest.permission.ACCESS_FINE_LOCATION,
        )
        PermissionBox(
            permissions = permissions,
            requiredPermissions = listOf(permissions.first()),
            onGranted = {
                GymCardStack(
                    usePreciseLocation = it.contains(Manifest.permission.ACCESS_FINE_LOCATION),
                    loading = uiState.isLoading,
                    data = { uiState.gymList },
                    handleLike = { viewModel.handleLike() },
                    handleDislike = { viewModel.handleDislike() },
                    modifier = modifier.padding(paddingValues)
                )
            },
        )

        // Check for user messages to display on the screen
        uiState.userMessage?.let { message ->
            val snackbarText = stringResource(message)
            LaunchedEffect(snackbarHostState, viewModel, message, snackbarText) {
                snackbarHostState.showSnackbar(snackbarText)
                viewModel.snackbarMessageShown()
            }
        }

    }
}


@RequiresPermission(
    anyOf = [Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION],
)@Composable
fun GymCardStack(
    usePreciseLocation: Boolean,
    loading: Boolean,
    data: () -> List<Gym>,
    handleLike: () -> Unit,
    handleDislike: () -> Unit,
    modifier: Modifier = Modifier
) {
    val scope = rememberCoroutineScope()
    val context = LocalContext.current
    val locationClient = remember {
        LocationServices.getFusedLocationProviderClient(context)
    }
    var locationInfo by remember {
        mutableStateOf("")
    }
    val getLocation = {
        scope.launch(Dispatchers.IO) {
            val result = locationClient.lastLocation.await()
            locationInfo = if (result == null) {
                "No last known location. Try fetching the current location first"
            } else {
                "Current location is \n" + "lat : ${result.latitude}\n" +
                        "long : ${result.longitude}\n" + "fetched at ${System.currentTimeMillis()}"
            }
        }
    }

    Column(modifier = modifier.padding(16.dp)) {
        Title()
        if (!loading)
            GymSwipableCards(data, handleLike, handleDislike)
    }

}

@Preview
@Composable
fun Title() {
    val rainbowColorBrush = Brush.radialGradient(
        listOf(Color.Red, Color.Blue, Color.Yellow),
        tileMode = TileMode.Repeated
    )
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .drawBehind {
                drawRect(rainbowColorBrush, style = Stroke(16.dp.toPx()))
            }
    )
    {
        Text(
            text = stringResource(R.string.app_name),
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier
                .padding(8.dp)
                .align(CenterHorizontally)
        )
    }
}



