/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class Dashboard extends JFrame {

    public static void main(String[] args) {
        new Dashboard().setVisible(true);
    }

    public Dashboard() {
        super("HOTEL MANAGEMENT SYSTEM");

        setForeground(Color.CYAN);
        setLayout(null);

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("images/ProjectManagement.png"));
        Image i2 = i1.getImage().getScaledInstance(1950, 1000, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel NewLabel = new JLabel(i3);
        NewLabel.setBounds(0, 0, 1950, 1000);
        add(NewLabel);

        JLabel mainLabel = new JLabel("College Project Management Dashboard");
        mainLabel.setForeground(Color.WHITE);
        mainLabel.setFont(new Font("Tahoma", Font.PLAIN, 46));
        mainLabel.setBounds(600, 60, 1000, 85);
        NewLabel.add(mainLabel);

        JMenuBar menuBar = new JMenuBar();
        setJMenuBar(menuBar);

        JMenu MainMenu = new JMenu("Main Menu");
        MainMenu.setForeground(Color.BLUE);
        menuBar.add(MainMenu);

        JMenuItem StudentDashboard = new JMenuItem("STUDENT DASHBOARD");
        MainMenu.add(StudentDashboard);

        StudentDashboard.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                //new Reception();
            }
        });

        JMenu AdminMenu = new JMenu("ADMIN");
        AdminMenu.setForeground(Color.RED);
        menuBar.add(AdminMenu);

        JMenuItem SubAdminMenu1 = new JMenuItem("ADD TEACHER");
        AdminMenu.add(SubAdminMenu1);

        SubAdminMenu1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                try {
                    JOptionPane.showMessageDialog(null, "Add Teacher");
                    //new AddEmployee().setVisible(true);
                    new AddTeacher().setVisible(true);
                } catch (Exception e) {
                }
            }
        });
        
        JMenuItem SubAdminMenu2 = new JMenuItem("ADD SUBJECT");
        AdminMenu.add(SubAdminMenu2);
        
        SubAdminMenu2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                try {
                    JOptionPane.showMessageDialog(null, "Add Subject");
                    // new AddRoom().setVisible(true);
                } catch (Exception e) {
                }
            }
        });

        

        // JMenuItem FlightDetailshello3 = new JMenuItem("ADD PROJECT");
        // AdminMenu.add(FlightDetailshello3);

        // FlightDetailshello3.addActionListener(new ActionListener() {
        //     public void actionPerformed(ActionEvent ae) {
        //         try {
        //             //new AddDrivers().setVisible(true);
        //         } catch (Exception e) {
        //         }
        //     }
        // });

        setSize(1950, 1090);
        setVisible(true);
        getContentPane().setBackground(Color.WHITE);
    }
}
