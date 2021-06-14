
import java.security.NoSuchAlgorithmException;
import java.sql.*;
import java.util.ArrayList;

import com.mysql.cj.conf.ConnectionUrlParser.Pair;

/*
    Encapsulates the Database Connection 
*/
public class conn {

    Connection c;

    /*
     * @description: Sets up the connection to the database and ready for sql
     * transactions
     */
    public conn() throws Exception {

        Class.forName("com.mysql.cj.jdbc.Driver");
        this.c = DriverManager.getConnection("jdbc:mysql:///db_cpm", "root", "root");

    }

    /*
     * @description: Saves the user to the database
     * 
     * @name: Name of the User
     * 
     * @username: username for authentication
     * 
     * @password: password for authentication
     * 
     * @user_type: 0: Teacher 1: Student
     * 
     * @return: boolean indicating operation success or failure
     */
    public boolean add_user(String name, String username, String password, int user_type) {
        try {
            password = utils.passwd_to_hash(password);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        String q_1 = "insert into user values(?, ?);";
        String q_2;
        if (user_type == 0)
            q_2 = "insert into teacher(name, username) values(?, ?);";
        else
            q_2 = "insert into student(name, username) values(?, ?);";

        try {
            PreparedStatement ps1 = c.prepareStatement(q_1);
            ps1.setString(1, username);
            ps1.setString(2, password);

            PreparedStatement ps2 = c.prepareStatement(q_2);
            ps2.setString(1, name);
            ps2.setString(2, username);

            ps1.executeUpdate();
            ps2.executeUpdate();

            ps1.close();
            ps2.close();

        } catch (SQLException e) {
            // Failed to save
            return false;
        }
        // Saved successfully
        return true;

    }

    // public boolean add_teacher(String name, String id, String password) throws
    // SQLException {
    // try {
    // password = utils.passwd_to_hash(password);
    // } catch (NoSuchAlgorithmException e) {
    // e.printStackTrace();
    // }
    // String q = "insert into teacher values('" + id + "', '" + name + "', '" +
    // password + "')";

    // try {

    // Statement s = c.createStatement();
    // s.executeUpdate(q);
    // } catch (SQLException e) {
    // // Failed to save
    // return false;
    // }
    // // Saved successfully
    // return true;
    // // s.executeUpdate(q2);

    // }

    // public boolean add_student(String name) throws SQLException {
    // String q = "insert into student(name) values('" + name + "')";

    // try {

    // Statement s = c.createStatement();
    // s.executeUpdate(q);
    // } catch (SQLException e) {
    // // Failed to save
    // return false;
    // }
    // // Saved successfully
    // return true;
    // }

    public boolean add_project(String name, String subject, String teacherID) throws SQLException {
        String q = "insert into project(name, subject, teacher_id) values('" + name + "', '" + subject + "', '"
                + teacherID + "')";

        try {

            Statement s = c.createStatement();
            s.executeUpdate(q);
        } catch (SQLException e) {
            // Failed to save
            return false;
        }
        // Saved successfully
        return true;
    }

    public boolean submit_project(int project_id, int student_id, String reportFile) throws SQLException {
        String q = "insert into project_submission(project_id, student_id, report_pdf) values ('" + project_id + "', '"
                + student_id + "', '" + reportFile + "');";

        try {

            Statement s = c.createStatement();
            s.executeUpdate(q);
        } catch (SQLException e) {
            // Failed to save
            return false;
        }
        // Saved successfully
        return true;
    }

    private ArrayList<String[]> get_id_name_pair_list(String tableName) {
        String q = "select id, name from " + tableName + ";";
        Statement s;
        ResultSet rs;

        ArrayList<String[]> the_list = new ArrayList<String[]>();
        ArrayList<String> names = new ArrayList<String>();
        ArrayList<String> ids = new ArrayList<String>();
        try {

            s = c.createStatement();
            rs = s.executeQuery(q);
            while ((rs.next())) {
                names.add((rs.getString("name")));
                ids.add((rs.getString("id")));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        the_list.add(names.toArray(new String[0]));
        the_list.add(ids.toArray(new String[0]));
        return the_list;
    }

    public ArrayList<String[]> teachers_list() {
        return get_id_name_pair_list("teacher");
    }

    public ArrayList<String[]> student_list() {
        return get_id_name_pair_list("student");
    }

    public ArrayList<String[]> subject_list() {
        return get_id_name_pair_list("subject");
    }

    public ArrayList<String[]> project_list() {
        return get_id_name_pair_list("project");
    }

    public String[][] get_student_data() {
        String q = "select * from student;";
        ArrayList<String[]> temp = new ArrayList<String[]>();
        try {

            Statement s = c.createStatement();
            ResultSet rs = s.executeQuery(q);
            while (rs.next()) {

                temp.add(new String[] { rs.getString("id"), rs.getString("name") });

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return temp.toArray(String[][]::new);
    }

    // public String[][] get_subject_data() {
    //     String q = "select * from subject;";
    //     ArrayList<String[]> temp = new ArrayList<String[]>();
    //     try {
    //         Statement s = c.createStatement();
    //         ResultSet rs = s.executeQuery(q);
    //         while (rs.next()) {
    //             temp.add(new String[] { rs.getString("id"), rs.getString("name") });
    //         }
    //     } catch (Exception e) {
    //         e.printStackTrace();
    //     }
    //     return temp.toArray(String[][]::new);
    // }

    public String[][] get_teacher_data() {
        String q = "select * from teacher;";
        ArrayList<String[]> temp = new ArrayList<String[]>();
        try {

            Statement s = c.createStatement();
            ResultSet rs = s.executeQuery(q);
            while (rs.next()) {

                temp.add(new String[] { rs.getString("id"), rs.getString("name") });

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return temp.toArray(String[][]::new);
    }

    public String[][] get_submission_data() {
        String q = "select project.name as project_name, student.name as student_name, report_pdf from project_submission inner join student on student.id=student_id inner join project on project.id=project_id;";
        ArrayList<String[]> temp = new ArrayList<String[]>();
        try {

            Statement s = c.createStatement();
            ResultSet rs = s.executeQuery(q);
            while (rs.next()) {

                temp.add(new String[] { rs.getString("project_name"), rs.getString("student_name"),
                        rs.getString("report_pdf") });

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return temp.toArray(String[][]::new);
    }

    public String[][] get_project_data() {
        String q = "select project.name, subject.name as subject, teacher.name as teacher from project inner join teacher on project.teacher_id=teacher.id inner join subject on subject.id=project.subject;";
        ArrayList<String[]> temp = new ArrayList<String[]>();
        try {

            Statement s = c.createStatement();
            ResultSet rs = s.executeQuery(q);
            while (rs.next()) {

                temp.add(new String[] { rs.getString("project.name"), rs.getString("subject"),
                        rs.getString("teacher") });

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return temp.toArray(String[][]::new);
    }

    // public boolean check_credentials(String id, String password) throws
    // SQLException {

    // try {
    // password = utils.passwd_to_hash(password);
    // } catch (NoSuchAlgorithmException e) {
    // e.printStackTrace();
    // }
    // String q = "select * from teacher where id='" + id + "' and password='" +
    // password + "'";

    // Statement s = c.createStatement();
    // ResultSet rs = s.executeQuery(q);

    // return rs.next();
    // }

    /*
     * @username: username of the user
     * 
     * @password: password of the user
     * 
     * @return: 0(User not found), 1(Teacher), 2(Student)
     */
    public int check_credentials(String username, String password) {

        try {
            password = utils.passwd_to_hash(password);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        String q = "select username, (select count(*) from teacher where username=?) as teacher, (select count(*) from student where username=?) as student from user where username=? and password=?";

        ResultSet rs;

        try {

            PreparedStatement s = c.prepareStatement(q);
            s.setString(1, username);
            s.setString(2, username);
            s.setString(3, username);
            s.setString(4, password);
            rs = s.executeQuery();
            if (!rs.next())
                return 0;

            // We found a match
            // Get the user type
            if(rs.getInt(2) == 1){
                return 1;
            }
            else{
                return 2;
            }

        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
        
    }

    // public static void main(String[] args) {
    //     try{

    //         conn c = new conn();
    //         System.out.println(c.check_credentials("shiva", "shiva"));
    //     }catch(Exception e){

    //     }
    // }
}
