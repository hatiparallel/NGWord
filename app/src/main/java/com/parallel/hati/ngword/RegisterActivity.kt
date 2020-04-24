package com.parallel.hati.ngword

import android.content.Intent
import android.graphics.Color
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast

class RegisterActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        val number = intent.getIntExtra("number", 0)
        val now = intent.getIntExtra("now", 0)

        val names = intent.getStringArrayExtra("names")
        val words = intent.getStringArrayExtra("words")

        val nameEditText = findViewById<EditText>(R.id.name_edit_text)
        val wordEditText = findViewById<EditText>(R.id.word_edit_text)
        val caution = findViewById<TextView>(R.id.caution)

        nameEditText.setText(names[now])

        findViewById<Button>(R.id.ok).setOnClickListener {
            if (nameEditText.getText().isNotEmpty()) {
                names[now] = nameEditText.getText().toString()
            } else {
                caution.setTextColor(Color.RED)
                return@setOnClickListener
            }

            if (wordEditText.getText().isNotEmpty()) {
                words[now] = wordEditText.getText().toString()
            }

            Log.d("name", names[now])
            Log.d("word", words[now])
            Log.d("now", now.toString())

            Toast.makeText(applicationContext, "ok", Toast.LENGTH_LONG).show()

            if (now == number - 1) {
                intent = Intent(this, PrepareActivity::class.java)
                intent.putExtra("shuffle", WordsDecider.shuffleNumber(number, number));
            } else {
                intent = Intent(this, RegisterActivity::class.java)
            }

            intent.putExtra("number", number)
            intent.putExtra("now", (now + 1) % number)
            intent.putExtra("names", names)
            intent.putExtra("words", words)
            startActivity(intent)
        }
    }
}
