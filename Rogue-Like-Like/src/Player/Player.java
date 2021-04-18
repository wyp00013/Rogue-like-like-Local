
package Player;
import Map.MapWindow;

public class Player {

    //Fields
    private String currentPosition;
    private String name;

    //Constructors?
    public Player(String name, String position){
        this.name = name;
        currentPosition = position;
    }

    //Methods

    public String getCurrentPosition() {
        return currentPosition;
    }
    public String getName() {
        return name;
    }
    public void setPlayerPosition(String room){
        currentPosition = room;
    }
}//End of Class
