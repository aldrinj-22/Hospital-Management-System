
package hospitalmanagementsystem;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.*;

public class PatientManagement extends JFrame implements ActionListener {

    private ArrayList<Patient> patients;
    private DefaultListModel<String> patientListModel;
    private JList<String> patientList;
    private JScrollPane scrollPane;

    private JLabel lblId, lblName, lblAge, lblHistory;

    private JTextField txtId, txtName, txtAge, txtHistory;

    private JButton btnAdd, btnUpdate, btnDelete, btnSearch, btnBack;

    public PatientManagement() {

        patients = new ArrayList<>();
        patientListModel = new DefaultListModel<>();
        patientList = new JList<>(patientListModel);

        setTitle("Patient Management");
        setSize(600, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(null);
        setLocationRelativeTo(null);

        JLabel title = new JLabel("PATIENT MANAGEMENT");
        title.setBounds(200, 20, 250, 30);
        add(title);

        lblId = new JLabel("Patient ID:");
        lblId.setBounds(50, 80, 100, 25);
        add(lblId);

        txtId = new JTextField();
        txtId.setBounds(160, 80, 300, 25);
        add(txtId);

        lblName = new JLabel("Name:");
        lblName.setBounds(50, 120, 100, 25);
        add(lblName);

        txtName = new JTextField();
        txtName.setBounds(160, 120, 300, 25);
        add(txtName);

        lblAge = new JLabel("Age:");
        lblAge.setBounds(50, 160, 100, 25);
        add(lblAge);

        txtAge = new JTextField();
        txtAge.setBounds(160, 160, 300, 25);
        add(txtAge);

        lblHistory = new JLabel("Medical History:");
        lblHistory.setBounds(50, 200, 100, 25);
        add(lblHistory);

        txtHistory = new JTextField();
        txtHistory.setBounds(160, 200, 300, 25);
        add(txtHistory);

        btnAdd = new JButton("Add");
        btnAdd.setBounds(50, 250, 100, 35);
        add(btnAdd);

        btnUpdate = new JButton("Update");
        btnUpdate.setBounds(160, 250, 100, 35);
        add(btnUpdate);

        btnDelete = new JButton("Delete");
        btnDelete.setBounds(270, 250, 100, 35);
        add(btnDelete);

        btnSearch = new JButton("Search");
        btnSearch.setBounds(380, 250, 100, 35);
        add(btnSearch);

        scrollPane = new JScrollPane(patientList);
        scrollPane.setBounds(50, 310, 430, 150);
        add(scrollPane);

        btnBack = new JButton("Back");
        btnBack.setBounds(200, 490, 150, 35);
        add(btnBack);

        btnAdd.addActionListener(this);
        btnUpdate.addActionListener(this);
        btnDelete.addActionListener(this);
        btnSearch.addActionListener(this);
        btnBack.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == btnAdd) {

            if (txtId.getText().trim().isEmpty()
                    || txtName.getText().trim().isEmpty()
                    || txtAge.getText().trim().isEmpty()
                    || txtHistory.getText().trim().isEmpty()) {

                JOptionPane.showMessageDialog(this,
                        "Please fill in all information!",
                        "Input Error",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }

            try {
                int id = Integer.parseInt(txtId.getText().trim());
                String name = txtName.getText().trim();
                int age = Integer.parseInt(txtAge.getText().trim());
                String history = txtHistory.getText().trim();

                if (id <= 0 || age <= 0) {
                    JOptionPane.showMessageDialog(this,
                            "Patient ID and Age must be greater than zero!",
                            "Input Error",
                            JOptionPane.ERROR_MESSAGE);
                    return;
                }

                for (Patient patient : patients) {
                    if (patient.id == id) {
                        JOptionPane.showMessageDialog(this,
                                "Patient ID already exists!",
                                "Input Error",
                                JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                }

                Patient patient = new Patient(id, name, age, history);
                patients.add(patient);

                patientListModel.addElement(
                        "ID: " + id
                        + " | Name: " + name
                        + " | Age: " + age
                        + " | History: " + history
                );

                JOptionPane.showMessageDialog(this,
                        "Patient added successfully!");

                txtId.setText("");
                txtName.setText("");
                txtAge.setText("");
                txtHistory.setText("");

            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this,
                        "Patient ID and Age must be numbers!",
                        "Input Error",
                        JOptionPane.ERROR_MESSAGE);
            }

        } else if (e.getSource() == btnUpdate) {

            int index = patientList.getSelectedIndex();

            if (index == -1) {
                JOptionPane.showMessageDialog(this,
                        "Please select a patient from the list!",
                        "Update Error",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }

            Patient oldPatient = patients.get(index);

            String idInput = JOptionPane.showInputDialog(this,
                    "Enter updated Patient ID:", oldPatient.id);

            if (idInput == null || idInput.trim().isEmpty()) {
                return;
            }

            String nameInput = JOptionPane.showInputDialog(this,
                    "Enter updated Name:", oldPatient.name);

            if (nameInput == null || nameInput.trim().isEmpty()) {
                return;
            }

            String ageInput = JOptionPane.showInputDialog(this,
                    "Enter updated Age:", oldPatient.age);

            if (ageInput == null || ageInput.trim().isEmpty()) {
                return;
            }

            String historyInput = JOptionPane.showInputDialog(this,
                    "Enter updated Medical History:",
                    oldPatient.medicalHistory);

            if (historyInput == null || historyInput.trim().isEmpty()) {
                return;
            }

            try {
                int id = Integer.parseInt(idInput.trim());
                int age = Integer.parseInt(ageInput.trim());
                String name = nameInput.trim();
                String history = historyInput.trim();

                if (id <= 0 || age <= 0) {
                    JOptionPane.showMessageDialog(this,
                            "Patient ID and Age must be greater than zero!",
                            "Input Error",
                            JOptionPane.ERROR_MESSAGE);
                    return;
                }

                for (int i = 0; i < patients.size(); i++) {
                    if (i != index && patients.get(i).id == id) {
                        JOptionPane.showMessageDialog(this,
                                "Patient ID already exists!",
                                "Input Error",
                                JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                }

                Patient updatedPatient =
                        new Patient(id, name, age, history);

                patients.set(index, updatedPatient);

                patientListModel.set(index,
                        "ID: " + id
                        + " | Name: " + name
                        + " | Age: " + age
                        + " | History: " + history);

                JOptionPane.showMessageDialog(this,
                        "Patient updated successfully!");

                txtId.setText("");
                txtName.setText("");
                txtAge.setText("");
                txtHistory.setText("");

            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this,
                        "Patient ID and Age must be numbers!",
                        "Input Error",
                        JOptionPane.ERROR_MESSAGE);
            }

        } else if (e.getSource() == btnDelete) {

            int index = patientList.getSelectedIndex();

            if (index == -1) {
                JOptionPane.showMessageDialog(this,
                        "Please select a patient to delete!",
                        "Delete Error",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }

            int confirm = JOptionPane.showConfirmDialog(this,
                    "Are you sure you want to delete this patient?",
                    "Confirm Delete",
                    JOptionPane.YES_NO_OPTION);

            if (confirm == JOptionPane.YES_OPTION) {

                patients.remove(index);
                patientListModel.removeElementAt(index);

                txtId.setText("");
                txtName.setText("");
                txtAge.setText("");
                txtHistory.setText("");

                JOptionPane.showMessageDialog(this,
                        "Patient deleted successfully!");
            }

        } else if (e.getSource() == btnSearch) {

            String search = JOptionPane.showInputDialog(this,
                    "Enter Patient ID or Name:");

            if (search == null || search.trim().isEmpty()) {
                return;
            }

            search = search.trim();
            boolean found = false;

            for (int i = 0; i < patients.size(); i++) {

                Patient patient = patients.get(i);

                if (String.valueOf(patient.id).equalsIgnoreCase(search)
                        || patient.name.equalsIgnoreCase(search)) {

                    patientList.setSelectedIndex(i);
                    patientList.ensureIndexIsVisible(i);

                    // Display the found patient's details in the fields
                    txtId.setText(String.valueOf(patient.id));
                    txtName.setText(patient.name);
                    txtAge.setText(String.valueOf(patient.age));
                    txtHistory.setText(patient.medicalHistory);

                    JOptionPane.showMessageDialog(this,
                            "Patient found successfully!",
                            "Search Result",
                            JOptionPane.INFORMATION_MESSAGE);

                    found = true;
                    break;
                }
            }

            if (!found) {
                JOptionPane.showMessageDialog(this,
                        "No patient found!",
                        "Search Result",
                        JOptionPane.INFORMATION_MESSAGE);
            }

        } else if (e.getSource() == btnBack) {

            dispose();
        }
    }
}
