package com.sardordev.lockimages.presenter.page

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.PopupMenu
import androidx.core.app.ActivityCompat
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.sardordev.lockimages.R
import com.sardordev.lockimages.data.localstorage.LocalStorage
import com.sardordev.lockimages.data.model.SavedImg
import com.sardordev.lockimages.databinding.FragmentAlbumsScreenBinding
import com.sardordev.lockimages.domain.imp.AppRepositoryImp
import com.sardordev.lockimages.`object`.GetObjects
import com.sardordev.lockimages.presenter.adapter.SavedAdapter
import com.sardordev.lockimages.presenter.factory.AlbumViewModelFactory
import com.sardordev.lockimages.viewmodel.AlbumViewModel
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.util.LinkedList

class AlbumsScreen : Fragment() {
    private val binding by lazy { FragmentAlbumsScreenBinding.inflate(layoutInflater) }
    private var idkey = GetObjects.albumData.id
    private val savedAdapter = SavedAdapter()
    private val viewModel: AlbumViewModel by viewModels<AlbumViewModel>(factoryProducer = {
        val repository = AppRepositoryImp.getInstance()
        return@viewModels AlbumViewModelFactory(repository)
    })

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        binding.imgAdd.setOnClickListener {
            initpopUp()
        }
        initRecycler()










        return binding.root
    }


    private fun initpopUp() {
        val popupMenu = PopupMenu(requireContext(), binding.imgAdd)
        popupMenu.menuInflater.inflate(R.menu.menu_gallery, popupMenu.menu)
        popupMenu.setOnMenuItemClickListener { item ->
            when (item.itemId) {
                R.id.menu_album_fromGallery -> {
                    findNavController().navigate(R.id.galleryScreen)
                }
            }
            true
        }
        popupMenu.show()
    }


    private fun initRecycler() {
        var savedList = ArrayList<SavedImg>()
        viewModel.getByidkey(idkey).observe(viewLifecycleOwner, Observer {
            Log.d("EEE",savedList.toString())
            savedAdapter.submitList(it.distinctBy {it.path})
        })
        binding.rvSavedImg.apply {
            adapter = savedAdapter
            layoutManager = GridLayoutManager(requireContext(), 4)
        }
    }





}