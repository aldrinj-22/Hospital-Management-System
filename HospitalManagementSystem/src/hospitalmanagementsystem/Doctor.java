/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hospitalmanagementsystem;

/**
 *
 * @author Johnroe
 */
import java.util.LinkedList;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

/**
 *
 * @author admin
 */
public class Doctor extends JFrame{
    
    private LinkedList<String> linklist;
    private DefaultListModel<String> model;
    private JLabel name,id,specialty,patientid,title;
    private JTextField txtname,txtid,txtspecialty,txtpatient;
    private JButton add,update,delete,search;
    private JScrollPane scrollpane;
    private JList list;
    
    Doctor(){
    linklist = new LinkedList<>();
    model = new DefaultListModel<>();
    
    setSize(600, 600);
    setLayout(null);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    
    
    list = new JList<>(model);
    scrollpane = new JScrollPane(list);
    scrollpane.setBounds(50, 310, 500, 100);
    add(scrollpane);
    
        
    title = new JLabel("DOCTORS MANAGEMENT");
    title.setBounds(200,40, 220, 15);
    add(title);
    
    name = new JLabel("Doctors name: ");
    name.setBounds(50, 80, 130, 10);
    add(name);
    
    id = new JLabel("Doctors Id:  ");
    id.setBounds(50,120, 100, 10);
    add(id);
    
    specialty = new JLabel("Doctors specialty:  ");
    specialty.setBounds(50, 160, 130, 20);
    add(specialty);
    
    patientid = new JLabel("Patient Id:  ");
    patientid.setBounds(50, 200, 180, 20);
    add(patientid);
    
    txtname = new JTextField();
    txtname.setBounds(200,80, 180, 20);
    add(txtname);
    
    txtid = new JTextField();
    txtid.setBounds(200,120, 180, 20);
    add(txtid);
    
    txtspecialty = new JTextField();
    txtspecialty.setBounds(200,160, 180, 20);
    add(txtspecialty);
    
    txtpatient = new JTextField();
    txtpatient.setBounds(200,200, 180, 20);
    add(txtpatient);
    
    add = new JButton("Add ");
    add.setBounds(50, 250, 80, 25);
    add(add);
    
    delete = new JButton("Delete ");
    delete.setBounds(150, 250, 80, 25);
    add(delete);
    
    update = new JButton("Update ");
    update.setBounds(250, 250, 80, 25);
    add(update);
    
    search = new JButton("Search ");
    search.setBounds(350, 250, 80, 25);
    add(search);
    

    }
    
}