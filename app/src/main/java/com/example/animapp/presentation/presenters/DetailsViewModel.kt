package com.example.animapp.presentation.presenters

import androidx.compose.runtime.Immutable
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.animapp.domain.anim.AnimInfo
import com.example.animapp.domain.anim.GetAnimByIdUseCase
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch
import retrofit2.HttpException

@Immutable
data class DetailsViewState(
    val isLoading: Boolean = false,
    val animInfo: AnimInfo? = null,
)

sealed interface DetailsEvent {
    data class OnLoadAnimById(val id: Int) : DetailsEvent
}

sealed interface DetailsAction {
    data class ShowToast(val text: String) : DetailsAction
}

class DetailsViewModel(
    private val getAnimByIdUseCase: GetAnimByIdUseCase,
) : ViewModel() {
    private val _state = MutableStateFlow(DetailsViewState())
    val state: StateFlow<DetailsViewState>
        get() = _state.asStateFlow()

    private val _action = MutableSharedFlow<DetailsAction?>()
    val action: SharedFlow<DetailsAction?>
        get() = _action.asSharedFlow()

    fun event(detailEvent: DetailsEvent) {
        when (detailEvent) {
            is DetailsEvent.OnLoadAnimById -> onLoadAnimById(detailEvent.id)
        }
    }

    private fun onLoadAnimById(id: Int) {
        viewModelScope.launch {
            try {
                _state.emit(
                    _state.value.copy(
                        isLoading = true
                    )
                )
                _state.emit(
                    _state.value.copy(
                        animInfo = getAnimByIdUseCase.invoke(id)
                    )
                )
            } catch (e: HttpException) {
                _action.emit(DetailsAction.ShowToast("An unexpected error has occurred: ${e}!"))
            } finally {
                _state.emit(
                    _state.value.copy(
                        isLoading = false
                    )
                )
            }
        }
    }
}
