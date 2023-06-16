package com.dicoding.econscan.ui.tp

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.dicoding.econscan.databinding.ActivityTipBinding
import com.dicoding.econscan.EcobrickActivity
import com.dicoding.econscan.RecycleActivity
import com.dicoding.econscan.ReduceActivity
import com.dicoding.econscan.ReuseActivity

class TipFragment  : Fragment() {
    private lateinit var binding: ActivityTipBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = ActivityTipBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.ibEco.setOnClickListener {
            val intent = Intent(requireContext(), EcobrickActivity::class.java)
            startActivity(intent)
            requireActivity().finish()
        }

        binding.ibRecycle.setOnClickListener {
            val intent = Intent(requireContext(), RecycleActivity::class.java)
            startActivity(intent)
            requireActivity().finish()
        }

        binding.ibReduse.setOnClickListener {
            val intent = Intent(requireContext(), ReduceActivity::class.java)
            startActivity(intent)
            requireActivity().finish()
        }

        binding.ibReuse.setOnClickListener {
            val intent = Intent(requireContext(), ReuseActivity::class.java)
            startActivity(intent)
            requireActivity().finish()
        }
    }
}

