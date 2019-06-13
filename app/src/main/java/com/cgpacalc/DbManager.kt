package com.cgpacalc

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.widget.Toast

class DbManager(context: Context, version: Int) {

    val dbName: String = "MyDatabase"
    val dbVersion: Int = version
    val dbTable: String = "MyTable"
    val colGrade: String = "KEY"
    val colGPA: String = "VALUE"
    private val colID: String = "ID"
    val sqlCreateTable: String = """
        CREATE TABLE $dbTable (
	        $colID      INTEGER PRIMARY KEY,
            $colGrade	TEXT NOT NULL UNIQUE,
	        $colGPA	    INTEGER NOT NULL
        )""".trimIndent()
    var sqlDB: SQLiteDatabase? = null

    init {
        val db = DatabaseHelper(context)
        sqlDB = db.writableDatabase
    }

    inner class DatabaseHelper(context: Context) : SQLiteOpenHelper(context, dbName, null, dbVersion) {

        var context: Context? = context

        override fun onCreate(db: SQLiteDatabase?) {
            db!!.execSQL(sqlCreateTable)
            this.LoadDefault(db)
        }

        override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
            db!!.execSQL("DROP TABLE IF EXISTS $dbTable")
            db.execSQL(sqlCreateTable)
            this.LoadDefault(db)
        }

        private fun LoadDefault(db: SQLiteDatabase) {
            val dbDefaultData = DbDefaultData()
            dbDefaultData.load(db, dbTable, colGrade, colGPA)
            when (dbDefaultData.result()) {
                true -> Toast.makeText(this.context, "Developed by Rizwan Hasan", Toast.LENGTH_LONG).show()
                false -> Toast.makeText(this.context, "Can't create or update database", Toast.LENGTH_LONG).show()
            }
        }
    }
}