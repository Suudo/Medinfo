package com.example.medinfo.ui.screens.archive

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.medinfo.data.ErrorType
import com.example.medinfo.data.handleResponse
import com.example.medinfo.domain.model.Post
import com.example.medinfo.domain.use_case.PostType
import com.example.medinfo.domain.use_case.PostUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ArchiveViewModel @Inject constructor(
    private val postUseCase: PostUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(ArchiveState())
    val state = _state.asStateFlow()

    init {
        getArchives()
    }

    private fun getArchives() {
        viewModelScope.launch {
            postUseCase(PostType.ARCHIVES).collectLatest { respone ->
                handleResponse(
                    response = respone,
                    onSuccess = { _state.update { it.copy(archives = respone.data) } },
                    onLoading = { _state.update { it.copy(loading = true) } },
                    onError = { _state.update { it.copy(error = respone.error) } }
                )
            }
        }
    }
}

data class ArchiveState(
    var archives: List<Post>? = null,
    var loading: Boolean = true,
    var error: ErrorType? = null
)