package com.gpacalc

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.WindowManager
import android.view.animation.AlphaAnimation
import kotlinx.android.synthetic.main.activity_splashscreen.*

class SplashScreen : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splashscreen)
        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN)
        this.myAnimator()
        val mainActivity = object : Thread() {
            override fun run() {
                try {
                    sleep(1500)
                    startActivity(Intent(baseContext, MainActivity::class.java))
                    finish()
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
        }
        mainActivity.start()
    }

    private fun myAnimator() {
        val fadeInOne = AlphaAnimation(0.0f, 1.0f)
        fadeInOne.duration = 500;
        fadeInOne.fillAfter = true;
        splashLogo.startAnimation(fadeInOne);
        val fadeInTwo = AlphaAnimation(0.0f, 1.0f)
        fadeInTwo.duration = 1000;
        fadeInTwo.fillAfter = true;
        organizationName.startAnimation(fadeInTwo)
    }
}