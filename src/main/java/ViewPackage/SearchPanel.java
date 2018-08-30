package ViewPackage;

import ControllerPackage.Controller;
import ExceptionPackage.ConnectionException;
import ModelPackage.EmployeeModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.Date;
import java.util.ArrayList;
import java.util.GregorianCalendar;

public class SearchPanel extends JPanel {

    private Container container;
    private Controller controller;
    private JLabel startLabel,endLabel,nameLabel,textLabel;
    private JSpinner startSpinner,endSpinner;
    private JComboBox nameCombo;
    private JButton cancelButton,searchButton;
    private JPanel panelButton,informationsPanel,textPanel;
    private LayoutWindow layoutWindow;
    private ArrayList<EmployeeModel> employeeList;
    private String employee;
    private Date dateBegin,dateEnd;
    private Integer idEmployee;
    private Integer [] idEmployees;


    public SearchPanel(LayoutWindow layoutWindow) throws ConnectionException{

        this.layoutWindow = layoutWindow;
        container = layoutWindow.getContentPane();
        container.removeAll();
        controller = new Controller();



        // Boutons
        panelButton = new JPanel();
        panelButton.setLayout(new FlowLayout());
        cancelButton = new JButton("Annuler");
        searchButton = new JButton("Rechercher");

        ButtonListener listener = new ButtonListener();
        cancelButton.addActionListener(listener);
        searchButton.addActionListener(listener);

        panelButton.setLayout(new GridLayout(1,2,100,100));
        panelButton.add(cancelButton);
        panelButton.add(searchButton);

        // Informations panel

        informationsPanel = new JPanel();
        informationsPanel.setLayout(new GridLayout(3,4,10,25));

        startLabel = new JLabel("Date de début : ");
        startLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        informationsPanel.add(startLabel);
        startSpinner = new JSpinner();
        startSpinner.setModel(new SpinnerDateModel());
        JSpinner.DateEditor editorStart = new JSpinner.DateEditor(startSpinner,"dd/MM/yyyy");
        startSpinner.setEditor(editorStart);
        informationsPanel.add(startSpinner);

        endLabel = new JLabel("Date de fin : ");
        endLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        informationsPanel.add(endLabel);
        endSpinner = new JSpinner();
        endSpinner.setModel(new SpinnerDateModel());
        JSpinner.DateEditor editorEnd = new JSpinner.DateEditor(endSpinner,"dd/MM/yyyy");
        endSpinner.setEditor(editorEnd);
        informationsPanel.add(endSpinner);

        nameLabel = new JLabel("Nom du livreur : ");
        nameLabel.setHorizontalAlignment(SwingConstants.RIGHT);  //ComboBox transporter (employee with fonction = 'transporteur')
        informationsPanel.add(nameLabel);
        employeeList = controller.transporterListing();
        nameCombo = new JComboBox();
        idEmployees = new Integer[employeeList.size()];
        int i = 0;
        for (EmployeeModel AllEmployee : employeeList)
        {
            nameCombo.addItem(AllEmployee.getLastName() + " " + AllEmployee.getFirstName());
            idEmployees[i] = AllEmployee.getIdEmployee();
            i++;
        }
        nameCombo.setMaximumRowCount(5);
        nameCombo.setSelectedItem(null);
        TransporterListener transporterListener = new TransporterListener();
        nameCombo.addItemListener(transporterListener);
        nameCombo.setAutoscrolls(true);
        informationsPanel.add(nameCombo);



        //text Panel

        textPanel = new JPanel();
        textPanel.setLayout(new FlowLayout());

        textLabel = new JLabel(
                "<html><body><div color='black'><center>Recherche : Livraison </center><br/" +
                        "<p>Cette recherche permet d'afficher toutes les informations connues </p><br"+
                        "<p>sur des vélos transportés par un transporteur entre deux dates données </p><br"+
                        "<p></p><br</div></body></html>");

        textLabel.setHorizontalAlignment(SwingConstants.CENTER);
        textPanel.add(textLabel);

        container.add(textPanel,BorderLayout.NORTH);
        container.add(informationsPanel,BorderLayout.CENTER);
        container.add(panelButton,BorderLayout.SOUTH);

        layoutWindow.getContentPane().repaint();
        layoutWindow.setVisible(true);





    }

    private class ButtonListener implements ActionListener{


        public void actionPerformed(ActionEvent e) {

            try {

                    if (e.getSource() == cancelButton) {
                        WelcomePanel welcomePanel = new WelcomePanel(layoutWindow);
                    }

                    if (e.getSource() == searchButton) {

                        GregorianCalendar dateBegin = new GregorianCalendar();
                        dateBegin.setTime((Date) startSpinner.getValue());
                        GregorianCalendar dateEnd = new GregorianCalendar();
                        dateEnd.setTime ((Date) endSpinner.getValue());
                        ListingPanel listingPanel = new ListingPanel(layoutWindow,false,true,false,idEmployee,dateBegin,dateEnd);
                    }
                } catch (ConnectionException ex) {
                JOptionPane.showMessageDialog(null, "Erreur de connexion :" + ex.toString(), "Erreur", JOptionPane.ERROR_MESSAGE);
            }

        }
    }

    private class TransporterListener implements ItemListener{
                            // management array of idEmployee with comboBox selection

        @Override
        public void itemStateChanged(ItemEvent e) {

            idEmployee = idEmployees[nameCombo.getSelectedIndex()];
        }
    }
}
