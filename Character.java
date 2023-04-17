import java.util.ArrayList;


public class Character implements Contract {
    public ArrayList<String> inventory= new ArrayList<String>();
    boolean north = true;
    boolean south = true;
    public Integer location=0;
    public Integer height=5;
    public Integer health=10;

    public ArrayList<String> lastAction= new ArrayList<String>();
    
    public Character(){

    }
    
    public Integer getLoc(){
        return location;
    }
    public Integer getHeight(){
        return height;
    }
    public Integer getHealth(){
        return health;
    }
    public void printInventory(){
        System.out.println(inventory);
    }
    public void printActions(){
        System.out.println(lastAction);
    }
    public void grab(String item){
        inventory.add(item);
        
        lastAction.add("grab");
        lastAction.add(item);
    }

    public String drop(String item){
        if(inventory.contains(item)==true){
            inventory.remove(item);
            
            lastAction.add("grab");
            lastAction.add(item);
            return item+"dropped";
        }else{
            throw new RuntimeException("you arent carrying this item");
        }
        
    }

    public void examine(String item){
        System.out.println("this item is completely normal");
    }

    public void use(String item){
        lastAction.add("use");
        lastAction.add(item);
        
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
                
                lastAction.add("walk");
                lastAction.add(direction);
                return true;
            }else{
                return false;
            }
        }else if (direction=="south"){
            if (south==true){
                changeLocation(direction);
                
                lastAction.add("walk");
                lastAction.add(direction);
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
        
        lastAction.add("shrink");
        lastAction.add("null");
        return height;
    }

    public Number grow(){
        height=height*2;
        
        lastAction.add("grow");
        lastAction.add("null");
        return height;
    }

    public void rest(){
        if (health<10){
            health++;
        }
        
        lastAction.add("rest");
        lastAction.add("null");
    }

    public void undo(){
        int last=lastAction.size();
        if (lastAction.get(last-1)=="walk"){
            if (lastAction.get(last)=="north"){
                lastAction.remove(last);
                lastAction.remove(last-1);
                walk("south");
            }else{
                lastAction.remove(last);
                lastAction.remove(last-1);
                walk("north");
            }

        }else if (lastAction.get(last-1)=="shrink"){
            lastAction.remove(last);
            lastAction.remove(last-1);
            grow();
        }else if(lastAction.get(last-1)=="grow"){
            lastAction.remove(last);
            lastAction.remove(last-1);
            shrink();
        }else if(lastAction.get(last-1)=="grab"){
            drop(lastAction.get(last));
            lastAction.remove(last);
            lastAction.remove(last-1);
        }else if (lastAction.get(last-1)=="drop"){
            grab(lastAction.get(last));
            lastAction.remove(last);
            lastAction.remove(last-1);
        }else if (lastAction.get(last-1)=="use"){
            lastAction.remove(last);
            lastAction.remove(last-1);
        }else if (lastAction.get(last-1)=="rest"){
            health--;
            lastAction.remove(last);
            lastAction.remove(last-1);
        }

    }


    public static void main(String[] args) {

       Character you= new Character();
       System.out.println(you.getLoc());
       //you.walk("north");
       you.grab("apple");
       you.drop("apple");
       you.grow();
       you.shrink();
       System.out.println(you.getLoc());
        
    }
    
}