package com.tns.modules

import com.tns.classes.CardInformation
import com.tns.classes.LabelInformation
import com.tns.classes.ListInformation
import com.tns.client.Requester
import com.tns.json.CardData
import java.lang.StringBuilder
import kotlin.jvm.Throws

class Card internal constructor(data: CardData, shouldInit: Boolean = false) {

    var id: String? = null
    var name: String? = null
    var description: String? = null
    var position: Int? = null
    var listId: String? = null
    var dueDate: String? = null
    var boardId: String? = null
    var isDueComplete: Boolean? = null
    var memberIds: List<String>? = null
    var shortUrl: String? = null
    var url: String? = null
    var labelIds: List<String>? = null
    var labels: List<Label>? = null
    private var request = Requester()

    init {
        this.id = data.id
        this.name = data.name
        this.description = data.description
        this.position = data.position
        this.listId = data.listId
        this.dueDate = data.dueDate
        this.boardId = data.boardId
        this.isDueComplete = data.isDueComplete
        this.memberIds = data.memberIds
        this.labelIds = data.labelIds
        this.shortUrl = data.shortUrl
        this.url = data.url
        this.labels = data.labels?.map { Label(it) }

        if (shouldInit) {
            val listInformation = request.createRequest(ListInformation::class.java, "lists")
            val newCard = listInformation.createCard(listId!!, name, description, position, dueDate, isDueComplete, memberIds, labelIds).execute().body()
            this.id = newCard!!.id
            this.url = newCard.url
            this.shortUrl = newCard.shortUrl
            this.listId = newCard.listId
            this.labelIds = newCard.labelIds
            this.boardId = newCard.boardId
        }
    }

    fun addMember(memberId: String) {
        memberIds!!.plus(memberId)
        update()
    }

    fun update() {
        val cardInfo = request.createRequest(CardInformation::class.java, "cards")
        cardInfo.updateCard(id!!, name, description, position, dueDate, isDueComplete, memberIds, labelIds, listId).execute()
    }

    fun addComment(comment: String) {
        val cardInfo = request!!.createRequest(CardInformation::class.java, "cards")
        cardInfo.addComment(id!!, comment).execute().run {
            if(!isSuccessful) {
                throw Exception("Error adding comment. Error code: ${code()}")
            }
        }
    }

    fun completeDueDate() {
        this.isDueComplete = true
        val cardInfo = request!!.createRequest(CardInformation::class.java, "cards")
        val x = cardInfo.updateCard(id!!, name, description, position, dueDate, isDueComplete, memberIds, labelIds, listId).execute()
        if(!x.isSuccessful) {
            throw Exception("Failed to complete due date ${x.code()}")
        }
    }

    fun deleteComment(commentId: String) {
        val cardInfo = request!!.createRequest(CardInformation::class.java, "cards")
        val x = cardInfo.removeComment(id!!, commentId).execute()
        if(!x.isSuccessful) {
            throw Exception("Failed to delete comment ${x.code()}")
        }
    }

    fun delete() {
        val cardInfo = request!!.createRequest(CardInformation::class.java, "cards")
        val x = cardInfo.deleteCard(id!!).execute()
        if(!x.isSuccessful) {
            throw Exception("Failed to delete card ${x.code()}")
        }
    }

    fun moveCard(listId: String) {
        val cardInfo = request!!.createRequest(CardInformation::class.java, "cards")
        val x = cardInfo.moveCard(id!!, listId).execute()
        if(!x.isSuccessful) {
            throw Exception("Failed to move card ${x.code()}")
        }
    }

    fun retrieveLabels(): List<Label> {
        val cardInfo = request.createRequest(CardInformation::class.java, "cards")
        val labels = cardInfo.getLabels(id!!).execute().body()
        val labelList = mutableListOf<Label>()
        labels!!.forEach {
            val labelInfo = request.createRequest(LabelInformation::class.java, "labels")
            val label = labelInfo.getLabelInformation(it).execute().body()
            labelList.add(Label(label!!))
        }
        return labelList
    }

    fun getLabels(): List<Label>? {
        return labels;
    }

    fun setLabel(labelId: String) {
        val cardInfo = request.createRequest(CardInformation::class.java, "cards")
        cardInfo.addLabel(id!!, labelId).execute()
        this.labelIds = this.labelIds!!.plus(labelId)
    }

    fun setLabel(label: Label) {
        val cardInfo = request.createRequest(CardInformation::class.java, "cards")
        cardInfo.addLabel(id!!, label.id!!).execute().run {
            if(!isSuccessful) {
                throw Exception("Failed to set label ${code()}")
            }
        }
        this.labelIds = this.labelIds!!.plus(label.id!!)
    }

    fun removeLabel(labelId: String) {
        val cardInfo = request.createRequest(CardInformation::class.java, "cards")
        cardInfo.removeLabel(id!!, labelId).execute().run {
            if(!isSuccessful) {
                throw Exception("Failed to remove label ${code()}")
            }
        }
        this.labelIds = this.labelIds!!.minus(labelId)
    }

    fun removeLabel(label: Label) {
        val cardInfo = request.createRequest(CardInformation::class.java, "cards")
        cardInfo.removeLabel(id!!, label.id!!).execute().run {
            if(!isSuccessful) {
                throw Exception("Failed to remove label ${code()}")
            }
        }
        this.labelIds = this.labelIds!!.minus(label.id!!)
    }

    @Throws(Exception::class)
    fun setLabels(vararg labelIds: String) {
        val cardInfo = request!!.createRequest(CardInformation::class.java, "cards")
        val encodedList = StringBuilder()
        encodedList.append(labelIds[0])
        for (i in 1 until labelIds.size) {
            encodedList.append("," + labelIds[i])
        }
        val x = cardInfo.addLabel(id!!, encodedList.toString()).execute()
        // Check if successful
        if (!x.isSuccessful) {
            throw Exception("Failed to add labels! Error code: ${x.code()}")
        }
        this.labelIds = this.labelIds!!.plus(labelIds)
    }

    @Throws(Exception::class)
    fun setLabels(vararg labels: Label) {
        val cardInfo = request!!.createRequest(CardInformation::class.java, "cards")
        val encodedList = StringBuilder()
        encodedList.append(labels[0].id)
        for (i in 1 until labels.size) {
            encodedList.append("," + labels[i].id)
        }
        val x = cardInfo.addLabel(id!!, encodedList.toString()).execute()
        // Check if successful
        if (!x.isSuccessful) {
            throw Exception("Failed to add labels! Error code: ${x.code()}")
        }
        this.labelIds = listOf(this.labelIds!!.plus (
            labels.forEach {
                it.id
            }
        ).toString())
    }



}