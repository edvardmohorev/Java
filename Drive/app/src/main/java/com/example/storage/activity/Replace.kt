package com.example.storage.activity

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView
import com.example.storage.R
import com.example.storage.classes.DriveServiceHelper

class Replace : AppCompatActivity() {
    lateinit var oldParent: String
    lateinit var newParent: String
    lateinit var id: String

    private var mDriveServiceHelper: DriveServiceHelper? = null
    lateinit var replace: Button


    private lateinit var mDocContentEditText: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.content_replace)
    }
}

//        // Store the EditText boxes to be updated when files are opened/created/modified.
////        mFileTitleEditText = findViewById(R.id.file_title_edittext)
//        mDocContentEditText = findViewById(R.id.doc_content_edittext)
//        mDocContentEditText.layoutManager= LinearLayoutManager(this) as RecyclerView.LayoutManager?
//        replace=findViewById(R.id.replace)
//        val ad=intent.extras
//        newParent=ad!!.get("parent").toString()
//        oldParent=ad!!.get("parentFile").toString()
//        id=ad!!.get("id").toString()
//        replace.setOnClickListener {
//            mDriveServiceHelper!!.replace(id,oldParent,newParent)
//            finish()
//            val intent=Intent(this,MainActivity::class.java)
//            startActivity(intent)
//        }
//        Log.d(TAG,"$id,${newParent.toString()},$oldParent")
//
//
//        // Authenticate the user. For most apps, this should be done when the user performs an
//        // action that requires Drive access rather than in onCreate.
//        requestSignIn()
//
//    }
//
//
//    public override fun onActivityResult(requestCode: Int, resultCode: Int, resultData: Intent?) {
//        when (requestCode) {
//            REQUEST_CODE_SIGN_IN -> if (resultCode == Activity.RESULT_OK && resultData != null) {
//                handleSignInResult(resultData)
//            }
//
////            REQUEST_CODE_OPEN_DOCUMENT -> if (resultCode == Activity.RESULT_OK && resultData != null) {
////                val uri = resultData.data
////                if (uri != null) {
//////                    openFileFromFilePicker(uri)
////                }
////            }
//
//        }
//        super.onActivityResult(requestCode, resultCode, resultData)
//    }
//
//    /**
//     * Starts a sign-in activity using [.REQUEST_CODE_SIGN_IN].
//     */
//    private fun requestSignIn() {
//        Log.d(TAG, "Requesting sign-in")
//
//        val signInOptions = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
//            .requestEmail()
//            .requestScopes(Scope(DriveScopes.DRIVE))
//            .build()
//        val client = GoogleSignIn.getClient(this,signInOptions)
//
//        // The result of the sign-in Intent is handled in onActivityResult.
//        startActivityForResult(client.signInIntent, REQUEST_CODE_SIGN_IN)
//    }
//
//    /**
//     * Handles the `result` of a completed sign-in activity initiated from [ ][.requestSignIn].
//     */
//    private fun handleSignInResult(result: Intent) {
//        GoogleSignIn.getSignedInAccountFromIntent(result)
//            .addOnSuccessListener { googleAccount ->
//                Log.d(TAG, "Signed in as " + googleAccount.email!!)
//
//                // Use the authenticated account to sign in to the Drive service.
//                val credential = GoogleAccountCredential.usingOAuth2(
//                    this, setOf(DriveScopes.DRIVE)
//                )
//                credential.selectedAccount = googleAccount.account
//                val googleDriveService = Drive.Builder(
//                    AndroidHttp.newCompatibleTransport(),
//                    GsonFactory(),
//                    credential
//                )
//                    .setApplicationName("Drive API Migration")
//                    .build()
//
//                // The DriveServiceHelper encapsulates all REST API and SAF functionality.
//                // Its instantiation is required before handling any onClick actions.
//                mDriveServiceHelper = DriveServiceHelper(googleDriveService)
//                query(newParent,"application/vnd.google-apps.folder")
//            }
//            .addOnFailureListener { exception -> Log.e(TAG, "Unable to sign in.", exception) }
//    }
//    fun query(parent:String?,mimeType:String?) {
//        val fileList = arrayListOf<Files>()
//        Log.d(TAG, "Querying for files.")
//
//        mDriveServiceHelper!!.queryFiles(parent,mimeType)
//            ?.addOnSuccessListener { result ->
//
//                val adapter=ListAdapterLite(result,this,mDriveServiceHelper!!)
//                mDocContentEditText.adapter=adapter
//
//                Log.d(TAG, fileList.toString())
//
//            }
//            ?.addOnFailureListener { exception -> Log.e(TAG, "Unable to query files.", exception) }
//    }
//    companion object {
//        val TAG = "MainActivity"
//
//        val REQUEST_CODE_SIGN_IN = 1
//        private val REQUEST_CODE_OPEN_DOCUMENT = 2
//        val REQUEST_CODE_CREATE_DOCUMENT=3
//    }
//}