package com.zapantha.game.Misc;

/**
 * Created by sams on 12/20/2017.
 */

public class Love{
    private int lovePoints;
    public Love(){
        lovePoints = 0;
    }
    public void addLove(){
        lovePoints++;
    }
    public void minusLove(){
        lovePoints--;
    }
    public int get(){
        return lovePoints;
    }
}
