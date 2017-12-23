package com.zapantha.game.Misc;

/**
 * Created by sams on 12/20/2017.
 */

import java.io.*;
import java.util.*;
public class Tree{
    private ListNode front;
    private ListNode last;
    private int size;
    private ListNode previous;

    public Tree(){
        front = null;
        last = null;
        previous = null;
        size = 0;
    }
    public void add(String data, String choice){
        if(size == 0){
            ListNode current = new ListNode(data);
            front = current;
            last = front;
        }else if(choice.equalsIgnoreCase("A")){
            last.A = new ListNode(data,null,null,last);
        }else{
            last.B = new ListNode(data,null,null,last);
        }
        previous = last.previous;
        size++;
    }
    public void next(String choice){
        if(choice.equalsIgnoreCase("A"))
            last = last.A;
        else
            last = last.B;
    }

    public boolean reverse(){
        if(last == front)
            return false;
        last = last.previous;
        return true;
    }

    /**
     * make level 3 binary tree with scanner
     * @param scans scanner containing file or string to be populate tree by lines
     * @return tree populated with lines from the scanner
     */
    public Tree createTree(Scanner scans){
        Scanner scan = scans;
        Tree tree = new Tree();
        ArrayList<String> list = new ArrayList<String>();
        int i = 0;
        while(scan.hasNextLine()){
            list.add(scan.nextLine());
        }
        tree.add("oh","A");
        tree.add(list.get(0),"A");
        tree.add(list.get(1),"B");
        tree.next("A");
        tree.add(list.get(2),"A");
        tree.add(list.get(3),"B");
        tree.next("A");
        tree.add(list.get(4),"A");
        tree.add(list.get(5),"B");
        tree.reverse();
        tree.next("B");
        tree.add(list.get(6),"A");
        tree.add(list.get(7),"B");
        tree.reverse();
        tree.reverse();
        tree.next("B");
        tree.add(list.get(8),"A");
        tree.add(list.get(9),"B");
        tree.next("A");
        tree.add(list.get(10),"A");
        tree.add(list.get(11),"B");
        tree.reverse();
        tree.next("B");
        tree.add(list.get(12),"A");
        tree.add(list.get(13),"B");
        return tree;
    }

    /**
     * make level 3 binary tree with ArrayList
     */

    public Tree createTree(ArrayList<String> list){
        Tree tree = new Tree();
        tree.add(list.get(0),"A");
        tree.add(list.get(1),"A");
        tree.add(list.get(2),"B");
        tree.next("A");
        tree.add(list.get(3),"A");
        tree.add(list.get(4),"B");
        tree.next("A");
        tree.add(list.get(5),"A");
        tree.add(list.get(6),"B");
        tree.reverse();
        tree.next("B");
        tree.add(list.get(7),"A");
        tree.add(list.get(8),"B");
        tree.reverse();
        tree.reverse();
        tree.next("B");
        tree.add(list.get(9),"A");
        tree.add(list.get(10),"B");
        tree.next("A");
        tree.add(list.get(11),"A");
        tree.add(list.get(12),"B");
        tree.reverse();
        tree.next("B");
        tree.add(list.get(13),"A");
        tree.add(list.get(14),"B");
        return tree;
    }

    /**
     * retrieve element based on choices of A or B up to 3 levels, skips the first token
     * @param choices
     * @return String based on choices
     */
    public String get(String choices){
        ListNode current = front;
        Scanner element;
        String lines;
        for (int i = 0; i < choices.length(); i++){
            if((choices.charAt(i) == 'A' || choices.charAt(i) == 'a') && current != null)
                current = current.A;
            else if(current != null)
                current = current.B;
        }
        element = new Scanner(current.data);
        element.next();
        lines = element.nextLine().trim();
        while(element.hasNextLine()) {
            lines += '\n'+element.nextLine().trim();
        }
        return lines + '\n';
    }

    /**
     * return first element
     * @return String first element
     */
    public String get(){
        ListNode current = front;
        Scanner element;
        String lines;
        element = new Scanner(current.data);
        element.next();
        lines = element.nextLine().trim();
        while(element.hasNextLine()) {
            lines += '\n'+element.nextLine().trim();
        }
        return lines + '\n';
    }
}