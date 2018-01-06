package com.peaktime.dawntime.Community

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.peaktime.dawntime.R

/**
 * Created by minhyoung on 2018. 1. 6..
 */
class TakeImageAdapter(var dataList : ArrayList<TakeImageData>?) : RecyclerView.Adapter<TakeImageViewHolder>()  {
    override fun getItemCount(): Int = dataList!!.size

    override fun onBindViewHolder(holder: TakeImageViewHolder?, position: Int) {
        holder!!.takeImageView.setImageBitmap(dataList!!.get(position).takeImageView)
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): TakeImageViewHolder {
        val mainView : View = LayoutInflater.from(parent!!.context).inflate(R.layout.community_write_image_items, parent, false)
        return TakeImageViewHolder(mainView)
    }
}