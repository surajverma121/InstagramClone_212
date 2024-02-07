package com.example.instragramclone.utils

import android.app.ProgressDialog
import android.content.Context
import android.net.Uri
import com.google.firebase.storage.FirebaseStorage
import java.util.UUID


fun uploadImage (uri: Uri, folderName: String, callback: (String?) -> Unit) {
    var imageUrl: String? = null
    FirebaseStorage.getInstance().getReference(folderName).child(UUID.randomUUID().toString())
        .putFile(uri)
        .addOnSuccessListener {
            it.storage.downloadUrl.addOnSuccessListener {
                imageUrl = it.toString()

                callback(imageUrl)
            }
        }
}


fun uploadVideo (uri: Uri, folderName: String,progressDiaLog:ProgressDialog, callback: (String?) -> Unit){
    var imageUrl :String ?= null
    progressDiaLog.setTitle("Uploading ...")
    progressDiaLog.show()
    FirebaseStorage.getInstance().getReference(folderName).child(UUID.randomUUID().toString()).putFile(uri)
        .addOnSuccessListener {
            it.storage.downloadUrl.addOnSuccessListener {
                imageUrl =it.toString()
                progressDiaLog.dismiss()
                callback(imageUrl)
            }
        }
        .addOnSuccessListener {
            val uploadValue :Long = (it.bytesTransferred/it.totalByteCount)*100
            progressDiaLog.setMessage("Uploaded $uploadValue %")
        }
}