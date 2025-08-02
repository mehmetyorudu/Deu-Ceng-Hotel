import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class HotelSystem {
    int nRooms = 0;
    int nCustomers = 0;
    int nEmployees = 0;
    int nReservations = 0;
    //int roomid=1;
    double income;
    int totalStay = 0;
    double profit = 0;


    Room[] rooms = new Room[30];
    Customer[] customers = new Customer[30];
    Employee[] employees = new Employee[50];
    Reservation[] reservations = new Reservation[30];
    int[] totalStayDaysForEachMonth = new int[12];
    int[] numberOfCustomers = new int[366];
    int houseKeeperCount = 0;

    void start() {
        try {
            File inputFile = new File("commands.txt");
            Scanner scanner = new Scanner(inputFile);

            while (scanner.hasNextLine()) {
                String command = scanner.nextLine();
                String[] commandArr = command.split(";");

                //String birthDate = null;

                if (commandArr[0].equals("addRoom")) {
                    int numRoomsToAdd = Integer.parseInt(commandArr[1]);
                    for (int i = 0; i < numRoomsToAdd; i++) {
                        String roomType = (commandArr[2]);
                        boolean hasAC = Boolean.parseBoolean(commandArr[3]);
                        boolean hasBalcony = Boolean.parseBoolean(commandArr[4]);
                        double price = Double.parseDouble(commandArr[5]);
                        Room room = new Room(roomType, hasAC, hasBalcony, price);
                        addRoom(room);


                    }

                } else if (commandArr[0].equals("addCustomer")) {
                    String firstName = commandArr[1];
                    String lastName = commandArr[2];
                    String gender = commandArr[3];
                    String birthDate = commandArr[4];
                    String[] birthArr = (commandArr[4].split("\\."));
                    int birthday = Integer.parseInt(birthArr[0]);
                    int birthmonth = Integer.parseInt(birthArr[1]);
                    int birthyear = Integer.parseInt(birthArr[2]);
                    String address = commandArr[5];
                    String city = commandArr[6];
                    String state = commandArr[7];
                    String phone = commandArr[8];
                    String[] phonenumb = (commandArr[8].split(""));
                    String[] countryCode = new String[3];
                    String[] cityCode = new String[3];
                    String[] number = new String[7];
                    for (int i = 0; i < 3; i++) {
                        countryCode = new String[]{phonenumb[i]};
                    }

                    for (int i = 3; i < 6; i++) {
                        cityCode = new String[]{phonenumb[i]};
                    }

                    for (int i = 6; i < 13; i++) {
                        number = new String[]{phonenumb[i]};
                    }

                    PhoneNumber phoneNumber = new PhoneNumber(countryCode, cityCode, number);
                    Address address1 = new Address(address, city, state);
                    Date date = new Date(birthday, birthmonth, birthyear);
                    Customer customer = new Customer(firstName, lastName, gender, date, address1, phone);
                    addCustomer(customer);


                } else if (commandArr[0].equals("addEmployee")) {
                    String firstName = commandArr[1];
                    String lastName = commandArr[2];
                    String gender = commandArr[3];
                    String birthdate = commandArr[4];
                    String[] birthArr = (commandArr[4].split("\\."));
                    int birthday = Integer.parseInt(birthArr[0]);
                    int birthmonth = Integer.parseInt(birthArr[1]);
                    int birthyear = Integer.parseInt(birthArr[2]);
                    String address = commandArr[5];
                    String city = commandArr[6];
                    String state = commandArr[7];
                    String phone = commandArr[8];
                    String position = commandArr[9];
                    int salary = Integer.parseInt(commandArr[10]);

                    String[] phonenumb = (commandArr[8].split(""));
                    String[] countryCode = new String[3];
                    String[] cityCode = new String[3];
                    String[] number = new String[7];
                    for (int i = 0; i < 3; i++) {
                        countryCode = new String[]{phonenumb[i]};
                    }

                    for (int i = 3; i < 6; i++) {
                        cityCode = new String[]{phonenumb[i]};
                    }

                    for (int i = 6; i < 12; i++) {
                        number = new String[]{phonenumb[i]};
                    }
                    PhoneNumber phoneNumber = new PhoneNumber(countryCode, cityCode, number);

                    Address address1 = new Address(address, city, state);
                    Date date = new Date(birthday, birthmonth, birthyear);
                    Employee employee = new Employee(firstName, lastName, gender, date, address1, phone, position, salary);

                    addEmployee(employee);

                }
                else if (commandArr[0].equals("addReservation")) {

                    int customerID = Integer.parseInt(commandArr[1]);
                    int roomID = Integer.parseInt(commandArr[2]);
                    String startDate = commandArr[3];
                    String[] DateArr = (commandArr[3].split("\\."));
                    int day = Integer.parseInt(DateArr[0]);
                    int month = Integer.parseInt(DateArr[1]);
                    int year = Integer.parseInt(DateArr[2]);
                    String endDate = commandArr[4];
                    String[] DateArr1 = (commandArr[4].split("\\."));
                    int day1 = Integer.parseInt(DateArr1[0]);
                    int month1 = Integer.parseInt(DateArr1[1]);
                    int year1 = Integer.parseInt(DateArr1[2]);


                    Date date = new Date(day, month, year);
                    Date date1 = new Date(day1, month1, year1);
                    Reservation reservation = new Reservation(customerID, roomID, date, date1);
                    addReservation(reservation);
                    addReservationsForSim(reservation);


                } else if (commandArr[0].equals("listRooms")) {
                    listRooms();

                } else if (commandArr[0].equals("listCustomers")) {
                    listCustomers();

                } else if (commandArr[0].equals("listEmployees")) {
                    listEmployees();

                } else if (commandArr[0].equals("listReservations")) {
                    listAllReservations();

                } else if (commandArr[0].equals("deleteEmployee")) {
                    System.out.println("You deleted the employee. Employee id is: # "+commandArr[1]);
                    int employeeid = Integer.parseInt(commandArr[1]);
                    deleteEmployee(employeeid);
                } else if (commandArr[0].equals("statistics")) {
                    System.out.println("Statistics");
                    System.out.println("Total income: " + income + "TL");
                    calculateTheProfit();
                    mostReservedRoom();
                    bestCustomer();
                    //System.out.println(nRooms);
                    System.out.println("Monthly occupancy rate: ");
                    System.out.println("1\t\t2\t\t3\t\t4\t\t5\t\t6\t\t7\t\t8\t\t9\t\t10\t\t11\t\t12");


                    occupancyRate();
                    System.out.println();


                } else if (commandArr[0].equals("simulation")) {
                    String[] SimArr = (commandArr[1].split("\\."));
                    int simDay = Integer.parseInt(SimArr[0]);
                    int simMonth = Integer.parseInt(SimArr[1]);
                    int simYear = Integer.parseInt(SimArr[2]);
                    String[] SimArr2 = (commandArr[2].split("\\."));
                    int simDay2 = Integer.parseInt(SimArr2[0]);
                    int simMonth2 = Integer.parseInt(SimArr2[1]);
                    int simYear2 = Integer.parseInt(SimArr2[2]);

                    Date date = new Date(simDay, simMonth, simYear);
                    Date date2 = new Date(simDay2, simMonth2, simYear2);

                    simulation(date, date2);
                    averageSatisfaction(date, date2);


                } else if (commandArr[0].equals("searchCustomer")) {
                    char[] star = commandArr[1].toCharArray();
                    char[] qMark = commandArr[1].toCharArray();
                    System.out.println("Search Customer: " + commandArr[1]);

                    for(int i =0; i<star.length;i++)
                        if(star[i]=='*')
                        {
                            searchCustomer(star);
                            break;
                        }

                    for(int j=0;j<qMark.length;j++)
                        if(qMark[j]=='?')
                        {
                            searchCustomerbyQmark(qMark);
                            break;
                        }

                } else if (commandArr[0].equals("searchRoom")) {
                    String[] searchRoomArr = (commandArr[1].split("\\."));
                    int srchDay = Integer.parseInt(searchRoomArr[0]);
                    int srchMonth = Integer.parseInt(searchRoomArr[1]);
                    int srchYear = Integer.parseInt(searchRoomArr[2]);
                    String[] searchRoomArr2 = (commandArr[2].split("\\."));
                    int srchDay2 = Integer.parseInt(searchRoomArr2[0]);
                    int srchMonth2 = Integer.parseInt(searchRoomArr2[1]);
                    int srchYear2 = Integer.parseInt(searchRoomArr2[2]);
                    Date date = new Date(srchDay, srchMonth, srchYear);
                    Date date2 = new Date(srchDay2, srchMonth2, srchYear2);

                    whichDayOfTheYear(date);
                    whichDayOfTheYear(date2);
                    System.out.println();
                    System.out.println("Search Room:" + commandArr[1] + "-" + commandArr[2]);
                    searchRooms(date, date2);
                    System.out.println();

                }

            }


        } catch (FileNotFoundException e) {
            System.out.println("commands.txt file not found,please make sure the file name");
            throw new RuntimeException(e);
        }

    }

    void deleteEmployee(int employeeid) {
        for (int i = 0; i < employees.length; i++) {
            if (employees[i] != null) {
                if (employeeid == employees[i].getEmployeeid()) {
                    employees[i] = null;
                    break;
                }
            }
        }

    }

    void addRoom(Room room) {

        if (nRooms >= 30) return ;


        room.setRoomid(nRooms + 1);
        rooms[nRooms] = room;

        nRooms++;

    }

    void addEmployee(Employee employee) {

        if (nEmployees > 50) return;

        employee.setEmployeeid(nEmployees + 1);
        employees[nEmployees] = employee;
        nEmployees++;
        if (employee.getRole().equals("housekeeper"))

            houseKeeperCount++;

    }

    void addCustomer(Customer customer) {

        if (nCustomers > 30) return;

        customer.setCustomerid(nCustomers + 1);
        customers[nCustomers] = customer;
        nCustomers++;

    }

    void addReservationsForSim(Reservation reservation) {

        if (nReservations > 50) return;

        int targetIndex = findroomIndexById(reservation.getRoomID());

        rooms[targetIndex].hasReserved = true;



    }

    void addReservation(Reservation reservation) {
        boolean control=true;

        if (nReservations > 50) {
            return;
        }else {


            if (reservation.getDate().getMonth() == reservation.getDate1().getMonth()) {

                for (int i = reservation.getDate().getMonth(); i <reservation.getDate1().getMonth()+1 ; i++) {
                    for (int j = reservation.getDate().getDay(); j <daysThatMonthsHave(reservation.getDate().getMonth()) ; j++) {
                        if (rooms[reservation.getRoomID()-1]!=null&&rooms[reservation.getRoomID()-1].calendar[i-1][j-1]==true) {
                            control=false;
                        }
                    }
                }

            }else if (reservation.getDate().getMonth()+1==reservation.getDate1().getMonth()) {// aylar ardısık ise
                for (int i = reservation.getDate().getDay(); i <daysThatMonthsHave(reservation.getDate().getMonth()) ; i++) {
                    if (rooms[reservation.getRoomID()-1].calendar[reservation.getDate().getMonth()-1][i] == true) {
                        control=false;
                    }
                }
                for (int i = reservation.getDate1().getMonth(); i < reservation.getDate1().getMonth()+1; i++) {
                    for (int j = 1; j <reservation.getDate1().getDay() ; j++) {
                        if (rooms[reservation.getRoomID()-1].calendar[i-1][j-1] == true) {
                            control=false;

                        }
                    }
                }

            }else{
                for (int i = reservation.getDate().getMonth(); i <reservation.getDate().getMonth()+1 ; i++) {
                    for (int j = reservation.getDate().getDay(); j <daysThatMonthsHave(reservation.getDate().getDay()) ; j++) {
                        if (rooms[reservation.getRoomID()-1].calendar[i-1][j-1]==true) {
                            control=false;
                        }
                    }
                }
                for (int i = reservation.getDate().getMonth()+1; i <=reservation.getDate1().getMonth()-1 ; i++) {
                    for (int j = 1; j <daysThatMonthsHave(i) ; j++) {
                        if (rooms[reservation.getRoomID()-1].calendar[i-1][j-1] == true) {
                            control=false;
                        }
                    }
                }
                for (int i = reservation.getDate1().getMonth(); i <reservation.getDate1().getMonth()+1 ; i++) {
                    for (int j = 1; j <daysThatMonthsHave(i) ; j++) {
                        if (rooms[reservation.getRoomID()-1].calendar[i-1][j-1] == true) {
                            control=false;

                        }
                    }
                }
            }
        }

        if (control==true){

            changeStatusOfRoom(reservation.getRoomID(),reservation.getDate(),reservation.getDate1());
            reservations[nReservations] = reservation;
            reservation.setReservationid(++nReservations);

            for (int i = 0; i < rooms.length; i++) {
                if (rooms[i] != null) {
                    if (rooms[i].getRoomid() == reservation.getRoomID()) {
                        totalStay = calculateStayingDate(reservation.getDate(), reservation.getDate1());
                        income += totalStay * rooms[i].getRoomPrice();
                    }
                }

            }

            int startDay = whichDayOfTheYear(reservation.getDate());
            int endDay = whichDayOfTheYear(reservation.getDate1());

            for (int i = startDay; i < endDay; i++) {
                numberOfCustomers[i]++;//for simulation
            }


        }else{
            System.out.println("The Room #"+reservation.getRoomID()+ " has requested more than one but it has already reserved.");
            System.out.println();

        }
        
    }

    public void changeStatusOfRoom(int roomid,Date entrance, Date exit){
        if (entrance.getMonth() == exit.getMonth()) {
            for (int i = entrance.getMonth(); i < entrance.getMonth()+1 ; i++) {
                for (int j = entrance.getDay(); j < exit.getDay() ; j++) {
                    if (rooms[roomid-1]!=null) {
                        rooms[roomid-1].calendar[i-1][j-1]=true;
                    }

                }
            }

        } else if (entrance.getMonth()+1==exit.getMonth()) {
            for (int i =entrance.getMonth(); i <entrance.getMonth()+1 ; i++) {
                for (int j = entrance.getDay(); j <daysThatMonthsHave(entrance.getMonth()) ; j++) {
                    rooms[roomid-1].calendar[i-1][j-1]=true;
                }
            }

            for (int i = exit.getMonth(); i <exit.getMonth()+1 ; i++) {
                for (int j = 1; j <exit.getDay() ; j++) {
                    rooms[roomid-1].calendar[i-1][j-1]=true;
                }
            }

        }else{
            for (int i = entrance.getMonth(); i < entrance.getMonth()+1 ; i++) {
                for (int j = entrance.getDay(); j <daysThatMonthsHave(entrance.getMonth()) ; j++) {
                    rooms[roomid-1].calendar[i-1][j-1]=true;
                }
            }

            for (int i = entrance.getMonth()+1; i <=exit.getMonth()-1 ; i++) {
                for (int j = 1; j <daysThatMonthsHave(i) ; j++) {
                    rooms[roomid-1].calendar[i-1][j-1]=true;
                }
            }

            for (int i = exit.getMonth(); i < exit.getMonth()+1 ; i++) {
                for (int j = 1; j < exit.getDay() ; j++) {
                    rooms[roomid-1].calendar[i-1][j-1]=true;
                }
            }
        }

    }

    void listRooms() {
        System.out.println("List Rooms: ");


        for (int i = 0; i < rooms.length; i++) {

            if (rooms[i] != null) {

                String balcony;
                String aircondition;
                if (rooms[i].isHasBalcony() ) balcony = "balcony";
                else balcony = "no-balcony";

                if (rooms[i].isHasAirConditioner() ) aircondition = "aircondition";
                else aircondition = "no-aircondition";

                System.out.println("Room #" + rooms[i].getRoomid() + " " + rooms[i].getRoomType() +
                        " " + aircondition + " " + balcony + " " + " " + rooms[i].getRoomPrice() + "TL");

                if (rooms[i].getRoomid() > 30) {
                    System.out.println("please delete some");
                    break;
                }
            }


        }
        System.out.println(" ");
    }

    void listEmployees() {
        System.out.println("List Employees:");

        for (int i = 0; i < employees.length; i++) {
            if (employees[i] != null) {

                System.out.println("Employee #" + employees[i].getEmployeeid() + " " + employees[i].getFirstName() + " " + employees[i].getLastName() + " " + employees[i].getGender() +
                        " " + employees[i].getDate().getDay() + "." + employees[i].getDate().getMonth() + "."
                        + employees[i].getDate().getYear() + " " + employees[i].getAddress1().getText() + " " + employees[i].getAddress1().getCity()
                        + " " + employees[i].getAddress1().getDistrict() + " " + employees[i].getPhoneNumber() + " " + employees[i].getRole() + " " +
                        employees[i].getSalary());
            }
        }
        System.out.println();
    }

    void listCustomers() {
        System.out.println("List Customers:");

        for (int i = 0; i < customers.length; i++) {
            if (customers[i] != null) {

                System.out.println(String.format("Customer #" + customers[i].getCustomerid() + " " + customers[i].getFirstName() + " " + customers[i].getLastName() + " " + customers[i].getGender() + " " + customers[i].getDate().getDay() + "." + customers[i].getDate().getMonth() + "." + customers[i].getDate().getYear() + " " + customers[i].getAddress1().getText() + " " + customers[i].getAddress1().getCity() + " " + customers[i].getAddress1().getDistrict() + " " + customers[i].getPhoneNumber()));


            }
        }
        System.out.println();
    }

    public void listAllReservations() {
        System.out.println("All Reservations:");

        for (int i = 0; i < reservations.length; i++) {
            if (reservations[i] != null) {
                int index = findCustomerIndexbyId(reservations[i].getCustomerID());
                System.out.println("Room #" + reservations[i].getRoomID() + " " + customers[index].getFirstName() + " "+customers[index].getLastName()+
                        " " + reservations[i].getDate().getDay() + "." + reservations[i].getDate().getMonth() + "." + reservations[i].getDate().getYear() + " " + reservations[i].getDate1().getDay() + "." + reservations[i].getDate1().getMonth() + "." + reservations[i].getDate1().getYear());

            }

        }
        System.out.println();

    }

    void searchCustomer(char[] star) {

        for (int i = 0; i < star.length; i++) {

            boolean flag = true;
            if (customers[i] != null) {

                char[] searchArr = customers[i].getFirstName().toCharArray();

                int starIndex = 0;

                for (int k = 0; k < star.length; k++) {
                    if (star[k] == '*')
                        starIndex = k;


                }

                for (int j = 0; j < starIndex; j++) {
                    if (star[j] != searchArr[j]) {
                        flag = false;
                        break;
                    }
                    if (!flag)
                        break;
                }

                if (flag) {
                    System.out.println("Customer #" + customers[i].getCustomerid() + " " + customers[i].getFirstName() + " " + customers[i].getLastName() + " " + customers[i].getGender() + " " +
                            customers[i].getDate().getDay() + "." + customers[i].getDate().getMonth() + "."
                            + customers[i].getDate().getYear() + " " + customers[i].getAddress1().getText() +
                            " " + customers[i].getAddress1().getCity() + " " + customers[i].getAddress1().getDistrict()
                            + " " + customers[i].getPhoneNumber());
                }
            }
        }

    }

    void searchCustomerbyQmark(char[] qMark) {

        for (int i = 0; i < customers.length; i++) {
            boolean flag = true;

            if (customers[i] != null && qMark.length == customers[i].getFirstName().length()) {
                for (int j = 0; j < qMark.length; j++) {
                    if (customers[i] != null && qMark[j] != '?' && customers[i].getFirstName().charAt(j) != qMark[j]) {
                        flag = false;
                        break;
                    }


                }

            }

            if (customers[i] != null && flag  ) {

                System.out.println(String.format("Customer #" + customers[i].getCustomerid() + " " + customers[i].getFirstName() + " " + customers[i].getLastName() + " " + customers[i].getGender() + " " + customers[i].getDate().getDay() + "." + customers[i].getDate().getMonth() + "." + customers[i].getDate().getYear() + " " + customers[i].getAddress1().getText() + " " + customers[i].getAddress1().getCity() + " " + customers[i].getAddress1().getDistrict() + " " + customers[i].getPhoneNumber()));
                break;

            }

        }

        System.out.println();

    }

    int whichDayOfTheYear(Date date) {

        int month = date.getMonth();
        int day;

        int sumOfday = date.getDay();

        for (int i = 1; i < month; i++) {
            if (i == 1 || i == 3 || i == 5 || i == 7 || i == 8 || i == 10 || i == 12)
                day = 31;
            else if (i == 2)
                day = 29;
            else
                day = 30;
            sumOfday += day;
        }
        return sumOfday;
    }

    void simulation(Date date, Date date2) {
        System.out.println();
        System.out.println("Simulation between " + date.getDay() + "." + date.getMonth() + "." + date.getYear()
                + " and " + date2.getDay() + "." + date2.getMonth() + "." + date2.getYear());
        System.out.print("Day         :");


        if (date.getMonth() == date2.getMonth()) //if in the same month
        {

            for (int i = date.getDay(); i <= date2.getDay(); i++) {// if the month has 31 days
                System.out.print(String.format("%10d", i));
            }
            System.out.println(" ");

        } else if (date.getMonth() != date2.getMonth()) { //if they are in different months

            if (date2.getMonth() - date.getMonth() == 1) {//if they are in consecutive months


                for (int i = date.getDay(); i <= daysThatMonthsHave(date.getMonth()); i++) {// if that month is February
                    System.out.print(String.format("%10d", i));
                }
                for (int j = 1; j <= date2.getDay(); j++) { // and writing to the rest of the days
                    System.out.print(String.format("%10d", j));
                }
                System.out.println(" ");

            } else { //if they have another month or month between them


                for (int i = date.getDay(); i <= daysThatMonthsHave(date.getMonth()); i++) { //if in the same month
                    System.out.print(String.format("%10d", i));
                }

                for (int i = date.getMonth() + 1; i < date2.getMonth(); i++) { // in different months
                    for (int j = 1; j <= daysThatMonthsHave(date.getMonth() + 1); j++) {
                        System.out.print(String.format("%10d", j));
                    }
                }

                for (int j = 1; j <= date2.getDay(); j++) { // and writing to the rest of the days
                    System.out.print(String.format("%10d", j));
                }

                System.out.println();
            }


            //  until there we write the days now we write customer

        }

        int startDate = whichDayOfTheYear(date);
        int endDate = whichDayOfTheYear(date2);


        System.out.print("Customer    :");
        for (int i = startDate; i <= endDate; i++) {
            System.out.print(String.format("%10d", numberOfCustomers[i]));//that array belongs to add customer method
        }

        System.out.println();

        System.out.print("Satisfaction:");
        for (int i = startDate; i <= endDate; i++) {
            System.out.printf("%9d%%", satisfaction(numberOfCustomers[i]));
        }

        System.out.println();

        System.out.print("Average Satisfaction = " + averageSatisfaction(date, date2) + "%");
        System.out.println();
        System.out.println();

    }

    int satisfaction(int nCustomers) {

        if (nCustomers == 0)
            return 100;


        float stfctn = ((300 / nCustomers) * houseKeeperCount);

        if (stfctn > 100)
            return 100;

        else
            return (int) stfctn;


    }

    int averageSatisfaction(Date startdate, Date enddate) {

        float total = 0;

        int startDate = whichDayOfTheYear(startdate);
        int endDate = whichDayOfTheYear(enddate);

        int aradakiGun = endDate - startDate + 1;

        for (int i = startDate; i <= endDate; i++)
            total += satisfaction(numberOfCustomers[i]);

        return (int) total / aradakiGun;

    }

    void searchRooms(Date date1, Date date2) {


        for (int i = 0; i < rooms.length; i++) { // rezervasyonu olmayan odaların tamamı

            if (rooms[i] != null && !rooms[i].hasReserved) {

                String balcony;
                String aircondition;
                if (rooms[i].isHasBalcony() == true) balcony = "balcony";
                else balcony = "no-balcony";

                if (rooms[i].isHasAirConditioner() == true) aircondition = "aircondition";
                else aircondition = "no-aircondition";

                System.out.println("Room #" + rooms[i].getRoomid() + " " + rooms[i].getRoomType() + " " + aircondition + " " + balcony + " " + " " + rooms[i].getRoomPrice() + "TL");

            }

        }

        for (int i = 0; i < reservations.length; i++) {
            if (reservations[i] != null && isAvailableInDateRange(date1, date2, reservations[i])) {
                int index = findroomIndexById(reservations[i].getRoomID());
                String balcony;
                String aircondition;
                if (rooms[index].isHasBalcony() == true) balcony = "balcony";
                else balcony = "no-balcony";

                if (rooms[index].isHasAirConditioner() == true) aircondition = "aircondition";
                else aircondition = "no-aircondition";

                System.out.println("Room #" + rooms[index].getRoomid() + " "
                        + rooms[index].getRoomType() + " " + aircondition + " "
                        + balcony + " " + " " + rooms[index].getRoomPrice() + "TL");

            }
        }
    }

    int findroomIndexById(int id) {
        for (int i = 0; i < rooms.length; i++) {
            if (rooms[i] != null && rooms[i].getRoomid() == id) {
                return i;
            }
        }

        return 0;
    }

    boolean isAvailableInDateRange(Date startdate, Date enddate, Reservation reservation) {
        int resStart = whichDayOfTheYear(reservation.getDate());
        int resEnd = whichDayOfTheYear(reservation.getDate1());

        int startDate = whichDayOfTheYear(startdate);
        int endDate = whichDayOfTheYear(enddate);

        boolean isavailable = false;

        if (resEnd < startDate || resStart > endDate) {
            isavailable = true;
        }

        return isavailable;
    }

    public void calculateTheProfit() {
        int employeeExpensesPerMonth = 0;
        double netProfit;
        for (int i = 0; i < employees.length; i++) {
            if (employees[i] != null) {
                employeeExpensesPerMonth += employees[i].getSalary();
            }

        }
        netProfit = income - (employeeExpensesPerMonth * 12 + 120000.0); //120000 is constant expenses over the year
        System.out.println("The net profit over the year is: " + netProfit + "TL");
    }

    int findCustomerIndexbyId(int id) {
        for (int i = 0; i < customers.length; i++) {
            if (customers[i] != null) {
                if (customers[i].getCustomerid() == id)
                    return i;
            }
        }
        return 0;
    }

    public int calculateStayingDate(Date entranceDate, Date exitDate) {

        int totalDays = 0;
        int tempMonths = 0;
        int entranceMonth = 0;
        //int cikisAyi = 0;
        switch (entranceDate.getMonth()) {
            case 1, 3, 5, 7, 8, 10, 12:
                entranceMonth = 31;
                break;
            case 4, 6, 9, 11:
                entranceMonth = 30;
                break;
            case 2:
                entranceMonth = 29;
                break;
        }


        if (entranceDate.getMonth() == exitDate.getMonth()) {
            totalDays = exitDate.getDay() - entranceDate.getDay();
            return totalDays;

        } else {
            for (int i = entranceDate.getMonth() + 1; i < exitDate.getMonth(); i++) {
                switch (i) {
                    case 1, 3, 5, 7, 8, 10, 12:
                        tempMonths += 31;
                        break;

                    case 4, 6, 9, 11:
                        tempMonths += 30;
                        break;
                    case 2:
                        tempMonths += 29;
                        break;
                }
            }
            totalDays = (entranceMonth - entranceDate.getDay()) + tempMonths + (exitDate.getDay());
            return totalDays;
        }

    }

    int daysThatMonthsHave(int month) {//month = month

        if (month == 1 || month == 3 || month == 5 || month == 7 || month == 8 || month == 10 || month == 12)
            return 31;
        else if (month == 2)
            return 29;
        else
            return 30;


    }

    public void occupancyRate() {
        for (int j = 0; j < reservations.length; j++) {


            if (reservations[j] != null) {

                if (reservations[j].getDate().getMonth() == reservations[j].getDate1().getMonth()) {      //giriş ve çıkış ayı aynı aysa
                    totalStayDaysForEachMonth[reservations[j].getDate().getMonth() - 1] += reservations[j].getDate1().getDay() - reservations[j].getDate().getDay();
                } else if (reservations[j].getDate().getMonth() + 1 == reservations[j].getDate1().getMonth()) {       //aylar ardısıksa
                    totalStayDaysForEachMonth[reservations[j].getDate().getMonth() - 1] += daysThatMonthsHave(reservations[j].getDate().getMonth()) - reservations[j].getDate().getDay() + 1;
                    totalStayDaysForEachMonth[reservations[j].getDate1().getMonth() - 1] += reservations[j].getDate1().getDay() - 1;
                } else {
                    totalStayDaysForEachMonth[reservations[j].getDate().getMonth() - 1] += daysThatMonthsHave(reservations[j].getDate().getMonth()) - reservations[j].getDate().getDay() + 1;
                    totalStayDaysForEachMonth[reservations[j].getDate1().getMonth() - 1] += reservations[j].getDate1().getDay() - 1;      //giriş ve çıkış ayı arasında 1 den büyük fark varsa
                    for (int k = reservations[j].getDate().getMonth() + 1; k < reservations[j].getDate1().getMonth(); k++) {
                        totalStayDaysForEachMonth[k - 1] += daysThatMonthsHave(k);
                    }
                }

            }


        }

        for (int i = 0; i < 12; i++) {
            double rate = 0;
            rate = Math.round((double) 100 * totalStayDaysForEachMonth[i] / (daysThatMonthsHave(i + 1) * nRooms));

            System.out.print((int) rate + "%" + "\t\t");
        }


    }

    public void mostReservedRoom(){
        int[] counter = new int[rooms.length];
        int tempRoom = 1;
        for(int i =0; i<reservations.length; i++){
            if (reservations[i] != null){
                int days;
                days = calculateStayingDate(reservations[i].getDate(), reservations[i].getDate1());
                counter[i] = days;

            }
        }

        int max =0;
        max = counter[0];
        for (int i = 0; i < counter.length; i++){

            if(counter[i]> max){
                max = counter[i];
                tempRoom = reservations[i].getRoomID();
            }
        }
        System.out.println("The most reserved room = Room #" + tempRoom);
    }

    public void bestCustomer (){
        int[] counter = new int[customers.length];

        for (int i =0; i<reservations.length; i++){
            if(reservations[i] != null){
                int days;
                days = calculateStayingDate(reservations[i].getDate(), reservations[i].getDate1());
                counter[i] = days;
            }
        }
        int temp = 0;
        String tempName = "";
        int max = 0;
        max = counter[0];
        for (int i = 0; i < counter.length; i++){

            if(counter[i]> max){
                max = counter[i];
                temp = reservations[i].getCustomerID();

            }
        }
        int index = findCustomerIndexbyId(temp);
        if (customers[index] != null){
            tempName = customers[index].getFirstName() + " " + customers[index].getLastName();

        }

        System.out.println("The best customer = " + tempName + "   " + max + " days");


    }


}















