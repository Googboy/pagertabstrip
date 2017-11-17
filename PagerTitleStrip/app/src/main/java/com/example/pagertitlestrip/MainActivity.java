package com.example.pagertitlestrip;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.PagerTabStrip;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.text.style.ImageSpan;
import android.text.style.RelativeSizeSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private View view_one,view_two,view_three;
    private List<View> viewList;
    private ViewPager viewPager;
    private List<String> titleList;
    private PagerTabStrip pagerTab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //更改下划线颜色
        pagerTab = (PagerTabStrip) findViewById(R.id.pagerTab);
        pagerTab.setTabIndicatorColorResource(R.color.colorAccent);

        viewPager = (ViewPager) findViewById(R.id.viewpager);
        LayoutInflater inflater = getLayoutInflater();
        view_one = inflater.inflate(R.layout.view_one,null);
        view_two = inflater.inflate(R.layout.view_two,null);
        view_three = inflater.inflate(R.layout.view_three,null);

        viewList = new ArrayList<View>();
        viewList.add(view_one);
        viewList.add(view_two);
        viewList.add(view_three);

        titleList = new ArrayList<String>();
        titleList.add("提莫");
        titleList.add("亚索");
        titleList.add("德莱文");

        PagerAdapter pagerAdapter = new PagerAdapter() {
            @Override
            public int getCount() {
                return viewList.size();
            }

            @Override
            public boolean isViewFromObject(View view, Object object) {
                return view == object;
            }

            public void destroyItem(ViewGroup container,int position,Object object){
                container.removeView(viewList.get(position));
            }

//            @Override
//            public void destroyItem(View container, int position, Object object) {
//                super.destroyItem(container, position, object);
//            }

            public Object instantiateItem(ViewGroup container, int position){
                container.addView(viewList.get(position));
                return viewList.get(position);
            }

            public CharSequence getPageTitle(int position){
                SpannableStringBuilder ssb = new SpannableStringBuilder(" "+titleList.get(position));
                Drawable drawable = getResources().getDrawable(R.mipmap.ic_launcher);
                drawable.setBounds(0,0,drawable.getIntrinsicWidth(),drawable.getIntrinsicHeight());
                ImageSpan span = new ImageSpan(drawable,ImageSpan.ALIGN_BASELINE);
                ForegroundColorSpan fcs = new ForegroundColorSpan(Color.GREEN);//字体颜色设置为绿色
                ssb.setSpan(span,0,1, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);//设置图标
                ssb.setSpan(fcs,1,ssb.length(),Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);//设置字体颜色
                ssb.setSpan(new RelativeSizeSpan(1.2f),1,ssb.length(),Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                return ssb;
                //return titleList.get(position);这里是每更换下划线颜色之前设置的返回值
            }
        };
        viewPager.setAdapter(pagerAdapter);
    }
}
