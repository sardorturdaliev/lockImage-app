package com.sardordev.lockimages.presenter.page

import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.sardordev.lockimages.data.model.ImageData
import com.sardordev.lockimages.data.model.SavedImg
import com.sardordev.lockimages.databinding.FragmentGalleryScreenBinding
import com.sardordev.lockimages.domain.imp.AppRepositoryImp
import com.sardordev.lockimages.`object`.GetObjects
import com.sardordev.lockimages.presenter.adapter.ImageAdapter
import com.sardordev.lockimages.presenter.factory.GalleryViewModelFactory
import com.sardordev.lockimages.viewmodel.GalleryViewModel
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.io.File

/*
 1.save single images
 2.when (multiply) click recyclerview -> save img
 */



class GalleryScreen : Fragment(), ImageAdapter.ItemImageListener {
    private val binding by lazy { FragmentGalleryScreenBinding.inflate(layoutInflater) }
    private lateinit var imageAdapter: ImageAdapter
    private val viewModel: GalleryViewModel by viewModels<GalleryViewModel>(factoryProducer = {
        val repository = AppRepositoryImp.getInstance()
        return@viewModels GalleryViewModelFactory(repository)
    })
    private var idkey = GetObjects.albumData.id
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        imageAdapter = ImageAdapter(requireContext(), this)
        listImages()



        binding.btnLock.setOnClickListener {
            val rememberedList = ArrayList<ImageData>()
            viewModel.getIsremember.observe(viewLifecycleOwner, Observer { item ->
                Log.d("isremember", "${Environment.getExternalStorageDirectory()}")

                GlobalScope.launch {
                    for ( i in item){
                        if (!rememberedList.contains(i)){
                            viewModel.saveSelected(SavedImg(i.id,i.path,idkey))
                        }
                    }
                }
                findNavController().popBackStack()
            })





            deleteImagePath("/storage/emulated/0/Pictures/Telegram/IMG_20230224_230100_285.jpg")

        }





        return binding.root
    }


    private fun listImages() {
        loadImages()
        getImage()
    }


    private fun loadImages() {
        val images = mutableListOf<ImageData>()
        val uniqList = arrayListOf<ImageData>()
        val projection = arrayOf(MediaStore.Images.Media._ID, MediaStore.Images.Media.DATA)
        val cursor = requireContext().contentResolver.query(
            MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
            projection,
            null,
            null,
            null
        )
        cursor?.use {
            while (it.moveToNext()) {
                val id = it.getLong(it.getColumnIndexOrThrow(MediaStore.Images.Media._ID))
                val path = it.getString(it.getColumnIndexOrThrow(MediaStore.Images.Media.DATA))
                images.add(ImageData(0, path))
                Log.d("TAG", id.toString())
                for (item in images) {
                    for (i in images.indices) {
                        if (!uniqList.contains(item)) {
                            uniqList.add(item)
                            Log.d("listsize", "list b    ${images.size}")
                            /*
                            save to db
                             */
                            viewModel.insertImage(item)
                        }
                    }
                }
                Log.d("uniqlist", uniqList.size.toString())
            }
        }
    }


    private fun getImage() {
        viewModel.getAllImages.observe(viewLifecycleOwner, Observer {
            imageAdapter.updateData(it)
            binding.recyclerImg.apply {
                adapter = imageAdapter
                layoutManager = GridLayoutManager(requireContext(), 4)
            }
        })
    }


    override fun itemClick(id: Int, remember: Boolean, imageModel: ImageData, pos: Int) {
        viewModel.update(ImageData(id, imageModel.path, remember))
    }


    override fun onDestroy() {
        super.onDestroy()
        viewModel.deleteItem()
    }


    private fun deleteImagePath(filePath: String) {
        val file = File(filePath)
        if (file.exists()) {
            val deleted = file.delete()
            if (deleted) {
                Toast.makeText(requireContext(), "Deleted", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(requireContext(), "Fail", Toast.LENGTH_SHORT).show()
            }
        } else {
            Toast.makeText(requireContext(), "Image file not found", Toast.LENGTH_SHORT).show()
        }
    }






}


