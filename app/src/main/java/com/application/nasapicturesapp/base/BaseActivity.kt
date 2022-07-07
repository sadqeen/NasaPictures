package com.application.nasapicturesapp.base


import android.content.Context
import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding


abstract class BaseActivity<V:ViewDataBinding> :AppCompatActivity(){

    abstract fun getLayoutId():Int
    lateinit var mviewdatabinding: ViewDataBinding



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mviewdatabinding= DataBindingUtil.setContentView(this,getLayoutId())

    }

    fun getViewBinding():V{
        return mviewdatabinding as V
    }
    open fun hideKeyboard() {
        val view = this.currentFocus
        if (view != null) {
            val imm =
                getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm?.hideSoftInputFromWindow(view.windowToken, 0)
        }
    }
}