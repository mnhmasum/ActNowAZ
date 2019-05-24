package com.namageoff.actnowaz.features.main

import com.google.gson.annotations.SerializedName

data class NewsResponse(
    @SerializedName("title") val title: String,
    @SerializedName("description") val description: String,
    @SerializedName("report") val report: String,
    @SerializedName("date") val date: String,
    @SerializedName("imageURL") val imageURL: String,
    @SerializedName("link") val link: String,
    @SerializedName("phone") val phone: String
)