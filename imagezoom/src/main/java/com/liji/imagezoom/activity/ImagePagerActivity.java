package com.liji.imagezoom.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.widget.TextView;

import com.liji.imagezoom.R;
import com.liji.imagezoom.widget.HackyViewPager;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import java.util.ArrayList;
import java.util.List;

public class ImagePagerActivity extends ImagePagerActivity2 {
    protected static final String STATE_POSITION = "STATE_POSITION";
    public static final String EXTRA_IMAGE_INDEX = "image_index";
    public static final String EXTRA_IMAGE_URLS = "image_urls";
    public static final String EXTRA_IMAGE_TITLES = "image_titles";

    protected HackyViewPager mPager;
    protected int pagerPosition;
    protected TextView indicator;

    protected List<String> urlists = new ArrayList<>();
    protected boolean enable_download;
    protected boolean useDoubleTap;
    protected boolean useSingleTopToExit;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        enable_download = ImagePagerActivity.this.getIntent().getBooleanExtra("enable_download", true);
        useDoubleTap = ImagePagerActivity.this.getIntent().getBooleanExtra("useDoubleTap", true);
        useDoubleTap = ImagePagerActivity.this.getIntent().getBooleanExtra("useSingleTopToExit", true);
        DisplayImageOptions defaultOptions = new DisplayImageOptions
                .Builder()
                .showImageForEmptyUri(R.drawable.empty_photo)
                .showImageOnFail(R.drawable.empty_photo)
                .cacheInMemory(true)
                .cacheOnDisc(true)
                .build();
        ImageLoaderConfiguration config = new ImageLoaderConfiguration
                .Builder(getApplicationContext())
                .defaultDisplayImageOptions(defaultOptions)
                .discCacheSize(50 * 1024 * 1024)//
                .discCacheFileCount(100)//缓存一百张图片
                .writeDebugLogs()
                .build();
        ImageLoader.getInstance().init(config);


        pagerPosition = getIntent().getIntExtra(EXTRA_IMAGE_INDEX, 0);
        urlists = this.getIntent().getStringArrayListExtra(EXTRA_IMAGE_URLS);
        List<String> list = this.getIntent().getStringArrayListExtra(EXTRA_IMAGE_TITLES);
        if (list != null) {
            this.titleList = list;
        }
        mPager = (HackyViewPager) findViewById(R.id.pager);
        ImagePagerAdapter mAdapter = new ImagePagerAdapter(getSupportFragmentManager(), urlists);
        mPager.setAdapter(mAdapter);
        indicator = (TextView) findViewById(R.id.indicator);

        CharSequence text = getString(R.string.viewpager_indicator, 1, mPager.getAdapter().getCount());
        indicator.setText(text);

        // 更新下标
        final OnPageChangeListener onPageChangeListener = new OnPageChangeListener() {

            @Override
            public void onPageScrollStateChanged(int arg0) {
            }

            @Override
            public void onPageScrolled(int arg0, float arg1, int arg2) {
            }

            @Override
            public void onPageSelected(int position) {
                ImagePagerActivity.this.onPageSelected(position);
            }

        };
        mPager.setOnPageChangeListener(onPageChangeListener);
        if (savedInstanceState != null) {
            pagerPosition = savedInstanceState.getInt(STATE_POSITION);
        }

        mPager.setCurrentItem(pagerPosition);
        mPager.post(new Runnable() {
            @Override
            public void run() {
                onPageChangeListener.onPageSelected(mPager.getCurrentItem());
            }
        });
    }

    @Override
    protected void onPageSelected(int position) {
        CharSequence text = getString(R.string.viewpager_indicator, position + 1, mPager.getAdapter().getCount());
        indicator.setText(text);
        super.onPageSelected(position);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putInt(STATE_POSITION, mPager.getCurrentItem());
        super.onSaveInstanceState(outState);
    }

    private class ImagePagerAdapter extends FragmentStatePagerAdapter {

        public List<String> fileList;

        public ImagePagerAdapter(FragmentManager fm, List<String> fileList) {
            super(fm);
            this.fileList = fileList;
        }

        @Override
        public int getCount() {
            return fileList == null ? 0 : fileList.size();
        }

        @Override
        public Fragment getItem(int position) {
            String url = fileList.get(position);
            return ImageDetailFragment.newInstance(url, enable_download, useDoubleTap, useSingleTopToExit);
        }

    }
}