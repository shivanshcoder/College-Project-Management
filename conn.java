

import java.security.NoSuchAlgorithmException;
import java.sql.*;
import java.util.ArrayList;

import com.mysql.cj.conf.ConnectionUrlParser.Pair;  

public class conn{
    Connection c;
    public conn(){  
        try{  
            Class.forName("com.mysql.cj.jdbc.Driver");  
            this.c = DriverManager.getConnection("jdbc:mysql:///db_cpm","root","root"); 
            
        }catch(Exception e){ 
            System.out.println(e);
        }  
    } 

    public void add_teacher(String name, String id, String password)throws SQLException{
        try{
            password = utils.passwd_to_hash(password);
        }
        catch(NoSuchAlgorithmException e){
            e.printStackTrace();
        }
        String q = "insert into teacher values('" + id + "', '" + name + "', '" + password + "')";
        // String q2 = "insert into login values(" + id + ", '" + password + "')";
        
        Statement s = c.createStatement();  
        s.executeUpdate(q);
        // s.executeUpdate(q2);
        
    }

    public void add_student(String name)throws SQLException{
        String q = "insert into student(name) values('" +  name + "')";
        Statement s = c.createStatement();  
        s.executeUpdate(q);
    }

    public void add_project(String name, String subject, String teacherID) throws SQLException{
        String q = "insert into project(name, subject, teacher_id) values('" + name + "', '" + subject + "', '" + teacherID + "')";
        Statement s = c.createStatement();
        s.executeUpdate(q); 
    }

    public void submit_project(int project_id, int student_id, String reportFile )throws SQLException{
        String q = "insert into project_submission(project_id, student_id, report_pdf) values ('" + project_id + "', '" + student_id + "', '" + reportFile + "');";
        
        Statement s = c.createStatement();
        s.executeUpdate(q); 
    }
    
    
    private ArrayList< String[] > get_id_name_pair_list(String tableName){
        String q = "select id, name from "+tableName+";";
        Statement s;
        ResultSet rs;
        
        ArrayList< String[] >the_list = new ArrayList< String[] >();
        ArrayList<String>names = new ArrayList<String>();
        ArrayList<String>ids = new ArrayList<String>();
        try{

            s = c.createStatement();  
            rs = s.executeQuery(q);
            while((rs.next())){
                names.add((rs.getString("name")));
                ids.add((rs.getString("id")));
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
        the_list.add(names.toArray(new String[0]));
        the_list.add(ids.toArray(new String[0]));
        return the_list;
    } 

    public ArrayList< String[] > teachers_list(){
        return get_id_name_pair_list("teacher");
    } 
    public ArrayList< String[] > student_list(){
        return get_id_name_pair_list("student");
    } 
    public ArrayList< String[] > subject_list(){
        return get_id_name_pair_list("subject");
    } 
    public ArrayList< String[] > project_list(){
        return get_id_name_pair_list("project");
    } 


    

    public boolean check_credentials(String id, String password) throws SQLException{

        try{
            password = utils.passwd_to_hash(password);
        }
        catch(NoSuchAlgorithmException e){
            e.printStackTrace();
        }
        String q = "select * from teacher where id='"+id+"' and password='"+password+"'";
            
        Statement s = c.createStatement();  
        ResultSet rs = s.executeQuery(q); 

        return rs.next();
    }


}  

