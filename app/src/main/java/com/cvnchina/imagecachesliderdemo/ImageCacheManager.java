package com.cvnchina.imagecachesliderdemo;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.entity.BufferedHttpEntity;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by huaihong on 2016/10/11.
 */

public class ImageCacheManager {
    public Bitmap downloadImage(String url) {
        Bitmap bitmap = null;
        HttpGet httpRequest = new HttpGet(url);
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
        return bitmap;
    }
}
