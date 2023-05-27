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
import com.sardordev.lockimages.data.model.ImageData
import com.sardordev.lockimages.databinding.ItemImagesBinding

class ImageAdapter(private val context: Context, val listener: ItemImageListener) :
    ListAdapter<ImageData, ImageAdapter.VH>(diff) {
    private var currentPos = -1

    inner class VH(val binding: ItemImagesBinding) : RecyclerView.ViewHolder(binding.root) {
        fun onbind(imageModel: ImageData) {


            Glide.with(context).load(imageModel.path).into(binding.imgSetDivice)

            binding.imgChech.isVisible = imageModel.isRemember



            itemView.setOnClickListener {
                if (imageModel.isRemember) {//false
                    listener.itemClick(imageModel.id, false, imageModel, adapterPosition)
                } else {
                    listener.itemClick(imageModel.id, true, imageModel, adapterPosition)
                }
            }





        }
    }

    object diff : DiffUtil.ItemCallback<ImageData>() {
        override fun areItemsTheSame(oldItem: ImageData, newItem: ImageData): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: ImageData, newItem: ImageData): Boolean {
            return oldItem == newItem
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = VH(
        ItemImagesBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    override fun onBindViewHolder(holder: VH, position: Int) {
        holder.onbind(getItem(position))
    }


    interface ItemImageListener {
        fun itemClick(id: Int, remember: Boolean, imageModel: ImageData, pos: Int)
    }


    fun updateData(newData: List<ImageData>) {
        submitList(newData)
        notifyDataSetChanged()
    }


}