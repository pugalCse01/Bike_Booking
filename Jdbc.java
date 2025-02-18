package bike_booking;
import java.sql.*;

public class Jdbc{
    String customer_user_name;
    boolean isvalidate = false;
    int user_id;
    String durl = "jdbc:mysql://localhost:3306/cab_booking";
    String dname = "root";
    String dpass = "Pugal@9965";
         
     public boolean register(String name,String email,String phone,int password ){
         this.user_id = user_id;
 boolean isregisteruser = false;
 String query = "INSERT INTO customer_details(Name,email,Phone,password) Values (? ,? ,?,?)";
 try{
       Connection con = DriverManager.getConnection(durl,dname,dpass);
        PreparedStatement pr =  con.prepareStatement(query,Statement.RETURN_GENERATED_KEYS);
        pr.setString(1,name);
        pr.setString(2, email);
        pr.setString(3, phone);
        pr.setInt(4,password);
        int affected = pr.executeUpdate();
        ResultSet gk = pr.getGeneratedKeys();
        
        if(affected > 0){
            if(gk.next()){
             isregisteruser = true;
             user_id = gk.getInt(1);
        }
           }
        con.close();
        pr.close();
 }catch(Exception e){
     System.out.print(e.toString());
 }
        return isregisteruser;
       
 }
     public String validateuser(int user_id,int password){
         this.isvalidate = isvalidate;
          
          String name = "";
          try{
              Connection con = DriverManager.getConnection(durl,dname,dpass);
              String query1 = "select Name from customer_details where Register_id= ?  and password = ?";
              PreparedStatement pr = con.prepareStatement(query1);
              String query2 = "select Name from customer_details where Register_id= ?  and password = ?";
              pr.setInt(1,user_id);
              pr.setInt(2, password);
              System.out.println("Register user only");
              ResultSet rs = pr.executeQuery();
              
              if(rs.next()){
                  isvalidate = true;
                   name = rs.getString("Name");          
              }
              }catch(Exception e){
              System.out.print(e.toString());
          }
          return name;
     }
     public int Book_ticket(String name,int kilometres,int register_id){
          int name1 = 0;
          
         try{
            
             
             Connection con = DriverManager.getConnection(durl,dname,dpass);
             String query = "INSERT INTO customer_booking_detailsf(customer_name,kilometres,customer_reg_id) Values (? ,? ,?)";
             String query2 = "select ticket_id from customer_booking_detailsf where customer_reg_id= ?";
             PreparedStatement pr = con.prepareStatement(query,Statement.RETURN_GENERATED_KEYS);
             pr.setString(1,name);
             pr.setInt(2, kilometres);
             pr.setInt(3, register_id);
             int affected = pr.executeUpdate();
             if(affected>0){
                 PreparedStatement pr1 = con.prepareStatement(query2);
                 pr1.setInt(1,register_id);
                ResultSet rs = pr1.executeQuery();
                if(rs.next()){
                   name1 = rs.getInt("ticket_id");
                   
                }
             }
              }catch(Exception e){
             System.out.print(e.toString());
         }
     return name1;
       
     }
     public int[] Disp_and_cancel(int ticketid,int choice){
         this.customer_user_name = customer_user_name;
         try{
             Connection con = DriverManager.getConnection(durl,dname,dpass);
             if(choice == 1){
                 String query = "Select customer_name, kilometres,customer_reg_id from customer_booking_detailsf where ticket_id = ?";
                 
             PreparedStatement pr = con.prepareStatement(query);
             pr.setInt(1,ticketid);
             ResultSet rs = pr.executeQuery();
             if(rs.next()){
                 customer_user_name = rs.getString("customer_name");
                 
             return new int[]{rs.getInt("kilometres"), rs.getInt("customer_reg_id")};
             }
             
             }
             else{
                  String query = "DELETE FROM customer_booking_detailsf WHERE ticket_id = ?";
                 
             PreparedStatement pr = con.prepareStatement(query);
             pr.setInt(1,ticketid);
               pr.executeUpdate(); 
             
             con.close();
        pr.close();
             }
         }catch(Exception e){
             System.out.println(e.toString());
         }
         
         return new int[]{1,2};
     }
}
