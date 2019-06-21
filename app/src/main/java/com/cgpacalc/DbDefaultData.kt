package com.cgpacalc

import android.content.ContentValues
import android.database.sqlite.SQLiteDatabase

class DbDefaultData {

    private var result: Boolean = false

    fun load(db: SQLiteDatabase, dbTable: String, colGrade: String, colGPA: String) {
        val values = ContentValues()
        try {

            //Subject
            values.put(colGrade, "Subject")
            values.put(colGPA, "3.0")
            db.insert(dbTable, "", values)
            values.clear()

            // Laboratory
            values.put(colGrade, "Laboratory")
            values.put(colGPA, "1.5")
            db.insert(dbTable, "", values)
            values.clear()

            // A+
            values.put(colGrade, "A+")
            values.put(colGPA, "-1")
            db.insert(dbTable, "", values)
            values.clear()

            // A
            values.put(colGrade, "A")
            values.put(colGPA, "-1")
            db.insert(dbTable, "", values)
            values.clear()

            // A-
            values.put(colGrade, "A-")
            values.put(colGPA, "-1")
            db.insert(dbTable, "", values)
            values.clear()

            // B+
            values.put(colGrade, "B+")
            values.put(colGPA, "-1")
            db.insert(dbTable, "", values)
            values.clear()

            // B
            values.put(colGrade, "B")
            values.put(colGPA, "-1")
            db.insert(dbTable, "", values)
            values.clear()

            // B-
            values.put(colGrade, "B-")
            values.put(colGPA, "-1")
            db.insert(dbTable, "", values)
            values.clear()

            // C+
            values.put(colGrade, "C+")
            values.put(colGPA, "-1")
            db.insert(dbTable, "", values)
            values.clear()

            // C
            values.put(colGrade, "C")
            values.put(colGPA, "-1")
            db.insert(dbTable, "", values)
            values.clear()

            // C-
            values.put(colGrade, "C-")
            values.put(colGPA, "-1")
            db.insert(dbTable, "", values)
            values.clear()

            // D
            values.put(colGrade, "D")
            values.put(colGPA, "-1")
            db.insert(dbTable, "", values)
            values.clear()

            // F
            values.put(colGrade, "F")
            values.put(colGPA, "0")
            db.insert(dbTable, "", values)

            // Result
            this.result = true
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun result(): Boolean {
        return this.result
    }
}