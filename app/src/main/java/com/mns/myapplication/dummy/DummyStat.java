package com.mns.myapplication.dummy;

import java.util.Random;

/**
 * Created by mns on 11/20/15.
 */
public class DummyStat {
    public int wpm;
    public int pos;
    public int acc;

    private Random random = new Random();

    public DummyStat(){
        wpm = random.nextInt(200);
        pos = random.nextInt(5)+1;
        acc = random.nextInt(100)+1;
    }
}
