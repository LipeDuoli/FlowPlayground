package com.example.flowplayground.ui.home

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SuggestionChip
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.flowplayground.ui.model.Joke
import com.example.flowplayground.ui.theme.FlowPlaygroundTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SampleScreen(
    joke: Joke?,
    searchText: String? = null,
    onSearchChange: (String) -> Unit = {},
    reload: (() -> Unit)? = null,
    navigateNextScreen: () -> Unit,
) {
    var showResponse by remember {
        mutableStateOf(false)
    }
    Surface {
        Column {
            Button(onClick = navigateNextScreen) {
                Text(text = "Next Screen")
            }
            reload?.let {
                Button(onClick = reload) {
                    Text(text = "Reload")
                }
            }
            searchText?.let {
                TextField(
                    value = it,
                    onValueChange = onSearchChange,
                    label = { Text(text = "Search") })
            }
            joke?.let {
                Card(
                    modifier = Modifier
                        .padding(8.dp)
                        .fillMaxWidth()
                ) {
                    Column(
                        modifier = Modifier.padding(8.dp)
                    ) {
                        SuggestionChip(
                            onClick = { showResponse = !showResponse },
                            label = { Text(text = joke.type) },
                        )
                        Text(text = joke.setup)
                        AnimatedVisibility(visible = showResponse) {
                            Column {
                                Divider(modifier = Modifier.padding(vertical = 8.dp))
                                Text(text = joke.punchline)
                            }
                        }
                    }
                }
            }
        }
    }
}

@Preview
@Composable
private fun HomeScreenPreview() {
    FlowPlaygroundTheme {
        SampleScreen(
            joke = Joke(1, "Geral", "setup", "punchline"),
            searchText = "Shanitra",
            onSearchChange = {},
            reload = {},
            navigateNextScreen = { }
        )
    }
}