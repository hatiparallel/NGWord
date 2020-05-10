package com.parallel.hati.ngword

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.Button
import android.widget.ListView
import android.widget.TextView

class FinishAdapter(context : Context, words_count : Int, namelist : Array<String>, wordlist : Array<String>, shuffle : IntArray) : BaseAdapter() {
    val words_count = words_count
    val namelist = namelist
    val wordlist = wordlist
    val shuffle = shuffle
    private val mInflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

    override public fun getItem(position : Int) : Pair<String, String> {
        var word_rtn = ""
        for (w in 0..words_count - 1) {
            if (w != 0) {
                word_rtn += "・"
            }
            word_rtn += wordlist[shuffle[position*words_count + w]]
        }
        return Pair(namelist[position], word_rtn)
    }

    override public fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override public fun getCount() : Int {
        return namelist.size
    }
    override public fun getView(position : Int, view : View?, parent : ViewGroup): View {
        var view = view
        if (view == null) {
            view = mInflater.inflate(R.layout.view_finish, null)
        }

        val pair = this.getItem(position)
        if (pair != null) {
            view!!.findViewById<TextView>(R.id.user_name_view).setText(pair.first)
            view!!.findViewById<TextView>(R.id.word_view).setText(pair.second)
        }
        return view!!
    }
}