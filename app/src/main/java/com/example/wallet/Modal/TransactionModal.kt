package com.example.wallet.Modal

class TransactionModal {
    var id: Int = 0
    lateinit var name: String
    var amt: Int = 0
    lateinit var category: String
    lateinit var note: String
    var isExpense: Int = 0
    lateinit var time :String

    constructor(
        id: Int, name: String, amt: Int, category: String, note: String, isExpense: Int, time: String
    ) {
        this.id = id
        this.name = name
        this.amt = amt
        this.category = category
        this.note = note
        this.time = time
        this.isExpense = isExpense

    }
}