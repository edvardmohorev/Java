package com.irontec.roomexample

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.*
import com.irontec.roomexample.database.AppDatabase
import com.irontec.roomexample.database.entities.Course
import org.jetbrains.anko.doAsync


class Convert : AppCompatActivity() {
    lateinit var cos:List<Course>
    lateinit var bank_name:ListView
    lateinit var sell:RadioButton
    lateinit var buy:RadioButton
    lateinit var usd:RadioButton
    lateinit var eur:RadioButton
    lateinit var res:TextView
    lateinit var button:Button
    lateinit var number:EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_convert)

        bank_name=findViewById<ListView>(R.id.bank_name)
        sell=findViewById<RadioButton>(R.id.sell)
        buy=findViewById<RadioButton>(R.id.buy)
        usd=findViewById<RadioButton>(R.id.usd)
        eur=findViewById<RadioButton>(R.id.eur)
        res=findViewById<TextView>(R.id.result)
        button=findViewById<Button>(R.id.button)
        number=findViewById<EditText>(R.id.number)
        button.setOnClickListener {
            if (usd.isChecked && buy.isChecked){
                doAsync {
                    val data=AppDatabase.getInstance(context = this@Convert)
                    cos=data.courseDao().usd_buy
                }
                val num=number.text.toString()
                val result=num.toFloat()*cos[0].usd_buy!!.toFloat()
                val name:MutableList<String> = mutableListOf()
                for (el in cos){
                    name.add(el.name.toString())
                }
                val adapter=ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,name)
                bank_name.adapter = adapter
                res.text= result.toString()
            }
            if (usd.isChecked && sell.isChecked){
                doAsync {
                    val data=AppDatabase.getInstance(context = this@Convert)
                    cos=data.courseDao().usd_sell
                }
                val num=number.text.toString()
                val result=num.toFloat()*cos[0].usd_sell!!.toFloat()
                val name:MutableList<String> = mutableListOf()
                for (el in cos){
                    name.add(el.name.toString())
                }
                val adapter=ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,name)
                bank_name.adapter = adapter
                res.text= result.toString()
            }
            if (eur.isChecked && buy.isChecked){
                doAsync {
                    val data=AppDatabase.getInstance(context = this@Convert)
                    cos=data.courseDao().eur_buy
                }
                val num=number.text.toString()
                val result=num.toFloat()*cos[0].eur_buy!!.toFloat()
                val name:MutableList<String> = mutableListOf()
                for (el in cos){
                    name.add(el.name.toString())
                }
                val adapter=ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,name)
                bank_name.adapter = adapter
                res.text= result.toString()
            }
            if (eur.isChecked && sell.isChecked){
                doAsync {
                    val data=AppDatabase.getInstance(context = this@Convert)
                    cos=data.courseDao().eur_sell
                }
                val num=number.text.toString()
                val result=num.toFloat()*cos[0].eur_sell!!.toFloat()
                val name:MutableList<String> = mutableListOf()
                for (el in cos){
                    name.add(el.name.toString())
                }
                val adapter=ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,name)
                bank_name.adapter = adapter
                res.text= result.toString()
            }
        }

    }
}
