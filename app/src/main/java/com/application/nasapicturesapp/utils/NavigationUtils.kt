package com.application.nasapicturesapp.utils

import android.view.View
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

    fun addFragmentWithoutBackStack(fragment: Fragment, fragmentManager: FragmentManager, id: Int) {
        var transaction = fragmentManager.beginTransaction()
        transaction.replace(id, fragment, fragment.javaClass.simpleName)
        transaction.commit()
    }

    fun replaceFragmentWithSharedElement(
        fragmentManager: FragmentManager,
        transitioningView: View,
        fragment: Fragment,
        id: Int
    ) {
        fragmentManager
            .beginTransaction()
            .setReorderingAllowed(true)
            .addSharedElement(transitioningView, transitioningView.transitionName)
            .replace(
                id,
                fragment,
                fragment.javaClass.simpleName
            )
            .addToBackStack(fragment.javaClass.simpleName)
            .commit()
    }
}