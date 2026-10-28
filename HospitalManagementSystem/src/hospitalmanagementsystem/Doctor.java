/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hospitalmanagementsystem;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Johnroe / admin
 */
public class Doctor extends JFrame {
    
    private DefaultTableModel model;
    private JTable table;
    private JLabel name, id, specialty, patientid, title;
    private JTextField txtname, txtid, txtspecialty, txtpatient;
    private JButton add, update, delete, search;
    private JScrollPane scrollpane;
    
    public Doctor() {
        setTitle("Doctors Management System");
        setSize(650, 600);
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
    
        title = new JLabel("DOCTORS MANAGEMENT");
        title.setBounds(220, 20, 200, 20);
        add(title);
        
        
        name = new JLabel("Doctor's Name: ");
        name.setBounds(50, 70, 130, 20);
        add(name);
        
        id = new JLabel("Doctor's ID: ");
        id.setBounds(50, 110, 130, 20);
        add(id);
        
        specialty = new JLabel("Doctor's Specialty: ");
        specialty.setBounds(50, 150, 130, 20);
        add(specialty);
        
        patientid = new JLabel("Patient ID: ");
        patientid.setBounds(50, 190, 130, 20);
        add(patientid);
        
       
        txtname = new JTextField();
        txtname.setBounds(200, 70, 250, 25);
        add(txtname);
        
        txtid = new JTextField();
        txtid.setBounds(200, 110, 250, 25);
        add(txtid);
        
        txtspecialty = new JTextField();
        txtspecialty.setBounds(200, 150, 250, 25);
        add(txtspecialty);
        
        txtpatient = new JTextField();
        txtpatient.setBounds(200, 190, 250, 25);
        add(txtpatient);
        
    
        add = new JButton("Add");
        add.setBounds(50, 240, 90, 30);
        add(add);
        
        delete = new JButton("Delete");
        delete.setBounds(155, 240, 90, 30);
        add(delete);
        
        update = new JButton("Update");
        update.setBounds(260, 240, 90, 30);
        add(update);
        
        search = new JButton("Search");
        search.setBounds(365, 240, 90, 30);
        add(search);

        
        String[] columnNames = {"Doctor Name", "Doctor ID", "Specialty", "Patient ID"};
        model = new DefaultTableModel(columnNames, 0);
        table = new JTable(model);
        
        scrollpane = new JScrollPane(table);
        scrollpane.setBounds(50, 300, 530, 220);
        add(scrollpane);
    }
}