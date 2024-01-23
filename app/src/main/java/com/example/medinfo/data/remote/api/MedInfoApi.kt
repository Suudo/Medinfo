package com.example.medinfo.data.remote.api

import com.example.medinfo.data.remote.dto.PostDto
import retrofit2.http.GET

interface MedInfoApi {

    @GET("")
    suspend fun getNews(): List<PostDto>

    @GET("")
    suspend fun getArchives(): List<PostDto>

    @GET
    suspend fun getSlider(): List<PostDto>
}