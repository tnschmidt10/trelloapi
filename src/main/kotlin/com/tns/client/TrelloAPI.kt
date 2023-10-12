package com.tns.client

import com.tns.classes.BoardInformation
import com.tns.modules.Board

class TrelloAPI constructor(var key: String, var token: String) {

    internal companion object {
        private var instance: TrelloAPI? = null

        internal fun getInstance(): TrelloAPI {
            if (instance == null) {
                throw Exception("TrelloAPI has not been initialized")
            }
            return instance!!
        }
    }

    init {
        instance = this
    }

    fun getBoard(boardId: String): Board {
        val request = Requester()
        val boardInformation = request.createRequest(BoardInformation::class.java, "boards")
        val boardData = boardInformation.getBoardInformation(boardId).execute().body()
        val board = Board(boardData!!)
        return board
    }
}