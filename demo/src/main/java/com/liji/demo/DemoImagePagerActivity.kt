package com.liji.demo

import android.os.Bundle
import com.liji.imagezoom.activity.ImagePagerActivity
import kotlinx.android.synthetic.main.pager_image_detail.*

class DemoImagePagerActivity : ImagePagerActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dotsIndicator.setViewPager(mPager)
        mPager.adapter?.registerDataSetObserver(dotsIndicator.dataSetObserver)
    }
}