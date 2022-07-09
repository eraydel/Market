package com.dev.eraydel.market.view.ui.activities.stories

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.dev.eraydel.market.databinding.ActivityStoryTell3Binding
import com.dev.eraydel.market.view.ui.activities.MainActivity

class StoryTell3 : AppCompatActivity() {
    lateinit var binding: ActivityStoryTell3Binding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityStoryTell3Binding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()
    }

    fun click_main(view: View) {
        startActivity(Intent(this,MainActivity::class.java))
        //finish()
    }
}