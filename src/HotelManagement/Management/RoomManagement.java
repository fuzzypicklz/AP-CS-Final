package HotelManagement.Management;

import java.util.ArrayList;

import HotelManagement.Rooms.Room;
import HotelManagement.Rooms.Standard;
import HotelManagement.Rooms.Suite;

import java.io.File;
import java.io.IOException;

import java.util.Date; // TODO BIG IMPORTANTE

/**
 * Manages rooms in the hotel management system.
 * Provides functionality to add, remove, and retrieve rooms,
 * as well as export and import room data.
 */
public class RoomManagement{
    public static ArrayList<Room> roomList = new ArrayList<Room>();
    public static void main(String[] args){
        roomDebug();
        roomsToCSV();
    }
    /*
     * Method testing
     */
    public static void roomDebug(){
        Room r1 = new Standard(134);
        Room r2 = new Standard(135, 5, false, 150.00);
        Suite s1 = new Suite(1236);
        Suite s2 = new Suite(1237, 5, false, true, 200.00);
        addRoom(r1);
        addRoom(r2);
        addRoom(s1);
        addRoom(s2);

        addRoom(r1);

        removeRoom(r1);
        removeRoom(r1);

        PrintRecipt("JohnDoe", 3, new Date(), r2); // Example of how to call PrintRecipt, this will create a file in the current directory with the name "134JohnDoeWed Oct 11 14:32:00 UTC 2023.txt" (example date)
    }

    public static void addStandard(int number, int capacity, boolean isOccupied, double rate){
        Room r = new Standard(number, capacity, isOccupied, rate);
        if (getRoom(r.getNumber()) == null){
            roomList.add(r);
            System.out.println("Room "+r.getNumber()+" was added to roomList.");
        }
        else{
            System.out.println("Room " + r.getNumber() + " already exists!");
        }
    }

    public static void addSuite(int number, int capacity, boolean isOccupied, boolean hasBalcony, double rate){
        Suite r = new Suite(number, capacity, isOccupied, hasBalcony, rate);
        if (getRoom(r.getNumber()) == null){
            roomList.add(r);
            System.out.println("Room "+r.getNumber()+" was added to roomList.");
        }
        else{
            System.out.println("Room " + r.getNumber() + " already exists!");
        }
    }

    /**
     * Adds a room to the room list.
     * @param room The Room object to add.
     */
    public static void addRoom(Room r){
        if (getRoom(r.getNumber()) == null){
            roomList.add(r);
            System.out.println("Room "+r.getNumber()+" was added to roomList.");
        }
        else{
            System.out.println("Room " + r.getNumber() + " already exists!");
        }
    }

    /**
     * Removes a room from the room list.
     * @param room The Room object to remove.
     */
    public static void removeRoom(Room r){
        if(roomList.contains(r)){
            roomList.remove(r);
            System.out.println("Room " + r.getNumber() + " was removed.");
        }
        else System.out.println("Room " + r.getNumber() + " doesn't exist!");
    }
    /*
     * Prints a formatted list of room numbers in roomList
     */
    public static String getRoomsString(){
        String s = "";
        for(int i = 0; i < roomList.size(); i++){
            Room room = (Room) roomList.get(i);
            s += room.getNumber() + "\n";
        }
        return s;
    }
    /*
     * Returns a given Room based on its number.
     */
    /**
     * Retrieves a room by its number.
     * @param number The room number.
     * @return The Room object, or null if not found.
     */
    public static Room getRoom(int number){
        for (int i = 0; i < roomList.size(); i++){
            Room room = (Room) roomList.get(i);
            if (room.getNumber() == number){
                return room;
            }
        }
        System.out.println("Room " + number + " not found.");
        return null;
    }
    /*
     * Returns an ArrayList of all Rooms.
     */
    /**
     * Retrieves a string representation of all rooms in the system.
     * @return A formatted string containing all rooms.
     */
    public static String getRoomListString(){
        String s = "";
        for(Room room : roomList){
            s+=room.getNumber();
            s+="\n";
        }
        return s;
    }
    /*
     * Returns an ArrayList of unoccupied Rooms.
     */
    public static ArrayList<Room> getUnoccupiedRoomList(){
        ArrayList<Room> unoccupiedRooms = new ArrayList<Room>();
        for(Room room : roomList){
            if(room.getOccupants()==0){
                unoccupiedRooms.add(room);
            }
        }
        return unoccupiedRooms;
    }
    /*
     * Returns a formatted String of unoccupied Rooms.
     */
    /**
     * Retrieves a string representation of all unoccupied rooms in the system.
     * @return A formatted string containing all unoccupied rooms.
     */
    public static String getUnoccupiedRoomListString(){
        String s = "";
        for(Room room : roomList){
            if(room.getOccupants()==0){
                s+=room.getNumber();
                s+="\n";
            }
        }
        return s;
    }
    /*
     * exports a CSV containing all Room data.
     */
    /**
     * Exports all room data to a CSV file.
     */
    public static void roomsToCSV(){
        String s = "number,type,capacity,occupants,rate,balcony\n";
        for(int i = 0; i < roomList.size(); i++){
            if(roomList.get(i).getType().equalsIgnoreCase("suite")){
                Suite room = (Suite) roomList.get(i);
                s += room.getNumber() + ",";
                s += room.getType() + ",";
                s += room.getCapacity() + ",";
                s += room.getOccupants() + ",";
                s += room.getRate() + ",";
                s += room.hasBalcony()+"\n";
            }
            else {
                Room room = (Room) roomList.get(i);
                s += room.getNumber() + ",";
                s += room.getType() + ",";
                s += room.getCapacity() + ",";
                s += room.getOccupants() + ",";
                s += room.getRate() + "\n";
            }
        }
        try{
            File file = new File("data/rooms.csv");
            java.io.PrintWriter output = new java.io.PrintWriter(file);
            output.print(s);
            output.close();
            System.out.println("Rooms saved to file.");
        } catch (IOException e){
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
    /*
     * APPENDS all rooms
     */
    /**
     * Imports room data from a CSV file.
     */
    public static void roomsFromCSV(){
        try{
            File file = new File("data/rooms.csv");
            java.util.Scanner input = new java.util.Scanner(file);
            input.nextLine();

            String list = getRoomListString();
            while(input.hasNext()){
                String[] lineBreak = input.nextLine().split(",");
                int number = Integer.parseInt(lineBreak[0]);
                String type = lineBreak[1];
                int cap = Integer.parseInt(lineBreak[2]);
                int occ = Integer.parseInt(lineBreak[3]);
                double rate = Double.parseDouble(lineBreak[4]);
                if(list.contains(""+number)){
                    System.out.println("Room " + number + " already exists");
                    continue;
                }
                if (type.equalsIgnoreCase("suite")){
                    boolean hasBalc = Boolean.parseBoolean(lineBreak[5]);
                    if (occ>0) { // if there is at least 1 person in the room (since only the amount of people is stored in the CSV) set isocc to true
                        roomList.add(new Suite(number,cap,true,hasBalc,rate));
                        roomList.get(roomList.size()-1).setOccupancy(occ);
                    }
                    else{
                        roomList.add(new Suite(number,cap,false,hasBalc,rate));
                    }
                }
                else if(type.equalsIgnoreCase("standard")){
                    if(occ>0){
                        roomList.add(new Standard(number,cap,true,rate));
                        roomList.get(roomList.size()-1).setOccupancy(occ);
                    }
                    else{
                        roomList.add(new Standard(number,cap,false,rate));
                    }
                }
            }
            input.close();
            System.out.println("Rooms loaded from file.");
        } catch (IOException e){
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
    /*
     * Prints a receipt for a given room and stay duration.
     * Creates a text file in the "data" directory with the receipt details.
     * @param name The name of the guest
     * @param days The number of days stayed
     * @param dayIn The check-in date
     * @param room The room object for which the receipt is being generated
     */
    /**
     * Prints a receipt for a room booking.
     * @param name The name of the customer.
     * @param days The number of days booked.
     * @param checkIn The check-in date.
     * @param room The Room object being booked.
     */
    public static void PrintRecipt(String name, int days, Date dayIn, Room room){
        
        try{
            File file = new File("data/"+room.getNumber()+name+(dayIn.getMonth()+1)+"-"+dayIn.getDate()+"-"+dayIn.getYear()+".txt");
            java.io.PrintWriter output = new java.io.PrintWriter(file);

            java.util.Calendar calendar = java.util.Calendar.getInstance();
            calendar.setTime(dayIn);
            calendar.add(java.util.Calendar.DATE, days);
            Date dayOut = calendar.getTime();

            output.println("Thank you, "+name+" for staying with us!");
            output.println("Room Number: " + room.getNumber());
            output.println("Room Type: " + room.getType());
            output.println("Check-in Date: " + (dayIn.getMonth()+1)+"/"+dayIn.getDate()+"/"+dayIn.getYear());
            output.println("Check-out Date: " + (dayOut.getMonth()+1)+"/"+dayOut.getDate()+"/"+dayOut.getYear()); // Calculate check-out date based on days stayed
            output.println("Number of Nights: " + days);
            output.println("Rate per Night: $" + room.getRate());
            output.print("\n");
            output.println("Total Amount: $" + (room.getRate() * days)); // Total amount for the stay
            output.println("We hope you enjoy your stay!");
            output.close();

            System.out.println("Receipt created successfully");
            return;
        } catch (Exception e) {
            System.out.println("An error occurred while creating the file.");
            e.printStackTrace();
            return; // Exit if file creation fails
        }
    }
}