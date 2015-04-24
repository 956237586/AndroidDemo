package com.example.administrator.pager;

import android.app.Activity;
import android.graphics.Matrix;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
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
        InitHead(R.drawable.a, 350, R.id.text1, R.id.text2, R.id.text3);
        InitViewPager(0, R.layout.lay1, R.layout.lay2, R.layout.lay3);
    }

    /**
     * 初始化头部
     */
    private void InitHead(int imgRes, int animationSpeed, int... textViews) {
        this.animationSpeed = animationSpeed;
        for (int i = 0; i < textViews.length; i++)
            findViewById(textViews[i]).setOnClickListener(new MyClickListener(i));

        imageView = (ImageView) findViewById(R.id.imgView);
        int imgWidth = MyUtil.getImgWidth(this, imgRes);

        int basicOffset = (MyUtil.getScreenWidth(this) / textViews.length - imgWidth) / 2;
        offset = basicOffset * 2 + imgWidth;

        Matrix matrix = new Matrix();
        matrix.postTranslate(basicOffset, 0);// 设置动画初始位置
        imageView.setImageMatrix(matrix);
    }

    /**
     * 初始化ViewPager
     */
    private void InitViewPager(int defaultPage, int... pageLayouts) {
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
        imageView.startAnimation(animation);
        currentIndex = i;
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