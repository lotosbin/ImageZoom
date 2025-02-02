package com.liji.imagezoom.activity;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.liji.imagezoom.R;
import com.liji.imagezoom.util.BottomMenuDialog;
import com.liji.imagezoom.widget.PhotoViewAttacher;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.listener.SimpleImageLoadingListener;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class ImageDetailFragment extends Fragment {
    private String mImageUrl;
    
    private ImageView mImageView;
    
    private ProgressBar progressBar;
    
    private PhotoViewAttacher mAttacher;
    
    private Bitmap mDownloadImgBitmap = null;
    
    final int REQUEST_WRITE = 1001;//申请权限的请求码
    
    @Override
    public void onDestroy() {
        super.onDestroy();
        //        if (mHandler != null) {
        //            mHandler.removeCallbacksAndMessages(null);
        //        }
    }
    
    public Bitmap getDownloadImgBitmap() {
        return mDownloadImgBitmap;
    }
    
    public void setDownloadImgBitmap(Bitmap downloadImgBitmap) {
        mDownloadImgBitmap = downloadImgBitmap;
    }

    public static ImageDetailFragment newInstance(String imageUrl, Boolean enable_download, Boolean useDoubleTap, Boolean useSingleTopToExit) {
        final ImageDetailFragment f = new ImageDetailFragment();
        
        final Bundle args = new Bundle();
        args.putString("url", imageUrl);
        args.putBoolean("enable_download", enable_download);
        args.putBoolean("useDoubleTap", useDoubleTap);
        args.putBoolean("useSingleTopToExit", useSingleTopToExit);
        f.setArguments(args);
        
        return f;
    }
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mImageUrl = getArguments() != null ? getArguments().getString("url") : null;
        
    }
    
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View v = inflater.inflate(R.layout.fragment_image_detail, container, false);
        mImageView = (ImageView) v.findViewById(R.id.image);
        mAttacher = new PhotoViewAttacher(mImageView);
        Bundle arguments = this.getArguments();
        if (arguments.getBoolean("useSingleTopToExit", true)) {
            //单击退出页面
            mAttacher.setOnPhotoTapListener(new PhotoViewAttacher.OnPhotoTapListener() {

                @Override
                public void onPhotoTap(View arg0, float arg1, float arg2) {
                    getActivity().finish();
                }
            });
        }
        if (arguments.getBoolean("useDoubleTap", true)) {
            mAttacher.setOnDoubleTapListener(null);
        } else {
            mAttacher.setOnDoubleTapListener(new GestureDetector.OnDoubleTapListener() {
                @Override
                public boolean onSingleTapConfirmed(MotionEvent e) {
                    return false;
                }

                @Override
                public boolean onDoubleTap(MotionEvent e) {
                    return false;
                }

                @Override
                public boolean onDoubleTapEvent(MotionEvent e) {
                    return false;
                }
            });
        }
        //长按保存到本地相册
        if (arguments.getBoolean("enable_download", true)) {
            mAttacher.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {

                    final BottomMenuDialog dialog = new BottomMenuDialog.Builder()

                            .addItem("保存到本地相册", new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    checkPermission();

                                }
                            })
                            .addItem("取消", new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    Context context = getContext();
                                    if (context != null) {
                                        Toast.makeText(context, "取消", Toast.LENGTH_LONG).show();
                                    }
                                }
                            })

                            .build();
                    dialog.show(getFragmentManager());

                    return true;
                }
            });
        }

        progressBar = (ProgressBar) v.findViewById(R.id.loading);
        return v;
    }
    
    private void checkPermission() {
        //判断是否6.0以上的手机   不是就不用
        if (Build.VERSION.SDK_INT >= 23) {
            //判断是否有这个权限
            if (ContextCompat.checkSelfPermission(getContext(),
                    Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                //2、申请权限: 参数二：权限的数组；参数三：请求码
                requestPermissions(new String[] { Manifest.permission.WRITE_EXTERNAL_STORAGE }, REQUEST_WRITE);
            }
            else {
                saveImageToGallery(getContext(), getDownloadImgBitmap());
            }
        }
        else {
            saveImageToGallery(getContext(), getDownloadImgBitmap());
        }
    }
    
    //判断授权的方法  授权成功直接调用写入方法  这是监听的回调
    //参数  上下文   授权结果的数组   申请授权的数组
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
            @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        Context context = getContext();
        if (requestCode == REQUEST_WRITE && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            saveImageToGallery(context, getDownloadImgBitmap());
        } else {
            if (context != null) {
                Toast.makeText(context, "已拒绝SD卡读写操作，无法保存照片到本地", Toast.LENGTH_LONG).show();
            }
        }
        
    }
    
    /**
     * 保存到本地相册
     * @param context
     * @param bmp
     */
    public void saveImageToGallery(Context context, Bitmap bmp) {
        Log.d("ZoomImage", "saveImageToGallery:" + bmp);
        final String SAVE_PIC_PATH = Environment.getExternalStorageState().equalsIgnoreCase(Environment.MEDIA_MOUNTED)
                ? Environment.getExternalStorageDirectory().getAbsolutePath()
                : "/mnt/sdcard";//保存到SD卡

        // 首先保存图片
        File appDir = new File(SAVE_PIC_PATH + "/ZoomImage/");
        if (!appDir.exists()) {
            appDir.mkdir();
        }

        long nowSystemTime = System.currentTimeMillis();
        String fileName = nowSystemTime + ".png";
        File file = new File(appDir, fileName);
        try {
            if (!file.exists()) {
                file.createNewFile();
            }
            FileOutputStream fos = new FileOutputStream(file);
            bmp.compress(Bitmap.CompressFormat.JPEG, 100, fos);
            fos.flush();
            fos.close();
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        catch (IOException e) {
            e.printStackTrace();
        }

        //保存图片后发送广播通知更新数据库
        Uri uri = Uri.fromFile(file);
        context.sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, uri));


//        // 其次把文件插入到系统图库
//        try {
//            MediaStore.Images.Media.insertImage(context.getContentResolver(), file.getAbsolutePath(), fileName, null);
//        }
//        catch (FileNotFoundException e) {
//            e.printStackTrace();
//        }
//        // 最后通知图库更新
//        context.sendBroadcast(
//                new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, Uri.parse("file://" + file.getAbsolutePath())));
        Context context1 = getContext();
        if (context1 != null) {
            Toast.makeText(context1, "已保存到本地相册", Toast.LENGTH_LONG).show();
        }
    }
    
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        
        ImageLoader.getInstance().displayImage(mImageUrl, mImageView, new SimpleImageLoadingListener() {
            @Override
            public void onLoadingStarted(String imageUri, View view) {
                progressBar.setVisibility(View.VISIBLE);
            }
            
            @Override
            public void onLoadingFailed(String imageUri, View view, FailReason failReason) {
                String message;
                switch (failReason.getType()) {
                    case IO_ERROR:
                        message = "下载错误";
                        break;
                    case DECODING_ERROR:
                        message = "图片无法显示";
                        break;
                    case NETWORK_DENIED:
                        message = "网络有问题，无法下载";
                        break;
                    case OUT_OF_MEMORY:
                        message = "图片太大无法显示";
                        break;
                    default:
                        message = "未知的错误";
                        break;
                }
                FragmentActivity activity = getActivity();
                if (activity != null) {
                    Toast.makeText(activity, message, Toast.LENGTH_SHORT).show();
                }
                progressBar.setVisibility(View.GONE);
            }
            
            @Override
            public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {
                progressBar.setVisibility(View.GONE);
                setDownloadImgBitmap(loadedImage);
                //                Log.d("ZoomImage", "onLoadingComplete:" + loadedImage);
                //                Message message = mHandler.obtainMessage();
                //                message.what = 1;
                //                message.obj = loadedImage;
                //                mHandler.sendMessage(message);
                mAttacher.update();
            }
        });
        
    }
    
}
