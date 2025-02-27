package HotelManagement;

public class Suite extends Room {
    private int capacity = 4;
    private boolean hasBalcony = false;
    private double dailyRate = 500.00;
    private String type = "Suite";

    public Suite( int num, int cap, boolean isOcc, boolean hasBalc, double rate){
        super(num, cap, isOcc, rate);
        this.hasBalcony=hasBalc;
        this.type = "Suite";

    }
    public Suite(int num){
        super (num);
        this.dailyRate = 500.00;
    }
    public boolean hasBalcony(){
        return hasBalcony;
    }
    public String toString(){
        if(hasBalcony) return super.toString() + "\nBalcony: "+hasBalcony;
        return super.toString();
    }
    public static void main(String[] args){
        Suite s1 = new Suite(100, 10,false,true,2100.00);
        System.out.println(s1.toString());
        System.out.println(s1.type);
    }
}
