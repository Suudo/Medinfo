package com.example.medinfo.ui.screens.news

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
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
class NewsViewModel @Inject constructor(
    private val postUseCase: PostUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(NewsState())
    var state = _state.asStateFlow()

    init {
        getNews()
    }

    private fun getNews() {
        viewModelScope.launch {
            postUseCase(PostType.NEWS).collectLatest { response ->
                handleResponse(
                    response = response,
                    onSuccess = { _state.update { it.copy(news = response.data) } },
                    onLoading = { _state.update { it.copy(loading = true) } },
                    onError = { _state.update { it.copy(error = response.error) } }
                )
            }
        }
    }

}

data class NewsState(
    val news: List<Post>? = null,
    val loading: Boolean = true,
    val error: ErrorType? = null
)