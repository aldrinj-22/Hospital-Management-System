package hospitalmanagementsystem;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
import javax.swing.*;
import java.util.ArrayList;
import javax.swing.DefaultListModel;
/**
 *
 * @author admin
 */
public class PatientManagement extends JFrame {
    
   ArrayList<Patient> patients = new ArrayList<>();
 
 
    JLabel lblId;
    JLabel lblName;
    JLabel lblAge;
    JLabel lblHistory;

    JTextField txtId;
    JTextField txtName;
    JTextField txtAge;
    JTextField txtHistory;

    JButton btnAdd;
    JButton btnUpdate;
    JButton btnDelete;
    JButton btnSearch;
    JButton btnBack;

    DefaultListModel<String> patientListModel = new DefaultListModel<>();
    JList<String> patientList = new JList<>(patientListModel);

    public PatientManagement() {

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

      JScrollPane scrollPane = new JScrollPane(patientList);
      scrollPane.setBounds(50, 310, 430, 150);
      add(scrollPane);

        btnBack = new JButton("Back");
        btnBack.setBounds(200, 490, 150, 35);
        add(btnBack);

        
        btnAdd.addActionListener(e -> {

         if (txtId.getText().trim().isEmpty() ||
         txtName.getText().trim().isEmpty() ||
         txtAge.getText().trim().isEmpty() ||
         txtHistory.getText().trim().isEmpty()) {

         JOptionPane.showMessageDialog(this,
            "Please fill in all information!",
            "Input Error",
            JOptionPane.ERROR_MESSAGE);

         return;
       }

      int id = Integer.parseInt(txtId.getText());
      String name = txtName.getText();
      int age = Integer.parseInt(txtAge.getText());
      String history = txtHistory.getText();

      Patient patient = new Patient(id, name, age, history);
      patients.add(patient);

     patientListModel.addElement(
      "ID: " + id +
      " | Name: " + name +
      " | Age: " + age +
      " | History: " + history
     );

      txtId.setText("");
      txtName.setText("");
      txtAge.setText("");
      txtHistory.setText("");
      });
    
    
    btnUpdate.addActionListener(e -> {

    int index = patientList.getSelectedIndex();

    if (index == -1) {
        JOptionPane.showMessageDialog(this,
            "Please select a patient from the list!",
            "Update Error",
            JOptionPane.ERROR_MESSAGE);
        return;
    }

    Patient oldPatient = patients.get(index);

    txtId.setText(String.valueOf(oldPatient.id));
    txtName.setText(oldPatient.name);
    txtAge.setText(String.valueOf(oldPatient.age));
    txtHistory.setText(oldPatient.medicalHistory);

    String newId = txtId.getText();
    String newName = txtName.getText();
    String newAge = txtAge.getText();
    String newHistory = txtHistory.getText();

    String idInput = JOptionPane.showInputDialog(this,
        "Enter updated Patient ID:", newId);

    if (idInput == null || idInput.trim().isEmpty()) {
        return;
    }

    String nameInput = JOptionPane.showInputDialog(this,
        "Enter updated Name:", newName);

    if (nameInput == null || nameInput.trim().isEmpty()) {
        return;
    }

    String ageInput = JOptionPane.showInputDialog(this,
        "Enter updated Age:", newAge);

    if (ageInput == null || ageInput.trim().isEmpty()) {
        return;
    }

    String historyInput = JOptionPane.showInputDialog(this,
        "Enter updated Medical History:", newHistory);

    if (historyInput == null || historyInput.trim().isEmpty()) {
        return;
    }

    try {
        int id = Integer.parseInt(idInput);
        int age = Integer.parseInt(ageInput);

        Patient updatedPatient = new Patient(
            id, nameInput, age, historyInput
        );

        patients.set(index, updatedPatient);

        patientListModel.set(index,
            "ID: " + id +
            " | Name: " + nameInput +
            " | Age: " + age +
            " | History: " + historyInput
        );

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
        });
    
    
    btnDelete.addActionListener(e -> {

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
        patientListModel.remove(index);

        JOptionPane.showMessageDialog(this,
            "Patient deleted successfully!");
    }
    
    
    
        });
    
    
    btnSearch.addActionListener(event -> {

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

            JOptionPane.showMessageDialog(this,
                "PATIENT FOUND!\n\n" +
                "Patient ID: " + patient.id + "\n" +
                "Name: " + patient.name + "\n" +
                "Age: " + patient.age + "\n" +
                "Medical History: " + patient.medicalHistory,
                "Patient Details",
                JOptionPane.INFORMATION_MESSAGE);

            patientList.setSelectedIndex(i);
            patientList.ensureIndexIsVisible(i);

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
        });

        btnBack.addActionListener(e -> {
            dispose();
        });
        
       
    }
}

