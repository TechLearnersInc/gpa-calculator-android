package com.gpacalc

import android.annotation.SuppressLint
import android.content.Context
import android.support.v4.content.ContextCompat
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import kotlinx.android.synthetic.main.student_sub_listview_ticket.view.*

open class SubjectListViewAdaptar(thisContext: Context) : BaseAdapter() {

    private var context: Context = thisContext
    var listOfSubjects: MutableList<List<String>> = mutableListOf()

    @SuppressLint("ViewHolder", "InflateParams")
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val inflator: LayoutInflater = this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val myview: View = inflator.inflate(R.layout.student_sub_listview_ticket, null)

        val type: String = listOfSubjects[position][0]
        val grade: String = listOfSubjects[position][1]
        val credit: String = listOfSubjects[position][2]

        myview.type.text = type
        myview.grade.text = grade
        myview.credit.text = credit

        myview.setBackgroundColor(
            when (position % 2) {
                1 -> ContextCompat.getColor(this.context, R.color.listViewColorA)
                else -> ContextCompat.getColor(this.context, R.color.listViewColorB)
            }
        )

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