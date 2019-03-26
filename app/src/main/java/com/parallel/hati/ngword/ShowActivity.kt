package com.parallel.hati.ngword

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast

class ShowActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_show)

        val count = intent.getIntExtra("count", 0)
        val now = intent.getIntExtra("now", 0)

        val names = intent.getStringArrayExtra("names")
        val words = intent.getStringArrayExtra("words")

        val nameView = findViewById<TextView>(R.id.name)
        nameView.setText("john")

        val wordView = findViewById<TextView>(R.id.word)
        wordView.setText("salad")
    }
}
