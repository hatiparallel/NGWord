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

        val number = intent.getIntExtra("number", 0)
        val now = intent.getIntExtra("now", 0)
        val names = intent.getStringArrayExtra("names")
        val words = intent.getStringArrayExtra("words")

        val listView = findViewById(R.id.member_list_view) as ListView
        val dataArray = names
        val adapter = ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, dataArray)
        listView.adapter = adapter

        findViewById<Button>(R.id.restart_button).setOnClickListener {
            intent = Intent(this, RegisterActivity::class.java)
            intent.putExtra("number", number)
            intent.putExtra("names", names)
            intent.putExtra("words", WordsDecider.chooseDefaultWords(number))
            intent.putExtra("now", 0)
            startActivity(intent)
        }

        findViewById<Button>(R.id.cleanup_button).setOnClickListener {
            intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }
}