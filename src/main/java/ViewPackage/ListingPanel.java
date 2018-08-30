package ViewPackage;

import ControllerPackage.Controller;
import ExceptionPackage.ConnectionException;
import ModelPackage.*;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.GregorianCalendar;

public class ListingPanel extends JPanel {

    private Container container;
    private Controller controller;
    private JButton cancelButton,deleteButton,updateButton,returnButton,consultButton,exitButton;
    private JPanel buttonPanel,tablePanel;
    private JTable listTable;
    private LayoutWindow layoutWindow;
    private ArrayList<EmployeeModel> employeeListing = new ArrayList<>();
    private ArrayList<EmployeeModel> employeeSelection = new ArrayList<>();
    private EmployeeModel employeeDelete;
    private ArrayList<SearchModel> searchListing = new ArrayList<>();
    private SearchModel searchModel;
    private SearchTableModel searchTableModel;
    private Integer idEmployee;
    private GregorianCalendar dateBegin,dateEnd;
    private ArrayList<ReparationRecordModel> reparationListing = new ArrayList<>();
    private ReparationRecordModel reparationModel;
    private ReparationTableModel reparationTableModel;
    private Boolean employeeBool,searchBool,reparationBool;

    public ListingPanel(LayoutWindow layoutWindow,Boolean employeeBool,Boolean searchBool,Boolean reparationBool,Integer idEmployee, GregorianCalendar dateBegin, GregorianCalendar dateEnd) throws ConnectionException{

        this.layoutWindow = layoutWindow;
        container = layoutWindow.getContentPane();
        container.removeAll();
        controller = new Controller();
        this.searchBool = searchBool;
        this.employeeBool = employeeBool;
        this.reparationBool = reparationBool;

        // Boutons
        buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());
        buttonPanel.setLayout(new GridLayout(1,2,100,50));
        ButtonListener listener = new ButtonListener();


        if (employeeBool)
        {
            updateButton = new JButton("Modifier ");
            deleteButton = new JButton("Supprimer ");
            cancelButton = new JButton("Annuler ");
            cancelButton.addActionListener(listener);
            updateButton.addActionListener(listener);
            deleteButton.addActionListener(listener);
            buttonPanel.add(cancelButton);
            buttonPanel.add(updateButton);
            buttonPanel.add(deleteButton);

        }

        if (reparationBool)
        {
            consultButton = new JButton("Consulter");
            exitButton = new JButton("Annuler");
            consultButton.addActionListener(listener);
            exitButton.addActionListener(listener);
            buttonPanel.add(exitButton);
            buttonPanel.add(consultButton);
        }

        if (searchBool)
        {
            returnButton = new JButton("Retour ");
            returnButton.setHorizontalAlignment(SwingConstants.CENTER);
            returnButton.addActionListener(listener);
            buttonPanel.add(returnButton);
        }


        // List Table

        tablePanel = new JPanel();
        tablePanel.setLayout(new BorderLayout());

        if (employeeBool)
        {
            employeeListing = controller.employeeListing();
            TableModel modelEmployee = new TableModel(employeeListing);
            listTable = new JTable(modelEmployee);
            listTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
            listTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
            listTable.getSelectionModel().addListSelectionListener(listener);
            listTable.setModel(modelEmployee);

        }

        if (reparationBool)
        {
            reparationListing = controller.reparationListing();
            ReparationTableModel modelReparation = new ReparationTableModel(reparationListing);
            listTable = new JTable(modelReparation);
            listTable.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
            listTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
            listTable.getSelectionModel().addListSelectionListener(listener);
            listTable.setModel(modelReparation);
        }

        if (searchBool)
        {
            this.idEmployee = idEmployee;
            this.dateBegin = dateBegin;
            this.dateEnd = dateEnd;
            searchListing = controller.searchListing(idEmployee,dateBegin,dateEnd);
            SearchTableModel modelSearch = new SearchTableModel(searchListing);
            listTable = new JTable(modelSearch);
            listTable.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
            listTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
            listTable.setModel(modelSearch);

        }

        listTable.getTableHeader().setReorderingAllowed(false);


        tablePanel.add(listTable.getTableHeader(),BorderLayout.NORTH);
        tablePanel.add(listTable,BorderLayout.CENTER);

        JScrollPane barre = new JScrollPane();
        barre.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        barre.setViewportView(listTable);
        barre.setWheelScrollingEnabled(true);
        tablePanel.add(barre);



        container.add(tablePanel,BorderLayout.CENTER);
        container.add(buttonPanel,BorderLayout.SOUTH);

        layoutWindow.getContentPane().repaint();
        layoutWindow.setVisible(true);


    }

    private class ButtonListener implements ActionListener, ListSelectionListener {


        public void actionPerformed(ActionEvent e) {

            try {

                if (e.getSource() == cancelButton) {
                    WelcomePanel welcomePanel = new WelcomePanel(layoutWindow);
                }

                if (e.getSource() == exitButton) {
                    WelcomePanel welcomePanel = new WelcomePanel(layoutWindow);
                }

                if (e.getSource() == deleteButton) {
                    Integer idEmployee = (Integer) listTable.getValueAt(listTable.getSelectedRow(), 0);
                    employeeDelete = new EmployeeModel();
                    employeeDelete = controller.getEmployee(idEmployee);
                    DeletePanel deletePanel = new DeletePanel(layoutWindow,employeeDelete);
                }

                if (e.getSource() == updateButton) {

                    Integer idEmployee = (Integer) listTable.getValueAt(listTable.getSelectedRow(), 0);
                    employeeDelete = new EmployeeModel();
                    employeeDelete = controller.getEmployee(idEmployee);
                    Insert1Panel insert1Panel = new Insert1Panel(layoutWindow,employeeDelete,true,false);

                }

                if (e.getSource() == returnButton) {
                    SearchPanel searchPanel = new SearchPanel(layoutWindow);
                }

                if (e.getSource() == consultButton){
                    Integer idWorkOrder = (Integer) listTable.getValueAt(listTable.getSelectedRow(), 0);
                    reparationModel = new ReparationRecordModel();
                    reparationModel = controller.getReparation(idWorkOrder);
                    ReparationPanel reparationPanel = new ReparationPanel(layoutWindow,reparationModel);
                }

            } catch (ConnectionException ex) {
                JOptionPane.showMessageDialog(null, "Erreur de connexion :" + ex.toString(), "Erreur", JOptionPane.ERROR_MESSAGE);
            }

        }

        public void valueChanged(ListSelectionEvent le) {

            if (employeeBool)
            {
                updateButton.setEnabled(true);
                deleteButton.setEnabled(true);
            }
            if (reparationBool)
            {
                consultButton.setEnabled(true);
                exitButton.setEnabled(true);
            }


        }
    }

}
