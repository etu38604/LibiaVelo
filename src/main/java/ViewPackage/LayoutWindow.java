package ViewPackage;

import ExceptionPackage.ConnectionException;
import ModelPackage.EmployeeModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class LayoutWindow extends JFrame {

    private Container myContainer;
    private JMenu menuMenu,menuWorking,menuSearch;
    private JMenuBar menuBar;
    private JMenuItem homePage,insert,listing,exit,searchItem,jobListing;
    private WelcomePanel welcomePanel;
    private Insert1Panel insert1Panel;
    private SearchPanel searchPanel;
    private ListingPanel listingPanel;
    private EmployeeModel employeeModel;


    public LayoutWindow(){

        super("Libia Velo");

        addWindowListener(new WindowAdapter()
        {
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });

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
        jobListing = new JMenuItem("Ordres de réparation en attentes");
        jobListing.addActionListener(barListener);
        menuWorking.add(jobListing);
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
                    Boolean returnBool = false;
                    insert1Panel = new Insert1Panel(LayoutWindow.this,employeeModel,upDateBoll,returnBool);
                }
                if (e.getSource() == searchItem)
                    searchPanel = new SearchPanel(LayoutWindow.this);
                if (e.getSource() == listing)
                    listingPanel = new ListingPanel(LayoutWindow.this,true,false,false,null,null,null);

                if(e.getSource() == jobListing)
                    listingPanel = new ListingPanel(LayoutWindow.this,false,false,true,null,null,null);

            }
            catch (ConnectionException ex)
            {
                JOptionPane.showMessageDialog(null,"Erreur de connexion : " + ex.toString(),"Exception",JOptionPane.ERROR_MESSAGE);
                System.out.println("Erreur de connexion : " + ex);
            }


        }
    }



}
