package com.peaktime.dawntime.Community

import android.net.Uri
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.RequestManager
import com.peaktime.dawntime.R

/**
 * Created by minhyoung on 2018. 1. 6..
 */
class TakeImageAdapter(var dataList: ArrayList<Uri>?, var requestManager: RequestManager) : RecyclerView.Adapter<TakeImageViewHolder>() {
    override fun getItemCount(): Int = dataList!!.size

    override fun onBindViewHolder(holder: TakeImageViewHolder?, position: Int) {
        holder!!.takeImageView.setImageURI(dataList!!.get(position))
        requestManager.load(dataList!!.get(position)).into(holder.takeImageView)
//        holder!!.takeImageView.setImageBitmap(dataList!!.get(position))
//        holder!!.takeImageView.clipToOutline = true
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): TakeImageViewHolder {
        val mainView : View = LayoutInflater.from(parent!!.context).inflate(R.layout.community_write_image_items, parent, false)
        return TakeImageViewHolder(mainView)
    }
}