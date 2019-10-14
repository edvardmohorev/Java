package com.example.storage.recycleAdapter

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.util.Log
import android.view.*
import android.widget.*
import androidx.recyclerview.widget.RecyclerView
import androidx.core.content.ContextCompat.startActivity
import androidx.core.os.bundleOf
import androidx.navigation.Navigation
import com.example.storage.classes.DriveServiceHelper
import com.example.storage.classes.Files
import com.example.storage.R
import com.example.storage.fragment.BlankFragment
import java.io.*


class ListAdapter(
    val fileList: ArrayList<Files>,
    val context: Context,
    val mDriveServiceHelper: DriveServiceHelper,
    val viewFragment: View,
    val train: Int? =null
): RecyclerView.Adapter<ListAdapter.ViewHolder>() {


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
        holder.driveServiceHelper = mDriveServiceHelper

        val position = position
        val infoData=fileList.get(position)
        holder.name.setOnClickListener {
                if (holder.type == "application/vnd.google-apps.folder") {
                    val bundle=bundleOf("name" to holder.idFile)
                    Log.d(BlankFragment.TAG,bundle.toString())
                    Navigation.findNavController(viewFragment).navigate(train!!,bundle)
                }
                else {
                    val intent = Intent(Intent.ACTION_VIEW, Uri.parse(holder.url))
                    startActivity(context, intent, Bundle.EMPTY)
                }

        }
        holder.button.setOnClickListener { view ->
            val popupMenu = PopupMenu(context, view)
            popupMenu.setOnMenuItemClickListener {view->
                when(view.itemId) {
                    R.id.it1 ->
                        delete(holder.idFile, position, infoData, holder)
                    R.id.it2 -> {
                        val li = LayoutInflater.from(context)
                        val prompt = li.inflate(R.layout.folder_dialog, null)
                        val mDialog: AlertDialog.Builder = AlertDialog.Builder(context)
                        val folder=prompt.findViewById(R.id.editText) as EditText
                        val folderName=folder.text

                        val id=holder.idFile
                        mDialog.setView(prompt)
                        mDialog.setTitle("Введите новое имя")
                        mDialog
                            .setCancelable(false)
                            .setPositiveButton("Переменовать", DialogInterface.OnClickListener() { _: DialogInterface, _: Int ->
                                mDriveServiceHelper.renameFileAndFolder(id,folderName.toString())
                                holder.name.text=folderName.toString()
                            })
                            .setNegativeButton("Отмена", DialogInterface.OnClickListener() { dialogInterface: DialogInterface, i: Int ->
                                fun onClick(dialog: DialogInterface, id: Int) {
                                    Log.d(BlankFragment.TAG,"Cancel")
                                }
                            })
                        val alertDialog = mDialog.create()
                        alertDialog.show()
                    }
                    R.id.it3 ->{
//                        val intent=Intent(context,Replace::class.java)
//                        val bundle=bundleOf("parent" to "root",
//                            "parentFile" to holder.parent.toString(),
//                            "id" to holder.idFile)
//                        startActivity(context,intent,bundle)
                    }
                    R.id.it4 ->{
                       mDriveServiceHelper.download(holder.idFile,holder.nameFile,holder.type,context)
                    }
                }
                return@setOnMenuItemClickListener true
            }
            popupMenu.inflate(R.menu.recycle_list)
            popupMenu.show()
        }

    }
    fun delete(id: String,
               position: Int,
               infoData: Files,
               holder: ViewHolder
    ) {
        val al = AlertDialog.Builder(context)
        al.setTitle("Удалить ?")
        al.setPositiveButton("Да", DialogInterface.OnClickListener { dialogInterface, i ->
            mDriveServiceHelper.deleteFile(id)
            removeItem(infoData)
        })
        al.setNegativeButton("Нет", DialogInterface.OnClickListener { dialogInterface, i ->

        })
        al.show()
    }
    private val BUFFER_SIZE = 1024
    private val CONNECTION_TIMEOUT = 3000
    private var sCacheDirPath: String? = null
    fun loadAndSaveFile(name:String) {
            val file = File(
                Environment.getExternalStoragePublicDirectory(
                    Environment.DIRECTORY_DOWNLOADS
                ), name
            )
            Log.d(BlankFragment.TAG,file.toString())
    }



    private fun removeItem(infoData: Files) {
        val currPosition = fileList.indexOf(infoData)
        fileList.removeAt(currPosition)
        notifyItemRemoved(currPosition)
    }

    public class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val context = itemView.context
        lateinit var driveServiceHelper: DriveServiceHelper
        lateinit var idFile: String
        lateinit var parent: MutableList<String>
        lateinit var name: TextView
        lateinit var button: Button
        lateinit var imageView: ImageView
        lateinit var url:String
        lateinit var nameFile:String
        lateinit var type:String

        fun viewHold(files: Files) {
            imageView = itemView.findViewById(R.id.icon)
            type=files.type
            if (files.type == "application/vnd.google-apps.folder")
                imageView.setImageResource(R.drawable.ic_action_folder)
            else
                imageView.setImageResource(R.drawable.ic_action_name)
            idFile = files.id
            url= files.url.toString()
            nameFile= files.name.toString()
            parent = files.parents!!
            name = itemView.findViewById(R.id.name)
            button = itemView.findViewById(R.id.button)
            name.text = nameFile
        }


    }
}

