package com.tns.modules

import com.tns.classes.LabelInformation
import com.tns.client.Requester
import com.tns.json.LabelData

class Label internal constructor(data: LabelData, shouldInit: Boolean = false) {

    var id: String? = data.id
    var name: String = data.name!!
    var boardId: String = data.boardId!!
    var color: String = data.color.run {
        listOf("green", "yellow", "orange", "red", "purple", "blue", "sky", "lime", "pink", "black").forEach {
            if (this == it) {
                return@run this
            }
        }
        throw IllegalArgumentException("Invalid color. Valid colors are: green, yellow, orange, red, purple, blue, sky, lime, pink, black")
    }
    private var request = Requester()

    init {
        if(shouldInit) {
            val labelInformation = request.createRequest(LabelInformation::class.java, "")
            val label = labelInformation.createLabel(name, color, boardId).execute().run {
                if(!isSuccessful) {
                    throw Exception("Error creating label. Error code: ${code()}")
                }
                body().let {
                    return@let it
                }?: throw Exception("Error creating label. Error code: ${code()}")
            }
            this.id = label.id
        }
    }

    fun update() {
        val labelData = request.createRequest(LabelInformation::class.java, "labels")
        labelData.updateLabel(id!!, name, color).execute().run {
            if(!isSuccessful) {
                throw Exception("Error updating label. Error code: ${code()}")
            }
        }
    }

    fun delete() {
        val labelData = request.createRequest(LabelInformation::class.java, "labels")
        labelData.deleteLabel(id!!).execute().run {
            if(!isSuccessful) {
                throw Exception("Error deleting label. Error code: ${code()}")
            }
        }
    }

}