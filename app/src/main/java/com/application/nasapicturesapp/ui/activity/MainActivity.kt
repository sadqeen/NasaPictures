package com.application.nasapicturesapp.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.application.nasapicturesapp.R
import com.application.nasapicturesapp.base.BaseActivity
import com.application.nasapicturesapp.databinding.ActivityMainBinding

class MainActivity : BaseActivity<ActivityMainBinding>() {

    override fun getLayoutId(): Int {
        return R.layout.activity_main
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }
}