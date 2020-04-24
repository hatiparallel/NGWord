package com.parallel.hati.ngword

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.Button
import android.widget.ListView
import android.widget.TextView

class FinishAdapter(context : Context, names : Array<String>, words : Array<String>) : BaseAdapter() {
    val names = names
    val words = words
    private val mInflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

    override public fun getItem(position : Int) : Pair<String, String> {
        return Pair(names[position], words[position])
    }

    override public fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override public fun getCount() : Int {
        return names.size
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