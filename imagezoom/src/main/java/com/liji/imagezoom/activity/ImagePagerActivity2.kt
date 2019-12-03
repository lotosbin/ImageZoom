package com.liji.imagezoom.activity

import android.os.Bundle
import android.support.v4.app.FragmentActivity
import android.util.Log
import com.liji.imagezoom.R
import kotlinx.android.synthetic.main.pager_image_detail.*
import java.util.*

internal abstract class ImagePagerActivity2 : FragmentActivity() {
    @JvmField
    protected var titleList: List<String> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.pager_image_detail)
        image_zoom_button_back?.setOnClickListener { finish() }
    }

    protected open fun onPageSelected(position: Int) {
        try {
            image_zoom_title?.text = titleList[position]
        } catch (e: Exception) {
            Log.e("ImagePagerActivity", "onPageSelected:${e.message}", e)
        }
    }
}