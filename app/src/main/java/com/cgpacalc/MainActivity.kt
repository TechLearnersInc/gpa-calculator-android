package com.cgpacalc

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private var dbVersion: Int? = null
    private var dbManager: DbManager? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        // Main
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Buttons Functions
        this.Button_Functions()

        // Database
        this.dbVersion = 1
        this.dbManager = DbManager(applicationContext, dbVersion!!)
        val x: HashMap<String, Float> = this.dbManager!!.readDataToHashMap()

        for(key in x.keys){
            //println("Element at key $key = ${data[key]}")
            Log.i("FARIA","Element at key $key = ${x[key]}")
        }


    }

    // Adding three dot navigation menu to this activity
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    // Adding actions to menu item
    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item!!.itemId) {
            R.id.actionEditSheet -> startActivity(Intent(this, EditSheetActivity::class.java))
            R.id.actionAbout -> startActivity(Intent(this, AboutActivity::class.java))
            R.id.actionExit -> {
                val builder = AlertDialog.Builder(this)
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
        }
        return super.onOptionsItemSelected(item)
    }

    // Buttons Functions
    private fun Button_Functions() {
        calculateBtn.setOnClickListener {
            Toast.makeText(applicationContext, "Calculate Button", Toast.LENGTH_SHORT).show()
        }
    }


}
