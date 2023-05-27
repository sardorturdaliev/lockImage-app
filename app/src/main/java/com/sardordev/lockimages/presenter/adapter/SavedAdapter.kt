package com.sardordev.lockimages.presenter.adapter

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.sardordev.lockimages.data.model.SavedImg
import com.sardordev.lockimages.databinding.ItemImagesBinding

class SavedAdapter() : ListAdapter<SavedImg, SavedAdapter.VH>(diff) {

    inner class VH(val binding: ItemImagesBinding) : RecyclerView.ViewHolder(binding.root) {
        fun onbind(savedImg: SavedImg) {

            Glide.with(itemView.context).load(savedImg.path).into(binding.imgSetDivice)


        }
    }

    object diff : DiffUtil.ItemCallback<SavedImg>() {
        override fun areItemsTheSame(oldItem: SavedImg, newItem: SavedImg): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: SavedImg, newItem: SavedImg): Boolean {
            return oldItem == newItem
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = VH(
        ItemImagesBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    override fun onBindViewHolder(holder: VH, position: Int) {
        holder.onbind(getItem(position))
    }


    fun updateData(newData: List<SavedImg>) {
        submitList(newData)
        notifyDataSetChanged()
    }


}