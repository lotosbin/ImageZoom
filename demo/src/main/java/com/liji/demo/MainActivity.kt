package com.liji.demo

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Button
import com.liji.imagezoom.util.ImageZoomUtil
import java.util.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val urls = arrayListOf(
                "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1516698497240&di=0a888fbd1fcdec29595f9a3ac0f6f89d&imgtype=0&src=http%3A%2F%2Fwww.5djpg.com%2Fuploads%2Fallimg%2F140609%2F1-1406091P426.jpg",
                "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1516698497240&di=e66e6edd71d48bdd27c83e4ccd8d30f0&imgtype=0&src=http%3A%2F%2Fwww.5djpg.com%2Fuploads%2Fallimg%2F140609%2F1-1406091A944.png",
                "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1516698497240&di=e3b509473ae1f4cecce3557affe251a7&imgtype=0&src=http%3A%2F%2Fattach.bbs.miui.com%2Fforum%2F201601%2F27%2F213042j6ctt6key5zen96y.jpg",
                "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1516698497239&di=e412a209088e0d6859c9e5c550e1612e&imgtype=0&src=http%3A%2F%2Fstatic01.coloros.com%2Fbbs%2Fdata%2Fattachment%2Fforum%2F201706%2F21%2F183038mfrdvux3ridbkkzk.png",
                "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1516698497239&di=2da8ab024652c434aabd0d5258265a2b&imgtype=0&src=http%3A%2F%2Fwww.5djpg.com%2Fuploads%2Fallimg%2F140612%2F1-140612160249.jpg",
                "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1516698497239&di=99f3fc04714cbed6704515a14524b94e&imgtype=0&src=http%3A%2F%2Fattach.bbs.miui.com%2Fforum%2F201601%2F27%2F213021jsmsobnlm1izanaj.jpg",
                "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1516698497239&di=aceb54a4034612108567fb97ec5cb953&imgtype=0&src=http%3A%2F%2Fattach.bbs.miui.com%2Fforum%2F201601%2F27%2F213016znqdon3vvodrodvn.jpg",
                "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1516698497239&di=bf872f1eae001abc0e3ad8a6fd2f9cbc&imgtype=0&src=http%3A%2F%2Fwww.5djpg.com%2Fuploads%2Fallimg%2F140720%2F1-140H0150431.jpg",
                "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1516698497239&di=979c1c3972719f82f2cc5f600da3de47&imgtype=0&src=http%3A%2F%2Fwww.5djpg.com%2Fuploads%2Fallimg%2F140609%2F1-1406091P520.jpg")
        val titles = ArrayList<String>()
        titles.add("title1")
        titles.add("")
        titles.add("title3title3title3title3title3")
        titles.add("")
        titles.add("title4")
        val btn = findViewById<Button>(R.id.btn)
        btn.setOnClickListener {
            ImageZoomUtil.show<DemoImagePagerActivity>(this, urls[0], urls, titles);
        }
    }
}