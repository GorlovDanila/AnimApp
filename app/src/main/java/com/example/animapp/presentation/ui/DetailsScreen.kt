package com.example.animapp.presentation.ui

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImagePainter
import coil.compose.SubcomposeAsyncImage
import coil.compose.SubcomposeAsyncImageContent
import com.example.animapp.domain.anim.AnimInfo
import com.example.animapp.presentation.presenters.DetailsAction
import com.example.animapp.presentation.presenters.DetailsEvent
import com.example.animapp.presentation.presenters.DetailsViewModel
import com.example.animapp.presentation.presenters.DetailsViewState
import org.koin.androidx.compose.koinViewModel

@Composable
fun DetailsScreen(
    id: Int,
    viewModel: DetailsViewModel = koinViewModel(),
) {

    val state = viewModel.state.collectAsStateWithLifecycle()
    val action by viewModel.action.collectAsStateWithLifecycle(null)

    DetailsContent(
        id = id,
        viewState = state.value,
        eventHandler = viewModel::event
    )

    DetailsScreenActions(
        viewAction = action
    )
}

@Composable
fun DetailsContent(
    id: Int,
    viewState: DetailsViewState,
    eventHandler: (DetailsEvent) -> Unit,
) {

    if (viewState.animInfo == null) {
        eventHandler.invoke(DetailsEvent.OnLoadAnimById(id))
        if (viewState.isLoading) {
            CircularProgressIndicator(
                modifier = Modifier
                    .fillMaxSize()
                    .wrapContentWidth(Alignment.CenterHorizontally)
                    .wrapContentHeight(Alignment.CenterVertically)
            )
        }
    } else {
        DetailItem(viewState.animInfo)
    }
}

@Composable
private fun DetailsScreenActions(
    viewAction: DetailsAction?
) {
    val context = LocalContext.current
    LaunchedEffect(viewAction) {
        when (viewAction) {
            null -> Unit
            is DetailsAction.ShowToast -> {
                Toast.makeText(context, viewAction.text, Toast.LENGTH_LONG).show()
            }
        }
    }
}

@Composable
fun DetailItem(
    animInfo: AnimInfo?,
) {
    LazyColumn(modifier = Modifier
        .fillMaxSize()
        .background(Color.LightGray)) {
        item {
            Column(
                modifier = Modifier.padding(12.dp)
            ) {
                Row(
                    horizontalArrangement = Arrangement.Center,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    SubcomposeAsyncImage(
                        model = animInfo?.largeImageUrl,
                        contentDescription = null,
                    ) {
                        val state = painter.state
                        if (state is AsyncImagePainter.State.Loading || state is AsyncImagePainter.State.Error) {
                            CircularProgressIndicator(
                                modifier = Modifier.padding(
                                    vertical = 48.dp,
                                    horizontal = 36.dp
                                )
                            )
                        } else {
                            SubcomposeAsyncImageContent(modifier = Modifier.padding(vertical = 16.dp))
                        }
                    }
                }
                Column(modifier = Modifier.fillMaxWidth()) {
                    Text(
                        text = "${animInfo?.title}",
                        textAlign = TextAlign.Start,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(4.dp)
                    )
                    Text(
                        text = "Type: ${animInfo?.type}",
                        textAlign = TextAlign.Start,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(4.dp)
                    )
                    Text(
                        text = "Year: ${animInfo?.year}",
                        textAlign = TextAlign.Start,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(4.dp)
                    )
                    Text(
                        text = "Status: ${animInfo?.status}",
                        textAlign = TextAlign.Start,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(4.dp)
                    )
                    Text(
                        text = "Genres: ${
                            animInfo?.genres.toString().replace("[", "").replace("]", "")
                        }",
                        textAlign = TextAlign.Start,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(4.dp)
                    )
                    Text(
                        text = "Episodes: ${animInfo?.episodes}",
                        textAlign = TextAlign.Start,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(4.dp)
                    )
                    Text(
                        text = "${animInfo?.synopsis}",
                        textAlign = TextAlign.Start,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(4.dp)
                    )
                }
            }
        }
    }
}
