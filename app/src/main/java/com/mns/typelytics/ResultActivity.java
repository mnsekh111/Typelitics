package com.mns.typelytics;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.mns.typelytics.dummy.DummyRaceStats;
import com.mns.typelytics.dummy.DummyStat;
import com.mns.typelytics.dummy.DummyUser;
import com.squareup.picasso.Picasso;

import java.util.Map;

import de.hdodenhof.circleimageview.CircleImageView;

public class ResultActivity extends Activity {

    int participants = -1;
    int gameId = -1;
    Button btnNewRace;
    private LinearLayout llResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent i = getIntent();
        gameId = i.getIntExtra("gameid", 2);
        setContentView(R.layout.activity_result);
        initViews();

    }

    private void initViews() {

        try {
            llResult = (LinearLayout) findViewById(R.id.llResult);

            //Log.i("RESULT",""+DummyRaceStats.getList().size());
            Map<DummyUser, DummyStat> racers = DummyRaceStats.getList().get(gameId).racers;

            for (DummyUser user : racers.keySet()) {
                View view = ((LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE)).inflate(R.layout.template_user_race_stats, null);
                LinearLayout l = (LinearLayout) view;
                llResult.addView(l);
                TextView tvWPM = (TextView) l.findViewById(R.id.tvWPM);
                TextView tvPos = (TextView) l.findViewById(R.id.tvPosition);
                TextView tvAcc = (TextView) l.findViewById(R.id.tvAcc);

                CircleImageView civPP = (CircleImageView) l.findViewById(R.id.profile_image);
                ImageView ivWPMUpDown = (ImageView) l.findViewById(R.id.ivWPMUpDown);
                ImageView ivPosUpDown = (ImageView) l.findViewById(R.id.ivPositionUpDown);
                ImageView ivAccUpDown = (ImageView) l.findViewById(R.id.ivAccUpDown);

                civPP.setVisibility(View.VISIBLE);
                ivWPMUpDown.setVisibility(View.VISIBLE);
                ivPosUpDown.setVisibility(View.VISIBLE);
                ivAccUpDown.setVisibility(View.VISIBLE);

                tvWPM.setText("" + racers.get(user).wpm);
                tvPos.setText("" + racers.get(user).pos);
                tvAcc.setText("" + racers.get(user).acc);

                Picasso.with(this).load(user.profilePic).into(civPP);


                if (user.getAvgAcc() > racers.get(user).wpm)
                    ivWPMUpDown.setImageResource(R.drawable.arrow_down);
                if (user.getAvgAcc() > racers.get(user).pos)
                    ivPosUpDown.setImageResource(R.drawable.arrow_down);
                if (user.getAvgAcc() > racers.get(user).acc)
                    ivAccUpDown.setImageResource(R.drawable.arrow_down);
            }
        } catch (NullPointerException ne) {
            ne.printStackTrace();
        }

        btnNewRace = new Button(this);
        btnNewRace.setText(getResources().getText(R.string.new_race));
        btnNewRace.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ResultActivity.this.finish();
                startActivity(new Intent(getBaseContext(), RaceActivity.class));
            }
        });

        llResult.addView(btnNewRace);
    }

}
