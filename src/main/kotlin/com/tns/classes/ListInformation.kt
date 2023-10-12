package com.tns.classes

import com.tns.json.CardData
import com.tns.json.ListData
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

internal interface ListInformation {
    @GET("{listId}")
    fun getListInformation(@Path("listId") listId: String): Call<ListData>
    @GET("{listId}/cards")
    fun getCards(@Path("listId") listId: String): Call<List<CardData>>
    @POST("{listId}/cards")
    fun createCard(
        @Path("listId") listId: String,
        @Query("name") name: String?,
        @Query("desc") description: String?,
        @Query("pos") position: Int?,
        @Query("due") dueDate: String?,
        @Query("dueComplete") isDueComplete: Boolean?,
        @Query("idMembers") memberIds: List<String>?,
        @Query("idLabels") labelIds: List<String>?,
    ): Call<CardData>
}