package com.giahuy.fake_call.component.activity

import android.util.Log
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.CompositePageTransformer
import androidx.viewpager2.widget.MarginPageTransformer
import androidx.viewpager2.widget.ViewPager2
import com.giahuy.fake_call.adapter.ImagePreviewAdapter
import com.giahuy.fake_call.databinding.ActivityChooseThemeFakeCallBinding
import com.giahuy.fake_call.utils.ConstantApp.LIST_PREVIEW_CALL

class ChooseThemeFakeCallActivity : BaseActivity<ActivityChooseThemeFakeCallBinding>() {

    private lateinit var viewPager2: ViewPager2
    private lateinit var listPreviewCall: ArrayList<Int>
    private lateinit var imagePreviewAdapter: ImagePreviewAdapter

    override fun initView() {

        viewPager2 = binding.viewPager2
        listPreviewCall = LIST_PREVIEW_CALL
        imagePreviewAdapter = ImagePreviewAdapter(listPreviewCall, viewPager2)

        viewPager2.adapter = imagePreviewAdapter
        viewPager2.offscreenPageLimit = 3
        viewPager2.clipToPadding = false
        viewPager2.clipChildren = false
        viewPager2.getChildAt(0).overScrollMode = RecyclerView.OVER_SCROLL_NEVER

        val transformer = CompositePageTransformer()
        transformer.addTransformer(MarginPageTransformer(40))
        transformer.addTransformer { page, position ->
            Log.e("GiaHuy", position.toString())
            val r = 1 - kotlin.math.abs(position)
            page.scaleY = 0.85f + r * 0.14f
        }
        viewPager2.setPageTransformer(transformer)
        binding.dotsIndicator.attachTo(viewPager2)
    }

    override fun initData() {
    }

    override fun initListener() {
    }

    override fun getViewBinding(): ActivityChooseThemeFakeCallBinding {
        return ActivityChooseThemeFakeCallBinding.inflate(layoutInflater)
    }
}