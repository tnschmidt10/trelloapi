package com.tns.modules

import com.tns.classes.CardInformation
import com.tns.classes.ListInformation
import com.tns.client.Requester
import com.tns.json.ListData

class BoardList internal constructor (data: ListData) {

    var id: String? = null
    var name: String? = null
    var boardId: String? = null
    var position: Int? = null
    private var request = Requester()


    init {
        this.id = data.id
        this.name = data.name
        this.boardId = data.boardId
        this.position = data.position
    }
    fun getCards(): List<Card> {
        val listInformation = request.createRequest(ListInformation::class.java, "lists")
        val cardList = mutableListOf<Card>()
        listInformation.getCards(id!!).execute().run {
            if (isSuccessful) {
                body()!!.forEach {
                    cardList.add(Card(it))
                }
                return cardList
            } else {
                throw Exception("Error getting cards. Error code: ${code()}")
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