package com.example.instragramclone.Post

import android.app.ProgressDialog
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.example.instragramclone.HomeActivity
import com.example.instragramclone.Models.Reel
import com.example.instragramclone.Models.User
import com.example.instragramclone.databinding.ActivityReelsBinding
import com.example.instragramclone.utils.REEL
import com.example.instragramclone.utils.REEL_FOLDER
import com.example.instragramclone.utils.USER_NODE
import com.example.instragramclone.utils.uploadVideo
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import com.google.firebase.firestore.firestore
import com.google.firebase.firestore.toObject

class ReelsActivity : AppCompatActivity() {

    val binding by lazy {
        ActivityReelsBinding.inflate(layoutInflater)
    }

    private lateinit var videoUrl: String
    lateinit var progressDialog :ProgressDialog
    private val launcher = registerForActivityResult(ActivityResultContracts.GetContent()) { uri ->
        uri?.let {
            uploadVideo(uri, REEL_FOLDER,progressDialog ) { uri ->
                if (uri != null) {
                    videoUrl = uri
                }
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
         progressDialog = ProgressDialog(this)

        binding.selectReels.setOnClickListener {
            launcher.launch("video/*")
//            binding.progressBar.visibility = View.VISIBLE
        }

        binding.cancelBTN.setOnClickListener {
            startActivity(Intent(this@ReelsActivity, HomeActivity::class.java))
            finish()
        }




        binding.postBTN.setOnClickListener {
            Firebase.firestore.collection(USER_NODE).document(Firebase.auth.currentUser!!.uid).get().addOnSuccessListener {
                var user:User= it.toObject<User>()!!
            }
            val reel: Reel = Reel(videoUrl!!, binding.caption.text.toString())

            Firebase.firestore.collection(REEL).document().set(reel).addOnSuccessListener {
                Firebase.firestore.collection(Firebase.auth.currentUser!!.uid + REEL).document().set(reel)
                    .addOnSuccessListener {
                        startActivity(Intent(this@ReelsActivity, HomeActivity::class.java))
                        finish()

                    }

            }
        }
    }
}

