package com.application.nasapicturesapp.ui.fragment

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.application.nasapicturesapp.R
import com.application.nasapicturesapp.base.BaseFragment
import com.application.nasapicturesapp.databinding.MainfragmentBinding
import com.application.nasapicturesapp.model.PicturesModel
import com.application.nasapicturesapp.ui.adapter.MainGridAdapter
import com.application.nasapicturesapp.utils.ItemCallback
import com.application.nasapicturesapp.viewmodel.MainGridModel
import java.text.FieldPosition


class MainFragment : BaseFragment<MainfragmentBinding>(), ItemCallback {
    private val TAG = MainFragment::class.simpleName
    private lateinit var mainGridModel: MainGridModel
    private lateinit var mainfragmentBinding: MainfragmentBinding
    private var allPictures = ArrayList<PicturesModel>()
    private lateinit var gridAdapter: MainGridAdapter
    override fun getLayoutId(): Int {
        return R.layout.mainfragment
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mainfragmentBinding = getViewDataBinding()
        val factory =
            ViewModelProvider.AndroidViewModelFactory.getInstance(requireActivity().application)
        mainGridModel = ViewModelProvider(this, factory)[MainGridModel::class.java]

        mainGridModel.allPictures.observe(viewLifecycleOwner, Observer {
            Log.i(TAG, "data ${it.size}")
            allPictures.addAll(it)
            setData()
        })


    }

    private fun setData() {
        if (::gridAdapter.isInitialized) {
            gridAdapter.notifyDataSetChanged()
        } else {
            mainfragmentBinding.recylerviewMain.layoutManager =
                GridLayoutManager(requireContext(), 3)
            gridAdapter = MainGridAdapter(requireContext(), allPictures,this)
            mainfragmentBinding.recylerviewMain.adapter = gridAdapter

        }
    }

    override fun pictureClicked(picturesModel: PicturesModel) {

    }

}