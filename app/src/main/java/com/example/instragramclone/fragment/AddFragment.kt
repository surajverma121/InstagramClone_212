package com.example.instragramclone.fragment

import android.content.Intent
import android.os.Binder
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.instragramclone.Post.PostActivity
import com.example.instragramclone.Post.ReelsActivity
import com.example.instragramclone.R
import com.example.instragramclone.databinding.FragmentAddBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment


class AddFragment : BottomSheetDialogFragment() {

   private  lateinit var  binding: FragmentAddBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding=FragmentAddBinding.inflate(inflater, container, false)

        binding.post.setOnClickListener{
  activity?.startActivity(Intent(requireActivity(),PostActivity::class.java))
  activity?.finish()
        }
        binding.reel.setOnClickListener{
            activity?.startActivity(Intent(requireActivity(),ReelsActivity::class.java))

        }

  return  binding.root
    }

    companion object {


    }
}
