### **亮点**

一行代码实现网络图片、本地图片的缩放显示，支持长按保存到本地相册操作，适配6.0权限。

### github项目地址地址
[欢迎star、fork ,https://github.com/crazyandcoder/ImageZoom](https://github.com/crazyandcoder/ImageZoom)

### **效果演示及apk下载地址**

#### **下载地址及二维码**
[apk演示下载地址](https://fir.im/1ane?release_id=5a66ec6d959d6902df4a7925)

![](http://img.blog.csdn.net/20180123160537226?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvbGlqaV94Yw==/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast)

#### **预览效果**


![图集功能封装.gif](http://upload-images.jianshu.io/upload_images/676457-4b5954510344f256.gif?imageMogr2/auto-orient/strip)


**长按保存到本地相册，已经适配6.0权限**

 ![](http://img.blog.csdn.net/20180123160913549?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvbGlqaV94Yw==/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast)



### gradle 引用


```
dependencies {
  compile 'liji.library.dev:imagezoom:1.3.0'
}
```

### maven使用

```
<dependency>
  <groupId>liji.library.dev</groupId>
  <artifactId>imagezoom</artifactId>
  <version>1.3.0</version>
  <type>pom</type>
</dependency>
```

### **使用方法**

 

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


> 3、public static void show(Context context, String url) 

```
	/**
     * 跳转到图片预览页面
     *
     * @param context
     * @param url    图片URL
     */
```

>  4、public static void show(Context context, String url, int type)

```
	/**
     * 跳转到大图预览，只有一张图
     * @param context
     * @param url
     * @param type 包含加载本地图片的功能
     */

//type取值：
public class ImageUrlType {
    /**
     * 本地图片
     */
    public static final int LOCAL = 0;

    /**
     * drawable下面的图片
     */
    public static final int DRAWABLE=1;


}

//使用demo如加载drawable目录下面的图
 ImageZoom.show(this, "R.drawable.iclauncher", ImageUrlType.DRAWABLE);

```

### 更新说明

#### **V1.3.0版本更新内容（2018.01.23）**
 1. 新增加载本地图片的功能
 2. 新增加载drawable目录下面的图片的功能
 3. 新增长按图片保存图片到本地相册的功能。
 4. 适配6.0权限


