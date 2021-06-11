

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class Teacher extends JFrame { // Third Frame

    JTextField nameField, idField;
    JPasswordField passwordField;

	private JPanel contentPane;
	private JTable table;

    public Teacher(){
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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
		
		table = new JTable(c.get_teacher_data(), new String[]{"Teacher ID", "Teacher Name"});
		table.setBounds(0, 34, 1000, 450);
        table.setDefaultEditor(Object.class, null);

		JScrollPane pn = new JScrollPane(table);
		box.add(pn);
		add(box);

		JButton btnExit = new JButton("Back");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new Dashboard().setVisible(true);
				setVisible(false);
			}
		});
		btnExit.setBounds(450, 500, 120, 30);
                btnExit.setBackground(Color.BLACK);
                btnExit.setForeground(Color.WHITE);
		contentPane.add(btnExit);
		
        getContentPane().setBackground(Color.WHITE);
	}

    public void add() {
        getContentPane().setForeground(Color.BLUE);
        getContentPane().setBackground(Color.WHITE);
        setTitle("ADD Teacher Account");

        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        setSize(778, 486);
        getContentPane().setLayout(null);

        JLabel nameLabel = new JLabel("NAME");
        nameLabel.setFont(new Font("Tahoma", Font.PLAIN, 17));
        nameLabel.setBounds(60, 30, 150, 27);
        add(nameLabel);

        nameField = new JTextField();
        nameField.setBounds(200, 30, 150, 27);
        add(nameField);
        

        JLabel idLabel = new JLabel("ID");
        idLabel.setFont(new Font("Tahoma", Font.PLAIN, 17));
        idLabel.setBounds(60, 60, 150, 27);
        add(idLabel);

        idField = new JTextField();
        idField.setBounds(200, 60, 150, 27);
        add(idField);


        JLabel passwordLabel = new JLabel("Password");
        passwordLabel.setFont(new Font("Tahoma", Font.PLAIN, 17));
        passwordLabel.setBounds(60, 90, 150, 27);
        add(passwordLabel);

        passwordField = new JPasswordField();
        passwordField.setBounds(200, 90, 150, 27);
        add(passwordField);

        JButton Back = new JButton("BACK");
        Back.setBounds(50, 120, 150, 30);
        Back.setBackground(Color.BLACK);
        Back.setForeground(Color.WHITE);
        add(Back);

        Back.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent ae){
                if(Login.logged_in_username == null){
                    new MainWindow().setVisible(true);
                    setVisible(false);
                }
                else{
                    new Dashboard().setVisible(true);
                    setVisible(false);
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

        
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("images/auth.png"));
        Image i3 = i1.getImage().getScaledInstance(300,300, Image.SCALE_DEFAULT);
        ImageIcon i2 = new ImageIcon(i3);
        JLabel image = new JLabel(i2);
        image.setBounds(450, 120, 300,300);
        add(image);

        Next.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {


                try {
                    if(nameField.getText().equals("") || idField.getText().equals("") || passwordField.getText().equals("")){
                        JOptionPane.showMessageDialog(null, "Fields can't be empty");
                    }
                    else{
                        conn c = new conn();
                        boolean ret_val = c.add_teacher(nameField.getText(),idField.getText(), passwordField.getText());
                        if(ret_val){
                            JOptionPane.showMessageDialog(null, "Teacher Added");   
                            setVisible(false);
                        }
                        else{
                            JOptionPane.showMessageDialog(null, "Error! Teacher ID already exists");   
                        }
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