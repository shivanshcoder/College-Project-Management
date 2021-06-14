
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
	int btnIndex = 0;
		
	private JPanel contentPane;

	public Dashboard(int user_type){
		if(user_type==1){
			teacher_dashboard();
		}
		else{
			student_dashboard();
		}
	}

	private JButton addBtn(String text, ActionListener callback){
		JButton c = new JButton(text);
		c.addActionListener(callback);
		c.setBounds(10, 30+(btnIndex*40), 200, 30);
		c.setBackground(Color.BLACK);
		c.setForeground(Color.WHITE);
		btnIndex++;
		return c;
	}

	public void student_dashboard() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		setBounds(530, 200, 850, 570);
		contentPane = new JPanel();
		setContentPane(contentPane);
		contentPane.setLayout(null);

		ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("images/front.jpg"));
		Image i3 = i1.getImage().getScaledInstance(500, 500, Image.SCALE_DEFAULT);
		ImageIcon i2 = new ImageIcon(i3);
		JLabel l1 = new JLabel(i2);
		l1.setBounds(285, 30, 500, 470);
		add(l1);

		// contentPane.add(
		// 	addBtn("Add a Project", new ActionListener(){
		// 		public void actionPerformed(ActionEvent e) {
		// 			try {
		// 				Project p = new Project();
		// 				p.add();
		// 				p.setVisible(true);
		// 			} catch (Exception e1) {
		// 				e1.printStackTrace();
		// 			}
		// 		}
		// 	})
		// );

		
		contentPane.add(
			addBtn("Project Submission", new ActionListener(){
				public void actionPerformed(ActionEvent arg0) {
					try {
						ProjectSubmission p = new ProjectSubmission();
						p.add();
						p.setVisible(true);
					} catch (Exception e) {
						e.printStackTrace();
					}
	
				}
			})
		);

		contentPane.add(
			addBtn("List Projects", new ActionListener(){
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
			})
		);

		contentPane.add(
			addBtn("List Project Submissions", new ActionListener(){
				public void actionPerformed(ActionEvent e) {
					try {
	
						ProjectSubmission p = new ProjectSubmission();
						p.list();
						p.setVisible(true);
						setVisible(false);
	
					} catch (Exception e1) {
						e1.printStackTrace();
					}
	
				}
			})
		);


		contentPane.add(
			addBtn("List Students", new ActionListener(){
				public void actionPerformed(ActionEvent e) {
					try {
						Student s = new Student();
						s.list();
						s.setVisible(true);
					} catch (Exception e1) {
						e1.printStackTrace();
					}
				}
			})
		);


		// contentPane.add(
		// 	addBtn("List Subjects", new ActionListener(){
		// 		public void actionPerformed(ActionEvent e) {
		// 			try {
		// 				Subject s = new Subject();
		// 				s.list();
		// 				s.setVisible(true);
		// 			} catch (Exception e1) {
		// 				e1.printStackTrace();
		// 			}
		// 		}
		// 	})
		// );


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
	public void teacher_dashboard() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		setBounds(530, 200, 850, 570);
		contentPane = new JPanel();
		setContentPane(contentPane);
		contentPane.setLayout(null);

		ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("images/front.jpg"));
		Image i3 = i1.getImage().getScaledInstance(500, 500, Image.SCALE_DEFAULT);
		ImageIcon i2 = new ImageIcon(i3);
		JLabel l1 = new JLabel(i2);
		l1.setBounds(285, 30, 500, 470);
		add(l1);

		contentPane.add(
			addBtn("Add a Project", new ActionListener(){
				public void actionPerformed(ActionEvent e) {
					try {
						Project p = new Project();
						p.add();
						p.setVisible(true);
					} catch (Exception e1) {
						e1.printStackTrace();
					}
				}
			})
		);

		
		contentPane.add(
			addBtn("Create Classroom", new ActionListener(){
				public void actionPerformed(ActionEvent arg0) {
					try {

					} catch (Exception e) {
						e.printStackTrace();
					}
	
				}
			})
		);

		contentPane.add(
			addBtn("List Projects", new ActionListener(){
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
			})
		);

		contentPane.add(
			addBtn("List Project Submissions", new ActionListener(){
				public void actionPerformed(ActionEvent e) {
					try {
	
						ProjectSubmission p = new ProjectSubmission();
						p.list();
						p.setVisible(true);
						setVisible(false);
	
					} catch (Exception e1) {
						e1.printStackTrace();
					}
	
				}
			})
		);


		contentPane.add(
			addBtn("List Enrolled Students", new ActionListener(){
				public void actionPerformed(ActionEvent e) {
					try {
						Student s = new Student();
						s.list();
						s.setVisible(true);
					} catch (Exception e1) {
						e1.printStackTrace();
					}
				}
			})
		);


		contentPane.add(
			addBtn("List Subjects", new ActionListener(){
				public void actionPerformed(ActionEvent e) {
					try {
						Subject s = new Subject();
						s.list();
						s.setVisible(true);
					} catch (Exception e1) {
						e1.printStackTrace();
					}
				}
			})
		);


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
