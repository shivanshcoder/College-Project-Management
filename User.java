

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.xml.crypto.dsig.spec.ExcC14NParameterSpec;

public class User extends UtilFrame { // Third Frame

    JTextField nameField, usernameField;
    JPasswordField passwordField;
    JComboBox<String>roleField;

	private JPanel contentPane;
	private JTable table;

    private String[] colNames = new String[]{"ID", "NAME"};

    public User(){
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
    public void listViewStudents(){
        try{

            conn c = new conn();
            
            listView(c.get_student_data());
        }catch(Exception e){   
        }
    }

	protected void listView(String[][] listViewData ) {

		setBounds(430, 200, 1000, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		Box box = Box.createVerticalBox();
		box.setBounds(0,34,1000,450);
		
		table = new JTable(listViewData, colNames);
		table.setBounds(0, 34, 1000, 450);
        table.setDefaultEditor(Object.class, null);

		JScrollPane pn = new JScrollPane(table);
		box.add(pn);
		add(box);

		JButton btnExit = new JButton("Back");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new Dashboard(0).setVisible(true);
				setVisible(false);
			}
		});
		btnExit.setBounds(450, 500, 120, 30);
                btnExit.setBackground(Color.BLACK);
                btnExit.setForeground(Color.WHITE);
		contentPane.add(btnExit);
		
        getContentPane().setBackground(Color.WHITE);
	}

    public void addUser() {
        getContentPane().setForeground(Color.BLUE);
        getContentPane().setBackground(Color.WHITE);
        setTitle("ADD Account");

        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        setSize(778, 486);
        getContentPane().setLayout(null);

        addInputField("NAME", (nameField = new JTextField()));
        addInputField("USERNAME", (usernameField = new JTextField()));
        addInputField("ROLE", (roleField = new JComboBox<String>( new String[]{"Teacher", "Student"} )));

        enableBackBtn(new ActionListener(){
            public void actionPerformed(ActionEvent ae){
                if(Login.logged_in_username == null){
                    new MainWindow().setVisible(true);
                    setVisible(false);
                }
                else{
                    new Dashboard(0).setVisible(true);
                    setVisible(false);
                }
            }
        });

        enableSavebtn(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                
                try {
                    if(nameField.getText().equals("") || usernameField.getText().equals("") || passwordField.getText().equals("")){
                        JOptionPane.showMessageDialog(null, "Fields can't be empty");
                    }
                    else{
                        conn c = new conn();
                        boolean ret_val = c.add_user(nameField.getText(),usernameField.getText(), passwordField.getText(), roleField.getSelectedIndex());
                        if(ret_val){
                            JOptionPane.showMessageDialog(null, "User Added");   
                            setVisible(false);
                        }
                        else{
                            JOptionPane.showMessageDialog(null, "Error! Username already exists");   
                        }
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        setVisible(true);


        JLabel AddPassengers = new JLabel("ADD Account");
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

        setSize(900, 600);
        setVisible(true);
        setLocation(530, 200);

    }
    public static void main(String[] args) {
        User s = new User();
        s.addUser();
        s.setVisible(true);
    }

}