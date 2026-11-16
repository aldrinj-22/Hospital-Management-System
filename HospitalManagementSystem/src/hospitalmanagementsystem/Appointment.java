package hospitalmanagementsystem;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author aaron
 */
public class Appointment extends JFrame implements ActionListener {
    
   
    private LinkedList<String[]> appointmentList; // Backend storage for table rows
    private DefaultComboBoxModel<String> comboboxModel;
    private DefaultTableModel tablemodel;
    
    
    private JLabel label, label2, label3;
    private JScrollPane scrollpane;
    private JTextField txtfield;
    private JComboBox<String> box;
    private JButton add, resched, cancel, addemergency;
    private JTable table;
    
    Appointment(){
        
        appointmentList = new LinkedList<>();
        
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
        
        comboboxModel = new DefaultComboBoxModel<>();
        comboboxModel.addElement("All Status");
        comboboxModel.addElement("Pending");
        comboboxModel.addElement("Confirmed");
        comboboxModel.addElement("Cancelled");

        box = new JComboBox<>(comboboxModel);
        box.setBounds(650, 130, 180, 30);
        add(box);
       
        tablemodel = new DefaultTableModel(new Object[]{"Appointment ID", "Patient ID", "Doctor ID", "Date & Time", "Urgency", "Status"}, 0);
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
        
      
        add.addActionListener(this);
        resched.addActionListener(this);
        cancel.addActionListener(this);
        addemergency.addActionListener(this);         
    }

   @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == add){
           
            String patientID = JOptionPane.showInputDialog(this, "Enter Patient ID:", "Book New Appointment", JOptionPane.QUESTION_MESSAGE);
            
            if(patientID != null && !patientID.trim().isEmpty()){
                String doctorID = JOptionPane.showInputDialog(this, "Enter Doctor ID:", "Book New Appointment", JOptionPane.QUESTION_MESSAGE);
                String dateTime = JOptionPane.showInputDialog(this, "Enter Date & Time (YYYY-MM-DD HH:MM):", "Book New Appointment", JOptionPane.QUESTION_MESSAGE);
                
                if(doctorID != null && dateTime != null){
                   
                    String[] newAppointment = {"APT-" + (tablemodel.getRowCount() + 1), patientID, doctorID, dateTime, "Normal", "Pending"};
                    appointmentList.add(newAppointment);
                    tablemodel.addRow(newAppointment);
                    
                    JOptionPane.showMessageDialog(this, "Appointment successfully booked!", "Success", JOptionPane.INFORMATION_MESSAGE);
                }
            } else if(patientID != null) {
                JOptionPane.showMessageDialog(this, "Booking cancelled or Patient ID was left empty.", "Input Error", JOptionPane.ERROR_MESSAGE);
            }
            
        } else if(e.getSource() == resched){
            int selectedRow = table.getSelectedRow();
            if(selectedRow != -1){
                JOptionPane.showMessageDialog(this, "Rescheduling selected appointment...", "Success", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "Please select an appointment from the table first", "Selection Error", JOptionPane.ERROR_MESSAGE);
            }
            
        } else if(e.getSource() == cancel){
            int selectedRow = table.getSelectedRow();
            if(selectedRow != -1){
                appointmentList.remove(selectedRow);
                tablemodel.removeRow(selectedRow);
                JOptionPane.showMessageDialog(this, "Appointment cancelled successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "Please select an appointment from the table first", "Selection Error", JOptionPane.ERROR_MESSAGE);
            }
            
        } else if(e.getSource() == addemergency){
            String[] emergencyData = {"APT-EMG-" + (tablemodel.getRowCount() + 1), "PAT-EMG", "DOC-ER", "Immediate", "Critical", "Confirmed"};
            appointmentList.add(emergencyData);
            tablemodel.addRow(emergencyData);
            JOptionPane.showMessageDialog(this, "Emergency case added.", "Emergency", JOptionPane.WARNING_MESSAGE);
        }
    }
}