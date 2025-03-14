package HotelManagement;

import HotelManagement.Management.EmployeeManagement;
import HotelManagement.Management.RoomManagement;

import HotelManagement.Employees.*;
import HotelManagement.Rooms.*;

import java.util.Scanner;

import javax.swing.plaf.ViewportUI;

import java.util.ArrayList;

public class HotelDriver {
    public static void main(String[] Args){
        while (true){
            System.out.println("Welcome to the Hotel Management System");
            System.out.println("1. Admin Interface");
            System.out.println("2. Customer Interface");
            System.out.println(">> ");
            Scanner input = new Scanner(System.in);
            String choice = input.nextLine();
        }
    }
    public static void AdminInterface(){
        Scanner input = new Scanner(System.in);
        String choice = "";
        while (!choice.equalsIgnoreCase("exit") || choice.equalsIgnoreCase("e")) {
            System.out.println("Hotel Management System");
            System.out.println("1. Employee Management");
            System.out.println("2. Room Management");
            System.out.println("3. Customer Interface");
            System.out.println("Type 'exit' to quit.");
            System.out.println(">> ");
            choice = input.nextLine();
            switch (choice){
                case "1":
                    EmployeeManagement.EmployeeDebug();
                    break;
                case "2":
                    RoomManagement roomM = new RoomManagement();
                    roomM.roomDebug();
                    break;
                case "3":
                    //CustomerInterface();
                    break;
                case "exit":
                    System.out.println("Exiting...");
                    break;
                case "e":
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid choice, please try again.");
            }
        }
    }
    public static void EmployeeSystem(){
        Scanner input = new Scanner(System.in);
        String choice = "";
        while (!choice.equalsIgnoreCase("exit") || choice.equalsIgnoreCase("e")) {
            System.out.println("Employee Management System");
            System.out.println("1. Add Employee");
            System.out.println("2. Remove Employee");
            System.out.println("3. Modify Employee");
            System.out.println("4. View Employee");
            System.out.println("5. Export Employee Summary");
            System.out.println("6. Export Employees to CSV");
            System.out.println("7. Import Employees from CSV");
            System.out.println("Type 'exit' to quit.");
            System.out.println(">> ");
            choice = input.nextLine();
            switch (choice){
                case "1":
                    AddEmployeeLogic();
                    break;
                case "2":
                    RemoveEmployeeLogic();
                    break;
                case "3":
                    ModifyEmployeeLogic();
                    break;
                case "4":
                    ViewEmployeeLogic();
                case "5":
                    ExportEmployeeSummaryLogic();
                case "6":
                    EmployeeManagement.employeesToCSV();
                case "7":
                    EmployeeManagement.employeesFromCSV();
                case "exit":
                    System.out.println("Exiting...");
                    break;
                case "e":
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid choice, please try again.");
            }

            System.out.println("\n\n");
        }
    }

    public static void AddEmployeeLogic(){
        Scanner input = new Scanner(System.in);
        System.out.println("Enter employee first and last names:\n>> ");
        String name = input.nextLine();
        String[] names = name.split(" ");

        if (names.length != 2) {
            System.out.println("Please enter both first and last names.");
            return;
        }

        System.out.println("Enter employee position (or leave empty for intern):\n>> ");
        String position = input.nextLine();

        if (position.isEmpty() || position.equalsIgnoreCase("intern")) {
            EmployeeManagement.addIntern(names[0], names[1]);
        }
        else {
            EmployeeManagement.addEmployee(names[0], names[1], position);
        }
        System.out.println("Employee added successfully.");
    }

    public static void RemoveEmployeeLogic(){
        Scanner input = new Scanner(System.in);
        System.out.println("Remove employee by:\n1. ID\n2. Name\n>> ");
        String choice = input.nextLine();
        if (choice.equals("1")) {
            System.out.println("Enter employee ID:\n>> ");
            int id = input.nextInt();
            EmployeeManagement.removeEmployee(EmployeeManagement.getEmployee(id));
        } else if (choice.equals("2")) {
            System.out.println("Enter employee first and last names:\n>> ");
            String name = input.nextLine();
            String[] names = name.split(" ");
            if (names.length != 2) {
                System.out.println("Please enter both first and last names.");
                return;
            }
            EmployeeManagement.removeEmployee(EmployeeManagement.getEmployee(names[0], names[1]));
        } else {
            System.out.println("Invalid choice.");
        }
    }

    public static void ModifyEmployeeLogic(){
        // TODO
    }

    public static void ViewEmployeeLogic(){
        // TODO
    }

    public static void ExportEmployeeSummaryLogic(){
        // TODO
    }

    public static void RoomSystem(){
        Scanner input = new Scanner(System.in);
        String choice = "";
        while (!choice.equalsIgnoreCase("exit") || choice.equalsIgnoreCase("e")) {
            System.out.println("Room Management System");
            System.out.println("1. Add Room");
            System.out.println("2. Remove Room");
            System.out.println("3. Modify Room");
            System.out.println("4. View Room");
            System.out.println("5. Export Room Summary");
            System.out.println("6. Export Rooms to CSV");
            System.out.println("7. Import Rooms from CSV");
            System.out.println("Type 'exit' to quit.");
            System.out.println(">> ");
            choice = input.nextLine();
            switch (choice){
                case "1":
                    AddRoomLogic();
                    break;
                case "2":
                    RemoveRoomLogic();
                    break;
                case "3":
                    ModifyRoomLogic();
                    break;
                case "4":
                    ViewRoomsLogic();
                    break;
                case "5":
                    RoomSummaryLogic();
                    break;
                case "6":
                    RoomManagement.roomsToCSV();
                    break;
                case "7":
                    RoomManagement.roomsFromCSV();
                    break;
                case "exit":
                    System.out.println("Exiting...");
                    break;
                case "e":
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid choice, please try again.");
            }
        }
    }

    public static void AddRoomLogic(){
        Scanner input = new Scanner(System.in);
        System.out.println("Enter room type (1 for Suite, 2 for Standard):\n>> ");
        int roomType = input.nextInt();
        if (roomType != 1 && roomType != 2) {
            System.out.println("Invalid room type. Please enter 1 for Suite or 2 for Standard.");
            return;
        }

        else if(roomType == 1){
            System.out.println("Enter room number:\n>> ");
            int number = input.nextInt();
            System.out.println("Enter room capacity:\n>> ");
            int capacity = input.nextInt();
            System.out.println("Enter room rate:\n>> ");
            double rate = input.nextDouble();
            System.out.println("Is the room occupied? (y/n)\n>> ");
            String occupied = input.nextLine();
            System.out.println("has balcony? (y/n)\n>> ");
            String hasBalcony = input.nextLine();
            RoomManagement.addRoom(new Suite(number, capacity, occupied.equalsIgnoreCase("y"), hasBalcony.equalsIgnoreCase("y"), rate));
        }

        else if(roomType == 2){
            System.out.println("Enter room number:\n>> ");
            int number = input.nextInt();
            System.out.println("Enter room capacity:\n>> ");
            int capacity = input.nextInt();
            System.out.println("Enter room rate:\n>> ");
            double rate = input.nextDouble();
            System.out.println("Is the room occupied? (y/n)\n>> ");
            String occupied = input.nextLine();
            RoomManagement.addRoom(new Room(number, capacity, occupied.equalsIgnoreCase("y"), rate));
        }
    }

    public static void RemoveRoomLogic(){
        Scanner input = new Scanner(System.in);
        System.out.println("Enter room number to remove:\n>> ");
        int number = input.nextInt();
        RoomManagement.removeRoom(RoomManagement.getRoom(number));
    }

    public static void ModifyRoomLogic(){
        Scanner input = new Scanner(System.in);
        System.out.println("Enter room number to modify:\n>> ");
        int number = input.nextInt();
        Room room = RoomManagement.getRoom(number);
        if (room == null) {
            return;
        }
        System.out.println("Room detail to change:\n>> ");
        System.out.println("1. Room capacity");
        System.out.println("2. Room rate");
        System.out.println("3. Room occupancy");
        System.out.println("4. Room type");
        System.out.println(">> ");
        String choice = input.nextLine();

        switch(choice){
            case "1":
                System.out.println("Enter new room capacity:\n>> ");
                int newCapacity = input.nextInt();
                room.setCapacity(newCapacity);
                break;
            case "2":
                System.out.println("Enter new room rate:\n>> ");
                double newRate = input.nextDouble();
                room.setRate(newRate);
                break;
            case "3":
                System.out.println("How many people are in the room?\n>> ");
                int customers = input.nextInt();
                room.setOccupancy(customers);
                break;
            case "4":
                System.out.println("Enter new room type (1 for Suite, 2 for Standard):\n>> ");
                int roomType = input.nextInt();
                if (roomType == 1) {
                    room = new Suite(room.getNumber(), room.getOccupancy(), room.getOccupancy()>0, ((Suite) room).hasBalcony(), room.getRate());
                } else if (roomType == 2) {
                    room = new Room(room.getNumber(), room.getOccupancy(), room.getOccupancy()>0, room.getRate());
                } else {
                    System.out.println("Invalid room type.");
                }
                break;
            default:
                System.out.println("Invalid choice. Please try again.");
                break;
        }
    }

    public static void ViewRoomsLogic(){
        // TODO
    }

    public static void RoomSummaryLogic(){
        // TODO
    }
}
