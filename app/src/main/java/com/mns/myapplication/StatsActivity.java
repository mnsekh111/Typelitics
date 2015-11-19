package com.mns.myapplication;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import com.mns.myapplication.fragments.Stats1Fragment;

import java.util.ArrayList;
import java.util.List;

import github.chenupt.multiplemodel.viewpager.ModelPagerAdapter;
import github.chenupt.multiplemodel.viewpager.PagerModelManager;
import github.chenupt.springindicator.SpringIndicator;
import github.chenupt.springindicator.viewpager.ScrollerViewPager;

public class StatsActivity extends FragmentActivity {


    ScrollerViewPager viewPager;
    List list1,list2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stats);

        list1 = new ArrayList();
        list1.add("SHIT");
        list1.add("SHIT");
        list1.add("SHIT");


        list2 = new ArrayList();
        list2.add("SHIT");
        list2.add("SHIT");
        list2.add("SHIT");

        viewPager = (ScrollerViewPager) findViewById(R.id.view_pager);
        SpringIndicator springIndicator = (SpringIndicator) findViewById(R.id.indicator);

        PagerModelManager manager = new PagerModelManager();
        manager.addCommonFragment(Stats1Fragment.class, list1, list2);
        ModelPagerAdapter adapter = new ModelPagerAdapter(getSupportFragmentManager(), manager);
        viewPager.setAdapter(adapter);
        viewPager.fixScrollSpeed();

        springIndicator.setViewPager(viewPager);

    }
}
