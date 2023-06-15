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
            SampahItem(R.drawable.rectangle_40, "Peraturan Tentang Sampah", "Pengelolaan sampah di Indonesia diatur dalam Undang-Undang Nomor 18 Tahun 2008 tentang Pengelolaan Sampah."),
            SampahItem(R.drawable.rectangle_40, "Tempat Penampungan Sementara (TPS)", "TPS yaitu tempat sebelum sampah diangkut ke tempat pendauran ulang, pengolahan, dan/atau tempat pengolahan sampah terpadu. Dari TPS, sampah akan diangkut dan dibawa oleh Dinas Lingkungan menggunakan truk sampah ke Tempat Pemrosesan Akhir (TPA). TPA adalah tempat untuk memproses dan mengembalikan sampah ke media lingkungan secara aman bagi manusia dan lingkungan."),
            SampahItem(R.drawable.rectangle_40, "Pengelolaan Sampah", "Pengelolaan sampah di Indonesia dibagi menjadi dua, pertama yaitu pengelolaan sampah rumah tangga dan sampah sejenis sampah rumah tangga dan kedua yaitu pengelolaan sampah spesifik"),
            SampahItem(R.drawable.rectangle_40, "Peran Masyarakat", "Peran masyarakat antara lain pemberian usul, pertimbangan, dan saran kepada pemerintah pusat dan pemerintah daerah, perumusan kebijakan pengelolaan sampah, dan/atau pemberian saran dan pendapat dalam penyelesaian sengketa persampahan."),
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
