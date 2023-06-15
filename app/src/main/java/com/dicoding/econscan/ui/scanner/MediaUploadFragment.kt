package com.dicoding.econscan.ui.scanner

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.ImageDecoder
import android.graphics.Matrix
import android.media.ExifInterface
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.core.content.ContextCompat
import androidx.core.net.toFile
import androidx.core.view.isInvisible
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.dicoding.econscan.MainActivity
import com.dicoding.econscan.data.response.PostSampahResponse
import com.dicoding.econscan.data.util.Result
import com.dicoding.econscan.databinding.FragmentMediauploadBinding
import com.dicoding.econscan.ui.home.HomeFragment
import com.dicoding.econscan.ui.list.AnorganikFragment
import com.dicoding.econscan.utils.ViewModelFactory
import com.dicoding.econscan.utils.rotateBitmap
import com.dicoding.econscan.utils.uriToFile
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import java.io.File
import java.io.FileOutputStream

class MediaUploadFragment : Fragment() {
    private lateinit var binding: FragmentMediauploadBinding
    private lateinit var viewModel: UploadViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentMediauploadBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(this, ViewModelFactory(requireActivity().application)).get(UploadViewModel::class.java)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btOpenCamera.setOnClickListener {
            startActivity(Intent(requireContext(), CameraActivity::class.java))
        }
        binding.galleryButton.setOnClickListener {
            startGallery()
        }
        binding.submitButton.setOnClickListener {
            uploadImage()
        }
        binding.backButton.setOnClickListener {
            backList()
        }

        val fileUri = requireActivity().intent.getParcelableExtra<Uri>("selected_image")
        fileUri?.let { uri ->
            val isBackCamera = requireActivity().intent.getBooleanExtra("isBackCamera", false)
            val result = rotateBitmap(
                BitmapFactory.decodeFile(uri.path),
                isBackCamera
            )
            getFile = uri.toFile()
            binding.uploadImage.setImageBitmap(result)
        }


    }

    private var getFile: File? = null
    private fun startGallery() {
        val intent = Intent()
        intent.action = Intent.ACTION_GET_CONTENT
        intent.type = "image/*"
        val chooser = Intent.createChooser(intent, "Choose a Picture")
        launcherIntentGallery.launch(chooser)
    }

    private val launcherIntentGallery = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            val selectedImg: Uri = result.data?.data as Uri
            val myFile = uriToFile(selectedImg, requireContext())
            getFile = myFile
            binding.uploadImage.setImageURI(selectedImg)
        }
    }

    private fun uploadImage() {
        getFile?.let { file ->

            showLoading(true)
            val rotation = getRotationFromImage(file.path)
            val bitmap = BitmapFactory.decodeFile(file.path)
            val rotatedBitmap = bitmap.rotateBitmap(rotation)
            val compressedFile = reduceFileImage(rotatedBitmap)

            val requestImageFile = compressedFile.asRequestBody("image/jpeg".toMediaTypeOrNull())
            val imageMultipart: MultipartBody.Part = MultipartBody.Part.createFormData(
                "image",
                compressedFile.name,
                requestImageFile
            )
            viewModel.postSampah(imageMultipart).observe(viewLifecycleOwner) {
                if (it != null) {
                    when (it) {
                        is Result.Success -> {
                            showLoading(false)
                            Toast.makeText(requireContext(), "Upload success", Toast.LENGTH_SHORT).show()
                            processCreate(it.data)
                        }
                        is Result.Error -> {
                            showLoading(false)
                            Toast.makeText(requireContext(), it.error, Toast.LENGTH_LONG).show()
                        }
                        is Result.Loading -> {
                            showLoading(true)
                        }
                    }
                } }
        } ?: run {
            Toast.makeText(requireContext(), "Silakan masukkan berkas gambar terlebih dahulu.", Toast.LENGTH_SHORT).show()
        }
    }

    private fun processCreate(data: PostSampahResponse) {
        if (data.error) {
            Toast.makeText(requireContext(), "Sampah Predict Gagal ", Toast.LENGTH_LONG).show()
        } else {
            Toast.makeText(
                requireContext(),
                "Sampah bisa ",
                Toast.LENGTH_LONG
            ).show()
            val intent = Intent(requireContext(), AnorganikFragment::class.java)
            startActivity(intent)
            requireActivity().finish()
        }
    }

    private fun showLoading(state: Boolean) {
        binding.pbCreateStory.isVisible = state
        binding.uploadImage.isInvisible = state
        binding.galleryButton.isInvisible = state
        binding.backButton.isInvisible = state
        binding.btOpenCamera.isInvisible = state
        binding.submitButton.isInvisible = state
    }

    private fun getRotationFromImage(imagePath: String): Int {
        val exif = ExifInterface(imagePath)
        val rotation = exif.getAttributeInt(ExifInterface.TAG_ORIENTATION, ExifInterface.ORIENTATION_NORMAL)
        return when (rotation) {
            ExifInterface.ORIENTATION_ROTATE_90 -> 90
            ExifInterface.ORIENTATION_ROTATE_180 -> 180
            ExifInterface.ORIENTATION_ROTATE_270 -> 270
            else -> 0
        }
    }

    private fun Bitmap.rotateBitmap(degrees: Int): Bitmap {
        if (degrees == 0) {
            return this
        }

        val matrix = Matrix()
        matrix.setRotate(degrees.toFloat())
        return Bitmap.createBitmap(this, 0, 0, width, height, matrix, true)
    }

    private fun reduceFileImage(bitmap: Bitmap): File {
        val file = File.createTempFile("compressed", ".jpg", requireContext().cacheDir)
        val out = FileOutputStream(file)
        bitmap.compress(Bitmap.CompressFormat.JPEG, 85, out)
        out.flush()
        out.close()
        return file
    }

    private fun backList () {
        startActivity(Intent(requireContext(), HomeFragment::class.java))
    }

    override fun onDestroyView() {
        super.onDestroyView()
        getFile = null
//        _binding = null
    }
}



