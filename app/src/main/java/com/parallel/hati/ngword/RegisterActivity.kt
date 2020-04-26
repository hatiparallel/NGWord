package com.parallel.hati.ngword

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.KeyEvent
import android.view.View
import android.view.View.OnKeyListener
import android.view.inputmethod.InputMethodManager
import android.widget.*


class RegisterActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        val people_count = intent.getIntExtra("people_count", 0)
        val words_count = intent.getIntExtra("words_count", 0)
        val now = intent.getIntExtra("now", 0)

        val namelist = intent.getStringArrayExtra("namelist")
        val wordlist = intent.getStringArrayExtra("wordlist")

        val nameEditText = findViewById<EditText>(R.id.name_edit_text)
        val wordEditText = findViewById<EditText>(R.id.word_edit_text)
        val caution = findViewById<TextView>(R.id.caution)
        val okButton = findViewById<Button>(R.id.ok)

        nameEditText.setText(namelist[now])
        wordEditText.setVisibility(View.GONE)

        val listView = findViewById(R.id.member_list_view) as ListView
        val adapter = RegisterAdapter(this, words_count)
        listView.adapter = adapter

        val isValid = Array<Boolean>(words_count, { false })

        listView.onItemClickListener = AdapterView.OnItemClickListener { parent, view, position, id ->
            okButton.setVisibility(View.GONE)
            wordEditText.setVisibility(View.VISIBLE)
            wordEditText.requestFocus()
            val imm: InputMethodManager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.showSoftInput(wordEditText, 0)
            wordEditText.setVisibility(View.VISIBLE)

            if (isValid[position]) {
                wordEditText.setText(adapter.getItem(position)!!.text, TextView.BufferType.NORMAL)
            }

            wordEditText.addTextChangedListener(object : TextWatcher {
                override fun afterTextChanged(s: Editable?) {
                    Log.d("msg", s.toString())
                }
                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

                }
                override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

                }
            })

            wordEditText.setOnKeyListener(object : OnKeyListener {
                override fun onKey(v: View, keyCode: Int, event: KeyEvent): Boolean {
                    if (event.getAction() == KeyEvent.ACTION_DOWN && keyCode == KeyEvent.KEYCODE_ENTER) {
                        imm.hideSoftInputFromWindow(v.getWindowToken(), 0)
                        val written = wordEditText.text
                        Log.d("text", written.toString())
                        isValid[position] = adapter.update(written.toString(), position)
                        wordEditText.setVisibility(View.GONE)
                        wordEditText.clearFocus()
                        wordEditText.editableText.clear()
                        okButton.setVisibility(View.VISIBLE)
                        return true
                    }
                    return false
                }
            })
        }


        okButton.setOnClickListener {
            if (nameEditText.getText().isNotEmpty()) {
                namelist[now] = nameEditText.getText().toString()
            } else {
                caution.setTextColor(Color.RED)
                return@setOnClickListener
            }

            for (pos in 0..words_count - 1) {
                val textview = adapter.getItem(pos)
                if (textview != null && isValid[pos]) {
                    WordsDecider.setWord(wordlist, words_count, now, pos, textview.text.toString())
                }
            }

            if (now == people_count - 1) {
                intent = Intent(this, PrepareActivity::class.java)
                intent.putExtra("shuffle", WordsDecider.shuffleNumber(people_count, people_count));
            } else {
                intent = Intent(this, RegisterActivity::class.java)
            }

            intent.putExtra("people_count", people_count)
            intent.putExtra("words_count", words_count)
            intent.putExtra("now", (now + 1) % people_count)
            intent.putExtra("namelist", namelist)
            intent.putExtra("wordlist", wordlist)
            startActivity(intent)
        }
    }
}
