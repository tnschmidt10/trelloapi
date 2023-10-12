package com.tns.modules

import com.tns.classes.BoardInformation
import com.tns.classes.CardInformation
import com.tns.classes.LabelInformation
import com.tns.classes.ListInformation
import com.tns.client.Requester
import com.tns.json.BoardData

class Board internal constructor(data: BoardData) {

    var id: String? = null
    var name: String? = null
    var url: String? = null
    var shortUrl: String? = null
    private var request = Requester()

    init {
        this.id = data.id
        this.name = data.name
        this.url = data.url
        this.shortUrl = data.shortUrl
    }

    fun getAllLists(): List<BoardList> {
        val boardInformation = request.createRequest(BoardInformation::class.java, "boards")
        val boardLists = mutableListOf<BoardList>()
        boardInformation.getLists(id!!).execute().run {
            if (isSuccessful) {
                body()?.forEach {
                    boardLists.add(BoardList(it))
                }
                return boardLists
            } else {
                throw Exception("Error getting lists. Error code: ${code()}")
            }
        }
    }

    fun getList(listId: String): BoardList {
        val listInformation = request.createRequest(ListInformation::class.java, "lists")
        listInformation.getListInformation(listId).execute().run {
            if (isSuccessful) {
                return BoardList(body()!!)
            } else {
                throw Exception("Error getting list. Error code: ${code()}")
            }
        }
    }

    fun getAllCards(): List<Card> {
        val boardInformation = request.createRequest(BoardInformation::class.java, "boards")
        val cardList = mutableListOf<Card>()
        boardInformation.getCards(id!!).execute().run {
            body()!!.forEach {
                cardList.add(Card(it))
            }
        }
        return cardList
    }

    fun getLabels(): List<Label> {
        val boardInformation = request.createRequest(BoardInformation::class.java, "boards")
        val labelList = mutableListOf<Label>()
        val x = boardInformation.getLabels(id!!).execute().run {
            if(isSuccessful) {
                body()!!.forEach {
                    labelList.add(Label(it))
                }
            } else {
                throw Exception("Error getting labels. Error code: ${code()}")
            }
        }

        return labelList
    }

    fun getLabelById(id: String): Label {
        val labelInformation = request.createRequest(LabelInformation::class.java, "labels")
        labelInformation.getLabelInformation(id).execute().run {
            if(isSuccessful) {
                return Label(body()!!)
            } else {
                throw Exception("Error getting label. Error code: ${code()}")
            }
        }

    }



    fun getCardById(id: String): Card {
        val cardInformation = request.createRequest(CardInformation::class.java, "cards")
        cardInformation.getCardInformation(id).execute().run {
            if(isSuccessful) {
                return Card(body()!!)
            } else {
                throw Exception("Error getting card. Error code: ${code()}")
            }
        }
    }
}