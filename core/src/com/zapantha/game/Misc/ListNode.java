package com.zapantha.game.Misc;

/**
 * Created by sams on 12/20/2017.
 */

public class ListNode{
    public ListNode A;
    public ListNode B;
    public String data;
    public ListNode previous;

    public ListNode(String data){
        A = null;
        B = null;
        this.data = data;
        previous = null;
    }
    public ListNode(String data, ListNode A, ListNode B, ListNode Previous){
        this.A = A;
        this.B = B;
        this.data = data;
        this.previous = Previous;
    }
}
