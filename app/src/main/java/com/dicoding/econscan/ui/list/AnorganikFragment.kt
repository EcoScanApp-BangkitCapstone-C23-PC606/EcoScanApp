package com.dicoding.econscan.ui.list
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.dicoding.econscan.databinding.FragmentAnorganikBinding
import androidx.core.content.ContentProviderCompat.requireContext
import com.dicoding.econscan.*

class AnorganikFragment : AppCompatActivity() {
    private lateinit var binding: FragmentAnorganikBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = FragmentAnorganikBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        setupActionBar()
        binding.ibEco.setOnClickListener {
            val intent = Intent(this, EcobrickActivity::class.java)
            startActivity(intent)
            finish()
        }

        binding.ibRecycle.setOnClickListener {
            val intent = Intent(this, RecycleActivity::class.java)
            startActivity(intent)
            finish()
        }

        binding.ibReduse.setOnClickListener {
            val intent = Intent(this, ReduceActivity::class.java)
            startActivity(intent)
            finish()
        }

        binding.ibReuse.setOnClickListener {
            val intent = Intent(this, ReuseActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    private fun setupActionBar() {
        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            title = "EcoScan"
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                onBackPressed()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }
    override fun onBackPressed() {
        val intent = Intent(this, MainActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK)
        startActivity(intent)
    }
}

