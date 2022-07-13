package com.application.nasapicturesapp.ui.fragment

import android.graphics.drawable.Drawable
import android.os.Bundle
import android.util.Log
import android.view.View
import com.application.nasapicturesapp.R
import com.application.nasapicturesapp.base.BaseFragment
import com.application.nasapicturesapp.databinding.PictureDetailFragmentBinding
import com.application.nasapicturesapp.model.PicturesModel
import com.application.nasapicturesapp.utils.IntentConstant
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target

class PictureDetailFragment: BaseFragment<PictureDetailFragmentBinding>() {

    private lateinit var pictureDetailFragmentBinding: PictureDetailFragmentBinding
    private lateinit var picturesModel: PicturesModel

    companion object{
        fun newInstance(pictureModel:PicturesModel):PictureDetailFragment {
            val args = Bundle()
            args.putSerializable(IntentConstant.SINGLE_ITEM,pictureModel)
            val fragment = PictureDetailFragment()
            fragment.arguments = args
            return fragment
        }
    }

    override fun getLayoutId(): Int {
       return R.layout.picture_detail_fragment
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        pictureDetailFragmentBinding=getViewDataBinding()
        picturesModel= arguments?.getSerializable(IntentConstant.SINGLE_ITEM) as PicturesModel
        pictureDetailFragmentBinding.picture=picturesModel
        pictureDetailFragmentBinding.imageDetail.transitionName = picturesModel.title
        Glide.with(this)
            .load(picturesModel.url)
            .listener(object : RequestListener<Drawable?> {
                override fun onLoadFailed(
                    e: GlideException?,
                    model: Any,
                    target: Target<Drawable?>,
                    isFirstResource: Boolean
                ): Boolean {
                    parentFragment!!.startPostponedEnterTransition()
                    return false
                }

                override fun onResourceReady(
                    resource: Drawable?,
                    model: Any,
                    target: Target<Drawable?>,
                    dataSource: DataSource,
                    isFirstResource: Boolean
                ): Boolean {
                    parentFragment!!.startPostponedEnterTransition()
                    return false
                }
            })
            .into(pictureDetailFragmentBinding.imageDetail)
    }
}