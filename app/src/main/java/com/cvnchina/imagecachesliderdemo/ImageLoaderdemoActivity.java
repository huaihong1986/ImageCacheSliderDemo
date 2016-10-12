package com.cvnchina.imagecachesliderdemo;

import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.widget.ImageView;

/**
 * Created by huaihong on 2016/8/30.
 */
public class ImageLoaderdemoActivity extends Activity {
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.item);         /*FMU*/
        mImageView = (ImageView) findViewById(R.id.imageView);
        ImageLoader imageLoader = new ImageLoader() ;
//        // 使用内存缓存
//        imageLoader.setImageCache(new MemoryCache());
//        // 使用SD卡缓存
//        imageLoader.setImageCache(new DiskCache());
        // 使用双缓存
        imageLoader.setImageCache(new DoubleCache());
        imageLoader.displayImage("http://10.9.200.11:8011//cloud/advert/1471316456438.png",mImageView);

        // 使用自定义的图片缓存实现
//        imageLoader.setImageCache(new ImageCache() {
//
//            @Override
//            public void put(String url, Bitmap bmp) {
//                // 缓存图片
//            }
//
//            @Override
//            public Bitmap get(String url) {
//                return null/*从缓存中获取图片*/;
//            }
//        });

    }

    private ImageView mImageView;

}
