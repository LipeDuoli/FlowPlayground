package com.example.flowplayground

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.flowplayground.ui.home.SampleScreen
import com.example.flowplayground.ui.home.viewmodel.livedata.LiveDataBuilderFlowViewModel
import com.example.flowplayground.ui.home.viewmodel.livedata.LiveDataBuilderSearchViewModel
import com.example.flowplayground.ui.home.viewmodel.livedata.LiveDataBuilderViewModel
import com.example.flowplayground.ui.home.viewmodel.livedata.LiveDataFlowRepoViewModel
import com.example.flowplayground.ui.home.viewmodel.livedata.LiveDataViewModel
import com.example.flowplayground.ui.home.viewmodel.stateflow.StateFlowBuilderFlowViewModel
import com.example.flowplayground.ui.home.viewmodel.stateflow.StateFlowBuilderViewModel
import com.example.flowplayground.ui.home.viewmodel.stateflow.StateFlowDirectFlowReloadViewModel
import com.example.flowplayground.ui.home.viewmodel.stateflow.StateFlowDirectFlowViewModel
import com.example.flowplayground.ui.home.viewmodel.stateflow.StateFlowRepoViewModel
import com.example.flowplayground.ui.home.viewmodel.stateflow.StateFlowSearchFlowViewModel
import com.example.flowplayground.ui.home.viewmodel.stateflow.StateFlowSearchItemViewModel
import com.example.flowplayground.ui.home.viewmodel.stateflow.StateFlowViewModel
import com.example.flowplayground.ui.theme.FlowPlaygroundTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            FlowPlaygroundTheme {
                val navController = rememberNavController()

                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    NavHost(
                        navController = navController,
                        startDestination = "main",
                        modifier = Modifier.padding(8.dp)
                    ) {
                        composable("main") {
                            MainScreen(
                                navigate = { screen ->
                                    navController.navigate(screen)
                                }
                            )
                        }

                        composable("livedata") {
                            val viewModel: LiveDataViewModel = hiltViewModel()
                            val joke by viewModel.joke.observeAsState()

                            SampleScreen(
                                joke = joke,
                                reload = viewModel::fetchJoke,
                                navigateNextScreen = { navController.navigate("second") })
                        }

                        composable("livedatabuilder") {
                            val viewModel: LiveDataBuilderViewModel = hiltViewModel()
                            val joke by viewModel.joke.observeAsState()

                            SampleScreen(
                                joke = joke,
                                navigateNextScreen = { navController.navigate("second") })
                        }

                        composable("livedatabuildersearch") {
                            val viewModel: LiveDataBuilderSearchViewModel = hiltViewModel()
                            val joke by viewModel.joke.observeAsState()
                            val query by viewModel.query.observeAsState("")

                            SampleScreen(
                                joke = joke,
                                searchText = query,
                                onSearchChange = viewModel::search,
                                navigateNextScreen = { navController.navigate("second") })
                        }

                        composable("livedataflowrepo") {
                            val viewModel: LiveDataFlowRepoViewModel = hiltViewModel()
                            val joke by viewModel.joke.observeAsState()

                            SampleScreen(
                                joke = joke,
                                reload = viewModel::fetchJoke,
                                navigateNextScreen = { navController.navigate("second") })
                        }

                        composable("livedatabuilderflowrepo") {
                            val viewModel: LiveDataBuilderFlowViewModel = hiltViewModel()
                            val joke by viewModel.joke.observeAsState()

                            SampleScreen(
                                joke = joke,
                                navigateNextScreen = { navController.navigate("second") })
                        }

                        composable("stateflow") {
                            val viewModel: StateFlowViewModel = hiltViewModel()
                            val joke by viewModel.joke.collectAsStateWithLifecycle()

                            SampleScreen(
                                joke = joke,
                                reload = viewModel::fetchJoke,
                                navigateNextScreen = { navController.navigate("second") })
                        }

                        composable("stateflowbuilder") {
                            val viewModel: StateFlowBuilderViewModel = hiltViewModel()
                            val joke by viewModel.joke.collectAsStateWithLifecycle()

                            SampleScreen(
                                joke = joke,
                                navigateNextScreen = { navController.navigate("second") })
                        }

                        composable("stateflowsearchitem") {
                            val viewModel: StateFlowSearchItemViewModel = hiltViewModel()
                            val joke by viewModel.joke.collectAsStateWithLifecycle()
                            val query by viewModel.query.collectAsStateWithLifecycle()

                            SampleScreen(
                                joke = joke,
                                searchText = query,
                                onSearchChange = viewModel::search,
                                navigateNextScreen = { navController.navigate("second") })
                        }

                        composable("stateflowsearchflow") {
                            val viewModel: StateFlowSearchFlowViewModel = hiltViewModel()
                            val joke by viewModel.joke.collectAsStateWithLifecycle()
                            val query by viewModel.query.collectAsStateWithLifecycle()

                            SampleScreen(
                                joke = joke,
                                searchText = query,
                                onSearchChange = viewModel::search,
                                navigateNextScreen = { navController.navigate("second") })
                        }

                        composable("stateflowrepobuilder") {
                            val viewModel: StateFlowRepoViewModel = hiltViewModel()
                            val joke by viewModel.joke.collectAsStateWithLifecycle()

                            SampleScreen(
                                joke = joke,
                                reload = viewModel::fetchJoke,
                                navigateNextScreen = { navController.navigate("second") })
                        }

                        composable("stateflowbuilderflow") {
                            val viewModel: StateFlowBuilderFlowViewModel = hiltViewModel()
                            val joke by viewModel.joke.collectAsStateWithLifecycle()

                            SampleScreen(
                                joke = joke,
                                navigateNextScreen = { navController.navigate("second") })
                        }

                        composable("stateflowdirectflow") {
                            val viewModel: StateFlowDirectFlowViewModel = hiltViewModel()
                            val joke by viewModel.joke.collectAsStateWithLifecycle()

                            SampleScreen(
                                joke = joke,
                                navigateNextScreen = { navController.navigate("second") })
                        }

                        composable("stateflowdirectflowreload") {
                            val viewModel: StateFlowDirectFlowReloadViewModel = hiltViewModel()
                            val joke by viewModel.joke.collectAsStateWithLifecycle()

                            SampleScreen(
                                joke = joke,
                                reload = viewModel::fetchJoke,
                                navigateNextScreen = { navController.navigate("second") })
                        }

                        composable("second") {
                            Column {
                                Text(text = "Second Screen")
                                Button(onClick = { navController.navigateUp() }) {
                                    Text(text = "Back")
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    @Composable
    private fun MainScreen(navigate: (String) -> Unit = {}) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(6.dp),
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(8.dp)
        ) {
            SampleSection(
                description = "LiveData -> Fetch with function -> Repository return a item"
            ) {
                navigate("livedata")
            }
            SampleSection(
                description = "LiveData -> Fetch with builder -> Repository return a item"
            ) {
                navigate("livedatabuilder")
            }
            SampleSection(
                description = "LiveData -> Fetch search with \"map\" -> Repository return a item"
            ) {
                navigate("livedatabuildersearch")
            }
            SampleSection(
                description = "LiveData -> Fetch from function -> Repository return a flow"
            ) {
                navigate("livedataflowrepo")
            }
            SampleSection(
                description = "LiveData -> Fetch from builder -> Repository return a flow"
            ) {
                navigate("livedatabuilderflowrepo")
            }
            SampleSection(
                description = "StateFlow -> Fetch from function -> Repository return a item"
            ) {
                navigate("stateflow")
            }
            SampleSection(
                description = "StateFlow -> Fetch from builder -> Repository return a item"
            ) {
                navigate("stateflowbuilder")
            }
            SampleSection(
                description = "StateFlow -> Fetch from \"map\" -> Repository return a item"
            ) {
                navigate("stateflowsearchitem")
            }
            SampleSection(
                description = "StateFlow -> Fetch from \"map\" -> Repository return a flow"
            ) {
                navigate("stateflowsearchflow")
            }
            SampleSection(
                description = "StateFlow -> Fetch from function -> Repository return a flow"
            ) {
                navigate("stateflowrepobuilder")
            }
            SampleSection(
                description = "StateFlow -> Fetch from builder -> Repository return a flow"
            ) {
                navigate("stateflowbuilderflow")
            }
            SampleSection(
                description = "StateFlow -> Fetch direct -> Repository return a flow"
            ) {
                navigate("stateflowdirectflow")
            }
            SampleSection(
                description = "StateFlow -> Fetch direct with reload -> Repository return a flow"
            ) {
                navigate("stateflowdirectflowreload")
            }
        }
    }

    @Composable
    fun SampleSection(
        description: String,
        onClick: () -> Unit
    ) {
        val desc = description.split(" ", limit = 2)
        Column(
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(text = buildAnnotatedString {
                    withStyle(SpanStyle(fontWeight = FontWeight.Bold)) {
                        append(desc.first())
                        append(" ")
                    }
                    append(desc.last())
                }, modifier = Modifier.weight(1f))
                Spacer(modifier = Modifier.width(8.dp))
                Button(
                    contentPadding = PaddingValues(horizontal = 4.dp, vertical = 2.dp),
                    onClick = onClick
                ) {
                    Text(text = "Open")
                }
            }
            Divider()
        }
    }
}