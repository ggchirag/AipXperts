package com.example.aipxperts

import com.google.gson.annotations.SerializedName

data class response_model(
    @SerializedName("postId")
    var postid:Int,

    @SerializedName("id")
    var id:Int,

    @SerializedName("name")
    var name:String,

    @SerializedName("email")
    var email:String,

    @SerializedName("body")
    var body:String
)
