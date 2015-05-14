package com.example.administrator.pager;

import android.app.Activity;
import android.graphics.BitmapFactory;
import android.util.DisplayMetrics;

/**
 * Created by HYL on 2015/4/24.
 */
public class MyUtil {
    public static int getScreenWidth(Activity a) {
        DisplayMetrics dm = new DisplayMetrics();
        a.getWindowManager().getDefaultDisplay().getMetrics(dm);
        return dm.widthPixels;// 获取分辨率宽度
    }

    public static int getImgWidth(Activity a, int imgRes) {
        return BitmapFactory.decodeResource(a.getResources(), imgRes)
                .getWidth();
    }
}
