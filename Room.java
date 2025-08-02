
class Room {
    private int roomid;
    private String roomType;
    private boolean hasAirConditioner;
    private boolean hasBalcony;
    private double roomPrice;
    boolean hasReserved;

    Boolean[][] calendar=new Boolean[12][31];


    public Boolean getCalendar(int i, int j) {
        return calendar[i][j];
    }

    /*public void setCalendar(int i, int j) {
        this.calendar=[i][j];
    }*/

    public int getRoomid() {
        return roomid;
    }
    public boolean isHasReserved(){
        return hasReserved;
    }

    public void setRoomid(int roomid) {
        this.roomid = roomid;
    }

    public String getRoomType() {
        return roomType;
    }

    public void setRoomType(String roomType) {
        this.roomType = roomType;
    }

    public boolean isHasBalcony() {
        return hasBalcony;
    }

    public void setHasBalcony(boolean hasBalcony) {
        this.hasBalcony = hasBalcony;
    }

    public boolean isHasAirConditioner() {
        return hasAirConditioner;
    }

    public void setHasAirConditioner(boolean hasAirConditioner) {
        this.hasAirConditioner = hasAirConditioner;
    }

    public double getRoomPrice() {
        return roomPrice;
    }

    public void setRoomPrice(double roomPrice) {
        this.roomPrice = roomPrice;
    }




    public Room(String roomType, boolean hasAirConditioner, boolean hasBalcony, double roomPrice) {
        this.roomType = roomType;
        this.hasAirConditioner = hasAirConditioner;
        this.hasBalcony = hasBalcony;
        this.roomPrice = roomPrice;
        for (int i = 0; i <calendar.length ; i++) {
            for (int j = 0; j <calendar[i].length ; j++) {
                calendar[i][j]=false;

            }
        }

    }
}
