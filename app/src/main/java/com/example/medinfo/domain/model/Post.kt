package com.example.medinfo.domain.model

import com.example.medinfo.R
import com.example.medinfo.data.remote.dto.PostDto

data class Post(
    var id: Int,
    var title: String,
    var date: String,
    var image: Int,
    var description: String
)

fun PostDto.toPost() = Post(
    id = id,
    title = title,
    date = date,
    image = R.drawable.ic_post,
    description = description
)


