package com.cgpacalc

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import kotlinx.android.synthetic.main.grade_listview_ticket.view.*

open class GradeListViewAdaptar(thisContext: Context) : BaseAdapter() {

    private var context: Context = thisContext
    var listOfGrade: MutableList<HashMap<String, Float>>? = null

    init {
        this.refreshDatabase()
    }

    @SuppressLint("ViewHolder", "InflateParams")
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val inflator: LayoutInflater = this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val myview: View = inflator.inflate(R.layout.grade_listview_ticket, null)

        val gradeList: HashMap<String, Float> = this.listOfGrade!![position]
        val grade: String = gradeList.keys.toString().substring(1, gradeList.keys.toString().length - 1)
        val gpa: String = gradeList.values.toString().substring(1, gradeList.values.toString().length - 1)

        myview.gradeTextView.text = grade
        myview.gpaEditText.text = gpa

        return myview
    }

    override fun getItem(position: Int): Any {
        return this.listOfGrade!![position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getCount(): Int {
        return this.listOfGrade!!.size
    }

    open fun refreshDatabase() {
        // Database
        val dbManager = DbManager(this.context, MainActivity().dbVersion)
        this.listOfGrade = dbManager.readDataToList()
    }
}