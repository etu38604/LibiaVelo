package ViewPackage;

import DataAccessPackage.EmployeeDataAccess;
import DataAccessPackage.LocalityDataAccess;
import ExceptionPackage.ConnectionException;
import ModelPackage.EmployeeModel;
import ModelPackage.LocalityModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;

public class Insert1Panel extends JPanel {

    private Container container;
    private JButton cancel,next;
    private JLabel idLabel,fnameLabel,lnameLabel,inameLabel,birthdayLabel,mailLabel,phoneLabel,cityLabel,numberLabel,streetLabel,titleLabel;
    private JTextField idText,fnameText,lnameText,inameText,mailText,phoneText,streetText,numberText;
    private JSpinner birthdaySpinner;
    private JComboBox cityCombo;
    private JPanel panelBoutton, panelIdentifiant, panelInformations,northPanel,titlePanel;
    private LayoutWindow layoutWindow;
    private EmployeeModel employeeData;
    private ArrayList<LocalityModel> listLocality;
    private ArrayList<EmployeeModel> employeeArray;
    private String id,lastName,firstName,initialName,email,phonePrivate,street,streetNumber,locality;
    private Boolean updateBool;
    private SpinnerDateModel birthday;
    private GregorianCalendar birthdayGreg;
    private Date birthdayDate;
    private LocalityModel localitySelect;
    private LocalityDataAccess localityDataAccess;


    public Insert1Panel(LayoutWindow layoutWindow,EmployeeModel employeeListing,Boolean updateBool) throws ConnectionException{

        this.layoutWindow = layoutWindow;
        container = layoutWindow.getContentPane();
        container.removeAll();

        EmployeeDataAccess employeeDataAccess = new EmployeeDataAccess();



        id = new String();
        lastName = new String();         // Management Update / Insert employee
        firstName = new String();
        initialName = new String();
        email = new String();
        phonePrivate = new String();
        street = new String();
        streetNumber = new String();
        locality = new String();
        birthday = new SpinnerDateModel();


        this.updateBool = updateBool;
        this.employeeData = employeeListing;


        if (updateBool)
        {

            id = Integer.toString(employeeData.getIdEmployee());
            lastName = employeeData.getLastName();
            firstName = employeeData.getFirstName();
            initialName = employeeData.getInitialNameSupp();
            email = employeeData.getMail();
            phonePrivate = Integer.toString(employeeData.getPhonePrivate());
            street = employeeData.getStreet();
            streetNumber = Integer.toString(employeeData.getStreetNumber());

            birthdayDate = new java.sql.Date(employeeData.getBirthday().getTimeInMillis());
            birthday.setValue(birthdayDate);

            locality = Integer.toString(employeeData.getLocalityModel().getPostalCode())+" "+employeeData.getLocalityModel().getLabelLocality();

        } else
        {
            employeeArray = employeeDataAccess.employeeListing();
            id = Integer.toString(employeeArray.size() + 1);
        }


        // Boutons
        panelBoutton = new JPanel();
        panelBoutton.setLayout(new FlowLayout());
        cancel = new JButton("Annuler");
        next = new JButton("Suivant");

        ButtonListener listener = new ButtonListener();
        cancel.addActionListener(listener);
        next.addActionListener(listener);


        panelBoutton.setLayout(new GridLayout(1,2,100,100));
        panelBoutton.add(cancel);
        panelBoutton.add(next);


        //north panel

        northPanel = new JPanel();
        northPanel.setLayout(new BorderLayout());

        panelIdentifiant = new JPanel();
        panelIdentifiant.setLayout(new GridLayout(2,6,5,5));


        idLabel = new JLabel("Identifiant : ");
        idLabel.setHorizontalAlignment(SwingConstants.LEFT);
        panelIdentifiant.add(idLabel);
        idText = new JTextField(id);
        idText.setEditable(false);
        idText.setHorizontalAlignment(SwingConstants.CENTER);
        panelIdentifiant.add(idText);

        titlePanel = new JPanel();
        titlePanel.setLayout(new FlowLayout());

        titleLabel = new JLabel("Ajout d'un employé --- Données privées");
        titlePanel.add(titleLabel);

        northPanel.add(panelIdentifiant,BorderLayout.WEST);
        northPanel.add(titlePanel,BorderLayout.CENTER);

        //Information panel

        panelInformations = new JPanel();
        panelInformations.setLayout(new GridLayout(9,4,5,5));


        lnameLabel = new JLabel("Nom : ");
        lnameLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        panelInformations.add(lnameLabel);
        lnameText = new JTextField(lastName);
        lnameText.setHorizontalAlignment(SwingConstants.LEFT);
        panelInformations.add(lnameText);

        fnameLabel = new JLabel("Prénom : ");
        fnameLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        panelInformations.add(fnameLabel);
        fnameText = new JTextField(firstName);
        fnameText.setHorizontalAlignment(SwingConstants.LEFT);
        panelInformations.add(fnameText);

        inameLabel = new JLabel("Initial prénom(s) supplémetaire(s) : ");
        inameLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        panelInformations.add(inameLabel);
        inameText = new JTextField(initialName);
        inameText.setHorizontalAlignment(SwingConstants.LEFT);
        panelInformations.add(inameText);

        birthdayLabel = new JLabel("Date de naissance : ");
        birthdayLabel.setHorizontalAlignment(SwingConstants.RIGHT);   //JSpinner Date
        panelInformations.add(birthdayLabel);
        birthdaySpinner = new JSpinner();
        birthdaySpinner.setModel(birthday);
        JSpinner.DateEditor editor = new JSpinner.DateEditor(birthdaySpinner,"dd/MM/yyyy");
        birthdaySpinner.setEditor(editor);

        panelInformations.add(birthdaySpinner);


        mailLabel = new JLabel("Email : ");
        mailLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        panelInformations.add(mailLabel);
        mailText = new JTextField(email);
        mailText.setHorizontalAlignment(SwingConstants.LEFT);
        panelInformations.add(mailText);

        phoneLabel = new JLabel("Téléphonne : ");
        phoneLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        panelInformations.add(phoneLabel);
        phoneText = new JTextField(phonePrivate);
        phoneText.setHorizontalAlignment(SwingConstants.LEFT);
        panelInformations.add(phoneText);

        cityLabel = new JLabel("Ville : ");
        cityLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        panelInformations.add(cityLabel);
        localityDataAccess = new LocalityDataAccess();
        cityCombo = new JComboBox();
        listLocality = localityDataAccess.getAllLocality();
        listLocality.forEach((AllLocality) -> {
            cityCombo.addItem(AllLocality.getPostalCode() +" "+AllLocality.getLabelLocality());
        });
                                                                                             //Combo-Box City
        cityCombo.setSelectedItem(locality);
        cityCombo.setMaximumRowCount(5);

        panelInformations.add(cityCombo);

        streetLabel = new JLabel("Rue : ");
        streetLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        panelInformations.add(streetLabel);
        streetText = new JTextField(street);
        streetText.setHorizontalAlignment(SwingConstants.LEFT);
        panelInformations.add(streetText);

        numberLabel = new JLabel("Numéro : ");
        numberLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        panelInformations.add(numberLabel);
        numberText = new JTextField(streetNumber);
        numberText.setHorizontalAlignment(SwingConstants.LEFT);
        panelInformations.add(numberText);




        // Container
        container.add(northPanel,BorderLayout.NORTH);
        container.add(panelInformations,BorderLayout.CENTER);
        container.add(panelBoutton,BorderLayout.SOUTH);



        layoutWindow.getContentPane().repaint();
        layoutWindow.setVisible(true);




    }

    private class ButtonListener implements ActionListener{

        public void actionPerformed(ActionEvent e) {

           try {
               if (e.getSource() == cancel) {
                   WelcomePanel welcomePanel = new WelcomePanel(layoutWindow);
               }
               if (e.getSource() == next) {

                   employeeData.setIdEmployee(Integer.parseInt(idText.getText()));
                   employeeData.setLastName(lnameText.getText());
                   employeeData.setFirstName(fnameText.getText());
                   employeeData.setInitialNameSupp(inameText.getText());
                   employeeData.setMail(mailText.getText());
                   employeeData.setPhonePrivate(Integer.parseInt(phoneText.getText()));
                   employeeData.setStreet(streetText.getText());
                   employeeData.setStreetNumber(Integer.parseInt(numberText.getText()));

                   localitySelect = localityDataAccess.getLocality(cityCombo.getSelectedIndex() + 1);
                   employeeData.setLocalityModel(localitySelect);

                   birthdayGreg = new GregorianCalendar();
                   birthdayGreg.setTime((Date) birthdaySpinner.getValue());
                   employeeData.setBirthday(birthdayGreg);

                   Insert2Panel insert2Panel = new Insert2Panel(layoutWindow, employeeData);

               }
           } catch (ConnectionException ex)
           {
               System.out.println("Erreur de connexion : " + ex);
           }
        }
    }
}
