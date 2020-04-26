package com.parallel.hati.ngword

import android.content.Context
import android.graphics.Color
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*

class RegisterAdapter(context : Context, words_count : Int) : BaseAdapter() {
    val words_count = words_count
    val textviewlist = Array<TextView?>(words_count, { null })
    val textlist = Array<String>(words_count, { "" })
    private val mInflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

    override public fun getItem(position : Int) : TextView? {
        return textviewlist[position]
    }

    override public fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override public fun getCount() : Int {
        return words_count
    }
    override public fun getView(position : Int, view : View?, parent : ViewGroup): View {
        var view = view
        if (view == null) {
            view = mInflater.inflate(R.layout.view_register, null)
            textviewlist[position] = view!!.findViewById<TextView>(R.id.word_text)
            // textviewlist[position]!!.text = textlist[position]
            update(textlist[position], position)
        }

        view!!.findViewById<TextView>(R.id.word_text).setOnClickListener {
            (parent as ListView).performItemClick(view, position, R.id.word_text.toLong())
        }

        return view!!
    }

    open fun update(word: String, position: Int): Boolean {
        textlist[position] = word
        if (textviewlist[position] != null) {
            if (word.length > 0) {
                textviewlist[position]!!.setTextColor(Color.BLACK)
                textviewlist[position]!!.text = word
                return true
            } else {
                textviewlist[position]!!.setTextColor(Color.GRAY)
                textviewlist[position]!!.text = WordsDecider.getNoneWord(position)
                return false
            }
        }
        return false
    }
}