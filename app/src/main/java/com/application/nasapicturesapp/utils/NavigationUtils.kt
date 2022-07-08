package com.application.nasapicturesapp.utils

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager

object NavigationUtils {

    fun replaceFragment(fragment: Fragment, fragmentManager: FragmentManager, id: Int) {
        var transaction = fragmentManager.beginTransaction()
        transaction.replace(id, fragment)
        transaction.commit()
    }

    fun addFragment(fragment: Fragment, fragmentManager: FragmentManager, id: Int) {
        var transaction = fragmentManager.beginTransaction()
        transaction.add(id, fragment)
        transaction.addToBackStack(fragment.javaClass.name)
        transaction.commit()
    }
}