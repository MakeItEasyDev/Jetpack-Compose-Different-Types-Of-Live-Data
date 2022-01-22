package com.jetpack.launchflowlivedata

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.jetpack.launchflowlivedata.ui.theme.LaunchFlowLiveDataTheme

class MainActivity : ComponentActivity() {
    private val viewModel: LaunchFlowLiveDataViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LaunchFlowLiveDataTheme {
                Surface(color = MaterialTheme.colors.background) {
                    Scaffold(
                        topBar = {
                            TopAppBar(
                                title = {
                                    Text(
                                        text = "Different Types of Live Data",
                                        modifier = Modifier.fillMaxWidth(),
                                        textAlign = TextAlign.Center
                                    )
                                }
                            )
                        }
                    ) {
                        LaunchFlowLiveData(viewModel)
                    }
                }
            }
        }
    }
}

@Composable
fun LaunchFlowLiveData(viewModel: LaunchFlowLiveDataViewModel) {
    val liveDataPostResult = viewModel.liveDataPost.observeAsState()
    val liveDataSetWithContextResult = viewModel.liveDataSetWithContext.observeAsState()
    val liveDataSetLaunchResult = viewModel.liveDataSetLaunch.observeAsState()
    val liveDataFlowResult = viewModel.stateFlow.collectAsState()

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Button(onClick = { viewModel.triggerStateFlow() }) {
            Text(text = "Click Me!")
        }
        Text(
            text = "StateFlow: ${liveDataFlowResult.value}",
            modifier = Modifier.padding(5.dp),
            fontWeight = FontWeight.Bold,
            fontSize = 18.sp
        )
        Spacer(modifier = Modifier.height(20.dp))

        Button(onClick = { viewModel.triggerLivePostData() }) {
            Text(text = "Click Me!")
        }
        Text(
            text = "LiveData Post: ${liveDataPostResult.value}",
            modifier = Modifier.padding(5.dp),
            fontWeight = FontWeight.Bold,
            fontSize = 18.sp
        )
        Spacer(modifier = Modifier.height(20.dp))

        Button(onClick = { viewModel.triggerLiveSetWithContextData() }) {
            Text(text = "Click Me!")
        }
        Text(
            text = "LiveData Set With Context: ${liveDataSetWithContextResult.value}",
            modifier = Modifier.padding(5.dp),
            fontWeight = FontWeight.Bold,
            fontSize = 18.sp
        )
        Spacer(modifier = Modifier.height(20.dp))

        Button(onClick = { viewModel.triggerLiveSetLaunchData() }) {
            Text(text = "Click Me!")
        }
        Text(
            text = "LiveData Set Launch: ${liveDataSetLaunchResult.value}",
            modifier = Modifier.padding(5.dp),
            fontWeight = FontWeight.Bold,
            fontSize = 18.sp
        )
    }
}































