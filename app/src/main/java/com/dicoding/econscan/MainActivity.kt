package com.dicoding.econscan

import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowInsets
import android.view.WindowManager
import androidx.navigation.Navigation.findNavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.dicoding.econscan.databinding.ActivityMainBinding
import com.dicoding.econscan.ui.scanner.MediaUploadFragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)



        @Suppress("DEPRECATION")
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            window.insetsController?.hide(WindowInsets.Type.statusBars())
        } else {
            window.setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN
            )
        }
        supportActionBar?.hide()

        val navView: BottomNavigationView = binding.navView

        val navController = findNavController(R.id.fragment_activity_main)


        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_home, R.id.navigation_camera, R.id.navigation_tip
            )
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)

        val fileUri = intent.getParcelableExtra<Uri>("selected_image")
        fileUri?.let { uri ->
            val isBackCamera = intent.getBooleanExtra("isBackCamera", false)
            val bundle = Bundle()
            bundle.putBoolean("isBackCamera", isBackCamera)
            bundle.putParcelable("selected_image", uri)

            val mediaUploadFragment = MediaUploadFragment()
            mediaUploadFragment.arguments = bundle

            supportFragmentManager.beginTransaction()
                .replace(R.id.container, mediaUploadFragment)
                .commit()
        }
    }
    }


