package HotelManagement.Employees;

import java.util.ArrayList;

import HotelManagement.Rooms.Room;

import java.io.File;
import java.io.IOException;

public class Housekeeping extends Employee{
    ArrayList roomAssignment = new ArrayList<Room>();
    public Housekeeping(int id, String f, String l){
        super(id, f, l, "Housekeeping");
    }

    public Housekeeping(int id, String f, String l, ArrayList rooms){
        super(id, f, l, "Housekeeping");
        roomAssignment = rooms;
    }

    public void addRoom(Room r){
        roomAssignment.add(r);
    }

    public void removeRoom(Room r){
        roomAssignment.remove(r);
    }

    public ArrayList getRooms(){
        return roomAssignment;
    }

    public String getRoomsString(){
        String s = "";
        for(int i = 0; i < roomAssignment.size(); i++){
            Room room = (Room) roomAssignment.get(i);
            s += room.getNumber() + "\n";
        }
        return s;
    }

    public void roomsToCSV(){
        String s = "";
        for(int i = 0; i < roomAssignment.size(); i++){
            Room room = (Room) roomAssignment.get(i);
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
                String s = input.next();
                Room r = new Room(Integer.parseInt(s));
                roomAssignment.add(r);
            }
            input.close();
            System.out.println("Rooms loaded from file.");
        } catch (IOException e){
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
}
