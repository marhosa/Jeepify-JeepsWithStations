import java.io.*;
import java.util.*;

public class FileHandling {


    private static final String DESKTOP_PATH = System.getProperty("user.home") + "/Desktop/";

    public static int StringtoInt(String string) {
        return Integer.parseInt(string);
    }

    public static float StringtoFloat(String string) {
        return Float.parseFloat(string);
    }

    public static boolean StringtoBooleean(String string) {
        return Boolean.parseBoolean(string);
    }

    public static HashMap<Integer, Tickets> loadTicketSaves(HashMap<Integer, Tickets> DB_Tickets) {
        boolean fail = false;
        try {
            BufferedReader reader = new BufferedReader(new FileReader(DESKTOP_PATH + "DB_Ticket.lods"));

            String line;
            while ((line = reader.readLine()) != null) {
                String[] DB_Ticket_Array = line.split(",");

                int ticketID = StringtoInt(DB_Ticket_Array[0]);
                int ticketDriverID = StringtoInt(DB_Ticket_Array[1]);
                int ticketPassengerID = StringtoInt(DB_Ticket_Array[2]);
                int StationFROM = StringtoInt(DB_Ticket_Array[3]);
                int StationTO = StringtoInt(DB_Ticket_Array[4]);
                float ticketPrice = StringtoFloat(DB_Ticket_Array[5]);
                boolean isDiscounted = StringtoBooleean(DB_Ticket_Array[6]);

                DB_Tickets.put(ticketID, new Tickets(ticketID, ticketDriverID, ticketPassengerID, StationFROM, StationTO, ticketPrice, isDiscounted));
            }

            reader.close();
        }  catch(FileNotFoundException e){
            Functions.Spacer(5);
            System.out.println("Error: File not Found...\nMake sure to have the DB_Ticket.lods file in your Desktop\nPress Enter to Continue...");
            fail = true;
            try {
                System.in.read(); // Press Enter
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        catch (Exception e) {
            e.printStackTrace();
            System.out.println("\n\nPress Enter to Continue...");
            fail = true;
            try {
                System.in.read(); // Waits for the user to press Enter
            } catch (IOException ex) {
                ex.printStackTrace();
            }

        }
        finally{
            if(!fail){
                System.out.println("\nImport Successful!");
                System.out.println("Press Enter to Continue...");
                try {
                    System.in.read(); // Waits for the user to press Enter
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        }

        return DB_Tickets;
    }

    public static void updateTicketSaves(HashMap<Integer, Tickets> DB_Tickets) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(DESKTOP_PATH + "DB_Ticket.lods"));

            DB_Tickets.forEach((key, value) -> {
                try {
                    writer.write(value.getID() + "," + value.getDriver_ID() + "," + value.getPassenger_ID() + ","
                            + value.getStation_ID_FROM() + "," + value.getStation_ID_TO() + "," + value.getCommute_Price() + ","
                            + value.getDiscounted() + "\n");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });

            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static HashMap<Integer, Passenger> loadPassengerAccountSaves(HashMap<Integer, Passenger> DB_Passenger) {
        boolean fail = false;
        try {
            BufferedReader reader = new BufferedReader(new FileReader(DESKTOP_PATH + "DB_Passenger.lods"));

            String line;
            while ((line = reader.readLine()) != null) {
                String[] DB_Passenger_Array = line.split(",");

                int passengerID = StringtoInt(DB_Passenger_Array[0]);
                String passengerName = DB_Passenger_Array[1];
                int passengerAge = StringtoInt(DB_Passenger_Array[2]);
                float commuteFare = StringtoFloat(DB_Passenger_Array[3]);

                DB_Passenger.put(passengerID, new Passenger(passengerID, passengerName, passengerAge, commuteFare));
            }
            reader.close();

        }
        catch(FileNotFoundException e){
            Functions.Spacer(5);
            System.out.println("Error: File not Found...\nMake sure to have the DB_Passenger.lods file in your Desktop\nPress Enter to Continue...");
            fail = true;
            try {
                System.in.read(); // Press Enter
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        catch (Exception e) {
            e.printStackTrace();
            System.out.println("\n\nPress Enter to Continue...");
            fail = true;
            try {
                System.in.read(); // Waits for the user to press Enter
            } catch (IOException ex) {
                ex.printStackTrace();
            }

        }
        finally{
            if(!fail){
                System.out.println("\nImport Successful!");
                System.out.println("Press Enter to Continue...");
                fail = true;
                try {
                    System.in.read(); // Waits for the user to press Enter
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        }

        return DB_Passenger;
    }

    public static void updatePassengerAccountSaves(HashMap<Integer, Passenger> DB_Passenger) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(DESKTOP_PATH + "DB_Passenger.lods"));

            DB_Passenger.forEach((key, value) -> {
                try {
                    writer.write(value.getID() + "," + value.getName() + "," + value.getAge() + "," + value.getLoad() + "\n");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });

            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void createTicket(HashMap<Integer, Tickets> DB_Tickets, HashMap<Integer, Passenger> DB_Passengers,
                                    HashMap<Integer, Driver> DB_Drivers, HashMap<Integer, Stations> DB_Stations, int ID) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(DESKTOP_PATH + "Ticket No. " + DB_Tickets.get(ID).getID() + ".lods"));

            writer.write("Ticket ID: " + DB_Tickets.get(ID).getID());
            writer.write("\nPassenger: " + DB_Passengers.get(DB_Tickets.get(ID).getPassenger_ID()).getName());
            writer.write("\nDriver: " + DB_Drivers.get(DB_Tickets.get(ID).getDriver_ID()).getName());
            writer.write("\nFrom Station: " + DB_Stations.get(DB_Tickets.get(ID).getStation_ID_FROM()).getName());
            writer.write("\nTo Station: " + DB_Stations.get(DB_Tickets.get(ID).getStation_ID_TO()).getName());
            writer.write("\nPrice of: Php " + DB_Tickets.get(ID).getCommute_Price());

            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



}
