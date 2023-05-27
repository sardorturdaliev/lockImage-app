package com.sardordev.lockimages.presenter.page

import android.Manifest
import android.app.AlertDialog
import android.content.pm.PackageManager
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.PopupMenu
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView.LayoutManager
import com.sardordev.lockimages.R
import com.sardordev.lockimages.constants.GetConstants
import com.sardordev.lockimages.data.localstorage.LocalStorage
import com.sardordev.lockimages.data.model.AlbumData
import com.sardordev.lockimages.databinding.CustomNewalbumBinding
import com.sardordev.lockimages.databinding.FragmentMainBinding
import com.sardordev.lockimages.domain.imp.AppRepositoryImp
import com.sardordev.lockimages.`object`.GetObjects
import com.sardordev.lockimages.presenter.adapter.AlbumAdapter
import com.sardordev.lockimages.presenter.factory.AlbumViewModelFactory
import com.sardordev.lockimages.presenter.factory.MainViewModelFactory
import com.sardordev.lockimages.viewmodel.AlbumViewModel
import com.sardordev.lockimages.viewmodel.MainViewModel

class MainFragment : Fragment(), AlbumAdapter.AlbumClickListener {
    private val binding by lazy { FragmentMainBinding.inflate(layoutInflater) }
    private val viewModel by viewModels<MainViewModel>(factoryProducer = {
        val repository = AppRepositoryImp.getInstance()
        return@viewModels MainViewModelFactory(repository)
    })


    private val albumAdapter = AlbumAdapter(this)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        binding.imgAdd.setOnClickListener {
            addPopMenu()
        }


        initAlbumRecycler()

        checkPermission()

        clickOrientation()
        return binding.root
    }

    private fun addPopMenu() {
        val popupMenu = PopupMenu(requireContext(), binding.imgAdd)
        popupMenu.menuInflater.inflate(R.menu.menu_add, popupMenu.menu)
        popupMenu.setOnMenuItemClickListener { item ->
            when (item.itemId) {
                R.id.menu_fromCamera -> {
                    Toast.makeText(requireContext(), "Gallert", Toast.LENGTH_SHORT).show()
                }
                R.id.menu_newAlbum -> {
                    createCustomDialog()
                }
            }
            true
        }
        popupMenu.show()
    }


    private fun checkPermission() {
        if (ActivityCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.READ_EXTERNAL_STORAGE
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                requireActivity(),
                Array(1) { Manifest.permission.READ_EXTERNAL_STORAGE },
                121
            )
            LocalStorage.savePermission(true)
        }
    }


    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == 121 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            //code
            LocalStorage.savePermission(true)
        }
    }


    private fun createCustomDialog() {
        val alert = AlertDialog.Builder(requireContext())
        val view = CustomNewalbumBinding.inflate(layoutInflater)
        alert.setView(view.root)
        val dialog = alert.show()
        view.btnNewAlbum.setOnClickListener {
            if (view.edtNewalbum.text.isNotEmpty()) {
                viewModel.insertAlbum(AlbumData(0, view.edtNewalbum.text.toString()))
                dialog.dismiss()
            }
        }
        dialog.create()
    }


    private fun initAlbumRecycler() {
        viewModel.getAlbum.observe(viewLifecycleOwner, Observer {
            albumAdapter.submitList(it)
        })


    }


    override fun onclick(albumData: AlbumData) {
        GetObjects.albumData = albumData
        findNavController().navigate(R.id.albumsScreen)
    }


    private fun clickOrientation() {
        var getorient = LocalStorage.getSavedOrientationMain()//false , true
        if (getorient) orientaionVertical()
        else orientationHorzontal()

        binding.imgMenu.setOnClickListener {
            if (!getorient) {//false
                LocalStorage.saveOrientationMain(true)
                orientaionVertical()
            } else {
                LocalStorage.saveOrientationMain(false)
                orientationHorzontal()
            }
        }


    }


    private fun orientationHorzontal() {
        viewModel.getAlbum.observe(viewLifecycleOwner, Observer {
            albumAdapter.submitList(it)
        })

        binding.rvAlbum.apply {
            adapter = albumAdapter
            layoutManager = GridLayoutManager(requireContext(), 2)
        }
    }
    private fun orientaionVertical() {
        viewModel.getAlbum.observe(viewLifecycleOwner, Observer {
            albumAdapter.submitList(it)
        })
        binding.rvAlbum.apply {
            adapter = albumAdapter
            layoutManager = LinearLayoutManager(requireContext())
        }
    }




}

