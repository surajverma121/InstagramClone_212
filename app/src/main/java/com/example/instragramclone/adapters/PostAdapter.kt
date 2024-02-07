package com.example.instragramclone.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.instragramclone.Models.Post
import com.example.instragramclone.Models.User
import com.example.instragramclone.R
import com.example.instragramclone.databinding.PostRvBinding
import com.example.instragramclone.utils.USER_NODE
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore
import com.google.firebase.firestore.toObject

class PostAdapter(var context: android.content.Context, val postList: ArrayList<Post>):RecyclerView.Adapter<PostAdapter.MyHolder>() {

 inner  class  MyHolder( var binding:PostRvBinding):RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyHolder {
         var binding = PostRvBinding.inflate(LayoutInflater.from(parent.context),parent,false)
      return  MyHolder(binding)
    }

    override fun getItemCount(): Int {
 return postList.size

    }


    override fun onBindViewHolder(holder: MyHolder, position: Int) {
        Firebase.firestore.collection(USER_NODE).document(postList.get(position).Uid).get().addOnSuccessListener { it ->
            var user = it.toObject<User>()
            Glide.with(holder.binding.profileImage).load(user!!.image).placeholder(R.drawable.user).into(holder.binding.profileImage)
            holder.binding.name.text=user.name
        }
      Glide.with(context).load(postList.get(position).postUrl).placeholder(R.drawable.loading).into(holder.binding.postImage)
         holder.binding.time.text =postList.get(position).time
     holder.binding.cpstion.text=postList.get(position  ).caption
    }

}