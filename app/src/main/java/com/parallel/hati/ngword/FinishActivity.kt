package com.parallel.hati.ngword

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView

class FinishActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_finish)

        val shuffle = intent.getIntArrayExtra("shuffle")
        val people_count = intent.getIntExtra("people_count", 0)
        val words_count = intent.getIntExtra("words_count", 0)
        val namelist = intent.getStringArrayExtra("namelist")
        val wordlist = intent.getStringArrayExtra("wordlist")

        val listView = findViewById(R.id.member_list_view) as ListView
        val adapter = FinishAdapter(this, words_count, namelist, wordlist, shuffle)
        listView.adapter = adapter

        findViewById<Button>(R.id.restart_button).setOnClickListener {
            intent = Intent(this, RegisterActivity::class.java)
            intent.putExtra("people_count", people_count)
            intent.putExtra("words_count", words_count)
            intent.putExtra("namelist", namelist)
            intent.putExtra("wordlist", WordsDecider.chooseDefaultWords(people_count*words_count))
            intent.putExtra("now", 0)
            startActivity(intent)
        }

        findViewById<Button>(R.id.cleanup_button).setOnClickListener {
            intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }
}