package com.example.medinfo.domain.use_case

import com.example.medinfo.data.PostRepository
import com.example.medinfo.data.Resource
import com.example.medinfo.data.fetchResources
import com.example.medinfo.domain.model.Post
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class PostUseCase(private val postRepository: PostRepository) {

    operator fun invoke(postType: PostType): Flow<Resource<List<Post>>> = flow {
        fetchResources(this) {
            when (postType) {
                PostType.NEWS -> postRepository.getNews()
                PostType.ARCHIVES -> postRepository.getArchives()
                PostType.SLIDER -> postRepository.getSlider()
            }
        }
    }
}

enum class PostType {
    NEWS, ARCHIVES, SLIDER
}