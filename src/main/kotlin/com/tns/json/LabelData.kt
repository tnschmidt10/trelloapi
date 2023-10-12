package com.tns.json

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty

@JsonIgnoreProperties(ignoreUnknown = true)
internal data class LabelData (
    @JsonProperty("id")
    val id: String?,
    @JsonProperty("name")
    val name: String?,
    @JsonProperty("color")
    val color: String?,
    @JsonProperty("idBoard")
    val boardId: String?,
)
