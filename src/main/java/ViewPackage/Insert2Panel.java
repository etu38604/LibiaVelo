package ViewPackage;

import ExceptionPackage.ConnectionException;
import ModelPackage.EmployeeModel;
import ModelPackage.WorkShopModel;
import com.sun.org.apache.xpath.internal.operations.And;
import com.sun.org.apache.xpath.internal.operations.String;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.Date;
import java.util.GregorianCalendar;

public class Insert2Panel extends JPanel {

    private Container container;
    private LayoutWindow layoutWindow;
    private JButton returns,validate,cancel;
    private JPanel panelBoutton,titlePanel,panelIdentifiant,panelInformation,northPanel;
    private JLabel idLabel,titleLabel,hiringLabel,phoneProLabel,fonctionLabel,zoneLabel,workshopLabel;
    private JTextField idText,phoneProText;
    private JSpinner hiringSpinner;
    private JComboBox fonctionCombo,zoneCombo,workShopCombo;
    private JCheckBox partialCheck,responsableCheck,licenseCheck,workShopCheck;
    private EmployeeModel employeeData;
    private Boolean updateBool;
    private java.lang.String id;
    private java.lang.String phonePro;
    private java.lang.String workshop;
    private SpinnerDateModel hiring;
    private GregorianCalendar hiringGreg;
    private Date hiringDate;
    private WorkShopModel workShopModel;
    private java.lang.String [] fonction ={"préposé","transporteur","réparateur","administratif"};
    private java.lang.String [] zone ={"Namur-Centre","Jambes","Salzinnes","Namur-Bomel"};



    public Insert2Panel(LayoutWindow layoutWindow,EmployeeModel employeeListing) throws ConnectionException{

        this.layoutWindow = layoutWindow;
        container = layoutWindow.getContentPane();
        container.removeAll();

        id = new java.lang.String();
        phonePro = new java.lang.String();
        workshop = new java.lang.String();
        hiring = new SpinnerDateModel();


        this.updateBool = updateBool;
        this.employeeData = employeeListing;


        if (updateBool)
        {

            id = Integer.toString(employeeData.getIdEmployee());
            phonePro = Integer.toString(employeeData.getPhonePrivate());

            hiringDate = new java.sql.Date(employeeData.getDateHiring().getTimeInMillis());
            hiring.setValue(hiringDate);

            workshop = Integer.toString(employeeData.getLocalityModel().getPostalCode())+" "+employeeData.getLocalityModel().getLabelLocality();

        } else
            id = Integer.toString(employeeData.getIdEmployee());


        // Boutons
        panelBoutton = new JPanel();
        panelBoutton.setLayout(new FlowLayout());
        returns = new JButton("Retour");
        validate = new JButton("Valider");
        cancel = new JButton("Annuler");

        ButtonListener listener = new ButtonListener();
        returns.addActionListener(listener);
        validate.addActionListener(listener);
        cancel.addActionListener(listener);


        panelBoutton.setLayout(new GridLayout(1,2,100,100));
        panelBoutton.add(returns);
        panelBoutton.add(validate);
        panelBoutton.add(cancel);

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
        idText.setHorizontalAlignment(SwingConstants.LEFT);
        panelIdentifiant.add(idText);

        titlePanel = new JPanel();
        titlePanel.setLayout(new FlowLayout());

        titleLabel = new JLabel("Ajout d'un employé --- Données professionnelles");
        titlePanel.add(titleLabel);

        northPanel.add(panelIdentifiant,BorderLayout.WEST);
        northPanel.add(titlePanel,BorderLayout.CENTER);


        //information panel

        panelInformation = new JPanel();
        panelInformation.setLayout(new GridLayout(8,3));

        hiringLabel = new JLabel("Date d'embauche : ");
        hiringLabel.setHorizontalAlignment(SwingConstants.RIGHT);  //Spiner hiring date
        panelInformation.add(hiringLabel);
        hiringSpinner = new JSpinner();
        hiringSpinner.setModel(hiring);
        JSpinner.DateEditor editor = new JSpinner.DateEditor(hiringSpinner,"dd/MM/yyyy");
        hiringSpinner.setEditor(editor);
        panelInformation.add(hiringSpinner);

        phoneProLabel = new JLabel("Téléphone professionnel : ");
        phoneProLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        panelInformation.add(phoneProLabel);
        phoneProText = new JTextField(phonePro);
        phoneProText.setHorizontalAlignment(SwingConstants.LEFT);
        panelInformation.add(phoneProText);

        fonctionLabel = new JLabel("Fonction : ");
        fonctionLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        panelInformation.add(fonctionLabel);
        fonctionCombo = new JComboBox(fonction);     // ComboBox Fonction
        fonctionCombo.setMaximumRowCount(5);
        fonctionCombo.setAutoscrolls(true);
        FonctionListener fonctionListener = new FonctionListener();
        fonctionCombo.addItemListener(fonctionListener);
        panelInformation.add(fonctionCombo);
                                                                    // CheckBox
        partialCheck = new JCheckBox("Temps partiel");
        partialCheck.setHorizontalAlignment(SwingConstants.CENTER);
        licenseCheck = new JCheckBox("Permis plateau");
        licenseCheck.setHorizontalAlignment(SwingConstants.CENTER);
        responsableCheck = new JCheckBox("Responsable Zone");
        responsableCheck.setHorizontalAlignment(SwingConstants.CENTER);
        workShopCheck = new JCheckBox("Chef Atelier");
        workShopCheck.setHorizontalAlignment(SwingConstants.CENTER);

        if (updateBool)
        {
            if (employeeData.getPartTimeWork()) partialCheck.isSelected();
            if (employeeData.getDriverSpecialLicense()) licenseCheck.isSelected();
            if (employeeData.getLeader()) And(fonction == "réparateur")
            {
                responsableCheck.isSelected();
            }
        }

        panelInformation.add(partialCheck);
        panelInformation.add(licenseCheck);
        panelInformation.add(responsableCheck);
        panelInformation.add(workShopCheck);

        zoneLabel = new JLabel("Zone : ");
        zoneLabel.setHorizontalAlignment(SwingConstants.RIGHT);    //ComboBox Zone
        panelInformation.add(zoneLabel);
        zoneCombo = new JComboBox(zone);
        zoneCombo.setMaximumRowCount(5);
        zoneCombo.setAutoscrolls(true);
        panelInformation.add(zoneCombo);

        workshopLabel = new JLabel("Atelier : ");
        workshopLabel.setHorizontalAlignment(SwingConstants.RIGHT);  //ComboBox Atelier
        panelInformation.add(workshopLabel);
        String [] valuesWork ={"Bomel","Caserne"};
        workShopCombo = new JComboBox(valuesWork);
        workShopCombo.setSelectedItem("bomel");
        workShopCombo.setMaximumRowCount(5);
        workShopCombo.setAutoscrolls(true);
        panelInformation.add(workShopCombo);


        container.add(northPanel,BorderLayout.NORTH);
        container.add(panelInformation,BorderLayout.CENTER);
        container.add(panelBoutton,BorderLayout.SOUTH);



        layoutWindow.getContentPane().repaint();
        layoutWindow.setVisible(true);

    }

    private class ButtonListener implements ActionListener{


        public void actionPerformed(ActionEvent e) {

            try {

                if (e.getSource() == returns) {


                    Boolean upDateBool = true;
                    Insert1Panel insert1Panel = new Insert1Panel(layoutWindow, employeeData,upDateBool);

                }

                if (e.getSource() == cancel) {
                    WelcomePanel welcomePanel = new WelcomePanel(layoutWindow);
                }

                if (e.getSource() == validate) {
                    int ok = JOptionPane.showConfirmDialog(null, "Voulez vous vraiment ajouter ce nouvel employé ?", "Confirmation", JOptionPane.YES_NO_OPTION);
                    System.out.println(ok);

                    if (ok == 0) {
                        WelcomePanel welcomePanel = new WelcomePanel(layoutWindow);
                    }


                }
            } catch (ConnectionException ex)
            {
                System.out.println("Erreur de connexion : " + ex);
            }



        }
    }

    private class FonctionListener implements ItemListener{


        public void itemStateChanged(ItemEvent e) {

            switch (fonctionCombo.getSelectedIndex()) {

                case 0 : if(e.getStateChange() == ItemEvent.SELECTED){
                    workShopCheck.setEnabled(false);
                    workShopCombo.setEnabled(false);
                    responsableCheck.setEnabled(true);
                    zoneCombo.setEnabled(true);
                    break;
                }

                case 1 : if(e.getStateChange() == ItemEvent.SELECTED){
                    responsableCheck.setEnabled(false);
                    zoneCombo.setEnabled(false);
                    workShopCheck.setEnabled(true);
                    workShopCombo.setEnabled(true);
                    break;
                }

            }
        }
    }
}
