package com.application.nasapicturesapp.ui.adapter

import android.content.Context
import android.graphics.drawable.Drawable
import android.util.DisplayMetrics
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.application.nasapicturesapp.R
import com.application.nasapicturesapp.databinding.RowMaingridLayoutBinding
import com.application.nasapicturesapp.model.PicturesModel
import com.application.nasapicturesapp.ui.activity.MainActivity
import com.application.nasapicturesapp.utils.ItemCallback
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target


class MainGridAdapter(
    var context: Context,
    var allPictures: ArrayList<PicturesModel>,
    var itemCallback: ItemCallback
) : RecyclerView.Adapter<MainGridAdapter.GridHolder>() {

    var widthPixels = 0
    private var requestManager: RequestManager

    init {
        val metrics = DisplayMetrics()
        (context as MainActivity).windowManager.defaultDisplay.getMetrics(metrics)
        widthPixels = metrics.widthPixels
        this.requestManager = Glide.with(context)

    }

    class GridHolder(var rowMainGridLayoutBinding: RowMaingridLayoutBinding) :
        RecyclerView.ViewHolder(rowMainGridLayoutBinding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GridHolder {
        var rowPhotoLayoutBinding = DataBindingUtil.inflate<RowMaingridLayoutBinding>(
            LayoutInflater.from(parent.context), R.layout.row_maingrid_layout, parent, false
        )
        return GridHolder(rowPhotoLayoutBinding)
    }

    override fun onBindViewHolder(holder: GridHolder, position: Int) {
        var w = ((widthPixels) / 3)
        val params = FrameLayout.LayoutParams(
            w,
            w
        )
        holder.rowMainGridLayoutBinding.cardView.layoutParams = params
        bind(holder.rowMainGridLayoutBinding, position)


    }

    override fun getItemCount(): Int {
        return allPictures.size
    }

    private fun bind(rowMainGridLayoutBinding: RowMaingridLayoutBinding, position: Int) {
        rowMainGridLayoutBinding.image.transitionName = allPictures[position].title
        requestManager
            .load(allPictures[position].url)
            .listener(object : RequestListener<Drawable?> {
                override fun onLoadFailed(
                    e: GlideException?, model: Any,
                    target: Target<Drawable?>, isFirstResource: Boolean
                ): Boolean {
                    itemCallback.onLoadCompleted(rowMainGridLayoutBinding.image, position)
                    return false
                }

                override fun onResourceReady(
                    resource: Drawable?,
                    model: Any,
                    target: Target<Drawable?>,
                    dataSource: DataSource,
                    isFirstResource: Boolean
                ): Boolean {
                    itemCallback.onLoadCompleted(rowMainGridLayoutBinding.image, position)
                    return false
                }
            })
            .into(rowMainGridLayoutBinding.image)

        rowMainGridLayoutBinding.cardView.setOnClickListener {
            itemCallback.pictureClicked(rowMainGridLayoutBinding.image, position)

        }
    }
}