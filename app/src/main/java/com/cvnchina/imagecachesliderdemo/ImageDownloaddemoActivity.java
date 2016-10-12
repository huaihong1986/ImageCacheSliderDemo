package com.cvnchina.imagecachesliderdemo;

import android.app.Activity;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.ImageView;

import java.io.IOException;
import java.net.URL;

/**
 * Created by huaihong on 2016/8/30.
 */
public class ImageDownloaddemoActivity extends Activity {
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.item);         /*FMU*/
        mImageView = (ImageView) findViewById(R.id.imageView);
        imageCacheManager = new ImageCacheManager();
        new DownloadTask().execute("http://10.9.200.11:8011//cloud/advert/1471316456438.png");
    }

    private class DownloadTask extends AsyncTask<String, Void, Bitmap> {
        @Override
        protected Bitmap doInBackground(String... params) {
                return imageCacheManager.downloadImage(params[0]);
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

    private ImageView mImageView;
    private ImageCacheManager imageCacheManager;
}
