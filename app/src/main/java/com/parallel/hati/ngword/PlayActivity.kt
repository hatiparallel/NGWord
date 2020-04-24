package com.parallel.hati.ngword

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.AdapterView.OnItemClickListener
import android.widget.Button
import android.widget.ListView


class PlayActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_play)

        val shuffle : IntArray = intent.getIntArrayExtra("shuffle")
        val number = intent.getIntExtra("number", 0)
        val now = intent.getIntExtra("now", 0)
        val names = intent.getStringArrayExtra("names")
        val words = intent.getStringArrayExtra("words")

        val listView = findViewById(R.id.member_list_view) as ListView
        val adapter = PlayAdapter(this, names)
        listView.adapter = adapter
        listView.onItemClickListener = OnItemClickListener { parent, view, position, id ->
            intent = Intent(this, ShowActivity::class.java)
            intent.putExtra("shuffle", shuffle)
            intent.putExtra("number", number)
            intent.putExtra("now", position)
            intent.putExtra("playing", true)
            intent.putExtra("names", names)
            intent.putExtra("words", words)
            startActivity(intent)
        }


        findViewById<Button>(R.id.finish_button).setOnClickListener {
            intent = Intent(this, FinishActivity::class.java)
            intent.putExtra("number", number)
            intent.putExtra("names", names)
            intent.putExtra("words", words)
            startActivity(intent)
        }
    }
}