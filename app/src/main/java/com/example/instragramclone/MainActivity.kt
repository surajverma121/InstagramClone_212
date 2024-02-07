package com.example.instragramclone

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.provider.Telephony.Sms.Intents
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth


class MainActivity : AppCompatActivity() {
    @SuppressLint("SuspiciousIndentation")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        window.statusBarColor= Color.TRANSPARENT

Handler(Looper.getMainLooper()).postDelayed({
  if (FirebaseAuth.getInstance().currentUser== null)
      startActivity(Intent(this,SingUpActivity::class.java))
    else
        startActivity(Intent(this,HomeActivity::class.java))
    finish()
},2500)

    }
}