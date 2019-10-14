package com.example.storage.recycleAdapter

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.*
import android.widget.*
import androidx.recyclerview.widget.RecyclerView
import androidx.core.content.ContextCompat.startActivity
import com.example.storage.classes.DriveServiceHelper
import com.example.storage.classes.Files
import com.example.storage.R
import com.example.storage.activity.Replace


class ListAdapterLite(val fileList: ArrayList<Files>, val context: Context, val mDriveServiceHelper: DriveServiceHelper): RecyclerView.Adapter<ListAdapterLite.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false)
        return ViewHolder(view)

    }

    override fun getItemCount(): Int {
        return fileList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.viewHold(fileList[position])
        holder.itemView.tag = fileList
    }
    public class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val context = itemView.context
        lateinit var idFile: String
        lateinit var parent: MutableList<String>
        lateinit var name: TextView
        lateinit var button: Button
        lateinit var imageView: ImageView
        lateinit var url:String
        lateinit var nameFile:String

        fun viewHold(files: Files) {
            imageView = itemView.findViewById(R.id.icon)
            if (files.type == "application/vnd.google-apps.folder") {
//                imageView.setImageResource(R.drawable.ic_action_name)
                idFile = files.id
                url= files.url.toString()
                nameFile= files.name.toString()
                parent = files.parents!!
                name = itemView.findViewById(R.id.name)
                button = itemView.findViewById(R.id.button)
                name.text = nameFile
                name.setOnClickListener {
                    if (files.type == "application/vnd.google-apps.folder") {
                        val intent = Intent(context, Replace::class.java)
                        intent.putExtra("parent",idFile)
                        intent.putExtra("parentFile",parent.toString())
                        intent.putExtra("id",idFile)
                        startActivity(context,intent, Bundle.EMPTY)
                        startActivity(context, intent, Bundle.EMPTY)
                    }
                }
            }

        }


    }
}

