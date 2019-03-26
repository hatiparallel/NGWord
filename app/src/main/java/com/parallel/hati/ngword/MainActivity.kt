package com.parallel.hati.ngword

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<Button>(R.id.howto).setOnClickListener {
            Toast.makeText(applicationContext, "hello", Toast.LENGTH_SHORT).show()
        }

        findViewById<Button>(R.id.start).setOnClickListener {
            Toast.makeText(applicationContext, "start", Toast.LENGTH_LONG).show()
            startActivity(Intent(this, RegisterActivity::class.java))
        }
    }
}
