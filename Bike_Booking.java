
package bike_booking;
import java.util.*;

class TicketBooking {
    Jdbc jdbc= new Jdbc();
//    int ticketid;
//    int kilometres;
//    String Name;
    boolean isTicketBooked = false; // To track whether a ticket is booked

    public void booking(int ticketid, int kilometres, String Name) {
//        this.ticketid = ticketid;
//        this.kilometres = kilometres;
//        this.Name = Name;
        isTicketBooked = true;
        System.out.println("#############################################");
        System.out.println("Ticket booked successfully. Here are the details:");
        System.out.println("Ticket ID = " + ticketid);
        System.out.println("Name = " + Name);
        System.out.println("Total Rupees = " + kilometres * 15);
    }

    public void cancelticket(int ticketid) {
        if (!isTicketBooked) {
            System.out.println("No tickets to cancel.");
        } else {
            jdbc.Disp_and_cancel(ticketid,2);
            
            isTicketBooked = false;
            System.out.println("Ticket cancelled successfully.");
            System.out.println("##########################");
        }
    }

    public void displayticket(int ticketid) {
        if (isTicketBooked) {
             int[] result=jdbc.Disp_and_cancel(ticketid,1);
             System.out.println("Your name : "+ jdbc.customer_user_name);
             System.out.println("Your Register Number is : " + result[1]);
             System.out.println("Your Ticket id is : "+ ticketid);
             System.out.println("Your total Amount is : "+ result[0]*15);
            
        } else {
            System.out.println("No tickets are booked.");
        }
    }
}

public class Bike_Booking {
    public static void main(String[] args) {
        System.out.println("Welcome to the bike booking .Please Login to continue");
        System.out.println("_____New User____.Sign up");
        System.out.println("ENTER 2 for login and 1 for register");
        Scanner sc = new Scanner(System.in);
        int ways = sc.nextInt();
        Jdbc jdbc = new Jdbc();
        if(ways == 1){
            System.out.println("Enter name");
            sc.nextLine();
            String n1 = sc.nextLine();
            System.out.println("Enter email");
            String email = sc.nextLine();
            
            System.out.println("Enter phone number");
            String phone = sc.nextLine();
            System.out.println("Enter your password");
            int password = sc.nextInt();
             boolean result = jdbc.register(n1,email , phone ,password);
             if(result){
                 System.out.println("Registration successfull");
                 System.out.println("Your registration number is "+ jdbc.user_id);
             }
             else{
                 System.out.println("Registration unsuccessfull");
             }
            
        }
        else if(ways==2){
            System.out.println("Enter the register id and password to Continue");
            System.out.println("ENter the register id");
            int register_id = sc.nextInt();
            System.out.println("Enter the password");
            int password=sc.nextInt();
            String name = jdbc.validateuser(register_id,password);
            if(jdbc.isvalidate){
             System.out.println("Welcome " + name + " to the cab booking website. Please select an option to continue:");
            System.out.println("1. Book a ticket");
            System.out.println("2. Display ticket");
            System.out.println("3. Cancel ticket");
            System.out.println("4. Exit");

            TicketBooking tk = new TicketBooking();
            int choice;
            
            while (true) {
                System.out.println("Enter your choice:");
                choice = sc.nextInt();
                switch (choice) {
                    case 1:
                        if (tk.isTicketBooked) {
                            System.out.println("You already have a booked ticket.");
                        } else {
                            
//                            System.out.println("Enter Register ID:");
                            
                            System.out.println("Enter kilometres:");
                            int kilometres = sc.nextInt();
                             int tik_id= jdbc.Book_ticket(name, kilometres,register_id);
                            tk.booking(tik_id,kilometres,name);
//                            System.out.println("your ticket is is = "+tik_id);
                        }
                        break;
                    case 2:
                        System.out.println("#####################");
                        System.out.println("ENter the ticket id to continue");
                        int ticket_id = sc.nextInt();
                        tk.displayticket(ticket_id);
                        break;
                    case 3:
                        System.out.println("Enter ticket ID to cancel:");
                        int ticket_id1 = sc.nextInt();
                        tk.cancelticket(ticket_id1);
                        break;
                    case 4:
                        System.out.println("Exiting. Thank you!");
                        sc.close();
                        return;
                    default:
                        System.out.println("Please select a valid option.");
                        break;
                }
            }
        
    }else{
            System.out.println("enter correct details");
        }
        }
}
}
        
    
