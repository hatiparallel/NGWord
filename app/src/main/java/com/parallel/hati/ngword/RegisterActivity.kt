package com.parallel.hati.ngword

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast

class RegisterActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        val count = intent.getIntExtra("count", 0)
        val now = intent.getIntExtra("now", 0)

        val names = intent.getStringArrayListExtra("names")
        val words = intent.getStringArrayListExtra("words")

        findViewById<Button>(R.id.ok).setOnClickListener {
            Toast.makeText(applicationContext, "ok", Toast.LENGTH_LONG).show()
            intent = Intent(this, RegisterActivity::class.java)
            intent.putExtra("count", count)
            intent.putExtra("now", now + 1)
        }
    }
}
