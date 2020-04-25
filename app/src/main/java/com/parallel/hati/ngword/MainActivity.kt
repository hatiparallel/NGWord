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

        var count = 3
        val count_view = findViewById<TextView>(R.id.count_view)

        findViewById<ImageButton>(R.id.decrease_button).setOnClickListener {
            if (count > 2) {
                count -= 1
                count_view.setText(" ${count}人 ")
            } else {
                Toast.makeText(applicationContext, "人数は最小で2人までです", Toast.LENGTH_LONG).show()
            }
        }

        findViewById<ImageButton>(R.id.increase_button).setOnClickListener {
            if (count < 16) {
                count += 1
                count_view.setText(" ${count}人 ")
            } else {
                Toast.makeText(applicationContext, "人数は最大で16人までです", Toast.LENGTH_LONG).show()
            }
        }

        findViewById<Button>(R.id.howto_button).setOnClickListener {
            intent = Intent(this, HowToActivity::class.java)
            startActivity(intent)
        }

        findViewById<Button>(R.id.start).setOnClickListener {
            //val initWords: Array<String> = WordsDecider.chooseDefaultWords(count)
            intent = Intent(this, RegisterActivity::class.java)
            intent.putExtra("number", count)
            intent.putExtra("names", Array<String>(count, { "" }))
            intent.putExtra("words", WordsDecider.chooseDefaultWords(count))
            intent.putExtra("now", 0)
            startActivity(intent)
        }
    }
}
