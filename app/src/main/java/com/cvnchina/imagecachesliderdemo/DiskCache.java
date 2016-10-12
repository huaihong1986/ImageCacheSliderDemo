package com.cvnchina.imagecachesliderdemo;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * Created by huaihong on 2016/10/11.
 */
public class DiskCache implements ImageCache{
    private static final String TAG ="DiskCache";
    // 为了简单起见临时写个路径,在开发中请避免这种写法 !
    static String cacheDir = "sdcard/cache/";
    // 从缓存中获取图片
    public Bitmap get(String url) {
        try {
            return BitmapFactory.decodeFile(cacheDir + URLEncoder.encode(url,"UTF-8").replace("+", "%20"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        //url.substring(url.lastIndexOf("/")+1).trim()
        return null;
    }
    public  File getFilePath(String filePath,
                             String fileName) {
        File file = null;
        makeRootDirectory(filePath);
        try {
            file = new File(filePath + fileName);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return file;
    }

    public static void makeRootDirectory(String filePath) {
        File file = null;
        try {
            file = new File(filePath);
            if (!file.exists()) {
                file.mkdir();
            }
        } catch (Exception e) {

        }
    }
    // 将图片缓存到内存中
    public  void  put(String url, Bitmap bmp) {
        FileOutputStream fileOutputStream = null;
//        try {
//            String tempstr= URLEncoder.encode(url,"UTF-8").replace("+", "%20");
//            Log.e(TAG,"data="+tempstr);
//            String restorestr = URLDecoder.decode(tempstr,"UTF-8").replace("%20", "+");
//            Log.e(TAG,"data="+restorestr);
//        } catch (UnsupportedEncodingException e) {
//            e.printStackTrace();
//        }

        try {
            fileOutputStream = new
                    FileOutputStream(getFilePath(cacheDir,URLEncoder.encode(url,"UTF-8").replace("+", "%20")));
            bmp.compress(Bitmap.CompressFormat.PNG,
                    100, fileOutputStream);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } finally {
//            if (fileOutputStream != null) {
//                try {
//                    fileOutputStream.close();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
            CloseUtils.closeQuietly(fileOutputStream);
        }
    }
}
