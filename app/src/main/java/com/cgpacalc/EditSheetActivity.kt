package com.cgpacalc

import android.annotation.SuppressLint
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.view.LayoutInflater
import android.view.Menu
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_edit_sheet.*
import kotlinx.android.synthetic.main.gpa_input_dialogue.view.*
import java.lang.NumberFormatException

class EditSheetActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_sheet)

        // Grade Sheet Handler
        this.gradeListViewHandler()
    }

    // Adding three dot navigation menu to this activity
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.edit_sheet_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    // Grade Sheet Handler
    @SuppressLint("InflateParams")
    private fun gradeListViewHandler() {
        val adaptar = GradeListViewAdaptar(this@EditSheetActivity)
        MyListView.adapter = adaptar

        MyListView.setOnItemClickListener { parent, view, position, id ->
            run {
                val inputView = LayoutInflater.from(this@EditSheetActivity).inflate(R.layout.gpa_input_dialogue, null)

                val gradeList = adaptar.listOfGrade!![position]
                val grade: String = gradeList.keys.toString().substring(1, gradeList.keys.toString().length - 1)
                val gpa: String = gradeList.values.toString().substring(1, gradeList.values.toString().length - 1)

                inputView.gradeInputDialogue.text = grade
                inputView.gpaInputDialogue.setText(gpa)

                val inputDialogue = AlertDialog.Builder(this@EditSheetActivity)
                inputDialogue.setTitle("Edit GPA")
                inputDialogue.setView(inputView)
                inputDialogue.setCancelable(true)
                inputDialogue.setNegativeButton("Cancel") { dialogInterface, _ -> dialogInterface.cancel() }
                inputDialogue.setPositiveButton("Ok") { _, _ ->
                    run {
                        try {
                            val tmp: String = inputView.gpaInputDialogue.text.toString()
                            if (gpa != tmp) {
                                adaptar.listOfGrade!![position][grade] =
                                    inputView.gpaInputDialogue.text.toString().toFloat()
                                adaptar.notifyDataSetChanged()
                                Toast.makeText(this@EditSheetActivity, "Value changed", Toast.LENGTH_LONG).show()
                            } else {
                                Toast.makeText(this@EditSheetActivity, "No change", Toast.LENGTH_LONG).show()
                            }
                        } catch (e: NumberFormatException) {
                            e.printStackTrace()
                            Toast.makeText(this@EditSheetActivity, "Value error", Toast.LENGTH_LONG).show()
                        }
                    }
                }
                inputDialogue.show()
            }
        }
    }

}
