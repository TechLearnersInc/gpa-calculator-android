package com.cgpacalc

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import kotlinx.android.synthetic.main.student_sub_listview_ticket.view.*

open class SubjectListViewAdaptar(thisContext: Context) : BaseAdapter() {

    private var context: Context = thisContext
    private val listOfSubjects = listOf("START", "B", "C", "D", "E", "F", "G", "A", "B", "C", "D", "E", "F", "G", "A", "B", "C", "D", "E", "F", "G", "A", "B", "C", "D", "E", "F", "G", "END")

    @SuppressLint("ViewHolder", "InflateParams")
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val inflator: LayoutInflater = this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val myview: View = inflator.inflate(R.layout.student_sub_listview_ticket, null)

        val type: String = listOfSubjects[position]
        val grade: String = listOfSubjects[position]
        val credit: String = listOfSubjects[position]

        myview.type.text = type
        myview.grade.text = grade
        myview.credit.text = credit

        return myview
    }

    override fun getItem(position: Int): Any {
        return listOfSubjects[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getCount(): Int {
        return listOfSubjects.size
    }

}