package ru.yasdev.profile.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import org.koin.androidx.compose.koinViewModel
import ru.yasdev.profile.R
import ru.yasdev.profile.models.ProfileState
import ru.yasdev.profile.navigation.ProfileNavigator

@Composable
fun ProfileScreen(navigator: (ProfileNavigator) -> Unit) {
    Column {
        val viewModel = koinViewModel<ProfileViewModel>()
        when (val state = viewModel.state.collectAsState().value) {
            ProfileState.ErrorOnReceipt -> {
                Box(
                    Modifier.fillMaxSize(), contentAlignment = Alignment.Center
                ) {
                    Text(text = stringResource(id = R.string.unexpected_error))
                }
            }

            ProfileState.Loading -> {
                Box(
                    Modifier.fillMaxSize(), contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator(modifier = Modifier.size(50.dp))
                }
            }

            is ProfileState.Profile -> {
                Row(
                    horizontalArrangement = Arrangement.Center, modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        text = stringResource(id = R.string.profile),
                        Modifier.padding(15.dp),
                        fontSize = MaterialTheme.typography.titleLarge.fontSize
                    )
                }


                Row(verticalAlignment = Alignment.CenterVertically) {
                    Column {
                        Image(
                            painter = rememberAsyncImagePainter(model = state.image),
                            contentDescription = "",
                            Modifier
                                .width(200.dp)
                                .padding(15.dp)
                                .clip(RoundedCornerShape(25.dp))
                                .aspectRatio(1f)
                        )
                    }
                    Column(
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = state.firstName,
                            Modifier.padding(15.dp),
                            fontSize = MaterialTheme.typography.titleMedium.fontSize
                        )
                        Text(
                            text = state.lastName,
                            Modifier.padding(15.dp),
                            fontSize = MaterialTheme.typography.titleMedium.fontSize
                        )
                    }
                }
                Row(
                    horizontalArrangement = Arrangement.Center, modifier = Modifier.fillMaxWidth()
                ) {
                    Button(onClick = { viewModel.logOut(navigator) }, Modifier.padding(15.dp)) {
                        Text(text = stringResource(id = R.string.log_out))
                    }
                }
            }
        }

    }
}