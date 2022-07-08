package com.application.nasapicturesapp.base


import android.content.Context
import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.application.nasapicturesapp.R
import com.application.nasapicturesapp.viewmodel.ConnectivityModel
import es.dmoral.toasty.Toasty


abstract class BaseActivity<V : ViewDataBinding> : AppCompatActivity() {

    abstract fun getLayoutId(): Int
    private lateinit var connectivityModel: ConnectivityModel
    lateinit var mviewdatabinding: ViewDataBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mviewdatabinding = DataBindingUtil.setContentView(this, getLayoutId())
        val factory =
            ViewModelProvider.AndroidViewModelFactory.getInstance(this.application)
        connectivityModel = ViewModelProvider(this, factory)[ConnectivityModel::class.java]

        connectivityModel.status.observe(this, Observer {
            if (!it) {
                Toasty.error(applicationContext, getString(R.string.no_internet)).show()
            }
        })
    }

    fun getViewBinding(): V {
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