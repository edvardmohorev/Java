package com.example.storage.fragment


import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.storage.*
import com.example.storage.activity.MainActivity
import com.example.storage.classes.DriveServiceHelper
import com.example.storage.classes.Files
import com.example.storage.recycleAdapter.ListAdapterLite
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.Scope
import com.google.api.client.extensions.android.http.AndroidHttp
import com.google.api.client.googleapis.extensions.android.gms.auth.GoogleAccountCredential
import com.google.api.client.json.gson.GsonFactory
import com.google.api.services.drive.Drive
import com.google.api.services.drive.DriveScopes


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
/**
 * A simple [Fragment] subclass.
 *
 */
class ReplaceFragment : Fragment() {
    lateinit var oldParent:String
    lateinit var newParent:String
    lateinit var id:String

    private var mDriveServiceHelper: DriveServiceHelper? = null
    lateinit var replace: Button



    private lateinit var mDocContentEditText: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val viewFragment= inflater.inflate(R.layout.fragment_replace, container, false)
        newParent=arguments!!.getString("parent")
        oldParent=arguments!!.getString("parentFile")
        id=arguments!!.getString("id")
        return viewFragment
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val context: Context? =context
        mDocContentEditText = view.findViewById(R.id.doc_content_edittext)
        mDocContentEditText.layoutManager= LinearLayoutManager(context)
        replace=view.findViewById(R.id.replace)
        replace.setOnClickListener {
            mDriveServiceHelper!!.replace(id,oldParent,newParent)
            val intent= Intent(context, MainActivity::class.java)
            startActivity(intent)
        }
        Log.d(TAG,"$id,${newParent.toString()},$oldParent")


        // Authenticate the user. For most apps, this should be done when the user performs an
        // action that requires Drive access rather than in onCreate.
        requestSignIn()

    }


    public override fun onActivityResult(requestCode: Int, resultCode: Int, resultData: Intent?) {
        when (requestCode) {
            REQUEST_CODE_SIGN_IN -> if (resultCode == Activity.RESULT_OK && resultData != null) {
                handleSignInResult(resultData)
            }

//            REQUEST_CODE_OPEN_DOCUMENT -> if (resultCode == Activity.RESULT_OK && resultData != null) {
//                val uri = resultData.data
//                if (uri != null) {
////                    openFileFromFilePicker(uri)
//                }
//            }

        }
        super.onActivityResult(requestCode, resultCode, resultData)
    }

    /**
     * Starts a sign-in activity using [.REQUEST_CODE_SIGN_IN].
     */
    private fun requestSignIn() {
        Log.d(TAG, "Requesting sign-in")

        val signInOptions = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestEmail()
            .requestScopes(Scope(DriveScopes.DRIVE))
            .build()
        val client = GoogleSignIn.getClient(context!!,signInOptions)

        // The result of the sign-in Intent is handled in onActivityResult.
        startActivityForResult(client.signInIntent,
            REQUEST_CODE_SIGN_IN
        )
    }

    /**
     * Handles the `result` of a completed sign-in activity initiated from [ ][.requestSignIn].
     */
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
                query(newParent,"application/vnd.google-apps.folder")
            }
            .addOnFailureListener { exception -> Log.e(TAG, "Unable to sign in.", exception) }
    }
    fun query(parent:String?,mimeType:String?) {
        val fileList = arrayListOf<Files>()
        Log.d(TAG, "Querying for files.")

        mDriveServiceHelper!!.queryFiles(parent)
            ?.addOnSuccessListener { result ->

                val adapter=
                    ListAdapterLite(result, context!!, mDriveServiceHelper!!)
                mDocContentEditText.adapter=adapter

                Log.d(TAG, fileList.toString())

            }
            ?.addOnFailureListener { exception -> Log.e(TAG, "Unable to query files.", exception) }
    }
    companion object {
        val TAG = "MainActivity"

        val REQUEST_CODE_SIGN_IN = 1
        private val REQUEST_CODE_OPEN_DOCUMENT = 2
        val REQUEST_CODE_CREATE_DOCUMENT=3
    }
    }

