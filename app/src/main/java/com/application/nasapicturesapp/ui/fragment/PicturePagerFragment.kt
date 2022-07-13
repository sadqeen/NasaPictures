package com.application.nasapicturesapp.ui.fragment


import android.os.Bundle

import android.view.View
import androidx.core.app.SharedElementCallback
import androidx.fragment.app.Fragment
import androidx.transition.Transition
import androidx.transition.TransitionInflater
import androidx.viewpager.widget.ViewPager.SimpleOnPageChangeListener
import com.application.nasapicturesapp.R
import com.application.nasapicturesapp.base.BaseFragment
import com.application.nasapicturesapp.databinding.PicturePagerFragmentBinding
import com.application.nasapicturesapp.model.PicturesModel
import com.application.nasapicturesapp.ui.activity.MainActivity
import com.application.nasapicturesapp.ui.adapter.PicturePagerAdapter
import com.application.nasapicturesapp.utils.IntentConstant


class PicturePagerFragment :BaseFragment<PicturePagerFragmentBinding>() {

    private lateinit var picturePagerFragmentBinding: PicturePagerFragmentBinding
    private  var position=0
    private  var allData=ArrayList<PicturesModel>()

    companion object{
        fun newInstance(position:Int,data:ArrayList<PicturesModel>):PicturePagerFragment {
            val args = Bundle()
            args.putInt(IntentConstant.ITEM_POSITION,position)
            args.putSerializable(IntentConstant.ALL_ITEMS,data)
            val fragment =PicturePagerFragment ()
            fragment.arguments = args
            return fragment
        }
    }

    override fun getLayoutId(): Int {
      return R.layout.picture_pager_fragment
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        picturePagerFragmentBinding=getViewDataBinding()
        position= arguments?.getInt(IntentConstant.ITEM_POSITION)!!
        allData= arguments?.getSerializable(IntentConstant.ALL_ITEMS) as ArrayList<PicturesModel>
        picturePagerFragmentBinding.viewpager.adapter = PicturePagerAdapter(this,allData)

        picturePagerFragmentBinding.viewpager.currentItem = position
        picturePagerFragmentBinding.viewpager.addOnPageChangeListener(object : SimpleOnPageChangeListener() {
            override fun onPageSelected(position: Int) {
                (activity as MainActivity).setPosition(position)
            }
        })
        prepareSharedElementTransition()

        if (savedInstanceState == null) {
            postponeEnterTransition()
        }
    }

    private fun prepareSharedElementTransition() {
        val transition: Transition = TransitionInflater.from(context)
            .inflateTransition(R.transition.image_shared_element_transition)
        sharedElementEnterTransition = transition

        setEnterSharedElementCallback(
            object : SharedElementCallback() {
                override fun onMapSharedElements(
                    names: List<String?>,
                    sharedElements: MutableMap<String?, View?>
                ) {

                    val currentFragment: Fragment = picturePagerFragmentBinding.viewpager.adapter
                        ?.instantiateItem(picturePagerFragmentBinding.viewpager, position) as Fragment
                    val view: View = currentFragment.view ?: return

                    sharedElements[names[0]] = view.findViewById(R.id.imageDetail)
                }
            })
    }
}