package com.parallel.hati.ngword

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast

class RegisterActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        findViewById<Button>(R.id.ok).setOnClickListener {
            Toast.makeText(applicationContext, "ok", Toast.LENGTH_LONG).show()
        }
    }
}
