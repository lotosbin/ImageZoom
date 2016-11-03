package com.liji.demo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.liji.imagezoom.activity.ImagePagerActivity;
import com.liji.imagezoom.util.ImageZoom;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final String[] urls = new String[] {
                "http://imgsrc.baidu.com/forum/w%3d580/sign=95035e68d70735fa91f04eb1ae510f9f/8af78a13632762d0c91858cca3ec08fa513dc6be.jpg",
                "http://h.hiphotos.baidu.com/zhidao/pic/item/0823dd54564e9258469ab81a9e82d158cdbf4e93.jpg",
                "http://img.25pp.com/uploadfile/bizhi/ipad3/2015/0120/20150120090811863_ipadm.jpg",
                "http://b.zol-img.com.cn/sjbizhi/images/2/320x510/1352891767829.jpg",
                "http://image.tianjimedia.com/uploadImages/2012/289/71X94T2PF22Z.jpg",
                "http://img.pconline.com.cn/images/upload/upc/tx/wallpaper/1306/30/c1/22775100_1372595982522_320x480.jpg",
                "http://b.zol-img.com.cn/sjbizhi/images/5/320x510/1372924333667.jpg",
                "http://5.66825.com/download/pic/000/330/7ab6bda65d3355ac6b53eab8eb3210c1.jpg",
                "http://image.tianjimedia.com/uploadImages/2014/069/XXQR67MY1RAR.jpg", };
        
        final List<String> list = new ArrayList<>();
        for (int i = 0; i < urls.length; i++) {
            list.add(urls[i]);
        }
        
        Button btn = (Button) findViewById(R.id.btn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ImageZoom.show(MainActivity.this, urls[1], list);
            }
        });
        
    }
}
