package ViewPackage;

import DataAccessPackage.EmployeeDataAccess;
import ExceptionPackage.ConnectionException;
import ModelPackage.EmployeeModel;
import ModelPackage.TableModel;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class ListingPanel extends JPanel {

    private Container container;
    private JButton cancelButton,deleteButton,updateButton;
    private JPanel buttonPanel,tablePanel;
    private JTable listTable;
    private LayoutWindow layoutWindow;
    private ArrayList<EmployeeModel> employeeListing = new ArrayList<>();
    private ArrayList<EmployeeModel> employeeSelection = new ArrayList<>();
    private EmployeeDataAccess employeeDataAccess;
    private EmployeeModel employeeDelete;

    public ListingPanel(LayoutWindow layoutWindow) throws ConnectionException{

        this.layoutWindow = layoutWindow;
        container = layoutWindow.getContentPane();
        container.removeAll();

        // Boutons
        buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());
        updateButton = new JButton("Modifier ");
        deleteButton = new JButton("Supprimer ");
        cancelButton = new JButton("Annuler ");

        ButtonListener listener = new ButtonListener();
        updateButton.addActionListener(listener);
        deleteButton.addActionListener(listener);
        cancelButton.addActionListener(listener);


        buttonPanel.setLayout(new GridLayout(1,2,100,50));
        buttonPanel.add(cancelButton);
        buttonPanel.add(updateButton);
        buttonPanel.add(deleteButton);

        // List Table

        tablePanel = new JPanel();
        tablePanel.setLayout(new BorderLayout());

        EmployeeDataAccess employeeDataAccess = new EmployeeDataAccess();
        employeeListing = employeeDataAccess.employeeListing();
        TableModel model = new TableModel(employeeListing);
        listTable = new JTable(model);
        listTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        listTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        listTable.getSelectionModel().addListSelectionListener(listener);
        listTable.setModel(model);
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
                if (e.getSource() == deleteButton) {
                    String idEmployee = listTable.getValueAt(listTable.getSelectedRow(), 0).toString();
                    EmployeeModel employeeDelete = employeeDataAccess.getEmployee(2);
                    System.out.println(employeeDelete.getLastName());
                    DeletePanel DeletePanel = new DeletePanel(layoutWindow,employeeDelete);
                }

            } catch (ConnectionException ex) {
                JOptionPane.showMessageDialog(null, "Erreur de connexion :" + ex.toString(), "Erreur", JOptionPane.ERROR_MESSAGE);
            }

        }

        public void valueChanged(ListSelectionEvent le) {
            updateButton.setEnabled(true);
            deleteButton.setEnabled(true);
        }
    }

}
