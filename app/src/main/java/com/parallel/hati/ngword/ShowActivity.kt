package com.parallel.hati.ngword

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ListView
import android.widget.TextView
import android.widget.Toast

class ShowActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_show)

        val shuffle : IntArray = intent.getIntArrayExtra("shuffle")
        val people_count = intent.getIntExtra("people_count", 0)
        val words_count = intent.getIntExtra("words_count", 0)
        val now = intent.getIntExtra("now", 0)
        val namelist = intent.getStringArrayExtra("namelist")
        val wordlist = intent.getStringArrayExtra("wordlist")
        val playing = intent.getBooleanExtra("playing", false)

        val nameView = findViewById<TextView>(R.id.name_view)
        nameView.setText(namelist[now])

        val listView = findViewById(R.id.word_list_view) as ListView
        val adapter = ShowAdapter(this, wordlist, shuffle, words_count, now)
        listView.adapter = adapter

        findViewById<Button>(R.id.next_button).setOnClickListener {
            if (playing || now == people_count - 1) {
                intent = Intent(this, PlayActivity::class.java)
            } else{
                intent = Intent(this, PrepareActivity::class.java)
                intent.putExtra("now", (now + 1) % people_count)
            }
            intent.putExtra("people_count", people_count)
            intent.putExtra("words_count", words_count)
            intent.putExtra("namelist", namelist)
            intent.putExtra("wordlist", wordlist)
            intent.putExtra("shuffle", shuffle)
            startActivity(intent)
        }
    }
}
