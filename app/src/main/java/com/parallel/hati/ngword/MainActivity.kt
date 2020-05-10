package com.parallel.hati.ngword

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var people_count = 3
        val people_count_view = findViewById<TextView>(R.id.people_count_view)

        val people_count_min = 2
        val people_count_max = 8

        var words_count = 1
        val words_count_view = findViewById<TextView>(R.id.words_count_view)

        val words_count_min = 1
        val words_count_max = 5

        findViewById<ImageButton>(R.id.people_decrease_button).setOnClickListener {
            if (people_count > people_count_min) {
                people_count -= 1
                people_count_view.setText(" ${people_count}人 ")
            } else {
                Toast.makeText(applicationContext, "人数は最小で${people_count_min}人までです", Toast.LENGTH_LONG).show()
            }
        }

        findViewById<ImageButton>(R.id.people_increase_button).setOnClickListener {
            if (people_count < people_count_max) {
                people_count += 1
                people_count_view.setText(" ${people_count}人 ")
            } else {
                Toast.makeText(applicationContext, "人数は最大で${people_count_max}人までです", Toast.LENGTH_LONG).show()
            }
        }

        findViewById<ImageButton>(R.id.words_decrease_button).setOnClickListener {
            if (words_count > words_count_min) {
                words_count -= 1
                words_count_view.setText(" ${words_count}人 ")
            } else {
                Toast.makeText(applicationContext, "ワードは最小で${words_count_min}個までです", Toast.LENGTH_LONG).show()
            }
        }

        findViewById<ImageButton>(R.id.words_increase_button).setOnClickListener {
            if (words_count < words_count_max) {
                words_count += 1
                words_count_view.setText(" ${words_count}個 ")
            } else {
                Toast.makeText(applicationContext, "ワードは最大で${words_count_max}個までです", Toast.LENGTH_LONG).show()
            }
        }

        findViewById<Button>(R.id.howto_button).setOnClickListener {
            intent = Intent(this, HowToActivity::class.java)
            startActivity(intent)
        }

        findViewById<Button>(R.id.start).setOnClickListener {
            //val initWords: Array<String> = WordsDecider.chooseDefaultWords(count)
            intent = Intent(this, RegisterActivity::class.java)
            intent.putExtra("people_count", people_count)
            intent.putExtra("words_count", words_count)
            intent.putExtra("namelist", Array<String>(people_count, { "" }))
            intent.putExtra("wordlist", WordsDecider.chooseDefaultWords(people_count * words_count))
            intent.putExtra("now", 0)
            startActivity(intent)
        }
    }
}
