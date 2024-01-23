package com.example.medinfo.domain.model

import com.example.medinfo.data.remote.dto.PostDto

data class Post(
    var id: Int,
    var title: String,
    var date: String,
    var image: String,
    var description: String
)

fun PostDto.toPost() = Post(
    id = id,
    title = title,
    date = date,
    image = image,
    description = description
)


