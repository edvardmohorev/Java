package com.example.storage.activity

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import com.example.storage.R

class CreateFile : AppCompatActivity(), View.OnClickListener {


    lateinit var name:EditText
    lateinit var text:EditText
    lateinit var button: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_file)
        name=findViewById(R.id.nameFile)
        text=findViewById(R.id.text)
        button=findViewById(R.id.create_btn)
        button.setOnClickListener(this)
    }
    override fun onClick(view: View) {
        val intent=Intent()
        intent.putExtra("nameFile",name.text.toString())
        intent.putExtra("textFile",text.text.toString())
        setResult(Activity.RESULT_OK,intent)
        finish()
    }
}
