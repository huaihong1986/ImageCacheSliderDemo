package com.cvnchina.imagecachesliderdemo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashMap;

import com.cvnchina.imageslider.Animations.DescriptionAnimation;
import com.cvnchina.imageslider.Indicators.PagerIndicator;
import com.cvnchina.imageslider.SliderLayout;
import com.cvnchina.imageslider.SliderTypes.BaseSliderView;
import com.cvnchina.imageslider.SliderTypes.TextSliderView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity implements BaseSliderView.OnSliderClickListener{
    private SliderLayout mImageSlider;
    private TextView mDownloadBtn;
    private TextView mCacheBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mImageSlider = (SliderLayout) this.findViewById(R.id.slider);
        mDownloadBtn = (TextView) this.findViewById(R.id.downloadbtn);
        mDownloadBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(MainActivity.this, ImageDownloaddemoActivity.class);
                startActivity(it);
            }
        });
        mCacheBtn = (TextView) this.findViewById(R.id.cachebtn);
        mCacheBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(MainActivity.this, ImageLoaderdemoActivity.class);
                startActivity(it);
            }
        });
        testData();
    }
    private void testData() {
        HashMap<String, String> map = new HashMap<>();
        map.put("1", "");
        map.put("2", "http://img0.imgtn.bdimg.com/it/u=1521443619,1923948459&fm=21&gp=0.jpg");
        map.put("3", "http://img2.imgtn.bdimg.com/it/u=1924459939,1757185993&fm=21&gp=0.jpg");
        map.put("4", "http://img2.imgtn.bdimg.com/it/u=3966690544,3724186196&fm=21&gp=0.jpg");
        map.put("5","http://10.9.200.11:8011//cloud/advert/1471312704484.jpg");
        map.put("8","http://10.9.200.11:8011//cloud/advert/1471316456438.png");
        initBannerView(map);
    }

    private void initBannerView(HashMap<String, String> url_maps) {
        for (String name : url_maps.keySet()) {
            TextSliderView textSliderView = new TextSliderView(this);
            // 初始化幻灯片页面
            //url_maps.get(name) 非空
            if(!TextUtils.isEmpty(url_maps.get(name)))
                textSliderView.description("").image(url_maps.get(name)).empty(R.mipmap.default_adv).error(R.mipmap.default_adv).setOnSliderClickListener(this);
            else textSliderView.description("").image(R.mipmap.default_adv).empty(R.mipmap.default_adv).error(R.mipmap.default_adv).setOnSliderClickListener(this);
            // 添加要传递的数据
            textSliderView.getBundle().putString("extra", name);
            mImageSlider.addSlider(textSliderView);
        }

        // 幻灯片切换方式
        mImageSlider.setPresetTransformer(SliderLayout.Transformer.Accordion);
        // 指示符位置
        mImageSlider.setPresetIndicator(SliderLayout.PresetIndicators.Center_Bottom);
        // 定义指示器样式
        // mDemoSlider.setCustomIndicator(your view);
        // 幻灯片循环
        // mDemoSlider.startAutoCycle();
        // 停止循环
        mImageSlider.stopAutoCycle();
        // 设置指示器的显示与否
        mImageSlider.setIndicatorVisibility(PagerIndicator.IndicatorVisibility.Visible);
        // 设置幻灯片的转化时间
        // mDemoSlider.setSliderTransformDuration(5000, null);
        // 用来自定义幻灯片标题的显示方式
        mImageSlider.setCustomAnimation(new DescriptionAnimation());
        // 幻灯片切换时间
        mImageSlider.setDuration(10000);
    }

    @Override
    public void onSliderClick(BaseSliderView slider) {
        Toast.makeText(MainActivity.this, "功能暂未开放敬请期待", Toast.LENGTH_SHORT).show();
    }


}
