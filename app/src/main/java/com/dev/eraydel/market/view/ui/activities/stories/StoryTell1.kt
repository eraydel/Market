package com.dev.eraydel.market.view.ui.activities.stories

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.dev.eraydel.market.R
import com.dev.eraydel.market.databinding.ActivityStoryTell1Binding

class StoryTell1 : AppCompatActivity() {
    lateinit var binding: ActivityStoryTell1Binding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityStoryTell1Binding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()
    }

    fun click_st1(view: View) {
        startActivity(Intent(this,StoryTell2::class.java))
        //finish()
    }
}