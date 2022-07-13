package com.application.nasapicturesapp.ui.fragment

import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.View.OnLayoutChangeListener
import android.widget.ImageView
import androidx.core.app.SharedElementCallback
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.transition.TransitionInflater
import androidx.transition.TransitionSet
import com.application.nasapicturesapp.R
import com.application.nasapicturesapp.base.BaseFragment
import com.application.nasapicturesapp.databinding.MainfragmentBinding
import com.application.nasapicturesapp.model.PicturesModel
import com.application.nasapicturesapp.ui.activity.MainActivity
import com.application.nasapicturesapp.ui.adapter.MainGridAdapter
import com.application.nasapicturesapp.utils.ItemCallback
import com.application.nasapicturesapp.utils.NavigationUtils
import com.application.nasapicturesapp.viewmodel.MainGridModel


class MainFragment : BaseFragment<MainfragmentBinding>(), ItemCallback {
    private val TAG = MainFragment::class.simpleName
    private lateinit var mainGridModel: MainGridModel
    private lateinit var mainfragmentBinding: MainfragmentBinding
    private var allPictures = ArrayList<PicturesModel>()
    private lateinit var gridAdapter: MainGridAdapter
    override fun getLayoutId(): Int {
        return R.layout.mainfragment
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mainfragmentBinding = getViewDataBinding()
        val factory =
            ViewModelProvider.AndroidViewModelFactory.getInstance(requireActivity().application)
        mainGridModel = ViewModelProvider(this, factory)[MainGridModel::class.java]

        mainGridModel.allPictures.observe(viewLifecycleOwner, Observer {
            Log.e(TAG, "data ${it.size}")
            allPictures.clear()
            allPictures.addAll(it)
            setData()
        })


    }

    private fun setData() {
        mainfragmentBinding.recylerviewMain.layoutManager =
            GridLayoutManager(requireContext(), 3)
        gridAdapter = MainGridAdapter(requireContext(), allPictures, this)
        mainfragmentBinding.recylerviewMain.adapter = gridAdapter
        prepareTransitions()
        postponeEnterTransition()
        if ((activity as MainActivity).getPosition() != 0) {
            scrollToPosition()
        }

    }

    override fun pictureClicked(card: ImageView, position: Int) {
        (activity as MainActivity).setPosition(position)
        (exitTransition as TransitionSet).excludeTarget(card, true)
        val transitioningView: ImageView = card
        NavigationUtils.replaceFragmentWithSharedElement(
            (context as MainActivity).supportFragmentManager,
            transitioningView,
            PicturePagerFragment.newInstance(position, allPictures),
            R.id.maincontainer
        )
    }

    override fun onLoadCompleted(view: ImageView?, adapterPosition: Int) {
        if ((activity as MainActivity).getPosition() != adapterPosition) {
            return
        }
        startPostponedEnterTransition()
    }

    private fun prepareTransitions() {
        exitTransition = TransitionInflater.from(context)
            .inflateTransition(R.transition.grid_exit_transition)

        setExitSharedElementCallback(
            object : SharedElementCallback() {
                override fun onMapSharedElements(
                    names: List<String?>,
                    sharedElements: MutableMap<String?, View?>
                ) {
                    val selectedViewHolder: RecyclerView.ViewHolder =
                        mainfragmentBinding.recylerviewMain
                            .findViewHolderForAdapterPosition((activity as MainActivity).getPosition())
                            ?: return

                    sharedElements[names[0]] =
                        selectedViewHolder.itemView.findViewById(R.id.image)
                }
            })
    }

    private fun scrollToPosition() {
        mainfragmentBinding.recylerviewMain.addOnLayoutChangeListener(object :
            OnLayoutChangeListener {
            override fun onLayoutChange(
                v: View,
                left: Int,
                top: Int,
                right: Int,
                bottom: Int,
                oldLeft: Int,
                oldTop: Int,
                oldRight: Int,
                oldBottom: Int
            ) {
                mainfragmentBinding.recylerviewMain.removeOnLayoutChangeListener(this)
                val layoutManager: RecyclerView.LayoutManager =
                    mainfragmentBinding.recylerviewMain.layoutManager!!
                val viewAtPosition =
                    layoutManager.findViewByPosition((activity as MainActivity).getPosition())
                if (viewAtPosition == null || layoutManager
                        .isViewPartiallyVisible(viewAtPosition, false, true)
                ) {
                    mainfragmentBinding.recylerviewMain.post(Runnable {
                        layoutManager.scrollToPosition(
                            (activity as MainActivity).getPosition()
                        )
                    })
                }
            }
        })
    }

}