package com.application.nasapicturesapp.ui.adapter

import android.content.Context
import android.util.DisplayMetrics
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.application.nasapicturesapp.R
import com.application.nasapicturesapp.databinding.RowMaingridLayoutBinding
import com.application.nasapicturesapp.model.PicturesModel
import com.application.nasapicturesapp.ui.activity.MainActivity
import com.application.nasapicturesapp.utils.ItemCallback

class MainGridAdapter (var context: Context,var allPictures:ArrayList<PicturesModel>,var itemCallback: ItemCallback):RecyclerView.Adapter<MainGridAdapter.GridHolder>(){

    var widthPixels=0
    init {
        val metrics = DisplayMetrics()
        (context as MainActivity).windowManager.defaultDisplay.getMetrics(metrics)
        widthPixels = metrics.widthPixels

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
        var w=((widthPixels)/3)
        val params1 = LinearLayout.LayoutParams(
            w,
            w
        )
        holder.rowMainGridLayoutBinding.image.layoutParams=params1
        holder.rowMainGridLayoutBinding.picture=allPictures[position]
        holder.rowMainGridLayoutBinding.image.setOnClickListener {
            itemCallback.pictureClicked(allPictures[position])
        }

    }

    override fun getItemCount(): Int {
       return allPictures.size
    }
}