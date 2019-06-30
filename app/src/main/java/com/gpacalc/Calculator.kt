package com.gpacalc

import android.content.Context
import kotlin.math.round
import kotlin.math.roundToLong

class Calculator(thisContext: Context, table: MutableList<List<String>>) {

    private var listOfSubjects: MutableList<List<String>>? = null
    private var dbManager: DbManager? = null
    private var context: Context? = null

    init {
        this.context = thisContext
        this.dbManager = DbManager(this.context!!, MainActivity().dbVersion)
        this.listOfSubjects = table
    }

    fun calculate(): Double {
        var credit: Double
        var addition = 0.0
        var totalCredit = 0.0

        for (i in 0 until this.listOfSubjects!!.size) {
            credit = this.listOfSubjects!![i][2].toDouble()
            addition += credit * this.dbManager!!.getPoint(this.listOfSubjects!![i][1])
            totalCredit += credit
        }

        return "%.2f".format(addition / totalCredit).toDouble()
    }

}