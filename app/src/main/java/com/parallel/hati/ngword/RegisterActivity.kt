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

private fun fillNameText(textView : TextView, name : String) {
    if (name.isNotEmpty()) {
        textView.setTextColor(Color.BLACK)
        textView.setText(name)
    } else {
        textView.setTextColor(Color.GRAY)
        textView.setText(R.string.name_edit)
    }
}

class RegisterActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        val people_count = intent.getIntExtra("people_count", 0)
        val words_count = intent.getIntExtra("words_count", 0)
        val now = intent.getIntExtra("now", 0)

        val namelist = intent.getStringArrayExtra("namelist")
        val wordlist = intent.getStringArrayExtra("wordlist")

        val nameText = findViewById<TextView>(R.id.name_text)
        val inputEditText = findViewById<EditText>(R.id.input_edit_text)
        val caution = findViewById<TextView>(R.id.caution)
        val okButton = findViewById<Button>(R.id.ok)

        val inputDecider = findViewById<LinearLayout>(R.id.input_decider)

        fillNameText(nameText, namelist[now])
        inputDecider.setVisibility(View.GONE)

        val listView = findViewById(R.id.member_list_view) as ListView
        val adapter = RegisterAdapter(this, words_count)
        listView.adapter = adapter

        val isValid = Array<Boolean>(words_count, { false })

        var typing = false

        val imm: InputMethodManager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager

        nameText.setOnClickListener {
            if (typing) {
                return@setOnClickListener
            }
            typing = true

            okButton.setVisibility(View.GONE)

            inputDecider.setVisibility(View.VISIBLE)
            inputEditText.requestFocus()
            imm.showSoftInput(inputEditText, 0)
            inputDecider.setVisibility(View.VISIBLE)

            if (namelist[now].isNotEmpty()) {
                inputEditText.setText(namelist[now], TextView.BufferType.NORMAL)
            }

            inputEditText.setOnKeyListener(object : OnKeyListener {
                override fun onKey(v: View, keyCode: Int, event: KeyEvent): Boolean {
                    if (event.getAction() == KeyEvent.ACTION_DOWN && keyCode == KeyEvent.KEYCODE_ENTER) {
                        imm.hideSoftInputFromWindow(v.getWindowToken(), 0)

                        val written = inputEditText.text.toString()
                        fillNameText(nameText, written)
                        namelist[now] = written

                        inputDecider.setVisibility(View.GONE)
                        inputEditText.clearFocus()
                        inputEditText.editableText.clear()

                        okButton.setVisibility(View.VISIBLE)
                        typing = false
                        return true
                    }
                    return false
                }
            })

            findViewById<Button>(R.id.input_decide_button).setOnClickListener {
                imm.hideSoftInputFromWindow(it.getWindowToken(), 0)

                val written = inputEditText.text.toString()
                fillNameText(nameText, written)
                namelist[now] = written

                inputDecider.setVisibility(View.GONE)
                inputEditText.clearFocus()
                inputEditText.editableText.clear()

                okButton.setVisibility(View.VISIBLE)
                typing = false
            }
        }

        /*

        inputEditText.setOnFocusChangeListener(object : View.OnFocusChangeListener {
            override fun onFocusChange(view : View?, hasFocus : Boolean) {
                if (hasFocus) {
                    okButton.setVisibility(View.GONE)
                } else {
                    imm.hideSoftInputFromWindow(view!!.getWindowToken(), 0)
                    okButton.setVisibility(View.VISIBLE)
                }
            }
        })

        inputEditText.setOnKeyListener(object : OnKeyListener {
            override fun onKey(v: View, keyCode: Int, event: KeyEvent) : Boolean {
                Log.d("code", keyCode.toString())
                if (event.getAction() == KeyEvent.ACTION_DOWN && keyCode == KeyEvent.KEYCODE_ENTER) {
                    imm.hideSoftInputFromWindow(v.getWindowToken(), 0)
                    okButton.setVisibility(View.VISIBLE)
                    return true
                }
                return true
            }
        })

         */

        listView.onItemClickListener = AdapterView.OnItemClickListener { parent, view, position, id ->
            if (typing) {
                return@OnItemClickListener
            }
            typing = true

            okButton.setVisibility(View.GONE)

            inputDecider.setVisibility(View.VISIBLE)
            inputEditText.requestFocus()
            imm.showSoftInput(inputEditText, 0)
            inputDecider.setVisibility(View.VISIBLE)

            if (isValid[position]) {
                inputEditText.setText(adapter.getItem(position)!!.text, TextView.BufferType.NORMAL)
            }

            /*
            inputEditText.setOnFocusChangeListener(object : View.OnFocusChangeListener {
                override fun onFocusChange(view : View?, hasFocus : Boolean) {
                    if (hasFocus) {
                        okButton.setVisibility(View.GONE)
                    } else {
                        imm.hideSoftInputFromWindow(view!!.getWindowToken(), 0)
                        okButton.setVisibility(View.VISIBLE)
                    }
                }
            })
             */

            inputEditText.setOnKeyListener(object : OnKeyListener {
                override fun onKey(v: View, keyCode: Int, event: KeyEvent): Boolean {
                    if (event.getAction() == KeyEvent.ACTION_DOWN && keyCode == KeyEvent.KEYCODE_ENTER) {
                        imm.hideSoftInputFromWindow(v.getWindowToken(), 0)

                        val written = inputEditText.text
                        isValid[position] = adapter.update(written.toString(), position)

                        inputDecider.setVisibility(View.GONE)
                        inputEditText.clearFocus()
                        inputEditText.editableText.clear()

                        okButton.setVisibility(View.VISIBLE)
                        typing = false

                        return true
                    }
                    return false
                }
            })

            findViewById<Button>(R.id.input_decide_button).setOnClickListener {
                imm.hideSoftInputFromWindow(view.getWindowToken(), 0)

                val written = inputEditText.text
                isValid[position] = adapter.update(written.toString(), position)

                inputDecider.setVisibility(View.GONE)
                inputEditText.clearFocus()
                inputEditText.editableText.clear()

                okButton.setVisibility(View.VISIBLE)
                typing = false
            }
        }


        okButton.setOnClickListener {
            if (namelist[now].isEmpty()) {
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
                intent.putExtra("shuffle", WordsDecider.shuffleNumber(people_count, words_count));
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
