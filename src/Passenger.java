public class Passenger extends absPerson {
    private int ID;
    private float Load;


    Passenger (int id, String name, int age, float Load) {
        super(name, age);
        this.Load = Load;
        this.ID = id;
    }

    public float getLoad() {
        return Load;
    }

    public void setLoad(float Load) {
        this.Load = Load;
    }

    public int getID() {
        return ID;
    }
}
