package HotelManagement.Rooms;

public interface Room {
    int getNumber();
    void setNumber(int number);

    int getCapacity();
    void setCapacity(int capacity);

    double getRate();
    void setRate(double rate);

    boolean isOccupied();
    String getType();
    String getFloor();
    boolean setOccupancy(int customers);
    void setOccupied(boolean occupied);
    int getOccupants();
    String toString();

    
}