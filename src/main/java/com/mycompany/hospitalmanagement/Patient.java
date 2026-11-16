/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.hospitalmanagement;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class Patient extends JFrame implements ActionListener {

    private DefaultTableModel model;
    private JTable table;
    private JScrollPane scrollPane;

    private LinkedList<String[]> linkedList;

    private JLabel title, lblId, lblName, lblAge, lblHistory;
    private JTextField txtId, txtName, txtAge, txtHistory;
    private JButton add, update, delete, search, back;

    public Patient() {

        linkedList = new LinkedList<>();

        setTitle("Patient Management");
        setSize(650, 600);
        setLayout(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        title = new JLabel("PATIENT MANAGEMENT");
        title.setBounds(230, 20, 250, 20);
        add(title);

        lblId = new JLabel("Patient ID:");
        lblId.setBounds(50, 70, 130, 20);
        add(lblId);

        lblName = new JLabel("Name:");
        txtName.setBounds(50, 110, 130, 20);
        add(txtName);

        lblAge = new JLabel("Age:");
        lblAge.setBounds(50, 150, 130, 20);
        add(lblAge);

        lblHistory = new JLabel("Medical History:");
        lblHistory.setBounds(50, 190, 130, 20);
        add(lblHistory);

        txtId = new JTextField();
        txtId.setBounds(200, 70, 250, 25);
        add(txtId);

        txtName = new JTextField();
        txtName.setBounds(200, 110, 250, 25);
        add(txtName);

        txtAge = new JTextField();
        txtAge.setBounds(200, 150, 250, 25);
        add(txtAge);

        txtHistory = new JTextField();
        txtHistory.setBounds(200, 190, 250, 25);
        add(txtHistory);

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

        String[] columnNames = {
            "Patient ID",
            "Name",
            "Age",
            "Medical History"
        };

        model = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        
        table = new JTable(model);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        scrollPane = new JScrollPane(table);
        scrollPane.setBounds(50, 290, 530, 200);
        add(scrollPane);

        back = new JButton("Back");
        back.setBounds(250, 505, 150, 30);
        add(back);

        add.addActionListener(this);
        update.addActionListener(this);
        delete.addActionListener(this);
        search.addActionListener(this);
        back.addActionListener(this);

        table.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                int row = table.getSelectedRow();
                if (row != -1) {
                    txtId.setText(model.getValueAt(row, 0).toString());
                    txtName.setText(model.getValueAt(row, 1).toString());
                    txtAge.setText(model.getValueAt(row, 2).toString());
                    txtHistory.setText(model.getValueAt(row, 3).toString());
                }
            }
        });
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == add) {

            String patientID = txtId.getText().trim();
            String patientName = txtName.getText().trim();
            String patientAge = txtAge.getText().trim();
            String patientHistory = txtHistory.getText().trim();

            if (patientID.isEmpty() || patientName.isEmpty() 
                    || patientAge.isEmpty() || patientHistory.isEmpty()) {

                JOptionPane.showMessageDialog(
                        this,
                        "Please fill in all information.",
                        "Input Error",
                        JOptionPane.ERROR_MESSAGE
                );

            } else {
                try {
                    int id = Integer.parseInt(patientID);
                    int age = Integer.parseInt(patientAge);

                    if (id <= 0 || age <= 0) {
                        JOptionPane.showMessageDialog(
                                this,
                                "Patient ID and Age must be greater than zero!",
                                "Input Error",
                                JOptionPane.ERROR_MESSAGE
                        );
                        return;
                    }

                    // Check duplicate Patient ID
                    boolean duplicate = false;
                    for (String[] patient : linkedList) {
                        if (patient[0].equals(patientID)) {
                            duplicate = true;
                            break;
                        }
                    }

                    if (duplicate) {
                        JOptionPane.showMessageDialog(
                                this,
                                "Patient ID already exists.",
                                "Input Error",
                                JOptionPane.ERROR_MESSAGE
                        );
                    } else {
                        String[] patient = {
                            patientID,
                            patientName,
                            patientAge,
                            patientHistory
                        };

                        linkedList.add(patient);
                        model.addRow(patient);

                        JOptionPane.showMessageDialog(
                                this,
                                "Patient added successfully!"
                        );
                        clearFields();
                    }

                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(
                            this,
                            "Patient ID and Age must be numbers!",
                            "Input Error",
                            JOptionPane.ERROR_MESSAGE
                    );
                }
            }

        } else if (e.getSource() == delete) {

            int row = table.getSelectedRow();

            if (row != -1) {
                int confirm = JOptionPane.showConfirmDialog(
                        this,
                        "Are you sure you want to delete this patient?",
                        "Confirm Delete",
                        JOptionPane.YES_NO_OPTION
                );

                if (confirm == JOptionPane.YES_OPTION) {
                    linkedList.remove(row);
                    model.removeRow(row);
                    clearFields();
                    JOptionPane.showMessageDialog(
                            this,
                            "Patient deleted successfully!"
                    );
                }
            } else {
                JOptionPane.showMessageDialog(
                        this,
                        "Please select a patient to delete.",
                        "Input Error",
                        JOptionPane.ERROR_MESSAGE
                );
            }

        } else if (e.getSource() == update) {

            int row = table.getSelectedRow();

            if (row != -1) {

                String patientID = txtId.getText().trim();
                String patientName = txtName.getText().trim();
                String patientAge = txtAge.getText().trim();
                String patientHistory = txtHistory.getText().trim();

                if (patientID.isEmpty() || patientName.isEmpty() 
                        || patientAge.isEmpty() || patientHistory.isEmpty()) {

                    JOptionPane.showMessageDialog(
                            this,
                            "Please fill in all information.",
                            "Input Error",
                            JOptionPane.ERROR_MESSAGE
                    );

                } else {
                    try {
                        int id = Integer.parseInt(patientID);
                        int age = Integer.parseInt(patientAge);

                        if (id <= 0 || age <= 0) {
                            JOptionPane.showMessageDialog(
                                    this,
                                    "Patient ID and Age must be greater than zero!",
                                    "Input Error",
                                    JOptionPane.ERROR_MESSAGE
                            );
                            return;
                        }

                        // Check duplicate ID excluding current row
                        for (int i = 0; i < linkedList.size(); i++) {
                            if (i != row && linkedList.get(i)[0].equals(patientID)) {
                                JOptionPane.showMessageDialog(
                                        this,
                                        "Patient ID already exists!",
                                        "Input Error",
                                        JOptionPane.ERROR_MESSAGE
                                );
                                return;
                            }
                        }

                        String[] patient = {
                            patientID,
                            patientName,
                            patientAge,
                            patientHistory
                        };

                        linkedList.set(row, patient);
                        for (int i = 0; i < patient.length; i++) {
                            model.setValueAt(patient[i], row, i);
                        }

                        clearFields();
                        JOptionPane.showMessageDialog(
                                this,
                                "Patient updated successfully!"
                        );

                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(
                                this,
                                "Patient ID and Age must be numbers!",
                                "Input Error",
                                JOptionPane.ERROR_MESSAGE
                        );
                    }
                }

            } else {
                JOptionPane.showMessageDialog(
                        this,
                        "Please select a patient to update.",
                        "Input Error",
                        JOptionPane.ERROR_MESSAGE
                );
            }

        } else if (e.getSource() == search) {

            String searchID = txtId.getText().trim();

            if (searchID.isEmpty()) {
                JOptionPane.showMessageDialog(
                        this,
                        "Please enter the Patient ID to search.",
                        "Input Error",
                        JOptionPane.ERROR_MESSAGE
                );
            } else {

                boolean found = false;

                for (int i = 0; i < linkedList.size(); i++) {
                    if (linkedList.get(i)[0].equalsIgnoreCase(searchID)) {
                        table.setRowSelectionInterval(i, i);
                        table.scrollRectToVisible(
                                table.getCellRect(i, 0, true)
                        );
                        
                        txtId.setText(linkedList.get(i)[0]);
                        txtName.setText(linkedList.get(i)[1]);
                        txtAge.setText(linkedList.get(i)[2]);
                        txtHistory.setText(linkedList.get(i)[3]);

                        found = true;
                        break;
                    }
                }

                if (!found) {
                    JOptionPane.showMessageDialog(
                            this,
                            "Patient not found.",
                            "Search Result",
                            JOptionPane.INFORMATION_MESSAGE
                    );
                }
            }

        } else if (e.getSource() == back) {
            dispose();
        }
    }

    private void clearFields() {
        txtId.setText("");
        txtName.setText("");
        txtAge.setText("");
        txtHistory.setText("");
        table.clearSelection();
    }
}