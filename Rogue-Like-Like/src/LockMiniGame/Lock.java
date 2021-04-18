package LockMiniGame;

import java.util.Random;
class Lock {

    private int target;
    private int difficulty;
    private int upperTargetBound;
    private int lowerTargetBound;

    public Lock(int difficulty){
        this.difficulty = difficulty;
    }

    public int getLockTargetUpper(){

        return upperTargetBound;
    }
    public int getLockTargetLower(){
        return lowerTargetBound;
    }
    public String getDifficulty(){
        if (difficulty == 3){
            return "HARD";
        }
        else
        if (this.difficulty == 2){
            return "MEDIUM";
        }
        else {
            return "EASY";
        }
    }

    public void setLockTarget(){
        Random rand = new Random();
        target = rand.nextInt(70) + 10;
        if (difficulty == 3){
            lowerTargetBound = target - 3;
            upperTargetBound = target + 3;
        }
        else
            if (difficulty == 2){
                lowerTargetBound = target - 6;
                upperTargetBound = target + 6;
            }
            else {
                lowerTargetBound = target - 10;
                upperTargetBound = target + 10;
            }
    }





}