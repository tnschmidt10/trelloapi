package com.tns.classes

import com.tns.json.CardData
import retrofit2.Call
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path
import retrofit2.http.Query

internal interface CardInformation {
    @GET("{cardId}")
    fun getCardInformation(@Path("cardId") cardId: String): Call<CardData>
    @PUT("{cardId}/idLabels")
    fun addLabel(@Path("cardId") cardId: String, @Query("value") labelId: String): Call<Void>
    @POST("{cardId}/idMembers")
    fun addMember(@Path("cardId") cardId: String, @Path("memberId") memberId: String): Call<CardData>
    @POST("{cardId}/actions/comments")
    fun addComment(@Path("cardId") cardId: String, @Query("text") comment: String): Call<CardData>
    @DELETE("{cardId}/idLabels/{labelId}")
    fun removeLabel(@Path("cardId") cardId: String, @Path("labelId") labelId: String): Call<CardData>
    @DELETE("{cardId}/idMembers/{memberId}")
    fun removeMember(@Path("cardId") cardId: String, @Path("memberId") memberId: String): Call<CardData>
    @DELETE("{cardId}/actions/{actionId}/comments")
    fun removeComment(@Path("cardId") cardId: String, @Path("actionId") actionId: String): Call<CardData>
    @PUT("{cardId}")
    fun updateCard(
        @Path("cardId") cardId: String,
        @Query("name") name: String?,
        @Query("desc") description: String?,
        @Query("pos") position: Int?,
        @Query("due") dueDate: String?,
        @Query("dueComplete") isDueComplete: Boolean?,
        @Query("idMembers") memberIds: List<String>?,
        @Query("idLabels") labelIds: List<String>?,
        @Query("idList") listId: String?,
    ): Call<CardData>
    @DELETE("{cardId}")
    fun deleteCard(@Path("cardId") cardId: String): Call<CardData>
    @PUT("{cardId}")
    fun moveCard(@Path("cardId") cardId: String, @Query("idList") listId: String): Call<CardData>
    @GET("{cardId}/idLabels")
    fun getLabels(@Path("cardId") cardId: String): Call<List<String>>
}