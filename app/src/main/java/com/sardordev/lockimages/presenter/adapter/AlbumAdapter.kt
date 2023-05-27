package com.sardordev.lockimages.presenter.adapter

import android.media.browse.MediaBrowser.ItemCallback
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.AbsListView.RecyclerListener
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.sardordev.lockimages.data.model.AlbumData
import com.sardordev.lockimages.databinding.ItemAlbumBinding

class AlbumAdapter(private val listener: AlbumClickListener) :
    ListAdapter<AlbumData, AlbumAdapter.VH>(diff) {
    inner class VH(val binding: ItemAlbumBinding) : RecyclerView.ViewHolder(binding.root) {
        fun onbind(albumData: AlbumData) {
            binding.tvAlbumName.text = albumData.nameAlbum

            itemView.setOnClickListener {
                listener.onclick(albumData)
            }
        }
    }

    object diff : DiffUtil.ItemCallback<AlbumData>() {
        override fun areItemsTheSame(oldItem: AlbumData, newItem: AlbumData): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: AlbumData, newItem: AlbumData): Boolean {
            return oldItem == newItem
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = VH(
        ItemAlbumBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
    )

    override fun onBindViewHolder(holder: VH, position: Int) {
        holder.onbind(getItem(position))
    }

    interface AlbumClickListener {
        fun onclick(albumData: AlbumData)
    }


}