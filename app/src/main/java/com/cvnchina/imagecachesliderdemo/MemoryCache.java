package com.cvnchina.imagecachesliderdemo;

import android.graphics.Bitmap;
import android.util.LruCache;

/**
 * Created by huaihong on 2016/10/11.
 */
// 内存缓存MemoryCache类
public class MemoryCache implements ImageCache {
    private LruCache<String, Bitmap> mMemeryCache;

    public MemoryCache() {
        initImageCache();
    }
    private void initImageCache() {
        // 计算可使用的最大内存
        final int maxMemory = (int) (Runtime.getRuntime().maxMemory() / 1024);
        // 取四分之一的可用内存作为缓存
        final int cacheSize = maxMemory / 4;
        mMemeryCache = new LruCache<String, Bitmap>(cacheSize) {
            @Override
            protected int sizeOf(String key, Bitmap bitmap) {
                return bitmap.getRowBytes() *
                        bitmap.getHeight() / 1024;
            }
        };
    }

    @Override
    public Bitmap get(String url) {
        return mMemeryCache.get(url);
    }

    @Override
    public void put(String url, Bitmap bmp) {
        mMemeryCache.put(url, bmp);
    }
}
