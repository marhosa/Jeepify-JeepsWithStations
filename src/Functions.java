/*
dito ilalagay functions natin
declare them as public static returntype name (parameters)
if gagamitin sa main, they be like:
Functions.namengfunction(parameters);
*/

import java.util.*;

public class Functions {
    public static void Spacer(int a){
        for (int i = 0; i < a; i++) {
            System.out.println(" ");
        }
    }

    public static void printMainMenu(){
        System.out.println("[1] Ride a Jeep");
        System.out.println("[2] Account CRUD");
        System.out.println("[3] Add Balance");
        System.out.println("[4] View Account Info");
        System.out.println("[5] Admin Settings");
        System.out.println("[6] Exit");

    }

    public static void printAdminMenu(){
        System.out.println(">> ADMINISTRATOR SETTINGS <<");
        System.out.println("[1] Import Accounts");
        System.out.println("[2] Import Tickets\n");
        System.out.println("[3] Export Accounts");
        System.out.println("[4] Export Tickets\n");
        System.out.println("[5] Profit Statistics");
        System.out.println("[6] Passenger Statistics\n");
        System.out.println("[7] Exit Admin View");
    }

    public static void printCase2Menu(){
        System.out.println("ACCOUNTS MENU");
        System.out.println("[1] Create Account ");
        System.out.println("[2] Search Account ");
        System.out.println("[3] Update Account ");
        System.out.println("[4] Delete Account ");
        System.out.println("[5] Exit Accounts Menu");
    }

    public static void printCase4Menu(){
        System.out.println("[1] Print Full Account Info");
        System.out.println("[2] Check Balance");
    }


    //generate a Passenger ID (will be unique)
    //just do: "generatePassengerID(DB_Passengers);"
    public static int generatePassengerID(HashMap<Integer,Passenger> mappp){
        int id = 1;
        if(mappp.isEmpty()){
            return id;
        }
        while(mappp.containsKey(id)){
            id++;
        }
        return id;
    }

    //generate a Ticket ID (will be unique)
    //just do: "generateTicketID(DB_Tickets);"
    public static int generateTicketID(HashMap<Integer,Tickets> mappp){
        int id = 1;
        if(mappp.isEmpty()){
            return id;
        }
        while(mappp.containsKey(id)){
            id++;
        }
        return id;
    }

    public static void showStations(HashMap<Integer, Stations> x){
        for(int i = 1; i < x.size() + 1; i++){
            System.out.println("[" + x.get(i).getID() + "] |  " + x.get(i).getName());
        }
    }




}




