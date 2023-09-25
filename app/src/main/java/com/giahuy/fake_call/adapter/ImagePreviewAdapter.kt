package com.giahuy.fake_call.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatImageView
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.giahuy.fake_call.R

class ImagePreviewAdapter(
    private val listImageView: ArrayList<Int>,
    private val viewPager2: ViewPager2
) : RecyclerView.Adapter<ImagePreviewAdapter.ImageViewHolder>() {

    inner class ImageViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageView = itemView.findViewById<AppCompatImageView>(R.id.imvPreview)!!
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
        return ImageViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_preview_call, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return listImageView.size
    }

    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
        holder.imageView.setImageResource(listImageView[position])
    }

}