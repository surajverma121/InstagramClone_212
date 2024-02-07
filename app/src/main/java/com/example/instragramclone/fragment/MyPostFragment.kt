package com.example.instragramclone.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.instragramclone.Models.Post
import com.example.instragramclone.adapters.MyPostRvAdapter
import com.example.instragramclone.databinding.FragmentMyPostBinding
import com.example.instragramclone.databinding.MyPostRvDesignBinding
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.toObject
import com.google.firebase.ktx.Firebase


class MyPostFragment : Fragment() {
private  lateinit var binding: FragmentMyPostBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentMyPostBinding.inflate(inflater, container, false)
      var  postList = ArrayList<Post>()
        var adapter  = MyPostRvAdapter(requireContext(),postList)
        binding.gv.layoutManager =  StaggeredGridLayoutManager(3,StaggeredGridLayoutManager.VERTICAL)
        binding.gv.adapter = adapter
        Firebase.firestore.collection(Firebase.auth.currentUser!!.uid).get().addOnSuccessListener {
            var tempList = arrayListOf<Post>()

   for ( i in it.documents){
       var post :Post = i.toObject<Post>()!!
       tempList.add(post)

   }

            postList.addAll(tempList)
            adapter.notifyDataSetChanged()
        }

      return binding.root
    }

    companion object {

            }

}
//holder.binding.postImage
