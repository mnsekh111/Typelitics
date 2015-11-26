package com.mns.typelytics.dummy;

/**
 * Created by mns on 11/20/15.
 */
public class DummyStat {
    public int wpm;
    public int pos;
    public int acc;

    public DummyStat(){
        wpm = 0;
        pos = DummyRaceStats.MAX_RACERS;
        acc = 0;
    }

    public DummyStat(int wpm, int pos, int acc) {
        this.wpm = wpm;
        this.pos = pos;
        this.acc = acc;
    }
}
