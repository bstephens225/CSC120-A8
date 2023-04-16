import java.util.ArrayList;
import java.util.Hashtable;
public class Character implements Contract {
    ArrayList<String> inventory;
    boolean north = true;
    boolean south = true;
    Integer location=0;
    Integer height=5;
    Integer health=10;
    Hashtable<String,String> actions;

    public Character(){

    }
    
    public void grab(String item){
        inventory.add(item);
        actions.put("grab", item);
    }

    public String drop(String item){
        inventory.remove(item);
        actions.put("drop", item);
        return item+"dropped";
    }

    public void examine(String item){
        System.out.println("this item is completely normal");
    }

    public void use(String item){
        actions.put("use", item);
    }

    public void changeLocation(String direction){
        if (direction=="north"){
            location++;
        }else if (direction =="south"){
            location--;
        }
        if (location>=5){
            north=false;
        }
        if (location<=-5){
            south=false;
        }
    }

    public boolean walk(String direction){
        if (direction=="north"){
            if (north==true){
                changeLocation(direction);
                actions.put("walk", direction);
                return true;
            }else{
                return false;
            }
        }else if (direction=="south"){
            if (south==true){
                changeLocation(direction);
                actions.put("walk", direction);
                return true;
            }else{
                return false;
            }
        }else{
            throw new RuntimeException("that's not a direction in this world");

        }
    }

    public boolean fly(int x, int y){
        //System.out.println("you can't fly silly! you're not a bird");
        return false;
    }

    public Number shrink(){
        height=height/2;
        actions.put("shrink", "");
        return height;
    }

    public Number grow(){
        height=height*2;
        actions.put("shrink", "");
        return height;
    }

    public void rest(){
        if (health<10){
            health++;
        }
        actions.put("rest", "");
    }

    public void undo(){}
}