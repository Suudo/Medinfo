package com.example.medinfo.data

import com.example.medinfo.domain.model.Post

interface PostRepository {

    suspend fun getNews(): List<Post>

    suspend fun getArchives(): List<Post>

    suspend fun getSlider(): List<Post>

}