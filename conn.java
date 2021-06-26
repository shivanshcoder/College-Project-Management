

import java.security.NoSuchAlgorithmException;
import java.sql.*;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import com.mysql.cj.conf.ConnectionUrlParser.Pair;  

public class conn{
    Connection c;
    public conn(){  
        try{  
            Class.forName("com.mysql.cj.jdbc.Driver");  
            this.c = DriverManager.getConnection("jdbc:mysql:///db_cpm_old","newuser","root"); 
            
        }catch(Exception e){ 
            System.out.println(e);
        }  
    } 

    public boolean add_teacher(String name, String id, String password)throws SQLException{
        try{
            password = utils.passwd_to_hash(password);
        }
        catch(NoSuchAlgorithmException e){
            e.printStackTrace();
        }
        String q = "insert into teacher values('" + id + "', '" + name + "', '" + password + "')";
        
        try{

            Statement s = c.createStatement();  
            s.executeUpdate(q);
        }catch(SQLException e){
            // Failed to save
            return false;
        }
        // Saved successfully
        return true;
        // s.executeUpdate(q2);
        
    }

    public void add_student(String name)throws SQLException{
        String q = "insert into student(name) values('" +  name + "');";

        Statement s = c.createStatement();  
        s.executeUpdate(q);
    }
    public void add_subject(String name)throws SQLException{
        String q = "insert into subject(name) values('" +  name + "');";

        Statement s = c.createStatement();  
        s.executeUpdate(q);
    }

    // public ArrayList<String> get_student_row(int id)throws SQLException{
    //     String q = "select * from student where id="+id+";";

    //     ArrayList< String > temp = new ArrayList< String >();
    //     try{
            
    //         Statement s = c.createStatement();
    //         ResultSet rs = s.executeQuery(q);
    //         while(rs.next()){
                
    //             temp.add(
    //                 rs.getString("name")
    //             );
    
    //         }
    //     }catch(Exception e){
    //         e.printStackTrace();
    //     }
    //     return temp;

    // }

    public void update_student(int id, String newName)throws SQLException{
        String q = "update student set name='"+newName+"' where id="+id+";";

        Statement s = c.createStatement();
        s.executeUpdate(q);
    }

    public void delete_student(int id) throws SQLException{
        String q = "delete from student where id="+id+";";
        Statement s = c.createStatement();
        s.executeUpdate(q);
    }
    


    public static void main(String[] args) {
        try{
        //     conn c = new conn();
        //     c.delete_student(2);
        String path = JOptionPane.showInputDialog("Enter a path");
        System.out.println(path);
    
    }
        catch(Exception e){

        }
    }

    public boolean add_project(String name, String subject, String teacherID) throws SQLException{
        String q = "insert into project(name, subject, teacher_id) values('" + name + "', '" + subject + "', '" + teacherID + "')";
        
        try{
            Statement s = c.createStatement();  
            s.executeUpdate(q);
        }catch(SQLException e){
            // Failed to save
            return false;
        }
        // Saved successfully
        return true;
    }

    public boolean submit_project(int project_id, int student_id, String reportFile )throws SQLException{
        String q = "insert into project_submission(project_id, student_id, report_pdf) values ('" + project_id + "', '" + student_id + "', '" + reportFile + "');";
        
        
        try{

            Statement s = c.createStatement();  
            s.executeUpdate(q);
        }catch(SQLException e){
            // Failed to save
            return false;
        }
        // Saved successfully
        return true;
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

    public String[][] get_student_data(){
        String q = "select * from student;";
        ArrayList< String[] > temp = new ArrayList< String[] >();
        try{
            
            Statement s = c.createStatement();
            ResultSet rs = s.executeQuery(q);
            while(rs.next()){
                
                temp.add(new String[] {
                    rs.getString("id"),
                    rs.getString("name")
                });
    
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return temp.toArray(String[][]::new);
    }

    public String[][] get_subject_data(){
        String q = "select * from subject;";
        ArrayList< String[] > temp = new ArrayList< String[] >();
        try{
            
            Statement s = c.createStatement();
            ResultSet rs = s.executeQuery(q);
            while(rs.next()){
                
                temp.add(new String[] {
                    rs.getString("id"),
                    rs.getString("name")
                });
    
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return temp.toArray(String[][]::new);
    }

    public String[][] get_teacher_data(){
        String q = "select * from teacher;";
        ArrayList< String[] > temp = new ArrayList< String[] >();
        try{
            
            Statement s = c.createStatement();
            ResultSet rs = s.executeQuery(q);
            while(rs.next()){
                
                temp.add(new String[] {
                    rs.getString("id"),
                    rs.getString("name")
                });
    
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return temp.toArray(String[][]::new);
    }

    public String[][] get_submission_data(){
        String q = "select project.name as project_name, student.name as student_name, report_pdf from project_submission inner join student on student.id=student_id inner join project on project.id=project_id;";
        ArrayList< String[] > temp = new ArrayList< String[] >();
        try{
            
            Statement s = c.createStatement();
            ResultSet rs = s.executeQuery(q);
            while(rs.next()){
                
                temp.add(new String[] {
                    rs.getString("project_name"),
                    rs.getString("student_name"),
                    rs.getString("report_pdf")
                });
    
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        
        return temp.toArray(String[][]::new);
    }

    public String[][] get_project_data(String usernameFilter){
        String q = "select project.name, subject.name as subject, teacher.name as teacher from project inner join teacher on project.teacher_id=teacher.id inner join subject on subject.id=project.subject";
        
        if(usernameFilter != null){
            q+=" where project.teacher_id='"+usernameFilter+"';";
        }
        else{
            q+=";";
        }
        
        ArrayList< String[] > temp = new ArrayList< String[] >();
        try{
            
            Statement s = c.createStatement();
            ResultSet rs = s.executeQuery(q);
            while(rs.next()){
                
                temp.add(new String[] {
                    rs.getString("project.name"),
                    rs.getString("subject"),
                    rs.getString("teacher")
                });
    
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return temp.toArray(String[][]::new);
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

