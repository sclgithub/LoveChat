package com.techscl.lovechat.base;

import android.graphics.Bitmap;
import android.util.LruCache;

import com.android.volley.toolbox.ImageLoader;

/**
 * Created by dllo on 15/8/5.
 */
public class MyImageLoader implements ImageLoader.ImageCache {
    private LruCache<String, Bitmap> cache;

    public MyImageLoader() {
        cache = new LruCache<String, Bitmap>(20 * 1024 * 1024) {
            @Override
            protected int sizeOf(String key, Bitmap bitmap) {
                return bitmap.getRowBytes() * bitmap.getHeight();
            }
        };
    }

    @Override
    public Bitmap getBitmap(String s) {
        return cache.get(s);
    }

    @Override
    public void putBitmap(String s, Bitmap bitmap) {
        cache.put(s,bitmap);
    }
}
