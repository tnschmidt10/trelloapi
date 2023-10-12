package com.tns.json

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty

@JsonIgnoreProperties(ignoreUnknown = true)
internal data class CardData (
        @JsonProperty("id")
        val id: String?,
        @JsonProperty("name")
        val name: String?,
        @JsonProperty("desc")
        val description: String?,
        @JsonProperty("due")
        val dueDate: String?,
        @JsonProperty("dueComplete")
        val isDueComplete: Boolean?,
        @JsonProperty("idList")
        val listId: String?,
        @JsonProperty("idMembers")
        val memberIds: List<String>?,
        @JsonProperty("idLabels")
        val labelIds: List<String>?,
        @JsonProperty("idBoard")
        val boardId: String?,
        @JsonProperty("url")
        val url: String?,
        @JsonProperty("shortUrl")
        val shortUrl: String?,
        @JsonProperty("pos")
        val position: Int?,
        @JsonProperty("labels")
        val labels: List<LabelData>?,
)