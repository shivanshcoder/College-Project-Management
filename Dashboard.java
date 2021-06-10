
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import javax.swing.*;
import java.awt.Image;

// import java.sql.*;
import java.awt.event.*;
import java.awt.*;

public class Dashboard extends JFrame {
		
	private JPanel contentPane;

	public Dashboard() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		setBounds(530, 200, 850, 570);
		contentPane = new JPanel();
		setContentPane(contentPane);
		contentPane.setLayout(null);

		ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("images/ProjectManagement.png"));
		Image i3 = i1.getImage().getScaledInstance(500, 500, Image.SCALE_DEFAULT);
		ImageIcon i2 = new ImageIcon(i3);
		JLabel l1 = new JLabel(i2);
		l1.setBounds(250, 30, 500, 470);
		add(l1);

        JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		JMenu Admin = new JMenu("Admin");
		Admin.setForeground(Color.RED);
		menuBar.add(Admin);

		JMenuItem menu1 = new JMenuItem("Add Student");
		Admin.add(menu1);
		menu1.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent ae){
                try{
                    Student s = new Student();
					s.add();
					s.setVisible(true);
                }catch(Exception e ){}
            }
		});
		JMenuItem menu2 = new JMenuItem("Add Subject");
		Admin.add(menu2);
		menu2.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent ae){
                try{
                    Subject s = new Subject();
					s.add();
					s.setVisible(true);
                }catch(Exception e ){}
            }
		});


		JButton btnNewProject = new JButton("Add a Project");
		btnNewProject.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Project p = new Project();
					p.add();
					p.setVisible(true);
					setVisible(false);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		btnNewProject.setBounds(10, 30, 200, 30);
		btnNewProject.setBackground(Color.BLACK);
		btnNewProject.setForeground(Color.WHITE);
		contentPane.add(btnNewProject);

		JButton btnProjectSubmission = new JButton("Project Submission");
		btnProjectSubmission.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					ProjectSubmission p = new ProjectSubmission();
					p.add();
					p.setVisible(true);
					setVisible(false);
					setVisible(false);
				} catch (Exception e) {
					e.printStackTrace();
				}

			}
		});
		btnProjectSubmission.setBounds(10, 70, 200, 30);
		btnProjectSubmission.setBackground(Color.BLACK);
		btnProjectSubmission.setForeground(Color.WHITE);

		contentPane.add(btnProjectSubmission);

		JButton btnListProjects = new JButton("List Projects");
		btnListProjects.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Project p = new Project();
					p.list();
					p.setVisible(true);
					setVisible(false);

				} catch (Exception e1) {
					e1.printStackTrace();
				}

			}
		});
		btnListProjects.setBounds(10, 110, 200, 30);
		btnListProjects.setBackground(Color.BLACK);
		btnListProjects.setForeground(Color.WHITE);

		contentPane.add(btnListProjects);

		JButton btnListSubmissions = new JButton("List Project Submissions");
		btnListSubmissions.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {

					ProjectSubmission p = new ProjectSubmission();
					p.list();
					p.setVisible(true);
					setVisible(false);
					setVisible(false);

				} catch (Exception e1) {
					e1.printStackTrace();
				}

			}
		});
		btnListSubmissions.setBounds(10, 150, 200, 30);
		btnListSubmissions.setBackground(Color.BLACK);
		btnListSubmissions.setForeground(Color.WHITE);

		contentPane.add(btnListSubmissions);

		JButton btnListStudents = new JButton("List Students");
		btnListStudents.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Student s = new Student();
					s.list();
					s.setVisible(true);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		btnListStudents.setBounds(10, 190, 200, 30);
		btnListStudents.setBackground(Color.BLACK);
		btnListStudents.setForeground(Color.WHITE);

		contentPane.add(btnListStudents);

		JButton btnListSubjects = new JButton("List Subjects");
		btnListSubjects.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Subject s = new Subject();
					s.list();
					s.setVisible(true);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		btnListSubjects.setBounds(10, 230, 200, 30);
		btnListSubjects.setBackground(Color.BLACK);
		btnListSubjects.setForeground(Color.WHITE);

		contentPane.add(btnListSubjects);

		// JButton btnNewButton_4 = new JButton("Check Out");
		// btnNewButton_4.addActionListener(new ActionListener() {
		// public void actionPerformed(ActionEvent e) {
		// CheckOut check;
		// try {
		// check = new CheckOut();
		// check.setVisible(true);
		// setVisible(false);
		// } catch (SQLException e1) {
		// // TODO Auto-generated catch block
		// e1.printStackTrace();
		// }
		// }
		// });
		// btnNewButton_4.setBounds(10, 270, 200, 30);
		// btnNewButton_4.setBackground(Color.BLACK);
		// btnNewButton_4.setForeground(Color.WHITE);

		// contentPane.add(btnNewButton_4);

		// JButton btnNewButton_5 = new JButton("Update Check Status");
		// btnNewButton_5.addActionListener(new ActionListener() {
		// public void actionPerformed(ActionEvent e) {
		// try{
		// UpdateCheck update = new UpdateCheck();
		// update.setVisible(true);
		// setVisible(false);
		// }
		// catch(Exception e1){
		// e1.printStackTrace();
		// }
		// }
		// });
		// btnNewButton_5.setBounds(10, 310, 200, 30);
		// btnNewButton_5.setBackground(Color.BLACK);
		// btnNewButton_5.setForeground(Color.WHITE);

		// contentPane.add(btnNewButton_5);

		// JButton btnNewButton_6 = new JButton("Update Room Status");
		// btnNewButton_6.addActionListener(new ActionListener() {
		// public void actionPerformed(ActionEvent e) {
		// try{
		// UpdateRoom room = new UpdateRoom();
		// room.setVisible(true);
		// setVisible(false);
		// }catch(Exception s)
		// {
		// s.printStackTrace();
		// }
		// }
		// });
		// btnNewButton_6.setBounds(10, 350, 200, 30);
		// btnNewButton_6.setBackground(Color.BLACK);
		// btnNewButton_6.setForeground(Color.WHITE);

		// contentPane.add(btnNewButton_6);

		// JButton btnPickUpSerice = new JButton("Pick up Service");
		// btnPickUpSerice.addActionListener(new ActionListener() {
		// public void actionPerformed(ActionEvent arg0) {
		// try{
		// PickUp pick = new PickUp();
		// pick.setVisible(true);
		// setVisible(false);
		// }
		// catch(Exception e){
		// e.printStackTrace();
		// }
		// }
		// });
		// btnPickUpSerice.setBounds(10, 390, 200, 30);
		// btnPickUpSerice.setBackground(Color.BLACK);
		// btnPickUpSerice.setForeground(Color.WHITE);

		// contentPane.add(btnPickUpSerice);

		// JButton btnSearchRoom = new JButton("Search Room");
		// btnSearchRoom.addActionListener(new ActionListener() {
		// public void actionPerformed(ActionEvent e) {
		// try {
		// SearchRoom search = new SearchRoom();
		// search.setVisible(true);
		// setVisible(false);
		// }
		// catch (Exception ss){
		// ss.printStackTrace();
		// }
		// }
		// });
		// btnSearchRoom.setBounds(10, 430, 200, 30);
		// btnSearchRoom.setBackground(Color.BLACK);
		// btnSearchRoom.setForeground(Color.WHITE);

		// contentPane.add(btnSearchRoom);

		JButton btnNewButton_7 = new JButton("Log Out");
		btnNewButton_7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				try {
					new Login().setVisible(true);
					setVisible(false);

				} catch (Exception e) {
					e.printStackTrace();
				}

			}
		});
		btnNewButton_7.setBounds(10, 470, 200, 30);
		btnNewButton_7.setBackground(Color.BLACK);
		btnNewButton_7.setForeground(Color.WHITE);

		contentPane.add(btnNewButton_7);
		getContentPane().setBackground(Color.WHITE);

		setVisible(true);
	}
}
