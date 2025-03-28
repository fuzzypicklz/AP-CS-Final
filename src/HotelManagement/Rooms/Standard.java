package HotelManagement.Rooms;

import java.util.Date;

public class Standard implements Room {
    private int number;
    private int capacity = 2;
    private double rate = 100.00;
    private boolean occupied = false;
    private int occupants = 0;

    public Standard(int number, int capacity, boolean occupied, double rate) {
        this.number = number;
        this.capacity = capacity;
        this.occupied = occupied;
        this.rate = rate;
    }

    public Standard(int num) {
        this.number = num;
    }

    @Override
    public int getNumber() {
        return number;
    }

    @Override
    public void setNumber(int number) {
        this.number = number;
    }

    @Override
    public int getCapacity() {
        return capacity;
    }

    @Override
    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    @Override
    public double getRate() {
        return rate;
    }

    @Override
    public void setRate(double rate) {
        this.rate = rate;
    }

    @Override
    public boolean isOccupied() {
        return occupied;
    }

    @Override
    public void setOccupied(boolean occupied) {
        this.occupied = occupied;
    }

    /**
     * Returns a string representation of the room, including its number, capacity, rate, and occupancy status.
     * @return A formatted string describing the room.
     */
    @Override
    public String toString() {
        return "Room "+ number+
        "\nCapacity: " + capacity +
        "\nRate: $" + rate +
        "\nOccupied: " + (occupied ? "Yes" : "No") +
        (occupied ? "\nOccupants: " + occupants : "") + // Only show occupants if occupied
        "";
    }

    @Override
    public int getOccupants() {
        if (occupied && occupants > 0) return occupants;
        else return 0;
    }

    /**
     * Returns the type of the room as a string.
     * @return "standard" as the type of the room.
     */
    @Override
    public String getType() {
        return "standard";
    }

    /**
     * Returns the floor of the room based on its number.
     * Assumes the first digit(s) of the room number indicate the floor.
     * @return The floor as a string.
     */
    @Override
    public String getFloor() {
        return (number + "").substring(0, (number + "").length() - 2);
    }

    /**
     * Sets the occupancy of the room.
     * Marks the room as occupied if the number of customers is valid.
     * @param customers The number of customers to occupy the room.
     * @return True if the occupancy was successfully set, false otherwise.
     */
    @Override
    public boolean setOccupancy(int customers) {
        if (customers <= capacity && customers >= 0) {
            this.occupants = customers;
            this.occupied = (customers > 0);

            return true;
        }
        if(customers <= 0){
            this.occupants = 0; // Set to 0 if negative number is passed
            this.occupied = false;
            return true; // Allow setting to 0
        }
        return false;
    }
}