package com.parallel.hati.ngword

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class RegisterActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        val count = intent.getIntExtra("count", 0)
        val now = intent.getIntExtra("now", 0)

        val names = intent.getStringArrayExtra("names")
        val words = intent.getStringArrayExtra("words")

        val nameEditText = findViewById<EditText>(R.id.name_edit_text)
        val wordEditText = findViewById<EditText>(R.id.word_edit_text)

        findViewById<Button>(R.id.ok).setOnClickListener {
            if (nameEditText.getText().isNotEmpty()) {
                names[now] = nameEditText.getText().toString()
            }

            if (wordEditText.getText().isNotEmpty()) {
                words[now] = wordEditText.getText().toString()
            }

            Log.d("name", names[0])
            Log.d("word", words[0])

            Toast.makeText(applicationContext, "ok", Toast.LENGTH_LONG).show()

            if (now == count - 1) {
                intent = Intent(this, ShowActivity::class.java)
            } else {
                intent = Intent(this, RegisterActivity::class.java)
            }

            intent.putExtra("count", count)
            intent.putExtra("now", (now + 1)%count)
            intent.putExtra("names", names)
            intent.putExtra("words", words)
            startActivity(intent)
        }
    }
}
