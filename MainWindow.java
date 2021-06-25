import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class MainWindow extends JFrame implements ActionListener {

    JLabel l1;
    JButton b1;
    JButton b2;

    public MainWindow() {

        setSize(800, 600); // setContentPane(300,300,1366,390); frame size
        setLayout(null);
        setLocation(300, 300);

        l1 = new JLabel("");
        b1 = new JButton("Login");
        b2 = new JButton("Register");

        b1.setBackground(Color.WHITE);
        b1.setForeground(Color.BLACK);
        b2.setBackground(Color.WHITE);
        b2.setForeground(Color.BLACK);

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("images/front.jpg"));
        Image i3 = i1.getImage().getScaledInstance(500, 390, Image.SCALE_DEFAULT);
        ImageIcon i2 = new ImageIcon(i3);
        l1 = new JLabel(i2);

        JLabel lid = new JLabel("College Project Management");
        lid.setBounds(225, 5, 600, 100);
        lid.setFont(new Font("serif", Font.PLAIN, 30));
        lid.setForeground(Color.black);
        add(lid);

        b1.setBounds(600, 325, 125, 50);
        b2.setBounds(600, 265, 125, 50);

        add(b2);
        add(b1);
        add(l1);

        b1.addActionListener(this);
        b2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                Teacher t = new Teacher();
                t.add();
                t.setVisible(true);
                // setVisible(false);
            }
        });

        setVisible(true);

    }

    public void actionPerformed(ActionEvent ae) {

        new Login().setVisible(true);
        this.setVisible(false);

    }

    public static void main(String[] args) {

        MainWindow window = new MainWindow();
        window.setVisible(true);
    }
}
