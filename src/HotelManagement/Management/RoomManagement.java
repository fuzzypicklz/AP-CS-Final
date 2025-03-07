package HotelManagement.Management;

import java.util.ArrayList;

import HotelManagement.Rooms.Room;
import HotelManagement.Rooms.Suite;

import java.io.File;
import java.io.IOException;

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
        Room r1 = new Room(134);
        Room r2 = new Room(135, 5, false, 150.00);
        Suite s1 = new Suite(1236);
        Suite s2 = new Suite(1237, 5, false, true, 200.00);
        addRoom(r1);
        addRoom(r2);
        addRoom(s1);
        addRoom(s2);

        addRoom(r1);

        removeRoom(r1);
        removeRoom(r1);
    }

    public static void addRoom(Room r){
        if (getRoom(r.getNumber()) == null){
            roomList.add(r);
            System.out.println("Room "+r.getNumber()+" was added to roomList.");
        }
        else{
            System.out.println("Room " + r.getNumber() + " already exists!");
        }
    }

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
     * exports a CSV containing all Room data.
     */
    public static void roomsToCSV(){
        String s = "number,type,capacity,occupants,rate,balcony\n";
        for(int i = 0; i < roomList.size(); i++){
            if(roomList.get(i).getType().equalsIgnoreCase("suite")){
                Suite room = (Suite) roomList.get(i);
                s += room.getNumber() + ",";
                s += room.getType() + ",";
                s += room.getOccupancy() + ",";
                s += room.getOccupants() + ",";
                s += room.getRate() + ",";
                s += room.hasBalcony()+"\n";
            }
            else {
                Room room = (Room) roomList.get(i);
                s += room.getNumber() + ",";
                s += room.getType() + ",";
                s += room.getOccupancy() + ",";
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
    public static void roomsFromCSV(){
        try{
            File file = new File("data/rooms.csv");
            java.util.Scanner input = new java.util.Scanner(file);
            while(input.hasNext()){
                String[] lineBreak = input.nextLine().split(",");
                int number = Integer.parseInt(lineBreak[0]);
                String type = lineBreak[1];
                int cap = Integer.parseInt(lineBreak[2]);
                int occ = Integer.parseInt(lineBreak[3]);
                int rate = Integer.parseInt(lineBreak[4]);
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
                        roomList.add(new Room(number,cap,true,rate));
                        roomList.get(roomList.size()-1).setOccupancy(occ);
                    }
                    else{
                        roomList.add(new Room(number,cap,false,rate));
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
}