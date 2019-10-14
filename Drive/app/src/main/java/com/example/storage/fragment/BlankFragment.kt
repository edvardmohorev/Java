package com.example.storage.fragment


import android.app.Activity
import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.os.Environment
import android.util.Log
import android.view.*
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.Scope
import com.google.api.client.extensions.android.http.AndroidHttp
import com.google.api.client.googleapis.extensions.android.gms.auth.GoogleAccountCredential
import com.google.api.client.json.gson.GsonFactory
import com.google.api.services.drive.Drive
import com.google.api.services.drive.DriveScopes
import android.view.MenuInflater
import android.widget.EditText
import android.widget.Toast
import com.example.storage.activity.CreateFile
import com.example.storage.classes.DriveServiceHelper
import com.example.storage.recycleAdapter.ListAdapter
import com.example.storage.R
import java.io.File


@Suppress("UNREACHABLE_CODE")
class BlankFragment : Fragment() {
    lateinit var nameFile:String
    lateinit var textFile:String
    lateinit var client: GoogleSignInClient
    lateinit var sing: Button
    lateinit var viewFragment:View
    var flag:Int=0
    private var mDriveServiceHelper: DriveServiceHelper? = null
    private lateinit var mDocContentEditText: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        viewFragment =inflater.inflate(R.layout.fragment_main, container, false)
        setHasOptionsMenu(true)
        return viewFragment

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val context: Context? =context
        mDocContentEditText = view.findViewById(R.id.doc_content_edittext)
        mDocContentEditText.layoutManager= LinearLayoutManager(context)
        val sign_in=view.findViewById<Button>(R.id.sign_in)

        // Authenticate the user. For most apps, this should be done when the user performs an
        // action that requires Drive access rather than in onCreate.
        requestSignIn()
        sing=view.findViewById(R.id.sign_in)
        sing.setOnClickListener {
            requestSignIn()
        }
    }
    public override fun onActivityResult(requestCode: Int, resultCode: Int, resultData: Intent?) {
        when (requestCode) {
            REQUEST_CODE_SIGN_IN -> if (resultCode == Activity.RESULT_OK && resultData != null) {
                handleSignInResult(resultData)
                mDocContentEditText.visibility=View.VISIBLE
                sing.visibility=View.GONE

            }

            REQUEST_CODE_OPEN_DOCUMENT -> if (resultCode == Activity.RESULT_OK && resultData != null) {
                val uri = resultData.data.path.split(":")
                val sdPath = Environment.getExternalStorageDirectory()
                val file = File(sdPath.path + "/" + uri[1])
                mDriveServiceHelper?.addFile(file,flag)
                query()
            }
            REQUEST_CODE_CREATE_DOCUMENT -> if (resultCode == Activity.RESULT_OK && resultData != null) {
                nameFile = resultData.getStringExtra("nameFile")
                textFile = resultData.getStringExtra("textFile")
                mDriveServiceHelper!!.createFile(textFile,nameFile)
//                query()
            }
        }
        super.onActivityResult(requestCode, resultCode, resultData)
    }
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_list, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.create_doc ->
                createFile()
                    R.id.upload->
                        addFile()
            R.id.create_folder
                    ->
                createFolder()
            R.id.requery ->
                query()
            R.id.sign_out -> {
                client.revokeAccess()
                    .addOnSuccessListener {
                        mDocContentEditText.visibility = View.GONE
                        sing.visibility = View.VISIBLE
                    }
            }
            R.id.search ->search()
        }
        return super.onOptionsItemSelected(item)
    }
    fun addFile(){
        val catNamesArray:Array<CharSequence> = listOf<String>("Документ","Картинка").toTypedArray()
        val intent = Intent(Intent.ACTION_OPEN_DOCUMENT)

    val builder = AlertDialog.Builder(context)
    builder.setTitle("Выберите тип файла")
            .setItems(catNamesArray, DialogInterface.OnClickListener() { dialogInterface: DialogInterface, i: Int ->
                    when(i){
                        0->{
                            intent.type = "application/*"
                            startActivityForResult(intent, REQUEST_CODE_OPEN_DOCUMENT)
                            flag=0
                        }
                        1->{
                            intent.type = "image/*"
                            startActivityForResult(intent, REQUEST_CODE_OPEN_DOCUMENT)
                            flag=1
                        }
                    }
            })

    builder.show()
    }
    private fun requestSignIn() {
        Log.d(TAG, "Requesting sign-in")

        val signInOptions = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestEmail()
            .requestScopes(Scope(DriveScopes.DRIVE))
            .build()
        client = GoogleSignIn.getClient(context!!,signInOptions)

        // The result of the sign-in Intent is handled in onActivityResult.
        startActivityForResult(client.signInIntent,
            REQUEST_CODE_SIGN_IN
        )

    }
//    override fun onCreateOptionsMenu(menu: Menu?,menuInflater:MenuInflater) {
//        menuInflater.inflate(R.menu.menu_list,menu)
//    }
    private fun handleSignInResult(result: Intent) {
        GoogleSignIn.getSignedInAccountFromIntent(result)
            .addOnSuccessListener { googleAccount ->
                Log.d(TAG, "Signed in as " + googleAccount.email!!)

                // Use the authenticated account to sign in to the Drive service.
                val credential = GoogleAccountCredential.usingOAuth2(
                    context, setOf(DriveScopes.DRIVE)
                )
                credential.selectedAccount = googleAccount.account
                val googleDriveService = Drive.Builder(
                    AndroidHttp.newCompatibleTransport(),
                    GsonFactory(),
                    credential
                )
                    .setApplicationName("Drive API Migration")
                    .build()

                // The DriveServiceHelper encapsulates all REST API and SAF functionality.
                // Its instantiation is required before handling any onClick actions.
                mDriveServiceHelper = DriveServiceHelper(googleDriveService)
                query()
                Log.d(TAG, Environment.DIRECTORY_DOWNLOADS)
            }
            .addOnFailureListener { exception -> Log.e(TAG, "Unable to sign in.", exception) }
    }

    fun createFile() {
        if (mDriveServiceHelper != null) {
            Log.d(TAG, "Creating a file.")
            val intent= Intent(context, CreateFile::class.java)
            startActivityForResult(intent,
                REQUEST_CODE_CREATE_DOCUMENT
            )

        }
    }
    fun query(parent:String?=null) {
        Log.d(TAG, "Querying for files.")

        mDriveServiceHelper!!.queryFiles()?.addOnSuccessListener { result ->
            val train= R.id.action_blankFragment_to_blankFragment2
            val adapter= ListAdapter(
                result,
                context!!,
                mDriveServiceHelper!!,
                viewFragment,
                train
            )
            mDocContentEditText.adapter=adapter
        }
            ?.addOnFailureListener { exception -> Log.e(TAG, "Unable to query files.", exception) }
    }

    fun createFolder(){
        val li = LayoutInflater.from(context)
        val prompt = li.inflate(R.layout.folder_dialog, null)
        val mDialog: AlertDialog.Builder = AlertDialog.Builder(context)
        val folder=prompt.findViewById(R.id.editText) as EditText
        val folderName=folder.text
        mDialog.setTitle("Введите название папки")
        mDialog.setView(prompt)
        mDialog
            .setCancelable(false)
            .setPositiveButton("Создать", DialogInterface.OnClickListener() { _: DialogInterface, _: Int ->
                mDriveServiceHelper!!.createFolder(folderName.toString())
                query()
            })
            .setNegativeButton("Отмена", DialogInterface.OnClickListener() { dialogInterface: DialogInterface, i: Int ->
                fun onClick(dialog: DialogInterface, id: Int) {
                    Log.d(TAG,"Cancel")
                }
            })
        val alertDialog = mDialog.create()
        alertDialog.show()
    }
    private fun search(){
        val li = LayoutInflater.from(context)
        val prompt = li.inflate(R.layout.folder_dialog, null)
        val mDialog: AlertDialog.Builder = AlertDialog.Builder(context)
        val folder=prompt.findViewById(R.id.editText) as EditText
        val folderName=folder.text
        mDialog.setTitle("Введите название файла или папки")
        mDialog.setView(prompt)
        mDialog
            .setCancelable(false)
            .setPositiveButton("Найти", DialogInterface.OnClickListener() { _: DialogInterface, _: Int ->
                mDriveServiceHelper!!.searchFile(folderName.toString())
                    ?.addOnSuccessListener { results ->
                        val train= R.id.action_blankFragment_to_blankFragment2
                        val adapter = ListAdapter(
                            results,
                            context!!,
                            mDriveServiceHelper!!,
                            viewFragment,
                            train
                        )
                        mDocContentEditText.adapter = adapter
                    }
            })
            .setNegativeButton("Отмена", DialogInterface.OnClickListener() { dialogInterface: DialogInterface, i: Int ->
                fun onClick(dialog: DialogInterface, id: Int) {
                    Log.d(TAG,"Cancel")
                }
            })
        val alertDialog = mDialog.create()
        alertDialog.show()
    }

    companion object {
        val TAG = "MainActivity"
        val REQUEST_CODE_SIGN_IN = 1
        private val REQUEST_CODE_OPEN_DOCUMENT = 2
        val REQUEST_CODE_CREATE_DOCUMENT=3
    }


}
