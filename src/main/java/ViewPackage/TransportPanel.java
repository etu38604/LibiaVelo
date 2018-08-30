package ViewPackage;

import ControllerPackage.Controller;
import ExceptionPackage.ConnectionException;
import ExceptionPackage.ValidatorException;
import ModelPackage.*;
import ValidatorPackage.Validator;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.Date;
import java.util.ArrayList;
import java.util.GregorianCalendar;

public class TransportPanel extends JPanel{

    private Container container;
    private Controller controller;
    private LayoutWindow layoutWindow;
    private JPanel buttonPanel,informationsPanel;
    private JSpinner dateTransportSpinner;
    private SpinnerDateModel dateTransportModel;
    private Date transportDate;
    private Boolean isToReparation;
    private JTextField idText;
    private JComboBox employeeCombo,stationIssuanceCombo,stationOriginCombo,stationDestinationCombo,bikeCombo,workshopCombo;
    private JLabel employeeLabel,bikeLabel,dateTransportLabel,stationIssuanceLabel,stationOriginLabel,stationDestinationLabel,workshopLabel;
    private JButton validateButton, cancelButton;
    private ReparationRecordModel reparation;
    private TransportOrderModel transport;
    private ArrayList<EmployeeModel> employeeListing;
    private ArrayList<BikeModel> bikeListing;
    private ArrayList<WorkShopModel> workshopListing;
    private ArrayList<StationModel> stationListing;
    private GregorianCalendar transportGreg;
    private BikeModel bikeModel;
    private WorkShopModel workShopModel;
    private StationModel stationModel;
    private EmployeeModel employeeModel;
    private Integer[] idEmployees,idStations,idBikes,idWorkshops;
    private Integer idEmployee,idStationIssuance,idStationOrigin,idStationDestination,idBike,idWorkshop;
    private Validator validator;


    public TransportPanel(LayoutWindow layoutWindow,ReparationRecordModel reparation,Boolean isToReparation) throws ConnectionException {


        this.layoutWindow = layoutWindow;
        container = layoutWindow.getContentPane();
        container.removeAll();
        controller = new Controller();
        this.reparation = reparation;
        transport = new TransportOrderModel();
        this.isToReparation = isToReparation;


        // Buttons

        buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());
        cancelButton = new JButton("Annuler");
        validateButton = new JButton("Valider");

        ButtonListener listener = new ButtonListener();
        cancelButton.addActionListener(listener);
        validateButton.addActionListener(listener);


        buttonPanel.setLayout(new GridLayout(1,2,50,100));
        buttonPanel.add(cancelButton);
        buttonPanel.add(validateButton);



        // informations panel

        informationsPanel = new JPanel();
        informationsPanel.setLayout(new GridLayout(7,3,50,50));

        dateTransportLabel = new JLabel("Date de transport : ");
        dateTransportLabel.setHorizontalAlignment(SwingConstants.RIGHT);   //JSpinner Date
        informationsPanel.add(dateTransportLabel);
        dateTransportSpinner = new JSpinner();
        dateTransportModel = new SpinnerDateModel();
        transportDate = new Date(System.currentTimeMillis());
        dateTransportModel.setValue(transportDate);
        dateTransportSpinner.setModel(dateTransportModel);
        JSpinner.DateEditor editor = new JSpinner.DateEditor(dateTransportSpinner,"dd/MM/yyyy");
        dateTransportSpinner.setEditor(editor);

        informationsPanel.add(dateTransportSpinner);

        IdListener idListener = new IdListener();

        bikeLabel = new JLabel("Numéro du Vélo : ");
        bikeLabel.setHorizontalAlignment(SwingConstants.RIGHT);         //ComboBox Bike
        bikeCombo = new JComboBox();
        bikeListing = controller.getAllBike();
        idBikes = new Integer[bikeListing.size()];
        int b = 0;
        for (BikeModel AllBike : bikeListing)
        {
            bikeCombo.addItem(AllBike.getIdBike());
            idBikes[b] = AllBike.getIdBike();
            b++;
        }
        bikeCombo.setMaximumRowCount(5);
        bikeCombo.setAutoscrolls(true);
        bikeCombo.addItemListener(idListener);




        employeeLabel = new JLabel("Transporteur : ");
        employeeLabel.setHorizontalAlignment(SwingConstants.RIGHT);  //ComboBox Employee (transpoter)
        employeeCombo = new JComboBox();
        employeeListing = controller.transporterListing();
        idEmployees = new Integer[employeeListing.size()];
        int i = 0;
        for (EmployeeModel AllEmployee : employeeListing)
        {
            employeeCombo.addItem(AllEmployee.getLastName() + "  " + AllEmployee.getFirstName());
            idEmployees[i] = AllEmployee.getIdEmployee();
            i++;
        }
        employeeCombo.setMaximumRowCount(5);
        employeeCombo.setAutoscrolls(true);
        employeeCombo.addItemListener(idListener);


        stationIssuanceLabel = new JLabel("Station émettrice de l'ordre : ");       //ComboBox Stations Issuance + Origin + Destination
        stationIssuanceLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        stationOriginLabel = new JLabel("Station d'origine de l'ordre : ");
        stationOriginLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        stationDestinationLabel = new JLabel("Station de destination de l'ordre : ");
        stationDestinationLabel.setHorizontalAlignment(SwingConstants.RIGHT);

        stationIssuanceCombo = new JComboBox();
        stationOriginCombo = new JComboBox();
        stationDestinationCombo = new JComboBox();

        stationListing = controller.stationListing();
        idStations = new Integer[stationListing.size()];
        int j = 0;
        for (StationModel AllStation : stationListing)
        {
            stationIssuanceCombo.addItem(AllStation.getLabelStation());
            stationOriginCombo.addItem(AllStation.getLabelStation());
            stationDestinationCombo.addItem(AllStation.getLabelStation());

            idStations[j] = AllStation.getIdStation();
            j++;
        }


        stationIssuanceCombo.setMaximumRowCount(5);
        stationIssuanceCombo.setAutoscrolls(true);
        stationIssuanceCombo.addItemListener(idListener);
        stationOriginCombo.setMaximumRowCount(5);
        stationOriginCombo.setAutoscrolls(true);
        stationOriginCombo.addItemListener(idListener);
        stationDestinationCombo.setMaximumRowCount(5);
        stationDestinationCombo.setAutoscrolls(true);
        stationDestinationCombo.addItemListener(idListener);


        workshopLabel = new JLabel("Atelier de réparation : ");
        workshopLabel.setHorizontalAlignment(SwingConstants.RIGHT);          //ComboBox Workshop
        workshopCombo = new JComboBox();
        workshopListing = controller.getAllWorkShop();
        idWorkshops = new Integer[workshopListing.size()];
        int w = 0;
        for (WorkShopModel AllWorkshop : workshopListing)
        {
            workshopCombo.addItem(AllWorkshop.getPlace());
            idWorkshops[w] = AllWorkshop.getIdWorkShop();
            w++;
        }
        workshopCombo.setMaximumRowCount(5);
        workshopCombo.setAutoscrolls(true);
        workshopCombo.addItemListener(idListener);
                                                        // Management Transport Order from reparation or from station
        if (isToReparation)
        {
            bikeCombo.setSelectedItem(reparation.getBikeModel().getIdBike());
            idBike = idBikes[bikeCombo.getSelectedIndex()];
            employeeCombo.setSelectedItem(reparation.getWorkShopModel().getPlace());
            idEmployee = idEmployees[employeeCombo.getSelectedIndex()];
            stationIssuanceCombo.setSelectedItem(reparation.getBikeModel().getStationModel().getLabelStation());
           // idStationIssuance = idStations[stationIssuanceCombo.getSelectedIndex()];
            stationOriginCombo.setSelectedItem(null);
            stationOriginCombo.setEnabled(false);
            idStationOrigin = null;
            stationDestinationCombo.setSelectedItem(null);
            stationDestinationCombo.setEnabled(false);
            idStationDestination = null;
            workshopCombo.setSelectedItem(reparation.getWorkShopModel().getPlace());
            idWorkshop = idWorkshops[workshopCombo.getSelectedIndex()];
        } else
        {
            idBike = null;
            idEmployee = null;
            idStationIssuance = null;
            idStationOrigin = null;
            idStationDestination = null;
            workshopCombo.setSelectedItem(null);
            workshopCombo.setEnabled(false);
            idWorkshop = null;
        }


        informationsPanel.add(bikeLabel);
        informationsPanel.add(bikeCombo);
        informationsPanel.add(employeeLabel);
        informationsPanel.add(employeeCombo);
        informationsPanel.add(stationIssuanceLabel);
        informationsPanel.add(stationIssuanceCombo);
        informationsPanel.add(stationOriginLabel);
        informationsPanel.add(stationOriginCombo);
        informationsPanel.add(stationDestinationLabel);
        informationsPanel.add(stationDestinationCombo);
        informationsPanel.add(workshopLabel);
        informationsPanel.add(workshopCombo);



        container.add(informationsPanel,BorderLayout.CENTER);
        container.add(buttonPanel,BorderLayout.SOUTH);



        layoutWindow.getContentPane().repaint();
        layoutWindow.setVisible(true);




    }

    private class ButtonListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {

            try {

                if (e.getSource() == cancelButton)
                {
                    WelcomePanel welcomePanel = new WelcomePanel(layoutWindow);
                }

                if (e.getSource() == validateButton)
                {
                    transportGreg = new GregorianCalendar();
                    transportGreg.setTime((java.util.Date) dateTransportSpinner.getValue());
                    transport.setDateTransport(transportGreg);

                    idEmployee = idEmployees[employeeCombo.getSelectedIndex()];
                    employeeModel = controller.getEmployee(idEmployee);
                    transport.setEmployeeModel(employeeModel);

                    idStationIssuance = idStations[stationIssuanceCombo.getSelectedIndex()];
                    stationModel = controller.getStation(idStationIssuance);
                    transport.setStationIssuance(stationModel);

                    if (idStationOrigin != null)
                    {
                        idStationOrigin = idStations[stationOriginCombo.getSelectedIndex()];
                        stationModel = controller.getStation(idStationOrigin);
                    } else
                    {
                        stationModel = null;
                    }

                    transport.setStationOrigin(stationModel);

                    if (idStationDestination != null)
                    {
                        idStationDestination = idStations[stationDestinationCombo.getSelectedIndex()];
                        stationModel = controller.getStation(idStationDestination);
                    } else
                    {
                        stationModel = null;
                    }

                    transport.setStationDestination(stationModel);

                    idBike = idBikes[bikeCombo.getSelectedIndex()];
                    bikeModel = controller.getBike(idBike);
                    transport.setBikeModel(bikeModel);

                    if (idWorkshop != null)
                    {
                        idWorkshop = idWorkshops[workshopCombo.getSelectedIndex()];
                        workShopModel = controller.getWorkShop(idWorkshop);
                    } else
                    {
                        workShopModel = null;
                    }

                    transport.setWorkShopModel(workShopModel);

                    int ok = JOptionPane.showConfirmDialog(null, "Confirmer ce nouvel ordre de transport ?", "Confirmation", JOptionPane.YES_NO_OPTION);

                    if (ok == 0) {
                        try
                        {

                            Boolean modification = controller.insertTransportOrder(transport);

                            if (modification)
                            {
                                JOptionPane.showMessageDialog(null,"Nouvel ordre de transport bien ajouté !");
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

            } catch (ConnectionException ex) {
                JOptionPane.showMessageDialog(null, "Erreur de connexion : " + ex.toString(), "Exception", JOptionPane.ERROR_MESSAGE);
                System.out.println("Erreur de connexion : " + ex);
            }
        }
    }

    private class IdListener implements ItemListener {


        @Override
        public void itemStateChanged(ItemEvent ei) {

            idEmployee = idEmployees[employeeCombo.getSelectedIndex()];
            idBike = idBikes[bikeCombo.getSelectedIndex()];
            //System.out.println(idStations[stationIssuanceCombo.getSelectedIndex()]);
            //idStationIssuance = idStations[stationIssuanceCombo.getSelectedIndex()];
            //idStationOrigin = idStations[stationOriginCombo.getSelectedIndex()];
            //idStationDestination = idStations[stationDestinationCombo.getSelectedIndex()];
            idWorkshop = idWorkshops[workshopCombo.getSelectedIndex()];
        }
    }

}
