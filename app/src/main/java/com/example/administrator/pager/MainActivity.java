package com.example.administrator.pager;

import android.app.Activity;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;

import java.util.ArrayList;

public class MainActivity extends Activity implements ViewPager.OnPageChangeListener {
    private ViewPager viewPager;//页卡内容
    private ImageView imageView;//动画图片
    private int currentIndex = 0;//当前页卡编号
    private int animationSpeed;//动画速度
    private int offset;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        InitTextView(new int[]{R.id.text1, R.id.text2, R.id.text3});
        InitImageView(R.drawable.a, 350);
        InitViewPager(new int[]{R.layout.lay1, R.layout.lay2, R.layout.lay3}, 0);
    }

    /**
     * 初始化头标
     */
    private void InitTextView(int[] textViews) {
        for (int i = 0; i < textViews.length; i++)
            findViewById(textViews[i]).setOnClickListener(new MyClickListener(i));
    }

    /**
     * 初始化动画
     */
    private void InitImageView(int imgRes, int animationSpeed) {
        this.animationSpeed = animationSpeed;
        imageView = (ImageView) findViewById(R.id.imgView);
        int imgWidth = BitmapFactory.decodeResource(getResources(), imgRes)
                .getWidth();

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        int screenW = dm.widthPixels;// 获取分辨率宽度
        int basicOffset = (screenW / 3 - imgWidth) / 2;
        offset = basicOffset * 2 + imgWidth;

        Matrix matrix = new Matrix();
        matrix.postTranslate(basicOffset, 0);// 设置动画初始位置
        imageView.setImageMatrix(matrix);
    }

    /**
     * 初始化ViewPager
     */
    private void InitViewPager(int[] pageLayouts, int defaultPage) {
        viewPager = (ViewPager) findViewById(R.id.pager);
        ArrayList<View> listViews = new ArrayList<>();
        LayoutInflater mInflater = getLayoutInflater();
        for (int pageLayout : pageLayouts) listViews.add(mInflater.inflate(pageLayout, null));
        viewPager.setAdapter(new MyPagerAdapter(listViews));
        viewPager.setCurrentItem(defaultPage);
        viewPager.setOnPageChangeListener(this);
    }

    @Override
    public void onPageSelected(int i) {
        Animation animation = new TranslateAnimation(currentIndex * offset,
                i * offset, 0, 0);
        animation.setFillAfter(true);// True:图片停在动画结束位置
        animation.setDuration(Math.abs(i - currentIndex) * animationSpeed);
        currentIndex = i;
        imageView.startAnimation(animation);
    }

    @Override
    public void onPageScrolled(int i, float v, int i2) {

    }


    @Override
    public void onPageScrollStateChanged(int i) {

    }

    /**
     * 头标点击监听
     */
    class MyClickListener implements View.OnClickListener {
        private int index;

        public MyClickListener(int i) {
            index = i;
        }

        @Override
        public void onClick(View v) {
            viewPager.setCurrentItem(index);
        }
    }
}