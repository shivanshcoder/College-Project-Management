
import java.awt.EventQueue;

import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.ArrayList;

import javax.swing.*;

import com.mysql.cj.conf.ConnectionUrlParser.Pair;

public class AddProject extends JFrame { // Third Frame

    JTextField nameField;
    JComboBox subjectField;
    // JComboBox teacherIDField;
    ArrayList< String[] > subjectList;

    public AddProject() {
        getContentPane().setForeground(Color.BLUE);
        getContentPane().setBackground(Color.WHITE);
        setTitle("ADD Project");

        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        setSize(778, 486);
        getContentPane().setLayout(null);

        JLabel nameLabel = new JLabel("PROJECT NAME");
        nameLabel.setFont(new Font("Tahoma", Font.PLAIN, 17));
        nameLabel.setBounds(60, 30, 150, 27);
        add(nameLabel);

        nameField = new JTextField();
        nameField.setBounds(200, 30, 150, 27);
        add(nameField);
        

        // JLabel subjectLabel = new JLabel("SUBJECT");
        // subjectLabel.setFont(new Font("Tahoma", Font.PLAIN, 17));
        // subjectLabel.setBounds(60, 60, 150, 27);
        // add(subjectLabel);

        // subjectField = new JTextField();
        // subjectField.setBounds(200, 60, 150, 27);
        // add(subjectField);


        JLabel subjectLabel = new JLabel("Subject");
        subjectLabel.setFont(new Font("Tahoma", Font.PLAIN, 17));
        subjectLabel.setBounds(60, 60, 150, 27);
        add(subjectLabel);

        conn c = new conn();
        subjectList = c.subject_list();

        subjectField = new JComboBox<String>(subjectList.get(0));
        subjectField.setBounds(200, 60, 150, 27);
        add(subjectField);



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


                try {
                    if(!(nameField.getText().equals(""))){

                        conn c = new conn();
                        
                        int subject_id = subjectField.getSelectedIndex();
                        
                        c.add_project(nameField.getText(), subjectList.get(1)[subject_id], Login.logged_in_username);
                        // c.add_project(nameField.getText(),subjectLabel.getText(), Login.logged_in_username);
                        
                        JOptionPane.showMessageDialog(null, "Project Added");
                        setVisible(false);
                    }
                    else{
                        JOptionPane.showMessageDialog(null, "Fields can't be empty");
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        setSize(900, 600);
        setVisible(true);
        setLocation(530, 200);

    }

}