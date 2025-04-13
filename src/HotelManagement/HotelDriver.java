package HotelManagement;

import HotelManagement.Management.EmployeeManagement;
import HotelManagement.Management.RoomManagement;

import HotelManagement.Employees.*;
import HotelManagement.Rooms.*;

import java.util.Scanner;
import java.util.Date;
import java.util.ArrayList;

/**
 * The main driver class for the hotel management system.
 * Provides interfaces for administrators and customers to interact with the system.
 */
public class HotelDriver {
    /**
     * Main entry point for the Hotel Management System.
     * Provides options for Admin and Customer interfaces.
     * @param Args Command-line arguments (not used).
     */
    public static void main(String[] Args){
        while (true){
            System.out.println("\nWelcome to the Hotel Management System");
            System.out.println("1. Admin Interface");
            System.out.println("2. Customer Interface");
            System.out.print(">> ");
            Scanner input = new Scanner(System.in);
            String choice = input.nextLine();
            System.out.println("");
            if(choice.equals("1")){
                
                AdminInterface();
            }
            if(choice.equals("2")){
                CustomerInterface();
            }
        }
    }
    
    /**
     * Provides the Admin interface for managing employees and rooms.
     */
    public static void AdminInterface(){
        Scanner input = new Scanner(System.in);
        String choice = "";
        while (!choice.equalsIgnoreCase("exit") && !choice.equalsIgnoreCase("e")) {
            System.out.println("Hotel Management System");
            System.out.println("1. Employee Management");
            System.out.println("2. Room Management");
            System.out.println("Type 'exit' to quit.");
            System.out.print(">> ");
            choice = input.nextLine();
            switch (choice){
                case "1":
                    EmployeeSystem();
                    break;
                case "2":
                    RoomSystem();
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
    
    /**
     * Provides the Employee Management System interface.
     */
    public static void EmployeeSystem(){
        Scanner input = new Scanner(System.in);
        String choice = "";
        while (!choice.equalsIgnoreCase("exit") && !choice.equalsIgnoreCase("e")) {
            System.out.println("\nEmployee Management System");
            System.out.println("1. Add Employee");
            System.out.println("2. Remove Employee");
            System.out.println("3. Modify Employee");
            System.out.println("4. View Employee");
            System.out.println("5. Export Employee Summary");
            System.out.println("6. Export Employees to CSV");
            System.out.println("7. Import Employees from CSV");
            System.out.println("Type 'exit' to quit.");
            System.out.print(">> ");
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
                    break;
                case "5":
                    ExportEmployeeSummaryLogic();
                    break;
                case "6":
                    EmployeeManagement.employeesToCSV();
                    break;
                case "7":
                    EmployeeManagement.employeesFromCSV();
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

            System.out.println("\n");
        }
    }

    /**
     * Logic for adding a new employee.
     */
    public static void AddEmployeeLogic(){
        Scanner input = new Scanner(System.in);
        System.out.print("\nEnter employee first and last names:\n>> ");
        String name = input.nextLine();
        String[] names = name.split(" ");

        if (names.length != 2) {
            System.out.println("Please enter both first and last names.");
            return;
        }

        System.out.print("Enter employee position (or leave empty for intern):\n>> ");
        String position = input.nextLine();

        if (position.isEmpty() || position.equalsIgnoreCase("intern")) {
            EmployeeManagement.addIntern(names[0], names[1]);
        }
        else {
            EmployeeManagement.addEmployee(names[0], names[1], position);
        }
        System.out.println("Employee added successfully.");
    }

    /**
     * Logic for removing an existing employee.
     */
    public static void RemoveEmployeeLogic(){
        Scanner input = new Scanner(System.in);
        System.out.print("\nRemove employee by:\n1. ID\n2. Name\n>> ");
        String choice = input.nextLine();
        if (choice.equals("1")) {
            System.out.print("Enter employee ID:\n>> ");
            int id = input.nextInt();
            EmployeeManagement.removeEmployee(EmployeeManagement.getEmployee(id));
        } else if (choice.equals("2")) {
            System.out.print("Enter employee first and last names:\n>> ");
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

    /**
     * Logic for modifying an employee's details.
     */
    public static void ModifyEmployeeLogic(){
        Scanner input = new Scanner(System.in);
        System.out.print("\nChoose ID method:\n1. ID\n2. Full name\n>> ");
        int choice = input.nextInt();
        if(choice == 1){
            System.out.print("Input ID\n>> ");
            int ID = input.nextInt();
            Employee employee = EmployeeManagement.getEmployee(ID);

            if(employee == null){
                return;
            }

            System.out.println("\nEmployee detail to change:");
            System.out.println("1. Name");
            System.out.println("2. ID");
            System.out.println("3. Position");
            System.out.print(">> ");


        }
        else if(choice == 2){
            System.out.print("Input Full name\n>> ");
            String name = input.nextLine();
            String[] names = name.split(" ");
            Employee employee = EmployeeManagement.getEmployee(names[0], names[1]);

            if(employee == null){
                return;
            }

            System.out.println("\nEmployee detail to change:");
            System.out.println("1. Name");
            System.out.println("2. ID");
            System.out.println("3. Position");
            System.out.println("4. Wage");
            System.out.print(">> ");

            int j = input.nextInt();

            if(j == 1){
                System.out.print("Input new full name\n>> ");
                names = input.nextLine().split(" ");
                employee.setName(names[0], names[1]);
            }
            else if(j == 2){
                System.out.print("Input new ID\n>> ");
                int id = input.nextInt();
                employee.setID(id);
            }
            else if(j == 3){
                System.out.print("Input new Position\n>> ");
                String p = input.nextLine();
                if(p.equalsIgnoreCase("housekeeping")){
                    Housekeeping newHouseKeeping = new Housekeeping(employee.getID(), employee.getFname(), employee.getLname());
                    newHouseKeeping.setWage(employee.getWage());
                }
                else if(p.equalsIgnoreCase("manager")){
                    Manager newHouseManager = new Manager(employee.getID(), employee.getFname(), employee.getLname());
                    newHouseManager.setWage(employee.getWage());
                }
                else{
                    employee.setPosition(p.toLowerCase());
                }
            }
            else if(j == 4){
                System.out.print("Input new Wage\n>> ");
                double id = input.nextDouble();
                employee.setWage(id);
            }
        }
        else{
            System.out.print("Invalid choice. please input 1 or 2.");
            return;
        }

        

        
    }

    /**
     * Logic for viewing an employee's details.
     */
    public static void ViewEmployeeLogic(){
        Scanner input = new Scanner(System.in);
        System.out.print("\nChoose ID method:\n1. ID\n2. Full name\n>> ");
        int choice = input.nextInt();
        if(choice == 1){
            System.out.print("Input ID\n>> ");
            int ID = input.nextInt();
            Employee employee = EmployeeManagement.getEmployee(ID);
            if(employee != null){
                System.out.println(employee.toString());
            } else {
                System.out.println("Employee not found.");
            }
        }
        if(choice == 2){
            System.out.print("Input Full name\n>> ");
            String[] names = input.nextLine().split(" ");
            Employee employee = EmployeeManagement.getEmployee(names[0], names[1]);
            if(employee != null){
                System.out.println(employee.toString());
            } else {
                System.out.println("Employee not found.");
            }
        }
        
    }

    /**
     * Logic for exporting an employee's summary.
     */
    public static void ExportEmployeeSummaryLogic(){
        Scanner input = new Scanner(System.in);
        System.out.print("\nChoose ID method:\n1. ID\n2. Full name\n>> ");
        int choice = input.nextInt();
        if(choice == 1){
            System.out.print("Input ID\n>> ");
            int ID = input.nextInt();
            Employee employee = EmployeeManagement.getEmployee(ID);
            if(employee != null){
                EmployeeManagement.employeeSummary(employee);
            } else {
                System.out.println("Employee not found.");
            }
        }
        else if(choice == 2){
            System.out.print("Input Full name\n>> ");
            String[] names = input.nextLine().split(" ");
            Employee employee = EmployeeManagement.getEmployee(names[0], names[1]);
            if(employee != null){
                EmployeeManagement.employeeSummary(employee);
            } else {
                System.out.println("Employee not found.");
            }
        }
        else{
            System.out.println("Invalid choice. please input 1 or 2.");
            return;
        }
    }

    /**
     * Provides the Room Management System interface.
     */
    public static void RoomSystem(){
        Scanner input = new Scanner(System.in);
        String choice = "";
        while (!choice.equalsIgnoreCase("exit") && !choice.equalsIgnoreCase("e")) {
            System.out.println("\nRoom Management System");
            System.out.println("1. Add Room");
            System.out.println("2. Remove Room");
            System.out.println("3. Modify Room");
            System.out.println("4. View Room");
            System.out.println("5. Room Browser");
            System.out.println("6. Export Room Summary");
            System.out.println("7. Export Rooms to CSV");
            System.out.println("8. Import Rooms from CSV");
            System.out.println("Type 'exit' to quit.");
            System.out.print(">> ");
            choice = input.nextLine();
            switch (choice.toLowerCase()){
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
                    AdminBrowseRoomsLogic();
                case "6":
                    RoomSummaryLogic();
                    break;
                case "7":
                    RoomManagement.roomsToCSV();
                    break;
                case "8":
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
            
            System.out.println("\n");
        }
    }

    /**
     * Logic for adding a new room.
     */
    public static void AddRoomLogic(){
        Scanner input = new Scanner(System.in);
        System.out.print("\nEnter room type (1 for Standard, 2 for Suite):\n>> ");
        int roomType = input.nextInt();
        if (roomType != 1 && roomType != 2) {
            System.out.print("Invalid room type. Please enter 1 for Standard or 2 for Suite.");
            return;
        }

        else if(roomType == 2){
            System.out.print("Enter room number:\n>> ");
            int number = input.nextInt();
            System.out.print("Enter room capacity:\n>> ");
            int capacity = input.nextInt();
            System.out.print("Enter room rate:\n>> ");
            double rate = input.nextDouble();
            input.nextLine(); // Consume leftover newline
            System.out.print("Is the room occupied? (y/n)\n>> ");
            String occupied = input.nextLine();
            System.out.print("has balcony? (y/n)\n>> ");
            String hasBalcony = input.nextLine();
            RoomManagement.addRoom(new Suite(number, capacity, occupied.equalsIgnoreCase("y"), hasBalcony.equalsIgnoreCase("y"), rate));
        }

        else if(roomType == 1){
            System.out.print("Enter room number:\n>> ");
            int number = input.nextInt();
            System.out.print("Enter room capacity:\n>> ");
            int capacity = input.nextInt();
            System.out.print("Enter room rate:\n>> ");
            double rate = input.nextDouble();
            input.nextLine(); // Consume leftover newline
            System.out.print("Is the room occupied? (y/n)\n>> ");
            String occupied = input.nextLine();
            RoomManagement.addRoom(new Standard(number, capacity, occupied.equalsIgnoreCase("y"), rate));
        }
    }

    /**
     * Logic for removing an existing room.
     */
    public static void RemoveRoomLogic(){
        Scanner input = new Scanner(System.in);
        System.out.print("\nEnter room number to remove:\n>> ");
        int number = input.nextInt();
        RoomManagement.removeRoom(RoomManagement.getRoom(number));
    }

    /**
     * Logic for modifying a room's details.
     */
    public static void ModifyRoomLogic(){
        Scanner input = new Scanner(System.in);
        System.out.print("\nEnter room number to modify:\n>> ");
        int number = input.nextInt();
        Room room = RoomManagement.getRoom(number);
        if (room == null) {
            return;
        }
        System.out.println("\nRoom detail to change:");
        System.out.println("1. Room capacity");
        System.out.println("2. Room rate");
        System.out.println("3. Room occupancy");
        System.out.println("4. Room type");
        System.out.print(">> ");
        input.nextLine(); // Consume leftover newline
        String choice = input.nextLine();

        switch(choice){
            case "1":
                System.out.print("Enter new room capacity:\n>> ");
                int newCapacity = input.nextInt();
                room.setCapacity(newCapacity);
                break;
            case "2":
                System.out.print("Enter new room rate:\n>> ");
                double newRate = input.nextDouble();
                room.setRate(newRate);
                break;
            case "3":
                System.out.print("How many people are in the room?\n>> ");
                int customers = input.nextInt();
                room.setOccupancy(customers);
                break;
            case "4":
                System.out.print("Enter new room type (1 for Suite, 2 for Standard):\n>> ");
                int roomType = input.nextInt();
                if (roomType == 1) {
                    room = new Suite(room.getNumber(), room.getCapacity(), room.getCapacity()>0, ((Suite) room).hasBalcony(), room.getRate());
                } else if (roomType == 2) {
                    room = new Standard(room.getNumber(), room.getCapacity(), room.getCapacity()>0, room.getRate());
                } else {
                    System.out.println("Invalid room type.");
                }
                break;
            default:
                System.out.println("Invalid choice. Please try again.");
                break;
        }
    }

    /**
     * Logic for viewing room details.
     */
    public static void ViewRoomsLogic(){
        Scanner s = new Scanner(System.in);
        System.out.println(RoomManagement.getRoomListString());
        System.out.print("Input room number for more details or type exit.\n>> ");
        String input = s.nextLine();
        if (!input.equalsIgnoreCase("exit") && !input.equalsIgnoreCase("e")) {
            Room room = RoomManagement.getRoom(Integer.parseInt(input));
            if(room!=null){
                System.out.println("\n"+room.toString());
                s.nextLine();
            }
        }
        else{
            System.out.println("Exiting...");
            return;
        }
    }

    /**
     * Logic for admin browsing rooms.
     */
    public static void AdminBrowseRoomsLogic(){
        System.out.println("Rooms:");
        System.out.println(RoomManagement.getRoomListString());

        System.out.println("Room Browser");

        System.out.println("1. Change search parameters:");
        
        System.out.print(">> ");
        Scanner input = new Scanner(System.in);
        String choice = input.nextLine();

        if(choice.equals("1")){
            System.out.println("Room Parameters:");
            System.out.println("1. Room Type");
            System.out.println("2. Room Rate");
            System.out.println("3. Room Capacity");
            System.out.println("4. Occcupied status: ");
            System.out.println("5. Room Occupants");


            System.out.print(">> ");

            String paramChoice = input.nextLine();

            if(paramChoice.equals("1")){
                System.out.print("Enter room type (1 for Standard, 2 for Suite):\n>> ");
                int roomType = input.nextInt();
                if(roomType == 1){
                    for (Room r : RoomManagement.roomList) {
                        if (!(r instanceof Suite)) {
                            System.out.println(r.getNumber());
                        }
                    }
                }
                else if(roomType == 2){
                    for (Room r : RoomManagement.roomList) {
                        if (r instanceof Suite) {
                            System.out.println(r.getNumber());
                        }
                    }
                }
                else{
                    System.out.println("Invalid room type. Please enter 1 for Suite or 2 for Standard.");
                }
            }
            else if(paramChoice.equals("2")){
                System.out.print("Enter minimum room rate:\n>> ");
                double minRate = input.nextDouble();
                System.out.print("Enter maximum room rate:\n>> ");
                double maxRate = input.nextDouble();
                for (Room r : RoomManagement.roomList) {
                    if (r.getRate() <= maxRate && r.getRate() >= minRate) {
                        System.out.println(r.getNumber());
                    }
                }
            }
            else if(paramChoice.equals("3")){
                System.out.print("Enter minimum room capacity:\n>> ");
                int minCapacity = input.nextInt();
                System.out.print("Enter maximum room capacity:\n>> ");
                int maxCapacity = input.nextInt();
                for (Room r : RoomManagement.roomList) {
                    if (r.getCapacity() <= maxCapacity && r.getCapacity() >= minCapacity) {
                        System.out.println(r.getNumber());
                    }
                }
            }
            else if(paramChoice.equals("4")){
                System.out.print("Enter occupied status (1 for occupied, 0 for unoccupied):\n>> ");
                int occupiedStatus = input.nextInt();
                for (Room r : RoomManagement.roomList) {
                    if ((occupiedStatus == 1 && r.isOccupied()) || (occupiedStatus == 0 && !r.isOccupied())) {
                        System.out.println(r.getNumber());
                    }
                }
            }
            else if(paramChoice.equals("5")){
                System.out.print("Enter minimum number of occupants:\n>> ");
                int minOccupants = input.nextInt();
                System.out.print("Enter maximum number of occupants:\n>> ");
                int maxOccupants = input.nextInt();
                for (Room r : RoomManagement.roomList) {
                    if (r.getOccupants() <= maxOccupants && r.getOccupants() >= minOccupants) {
                        System.out.println(r.getNumber());
                    }
                }
            }
            else{
                System.out.println("Invalid choice, please try again.");
            }
        }
        else{
            System.out.println("Invalid choice, please try again.");
        }
    }

    /**
     * Logic for exporting a room's summary.
     */
    public static void RoomSummaryLogic(){
        Scanner s = new Scanner(System.in);
        RoomManagement.getRoomListString();
        System.out.print("Input room number for summary or type exit.\n>> ");
        String input = s.nextLine();
        if (!input.equalsIgnoreCase("exit") && !input.equalsIgnoreCase("e") && !input.equals("")) {
            Room room = RoomManagement.getRoom(Integer.parseInt(input));
            if(room!=null){
                System.out.println(room.toString());
            }
            else{
                System.out.println("Room not found.");
            }
        }
    }

    /**
     * Provides the Customer interface for booking and browsing rooms.
     */
    public static void CustomerInterface(){
        Scanner input = new Scanner(System.in);
        String choice = "";

        while (!choice.equalsIgnoreCase("exit") && !choice.equalsIgnoreCase("exit")) {
            System.out.println("\nWelcome to the Customer Interface");
            System.out.println("1. Book Room");
            System.out.println("2. Browse Rooms");
            System.out.println("3. View Room Details");
            System.out.print(">> ");
            choice = input.nextLine();
            if(choice.equals("1")){
                RoomBookingLogic();
            }
            else if(choice.equals("2")){
                BrowseRoomsLogic();
            }
            else if(choice.equals("3")){
                ViewRoomsLogic();
            }
            else if(choice.equalsIgnoreCase("exit") || choice.equalsIgnoreCase("e")){
                System.out.println("Exiting...");
                return;
            }
            else{
                System.out.println("Invalid choice, please try again.");
            }
            
        }
    }

    /**
     * Logic for booking a room.
     */
    public static void RoomBookingLogic(){
        System.out.print("Input room number\n>> ");
        Scanner input = new Scanner(System.in);
        int number = input.nextInt();
        Room room = RoomManagement.getRoom(number);

        if(room != null && room.getOccupants()==0){
            System.out.println("Room " + number + " is available for booking.");
            System.out.print("Please input your name.\n>> ");
            input.nextLine(); // Consume leftover newline
            String name = input.nextLine();
            System.out.print("Please enter the date of booking in MM/DD/YYYY format\n>> ");
            String dateInput = input.nextLine();
            String[] dateParts = dateInput.split("/");

            if (dateParts.length != 3) {
                System.out.println("Invalid date format. Please use MM/DD/YYYY.");
                return;
            }
            Date dayIn = new Date(Integer.parseInt(dateParts[2]),
                    Integer.parseInt(dateParts[0]) - 1,
                    Integer.parseInt(dateParts[1]));

            System.out.print("How many days are you staying? \n>> ");
            int days = input.nextInt();
            double totalCost = room.getRate() * days;
            input.nextLine(); // Consume leftover newline
            System.out.print("How many people are staying in the room? \n>> ");
            int customers = input.nextInt();
            input.nextLine(); // Consume leftover newline
            if(customers > room.getCapacity()){
                System.out.println("Too many people! pick another room");
                return;
            }
            System.out.println("Total cost for " + days + " days is: $" + totalCost);
            System.out.print("Would you like to book this room? (y/n)\n>> ");
            String choice = input.next();
            if(choice.equalsIgnoreCase("y")){
                
                room.setOccupancy(customers); // Assuming 1 person for simplicity
                System.out.println("Room " + number + " has been booked. you have been charged "+totalCost+" for "+days+" days stay.");
                RoomManagement.PrintRecipt(name, days, dayIn, room); // Assuming this method prints a receipt for the booking, you can implement it as needed
            }
            else{
                System.out.println("Booking cancelled.");
            }
        }
        else{
            System.out.println("Room " + number + " is not availabe.");
        }
    }

    /**
     * Logic for browsing available rooms.
     */
    public static void BrowseRoomsLogic(){
        System.out.println("Available Rooms:");
        System.out.println(RoomManagement.getUnoccupiedRoomListString());

        System.out.println("Room Browser");

        System.out.println("1. Change search parameters:");
        
        System.out.print(">> ");
        Scanner input = new Scanner(System.in);
        String choice = input.nextLine();

        if(choice.equals("1")){
            System.out.println("Room Parameters:");
            System.out.println("1. Room Type");
            System.out.println("2. Room Rate");
            System.out.println("3. Room Capacity");

            System.out.print(">> ");

            String paramChoice = input.nextLine();

            if(paramChoice.equals("1")){
                System.out.print("Enter room type (1 for Standard, 2 for Suite):\n>> ");
                int roomType = input.nextInt();
                if(roomType == 1){
                    for (Room r : RoomManagement.roomList) {
                        if (!(r instanceof Suite)) {
                            System.out.println(r.getNumber());
                        }
                    }
                }
                else if(roomType == 2){
                    for (Room r : RoomManagement.roomList) {
                        if (r instanceof Suite) {
                            System.out.println(r.getNumber());
                        }
                    }
                }
                else{
                    System.out.println("Invalid room type. Please enter 1 for Suite or 2 for Standard.");
                }
            }
            else if(paramChoice.equals("2")){
                System.out.print("Enter minimum room rate:\n>> ");
                double minRate = input.nextDouble();
                System.out.print("Enter maximum room rate:\n>> ");
                double maxRate = input.nextDouble();
                for (Room r : RoomManagement.roomList) {
                    if (r.getRate() <= maxRate && r.getRate() >= minRate) {
                        System.out.println(r.getNumber());
                    }
                }
            }
            else if(paramChoice.equals("3")){
                System.out.print("Enter minimum room capacity:\n>> ");
                int minCapacity = input.nextInt();
                System.out.print("Enter maximum room capacity:\n>> ");
                int maxCapacity = input.nextInt();
                for (Room r : RoomManagement.roomList) {
                    if (r.getCapacity() <= maxCapacity && r.getCapacity() >= minCapacity) {
                        System.out.println(r.getNumber());
                    }
                }
            }
        }
        else{
            System.out.println("Invalid choice, please try again.");
        }
    }
    
    /**
     * Logic for viewing room details from the customer interface.
     * This method allows customers to view details of available rooms.
     */
    public static void CustomerViewRoomsLogic(){
        Scanner s = new Scanner(System.in);
        System.out.println(RoomManagement.getUnoccupiedRoomListString());
        System.out.print("Input room number for more details or type exit.\n>> ");
        String input = s.nextLine();
        if (!input.equalsIgnoreCase("exit") && !input.equalsIgnoreCase("e")) {
            Room room = RoomManagement.getRoom(Integer.parseInt(input));
            if(room!=null){
                System.out.println("\n"+room.toString());
                s.nextLine();
            }
        }
        else{
            System.out.println("Exiting...");
            return;
        }

    }
}
