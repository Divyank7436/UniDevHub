package com.example.unidevhub.models

data class Post(
    val username: String = "",
    val userImage: String = "",
    var title: String = "",
    var description: String = "",
    var image: String = "",
    var video: String = "",
    var likes: Int = 0,
    var likeFlag: Boolean = false, // Add comments property
)
