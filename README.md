
### ImageZoom
图片缩放控件，能够查看大图，进行缩放功能。


### Download

use Gradle:

```
dependencies {
  compile 'liji.library.dev:imagezoom:1.1.1'
}
```

or Maven:

```
<dependency>
  <groupId>liji.library.dev</groupId>
  <artifactId>imagezoom</artifactId>
  <version>1.1.1</version>
  <type>pom</type>
</dependency>
```

### 使用方法

 1. 第一种方式 
 

```
	/**
     * 跳转到图片预览页面
     *
     * @param context
     * @param positon 图片显示的页码
     * @param list    所有图片URL
     */
ImageZoom.show(Context context, int positon, List<String> list);
```

2. 第二种方式

```
	/**
     * 跳转到图片预览页面
     *
     * @param context
     * @param url     当前图片url
     * @param list    所有图片URL
     */
ImageZoom.show(Context context, String url, List<String> list);
```


 

### 效果预览：
![](http://img.blog.csdn.net/20160720091901896)

![](http://img.blog.csdn.net/20160720091917881)

![](http://img.blog.csdn.net/20160720091926271)

![](http://img.blog.csdn.net/20160720091936037)


### 关于
1. 我的博客：[http://crazyandcoder.github.io/](http://crazyandcoder.github.io/)
2. 我的github: [https://github.com/crazyandcoder](https://github.com/crazyandcoder)


