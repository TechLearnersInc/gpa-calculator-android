package com.cgpacalc

import android.annotation.SuppressLint
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuItem
import android.view.WindowManager
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_edit_sheet.*
import kotlinx.android.synthetic.main.gpa_input_dialogue.view.*

class EditSheetActivity : AppCompatActivity() {

    private var adaptarGrade: GradeListViewAdaptar? = null
    private var adaptarCredit: CreditListViewAdaptar? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_sheet)

        // Grade Sheet Handler
        this.gradeListViewHandler()

        // Credit Sheet Handler
        this.creditListViewHandler()

        // Hide Keyboard when activity starts
        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN)
    }

    // Adding three dot navigation menu to this activity
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.edit_sheet_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    // Adding RESET menu button to this activity
    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item!!.itemId) {
            R.id.actionResetDb -> {
                val warningDialog = AlertDialog.Builder(this@EditSheetActivity)
                warningDialog.setTitle("Do you really want to reset database?")
                warningDialog.setCancelable(true)
                warningDialog.setNegativeButton("No") { dialogInterface, _ -> dialogInterface.cancel() }
                warningDialog.setPositiveButton("Yes") { _, _ ->
                    run {
                        when (this.adaptarGrade!!.dbManager!!.resetDb()) {
                            true -> {
                                finish()
                                startActivity(intent)
                                Toast.makeText(this@EditSheetActivity, "Reset successful", Toast.LENGTH_LONG).show()
                            }
                            false -> Toast.makeText(
                                this@EditSheetActivity,
                                "Reset unsuccessful",
                                Toast.LENGTH_LONG
                            ).show()
                        }
                    }
                }
                warningDialog.show()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    // Grade Sheet Handler
    @SuppressLint("InflateParams")
    private fun gradeListViewHandler() {
        this.adaptarGrade = GradeListViewAdaptar(this@EditSheetActivity)
        this.GradeListView.adapter = this.adaptarGrade

        @Suppress("UsePropertyAccessSyntax")
        this.GradeListView.setOnItemClickListener { _, _, position, _ ->
            run {
                val inputView = LayoutInflater.from(this@EditSheetActivity).inflate(R.layout.gpa_input_dialogue, null)

                val gradeList = this.adaptarGrade!!.listOfGrade!![position]
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
                                this.adaptarGrade!!.listOfGrade!![position][grade] =
                                    inputView.gpaInputDialogue.text.toString().trim().toFloat()
                                this.adaptarGrade!!.dbManager!!.updateData(
                                    inputView.gradeInputDialogue.text.toString(),
                                    inputView.gpaInputDialogue.text.toString().trim()
                                )
                                this.adaptarGrade!!.notifyDataSetChanged()
                                Toast.makeText(this@EditSheetActivity, "Success", Toast.LENGTH_LONG).show()
                            } else {
                                Toast.makeText(this@EditSheetActivity, "No change", Toast.LENGTH_LONG).show()
                            }
                        } catch (e: NumberFormatException) {
                            e.printStackTrace()
                            Toast.makeText(this@EditSheetActivity, "Wrong input", Toast.LENGTH_LONG).show()
                        }
                    }
                }
                inputDialogue.show()
            }
        }
    }

    // Credit Sheet Handler
    @SuppressLint("InflateParams")
    private fun creditListViewHandler() {
        this.adaptarCredit = CreditListViewAdaptar(this@EditSheetActivity)
        this.CreditListView.adapter = this.adaptarCredit

        @Suppress("UsePropertyAccessSyntax")
        this.CreditListView.setOnItemClickListener { _, _, position, _ ->
            run {
                val inputView = LayoutInflater.from(this@EditSheetActivity).inflate(R.layout.gpa_input_dialogue, null)

                val gradeList = this.adaptarCredit!!.listOfCredit!![position]
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
                                this.adaptarCredit!!.listOfCredit!![position][grade] =
                                    inputView.gpaInputDialogue.text.toString().trim().toFloat()
                                this.adaptarCredit!!.dbManager!!.updateData(
                                    inputView.gradeInputDialogue.text.toString(),
                                    inputView.gpaInputDialogue.text.toString().trim()
                                )
                                this.adaptarCredit!!.notifyDataSetChanged()
                                Toast.makeText(this@EditSheetActivity, "Success", Toast.LENGTH_LONG).show()
                            } else {
                                Toast.makeText(this@EditSheetActivity, "No change", Toast.LENGTH_LONG).show()
                            }
                        } catch (e: NumberFormatException) {
                            e.printStackTrace()
                            Toast.makeText(this@EditSheetActivity, "Wrong input", Toast.LENGTH_LONG).show()
                        }
                    }
                }
                inputDialogue.show()
            }
        }
    }
}
