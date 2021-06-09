

import java.security.NoSuchAlgorithmException;
import java.sql.*;  

public class conn{
    Connection c;
    public conn(){  
        try{  
            Class.forName("com.mysql.cj.jdbc.Driver");  
            this.c = DriverManager.getConnection("jdbc:mysql:///db_cpm","root","root"); 
            
        }catch(Exception e){ 
            System.out.println("ERRRORS");
            System.out.println(e);
        }  
    } 

    public void add_teacher(String name, int id, String password)throws SQLException{
        try{
            password = utils.passwd_to_hash(password);
        }
        catch(NoSuchAlgorithmException e){
            e.printStackTrace();
        }
        String q = "insert into teacher values(" + id + ", '" + name + "', '" + password + "')";
        // String q2 = "insert into login values(" + id + ", '" + password + "')";
        
        Statement s = c.createStatement();  
        s.executeUpdate(q);
        // s.executeUpdate(q2);
    }
    
    public boolean check_credentials(String id, String password) throws SQLException{

        try{
            password = utils.passwd_to_hash(password);
        }
        catch(NoSuchAlgorithmException e){
            e.printStackTrace();
        }
        System.out.println(password);
        String q = "select * from teacher where id='"+id+"' and password='"+password+"'";
            
        Statement s = c.createStatement();  
        ResultSet rs = s.executeQuery(q); 

        return rs.next();
    }


}  

