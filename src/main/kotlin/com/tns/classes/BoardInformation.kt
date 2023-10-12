package com.tns.classes

import com.tns.json.BoardData
import com.tns.json.CardData
import com.tns.json.LabelData
import com.tns.json.ListData
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

internal interface BoardInformation {
    @GET("{boardId}")
    fun getBoardInformation(@Path("boardId") boardId: String): Call<BoardData>
    @GET("{boardId}/lists")
    fun getLists(@Path("boardId") boardId: String): Call<List<ListData>>
    @GET("{boardId}/cards")
    fun getCards(@Path("boardId") boardId: String): Call<List<CardData>>
    @GET("{boardId}/labels")
    fun getLabels(@Path("boardId") boardId: String): Call<List<LabelData>>
}