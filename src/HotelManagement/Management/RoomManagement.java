package HotelManagement.Management;

import java.util.ArrayList;

import HotelManagement.Rooms.Room;
import HotelManagement.Rooms.Suite;

import java.io.File;
import java.io.IOException;

public class RoomManagement{
    public ArrayList roomList = new ArrayList<Room>();
    /*
     * Debugging method to add rooms for testing purposes
     */
    public void roomDebug(){
        Room r1 = new Room(134);
        Room r2 = new Room(135, 5, false, 150.00);
        Suite s1 = new Suite(1236);
        Suite s2 = new Suite(1237, 5, false, true, 200.00);
        addRoom(r1);
        addRoom(r2);
        addRoom(s1);
        addRoom(s2);
    }

    public void addRoom(Room r){
        roomList.add(r);
    }

    public void removeRoom(Room r){
        roomList.remove(r);
    }
    
    public ArrayList<Room> getRoomList(){
        return roomList;
    }
    /*
     * Sends the assigned rooms to a formatted String.
     */
    public String getRoomsString(){
        String s = "";
        for(int i = 0; i < roomList.size(); i++){
            Room room = (Room) roomList.get(i);
            s += room.getNumber() + "\n";
        }
        return s;
    }
    /*
     * Returns a room object based on the room number
     */
    public Room getRoom(int number){
        for (int i = 0; i < roomList.size(); i++){
            Room room = (Room) roomList.get(i);
            if (room.getNumber() == number){
                return room;
            }
        }
        System.out.println("Room not found.");
        return null;
    }
    /*
     * Exports a CSV associated with the list of rooms
     */
    public void roomsToCSV(){
        String s = "";
        for(int i = 0; i < roomList.size(); i++){
            Room room = (Room) roomList.get(i);
            s += room.getNumber() + ",";
        }
        try{
            File file = new File("rooms.csv");
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
     * Loads rooms from a CSV file
     */
    public void roomsFromCSV(){
        try{
            File file = new File("rooms.csv");
            java.util.Scanner input = new java.util.Scanner(file);
            while(input.hasNext()){
                int roomNumber = input.nextInt();
                Room room = new Room(roomNumber);
                roomList.add(room);
            }
            System.out.println("Rooms loaded from file.");
        } catch (IOException e){
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
}