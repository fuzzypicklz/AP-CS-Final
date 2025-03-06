package HotelManagement.Management;

import java.util.ArrayList;

import HotelManagement.Rooms.Room;
import HotelManagement.Rooms.Suite;

import java.io.File;
import java.io.IOException;

public class RoomManagement{
    public ArrayList roomList = new ArrayList<Room>();

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

    public String getRoomsString(){
        String s = "";
        for(int i = 0; i < roomList.size(); i++){
            Room room = (Room) roomList.get(i);
            s += room.getNumber() + "\n";
        }
        return s;
    }

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