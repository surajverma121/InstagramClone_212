package com.example.instragramclone.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.instragramclone.Models.Reel
import com.example.instragramclone.databinding.ReelDgBinding
import com.squareup.picasso.Picasso

class ReelAdapter(var context: Context, var reelList: ArrayList<Reel>) :
    RecyclerView.Adapter<ReelAdapter.ViewHolder>() {

    inner class ViewHolder(var biniding: ReelDgBinding) : RecyclerView.ViewHolder(biniding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var binding = ReelDgBinding.inflate(LayoutInflater.from(context), parent, false)
        return ViewHolder(binding)


    }

    override fun getItemCount(): Int {
        return reelList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        Picasso.get().load(reelList.get(position).profileLink).into(holder.biniding.profileImage)
        holder.biniding.caption.setText(reelList.get(position).caption)
        holder.biniding.videoView.setVideoPath(reelList.get(position).ReelUrl)

        holder.biniding.videoView.setOnPreparedListener {


            holder.biniding.videoView.start()
        }
    }
}