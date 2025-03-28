package HotelManagement.Rooms;

public class Suite implements Room {
    private int number;
    private int capacity = 4;
    private double rate = 150.00;
    private boolean occupied = false;
    private boolean hasBalcony = false;
    private int occupants = 0;

    public Suite(int number, int capacity, boolean occupied, boolean hasBalcony, double rate) {
        this.number = number;
        this.capacity = capacity;
        this.occupied = occupied;
        this.hasBalcony = hasBalcony;
        this.rate = rate;
    }

    public Suite(int number){
        this.number = number;
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

    /**
     * Returns the floor of the room based on its number.
     * Assumes the first digit(s) of the room number indicate the floor.
     * @return The floor as a string.
     */
    @Override
    public String getFloor() {
        if(number+"".length()>2){
            return (number+"").substring(0, (number+"").length()-2); // Assuming the first digit of the room number indicates the floor, e.g., 101 -> 1
        }
        else return number+"";
    }

    /**
     * Returns the type of the room as a string.
     * @return "Suite" as the type of the room.
     */
    @Override
    public String getType() {
        return "suite"; // Return the type of room
    }

    @Override
    public int getOccupants() {
        return occupants;
    }

    /**
     * Checks if the suite has a balcony.
     * @return True if the suite has a balcony, false otherwise.
     */
    public boolean hasBalcony() {
        return hasBalcony;
    }

    /**
     * Sets whether the suite has a balcony.
     * @param hasBalcony True if the suite has a balcony, false otherwise.
     */
    public void setBalcony(boolean hasBalcony) {
        this.hasBalcony = hasBalcony;
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
}
