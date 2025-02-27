package HotelManagement;

public class Room{
    private int capacity = 2;
    private int number;
    private boolean isOccupied = false;
    private int occupants = 0;
    private double dailyRate = 100.00;
    private String type = "standard";
    public Room(int num, int cap, boolean isOcc, double rate){
        this.capacity = cap;
        this.number = num;
        this.isOccupied = isOcc;
        this.dailyRate = rate;
        
    }
    public Room(int num){
        this.number = num;
    }

    public int returnOccupants(){
        if (isOccupied) return occupants;
        else return 0;
    }

    public int getOccupancy(){
        return capacity;
    }
    public boolean setOccupancy(int customers){
        if (!isOccupied && customers<=capacity) {
            isOccupied=true;
            occupants = customers;
            return true; // returns true if successful
        }
        return false;
    }
    public String getType(){
        return this.type;
    }
    public double getRate(){
        return dailyRate;
    }
    public String toString(){
        String result = "Room "+number+
                        "\nType: "+this.getType()+
                        "\nCost per night: "+dailyRate+
                        "\ncapacity: "+capacity;
        if(isOccupied){
            result += "Occupants: "+occupants;
        }

        return result;
                
    }
}