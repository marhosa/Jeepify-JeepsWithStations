//linagyan ko ng to and from

public class Tickets {
    private int ID;
    private int Driver_ID;
    private int Passenger_ID;
    private int Station_ID_FROM;
    private int Station_ID_TO;
    private float Commute_Price;
    private boolean Discounted;

//note: lagyan ng private float magkano-yung-commute
//tas lagyan ito ng getter at setter and modify constructor
//actually wala pang getter and setters ung tickets so ayun rin

    //constructor
    public Tickets(int ID, int Driver_ID, int Passenger_ID, int Station_ID_FROM, int Station_ID_TO, float Commute_Price, boolean Discounted) {
        this.ID = ID;
        this.Driver_ID = Driver_ID;
        this.Passenger_ID = Passenger_ID;
        this.Station_ID_FROM = Station_ID_FROM;
        this.Station_ID_TO = Station_ID_TO;
        this.Commute_Price = Commute_Price;
        this.Discounted = Discounted;
    }

    public void setID(int ID){
        this.ID = ID;
    }

    public void setDriver_ID(int Driver_ID){
        this.Driver_ID = Driver_ID;
    }

    public void setPassenger_ID(int Passenger_ID){
        this.Passenger_ID = Passenger_ID;
    }

    public void setStation_ID_FROM(int Station_ID_FROM){
        this.Station_ID_FROM = Station_ID_FROM;
    }

    public void setStation_ID_TO(int Station_ID_TO){
        this.Station_ID_TO = Station_ID_TO;
    }

    public void setCommute_Price(float Commute_Price){
        this.Commute_Price = Commute_Price;
    }

    public void setDiscounted(boolean Discounted){
        this.Discounted = Discounted;
    }

    public int getID(){
        return this.ID;
    }

    public int getDriver_ID(){
        return this.Driver_ID;
    }

    public int getPassenger_ID(){
        return this.Passenger_ID;
    }

    public int getStation_ID_FROM(){
        return this.Station_ID_FROM;
    }

    public int getStation_ID_TO(){
        return this.Station_ID_TO;
    }

    public float getCommute_Price(){
        return this.Commute_Price;
    }

    public boolean getDiscounted(){
        return this.Discounted;
    }
}



/*
Explanation:


Alam mo yung inaral natin sa SQL na parang JOINS?

Ito yung nag JOJOIN ng passenger, driver, stationIDs (to and from)

And fare rin kung magkano (like ticket talaga)(pa add ng feature pls)

And pwede to gamitin ng other members to implement their own features.

*/









