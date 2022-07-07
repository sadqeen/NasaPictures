package com.application.nasapicturesapp.ui.fragment

import android.os.Bundle
import android.view.View
import com.application.nasapicturesapp.R
import com.application.nasapicturesapp.databinding.MainfragmentBinding
import com.application.practicalapp.base.BaseFragment

class MainFragment :BaseFragment<MainfragmentBinding>() {
    override fun getLayoutId(): Int {
        return R.layout.mainfragment
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}