package com.example.animapp.presentation.presenters

import androidx.compose.runtime.Immutable
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.animapp.domain.anim.model.AnimInfo
import com.example.animapp.domain.anim.model.AnimListInfo
import com.example.animapp.domain.anim.remote.GetAnimListUseCase
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch
import retrofit2.HttpException

@Immutable
data class MainViewState(
    val isLoading: Boolean = false,
    val anims: AnimListInfo? = null,
    val animInfo: AnimInfo? = null,
)

sealed interface MainEvent {
    object OnLoadAnim : MainEvent
    data class OnAnimClick(val animInfo: AnimInfo) : MainEvent
}

sealed interface MainAction {
    data class Navigate(val animId: Int) : MainAction
    data class ShowToast(val text: String) : MainAction
}

class MainViewModel(
    private val getAnimListUseCase: GetAnimListUseCase,
) : ViewModel() {

    private val _state = MutableStateFlow(MainViewState())
    val state: StateFlow<MainViewState>
        get() = _state.asStateFlow()

    private val _action = MutableSharedFlow<MainAction?>()
    val action: SharedFlow<MainAction?>
        get() = _action.asSharedFlow()

    fun event(mainEvent: MainEvent) {
        when (mainEvent) {
            is MainEvent.OnLoadAnim -> onLoadAnim()
            is MainEvent.OnAnimClick -> onAnimClick(mainEvent.animInfo)
        }
    }

    private fun onLoadAnim() {
        viewModelScope.launch {
            try {
                _state.emit(
                    _state.value.copy(
                        isLoading = true
                    )
                )
                _state.emit(
                    _state.value.copy(
                        anims = getAnimListUseCase.invoke()
                    )
                )
            } catch (e: HttpException) {
                _action.emit(MainAction.ShowToast("An unexpected error has occurred: ${e}!"))
            } finally {
                _state.emit(
                    _state.value.copy(
                        isLoading = false
                    )
                )
            }
        }
    }

    private fun onAnimClick(animInfo: AnimInfo) {
        viewModelScope.launch { _action.emit(MainAction.Navigate(animId = animInfo.malId ?: 0)) }
    }
}
