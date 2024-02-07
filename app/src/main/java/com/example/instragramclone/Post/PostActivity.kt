package com.example.instragramclone.Post


import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.example.instragramclone.HomeActivity
import com.example.instragramclone.Models.Post
import com.example.instragramclone.Models.User
import com.example.instragramclone.databinding.ActivityPostBinding
import com.example.instragramclone.utils.POST
import com.example.instragramclone.utils.POST_FOLDER
import com.example.instragramclone.utils.USER_NODE
import com.example.instragramclone.utils.uploadImage
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import com.google.firebase.firestore.firestore
import com.google.firebase.firestore.toObject

class PostActivity : AppCompatActivity() {
    val binding by lazy {
        ActivityPostBinding.inflate(layoutInflater)
    }
    var imageUrl :String ?= null

    private val launcher = registerForActivityResult(ActivityResultContracts.GetContent()) { uri ->
        uri?.let {
            uploadImage(uri, POST_FOLDER) {
                    uri->
                if ( uri != null) {
                    binding.imageView1.setImageURI(Uri.parse(uri))
                    imageUrl = uri
                    binding.progressBar.visibility= View.GONE
                }
            }
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setSupportActionBar(binding.materialToolbar)
        getSupportActionBar()?.setDisplayHomeAsUpEnabled(true)
        getSupportActionBar()?.setDisplayShowHomeEnabled(true)
        binding.materialToolbar.setNavigationOnClickListener {
            startActivity(Intent(this@PostActivity,HomeActivity::class.java))

            finish()
        }
           binding.imageView1.setOnClickListener{
               launcher.launch("image/*")
               binding.progressBar.visibility= View.VISIBLE
           }

        binding.cancelBTN.setOnClickListener {
            startActivity(Intent(this@PostActivity,HomeActivity::class.java))
        finish()
        }

        binding.postBTN.setOnClickListener {
            Firebase.firestore.collection(USER_NODE).document(Firebase.auth.currentUser!!.uid).get()
                .addOnSuccessListener {
                  //  val post: Post = Post(imageUrl!!, caption = binding.caption.text.toString())

                    var user = it.toObject<User>()!!

                    var post: Post = Post(
                        postUrl = imageUrl!!,
                        caption = binding.caption.text.toString(),
                        Uid = Firebase.auth.currentUser!!.uid,
                        time = System.currentTimeMillis().toString()
                    )


                    Firebase.firestore.collection(POST).document().set(post).addOnSuccessListener {
                        Firebase.firestore.collection(Firebase.auth.currentUser!!.uid).document()
                            .set(post).addOnSuccessListener {
                            startActivity(Intent(this@PostActivity, HomeActivity::class.java))
                            finish()

                        }

                    }
                }
        }

    }
}


