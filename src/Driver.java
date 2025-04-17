public class Driver extends absPerson{
    private int ID;

    public Driver (int ID, String name, int age){
        super(name, age);
        this.ID = ID;
    }

    public int getID(){
        return this.ID;
    }
}



