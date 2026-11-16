package hospitalmanagementsystem;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class Doctor extends JFrame implements ActionListener {

    private DefaultTableModel model;
    private JTable table;
    private JScrollPane scrollPane;

    private LinkedList<String[]> linkedList;

    private JLabel name, id, specialty, patientid, title;

    private JTextField txtname, txtid, txtspecialty, txtpatient;

    private JButton add, update, delete, search, back;

    public Doctor() {

        linkedList = new LinkedList<>();

        setTitle("Doctors Management System");
        setSize(650, 600);
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        title = new JLabel("DOCTORS MANAGEMENT");
        title.setBounds(220, 20, 250, 20);
        add(title);

        name = new JLabel("Doctor's Name:");
        name.setBounds(50, 70, 130, 20);
        add(name);

        id = new JLabel("Doctor's ID:");
        id.setBounds(50, 110, 130, 20);
        add(id);

        specialty = new JLabel("Doctor's Specialty:");
        specialty.setBounds(50, 150, 130, 20);
        add(specialty);

        patientid = new JLabel("Patient ID:");
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

        String[] columnNames = {
            "Doctor Name",
            "Doctor ID",
            "Specialty",
            "Patient ID"
        };

        model = new DefaultTableModel(columnNames, 0);
        table = new JTable(model);

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
                    txtname.setText(model.getValueAt(row, 0).toString());
                    txtid.setText(model.getValueAt(row, 1).toString());
                    txtspecialty.setText(model.getValueAt(row, 2).toString());
                    txtpatient.setText(model.getValueAt(row, 3).toString());
                }
            }
        });
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == add) {

            String doctorName = txtname.getText().trim();
            String doctorID = txtid.getText().trim();
            String doctorSpecialty = txtspecialty.getText().trim();
            String patientID = txtpatient.getText().trim();

            if (doctorName.isEmpty() || doctorID.isEmpty()
                    || doctorSpecialty.isEmpty() || patientID.isEmpty()) {

                JOptionPane.showMessageDialog(
                        this,
                        "Please fill in all information.",
                        "Input Error",
                        JOptionPane.ERROR_MESSAGE
                );

            } else {

                // Check duplicate Doctor ID
                boolean duplicate = false;

                for (String[] doctor : linkedList) {
                    if (doctor[1].equals(doctorID)) {
                        duplicate = true;
                        break;
                    }
                }

                if (duplicate) {

                    JOptionPane.showMessageDialog(
                            this,
                            "Doctor ID already exists.",
                            "Input Error",
                            JOptionPane.ERROR_MESSAGE
                    );

                } else {

                    String[] doctor = {
                        doctorName,
                        doctorID,
                        doctorSpecialty,
                        patientID
                    };

                    linkedList.add(doctor);
                    model.addRow(doctor);

                    clearFields();
                }
            }

        } else if (e.getSource() == delete) {

            int row = table.getSelectedRow();

            if (row != -1) {
                linkedList.remove(row);
                model.removeRow(row);
                clearFields();
            } else {
                JOptionPane.showMessageDialog(
                        this,
                        "Please select a doctor to delete.",
                        "Input Error",
                        JOptionPane.ERROR_MESSAGE
                );
            }

        } else if (e.getSource() == update) {

            int row = table.getSelectedRow();

            if (row != -1) {

                String doctorName = txtname.getText().trim();
                String doctorID = txtid.getText().trim();
                String doctorSpecialty = txtspecialty.getText().trim();
                String patientID = txtpatient.getText().trim();

                if (doctorName.isEmpty() || doctorID.isEmpty()
                        || doctorSpecialty.isEmpty() || patientID.isEmpty()) {

                    JOptionPane.showMessageDialog(
                            this,
                            "Please fill in all information.",
                            "Input Error",
                            JOptionPane.ERROR_MESSAGE
                    );

                } else {

                    String[] doctor = {
                        doctorName,
                        doctorID,
                        doctorSpecialty,
                        patientID
                    };

                    linkedList.set(row, doctor);

                    for (int i = 0; i < doctor.length; i++) {
                        model.setValueAt(doctor[i], row, i);
                    }

                    clearFields();
                }

            } else {
                JOptionPane.showMessageDialog(
                        this,
                        "Please select a doctor to update.",
                        "Input Error",
                        JOptionPane.ERROR_MESSAGE
                );
            }

        } else if (e.getSource() == search) {

            String doctorID = txtid.getText().trim();

            if (doctorID.isEmpty()) {
                JOptionPane.showMessageDialog(
                        this,
                        "Please enter the Doctor's ID.",
                        "Input Error",
                        JOptionPane.ERROR_MESSAGE
                );
            } else {

                boolean found = false;

                for (int i = 0; i < linkedList.size(); i++) {
                    if (linkedList.get(i)[1].equals(doctorID)) {
                        table.setRowSelectionInterval(i, i);
                        table.scrollRectToVisible(
                                table.getCellRect(i, 0, true)
                        );
                        found = true;
                        break;
                    }
                }

                if (!found) {
                    JOptionPane.showMessageDialog(
                            this,
                            "Doctor not found.",
                            "Search Result",
                            JOptionPane.INFORMATION_MESSAGE
                    );
                }
            }

        } else if (e.getSource() == back) {
            dispose(); // Closes the current Doctor window
            
            // Uncomment the line below if you want to return to your Main Menu window:
            // new MainMenu().setVisible(true);
        }
    }

    private void clearFields() {
        txtname.setText("");
        txtid.setText("");
        txtspecialty.setText("");
        txtpatient.setText("");
        table.clearSelection();
    }
}