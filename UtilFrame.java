
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class UtilFrame  extends JFrame{
    
    int inputFieldIndex = 0;
    
    protected void addInputField(String label, JComponent component){
        JLabel fieldLabel = new JLabel(label);
        fieldLabel.setFont(new Font("Tahoma", Font.PLAIN, 17));
        fieldLabel.setBounds(60, 30 + (30*inputFieldIndex), 150, 27);
        add(fieldLabel);

        component.setBounds(200, 30 + (30*inputFieldIndex), 150, 27);
        add(component);
        inputFieldIndex++;
    } 

    protected void enableBackBtn(ActionListener ae){
        
        JButton Back = new JButton("BACK");
        Back.setBounds(50, 30 + (30*inputFieldIndex), 150, 30);
        Back.setBackground(Color.BLACK);
        Back.setForeground(Color.WHITE);
        add(Back);

        Back.addActionListener(ae);
    
    }
    
    
    protected void enableSavebtn(ActionListener ae){
        JButton Save = new JButton("SAVE");
        Save.setBounds(200, 30 + (30*inputFieldIndex), 150, 30);
        Save.setBackground(Color.BLACK);
        Save.setForeground(Color.WHITE);
        add(Save);
    
        Save.addActionListener(ae);
        
    }
}
