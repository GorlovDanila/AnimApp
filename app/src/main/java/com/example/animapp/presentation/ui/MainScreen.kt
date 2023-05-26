package com.example.animapp.presentation.ui

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import coil.compose.AsyncImagePainter
import coil.compose.SubcomposeAsyncImage
import coil.compose.SubcomposeAsyncImageContent
import com.example.animapp.R
import com.example.animapp.domain.anim.model.AnimInfo
import com.example.animapp.presentation.presenters.MainAction
import com.example.animapp.presentation.presenters.MainEvent
import com.example.animapp.presentation.presenters.MainViewModel
import com.example.animapp.presentation.presenters.MainViewState
import com.example.animapp.presentation.ui.theme.MyTheme
import org.koin.androidx.compose.koinViewModel

@Composable
fun MainScreen(
    navController: NavController,
    viewModel: MainViewModel = koinViewModel(),
) {

    val state = viewModel.state.collectAsStateWithLifecycle()
    val action by viewModel.action.collectAsStateWithLifecycle(null)

    MainContent(
        viewState = state.value,
        eventHandler = viewModel::event
    )

    MainScreenActions(
        navController = navController,
        viewAction = action
    )
}

@Composable
fun MainContent(
    viewState: MainViewState,
    eventHandler: (MainEvent) -> Unit,
) {
    if (viewState.anims == null) {
        eventHandler.invoke(MainEvent.OnLoadAnim)
        if (viewState.isLoading) {
            CircularProgressIndicator(
                modifier = Modifier
                    .fillMaxSize()
                    .wrapContentWidth(Alignment.CenterHorizontally)
                    .wrapContentHeight(Alignment.CenterVertically)
            )
        }
    } else {
        AnimList(viewState, eventHandler)
    }
}

@Composable
private fun MainScreenActions(
    navController: NavController,
    viewAction: MainAction?
) {
    val context = LocalContext.current
    val prefix = stringResource(id = R.string.details_prefix)
    LaunchedEffect(viewAction) {
        when (viewAction) {
            null -> Unit
            is MainAction.Navigate -> {
                navController.navigate(prefix + viewAction.animId)
            }
            is MainAction.ShowToast -> {
                Toast.makeText(context, viewAction.text, Toast.LENGTH_LONG).show()
            }
        }
    }
}

@Composable
fun MyListItem(
    animInfo: AnimInfo,
    onClick: (AnimInfo) -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(12.dp)
            .clickable {
                onClick.invoke(animInfo)
            },
        elevation = CardDefaults.cardElevation(12.dp),
    ) {
        Row(horizontalArrangement = Arrangement.SpaceEvenly) {
            SubcomposeAsyncImage(
                model = animInfo.imageUrl,
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
                    SubcomposeAsyncImageContent(modifier = Modifier.padding(12.dp))
                }
            }

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 8.dp, vertical = 12.dp),
            ) {
                Text(
                    text = "${animInfo.title}",
                    textAlign = TextAlign.Center,
                    style = MyTheme.typography.globalTextStyle
                )
                Text(
                    text = "Type: ${animInfo.type}",
                    textAlign = TextAlign.Center,
                    style = MyTheme.typography.globalTextStyle
                )
                Text(
                    text = "Year: ${animInfo.year}",
                    textAlign = TextAlign.Center,
                    style = MyTheme.typography.globalTextStyle
                )
                Text(
                    text = animInfo.genres.toString().replace("[", "").replace("]", ""),
                    textAlign = TextAlign.Center,
                    style = MyTheme.typography.globalTextStyle
                )
                Row {
                    Text(
                        text = "${animInfo.synopsis}",
                        textAlign = TextAlign.Start,
                        maxLines = 3,
                        overflow = TextOverflow.Ellipsis,
                        modifier = Modifier.padding(vertical = 4.dp),
                        style = MyTheme.typography.globalTextStyle
                    )
                }
            }
        }
    }
}

@Composable
fun AnimList(
    viewState: MainViewState,
    eventHandler: (MainEvent) -> Unit,
) {

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(MyTheme.colors.primaryBackground)
    ) {
        viewState.anims?.listAnim?.let { list ->
            items(
                list.size,
                key = { list[it].title.toString() }) { index ->
                MyListItem(
                    animInfo = list[index],
                    onClick = { eventHandler.invoke(MainEvent.OnAnimClick(it)) }
                )
            }
        }
    }
}
