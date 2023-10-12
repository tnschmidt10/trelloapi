package com.tns.classes

import com.tns.json.LabelData
import retrofit2.Call
import retrofit2.http.*

internal interface LabelInformation {
    @GET("{labelId}")
    fun getLabelInformation(@Path("labelId") labelId: String): Call<LabelData>
    @PATCH("{labelId}")
    fun updateLabel(
        @Path("labelId") labelId: String,
        @Query("name") name: String?,
        @Query("color") color: String?,
    ): Call<LabelData>
    @DELETE("{labelId}")
    fun deleteLabel(@Path("labelId") labelId: String): Call<LabelData>
    @POST("labels")
    fun createLabel(@Query("name") name: String, @Query("color") color: String, @Query("idBoard") boardId: String): Call<LabelData>
}