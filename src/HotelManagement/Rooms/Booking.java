package HotelManagement.Rooms;

import java.util.Date;
import java.util.Calendar;

public class Booking {
    private Date dayIn;
    private Date dayOut;
    private Room room;
    private String name;
    private int amntDue;
    public Booking(Date dayIn, int daysStayed, Room room, String name){
        this.dayIn = dayIn;

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(dayIn);
        calendar.add(Calendar.DAY_OF_MONTH, 5); // Add 5 days

        this.dayOut = calendar.getTime();
        this.room = room;
        this.name = name;
        this.amntDue = room.
    }
}
