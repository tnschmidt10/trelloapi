package com.tns.json

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty

@JsonIgnoreProperties(ignoreUnknown = true)
internal data class BoardData (
        @JsonProperty("id")
        val id: String?,
        @JsonProperty("name")
        val name: String?,
        @JsonProperty("desc")
        val description: String?,
        @JsonProperty("url")
        val url: String?,
        @JsonProperty("shortUrl")
        val shortUrl: String?,
)