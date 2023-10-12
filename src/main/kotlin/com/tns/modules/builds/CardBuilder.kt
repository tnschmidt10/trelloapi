package com.tns.modules.builds

import com.tns.json.CardData
import com.tns.modules.Card
import java.util.Date

class CardBuilder() {
    /**
     * [CardBuilder] is a class that is used to build a [Card] object.
     * @param name The name of the card.
     * @param description The description of the card.
     * @param position The position of the card. (0 is the top of the list)
     * @param listId The id of the list that the card is in.
     * @param dueDate The due date of the card. ([Date] object)
     * @param isDueComplete Whether the card is due.
     * @param memberIds The ids of the members that are assigned to the card.
     * @return A [Card] object.
     * This [Card] automatically initializes itself when it is built.
     */
    private var name: String? = null
    private var description: String? = null
    private var position: Int? = null
    private var listId: String? = null
    private var dueDate: Date? = null
    private var isDueComplete: Boolean? = null
    private var memberIds: List<String>? = null

    fun setName(name: String) = apply { this.name = name }
    fun setDescription(description: String) = apply { this.description = description } // retrofit automatically encodes urls
    fun setPosition(position: Int) = apply { this.position = position }
    fun setListId(listId: String) = apply { this.listId = listId }
    fun setDueDate(dueDate: Date) = apply { this.dueDate = dueDate }
    fun isDueComplete(isDueComplete: Boolean) = apply { this.isDueComplete = isDueComplete }
    fun setMemberIds(memberIds: List<String>) = apply { this.memberIds = memberIds }

    fun build(): Card {
        val data = CardData(null, name, description, dueDate.toString(), isDueComplete, listId, memberIds, null, null, null, null, position, null)
        return Card(data, true)
    }
}