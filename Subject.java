
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class Subject extends JFrame {
    
    JTextField nameField, idField;
    JPasswordField passwordField;
    
	private JPanel contentPane;
	private JTable table;

    public Subject(){
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
		
		table = new JTable(c.get_subject_data(), new String[]{"Subject ID", "Subject Name"});
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

    
    public void add() {
        getContentPane().setForeground(Color.BLUE);
        getContentPane().setBackground(Color.WHITE);
        setTitle("ADD Subject");

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
        
        JButton Next = new JButton("SAVE");
        Next.setBounds(200, 60, 150, 30);
        Next.setBackground(Color.BLACK);
        Next.setForeground(Color.WHITE);
        add(Next);

        setVisible(true);

        JLabel AddPassengers = new JLabel("ADD Subject");
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
                    if(!nameField.getText().equals("")){

                        conn c = new conn();
                        c.add_subject(nameField.getText());
                        
                        JOptionPane.showMessageDialog(null, "Subject Added");
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
