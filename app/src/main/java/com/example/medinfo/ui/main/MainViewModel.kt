package com.example.medinfo.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
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
class MainViewModel @Inject constructor(
    private val postUseCase: PostUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(MainState())
    val state = _state.asStateFlow()

    init {
        getSliders()
    }

    private fun getSliders() {
        viewModelScope.launch {
            postUseCase.invoke(PostType.SLIDER).collectLatest { response ->
                handleResponse(
                    response = response,
                    onSuccess = { _state.update { it.copy(slider = response.data) } },
                )
            }
        }
    }
}

data class MainState(
    val slider: List<Post>? = null
)