package com.example.medinfo.domain.repository

import com.example.medinfo.data.repository.PostRepository
import com.example.medinfo.data.remote.api.MedInfoApi
import com.example.medinfo.data.remote.dto.PostDto
import com.example.medinfo.domain.model.Post
import com.example.medinfo.domain.model.toPost
import javax.inject.Inject

class PostRepositoryImpl @Inject constructor(
    private val medInfoApi: MedInfoApi
) : PostRepository {

    override suspend fun getNews() = news.map { it.toPost() }

    override suspend fun getArchives() = archives.map { it.toPost() }

    override suspend fun getSlider() = sliders.map { it.toPost() }

    private val news = listOf(
        PostDto(0, "news", "date", "", "decription"),
        PostDto(1, "news", "date", "", "decription"),
        PostDto(2, "news", "date", "", "decription"),
        PostDto(3, "news", "date", "", "decription")
    )

    private val archives = listOf(
        PostDto(0, "archives", "date", "", "decription"),
        PostDto(1, "archives", "date", "", "decription"),
        PostDto(2, "archives", "date", "", "decription"),
        PostDto(3, "archives", "date", "", "decription")
    )


    private val sliders = listOf(
        PostDto(0, "sliders", "date", "", "decription"),
        PostDto(1, "sliders", "date", "", "decription"),
        PostDto(2, "sliders", "date", "", "decription"),
        PostDto(3, "sliders", "date", "", "decription")
    )

}