package com.liji.imagezoom.util

import android.content.Context
import android.content.Intent
import android.util.Log
import com.liji.imagezoom.activity.ImagePagerActivity
import java.util.*

object ImageZoomUtil {
    inline fun <reified T : ImagePagerActivity> show(context: Context, url: String?, list: List<String?>, titles: List<String?>?) {
        try {
            val position = list.indexOf(url)
            val intent = Intent(context, T::class.java)
            // 图片url,为了演示这里使用常量，一般从数据库中或网络中获取
            intent.putStringArrayListExtra(ImagePagerActivity.EXTRA_IMAGE_URLS, list as ArrayList<String?>)
            intent.putStringArrayListExtra(ImagePagerActivity.EXTRA_IMAGE_TITLES, titles as ArrayList<String?>?)
            intent.putExtra(ImagePagerActivity.EXTRA_IMAGE_INDEX, position)
            context.startActivity(intent)
        } catch (e: Exception) {
            Log.e("imagezoom", e.message)
        }
    }
}