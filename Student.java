
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

public class Student extends JFrame {

    JTextField nameField, idField;
    JPasswordField passwordField;

    private JPanel contentPane;
    private JTable table;

    // private int selectedID;

    public Student() {
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
        box.setBounds(0, 34, 1000, 450);

        table = new JTable(c.get_student_data(), new String[] { "Student ID", "Student Name" });
        table.setBounds(0, 34, 1000, 450);

        // Disable on click edit
        table.setDefaultEditor(Object.class, null);

        table.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                int row = table.rowAtPoint(evt.getPoint());
                int col = table.columnAtPoint(evt.getPoint());
                if (row >= 0 && col >= 0) {

                    try {
                        JPopupMenu menu = new JPopupMenu("Edit");
                        JMenuItem item = new JMenuItem("DELETE");
                        item.addActionListener(new ActionListener() {
                            public void actionPerformed(ActionEvent e) {
                                try {

                                    int result = JOptionPane.showConfirmDialog(null, "Would you like to the Student?",
                                            "Warning", JOptionPane.YES_NO_OPTION);

                                    if (result == JOptionPane.YES_OPTION) {
                                        // id is at col 0
                                        c.delete_student(Integer.parseInt((String) table.getValueAt(row, 0)));
                                        // ((DefaultTableModel) table.getModel()).removeRow(row);
                                        // table.repaint();
                                        setVisible(false);
                                    }

                                } catch (Exception eee) {
                                    eee.printStackTrace();
                                }
                            }
                        });

                        JMenuItem item2 = new JMenuItem("Update");
                        item2.addActionListener(new ActionListener() {
                            public void actionPerformed(ActionEvent e) {
                                try {
                                    String newName = JOptionPane.showInputDialog("Enter new Student Name");
                                    if (newName != null) {
                                        // id is at col 0
                                        c.update_student(Integer.parseInt((String) table.getValueAt(row, 0)), newName);
                                        // ((DefaultTableModel) table.getModel()).removeRow(row);
                                        // table.repaint();
                                        setVisible(false);
                                    }

                                } catch (Exception eee) {
                                    eee.printStackTrace();
                                }
                            }
                        });

                        menu.add(item);
                        menu.add(item2);

                        menu.show(table, evt.getX(), evt.getY());

                    } catch (Exception e) {
                        JOptionPane.showMessageDialog(null, "Failed ");
                    }

                }
            }
        });

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

        // ArrayList<String>temp;

        // if(selectedID != -1){

        // try{
        // conn c = new conn();
        // temp = c.get_student_row(selectedID);

        // }catch(Exception e){
        // e.printStackTrace();
        // return;
        // }
        // }

        getContentPane().setForeground(Color.BLUE);
        getContentPane().setBackground(Color.WHITE);
        setTitle("ADD Student");

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

        JLabel AddPassengers = new JLabel("ADD Student");
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
                    if (!nameField.getText().equals("")) {

                        conn c = new conn();
                        // if(selectedID != -1){
                        // c.update_student(selectedID);
                        // selectedID = -1;
                        // updateVal = false;
                        // repaint();
                        // }
                        c.add_student(nameField.getText());

                        JOptionPane.showMessageDialog(null, "Student Added");
                        setVisible(false);
                    } else {
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
