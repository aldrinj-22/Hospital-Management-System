package hospitalmanagementsystem;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
 import javax.swing.*;
 import java.awt.event.ActionEvent;
 import java.awt.event.ActionListener;
 
/**
 *
 * @author admin
 */
public class HospitalManagementSystem extends JFrame {
    
     private JButton btnPatients, btnDoctors, btnAppointments, btnReports, btnExit;
     
     
    public HospitalManagementSystem(){
        
       
        setTitle("Hospital Management System");
        setSize(500, 500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(null);
        
         JLabel title = new JLabel("HOSPITAL MANAGEMENT SYSTEM");
        title.setBounds(130, 40, 300, 30);
        add(title);

        btnPatients = new JButton("Patient Management");
        btnPatients.setBounds(110, 100, 240, 40);
        add(btnPatients);
        
        btnPatients.addActionListener(e -> {
        PatientManagement patientWindow = new PatientManagement();
        patientWindow.setVisible(true);
        });

        btnDoctors = new JButton("Doctor Management");
        btnDoctors.setBounds(110, 160, 240, 40);
        add(btnDoctors);

        btnAppointments = new JButton("Appointment Management");
        btnAppointments.setBounds(110, 220, 240, 40);
        add(btnAppointments);

        btnReports = new JButton("Reports");
        btnReports.setBounds(110, 280, 240, 40);
        add(btnReports);

        btnExit = new JButton("Exit");
        btnExit.setBounds(110, 340, 240, 40);
        add(btnExit);

        btnExit.addActionListener(e -> {
            System.exit(0);
        });
    }
    
}
