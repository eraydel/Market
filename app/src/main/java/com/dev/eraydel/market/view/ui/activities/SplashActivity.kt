package com.dev.eraydel.market.view.ui.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.dev.eraydel.market.R
import com.dev.eraydel.market.view.ui.activities.stories.StoryTell1
import kotlin.concurrent.thread

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        supportActionBar?.hide()
        thread {
            Thread.sleep(3000)
            val intent =  Intent(this,StoryTell1::class.java)
            startActivity(intent)
            finish()
        }
    }
}