package StoryBoard;


import LockMiniGame.LockGame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class StoryBoard {


    private final String[] choiceArray = new String[4];
    private String txtArea;
    private String curPos;


    LockGame[] lockGames = new LockGame[3]; //creates all the locks array[size] is number of locks in game

    int presses = 1;
    int curLock = 0;

    //Constructor
    public StoryBoard() {


    }

    public String[] getChoices(){
        return choiceArray;
    }
    public String getCurPos(){
        return curPos;
    }
    public String getTxtArea(){
        return txtArea;
    }

    public void frontDoor() {
        curPos = "frontDoor";
        txtArea = (" You broke into the manors front door.\n"
                + "There is a guard approaching from your left,\n"
                + "ahead you see a shut door.\n\n"
                + "What do you do?");
        choiceArray[0] = ("Talk to the guard");
        choiceArray[1] = ("Try the door ahead");
        choiceArray[2] = ("Sneak to the right");
        choiceArray[3] = ("");
    }

    public void beforeLock() {
        curPos = "beforeLock";
        txtArea = ("You'll need to pick the lock to proceed \n");
        choiceArray[0] = ("Pick Lock");
        choiceArray[1] = ("");
        choiceArray[2] = ("");
        choiceArray[3] = ("");
    }
    public void curLock(){
//        sbFrame.setVisible(true);
        curPos = "curLock";
        txtArea = ("Pick the lock to continue forward!");
        if(presses % 2 == 0){
            txtArea = ("You need to pick the lock to move forward!");
        }
        if(presses % 5 == 0){
            txtArea = ("Dont try to cheat just pick the lock...");
        }
        if(presses % 7 == 0){
            txtArea = ("You're persistent.");
        }
        choiceArray[0] = ("");
        choiceArray[1] = ("");
        choiceArray[2] = ("");
        choiceArray[3] = ("Continue >");
    }


}

