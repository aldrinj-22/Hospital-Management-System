package hospitalmanagementsystem;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author aaron
 */
public class Appointment extends JFrame{
    private DefaultListModel<String> listmodel;
    private DefaultComboBoxModel<String> combobox;
    private JList<String> list;
    private JComboBox<String> box;
    private DefaultTableModel tablemodel;
    private JLabel label,label2,label3;
    private JScrollPane scrollpane;
    private JTextField txtfield;
    private JButton add,resched,cancel,addemergency;
    private JTable table;
    
            
    
    Appointment(){
        listmodel = new DefaultListModel<>();
        
        setTitle("Appointment");
        setSize(1000, 700);
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        
        label = new JLabel("HOSPITAL MANAGEMENT SYSTEM - APPOINTMENT DASHBOARD");
        label.setBounds(300, 10, 500, 60);
        add(label);
        
        label2 = new JLabel("Search Appointment ID / Patient ID...");
        label2.setBounds(80, 130, 220, 30);
        add(label2);
        
        label3 = new JLabel("Filter:");
        label3.setBounds(600, 130, 140, 30);
        add(label3);
        
        txtfield = new JTextField();
        txtfield.setBounds(300, 130, 180, 30);
        add(txtfield);
        
        combobox = new DefaultComboBoxModel<>();
        combobox.addElement("All Status");
        combobox.addElement("Pending");
        combobox.addElement("Confirmed");
        combobox.addElement("Cancelled");

        box = new JComboBox(combobox);
        box.setBounds(650, 130, 180, 30);
        add(box);
       
        tablemodel = new DefaultTableModel(new Object[]{"Appointment ID"," patient ID","Doctor ID","Date & Time","Urgency","Status"},0);

        table = new JTable(tablemodel);


         scrollpane = new JScrollPane(table);
         scrollpane.setBounds(30, 180, 900, 300);
         add(scrollpane);
                
        
        add = new JButton("+ Book New Appointment");
        add.setBounds(80, 80, 180, 30);
        add(add);
        
        resched = new JButton("Reschedule");
        resched.setBounds(280, 80, 180, 30);
        add(resched);
        
        cancel = new JButton("Cancel");
        cancel.setBounds(480, 80, 180, 30);
        add(cancel);
        
        addemergency = new JButton("+ Add Emergency Case");
        addemergency.setBounds(680, 80, 180, 30);
        add(addemergency);
                
    }
}
