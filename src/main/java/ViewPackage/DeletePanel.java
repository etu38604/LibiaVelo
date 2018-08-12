package ViewPackage;

import ExceptionPackage.ConnectionException;
import ModelPackage.EmployeeModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class DeletePanel extends JPanel {

    private Container container;
    private JPanel buttonPanel,informationsPanel;
    private JButton cancelButton,deleteButton;
    private JLabel identifiantLabel,fonctionLabel,lastNameLabel,firstNameLabel;
    private JTextField identifiantText,fonctionText,lastNameText,firstNameText;
    private LayoutWindow layoutWindow;
    private EmployeeModel employeeDelete;

    public DeletePanel(LayoutWindow layoutWindow, EmployeeModel employeeDelete) throws ConnectionException {

        this.layoutWindow = layoutWindow;
        container = layoutWindow.getContentPane();
        container.removeAll();

        // Button Panel
        buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());
        cancelButton = new JButton("Annuler");
        deleteButton = new JButton("Supprimer");

        ButtonListener listener = new ButtonListener();
        cancelButton.addActionListener(listener);
        deleteButton.addActionListener(listener);

        buttonPanel.setLayout(new GridLayout(1,2,100,100));
        buttonPanel.add(cancelButton);
        buttonPanel.add(deleteButton);

        // Informations Panel

        this.employeeDelete = employeeDelete;

        informationsPanel = new JPanel();
        informationsPanel.setLayout(new GridLayout(4,2,10,100));

        identifiantLabel = new JLabel("Identifiant : ");
        identifiantLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        informationsPanel.add(identifiantLabel);
        identifiantText = new JTextField(employeeDelete.getIdEmployee());
        identifiantText.setHorizontalAlignment(SwingConstants.LEFT);
        identifiantText.setEditable(false);
        informationsPanel.add(identifiantText);

        fonctionLabel = new JLabel("Fonction : ");
        fonctionLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        informationsPanel.add(fonctionLabel);
        fonctionText = new JTextField(employeeDelete.getWorkType());
        fonctionText.setHorizontalAlignment(SwingConstants.LEFT);
        fonctionText.setEditable(false);
        informationsPanel.add(fonctionText);

        lastNameLabel = new JLabel(" Nom : ");
        lastNameLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        informationsPanel.add(lastNameLabel);
        lastNameText = new JTextField(employeeDelete.getLastName());
        lastNameText.setHorizontalAlignment(SwingConstants.LEFT);
        lastNameText.setEditable(false);
        informationsPanel.add(lastNameText);

        firstNameLabel = new JLabel("Prénom : ");
        firstNameLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        informationsPanel.add(firstNameLabel);
        firstNameText = new JTextField(employeeDelete.getFirstName());
        firstNameText.setHorizontalAlignment(SwingConstants.LEFT);
        firstNameText.setEditable(false);
        informationsPanel.add(firstNameText);

        container.add(informationsPanel,BorderLayout.CENTER);
        container.add(buttonPanel,BorderLayout.SOUTH);

        layoutWindow.getContentPane().repaint();
        layoutWindow.setVisible(true);


    }

    private class ButtonListener implements ActionListener{


        public void actionPerformed(ActionEvent e) {

            try {

                if (e.getSource() == cancelButton) {
                    ListingPanel listingPanel = new ListingPanel(layoutWindow);
                }

                if (e.getSource() == deleteButton) {
                    int ok = JOptionPane.showConfirmDialog(null, "Voulez vous vraiment supprimer cet employé ?", "Confirmation", JOptionPane.YES_NO_OPTION);
                    System.out.println(ok);

                    if (ok == 0) {
                        WelcomePanel welcomePanel = new WelcomePanel(layoutWindow);
                    }


                }
            }
            catch (ConnectionException ex){
                System.out.println("Erreur de connexion : " + ex);
            }

        }
    }
}
