package com.example.retrofitstudy

import com.google.gson.annotations.SerializedName

data class ContentItemDto(
    @SerializedName("name")
    val name: String,

    @SerializedName("id")
    val id: String,

    @SerializedName("assetName")
    val assetName: String,

    @SerializedName("assetPath")
    val assetPath: String)
