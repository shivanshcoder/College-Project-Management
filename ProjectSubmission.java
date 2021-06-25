

import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;


public class ProjectSubmission extends JFrame { // Third Frame

    JComboBox<String>projectField;
    JComboBox<String>studentField;

    File reportFile = null;

    // JComboBox teacherIDField;
    ArrayList<String[]> projectList;
    ArrayList<String[]> studentList;

    
	private JPanel contentPane;
	private JTable table;

	public ProjectSubmission(){
		getContentPane().setForeground(Color.BLUE);
		getContentPane().setBackground(Color.WHITE);
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
		
		// table = new JTable(c.get_student_data(), new String[]{"project_name", "lol"});
       

		table = new JTable(c.get_submission_data(), new String[]{"Project Name", "Student Name", "Report File"});
		table.setBounds(0, 34, 1000, 450);

        //Disable on click edit
        table.setDefaultEditor(Object.class, null);

        table.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                int row = table.rowAtPoint(evt.getPoint());
                int col = table.columnAtPoint(evt.getPoint());
                if (row >= 0 && col >= 0 && Desktop.isDesktopSupported()) {
                    System.out.println(row+ ":" +col);
                    int result = JOptionPane.showConfirmDialog (null, "Would you like to view the Report PDF","Warning",JOptionPane.YES_NO_OPTION);

                    try{

                        if(result == JOptionPane.YES_OPTION){
                            File myFile = new File("submitted_files/" + table.getValueAt(row,col)+".pdf");
                            Desktop.getDesktop().open(myFile);
                            
                        }
                    }
                    catch(Exception e){
                        JOptionPane.showMessageDialog(null, "Failed to open File!");
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
        getContentPane().setForeground(Color.BLUE);
        getContentPane().setBackground(Color.WHITE);
        setTitle("Make Project Submission");

        setSize(778, 486);
        getContentPane().setLayout(null);

        
        conn c = new conn();
        projectList = c.project_list();
        studentList = c.student_list();

        JLabel nameLabel = new JLabel("PROJECT NAME");
        nameLabel.setFont(new Font("Tahoma", Font.PLAIN, 17));
        nameLabel.setBounds(60, 30, 150, 27);
        add(nameLabel);

        projectField = new JComboBox<String>(projectList.get(0));
        projectField.setBounds(200, 30, 150, 27);
        add(projectField);
        
        
        JLabel subjectLabel = new JLabel("STUDENT");
        subjectLabel.setFont(new Font("Tahoma", Font.PLAIN, 17));
        subjectLabel.setBounds(60, 60, 150, 27);
        add(subjectLabel);
        
        studentField = new JComboBox<String>(studentList.get(0));
        studentField.setBounds(200, 60, 150, 27);
        add(studentField);

        JLabel reportLabel = new JLabel("PROJECT REPORT");
        reportLabel.setFont(new Font("Tahoma", Font.PLAIN, 17));
        reportLabel.setBounds(60, 90, 150, 27);
        add(reportLabel);

        JButton reportPath = new JButton("Choose Report");
        reportPath.setBounds(200, 90, 150, 30);
        reportPath.setBackground(Color.BLACK);
        reportPath.setForeground(Color.WHITE);
        add(reportPath);

        reportPath.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent ar){

                JFileChooser chooser = new JFileChooser();
                FileNameExtensionFilter filter = new FileNameExtensionFilter("PDF Files", "pdf");
                chooser.setFileFilter(filter);
                int returnVal = chooser.showOpenDialog(reportPath.getParent());

                if(returnVal == JFileChooser.APPROVE_OPTION) {
                    reportFile = chooser.getSelectedFile();
                    reportPath.setText("File Chosen");
                    reportPath.setToolTipText(reportFile.getName());
                    reportPath.setBackground(Color.GREEN);
                    reportPath.setForeground(Color.BLACK);

                }
            }
        });

        
        JButton Back = new JButton("BACK");
        Back.setBounds(50, 120, 150, 30);
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

        JLabel AddPassengers = new JLabel("Make Project Submission");
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
                    int student_index = studentField.getSelectedIndex();
                    int project_index = projectField.getSelectedIndex();
                    if((reportFile != null) && (student_index != -1) && (project_index != -1)){

                        String student_id_str = studentList.get(1)[student_index];
                        String project_id_str = projectList.get(1)[project_index];
                        String hashVal = utils.passwd_to_hash(student_id_str+project_id_str);

                        File newF = new File("submitted_files/"+hashVal+".pdf");
                        try{

                            Files.copy(reportFile.toPath(), newF.toPath(),StandardCopyOption.REPLACE_EXISTING);

                            conn c = new conn();
                            c.submit_project(Integer.parseInt(project_id_str),Integer.parseInt(student_id_str), hashVal);
                            
                            JOptionPane.showMessageDialog(null, "Project Submitted");
                            setVisible(false);
                        }
                        catch(IOException e){
                            e.printStackTrace();
                        }

                    }
                    else{
                        JOptionPane.showMessageDialog(null, "Fields can't be left empty");
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