package com.cvnchina.imagecachesliderdemo;

import android.graphics.Bitmap;

/**
 * Created by huaihong on 2016/10/11.
 */

public interface ImageCache {
    public Bitmap get(String url);
    public void put(String url, Bitmap bmp);
}
