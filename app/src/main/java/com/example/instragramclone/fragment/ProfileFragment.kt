package com.example.instragramclone.fragment

import android.content.ContentValues.TAG
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.instragramclone.Models.User
import com.example.instragramclone.R
import com.example.instragramclone.SingUpActivity
import com.example.instragramclone.adapters.ViewPagerAdapter
import com.example.instragramclone.databinding.FragmentProfileBinding
import com.example.instragramclone.utils.USER_NODE
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.firestore
import com.google.firebase.firestore.toObject
import com.squareup.picasso.Picasso

 private lateinit var  binding :FragmentProfileBinding

class ProfileFragment : Fragment() {
private  lateinit var binding: FragmentProfileBinding
private lateinit var viewPagerAdapter: ViewPagerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding= FragmentProfileBinding.inflate(inflater, container, false)

        binding.editProfile.setOnClickListener {
            val intent = Intent(activity,SingUpActivity::class.java)
            intent.putExtra("MODE",1)
            activity?.startActivity(intent)
            activity?.finish()
        }

        viewPagerAdapter =ViewPagerAdapter(requireActivity().supportFragmentManager)
        viewPagerAdapter.addFragments(MyPostFragment(),"My Post" )
        viewPagerAdapter.addFragments(MyReelsFragment(),"My Reels" )
        binding.viewPager.adapter= viewPagerAdapter
        binding.tabLayout.setupWithViewPager(binding.viewPager)
        return binding.root
    }


    companion object {

    }




//    override fun onStart() {
//        super.onStart()
////        Firebase.firestore.collection(USER_NODE).document(Firebase.auth.currentUser!!.uid).get()
//
//        Firebase.firestore.collection(USER_NODE).document(Firebase.auth.currentUser!!.uid).get()
//            .addOnSuccessListener {
//                val user:User =it.toObject<User>()!!
//                binding.name.text=user.name
//                binding.bio.text=user.email
//                if (!user.image.isNullOrEmpty()){
//                    Picasso.get().load(user.image).into(binding.profileImage)
//                }
//
//            }
//    }

}
