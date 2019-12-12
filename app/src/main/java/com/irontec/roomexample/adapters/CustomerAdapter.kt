package com.irontec.roomexample.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.irontec.roomexample.R
import com.irontec.roomexample.database.entities.Course
import kotlinx.android.synthetic.main.row_customer.view.*

/**
 * Created by axier on 8/2/18.
 */
class CustomerAdapter(private val mContext: Context, private val mData: MutableList<Course>) : BaseAdapter() {

    fun addAll(cours: List<Course>) {
        mData.clear()
        mData.addAll(cours)
        notifyDataSetChanged()
    }

    override fun getCount(): Int {
        return mData.size
    }

    override fun getItem(position: Int): Any {
        return mData[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var view = convertView
        val viewHolder: ViewHolder
        if (view != null) {
            viewHolder = view.tag as ViewHolder
        } else {
            view = LayoutInflater.from(mContext)
                    .inflate(R.layout.row_customer, parent, false)
            viewHolder = ViewHolder(view)
            view.tag = viewHolder
        }

        val customer = mData[position]
        viewHolder.title.text = ("""${customer.name}  USD b:${customer.usd_buy} s:${customer.usd_sell} EUR b:${customer.eur_buy} s:${customer.eur_sell}""")

        return view!!
    }

    internal class ViewHolder(view: View) {

        var title: TextView

        init {
            title = view.customer_title
        }
    }

}
