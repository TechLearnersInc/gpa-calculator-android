package com.cgpacalc

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.widget.Toast


class DbManager(context: Context, version: Int) {

    private var db: DatabaseHelper? = null
    val dbName: String = "MyDatabase"
    val dbVersion: Int = version
    val dbTable: String = "MyTable"
    val colGrade: String = "grade"
    val colGPA: String = "gpa"
    private val colID: String = "ID"
    val sqlCreateTable: String = """
        CREATE TABLE $dbTable (
	        $colID      INTEGER PRIMARY KEY,
            $colGrade	TEXT NOT NULL UNIQUE,
	        $colGPA	    INTEGER NOT NULL
        )""".trimIndent()
    private var sqlDB: SQLiteDatabase? = null

    init {
        this.db = DatabaseHelper(context)
        this.sqlDB = this.db!!.writableDatabase
    }

    inner class DatabaseHelper(context: Context) : SQLiteOpenHelper(context, dbName, null, dbVersion) {

        var context: Context? = context

        override fun onCreate(db: SQLiteDatabase?) {
            db!!.execSQL(sqlCreateTable)
            this.loadDefault(db)
        }

        override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
            db!!.execSQL("DROP TABLE IF EXISTS $dbTable")
            db.execSQL(sqlCreateTable)
            this.loadDefault(db)
        }

        fun resetDb(db: SQLiteDatabase?) {
            db!!.execSQL("DROP TABLE IF EXISTS $dbTable")
            db.execSQL(sqlCreateTable)
            this.loadDefault(db)
        }

        private fun loadDefault(db: SQLiteDatabase) {
            val dbDefaultData = DbDefaultData()
            dbDefaultData.load(db, dbTable, colGrade, colGPA)
            when (dbDefaultData.result()) {
                true -> Toast.makeText(this.context, "Developed by Rizwan Hasan", Toast.LENGTH_LONG).show()
                false -> Toast.makeText(this.context, "Can't create or update database", Toast.LENGTH_LONG).show()
            }
        }
    }

    fun readDataToList(): MutableList<HashMap<String, Float>> {
        val data: MutableList<HashMap<String, Float>> = mutableListOf()
        val query = "SELECT * FROM $dbTable"
        val result = sqlDB!!.rawQuery(query, null)
        if (result.moveToFirst()) {
            do {
                val hashMap:HashMap<String, Float> = HashMap()
                val grade: String = result.getString(result.getColumnIndex(colGrade))
                val gpa: String = result.getString(result.getColumnIndex(colGPA))
                hashMap[grade] = gpa.toFloat()
                data.add(hashMap)
            } while (result.moveToNext())
        }
        result.close()
        return data
    }

    fun updateData(grade: String, gpa: String) {
        val values = ContentValues()
        values.put(colGPA, gpa.toFloat())
        sqlDB!!.update(dbTable, values, "$colGrade=?", arrayOf(grade))
    }

    fun resetDb(): Boolean {
        return try {
            this.db!!.resetDb(this.sqlDB)
            true
        } catch (e: Exception) {
            e.printStackTrace()
            false
        }
    }
}