package ViewPackage;

import ControllerPackage.Controller;
import ExceptionPackage.ConnectionException;
import ExceptionPackage.ValidatorException;
import ModelPackage.EmployeeModel;
import ModelPackage.StationModel;
import ModelPackage.WorkShopModel;
import ModelPackage.ZoneModel;
import ValidatorPackage.Validator;

import java.lang.String;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;

public class Insert2Panel extends JPanel {

    private Container container;
    private Controller controller;
    private LayoutWindow layoutWindow;
    private JButton returns,validate,cancel,update;
    private JPanel panelBoutton,titlePanel,panelIdentifiant,panelInformation,northPanel;
    private JLabel idLabel,titleLabel,hiringLabel,phoneProLabel,fonctionLabel,zoneLabel,workshopLabel,stationLabel,managerLabel,noteLabel;
    private JTextField idText,phoneProText;
    private JSpinner hiringSpinner;
    private JComboBox fonctionCombo,zoneCombo,workShopCombo,stationCombo,managerCombo;
    private JCheckBox partialCheck,responsableCheck,licenseCheck,workShopCheck;
    private EmployeeModel employeeData,managerData;
    private Boolean updateBool,returnBool;
    private String id;
    private String phonePro;
    private String workshop;
    private String manager;
    private String workType;
    private String zone;
    private String station;
    private SpinnerDateModel hiring;
    private ArrayList<WorkShopModel> workShopList;
    private ArrayList<EmployeeModel> managerList;
    private ArrayList<ZoneModel> zoneList;
    private ArrayList<StationModel> stationList;
    private GregorianCalendar hiringGreg;
    private Date hiringDate;
    private WorkShopModel workShopModel;
    private ZoneModel zoneModel;
    private StationModel stationModel;
    private Integer [] idManagers;
    private Integer idManager;
    private Validator validator;
    private java.lang.String [] workTypeList ={"Préposé","Transporteur","Réparateur","Administratif"};



    public Insert2Panel(LayoutWindow layoutWindow,EmployeeModel employeeListing,Boolean updateBool,Boolean returnBool) throws ConnectionException{

        this.layoutWindow = layoutWindow;
        container = layoutWindow.getContentPane();
        container.removeAll();
        controller = new Controller();

        id = new String();
        phonePro = new String();
        workshop = new String();
        zone = new String();
        station = new String();
        manager = new String();
        workType = new String();
        hiring = new SpinnerDateModel();


        this.updateBool = updateBool;
        this.returnBool = returnBool;
        this.employeeData = employeeListing;




        if (updateBool || returnBool)
        {

            id = Integer.toString(employeeData.getIdEmployee());
            phonePro = Integer.toString(employeeData.getPhonePro());
            workType = employeeData.getWorkType();

            hiringDate = new Date(employeeData.getDateHiring().getTimeInMillis());
            hiring.setValue(hiringDate);

            if(employeeData.getWorkShopModel() != null )
                workshop = employeeData.getWorkShopModel().getPlace();
            if (employeeData.getZoneModel() != null)
                zone = employeeData.getZoneModel().getLabelZone();
            if (employeeData.getStationModel() != null)
                station = employeeData.getStationModel().getLabelStation();
            if (employeeData.getInCharge() != null)
                manager = employeeData.getInCharge().getLastName() +"  "+employeeData.getInCharge().getFirstName();

        } else
            id = Integer.toString(employeeData.getIdEmployee());


        // Boutons
        panelBoutton = new JPanel();
        panelBoutton.setLayout(new FlowLayout());
        returns = new JButton("Retour");
        validate = new JButton("Valider");
        cancel = new JButton("Annuler");
        update = new JButton("Mise à jour");

        ButtonListener listener = new ButtonListener();
        returns.addActionListener(listener);
        validate.addActionListener(listener);
        cancel.addActionListener(listener);
        update.addActionListener(listener);


        panelBoutton.setLayout(new GridLayout(1,2,100,100));
        panelBoutton.add(returns);

        if (updateBool)                     //Management insert or update
        {
            panelBoutton.add(update);
        } else
        {
            panelBoutton.add(validate);
        }

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


        if (updateBool)
        {
            panelIdentifiant.add(idText);
        }


        titlePanel = new JPanel();
        titlePanel.setLayout(new FlowLayout());

        String title = new String();

        if (updateBool)
        {
            title =("Ajout d'un employé --- Données professionnelles");
        } else
        {
            title =("Modification d'un employé --- Données professionnelles");
        }

        titleLabel = new JLabel(title);
        titlePanel.add(titleLabel);

        northPanel.add(panelIdentifiant,BorderLayout.WEST);
        northPanel.add(titlePanel,BorderLayout.CENTER);


        //information panel

        panelInformation = new JPanel();
        panelInformation.setLayout(new GridLayout(10,3));

        hiringLabel = new JLabel("Date d'embauche* : ");
        hiringLabel.setHorizontalAlignment(SwingConstants.RIGHT);  //Spiner hiring date
        panelInformation.add(hiringLabel);
        hiringSpinner = new JSpinner();
        hiringSpinner.setModel(hiring);
        JSpinner.DateEditor editor = new JSpinner.DateEditor(hiringSpinner,"dd/MM/yyyy");
        hiringSpinner.setEditor(editor);
        panelInformation.add(hiringSpinner);

        phoneProLabel = new JLabel("Téléphone professionnel* : ");
        phoneProLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        panelInformation.add(phoneProLabel);
        phoneProText = new JTextField(phonePro);
        phoneProText.setHorizontalAlignment(SwingConstants.LEFT);
        panelInformation.add(phoneProText);

        FonctionListener fonctionListener = new FonctionListener();  //Listener special for fonction

        fonctionLabel = new JLabel("Fonction* : ");
        fonctionLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        panelInformation.add(fonctionLabel);
        fonctionCombo = new JComboBox(workTypeList);     // ComboBox Fonction
        fonctionCombo.setMaximumRowCount(5);
        fonctionCombo.setAutoscrolls(true);
        fonctionCombo.setSelectedItem(workType);
        fonctionCombo.addItemListener(fonctionListener);
        panelInformation.add(fonctionCombo);

       ResponsableListener responsableListener = new ResponsableListener();
                                                                    // CheckBox
        partialCheck = new JCheckBox("Temps partiel");
        partialCheck.setHorizontalAlignment(SwingConstants.CENTER);
        licenseCheck = new JCheckBox("Permis plateau");
        licenseCheck.setHorizontalAlignment(SwingConstants.CENTER);
        responsableCheck = new JCheckBox("Responsable Zone");
        responsableCheck.setHorizontalAlignment(SwingConstants.CENTER);
        workShopCheck = new JCheckBox("Chef Atelier");
        workShopCheck.setHorizontalAlignment(SwingConstants.CENTER);
                                                                        // Management CheckBox from Update
        if (updateBool || returnBool)
        {
            if ((employeeData.getPartTimeWork() != null) && (employeeData.getPartTimeWork()))
            {
                partialCheck.setSelected(true);
            }

            if ((employeeData.getDriverSpecialLicense() != null) && (employeeData.getDriverSpecialLicense()))
            {
                licenseCheck.setSelected(true);
            }

            if ((employeeData.getLeader() != null) && (employeeData.getLeader()) && (employeeData.getWorkType() != null) && (employeeData.getWorkType().equals(workTypeList[0])))
            {
                responsableCheck.setSelected(true);
            }

            if ((employeeData.getLeader() != null) && (employeeData.getLeader()) && (employeeData.getWorkType() != null) && (employeeData.getWorkType().equals(workTypeList[2])))
            {
                workShopCheck.setSelected(true);
            }
        }

        responsableCheck.addItemListener(responsableListener);

        zoneLabel = new JLabel("Zone : ");
        zoneLabel.setHorizontalAlignment(SwingConstants.RIGHT);    //ComboBox Zone
        zoneCombo = new JComboBox();
        zoneList = controller.getAllZone();
        zoneList.forEach((AllZone) -> {
            zoneCombo.addItem(AllZone.getLabelZone());
        });
        zoneCombo.setSelectedItem(zone);
        zoneCombo.setMaximumRowCount(5);
        zoneCombo.setAutoscrolls(true);


        stationLabel = new JLabel("Station : ");
        stationLabel.setHorizontalAlignment(SwingConstants.RIGHT);    //ComboBox Station
        stationCombo = new JComboBox();
        stationList = controller.stationListing();
        stationList.forEach((AllStation) -> {
            stationCombo.addItem(AllStation.getLabelStation());
        });
        stationCombo.setSelectedItem(station);
        stationCombo.setMaximumRowCount(5);
        stationCombo.setAutoscrolls(true);



        workshopLabel = new JLabel("Atelier : ");
        workshopLabel.setHorizontalAlignment(SwingConstants.RIGHT);  //ComboBox Workshop
        workShopCombo = new JComboBox();
        workShopList = controller.getAllWorkShop();
        workShopList.forEach((AllWorkShop) -> {
            workShopCombo.addItem(AllWorkShop.getPlace());
        });
        workShopCombo.setSelectedItem(workshop);
        workShopCombo.setMaximumRowCount(5);
        workShopCombo.setAutoscrolls(true);


        managerLabel = new JLabel("Manager : ");
        managerLabel.setHorizontalAlignment(SwingConstants.RIGHT);  //ComboBox Manager
        managerCombo = new JComboBox();
        managerList = controller.employeeListing();
        idManagers = new Integer[managerList.size() + 1];
        int i = 1;
        managerCombo.addItem(" ");
        for (EmployeeModel AllManager : managerList)
        {
            managerCombo.addItem(AllManager.getLastName() + "  " + AllManager.getFirstName());
            idManagers[i] = AllManager.getIdEmployee();
            i++;
        }
        managerCombo.setSelectedItem(manager);
        managerCombo.setMaximumRowCount(5);
        managerCombo.setAutoscrolls(true);
        ManagerListener managerListener = new ManagerListener();
        managerCombo.addItemListener(managerListener);


        switch (fonctionCombo.getSelectedIndex()) {     // Management default enabled checkbox and combobox from default fonctionCombo

            case 0 :{          // Préposé
                workShopCheck.setEnabled(false);
                workShopCheck.setSelected(false);
                workShopCombo.setEnabled(false);
                responsableCheck.setEnabled(true);
                licenseCheck.setEnabled(false);
                licenseCheck.setSelected(false);

                if (responsableCheck.isSelected())
                {
                    zoneCombo.setEnabled(true);
                    stationCombo.setEnabled(false);
                } else
                {
                    zoneCombo.setEnabled(false);
                    stationCombo.setEnabled(true);
                }
                break;
            }

            case 1 :{      // Transporteur
                responsableCheck.setEnabled(false);
                responsableCheck.setSelected(false);
                zoneCombo.setEnabled(false);
                workShopCheck.setEnabled(false);
                workShopCombo.setEnabled(false);
                licenseCheck.setEnabled(true);
                stationCombo.setEnabled(false);
                break;
            }

            case 2 :{      // Réparateur
                responsableCheck.setEnabled(false);
                responsableCheck.setSelected(false);
                zoneCombo.setEnabled(false);
                workShopCheck.setEnabled(true);
                workShopCombo.setEnabled(true);
                licenseCheck.setEnabled(false);
                licenseCheck.setSelected(false);
                stationCombo.setEnabled(false);
                break;
            }

            case 3 :{      // Administratif
                responsableCheck.setEnabled(false);
                responsableCheck.setSelected(false);
                zoneCombo.setEnabled(false);
                workShopCheck.setEnabled(false);
                workShopCheck.setSelected(false);
                workShopCombo.setEnabled(false);
                licenseCheck.setEnabled(false);
                licenseCheck.setSelected(false);
                stationCombo.setEnabled(false);
                break;
            }

        }

        panelInformation.add(partialCheck);         // Order composant into pannel (=design)
        panelInformation.add(licenseCheck);
        panelInformation.add(responsableCheck);
        panelInformation.add(workShopCheck);

        panelInformation.add(zoneLabel);
        panelInformation.add(zoneCombo);
        panelInformation.add(stationLabel);
        panelInformation.add(stationCombo);
        panelInformation.add(workshopLabel);
        panelInformation.add(workShopCombo);
        panelInformation.add(managerLabel);
        panelInformation.add(managerCombo);

        noteLabel = new JLabel(" * Champs obligatoires");
        noteLabel.setHorizontalAlignment(SwingConstants.LEFT);
        panelInformation.add(noteLabel);



        container.add(northPanel,BorderLayout.NORTH);
        container.add(panelInformation,BorderLayout.CENTER);
        container.add(panelBoutton,BorderLayout.SOUTH);



        layoutWindow.getContentPane().repaint();
        layoutWindow.setVisible(true);

    }

    private class ButtonListener implements ActionListener{


        public void actionPerformed(ActionEvent e) {

            try {

                if (e.getSource() == cancel) {
                    employeeData = null;
                    WelcomePanel welcomePanel = new WelcomePanel(layoutWindow);
                }

                if ((e.getSource() == validate) || (e.getSource() == update) || (e.getSource() == returns))
                {

                    validator = new Validator();
                    switch (fonctionCombo.getSelectedIndex()){

                    case 0 :                    // Préposé
                            employeeData.setWorkShopModel(null);
                            employeeData.setDriverSpecialLicense(null);


                            if (responsableCheck.isSelected())          //Responsable Zone
                            {
                                employeeData.setLeader(true);
                                zoneModel = controller.getZone(zoneCombo.getSelectedIndex() + 1);
                                employeeData.setZoneModel(zoneModel);
                                employeeData.setStationModel(null);
                            }
                            else
                            {
                                stationModel = controller.getStation(stationCombo.getSelectedIndex() + 1);
                                employeeData.setStationModel(stationModel);
                                employeeData.setZoneModel(null);
                                employeeData.setLeader(false);
                            }
                            break;

                    case 1 :                //Transporteur
                            employeeData.setDriverSpecialLicense(licenseCheck.isSelected());
                            employeeData.setLeader(null);
                            employeeData.setZoneModel(null);
                            employeeData.setStationModel(null);
                            employeeData.setWorkShopModel(null);
                            break;

                    case 2 :                //Réparateur
                            employeeData.setDriverSpecialLicense(null);
                            employeeData.setLeader(workShopCheck.isSelected());
                            employeeData.setZoneModel(null);
                            employeeData.setStationModel(null);
                            workShopModel = controller.getWorkShop(workShopCombo.getSelectedIndex() + 1);
                            employeeData.setWorkShopModel(workShopModel);
                            break;

                    case 3 :                // Administratif
                            employeeData.setDriverSpecialLicense(null);
                            employeeData.setLeader(null);
                            employeeData.setZoneModel(null);
                            employeeData.setStationModel(null);
                            employeeData.setWorkShopModel(null);
                            break;
                }

                employeeData.setIdEmployee(Integer.parseInt(idText.getText()));
                employeeData.setWorkType((String) fonctionCombo.getSelectedItem());
                employeeData.setPartTimeWork(partialCheck.isSelected());


                idManager = idManagers[managerCombo.getSelectedIndex()];
                if (managerCombo.getSelectedIndex() == 0)
                {
                    employeeData.setInCharge(null);
                } else
                {
                    managerData = controller.getEmployee(idManager);
                    employeeData.setInCharge(managerData);
                }


                hiringGreg = new GregorianCalendar();
                hiringGreg.setTime((Date) hiringSpinner.getValue());
                employeeData.setDateHiring(hiringGreg);


                if (e.getSource() == returns) {

                    try
                    {
                        validator.controlTextRequiredNumber(phoneProText.getText(), " Téléphonne professionnel");
                        employeeData.setPhonePro(Integer.parseInt(phoneProText.getText()));
                        Boolean returnBool = true;
                        Boolean upDateBool = updateBool;

                        Insert1Panel insert1Panel = new Insert1Panel(layoutWindow,employeeData,upDateBool,returnBool);
                    }catch (ValidatorException ve)
                    {
                        JOptionPane.showMessageDialog(null,"Erreur de validation : " + ve.toString(),"Exception",JOptionPane.ERROR_MESSAGE);
                    }


                }


                if (e.getSource() == validate) {
                    int ok = JOptionPane.showConfirmDialog(null, "Voulez vous vraiment ajouter ce nouvel employé ?", "Confirmation", JOptionPane.YES_NO_OPTION);

                    if (ok == 0) {
                        try
                        {
                            validator.controlText(employeeData.getLastName(), "Nom de l'employé");
                            validator.controlText(employeeData.getFirstName(),"Prénom de l'employé");
                            validator.controlInitial(employeeData.getInitialNameSupp(), "Initials supplémentaires");
                            validator.controlText(employeeData.getStreet(),"Rue du domicile");
                            validator.controlRequiredNumber(employeeData.getStreetNumber(),"Numéro du domicile");
                            validator.controlTextRequiredNumber(phoneProText.getText(), " Téléphonne professionnel");
                            employeeData.setPhonePro(Integer.parseInt(phoneProText.getText()));
                            validator.controlRequiredNumber(employeeData.getPhonePro(), "Téléphone Pro");

                            if (employeeData.getPhonePrivate() != null)
                            {
                                validator.controlRequiredNumber(employeeData.getPhonePrivate(),"Téléphonne privé");
                            }

                            Boolean modification = controller.insertEmployee(employeeData);

                        if (modification)
                        {
                            JOptionPane.showMessageDialog(null,"Nouvel employe " + employeeData.getLastName()+ " "+employeeData.getFirstName()+" bien ajouté !");
                            WelcomePanel welcomePanel = new WelcomePanel(layoutWindow);
                        } else
                        {
                            JOptionPane.showMessageDialog(null,"Erreur lors de l'insertion","Erreur",JOptionPane.ERROR_MESSAGE);
                        }
                        } catch (ValidatorException ve)
                        {
                            JOptionPane.showMessageDialog(null,"Erreur de validation : " + ve.toString(),"Exception",JOptionPane.ERROR_MESSAGE);
                        }
                    }

                }

                if (e.getSource() == update) {
                    int ok = JOptionPane.showConfirmDialog(null, "Voulez vous vraiment modifier cet employé ?", "Confirmation", JOptionPane.YES_NO_OPTION);

                    if (ok == 0) {
                        try {

                            validator.controlText(employeeData.getLastName(), "Nom de l'employé");
                            validator.controlText(employeeData.getFirstName(),"Prénom de l'employé");
                            validator.controlInitial(employeeData.getInitialNameSupp(), "Initials supplémentaires");
                            validator.controlText(employeeData.getStreet(),"Rue du domicile");
                            validator.controlNumber(employeeData.getStreetNumber(),"Numéro du domicile");
                            validator.controlTextRequiredNumber(phoneProText.getText(), " Téléphonne professionnel");
                            employeeData.setPhonePro(Integer.parseInt(phoneProText.getText()));
                            validator.controlRequiredNumber(employeeData.getPhonePro(), "Téléphone Pro");

                            if (employeeData.getPhonePrivate() != null)
                            {
                                validator.controlRequiredNumber(employeeData.getPhonePrivate(),"Téléphonne privé");
                            }
                            Boolean modification = controller.updateEmployee(employeeData);
                            if (modification) {
                                JOptionPane.showMessageDialog(null, "L'employe " + employeeData.getLastName() + " " + employeeData.getFirstName() + " a bien été modifié !");
                                WelcomePanel welcomePanel = new WelcomePanel(layoutWindow);
                            } else {
                                JOptionPane.showMessageDialog(null, "Erreur lors de la modification", "Erreur", JOptionPane.ERROR_MESSAGE);
                            }
                        }catch (ValidatorException ve)
                        {
                            JOptionPane.showMessageDialog(null,"Erreur de validation : " + ve.toString(),"Exception",JOptionPane.ERROR_MESSAGE);
                        }


                    }
                }

                }
            } catch (ConnectionException ex)
            {
                JOptionPane.showMessageDialog(null,"Erreur de connexion : " + ex.toString(),"Exception",JOptionPane.ERROR_MESSAGE);
                System.out.println("Erreur de connexion : " + ex);
            }

        }
    }

    private class FonctionListener implements ItemListener{


        public void itemStateChanged(ItemEvent e) {

            switch (fonctionCombo.getSelectedIndex()) {     // Management enabled checkbox and combobox from fonctionCombo

                case 0 : if(e.getStateChange() == ItemEvent.SELECTED){          // Préposé
                    workShopCheck.setEnabled(false);
                    workShopCheck.setSelected(false);
                    workShopCombo.setEnabled(false);
                    responsableCheck.setEnabled(true);
                    licenseCheck.setEnabled(false);
                    licenseCheck.setSelected(false);
                    employeeData.setWorkShopModel(null);
                    employeeData.setDriverSpecialLicense(null);

                    if (responsableCheck.isSelected())
                    {
                        zoneCombo.setEnabled(true);
                        stationCombo.setEnabled(false);
                    } else
                    {
                        zoneCombo.setEnabled(false);
                        stationCombo.setEnabled(true);
                    }


                    break;
                }

                case 1 : if(e.getStateChange() == ItemEvent.SELECTED){      // Transporteur
                    responsableCheck.setEnabled(false);
                    responsableCheck.setSelected(false);
                    zoneCombo.setEnabled(false);
                    workShopCheck.setEnabled(false);
                    workShopCombo.setEnabled(false);
                    licenseCheck.setEnabled(true);
                    stationCombo.setEnabled(false);
                    break;
                }

                case 2 : if(e.getStateChange() == ItemEvent.SELECTED){      // Réparateur
                    responsableCheck.setEnabled(false);
                    responsableCheck.setSelected(false);
                    zoneCombo.setEnabled(false);
                    workShopCheck.setEnabled(true);
                    workShopCombo.setEnabled(true);
                    licenseCheck.setEnabled(false);
                    licenseCheck.setSelected(false);
                    stationCombo.setEnabled(false);
                    break;
                }

                case 3 : if(e.getStateChange() == ItemEvent.SELECTED){      // Administratif
                    responsableCheck.setEnabled(false);
                    responsableCheck.setSelected(false);
                    zoneCombo.setEnabled(false);
                    workShopCheck.setEnabled(false);
                    workShopCheck.setSelected(false);
                    workShopCombo.setEnabled(false);
                    licenseCheck.setEnabled(false);
                    licenseCheck.setSelected(false);
                    stationCombo.setEnabled(false);
                    break;
                }

            }
        }

    }

    private class ResponsableListener implements ItemListener {



        public void itemStateChanged(ItemEvent r) {

                if (responsableCheck.isSelected()) {
                    zoneCombo.setEnabled(true);
                    stationCombo.setEnabled(false);
                } else {
                    zoneCombo.setEnabled(false);
                    stationCombo.setEnabled(true);
                }

        }
    }

    private class ManagerListener implements ItemListener {


        @Override
        public void itemStateChanged(ItemEvent e) {

            idManager = idManagers[managerCombo.getSelectedIndex()];
        }
    }
}
