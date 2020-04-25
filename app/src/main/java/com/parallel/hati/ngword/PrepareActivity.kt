package com.parallel.hati.ngword

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView

class PrepareActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_prepare)

        val shuffle : IntArray = intent.getIntArrayExtra("shuffle")
        val number = intent.getIntExtra("number", 0)
        val now = intent.getIntExtra("now", 0)
        val names = intent.getStringArrayExtra("names")
        val words = intent.getStringArrayExtra("words")
        val playing = intent.getBooleanExtra("playing", false)

        val messageView = findViewById<TextView>(R.id.message)
        messageView.setText("${names[now]}さんのNGワードを\n表示します.\n準備ができたら\nOKを押してください．")

        findViewById<Button>(R.id.next_button).setOnClickListener {
            intent = Intent(this, ShowActivity::class.java)
            intent.putExtra("number", number)
            intent.putExtra("now", now)
            intent.putExtra("names", names)
            intent.putExtra("words", words)
            intent.putExtra("shuffle", shuffle)
            intent.putExtra("playing", false)
            startActivity(intent)
        }
    }
}
