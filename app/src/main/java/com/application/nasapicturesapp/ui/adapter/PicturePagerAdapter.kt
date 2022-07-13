package com.application.nasapicturesapp.ui.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentStatePagerAdapter
import com.application.nasapicturesapp.model.PicturesModel
import com.application.nasapicturesapp.ui.fragment.PictureDetailFragment

class PicturePagerAdapter(var fragment: Fragment,var picturesModel: ArrayList<PicturesModel>) : FragmentStatePagerAdapter(fragment.childFragmentManager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {


    override fun getCount(): Int {
        return picturesModel.size
    }

    override fun getItem(position: Int): Fragment {
        return PictureDetailFragment.newInstance(picturesModel[position])
    }


}