package com.irontec.roomexample

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import com.irontec.roomexample.adapters.CustomerAdapter
import com.irontec.roomexample.database.AppDatabase
import kotlinx.android.synthetic.main.activity_customer.*
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class CourseActivity : AppCompatActivity() {

    private var mAdapter: CustomerAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_customer)
        mAdapter = CustomerAdapter(this@CourseActivity, mutableListOf())
        customer_list.adapter = mAdapter

        doAsync {

            val database = AppDatabase.getInstance(context = this@CourseActivity)
            val customers = database.courseDao().all

            uiThread {
                mAdapter!!.addAll(customers)
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.empty, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId
        if (id == android.R.id.home) {
            finish()
        }
        if(id==R.id.convert){
            val intent=Intent(this,Convert::class.java)
            startActivity(intent)
        }
        return super.onOptionsItemSelected(item)
    }
}
