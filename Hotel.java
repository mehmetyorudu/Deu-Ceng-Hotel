public class Hotel {

    private String name;
    private Date date;
    private Address address;
    private PhoneNumber phoneNumber;
    private String hotelStars;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public PhoneNumber getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(PhoneNumber phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getHotelStars() {
        return hotelStars;
    }

    public void setHotelStars(String hotelStars) {
        this.hotelStars = hotelStars;
    }

    public Hotel(String name, Date date, Address address, PhoneNumber phoneNumber, String hotelStars) {
        this.name = name;
        this.date = date;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.hotelStars = hotelStars;
    }
}
