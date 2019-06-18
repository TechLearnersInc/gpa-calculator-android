package com.cgpacalc

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import kotlinx.android.synthetic.main.grade_listview_ticket.view.*

class GradeListViewAdaptar(thisContext: Context, x: MutableList<HashMap<String, Float>>) : BaseAdapter() {
    private val listOfGrade = x
    private val context: Context = thisContext

    @SuppressLint("ViewHolder", "InflateParams")
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val gradeList = this.listOfGrade[position]
        val inflator = this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val myview = inflator.inflate(R.layout.grade_listview_ticket, null)

        val grade: String = gradeList.keys.toString().substring(1, gradeList.keys.toString().length - 1)
        val gpa: String = gradeList.values.toString().substring(1, gradeList.values.toString().length - 1)

        myview.gradeTextView.text = grade
        myview.gpaEditText.setText(gpa)

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