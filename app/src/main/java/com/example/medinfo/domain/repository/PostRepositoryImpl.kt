package com.example.medinfo.domain.repository

import com.example.medinfo.data.PostRepository
import com.example.medinfo.data.remote.api.MedInfoApi
import com.example.medinfo.domain.model.Post
import com.example.medinfo.domain.model.toPost

class PostRepositoryImpl(private val medInfoApi: MedInfoApi) : PostRepository {

    override suspend fun getNews(): List<Post> =
        medInfoApi.getNews().map { it.toPost() }


    override suspend fun getArchives(): List<Post> =
        medInfoApi.getArchives().map { it.toPost() }


    override suspend fun getSlider(): List<Post> =
        medInfoApi.getSlider().map { it.toPost() }

}