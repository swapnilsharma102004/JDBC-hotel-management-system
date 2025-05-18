
import java.sql.*;
import java.util.*;

import static java.lang.System.exit;

public class Main {
    private static final String url="jdbc:mysql://localhost:3306/hotel_db";

    private static final String username="root";

    private static final String password= "Swapnil@251";

    public static void main(String[] args) throws  ClassNotFoundException,SQLException{



        try{
            Class.forName("com.mysql.cj.jdbc.Driver");

        }catch(ClassNotFoundException e){
            System.out.println(e.getMessage());
        }

        try{
            Connection con= DriverManager.getConnection(url,username,password);
            while(true){
                System.out.println();
                System.out.println("HOTEL MANAGEMENT SYSTEM");
                Scanner sc=new Scanner(System.in);
                System.out.println("1. Reserve a room");
                System.out.println("2. View Reservation");
                System.out.println("3. Get Room No.");
                System.out.println("4. Update Reservations");
                System.out.println("5. Delete Reservations");
                System.out.println("6. Exit");

                int choice=sc.nextInt();
                switch(choice){
                    case 1:
                        reserveRoom(con,sc);
                        break;
                    case 2:
                        viewReservations(con);
                        break;
                    case 3:
                        getRoomNo(con,sc);
                        break;
                    case 4:
                        updateReservations(con,sc);
                        break;
                    case 5:
                        deleteReservations(con,sc);
                        break;
                    case 6:
                        exit();
                        sc.close();
                        return;
                    default:
                        System.out.println("Invalid Choice, try again");
                }
            }


        }catch(SQLException e){
            System.out.println(e.getMessage());
        }catch(InterruptedException e){
            throw new RuntimeException(e);
        }
    }



    private static void reserveRoom(Connection con, Scanner sc) {
        try {
            System.out.print("Enter guest name: ");
            String guestName = sc.next();
            sc.nextLine();
            System.out.print("Enter room number: ");
            int roomNumber = sc.nextInt();
            System.out.print("Enter contact number: ");
            String contactNumber = sc.next();

            String sql = "INSERT INTO reservations (guest_name, room_number, contact_number) " +
                    "VALUES ('" + guestName + "', " + roomNumber + ", '" + contactNumber + "')";

            try (Statement statement = con.createStatement()) {
                int affectedRows = statement.executeUpdate(sql);

                if (affectedRows > 0) {
                    System.out.println("Reservation successful!");
                } else {
                    System.out.println("Reservation failed.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void viewReservations(Connection con) throws SQLException{
        String sql="SELECT reservation_id, guest_name,room_number,contact_number,reservation_date FROM reservations";

        try(Statement statement =con.createStatement();
            ResultSet resultSet=statement.executeQuery(sql)) {
            System.out.println("Current Reservation:");
            System.out.println("+----------------+--------------+--------------+-------------------+-------------------------+");
            System.out.println("| Reservation ID | Guest        | Room Number  | Contact Number    | Reservation Date        |");
            System.out.println("+----------------+--------------+--------------+-------------------+-------------------------+");
            while(resultSet.next()){
                int reservationId=resultSet.getInt("reservation_Id");
                String guestName=resultSet.getString("guest_name");
                int roomNumber= resultSet.getInt("room_number");
                String contactNumber=resultSet.getString("contact_number");
                String reservationDate=resultSet.getTimestamp("reservation_date").toString();


                System.out.printf("| %-14d | %-12s | %-12d | %-17s | %-21s |\n",
                        reservationId,guestName, roomNumber, contactNumber, reservationDate);
            }

            System.out.println("+----------------+--------------+--------------+-------------------+---------------------+");
        }
    }

    private static void getRoomNo(Connection con, Scanner sc) {
        try {
            System.out.print("Enter reservation ID: ");
            int reservationId = sc.nextInt();
            System.out.print("Enter guest name: ");
            String guestName = sc.next();

            String sql = "SELECT room_number FROM reservations " +
                    "WHERE reservation_id = " + reservationId +
                    " AND guest_name = '" + guestName + "'";

            try (Statement statement = con.createStatement();
                 ResultSet resultSet = statement.executeQuery(sql)) {

                if (resultSet.next()) {
                    int roomNumber = resultSet.getInt("room_number");
                    System.out.println("Room number for Reservation ID " + reservationId +
                            " and Guest " + guestName + " Room number is: " + roomNumber);
                } else {
                    System.out.println("Reservation not found for the given ID and guest name.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    private static void updateReservations(Connection con,Scanner sc){
        try{
            System.out.println("Enter reservation ID to update :");
            int reservationId = sc.nextInt();
            sc.nextLine();

            if(!reservationExists(con,reservationId)){
                System.out.println("Reservation NOT found for the given ID");
                return;
            }
            System.out.println("Enter new guest name :");
            String newGuestName=sc.nextLine();
            System.out.println("Enter new room Number :");
            int newRoomNumber=sc.nextInt();
            System.out.println("Enter new contact Number");
            String newContactNumber=sc.next();

            String sql="UPDATE reservations SET guest_name = '"+ newGuestName +"',"+
                    "room_number = "+ newRoomNumber+","+
                    "contact_number= '"+ newContactNumber + "' " +
                    "WHERE reservation_Id= "+ reservationId;

            try(Statement statement=con.createStatement()){
                int affectedRows=statement.executeUpdate(sql);


                if(affectedRows>0){
                    System.out.println("Reservation updated Successfull!!");

                }else {
                    System.out.println("Reservation update failed!!");
                }


            }
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
    private static void deleteReservations(Connection con, Scanner sc) {
        try {
            System.out.print("Enter reservation ID to delete: ");
            int reservationId = sc.nextInt();

            if (!reservationExists(con, reservationId)) {
                System.out.println("Reservation not found for the given ID.");
                return;
            }

            String sql = "DELETE FROM reservations WHERE reservation_id = " + reservationId;

            try (Statement statement = con.createStatement()) {
                int affectedRows = statement.executeUpdate(sql);

                if (affectedRows > 0) {
                    System.out.println("Reservation deleted successfully!");
                } else {
                    System.out.println("Reservation deletion failed.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static boolean reservationExists(Connection con, int reservationId) {
        try {
            String sql = "SELECT reservation_id FROM reservations WHERE reservation_id = " + reservationId;

            try (Statement statement = con.createStatement();
                 ResultSet resultSet = statement.executeQuery(sql)) {

                return resultSet.next(); // If there's a result, the reservation exists
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false; // Handle database errors as needed
        }
    }

    public static void exit() throws InterruptedException {
        System.out.print("Exiting System");
        int i = 5;
        while(i!=0){
            System.out.print(".");
            Thread.sleep(1000);
            i--;
        }
        System.out.println();
        System.out.println("ThankYou For Using Hotel Reservation System!!!");
    }
}