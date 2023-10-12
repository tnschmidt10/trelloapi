package com.tns.modules.builds

import com.tns.json.LabelData
import com.tns.modules.Board
import com.tns.modules.Label

class LabelBuilder constructor() {
    private var name: String? = null
    private var color: String? = null
    private var board: Board? = null
    private var key: String? = null
    private var token: String? = null

    fun setName(name: String) = apply { this.name = name }
    fun setColor(color: String) = apply { this.color = color }
    fun setBoard(board: Board) = apply { this.board = board }

    fun build(): Label {
        return Label(LabelData(null, name, color, board!!.id), true)
    }

}