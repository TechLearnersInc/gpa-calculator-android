package com.gpacalc

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuItem
import android.view.WindowManager
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.show_gpa.view.*

class MainActivity : AppCompatActivity() {

    val dbVersion: Int = 1
    private var subjectListViewAdaptar: SubjectListViewAdaptar? = null

    @SuppressLint("ResourceType")
    override fun onCreate(savedInstanceState: Bundle?) {
        // Main
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Database
        DbManager(applicationContext, this.dbVersion)

        // ListView Adapter Adding
        this.subjectListViewAdaptar = SubjectListViewAdaptar(this@MainActivity)
        this.subjectListView.adapter = subjectListViewAdaptar

        // Buttons Functions
        this.buttonFunctions()

        // Setting default Subject and Laboratory credit
        this.defaultSubLabCredit()

        // Hide Keyboard when activity starts
        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN)
    }

    // Adding three dot navigation menu to this activity
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    // Adding actions to menu item
    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item!!.itemId) {
            R.id.actionEditSheet -> startActivity(Intent(this@MainActivity, EditSheetActivity::class.java))
            R.id.actionAbout -> startActivity(Intent(this@MainActivity, AboutActivity::class.java))
            R.id.actionExit -> {
                val builder = AlertDialog.Builder(this@MainActivity)
                builder.setMessage("Are you sure to exit?")
                builder.setCancelable(true)
                builder.setNegativeButton("NO") { dialogInterface, _ -> dialogInterface.cancel() }
                builder.setPositiveButton("EXIT") { _, _ ->
                    run {
                        Toast.makeText(applicationContext, "Exited", Toast.LENGTH_SHORT).show()
                        finish()
                    }
                }
                builder.create().show()
            }
            R.id.actionRefresh -> {
                startActivity(Intent(this@MainActivity, MainActivity::class.java))
                finish()
            }
            R.id.actionClear -> {
                try {
                    this.subjectListViewAdaptar!!.listOfSubjects.clear()
                    this.subjectListViewAdaptar!!.notifyDataSetChanged()
                    Toast.makeText(this@MainActivity, "Cleared", Toast.LENGTH_LONG).show()
                } catch (e: Exception) {
                    Toast.makeText(this@MainActivity, "Unexpected error occurred", Toast.LENGTH_LONG).show()
                    e.printStackTrace()
                }
            }
        }
        return super.onOptionsItemSelected(item)
    }

    // Buttons Functions
    private fun buttonFunctions() {
        this.subAddButton.setOnClickListener { this.infoAddBtnFunc(true) }
        this.labAddButton.setOnClickListener { this.infoAddBtnFunc(false) }
        this.calculateButton.setOnClickListener { this.calculateBtnAction() }

    }

    private fun infoAddBtnFunc(isSubject: Boolean) {
        try {
            val grade: String
            val credit: String
            when (isSubject) {
                true -> {
                    grade = this.subGradeEditText.text.toString().toUpperCase().trim()
                    credit = this.subCreditEditText.text.toString().trim()
                }
                else -> {
                    grade = this.labGradeEditText.text.toString().toUpperCase().trim()
                    credit = this.labCreditEditText.text.toString().trim()
                }
            }
            if (grade.isNotEmpty() && credit.isNotEmpty()) {
                this.subjectListViewAdaptar!!.listOfSubjects.add(
                    when (isSubject) {
                        true -> listOf("Subject", grade, credit)
                        else -> listOf("Laboratory", grade, credit)
                    }
                )
                this.subjectListViewAdaptar!!.notifyDataSetChanged()
                this.subGradeEditText.text.clear()
                this.labGradeEditText.text.clear()
                Toast.makeText(this@MainActivity, "Added", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this@MainActivity, "No value found", Toast.LENGTH_SHORT).show()
            }
        } catch (e: Exception) {
            Toast.makeText(this@MainActivity, "Unexpected error occurred", Toast.LENGTH_SHORT).show()
            e.printStackTrace()
        }
    }

    @SuppressLint("InflateParams")
    private fun calculateBtnAction() {
        try {
            val x = Calculator(this@MainActivity, this.subjectListViewAdaptar!!.listOfSubjects)
            Log.i("FARIA", x.calculate())
            Toast.makeText(this@MainActivity, x.calculate(), Toast.LENGTH_LONG).show()
            //
            val builder = AlertDialog.Builder(this@MainActivity)
            builder.setCancelable(true)
            //
            val view = LayoutInflater.from(this@MainActivity).inflate(R.layout.show_gpa, null)
            view.GPA.text = x.calculate()
            builder.setView(view)
            //
            val dialogue = builder.create()
            dialogue.show()
            dialogue.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))


            //
        } catch (e: NullPointerException) {
            Toast.makeText(this@MainActivity, "Unable to calculate", Toast.LENGTH_LONG).show()
        }

    }

    private fun defaultSubLabCredit() {
        val creditData = DbManager(this@MainActivity, MainActivity().dbVersion)
            .readDataToList(false)
            .asReversed()
        subCreditEditText.setText(creditData[0]["Subject"].toString())
        labCreditEditText.setText(creditData[1]["Laboratory"].toString())
    }
}
