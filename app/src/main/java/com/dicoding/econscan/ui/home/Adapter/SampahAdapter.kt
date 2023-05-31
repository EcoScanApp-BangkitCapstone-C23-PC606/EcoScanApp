package com.dicoding.econscan.ui.home.Adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.dicoding.econscan.databinding.CustomItemViewBinding


class SampahAdapter(private val items: List<SampahItem>) : RecyclerView.Adapter<SampahAdapter.ViewHolder>() {

    inner class ViewHolder(val binding: CustomItemViewBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = CustomItemViewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]
        with(holder.binding) {
            ivItemPhoto.setImageResource(item.imageResId)
            tvItemName.text = item.name
            tvStoryDesc.text = item.description
        }
    }

    override fun getItemCount() = items.size
}
