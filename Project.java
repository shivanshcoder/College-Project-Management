/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.awt.*;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;


import javax.swing.JTable;
import java.sql.*;
import java.util.ArrayList;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Project extends JFrame {
	Connection conn = null;
	private JPanel contentPane;
	private JTable table;

    private JTextField nameField;
    private JComboBox<String>subjectField;
    
    private ArrayList< String[] > subjectList;

	public Project(){
		getContentPane().setForeground(Color.BLUE);
		getContentPane().setBackground(Color.WHITE);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}

    public void add() {
        setTitle("ADD Project");

        setSize(778, 486);
        getContentPane().setLayout(null);

        JLabel nameLabel = new JLabel("PROJECT NAME");
        nameLabel.setFont(new Font("Tahoma", Font.PLAIN, 17));
        nameLabel.setBounds(60, 30, 150, 27);
        add(nameLabel);

        nameField = new JTextField();
        nameField.setBounds(200, 30, 150, 27);
        add(nameField);
        
        JLabel subjectLabel = new JLabel("Subject");
        subjectLabel.setFont(new Font("Tahoma", Font.PLAIN, 17));
        subjectLabel.setBounds(60, 60, 150, 27);
        add(subjectLabel);

        conn c = new conn();
        subjectList = c.subject_list();

        subjectField = new JComboBox<String>(subjectList.get(0));
        subjectField.setBounds(200, 60, 150, 27);
        add(subjectField);


        JButton Back = new JButton("BACK");
        Back.setBounds(60, 120, 150, 30);
        Back.setBackground(Color.BLACK);
        Back.setForeground(Color.WHITE);
        add(Back);

        Back.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent ae){
                
                // new Dashboard().setVisible(true);
                setVisible(false);
                
            }
        });

        JButton Next = new JButton("SAVE");
        Next.setBounds(200, 120, 150, 30);
        Next.setBackground(Color.BLACK);
        Next.setForeground(Color.WHITE);
        add(Next);

        setVisible(true);

        JLabel AddPassengers = new JLabel("ADD Project");
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

	
	public void list() {

		setBounds(430, 200, 1000, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);


		conn c = new conn();
		
		Box box = Box.createVerticalBox();
		box.setBounds(0,34,1000,450);
		
		table = new JTable(c.get_project_data(), new String[]{"Project Name", "Subject Name", "Teacher Name"});
		table.setBounds(0, 34, 1000, 450);
        table.setDefaultEditor(Object.class, null);

		JScrollPane pn = new JScrollPane(table);
		box.add(pn);
		add(box);

		JButton btnExit = new JButton("Back");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//new Dashboard().setVisible(true);
				setVisible(false);
			}
		});
		btnExit.setBounds(450, 500, 120, 30);
                btnExit.setBackground(Color.BLACK);
                btnExit.setForeground(Color.WHITE);
		contentPane.add(btnExit);
		
        getContentPane().setBackground(Color.WHITE);
	}
}