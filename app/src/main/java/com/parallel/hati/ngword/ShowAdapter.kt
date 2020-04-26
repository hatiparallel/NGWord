package com.parallel.hati.ngword

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.Button
import android.widget.ListView
import android.widget.TextView

class ShowAdapter(context : Context, wordlist : Array<String>, shuffle : IntArray, words_count : Int, now : Int) : BaseAdapter() {
    val wordlist = wordlist
    val shuffle = shuffle
    val words_count = words_count
    val now = now
    private val mInflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

    override public fun getItem(position : Int) : String {
        return wordlist[shuffle[words_count*now + position]]
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
            view = mInflater.inflate(R.layout.view_show, null)
        }

        view!!.findViewById<TextView>(R.id.word_view).setText(this.getItem(position))
        return view!!
    }
}