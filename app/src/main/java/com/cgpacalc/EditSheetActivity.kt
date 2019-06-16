package com.cgpacalc

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import kotlinx.android.synthetic.main.activity_edit_sheet.*
import kotlinx.android.synthetic.main.grade_listview_ticket.view.*

class EditSheetActivity : AppCompatActivity() {

    private var dbManager: DbManager? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_sheet)

        // Database
        this.dbManager = DbManager(applicationContext, 1)
        val x: MutableList<HashMap<String, Float>> = this.dbManager!!.readDataToList()
        Log.i("FARIA", x.toString())

        val adapter = GradeAdaptar(this, x)
        MyListView.adapter = adapter

    }

    class GradeAdaptar(thisContext: Context, x: MutableList<HashMap<String, Float>>) : BaseAdapter() {

        private val listOfGrade = x
        private val context: Context = thisContext

        @SuppressLint("ViewHolder", "InflateParams")
        override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
            val grade = this.listOfGrade[position]
            val inflator = this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            val myview = inflator.inflate(R.layout.grade_listview_ticket, null)
            myview.gTextView.text = grade.toString()
            return myview
        }

        override fun getItem(position: Int): Any {
            return this.listOfGrade[position]
        }

        override fun getItemId(position: Int): Long {
            return position.toLong()
        }

        override fun getCount(): Int {
            return listOfGrade.size
        }

    }

}
