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
        val people_count = intent.getIntExtra("people_count", 0)
        val words_count = intent.getIntExtra("words_count", 0)
        val namelist = intent.getStringArrayExtra("namelist")
        val wordlist = intent.getStringArrayExtra("wordlist")

        val listView = findViewById(R.id.member_list_view) as ListView
        val adapter = PlayAdapter(this, namelist)
        listView.adapter = adapter
        listView.onItemClickListener = OnItemClickListener { parent, view, position, id ->
            intent = Intent(this, ShowActivity::class.java)
            intent.putExtra("shuffle", shuffle)
            intent.putExtra("people_count", people_count)
            intent.putExtra("words_count", words_count)
            intent.putExtra("now", position)
            intent.putExtra("playing", true)
            intent.putExtra("namelist", namelist)
            intent.putExtra("wordlist", wordlist)
            startActivity(intent)
        }


        findViewById<Button>(R.id.finish_button).setOnClickListener {
            intent = Intent(this, FinishActivity::class.java)
            intent.putExtra("shuffle", shuffle)
            intent.putExtra("people_count", people_count)
            intent.putExtra("words_count", words_count)
            intent.putExtra("namelist", namelist)
            intent.putExtra("wordlist", wordlist)
            startActivity(intent)
        }
    }
}