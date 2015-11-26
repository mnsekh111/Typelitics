package com.mns.typelytics;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.TextView;

import com.mns.typelytics.dummy.DummyUser;
import com.mns.typelytics.fragments.Stats1Fragment;
import com.mns.typelytics.fragments.Stats2Fragment;
import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.CircleImageView;
import github.chenupt.multiplemodel.viewpager.ModelPagerAdapter;
import github.chenupt.multiplemodel.viewpager.PagerModelManager;
import github.chenupt.springindicator.SpringIndicator;
import github.chenupt.springindicator.viewpager.ScrollerViewPager;

public class StatsActivity extends FragmentActivity {

    int userId;
    private CircleImageView profile_image;
    private ScrollerViewPager viewPager;
    private TextView tvWPM,tvPos,tvAcc;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stats);
        Intent intent = getIntent();
        userId = intent.getIntExtra("id",0);

        profile_image = (CircleImageView)findViewById(R.id.profile_image);
        profile_image.setVisibility(View.VISIBLE);

        tvWPM = (TextView) findViewById(R.id.tvWPM);
        tvPos = (TextView) findViewById(R.id.tvPosition);
        tvAcc = (TextView) findViewById(R.id.tvAcc);

        setStatCard(userId);

        viewPager = (ScrollerViewPager) findViewById(R.id.view_pager);
        SpringIndicator springIndicator = (SpringIndicator) findViewById(R.id.indicator);

        PagerModelManager manager = new PagerModelManager();
        //manager.addCommonFragment(Stats1Fragment.class, list1, list2);
        manager.addFragment(new Stats1Fragment(),getResources().getString(R.string.statsWPMVsGame));
        manager.addFragment(new Stats2Fragment(),getResources().getString(R.string.statsPosVsGame));
        manager.addFragment(new Stats1Fragment(),getResources().getString(R.string.statsAccVsGame));
        ModelPagerAdapter adapter = new ModelPagerAdapter(getSupportFragmentManager(), manager);
        viewPager.setAdapter(adapter);
        viewPager.fixScrollSpeed();

        springIndicator.setViewPager(viewPager);

    }

    public void setStatCard(int id){
        DummyUser dm = DummyUser.getUser(id);
        if(dm!=null) {
            tvWPM.setText(""+dm.getAvgWPM());
            tvPos.setText(""+dm.getAvgPos());
            tvAcc.setText(""+dm.getAvgAcc());
            Picasso.with(this).load(dm.profilePic).into(profile_image);
        }
        getActionBar().setTitle(dm.name);
    }
}
