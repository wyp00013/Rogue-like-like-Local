package Guard;

import Map.MapWindow;
import java.util.ArrayList;

public class Guard{

    //fields
    private int currentStep = 1;
    private int[] cords;
    private String currentPosition;
    private ArrayList<String> patrolPath = new ArrayList<>();
    
    //private int positionX;
    //private int positionY;

    //Constructors
    public Guard(String[] array){ //When Creating guards make sure to have the ending postion be next to the starting postion
        
        for(int i = 0; i < array.length; i++){
            patrolPath.add(array[i]);
        }
        currentPosition = patrolPath.get(currentStep-1);
    }
        
    //Methods
    public void advancePatrolStep(){
        currentStep++;//Advance CurrentStep
        if(currentStep > patrolPath.size()){//Check to see if CurrentStep is past array parameters.
            currentStep = 1; //If true reset current Step
        }
        currentPosition = patrolPath.get(currentStep-1);//Bring CurrentPosition to match CurrentStep
    }

    
    public String getCurrentPosition() {
        return currentPosition;
    }




}//End of Class