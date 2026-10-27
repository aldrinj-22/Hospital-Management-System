
package hospitalmanagementsystem;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class HospitalManagementSystem extends JFrame implements ActionListener {

    private JButton btnPatients, btnDoctors, btnAppointments, btnReports, btnExit;

    public HospitalManagementSystem() {

        setTitle("Hospital Management System");
        setSize(500, 500);
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

       
        JLabel title = new JLabel("HOSPITAL MANAGEMENT SYSTEM");
        title.setBounds(100, 40, 300, 30);
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

        btnDoctors.addActionListener(e -> {
        Doctor doctorwindow = new Doctor();
        doctorwindow.setVisible(true);
        });
        
        btnAppointments = new JButton("Appointment Management");
        btnAppointments.setBounds(110, 220, 240, 40);
        add(btnAppointments);

        btnReports = new JButton("Reports");
        btnReports.setBounds(110, 280, 240, 40);
        add(btnReports);

        btnExit = new JButton("Exit");
        btnExit.setBounds(110, 340, 240, 40);
        add(btnExit);

       
        btnPatients.addActionListener(this);
        btnDoctors.addActionListener(this);
        btnAppointments.addActionListener(this);
        btnReports.addActionListener(this);
        btnExit.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == btnPatients) {

            PatientManagement patientWindow = new PatientManagement();
            patientWindow.setVisible(true);

        } else if (e.getSource() == btnDoctors) {

            JOptionPane.showMessageDialog(this,
                    "Doctor Management is not available yet.");

        } else if (e.getSource() == btnAppointments) {

            JOptionPane.showMessageDialog(this,
                    "Appointment Management is not available yet.");

        } else if (e.getSource() == btnReports) {

            JOptionPane.showMessageDialog(this,
                    "Reports are not available yet.");

        } else if (e.getSource() == btnExit) {

            System.exit(0);

        }
    }
}
