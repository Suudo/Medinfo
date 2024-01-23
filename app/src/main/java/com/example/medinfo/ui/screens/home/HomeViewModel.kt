package com.example.medinfo.ui.screens.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.medinfo.data.ErrorType
import com.example.medinfo.data.Resource
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
class HomeViewModel @Inject constructor(
    private val postUseCase: PostUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(HomeState())
    val state = _state.asStateFlow()

    init {
        getNews()
        getArchives()
    }

    private fun getNews() {
        viewModelScope.launch {
            postUseCase(PostType.NEWS).collectLatest { response ->
                handleResponse(
                    response = response,
                    onSuccess = { _state.update { it.copy(news = response.data) } },
                    onLoading = { _state.update { it.copy(newsLoading = true) } },
                    onError = { _state.update { it.copy(error = response.error) } }
                )
            }
        }
    }

    private fun getArchives() {
        viewModelScope.launch {
            postUseCase(PostType.ARCHIVES).collectLatest { response ->
                handleResponse(
                    response = response,
                    onSuccess = { _state.update { it.copy(archives = response.data) } },
                    onLoading = { _state.update { it.copy(archivesLoading = true) } },
                    onError = { _state.update { it.copy(error = response.error) } }
                )
            }
        }
    }

}

data class HomeState(
    val news: List<Post>? = null,
    val archives: List<Post>? = null,
    val newsLoading: Boolean = true,
    val archivesLoading: Boolean = true,
    val error: ErrorType? = null
)