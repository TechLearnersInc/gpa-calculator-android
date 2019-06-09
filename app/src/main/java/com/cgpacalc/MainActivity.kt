package com.cgpacalc

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Toast.makeText(applicationContext, "Developed by Rizwan Hasan", Toast.LENGTH_SHORT).show()
    }

    // Adding three dot navigation menu to this activity
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    // Adding functions to menu item
    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        val id: Int = item!!.itemId

        if (id == R.id.actionEditSheet) {
            val intent: Intent = Intent(this, EditSheetActivity::class.java)
            startActivity(intent)
            Toast.makeText(applicationContext, "Settings", Toast.LENGTH_SHORT).show()
        }
        else if (id == R.id.actionAbout) {
            val intent: Intent = Intent(this, AboutActivity::class.java)
            startActivity(intent)
            Toast.makeText(applicationContext, "About", Toast.LENGTH_SHORT).show()
        }

        return super.onOptionsItemSelected(item)
    }


}
