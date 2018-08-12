package ViewPackage;

import DataAccessPackage.EmployeeDataAccess;
import ExceptionPackage.ConnectionException;
import ModelPackage.EmployeeModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Insert1Panel extends JPanel {

    private Container container;
    private JButton cancel,next;
    private JLabel idLabel,fnameLabel,lnameLabel,inameLabel,birthdayLabel,mailLabel,phoneLabel,cityLabel,numberLabel,streetLabel,titleLabel;
    private JTextField idText,fnameText,lnameText,inameText,mailText,phoneText,streetText,numberText;
    private JSpinner birthdaySpinner;
    private JComboBox cityCombo;
    private JPanel panelBoutton, panelIdentifiant, panelInformations,northPanel,titlePanel;
    private LayoutWindow layoutWindow;
    private WelcomePanel welcomePanel;
    private EmployeeModel employeeData;
    private EmployeeDataAccess employeeDataAccess;

    public Insert1Panel(LayoutWindow layoutWindow,EmployeeModel employeeListing) throws ConnectionException{

        this.layoutWindow = layoutWindow;
        container = layoutWindow.getContentPane();
        container.removeAll();

        EmployeeDataAccess employeeDataAccess = new EmployeeDataAccess();
        employeeData = employeeDataAccess.getEmployee(2);

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
        idText = new JTextField(employeeData.getIdEmployee());
        idText.setEditable(false);
        idText.setHorizontalAlignment(SwingConstants.LEFT);
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
        lnameText = new JTextField(employeeData.getLastName());
        lnameText.setHorizontalAlignment(SwingConstants.LEFT);
        panelInformations.add(lnameText);

        fnameLabel = new JLabel("Prénom : ");
        fnameLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        panelInformations.add(fnameLabel);
        fnameText = new JTextField(employeeData.getFirstName());
        fnameText.setHorizontalAlignment(SwingConstants.LEFT);
        panelInformations.add(fnameText);

        inameLabel = new JLabel("Initial prénom(s) supplémetaire(s) : ");
        inameLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        panelInformations.add(inameLabel);
        inameText = new JTextField(employeeData.getInitialNameSupp());
        inameText.setHorizontalAlignment(SwingConstants.LEFT);
        panelInformations.add(inameText);

        birthdayLabel = new JLabel("Date de naissance : ");
        birthdayLabel.setHorizontalAlignment(SwingConstants.RIGHT);   //JSpinner Date
        panelInformations.add(birthdayLabel);
        birthdaySpinner = new JSpinner();
        birthdaySpinner.setModel(new SpinnerDateModel());
        JSpinner.DateEditor editor = new JSpinner.DateEditor(birthdaySpinner,"dd/MM/yyyy");
        birthdaySpinner.setEditor(editor);
        panelInformations.add(birthdaySpinner);


        mailLabel = new JLabel("Email : ");
        mailLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        panelInformations.add(mailLabel);
        mailText = new JTextField(employeeData.getMail());
        mailText.setHorizontalAlignment(SwingConstants.LEFT);
        panelInformations.add(mailText);

        phoneLabel = new JLabel("Téléphonne : ");
        phoneLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        panelInformations.add(phoneLabel);
        phoneText = new JTextField(employeeData.getPhonePrivate());
        phoneText.setHorizontalAlignment(SwingConstants.LEFT);
        panelInformations.add(phoneText);

        cityLabel = new JLabel("Ville : ");
        cityLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        panelInformations.add(cityLabel);
        String [] values ={"Namur","Liege"};
        cityCombo = new JComboBox(values);                                //Combo-Box City
        cityCombo.setSelectedItem("Namur");
        cityCombo.setMaximumRowCount(5);
        panelInformations.add(cityCombo);

        streetLabel = new JLabel("Rue : ");
        streetLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        panelInformations.add(streetLabel);
        streetText = new JTextField(employeeData.getStreet());
        streetText.setHorizontalAlignment(SwingConstants.LEFT);
        panelInformations.add(streetText);

        numberLabel = new JLabel("Numéro : ");
        numberLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        panelInformations.add(numberLabel);
        numberText = new JTextField(employeeData.getStreetNumber());
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

            if (e.getSource() == cancel) {
                WelcomePanel welcomePanel = new WelcomePanel(layoutWindow);
            }
            if (e.getSource() == next){
                Insert2Panel insert2Panel = new Insert2Panel(layoutWindow);
            }

        }
    }
}
