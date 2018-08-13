package ViewPackage;

import ExceptionPackage.ConnectionException;
import ModelPackage.EmployeeModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LayoutWindow extends JFrame {

    private Container myContainer;
    private JMenu menuMenu,menuWorking,menuSearch;
    private JMenuBar menuBar;
    private JMenuItem homePage,insert,listing,exit,searchItem;
    private WelcomePanel welcomePanel;
    private Insert1Panel insert1Panel;
    private SearchPanel searchPanel;
    private ListingPanel listingPanel;
    private EmployeeModel employeeModel;


    public LayoutWindow(){

        super("Libia Velo");

        // Region MenuBare

        this.employeeModel = new EmployeeModel();

        menuBar = new JMenuBar();
        setJMenuBar(menuBar);

        BarListener barListener = new BarListener();

        menuMenu = new JMenu("Menu");
        menuBar.add(menuMenu);
        homePage = new JMenuItem("Accueil");
        homePage.addActionListener(barListener);
        menuMenu.add(homePage);
        insert = new JMenuItem("Ajout");
        insert.addActionListener(barListener);
        menuMenu.add(insert);
        listing = new JMenuItem("Listing");
        listing.addActionListener(barListener);
        menuMenu.add(listing);
        menuMenu.addSeparator();
        exit = new JMenuItem("Fermer");
        exit.addActionListener(barListener);
        menuMenu.add(exit);

        menuWorking = new JMenu("Metier");
        menuBar.add(menuWorking);

        menuSearch = new JMenu("Recherche");
        searchItem = new JMenuItem("Livreurs");
        searchItem.addActionListener(barListener);
        menuSearch.add(searchItem);
        menuBar.add(menuSearch);




        // Region Dimension et panneaux

        this.setBounds(100,100,900,600);


        myContainer = this.getContentPane();


       welcomePanel = new WelcomePanel(LayoutWindow.this);




        this.setVisible(true);
    }

    private class BarListener implements ActionListener{

        public void actionPerformed(ActionEvent e) {

            try {

                if (e.getSource() == homePage)
                    welcomePanel = new WelcomePanel(LayoutWindow.this);
                if (e.getSource() == exit)
                    System.exit(0);
                if (e.getSource() == insert)
                {
                    EmployeeModel employeeModel = new EmployeeModel();
                    Boolean upDateBoll = false;
                    insert1Panel = new Insert1Panel(LayoutWindow.this,employeeModel,upDateBoll);
                }
                if (e.getSource() == searchItem)
                    searchPanel = new SearchPanel(LayoutWindow.this);
                if (e.getSource() == listing)
                    listingPanel = new ListingPanel(LayoutWindow.this);
            }
            catch (ConnectionException ex) {
                System.out.println("Erreur de connexion : " + ex);
            }


        }
    }



}
