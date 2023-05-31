package com.dicoding.econscan.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.dicoding.econscan.R
import com.dicoding.econscan.databinding.FragmentHomeBinding
import com.dicoding.econscan.ui.home.Adapter.SampahAdapter
import com.dicoding.econscan.ui.home.Adapter.SampahItem

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Create some dummy data
        val items = listOf(
            SampahItem(R.drawable.rectangle_40, "Use public transportation", "Leave your car behind and take a \n" +
                    "greener route, walking, biking or \n" +
                    "public transport. Reduce emissions, \n" +
                    "save money, and stay fit on the go."),
            SampahItem(R.drawable.rectangle_40, "Reduce, reuse, and recycle", "Deskripsi sampah 2"),
            SampahItem(R.drawable.rectangle_40, "Use public transportation", "Deskripsi sampah 3"),
            SampahItem(R.drawable.rectangle_40, "Reduce, reuse, and recycle", "Deskripsi sampah 4"),
            // Add more items here
        )

        // Set up the RecyclerView
        binding.rvSekilasSampah.layoutManager = LinearLayoutManager(context)
        binding.rvSekilasSampah.adapter = SampahAdapter(items)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
