package com.skyline_info_system.baseapp.models.response


import com.google.gson.annotations.SerializedName

data class VideoListResponse(
    @SerializedName("hits") val hits: ArrayList<Hit>?,
    @SerializedName("page") val page: Int?,
    @SerializedName("page_size") val pageSize: Int?,
    @SerializedName("pages") val pages: Int?,
    @SerializedName("total") val total: Int?,
) {
    data class Hit(
        @SerializedName("aspect_ratio") val aspectRatio: String?,
        @SerializedName("created_at") val createdAt: String?,
        @SerializedName("description") val description: String?,
        @SerializedName("downloads") val downloads: Int?,
        @SerializedName("duration") val duration: Double?,
        @SerializedName("id") val id: String?,
        @SerializedName("is_vertical") val isVertical: Boolean?,
        @SerializedName("max_height") val maxHeight: Int?,
        @SerializedName("max_width") val maxWidth: Int?,
        @SerializedName("poster") val poster: String?,
        @SerializedName("published_at") val publishedAt: String?,
        @SerializedName("tags") val tags: List<String?>?,
        @SerializedName("thumbnail") val thumbnail: String?,
        @SerializedName("title") val title: String?,
        @SerializedName("updated_at") val updatedAt: String?,
        @SerializedName("urls") val urls: Urls?,
        @SerializedName("views") val views: Int?,
    ) {
        data class Urls(
            @SerializedName("mp4") val mp4: String?,
            @SerializedName("mp4_download") val mp4Download: String?,
            @SerializedName("mp4_preview") val mp4Preview: String?,
        )
    }
}