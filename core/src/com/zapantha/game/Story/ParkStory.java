package com.zapantha.game.Story;

import com.zapantha.game.Misc.Love;
import com.zapantha.game.Misc.Tree;

import java.util.Scanner;

/**
 * Created by sams on 12/20/2017.
 */

public class ParkStory {
    private String nextQuestion;
    private int i;
    private Scanner story;
    private Scanner options;
    private Scanner responses;
    private Love love;
    private Tree questionTree;
    private Tree responseTree;
    public ParkStory(Tree questionTree, Tree responseTree, Love love){
        this.love = love;
        int i = 0;
        this.questionTree = questionTree;
        this.responseTree = responseTree;
    }
    public String nextScene(String response){
        Scanner results = null;
        return "";
    }

    public boolean hasNextScene(){
        return false;
    }

    public String question(){
        return "";
    }

    private String interpretLine(Scanner results, String response){
        String currResponse = ""+response.charAt(0);
        String johnResponse = "";
        if(currResponse.equalsIgnoreCase("B")) {
            skipQuestion();
            love.minusLove();
            responses.nextLine();
            johnResponse = responses.nextLine();
        }else{
            johnResponse = responses.nextLine();
            responses.nextLine();
            love.addLove();
        }
        return johnResponse;
    }
    private void skipQuestion(){
        question();
    }
    public String intro(){
        if(i == 0) {
            i++;
            return question();
        }else
            return null;
    }
}
