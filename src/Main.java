import java.security.spec.RSAOtherPrimeInfo;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

public class Main {

    public static void main(String[] args) {
        // Scanner Declaration
        Scanner sc = new Scanner(System.in);

        // Store stations in HashMap
        HashMap<Integer, Stations> DB_Stations = new HashMap<>();
        DB_Stations.put(1, new Stations(1, "Nicanor Reyes St."));
        DB_Stations.put(2, new Stations(2, "Padre Paredes St."));
        DB_Stations.put(3, new Stations(3, "Padre Campa St."));
        DB_Stations.put(4, new Stations(4, "G. Tolentino St."));
        DB_Stations.put(5, new Stations(5, "P. Noval St."));
        DB_Stations.put(6, new Stations(6, "Galiciat St."));
        DB_Stations.put(7, new Stations(7, "Moret St."));

        // Store drivers in HashMap - Driver names are the developers of this program btw
        HashMap<Integer, Driver> DB_Drivers = new HashMap<>();
        DB_Drivers.put(1, new Driver(1, "Jude", 19));
        DB_Drivers.put(2, new Driver(2, "Marcus", 19));
        DB_Drivers.put(3, new Driver(3, "Matthew", 19));
        DB_Drivers.put(4, new Driver(4, "Shynalee", 19));

        // HashMap of tickets and passenger Declaration
        HashMap<Integer, Tickets> DB_Tickets = new HashMap<>();
        HashMap<Integer, Passenger> DB_Passengers = new HashMap<>();

        // Variables for main loop
        int mainchoice;

        // Main loop
        while (true) {
            Functions.Spacer(30);
            Functions.printMainMenu();

            while (true) {
                try {
                    System.out.print("Choice: ");
                    mainchoice = sc.nextInt();
                    sc.nextLine();
                    if (mainchoice > 0 && mainchoice < 7) {
                        break;
                    } else {
                        System.out.println("Please enter within the range");
                    }
                } catch (InputMismatchException e) {
                    System.out.println("Invalid input. Please enter a valid integer");
                    sc.nextLine();
                }
            }

            switch (mainchoice) {
                case 1:

                    if(DB_Passengers.isEmpty()){
                        System.out.println("<!> There are no registered accounts...\nPress enter to continue...");
                        sc.nextLine();
                        break;
                    }

                    Functions.Spacer(2);
                    System.out.println(">> RIDE A JEEP <<");

                    int passengerID;
                    Passenger currentPassenger = null;
                    while (true) {
                        try {
                            System.out.print("Enter Passenger ID: ");
                            passengerID = sc.nextInt();
                            sc.nextLine();

                            if (DB_Passengers.containsKey(passengerID)) {
                                currentPassenger = DB_Passengers.get(passengerID);
                                break;
                            } else {
                                System.out.println("<!> Passenger ID not found. Try again.");
                            }
                        } catch (InputMismatchException e) {
                            System.out.println("<!> Invalid input. Please enter a valid Passenger ID.");
                            sc.nextLine();
                        }
                    }

                    int currentStationID;
                    Stations currentStation = null;

                    Functions.Spacer(5);
                    Functions.showStations(DB_Stations);
                    while (true) {
                        try {
                            System.out.print("Enter Current Station ID: ");
                            currentStationID = sc.nextInt();
                            sc.nextLine();

                            if (DB_Stations.containsKey(currentStationID)) {
                                currentStation = DB_Stations.get(currentStationID);
                                break;
                            } else {
                                System.out.println("<!> Station ID not found. Try again.");
                            }
                        } catch (InputMismatchException e) {
                            System.out.println("<!> Invalid input. Please enter a valid Station ID.");
                            sc.nextLine();
                        }
                    }

                    int destinationStationID;
                    Stations destinationStation = null;
                    Functions.Spacer(5);
                    Functions.showStations(DB_Stations);
                    while (true) {
                        try {
                            System.out.print("Enter Destination Station ID: ");
                            destinationStationID = sc.nextInt();
                            sc.nextLine();

                            if (DB_Stations.containsKey(destinationStationID)) {
                                destinationStation = DB_Stations.get(destinationStationID);
                                break;
                            } else {
                                System.out.println("<!> Station ID not found. Try again.");
                            }
                        } catch (InputMismatchException e) {
                            System.out.println("<!> Invalid input. Please enter a valid Station ID.");
                            sc.nextLine();
                        }
                    }

                    boolean hasDiscount = false;
                    while (true) {
                        System.out.print("Are you a PWD or Student? (Y/N): ");
                        String discountResponse = sc.nextLine().trim().toUpperCase();
                        if (discountResponse.equals("Y")) {
                            hasDiscount = true;
                            break;
                        } else if (discountResponse.equals("N")) {
                            hasDiscount = false;
                            break;
                        } else {
                            System.out.println("<!> Invalid input. Please enter Y or N.");
                        }
                    }

                    final int baseRate = 13;
                    final int incrementRate = 3;
                    int distance = Math.abs(currentStationID - destinationStationID);
                    int baseFare = baseRate + (Math.max(0, distance - 1) * incrementRate);
                    int discountedFare = hasDiscount ? (int) (baseFare * 0.8) : baseFare;

                    float currentLoad = currentPassenger.getLoad();
                    if (currentLoad < discountedFare) {
                        System.out.println("<!> Insufficient load. Please top up and try again.");
                        System.out.println("Press Enter to continue...");
                        sc.nextLine();
                        break;
                    }

                    currentPassenger.setLoad(currentLoad - discountedFare);

                    int driverID = ThreadLocalRandom.current().nextInt(1, 5);
                    int ticketID = Functions.generateTicketID(DB_Tickets);

                    Tickets newTicket = new Tickets(
                            ticketID,
                            driverID,
                            passengerID,
                            currentStation.getID(),
                            destinationStation.getID(),
                            discountedFare,
                            hasDiscount
                    );

                    DB_Tickets.put(ticketID, newTicket);

                    Functions.Spacer(2);
                    System.out.println(">> Ticket Purchase Successful!");
                    System.out.println("Ticket ID: " + ticketID);
                    System.out.println("Passenger: " + currentPassenger.getName());
                    System.out.println("Driver: " + DB_Drivers.get(driverID).getName());
                    System.out.println("From: " + currentStation.getName());
                    System.out.println("To: " + destinationStation.getName());
                    System.out.println("Fare: " + discountedFare);
                    System.out.println("Remaining Load: " + currentPassenger.getLoad());
                    System.out.println("Press Enter to continue...");
                    sc.nextLine();
                    break;








                case 2: //CRUD ACCOUNTS

                    //Prints Menu
                    Functions.Spacer(20);
                    Functions.printCase2Menu();
                    int choicecase2;

                    //User inputs Choice
                    while (true) {
                        try {
                            System.out.print("Choice: ");
                            choicecase2 = sc.nextInt();
                            sc.nextLine();
                            if (choicecase2 > 0 && choicecase2 < 6) {
                                break;
                            } else {
                                System.out.println("Please enter within the range");
                            }
                        } catch (InputMismatchException e) {
                            System.out.println("Invalid input. Please enter a valid integer");
                            sc.nextLine();
                        }
                    }


                    //nested switch case of umm... Account CRUD
                    switch(choicecase2){
                        case 1:
                            Functions.Spacer(20);

                            //ask for name
                            System.out.println(">>Creating an Account<<");
                            System.out.print("Name: ");
                            String namecase2 = sc.nextLine();

                            //declare some temp variables
                            int agecase2, loadcase2;

                            //ask for age with error catcher
                            while (true) {
                                try {
                                    System.out.print("Age: ");
                                    agecase2 = sc.nextInt();
                                    sc.nextLine();
                                    if (agecase2 >= 1) {
                                        break;
                                    } else {
                                        System.out.println("Please enter an age greater or equal to 1");
                                    }
                                } catch (InputMismatchException e) {
                                    System.out.println("Invalid input. Please enter a valid integer");
                                    sc.nextLine();
                                }
                            }

                            //ask for load with error catcher
                            while (true) {
                                try {
                                    System.out.print("Load: ");
                                    loadcase2 = sc.nextInt();
                                    sc.nextLine();
                                    if (loadcase2 >= 0) {
                                        break;
                                    } else {
                                        System.out.println("Please enter a load greater or equal to 0");
                                    }
                                } catch (InputMismatchException e) {
                                    System.out.println("Invalid input. Please enter a valid integer");
                                    sc.nextLine();
                                }
                            }

                            //generate a primary key
                            int ID = Functions.generatePassengerID(DB_Passengers);
                            //add object to database
                            DB_Passengers.put(ID, new Passenger(ID, namecase2, agecase2, loadcase2));
                            System.out.println("\n\n\nAccount Made Successfully!");
                            System.out.println("REMEMBER your ID: " + ID);
                            System.out.println("\nPress Enter to continue...");
                            sc.nextLine();
                            break;


                        case 2: //Simple Account search
                            //aborts if there are no passengers
                            if(DB_Passengers.isEmpty()){
                                System.out.println("<!> There are no registered accounts...\nPress enter to continue...");
                                sc.nextLine();
                                break;
                            }
                            Functions.Spacer(7);
                            //Account ID to search
                            System.out.println("Enter the Account ID to search");
                            int tempidcase2;

                            //error catchers for inputting id
                            while (true) {
                                try {
                                    System.out.print("ID: ");
                                    tempidcase2 = sc.nextInt();
                                    sc.nextLine();
                                    if (tempidcase2 >= 1) {
                                        break;
                                    } else {
                                        System.out.println("Please enter an ID greater than zero");
                                    }
                                } catch (InputMismatchException e) {
                                    System.out.println("Invalid input. Please enter a valid integer");
                                    sc.nextLine();
                                }
                            }

                            //If no such account exists uhhhm state to user
                            if(!DB_Passengers.containsKey(tempidcase2)){
                                System.out.println("\n\n\n\n\n<!>Account does not exist!");
                                System.out.println("Press Enter to Continue...");
                                sc.nextLine();
                                break;
                            }


                            //get the passenger object with the same account id
                            Passenger tempPassengerC2 = DB_Passengers.get(tempidcase2);
                            Functions.Spacer(5);

                            //prints out the passenger details
                            System.out.println("Passenger FOUND!");
                            System.out.println("ID: " + tempPassengerC2.getID());
                            System.out.println("Name: " + tempPassengerC2.getName());
                            System.out.println("Age: " + tempPassengerC2.getAge());
                            System.out.println("Load: " + tempPassengerC2.getLoad());
                            System.out.println("Press Enter to Continue...");
                            sc.nextLine();
                            break;

                        case 3:
                            //reused code from case 2
                            //checks if passenger database is empty
                            if(DB_Passengers.isEmpty()){
                                System.out.println("<!> There are no registered accounts...\nPress enter to continue...");
                                sc.nextLine();
                                break;
                            }
                            Functions.Spacer(7);

                            //Account ID to search
                            System.out.println("Enter the Account ID to Modify");
                            while (true) {
                                try {
                                    System.out.print("ID: ");
                                    tempidcase2 = sc.nextInt();
                                    sc.nextLine();
                                    if (tempidcase2 >= 1) {
                                        break;
                                    } else {
                                        System.out.println("Please enter an ID greater than zero");
                                    }
                                } catch (InputMismatchException e) {
                                    System.out.println("Invalid input. Please enter a valid integer");
                                    sc.nextLine();
                                }
                            }

                            //If no such account exists uhhhm state to user
                            if(!DB_Passengers.containsKey(tempidcase2)){
                                System.out.println("\n\n\n\n\n<!>Account does not exist!");
                                System.out.println("Press Enter to Continue...");
                                sc.nextLine();
                                break;
                            }
                            tempPassengerC2 = DB_Passengers.get(tempidcase2);
                            Functions.Spacer(7);
                            //states account is found and prints some options user can do
                            System.out.println("ACCOUNT FOUND!\nWhat do you want to change?");
                            System.out.println("[1] Name");
                            System.out.println("[2] Age");
                            while (true) {
                                try {
                                    System.out.print("Choice: ");
                                    choicecase2 = sc.nextInt();
                                    sc.nextLine();
                                    if (choicecase2 == 1 || choicecase2 == 2) {
                                        break;
                                    } else {
                                        System.out.println("Please enter only 1 or 2");
                                    }
                                } catch (InputMismatchException e) {
                                    System.out.println("Invalid input. Please enter a valid integer");
                                    sc.nextLine();
                                }
                            }
                            //if choice is 1, asks user to input new name
                            if(choicecase2 == 1){
                                Functions.Spacer(7);
                                System.out.print("Enter New Name: ");
                                namecase2 = sc.nextLine();
                                tempPassengerC2.setName(namecase2);
                            }
                            //asks user to input new age
                            else{
                                while (true) {
                                    try {
                                        Functions.Spacer(7);
                                        System.out.print("Enter New Age: ");
                                        agecase2 = sc.nextInt();
                                        sc.nextLine();
                                        if (agecase2 >= 1) {
                                            break;
                                        } else {
                                            System.out.println("Please enter an age greater or equal to 1");
                                        }
                                    } catch (InputMismatchException e) {
                                        System.out.println("Invalid input. Please enter a valid integer");
                                        sc.nextLine();
                                    }
                                }
                                tempPassengerC2.setAge(agecase2);
                            }
                            break;

                        case 4:
                            //aborts if there are no passengers
                            if(DB_Passengers.isEmpty()){
                                System.out.println("<!> There are no registered accounts...\nPress enter to continue...");
                                sc.nextLine();
                                break;
                            }
                            Functions.Spacer(7);
                            //Account ID to search
                            System.out.println("Enter the Account ID to DELETE");
                            while (true) {
                                try {
                                    System.out.print("ID: ");
                                    tempidcase2 = sc.nextInt();
                                    sc.nextLine();
                                    if (tempidcase2 >= 1) {
                                        break;
                                    } else {
                                        System.out.println("Please enter an ID greater than zero");
                                    }
                                } catch (InputMismatchException e) {
                                    System.out.println("Invalid input. Please enter a valid integer");
                                    sc.nextLine();
                                }
                            }

                            //If no such account exists uhhhm state to user
                            if(!DB_Passengers.containsKey(tempidcase2)){
                                System.out.println("\n\n\n\n\n<!>Account does not exist!");
                                System.out.println("Press Enter to Continue...");
                                sc.nextLine();
                                break;
                            }

                            //get the passenger object with the same account id
                            tempPassengerC2 = DB_Passengers.get(tempidcase2);
                            Functions.Spacer(5);

                            System.out.println("(!)Are you SURE you want to DELETE account\nwith name");
                            System.out.println(tempPassengerC2.getName() + "?");
                            System.out.println("[1]YES\n[2]NO");
                            while (true) {
                                try {
                                    System.out.print("Choice: ");
                                    tempidcase2 = sc.nextInt();
                                    sc.nextLine();
                                    if (tempidcase2 == 1 || tempidcase2 == 2) {
                                        break;
                                    } else {
                                        System.out.println("Please enter an ID greater than zero");
                                    }
                                } catch (InputMismatchException e) {
                                    System.out.println("Invalid input. Please enter a valid integer");
                                    sc.nextLine();
                                }
                            }

                            if(tempidcase2 == 1){
                                Functions.Spacer(5);
                                tempidcase2 = tempPassengerC2.getID();
                                DB_Passengers.remove(tempidcase2);
                                System.out.println("Account Deleted Successfully");
                                System.out.println("Press Enter to Continue...");
                                sc.nextLine();
                            }
                            else{
                                Functions.Spacer(5);
                                System.out.println("Process Aborted");
                                System.out.println("Press Enter to Continue...");
                                sc.nextLine();
                            }
                            break;
                        case 5:
                            //yes this is empty
                            break;
                        default:
                            //this will never be reached
                    }
                    break;
                //end of Account CRUD Case 2

                case 3: // TopUp Balance
                    Functions.Spacer(20);
                    System.out.println("ADD BALANCE");

                    if(DB_Passengers.isEmpty()){
                        System.out.println("<!> There are no registered accounts...\nPress enter to continue...");
                        sc.nextLine();
                        break;
                    }

                    Functions.Spacer(1);

                    //Account ID to search
                    System.out.println("Enter your Account ID to add balance");
                    int tempidcase4;

                    //error catchers for inputting id
                    while (true) {
                        try {
                            System.out.print("ID: ");
                            tempidcase4 = sc.nextInt();
                            sc.nextLine();
                            if (tempidcase4 >= 1) {
                                break;
                            } else {
                                System.out.println("Please enter an ID greater than zero");
                            }
                        } catch (InputMismatchException e) {
                            System.out.println("Invalid input. Please enter a valid integer");
                            sc.nextLine();
                        }
                    }

                    //If no such account exists state to user
                    if(!DB_Passengers.containsKey(tempidcase4)){
                        System.out.println("\n\n\n\n\n<!>Account does not exist!");
                        System.out.println("Press Enter to Continue...");
                        sc.nextLine();
                        break;
                    }

                    //get the passenger object with the same account id
                    Passenger tempPassengerC4 = DB_Passengers.get(tempidcase4);
                    Functions.Spacer(3);

                    //prints out the passenger details
                    System.out.println("Passenger FOUND!");
                    System.out.println("Name: " + tempPassengerC4.getName());
                    System.out.println("Current Balance: " + tempPassengerC4.getLoad());
                    Functions.Spacer(1);

                    System.out.println("Enter how much you would like to add");
                    int addBalanceCase4;

                    //error catchers for inputting balance
                    while (true) {
                        try {
                            System.out.print("Add Balance: ");
                            addBalanceCase4 = sc.nextInt();
                            sc.nextLine();
                            if (addBalanceCase4 > 0) {
                                break;
                            } else {
                                System.out.println("Please enter a positive amount");
                            }
                        } catch (InputMismatchException e) {
                            System.out.println("Invalid input. Please enter a valid integer");
                            sc.nextLine();
                        }
                    }

                    tempPassengerC4.setLoad(tempPassengerC4.getLoad() + addBalanceCase4);

                    System.out.println("\n\n\nBalance Updated!\nPress enter to continue...");
                    sc.nextLine();

                    break;
                //end of Add Balance

                case 4: // View Account Info
                    // Prints Menu
                    Functions.Spacer(20);
                    Functions.printCase4Menu();
                    int choicecase4; // Declare choicecase4 here

                    while (true) {
                        try {
                            System.out.print("Choice: ");
                            choicecase4 = sc.nextInt();
                            sc.nextLine();
                            if (choicecase4 > 0 && choicecase4 < 3) {
                                break;
                            } else {
                                System.out.println("Please enter within the range");
                            }
                        } catch (InputMismatchException e) {
                            System.out.println("Invalid input. Please enter a valid integer");
                            sc.nextLine();
                        }
                    }

                    switch (choicecase4) {
                        case 1: // Print all account details as a text file
                            if (DB_Passengers.isEmpty()) {
                                System.out.println("<!> There are no registered accounts...\nPress enter to continue...");
                                sc.nextLine();
                                break;
                            }

                            System.out.println("Enter the Account ID to view details:");
                            int accountIdToView;

                            while (true) {
                                try {
                                    System.out.print("ID: ");
                                    accountIdToView = sc.nextInt();
                                    sc.nextLine();
                                    if (DB_Passengers.containsKey(accountIdToView)) {
                                        break;
                                    } else {
                                        System.out.println("<!> Account ID not found. Try again.");
                                    }
                                } catch (InputMismatchException e) {
                                    System.out.println("Invalid input. Please enter a valid integer");
                                    sc.nextLine();
                                }
                            }

                            Passenger passengerToView = DB_Passengers.get(accountIdToView);
                            Functions.Spacer(2);
                            System.out.println("Passenger Details:");
                            System.out.println("ID: " + passengerToView.getID());
                            System.out.println("Name: " + passengerToView.getName());
                            System.out.println("Age: " + passengerToView.getAge());
                            System.out.println("Current Load: " + passengerToView.getLoad());
                            System.out.println("Press Enter to continue...");
                            sc.nextLine();
                            break;

                        case 2: // Simply show balance (no need to print as file)
                            if (DB_Passengers.isEmpty()) {
                                System.out.println("<!> There are no registered accounts...\nPress enter to continue...");
                                sc.nextLine();
                                break;
                            }

                            System.out.println("Enter the Account ID to check balance:");
                            int accountIdForBalance;

                            while (true) {
                                try {
                                    System.out.print("ID: ");
                                    accountIdForBalance = sc.nextInt();
                                    sc.nextLine();
                                    if (DB_Passengers.containsKey(accountIdForBalance)) {
                                        break;
                                    } else {
                                        System.out.println("<!> Account ID not found. Try again.");
                                    }
                                } catch (InputMismatchException e) {
                                    System.out.println("Invalid input. Please enter a valid integer");
                                    sc.nextLine();
                                }
                            }

                            Passenger passengerForBalance = DB_Passengers.get(accountIdForBalance);
                            Functions.Spacer(2);
                            System.out.println("Current Balance for " + passengerForBalance.getName() + ": " + passengerForBalance.getLoad());
                            System.out.println("Press Enter to continue...");
                            sc.nextLine();
                            break;
                    }
                    break;

                case 5:

                    Functions.Spacer(20);
                    Functions.printAdminMenu();
                    int choicecase6;

                    while (true) {
                        try {
                            System.out.print("Choice: ");
                            choicecase6 = sc.nextInt();
                            sc.nextLine();
                            if (choicecase6 > 0 && choicecase6 < 8) {
                                break;
                            } else {
                                System.out.println("Please enter within the range");
                            }
                        } catch (InputMismatchException e) {
                            System.out.println("Invalid input. Please enter a valid integer");
                            sc.nextLine();
                        }
                    }

                    switch (choicecase6) {
                        case 1:
                            DB_Passengers = FileHandling.loadPassengerAccountSaves(DB_Passengers);
                            break;

                        case 2:
                            DB_Tickets = FileHandling.loadTicketSaves(DB_Tickets);
                            break;

                        case 3:
                            if (DB_Passengers.isEmpty()) {
                                System.out.println("<!> There are no registered accounts...\nPress enter to continue...");
                                sc.nextLine();
                                break;
                            }
                            FileHandling.updatePassengerAccountSaves(DB_Passengers);
                            System.out.println("Passenger data exported successfully.\nPress Enter to Continue...");
                            sc.nextLine();
                            break;

                        case 4:
                            if (DB_Tickets.isEmpty()) {
                                System.out.println("<!> There are no history of Tickets...\nPress enter to continue...");
                                sc.nextLine();
                                break;
                            }
                            FileHandling.updateTicketSaves(DB_Tickets);
                            System.out.println("Tickets data exported successfully.\nPress Enter to Continue...");
                            sc.nextLine();
                            break;

                        case 5:
                            if (DB_Tickets.isEmpty()) {
                                System.out.println("<!> There are no history of Tickets...\nPress enter to continue...");
                                sc.nextLine();
                                break;
                            }

                            Functions.Spacer(25);
                            System.out.println("- Accounting Section - \n");
                            int totaltiks = DB_Tickets.size();
                            float income = 0;
                            int pwdcount = 0;
                            float stationTO = 0;
                            float stationFROM = 0;

                            //traverse tickets
                            for(int i = 1; i <= DB_Tickets.size(); i++) {
                                if(DB_Tickets.get(i).getDiscounted()){
                                    pwdcount++;
                                }
                                income += DB_Tickets.get(i).getCommute_Price();
                                stationTO += DB_Tickets.get(i).getStation_ID_TO();
                                stationFROM += DB_Tickets.get(i).getStation_ID_FROM();


                            }

                            float mostTO = stationTO/totaltiks;
                            float mostFROM = stationFROM/totaltiks;

                            System.out.println("Total Passenger Rides      : " + totaltiks);
                            System.out.println("Total Income               : " + income);
                            System.out.println("Total Discounted Passengers: " + pwdcount);
                            System.out.println("\nAverage Fare               : " + (income/totaltiks));
                            System.out.println("Average Distance Travel    : " + Math.abs(mostTO - mostFROM));
                            System.out.println("\nPopular Boarding Station   : " + DB_Stations.get((int)mostFROM).getName());
                            System.out.println("Popular Destination Station: " + DB_Stations.get((int)mostTO).getName());
                            System.out.println("-------\nAre you done reading? Press Enter to Continue...");
                            sc.nextLine();



                            break;


                        case 6:
                            if (DB_Passengers.isEmpty()) {
                                System.out.println("<!> There are no history of Passenger Accounts...\nPress enter to continue...");
                                sc.nextLine();
                                break;
                            }
                            Functions.Spacer(25);
                            System.out.println("- Statistics Section - \n");

                            int passengercount = DB_Passengers.size();
                            float totalload = 0;
                            float totalage = 0;

                            //traverse passengers
                            for(int i = 1; i <= DB_Passengers.size(); i++) {
                                totalload += DB_Passengers.get(i).getLoad();
                                totalage += DB_Passengers.get(i).getAge();
                            }
                            System.out.println("Total Registered Accounts: " + passengercount);
                            System.out.println("Total Accounts Load      : " + totalload);
                            System.out.println("\nAverage Person Age       : " + (totalage/passengercount));
                            System.out.println("Average Account Load     : " + (totalload/passengercount));
                            System.out.println("-------\nAre you done reading? Press Enter to Continue...");
                            sc.nextLine();



                            break;



                        case 7:
                            System.out.println("Exiting admin view...");
                            break;
                    }
                    break;


                case 6: // End
                    Functions.Spacer(10);
                    sc.close();
                    System.out.println("Program End...");
                    return;
            }
        }
    }
}