package com.application.nasapicturesapp.ui.activity

import android.os.Bundle
import com.application.nasapicturesapp.R
import com.application.nasapicturesapp.base.BaseActivity
import com.application.nasapicturesapp.databinding.ActivityMainBinding
import com.application.nasapicturesapp.ui.fragment.MainFragment
import com.application.nasapicturesapp.utils.NavigationUtils

class MainActivity : BaseActivity<ActivityMainBinding>() {

    private var position: Int = 0
    override fun getLayoutId(): Int {
        return R.layout.activity_main
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        NavigationUtils.addFragmentWithoutBackStack(
            MainFragment(),
            supportFragmentManager,
            R.id.maincontainer
        )

    }

    fun setPosition(pos: Int) {
        this.position = pos
    }

    fun getPosition(): Int {
        return position
    }


}