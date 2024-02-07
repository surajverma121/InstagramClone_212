package com.example.instragramclone.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.instragramclone.R
import com.example.instragramclone.databinding.FragmentSearchBinding


class SearchFragment : Fragment() {
lateinit var  binding :FragmentSearchBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentSearchBinding.inflate(inflater, container, false)

        binding.searchView.setOnSearchClickListener {

        }


    return binding.root
    }

    companion object {

            }
    }
