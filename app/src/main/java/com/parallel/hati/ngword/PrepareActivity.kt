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
        val people_count = intent.getIntExtra("people_count", 0)
        val words_count = intent.getIntExtra("words_count", 0)
        val now = intent.getIntExtra("now", 0)
        val namelist = intent.getStringArrayExtra("nameilst")
        val wordlist = intent.getStringArrayExtra("wordlist")

        val messageView = findViewById<TextView>(R.id.message)
        messageView.setText("${namelist[now]}さんのNGワードを\n表示します.\n準備ができたら\nOKを押してください．")

        findViewById<Button>(R.id.next_button).setOnClickListener {
            intent = Intent(this, ShowActivity::class.java)
            intent.putExtra("people_count", people_count)
            intent.putExtra("words_count", words_count)
            intent.putExtra("now", now)
            intent.putExtra("namelist", namelist)
            intent.putExtra("wordlist", wordlist)
            intent.putExtra("shuffle", shuffle)
            intent.putExtra("playing", false)
            startActivity(intent)
        }
    }
}
