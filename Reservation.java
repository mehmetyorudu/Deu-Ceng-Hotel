import java.util.Stack;

public class Reservation {
    private int reservationid;
    private int roomID;
    private int customerID;
    private Date date;
    private Date date1;




    public Reservation(int customerID, int roomID,  Date date, Date date1) {
        this.roomID = roomID;
        this.customerID = customerID;
        this.date = date;
        this.date1 = date1;
    }

    public int getReservationid() {
        return reservationid;
    }

    public void setReservationid(int reservationid) {
        this.reservationid = reservationid;
    }

    public int getRoomID() {
        return roomID;
    }

    public void setRoomID(int roomID) {
        this.roomID = roomID;
    }

    public int getCustomerID() {
        return customerID;
    }

    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Date getDate1() {
        return date1;
    }

    public void setDate1(Date date1) {
        this.date1 = date1;
    }
}