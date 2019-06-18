package com.cgpacalc

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.widget.ArrayAdapter
import kotlinx.android.synthetic.main.activity_edit_sheet.*

class EditSheetActivity : AppCompatActivity() {

    private var dbManager: DbManager? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_sheet)

        // Database
        this.dbManager = DbManager(applicationContext, MainActivity().dbVersion)
        val x: MutableList<HashMap<String, Float>> = this.dbManager!!.readDataToList()
        Log.i("FARIA", x.toString())

        val adaptar = GradeListViewAdaptar(this, x)
        MyListView.adapter = adaptar
    }
}
