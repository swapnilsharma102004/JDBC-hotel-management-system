import jdk.jshell.spi.ExecutionControl;

import java.sql.*;
import java.util.*;

import static java.lang.System.exit;

public class Main {
    private static final String url="jdbc:mysql://localhost:3306/hotel_db";

    private static final String username="root";

    private static final String password= "Swapnil@251";

    public static void main(String[] args) throws  ClassNotFoundException,SQLException{



        try{
            Class.forName("com.mysql.jdbc.Driver");

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
                        reserveRoom(connection,sc);
                        break;
                    case 2:
                        viewReservations(connection);
                        break;
                    case 3:
                        getRoomNo(connection,sc);
                        break;
                    case 4:
                        updateReservations(connection,sc);
                        break;
                    case 5:
                        deleteReservations(connection,sc);
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
}