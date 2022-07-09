package com.dev.eraydel.market.view.ui.activities.stories

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.dev.eraydel.market.R
import com.dev.eraydel.market.databinding.ActivityStoryTell2Binding

class StoryTell2 : AppCompatActivity() {
    lateinit var binding: ActivityStoryTell2Binding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityStoryTell2Binding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()
    }

    fun click_st3(view: View) {
            startActivity(Intent(this,StoryTell3::class.java))
            //finish()
    }
}