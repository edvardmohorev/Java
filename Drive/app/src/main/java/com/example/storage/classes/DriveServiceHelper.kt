/**
 * Copyright 2018 Google LLC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.storage.classes

import android.app.Notification
import android.app.NotificationManager
import android.content.Context
import android.content.Intent
import android.os.Environment
import android.os.Environment.DIRECTORY_DOWNLOADS
import android.util.Log
import android.util.Pair
import androidx.core.app.NotificationManagerCompat
import com.example.storage.fragment.BlankFragment

import com.google.android.gms.tasks.Task
import com.google.android.gms.tasks.Tasks
import com.google.api.client.http.ByteArrayContent
import com.google.api.client.http.FileContent
import com.google.api.services.drive.Drive
import com.google.api.services.drive.model.File
import com.google.api.services.drive.model.FileList
import java.util.concurrent.Callable
import java.util.concurrent.Executors
import java.io.*
import javax.xml.datatype.DatatypeConstants.SECONDS
import android.R
import androidx.core.app.NotificationCompat
import java.util.concurrent.TimeUnit
import android.content.Context.NOTIFICATION_SERVICE
import androidx.core.content.ContextCompat.getSystemService
import android.content.Context.NOTIFICATION_SERVICE
import androidx.core.content.ContextCompat.getSystemService






/**
 * A utility for performing read/write operations on Drive files via the REST API and opening a
 * file picker UI via Storage Access Framework.
 */
class DriveServiceHelper(private val mDriveService: Drive) {
    private val mExecutor = Executors.newSingleThreadExecutor()
    /**
     * Creates a text file in the user's My Drive folder and returns its file ID.
     */
    fun createFile(content: String,nameFile:String,parent: String?=null): Task<File>? {
        return Tasks.call(mExecutor,Callable {
            var parents="root"
            if(parent!=null){
                parents=parent
            }
            val metadata = File()
                .setParents(listOf("$parents"))
                .setMimeType("application/vnd.google-apps.document")
                .setName(nameFile)
            val content:ByteArrayContent= ByteArrayContent.fromString("text/plain",content)

            val googleFile = mDriveService.files().create(metadata,content).setFields("id").execute()


            googleFile
        })
    }
    fun getRoot():Task<Any?>{
        var root:FileList
        return Tasks.call(mExecutor, Callable {
            root= mDriveService.files().list().setQ("'root' in parents").setFields("files(parents)").setSpaces("drive").execute()
            val fileRoot=root.files
            for (file in fileRoot){
                val getRoot=file.parents
                return@Callable getRoot
            }
        })

    }
    fun renameFileAndFolder(id:String,name:String):Task<String>{
        return Tasks.call(mExecutor, Callable {
            val metadata=File()
                .setName(name)
            val rename=mDriveService.files().update(id,metadata).execute()
            rename.id
        })
    }
    fun createFolder(nameFolder:String,parent:String?=null): Task<String> {
        return Tasks.call(mExecutor,Callable {
            var parents="root"
            if(parent!=null){
                parents=parent
            }
            val metadata = File()
                .setParents(listOf("$parents"))
                .setMimeType("application/vnd.google-apps.folder")
                .setName(nameFolder)

            val googleFile = mDriveService.files().create(metadata).execute()
                ?: throw IOException("Null result when requesting file creation.")

            googleFile.id
        })
    }



    fun deleteFile(id:String):Task<Void>{
        return Tasks.call(mExecutor, Callable {
           val deleteFile= mDriveService.files().delete(id).execute()
            deleteFile
        }
        )

    }
    /**
     * Opens the file identified by `fileId` and returns a [Pair] of its name and
     * contents.
     */
    fun readFile(fileId: String): Task<Pair<String, String>> {
        return Tasks.call(mExecutor, Callable{
            // Retrieve the metadata as a File object.
            val metadata = mDriveService.files().get(fileId).execute()
            val name = metadata.name

            // Stream the file contents to a String.
            mDriveService.files().get(fileId).executeMediaAsInputStream().use { `is` ->
                BufferedReader(InputStreamReader(`is`)).use { reader ->
                    val stringBuilder = StringBuilder()
                    var line: String=""
                    stringBuilder.append(line)
                    val contents = stringBuilder.toString()

                    Pair.create<String,String>(name,contents)
                }
            }
        })
    }

    /**
     * Updates the file identified by `fileId` with the given `name` and `content`.
     */
    fun saveFile(fileId: String, name: String, content: String): Task<Void> {
        return Tasks.call(mExecutor, Callable<Void>{
            // Create a File containing any metadata changes.
            val metadata = File().setName(name)

            // Convert content to an AbstractInputStreamContent instance.
            val contentStream = ByteArrayContent.fromString("text/plain", content)

            // Update the metadata and contents.
            mDriveService.files().update(fileId, metadata, contentStream).execute()
            null
        })
    }

    fun replace(id:String,oldParent: String?,newParent:String):Task<File>{
        return Tasks.call(mExecutor, Callable {
            val replace=mDriveService.files().update(id,null)
                .setAddParents("0AOAo0e8nSFhJUk9PVA")
                .setRemoveParents("1s3DECmTY4dqjIbpzw49KZsGc8tq_0XkA")
                .execute()
            replace
        })
    }
    /**
     * Returns a [FileList] containing all the visible files in the user's My Drive.
     *
     *
     * The returned list will only contain files visible to this app, i.e. those which were
     * created by this app. To perform operations on files not created by the app, the project must
     * request Drive Full Scope in the [Google
 * Developer's Console](https://play.google.com/apps/publish) and be submitted to Google for verification.
     */
    fun queryFiles(folder:String?=null): Task<ArrayList<Files>>? {
        val fileList = arrayListOf<Files>()
        var parent="root"
        if (folder !=null){
           parent=folder
        }
        return Tasks.call(
            mExecutor,
            Callable{
                val files=mDriveService.files().list().setQ("'$parent' in parents ").setFields("files(id, name,webViewLink,mimeType,parents,webContentLink)").setSpaces("drive").execute()
                val result=files.files
                for (file in result){
                 fileList.add(
                     Files(
                         file.name,
                         file.id,
                         file.webViewLink,
                         file.parents,
                         file.mimeType
                     )
                 )
                    Log.d(BlankFragment.TAG, fileList.toString())
                }
                fileList
            })

    }


    fun searchFile(fileName: String): Task<ArrayList<Files>>? {
        val fileList = arrayListOf<Files>()
        return Tasks.call(
            mExecutor,
            Callable{
                val files=mDriveService.files().list().setQ("name = '$fileName'").setFields("files(id, name,webViewLink,mimeType,parents,webContentLink)").setSpaces("drive").execute()
                val result=files.files
                for (file in result){
                    fileList.add(
                        Files(
                            file.name,
                            file.id,
                            file.webViewLink,
                            file.parents,
                            file.mimeType
                        )
                    )
//                    Log.d(MainActivity.TAG, file.parents.toString())
                }
                fileList
            })
    }

    /**
     * Returns an [Intent] for opening the Storage Access Framework file picker.
     */
    fun fileIntent(): Intent {
        val intent = Intent(Intent.ACTION_OPEN_DOCUMENT)
        intent.addCategory(Intent.CATEGORY_OPENABLE)
        intent.type = "*/*"

        return intent
    }
    fun addFile(uri:java.io.File,flag:Int,parent: String?=null):Task<File>{
        var parents="root"
        var fileContent: FileContent? =null
        var type=""
        if (parent !=null){
            parents=parent
        }
        when (flag){
            0->{
                type= "application/pdf"
                fileContent = FileContent("text/csv",uri)
            }
            1->{type="image/jpeg"
                fileContent = FileContent(type,uri)}
        }
        Log.d(BlankFragment.TAG, uri.parent)
        return Tasks.call(mExecutor, Callable {
            val metadata = File()
                .setParents(listOf(parents))
                .setMimeType(type)
                .setName(uri.name)


            val upload=mDriveService.files().create(metadata,fileContent).execute()
            upload
        })
    }
    fun download(id: String, name: String, type: String,context:Context): Task<Nothing?>? {
        return Tasks.call(mExecutor, Callable {
            val sdPath = Environment.getExternalStoragePublicDirectory(DIRECTORY_DOWNLOADS)
            val file = File(sdPath.path + "/here/$name")
            val outputStream = ByteArrayOutputStream()
            if (type=="application/vnd.google-apps.document")
                mDriveService.files().export(id,"application/msword").executeMediaAndDownloadTo(outputStream)
            else
            mDriveService.files().get(id).executeMediaAndDownloadTo(outputStream)
            Log.d(BlankFragment.TAG,outputStream.size().toString())
            val content=outputStream.toByteArray()
            val fout=FileOutputStream(file)
            fout.write(content)
            fout.close()
            null
        })
    }
    /**
     * Opens the file at the `uri` returned by a Storage Access Framework [Intent]
     * created by [.createFilePickerIntent] using the given `contentResolver`.
     */
//    fun openFileUsingStorageAccessFramework(
//        contentResolver: ContentResolver, uri: Uri
//    ): Task<Pair<String, String>> {
//        return Tasks.call(mExecutor, Callable{
//            // Retrieve the document's display name from its metadata.
//            var name: String
//            contentResolver.query(uri, null, null, null, null)!!.use { cursor ->
//                if (cursor != null && cursor.moveToFirst()) {
//                    val nameIndex = cursor.getColumnIndex(OpenableColumns.DISPLAY_NAME)
//                    name = cursor.getString(nameIndex)
//                } else {
//                    throw IOException("Empty cursor returned for file.")
//                }
//            }
//
//            // Read the document's contents as a String.
//            var content: String
//            contentResolver.openInputStream(uri)!!.use { `is` ->
//                BufferedReader(InputStreamReader(`is`)).use { reader ->
//                    val stringBuilder = StringBuilder()
//                    var line: String
//                    while ((line = reader.readLine()) != null) {
//                        stringBuilder.append(line)
//                    }
//                    content = stringBuilder.toString()
//                }
//            }
//
//            Pair.create(name, content)
//        })
//    }
}
