package com.example.retrofitstudy

import com.google.gson.annotations.SerializedName

data class ContentDto(
    @SerializedName("version")
    val version : String,

    @SerializedName("characters")
    val characters : ArrayList<ContentItemDto>,

    @SerializedName("maps")
    val maps : ArrayList<ContentItemDto>
)