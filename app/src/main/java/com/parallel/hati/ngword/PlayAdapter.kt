package com.parallel.hati.ngword

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.Button
import android.widget.ListView
import android.widget.TextView

class PlayAdapter(context : Context, members : Array<String>) : BaseAdapter() {
    val members = members
    private val mInflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

    override public fun getItem(position : Int) : String {
        return members[position]
    }

    override public fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override public fun getCount() : Int {
        return members.size
    }
    override public fun getView(position : Int, view : View?, parent : ViewGroup): View {
        var view = view
        if (view == null) {
            view = mInflater.inflate(R.layout.view_member, null)
        }

        val item = this.getItem(position)
        if (item != null) {
            view!!.findViewById<TextView>(R.id.user_name_view).setText(item)

            view!!.findViewById<Button>(R.id.user_button).setOnClickListener {
                (parent as ListView).performItemClick(view, position, R.id.user_button.toLong())
            }
        }
        return view!!
    }
}