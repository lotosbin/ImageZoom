基本上，现在的每个APP都会涉及到图片的展示以及相关的操作，如放大、缩小，平移等操作，常规做法是找一些第三方的图片显示控件进行使用，自己再重复造轮子的话，代价成本太大，所以今天来对图片进行简单的封装，让其能够支持现在的项目需求。

### github项目地址地址
[欢迎star、fork ,https://github.com/crazyandcoder/ImageZoom](https://github.com/crazyandcoder/ImageZoom)

### 效果演示

![扫描二维码下载APP演示.png](http://upload-images.jianshu.io/upload_images/676457-e2d3beec47193f5e.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)


![图集功能封装.gif](http://upload-images.jianshu.io/upload_images/676457-4b5954510344f256.gif?imageMogr2/auto-orient/strip)

### gradle 引用


```
dependencies {
  compile 'liji.library.dev:imagezoom:1.2.1'
}
```

### maven使用

```
<dependency>
  <groupId>liji.library.dev</groupId>
  <artifactId>imagezoom</artifactId>
  <version>1.2.1</version>
  <type>pom</type>
</dependency>
```

### 使用方法

 

```
		//数据源
		final String[] urls = new String[] {
                "http://b.zol-img.com.cn/sjbizhi/images/2/320x510/1352891767829.jpg",
                "http://image.tianjimedia.com/uploadImages/2012/289/71X94T2PF22Z.jpg", 
                "http://b.zol-img.com.cn/sjbizhi/images/5/320x510/1372924333667.jpg",
                "http://image.tianjimedia.com/uploadImages/2014/069/XXQR67MY1RAR.jpg", 
        };

		//添加list数据	
		final List<String> list = new ArrayList<>();
        for (int i = 0; i < urls.length; i++) {
            list.add(urls[i]);
        }
        
        //打开图集
        Button btn = (Button) findViewById(R.id.btn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ImageZoom.show(MainActivity.this, urls[1], list);
            }
        });
```

 
#### 方法简介

> 1、public static void show(Context context, String url, List<String> list)

```
	/**
     * 跳转到图片预览页面
     *
     * @param context
     * @param url     当前图片url
     * @param list    图片URL
     */
```

> 2、public static void show(Context context, int positon, List<String> list) 

```
	/**
     * 跳转到图片预览页面
     *
     * @param context
     * @param positon 图片显示的页码
     * @param list    图片URL
     */
```


**关于作者**

我的博客：http://crazyandcoder.github.io/
我的github: https://github.com/crazyandcoder
简书号：http://www.jianshu.com/users/18281bdb07ce/latest_articles
