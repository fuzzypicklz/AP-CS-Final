package HotelManagement.Rooms;

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

    public int getOccupants(){
        if (isOccupied && occupants > 0) return occupants;
        else return 0;
    }

    public int getOccupancy(){
        return capacity;
    }
    
    public String getType(){
        return this.type;
    }

    public double getRate(){
        return dailyRate;
    }
    
    public String getFloor(){
        return (number+"").substring(0,(number+"").length()-2);
    }

    public int getNumber(){
        return number;
    }

    public void setCapacity(int cap){
        this.capacity = cap;
    }
    
    public void setType(String type){
        this.type = type;
    }

    public void setRate(double rate){
        this.dailyRate = rate;
    }

    public boolean setOccupancy(int customers){
        if (!isOccupied && customers<=capacity) {
            isOccupied=true;
            occupants = customers;
            return true; // returns true if successful
        }
        return false;
    }

    public String toString(){
        String result = "Room "+number+
                        "\nFloor: "+getFloor()+
                        "\nType: "+this.getType()+
                        "\nCost per night: "+dailyRate+
                        "\ncapacity: "+capacity;
        if(isOccupied){
            result += "\nOccupants: "+occupants;
        }
        return result;
                
    }
}