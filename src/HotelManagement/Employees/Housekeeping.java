package HotelManagement.Employees;

import java.util.ArrayList;

import HotelManagement.Rooms.Room;

import java.io.File;
import java.io.IOException;

public class Housekeeping extends Employee{
    public ArrayList<Room> roomAssignment = new ArrayList<Room>();
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

    public ArrayList<Room> getRooms(){
        return roomAssignment;
    }
    /*
     * Sends the assigned rooms to a formatted String.
     */
    public String getRoomsString(){
        String s = "";
        for(int i = 0; i < roomAssignment.size(); i++){
            Room room = (Room) roomAssignment.get(i);
            s += "\n"+ room.getNumber();
        }
        return s;
    }
    /*
     * Exports a CSV associated with the Employee of the list of rooms they have been assigned
     */
    public void roomsToCSV(){
        String s = "";
        for(int i = 0; i < roomAssignment.size(); i++){
            Room room = (Room) roomAssignment.get(i);
            s += room.getNumber() + ",";
        }
        try{
            File file = new File("/data"+getID()+getLname()+getFname()+"rooms.csv");
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
     * Imports a CSV associated with the Employee of the rooms they hav ebeen assigned.
     */
    public void roomsFromCSV(){
        try{
            File file = new File("/data"+getID()+getLname()+getFname()+"rooms.csv");
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
    
    public String toString(){
        if (roomAssignment.size()>0) return super.toString() +
               "\nAssigned rooms:"+getRoomsString();
        else return super.toString();
    }
}
