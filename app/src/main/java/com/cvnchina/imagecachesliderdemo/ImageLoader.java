package com.cvnchina.imagecachesliderdemo;

/**
 * Created by huaihong on 2016/10/11.
 */

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Environment;
import android.widget.ImageView;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.entity.BufferedHttpEntity;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import android.os.Handler;

/**
 * 图片加载类
 */
public class ImageLoader {
    // 图片缓存
    ImageCache mImageCache = new MemoryCache();
    ImageView mImageView;


    // 注入缓存实现
    public void setImageCache(ImageCache cache) {
        mImageCache = cache;
    }

    public void displayImage(String imageUrl, ImageView imageView) {
        mImageView = imageView;
        mImageView.setTag(imageUrl);
        Bitmap bitmap = mImageCache.get(imageUrl);
        if (bitmap != null) {
            imageView.setImageBitmap(bitmap);
            return;
        }else {
            new DownloadTask().execute(imageUrl);
        }

    }
    private class DownloadTask extends AsyncTask<String, Void, Bitmap> {
        @Override
        protected Bitmap doInBackground(String... params) {

            return downloadImage(params[0]);

        }

        @Override
        protected void onPostExecute(Bitmap result) {
            mImageView.setImageBitmap(result);
            super.onPostExecute(result);
        }

        @Override
        protected void onPreExecute() {
            mImageView.setImageResource(R.mipmap.ic_launcher);
            super.onPreExecute();
        }
    }
    private  Bitmap downloadImage(String imageUrl) {

        Bitmap bitmap = null;
        HttpGet httpRequest = new HttpGet(String.valueOf(imageUrl));
        HttpClient httpclient = new DefaultHttpClient();
        HttpResponse response = null;
        try {
            response = (HttpResponse) httpclient.execute(httpRequest);
        } catch (IOException e) {
            e.printStackTrace();
        }
        HttpEntity entity = response.getEntity();
        BufferedHttpEntity bufferedHttpEntity = null;
        try {
            bufferedHttpEntity = new BufferedHttpEntity(entity);
        } catch (IOException e) {
            e.printStackTrace();
        }
        InputStream is = null;
        try {
            is = bufferedHttpEntity.getContent();
        } catch (IOException e) {
            e.printStackTrace();
        }
        bitmap = BitmapFactory.decodeStream(is);
        mImageCache.put(imageUrl,bitmap);
        return bitmap;
    }
}
