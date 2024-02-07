package com.example.instragramclone

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Html
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.text.set
import com.example.instragramclone.Models.User
import com.example.instragramclone.databinding.ActivitySingUpBinding
import com.example.instragramclone.utils.USER_NODE
import com.example.instragramclone.utils.USER_PROFIL_FOLDER
import com.example.instragramclone.utils.uploadImage
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import com.google.firebase.firestore.firestore
import com.google.firebase.firestore.toObject
import com.squareup.picasso.Picasso

class SingUpActivity : AppCompatActivity() {

    val binding by lazy {
        ActivitySingUpBinding.inflate(layoutInflater)
    }

    lateinit var user: User

    private val launcher = registerForActivityResult(ActivityResultContracts.GetContent()) { uri ->
        uri?.let {
            uploadImage(uri, USER_PROFIL_FOLDER) {
                if (it == null) {

                } else {
                    user.image = it
                    binding.profileImage.setImageURI(uri)
                }
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        fun View.hideKeyboard() {

            val inputManager =
                context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            inputManager.hideSoftInputFromWindow(windowToken, 0)
        }

        binding.toch.setOnClickListener {
            it.hideKeyboard()
        }
        val text =
            "<font color=#FF000000>Already have an Account   </font> <font color=#1E88E5 > Login ? </font>"
        binding.login.setText(Html.fromHtml(text))
        user = User()

        if (intent.hasExtra("MODE")) {
            if (intent.getIntExtra("MODE", -1) == 1) {
                binding.signUpBtn.text = "Update Profile"


                Firebase.firestore.collection(USER_NODE).document(Firebase.auth.currentUser!!.uid)
                    .get()

                    .addOnSuccessListener {
                        user = it.toObject<User>()!!
                        if (!user.image.isNullOrEmpty()) {
                            Picasso.get().load(user.image)
                                .into(binding.profileImage)
                        
                        }
                        else {

                        }
                        

                    }
            }
        }

        binding.signUpBtn.setOnClickListener {
       //     binding.signUpBtn.setEnabled(false);

            if (intent.hasExtra("MODE")) {
                if (intent.getIntExtra("MODE", -1) == 1) {
                    Firebase.firestore.collection(USER_NODE)
                        .document(Firebase.auth.currentUser!!.uid).set(user)
                        .addOnSuccessListener {
                            startActivity(
                                Intent(
                                    this@SingUpActivity, HomeActivity::class.java
                                )
                            )
                            finish()
                        }
                }

            } else {

                if (binding.name.editableText?.toString()
                        .equals("") or binding.email.editText?.text.toString()
                        .equals("") or binding.password.editText?.text.toString().equals("")
                ) {
                    Toast.makeText(
                        this@SingUpActivity, "plese fill all information", Toast.LENGTH_SHORT
                    ).show()
                } else {

                    FirebaseAuth.getInstance().createUserWithEmailAndPassword(
                        binding.email.editText?.text.toString(),
                        binding.password.editText?.text.toString()
                    ).addOnCompleteListener { result ->
                        if (result.isSuccessful) {
                            user.name = binding.name.editableText?.toString()
                            user.email = binding.email.editText?.text.toString()
                            user.password = binding.password.editText?.text.toString()
                            Firebase.firestore.collection("USER_NODE")
                                .document(Firebase.auth.currentUser!!.uid).set(user)
                                .addOnSuccessListener {
                                    startActivity(
                                        Intent(
                                            this@SingUpActivity, HomeActivity::class.java
                                        )
                                    )
                                    finish()
                                }


                        } else {

                            Toast.makeText(
                                this@SingUpActivity,
                                result.exception?.localizedMessage,
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }

                }
            }
        }

        binding.addimage.setOnClickListener {
            launcher.launch("image/*")
        }
        binding.login.setOnClickListener {
            startActivity(Intent(this@SingUpActivity, LoginActivity::class.java))
            finish()
        }
    }
}
