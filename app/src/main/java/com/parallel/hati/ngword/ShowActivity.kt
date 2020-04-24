package com.parallel.hati.ngword

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import android.widget.Toast

class ShowActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_show)

        val shuffle : IntArray = intent.getIntArrayExtra("shuffle")
        val number = intent.getIntExtra("number", 0)
        val now = intent.getIntExtra("now", 0)
        val names = intent.getStringArrayExtra("names")
        val words = intent.getStringArrayExtra("words")
        val playing = intent.getBooleanExtra("playing", false)

        val nameView = findViewById<TextView>(R.id.name)
        nameView.setText(names[now])

        val wordView = findViewById<TextView>(R.id.word)
        wordView.setText(words[shuffle[now]])

        findViewById<Button>(R.id.next_button).setOnClickListener {
            if (playing || now == number - 1) {
                intent = Intent(this, PlayActivity::class.java)
            } else{
                intent = Intent(this, ShowActivity::class.java)
            }
            intent.putExtra("number", number)
            intent.putExtra("now", (now + 1) % number)
            intent.putExtra("names", names)
            intent.putExtra("words", words)
            intent.putExtra("shuffle", shuffle)
            startActivity(intent)
        }
    }
}
