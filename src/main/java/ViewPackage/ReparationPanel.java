package ViewPackage;

import ControllerPackage.Controller;
import ExceptionPackage.ConnectionException;
import ExceptionPackage.ValidatorException;
import ModelPackage.BikeModel;
import ModelPackage.ReparationRecordModel;
import ModelPackage.WorkShopModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.Date;
import java.util.ArrayList;
import java.util.GregorianCalendar;

public class ReparationPanel extends JPanel {

    private Container container;
    private Controller controller;
    private LayoutWindow layoutWindow;
    private JPanel buttonPanel,informationsPanel,notePanel,idPanel;
    private JSpinner dateBeginSpinner,dateEndSpinner;
    private SpinnerDateModel dateBeginModel,dateEndModel;
    private Date beginDate,endDate;
    private GregorianCalendar beginGreg,endGreg;
    private JTextField idText,noteText;
    private JComboBox workShopCombo,workOrderCombo,bikeCombo;
    private JCheckBox downGradedCheck;
    private JLabel idLabel,workShopLabel,workOrderLabel,bikeLabel,noteLabel,dateBeginLabel,dateEndLabel;
    private JButton validateButton,deleteButton, returnButton;
    private ReparationRecordModel reparation;
    private ArrayList<WorkShopModel> workShopListing;
    private ArrayList<BikeModel> bikeListing;
    private BikeModel bikeModel;
    private WorkShopModel workShopModel;
    private Integer [] idBikes,idWorkShops;
    private Integer idBike,idWorkShop;
    private String [] workOrderList = {"Crevaison","Freins","Rayons","Cadre","Selle"};

    public ReparationPanel(LayoutWindow layoutWindow,ReparationRecordModel reparation) throws ConnectionException {


        this.layoutWindow = layoutWindow;
        container = layoutWindow.getContentPane();
        container.removeAll();
        controller = new Controller();
        this.reparation = reparation;

        // Buttons

        buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());
        returnButton = new JButton("Retour");
        validateButton = new JButton("Valider");
        deleteButton = new JButton("Supprimer");

        ButtonListener listener = new ButtonListener();
        returnButton.addActionListener(listener);
        validateButton.addActionListener(listener);
        deleteButton.addActionListener(listener);


        buttonPanel.setLayout(new GridLayout(1,3,50,100));
        buttonPanel.add(returnButton);
        buttonPanel.add(validateButton);
        buttonPanel.add(deleteButton);

        // idPanel panel


        idPanel = new JPanel();
        idPanel.setLayout(new FlowLayout(5));


        idLabel = new JLabel("Numéro : ");
        idLabel.setHorizontalAlignment(SwingConstants.LEFT);
        idPanel.add(idLabel);
        idText = new JTextField(reparation.getIdReparation().toString());
        idText.setEditable(false);
        idPanel.add(idText);


        // informations panel

        informationsPanel = new JPanel();
        informationsPanel.setLayout(new GridLayout(7,3,50,50));

        dateBeginLabel = new JLabel("Date de début * : ");
        dateBeginLabel.setHorizontalAlignment(SwingConstants.RIGHT);   //JSpinner Date
        informationsPanel.add(dateBeginLabel);
        dateBeginSpinner = new JSpinner();
        dateBeginModel = new SpinnerDateModel();
        beginDate = new Date(reparation.getDateBegin().getTimeInMillis());
        dateBeginModel.setValue(beginDate);
        dateBeginSpinner.setModel(dateBeginModel);
        JSpinner.DateEditor editor = new JSpinner.DateEditor(dateBeginSpinner,"dd/MM/yyyy");
        dateBeginSpinner.setEditor(editor);

        informationsPanel.add(dateBeginSpinner);

        dateEndLabel = new JLabel("Date de Fin * : ");
        dateEndLabel.setHorizontalAlignment(SwingConstants.RIGHT);   //JSpinner Date
        informationsPanel.add(dateEndLabel);
        dateEndSpinner = new JSpinner();
        dateEndModel = new SpinnerDateModel();
        endDate = new Date(reparation.getDateEnd().getTimeInMillis());
        dateEndModel.setValue(endDate);
        dateEndSpinner.setModel(dateEndModel);
        JSpinner.DateEditor editorEnd = new JSpinner.DateEditor(dateEndSpinner,"dd/MM/yyyy");
        dateEndSpinner.setEditor(editorEnd);
        informationsPanel.add(dateEndSpinner);

        IdListener idListener = new IdListener();

        bikeLabel = new JLabel("Numéro du Vélo : ");
        bikeLabel.setHorizontalAlignment(SwingConstants.RIGHT);  //ComboBox Bike
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
        bikeCombo.setSelectedItem(reparation.getBikeModel().getIdBike());
        bikeCombo.setMaximumRowCount(5);
        bikeCombo.setAutoscrolls(true);
        bikeCombo.addItemListener(idListener);
        informationsPanel.add(bikeLabel);
        informationsPanel.add(bikeCombo);


        workShopLabel = new JLabel("Atelier : ");
        workShopLabel.setHorizontalAlignment(SwingConstants.RIGHT);  //ComboBox Workshop
        workShopCombo = new JComboBox();
        workShopListing = controller.getAllWorkShop();
        idWorkShops = new Integer[workShopListing.size()];
        int i = 0;
        for (WorkShopModel AllWorkShop : workShopListing)
        {
            workShopCombo.addItem(AllWorkShop.getPlace());
            idWorkShops[i] = AllWorkShop.getIdWorkShop();
            i++;
        }
        workShopCombo.setSelectedItem(reparation.getWorkShopModel().getPlace());
        workShopCombo.setMaximumRowCount(5);
        workShopCombo.setAutoscrolls(true);
        workShopCombo.addItemListener(idListener);
        informationsPanel.add(workShopLabel);
        informationsPanel.add(workShopCombo);


        workOrderLabel = new JLabel("Ordre de travail* : ");
        workOrderLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        informationsPanel.add( workOrderLabel);
        workOrderCombo = new JComboBox(workOrderList);     // ComboBox WorkOrder
        workOrderCombo.setMaximumRowCount(5);
        workOrderCombo.setAutoscrolls(true);
        workOrderCombo.setSelectedItem(reparation.getWorkOrder());
        informationsPanel.add(workOrderCombo);

        downGradedCheck = new JCheckBox("Vélo déclassé");       //CheckBox DownGraded
        downGradedCheck.setHorizontalAlignment(SwingConstants.CENTER);
        if ((reparation.getDownGraded() != null) && (reparation.getDownGraded()))
        {
            downGradedCheck.setSelected(true);
        }



        noteLabel = new JLabel("Remarque : ");
        noteLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        noteText = new JTextField(reparation.getNote());


        informationsPanel.add( noteLabel);
        informationsPanel.add(noteText);
        informationsPanel.add(downGradedCheck);

        container.add(idPanel,BorderLayout.NORTH);
        container.add(informationsPanel,BorderLayout.CENTER);
        container.add(buttonPanel,BorderLayout.SOUTH);



        layoutWindow.getContentPane().repaint();
        layoutWindow.setVisible(true);




    }

    private class ButtonListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {

            try {

                if (e.getSource() == returnButton)
                {
                    ListingPanel listingPanel = new ListingPanel(layoutWindow,false,false,true,null,null,null);
                }

                if (e.getSource() == deleteButton)
                {
                    int ok = JOptionPane.showConfirmDialog(null, "Voulez vous vraiment supprimer cet ordre de réparation ?", "Confirmation", JOptionPane.YES_NO_OPTION);

                    if (ok == 0) {

                        Boolean modification = controller.deleteReparation(reparation.getIdReparation());

                        if (modification)
                        {
                            JOptionPane.showMessageDialog(null,"Ordre de réparation " + reparation.getIdReparation()+" bien supprimé !");
                            ListingPanel listingPanel = new ListingPanel(layoutWindow,false,false,true,null,null,null);
                        } else
                        {
                            JOptionPane.showMessageDialog(null,"Erreur lors de la suppression","Erreur",JOptionPane.ERROR_MESSAGE);
                        }

                    }
                }

                if (e.getSource() == validateButton)
                {
                    beginGreg = new GregorianCalendar();
                    beginGreg.setTime((java.util.Date) dateBeginSpinner.getValue());
                    reparation.setDateBegin(beginGreg);

                    endGreg = new GregorianCalendar();
                    endGreg.setTime((java.util.Date) dateEndSpinner.getValue());
                    reparation.setDateEnd(endGreg);

                    idBike = idBikes[bikeCombo.getSelectedIndex()];
                    bikeModel = controller.getBike(idBike);
                    reparation.setBikeModel(bikeModel);

                    idWorkShop = idWorkShops[workShopCombo.getSelectedIndex()];
                    workShopModel = controller.getWorkShop(idWorkShop);
                    reparation.setWorkShopModel(workShopModel);

                    if(noteText.getText().toString().isEmpty())
                    {
                        reparation.setNote(null);
                    } else
                    {
                        reparation.setNote(noteText.getText().toString());
                    }

                    Integer idworkOrder = workOrderCombo.getSelectedIndex();
                    reparation.setWorkOrder(workOrderList[idworkOrder]);


                    int ok = JOptionPane.showConfirmDialog(null, "Confirmer la validation de cet ordre de réparation ?", "Confirmation", JOptionPane.YES_NO_OPTION);

                    if (ok == 0) {

                        try
                        {
                            reparation.setValidate(true);
                            Boolean modification = controller.updateReparation(reparation);

                            if (modification)
                            {
                                JOptionPane.showMessageDialog(null,"Ordre de réparation " + reparation.getIdReparation()+" bien validé !");
                                TransportPanel transportPanel = new TransportPanel(layoutWindow,reparation,true);
                            } else
                            {
                                JOptionPane.showMessageDialog(null,"Erreur lors de la validation","Erreur",JOptionPane.ERROR_MESSAGE);
                            }
                        }catch (ValidatorException ve)
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

            idBike = idBikes[bikeCombo.getSelectedIndex()];
            idWorkShop = idWorkShops[workShopCombo.getSelectedIndex()];
        }
    }

}
