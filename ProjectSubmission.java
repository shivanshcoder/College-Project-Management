
import java.awt.EventQueue;

import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.CopyOption;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.sql.*;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

import com.mysql.cj.conf.ConnectionUrlParser.Pair;

public class ProjectSubmission extends JFrame { // Third Frame

    JComboBox projectField;
    JComboBox studentField;

    // JComboBox teacherIDField;
    ArrayList<String[]> projectList;
    ArrayList<String[]> studentList;

    public ProjectSubmission() {
        getContentPane().setForeground(Color.BLUE);
        getContentPane().setBackground(Color.WHITE);
        setTitle("ADD Project");

        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        setSize(778, 486);
        getContentPane().setLayout(null);

        
        conn c = new conn();
        projectList = c.project_list();
        studentList = c.student_list();

        JLabel nameLabel = new JLabel("PROJECT NAME");
        nameLabel.setFont(new Font("Tahoma", Font.PLAIN, 17));
        nameLabel.setBounds(60, 30, 150, 27);
        add(nameLabel);

        projectField = new JComboBox<String>(projectList.get(0));
        projectField.setBounds(200, 30, 150, 27);
        add(projectField);
        
        // JLabel subjectLabel = new JLabel("SUBJECT");
        // subjectLabel.setFont(new Font("Tahoma", Font.PLAIN, 17));
        // subjectLabel.setBounds(60, 60, 150, 27);
        // add(subjectLabel);
        
        // subjectField = new JTextField();
        // subjectField.setBounds(200, 60, 150, 27);
        // add(subjectField);
        
        
        JLabel subjectLabel = new JLabel("STUDENT");
        subjectLabel.setFont(new Font("Tahoma", Font.PLAIN, 17));
        subjectLabel.setBounds(60, 60, 150, 27);
        add(subjectLabel);
        
        studentField = new JComboBox<String>(studentList.get(0));
        studentField.setBounds(200, 60, 150, 27);
        add(studentField);

        JLabel reportLabel = new JLabel("PROJECT REPORT");
        reportLabel.setFont(new Font("Tahoma", Font.PLAIN, 17));
        reportLabel.setBounds(60, 90, 150, 27);
        add(reportLabel);

        JButton reportPath = new JButton("Choose Report");
        reportPath.setBounds(200, 90, 150, 30);
        reportPath.setBackground(Color.BLACK);
        reportPath.setForeground(Color.WHITE);
        add(reportPath);

        reportPath.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent ar){
                JFileChooser chooser = new JFileChooser();
                FileNameExtensionFilter filter = new FileNameExtensionFilter("PDF Files", "pdf");
                chooser.setFileFilter(filter);
                int returnVal = chooser.showOpenDialog(reportPath.getParent());
                if(returnVal == JFileChooser.APPROVE_OPTION) {
                    
                    System.out.println("You chose to open this file: " +
                        chooser.getSelectedFile().getName());
                    File newF = new File("submitted_files/"+chooser.getSelectedFile().getName());
                    try{

                        Files.copy(chooser.getSelectedFile().toPath(), newF.toPath(),StandardCopyOption.REPLACE_EXISTING);
                    }
                    catch(IOException e){
                        e.printStackTrace();
                    }
                }

            }
        });


        JButton Next = new JButton("SAVE");
        Next.setBounds(200, 120, 150, 30);
        Next.setBackground(Color.BLACK);
        Next.setForeground(Color.WHITE);
        add(Next);

        setVisible(true);

        JLabel AddPassengers = new JLabel("ADD Teacher Account");
        AddPassengers.setForeground(Color.BLUE);
        AddPassengers.setFont(new Font("Tahoma", Font.PLAIN, 31));
        AddPassengers.setBounds(450, 24, 442, 35);
        add(AddPassengers);

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("images/ProjectManagement.png"));
        Image i3 = i1.getImage().getScaledInstance(500, 500, Image.SCALE_DEFAULT);
        ImageIcon i2 = new ImageIcon(i3);
        JLabel image = new JLabel(i2);
        image.setBounds(410, 80, 480, 410);
        add(image);

        Next.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {


                // try {
                //     if(!(nameField.getText().equals(""))){

                //         conn c = new conn();
                        
                //         int subject_id = subjectField.getSelectedIndex();
                        
                //         c.add_project(nameField.getText(), subjectList.get(1)[subject_id], Login.logged_in_username);
                //         // c.add_project(nameField.getText(),subjectLabel.getText(), Login.logged_in_username);
                        
                //         JOptionPane.showMessageDialog(null, "Project Added");
                //         setVisible(false);
                //     }
                //     else{
                //         JOptionPane.showMessageDialog(null, "Fields can't be empty");
                //     }

                // } catch (Exception e) {
                //     e.printStackTrace();
                // }
            }
        });

        setSize(900, 600);
        setVisible(true);
        setLocation(530, 200);

    }

}