package ViewPackage;

import ControllerPackage.Controller;
import ExceptionPackage.ConnectionException;
import ModelPackage.EmployeeModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DeletePanel extends JPanel {

    private Container container;
    private Controller controller;
    private JPanel buttonPanel,informationsPanel,titlePanel,notePanel,centerPanel;
    private JButton cancelButton,deleteButton;
    private JLabel identifiantLabel,fonctionLabel,lastNameLabel,firstNameLabel,titleLabel,noteLabel;
    private JTextField identifiantText,fonctionText,lastNameText,firstNameText;
    private LayoutWindow layoutWindow;
    private EmployeeModel employeeDelete;

    public DeletePanel(LayoutWindow layoutWindow, EmployeeModel employeeDelete) throws ConnectionException {

        this.layoutWindow = layoutWindow;
        container = layoutWindow.getContentPane();
        container.removeAll();
        controller = new Controller();

        titlePanel = new JPanel();
        titlePanel.setLayout(new FlowLayout());
        titleLabel = new JLabel("  Suppression d'un employé  ");
        titlePanel.add(titleLabel);

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
        informationsPanel.setLayout(new GridLayout(4,2,10,25));

        identifiantLabel = new JLabel("Identifiant : ");
        identifiantLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        informationsPanel.add(identifiantLabel);
        identifiantText = new JTextField(Integer.toString(employeeDelete.getIdEmployee()));
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

        notePanel = new JPanel();
        notePanel.setLayout(new FlowLayout());
        noteLabel = new JLabel(" * Un grand patron ne peut être supprimé ");
        noteLabel.setHorizontalAlignment(SwingConstants.LEFT);
        notePanel.add(noteLabel);

        centerPanel = new JPanel();
        centerPanel.setLayout(new BorderLayout(20,50));
        centerPanel.add(informationsPanel,BorderLayout.CENTER);
        centerPanel.add(notePanel,BorderLayout.SOUTH);

        container.add(titlePanel,BorderLayout.NORTH);
        container.add(centerPanel,BorderLayout.CENTER);
        container.add(buttonPanel,BorderLayout.SOUTH);

        layoutWindow.getContentPane().repaint();
        layoutWindow.setVisible(true);


    }

    private class ButtonListener implements ActionListener{


        public void actionPerformed(ActionEvent e) {

            try {

                if (e.getSource() == cancelButton) {
                    ListingPanel listingPanel = new ListingPanel(layoutWindow,true,false,false,null,null,null);
                }

                if (e.getSource() == deleteButton) {
                    int ok = JOptionPane.showConfirmDialog(null, "Voulez vous vraiment supprimer cet employé ?", "Confirmation", JOptionPane.YES_NO_OPTION);

                    if (ok == 0) {

                        Boolean modification = controller.deleteEmployee(employeeDelete.getIdEmployee());

                        if (modification)
                        {
                            JOptionPane.showMessageDialog(null,"Employe " + employeeDelete.getLastName()+ " "+employeeDelete.getFirstName()+" bien supprimé !");
                            WelcomePanel welcomePanel = new WelcomePanel(layoutWindow);
                        } else
                        {
                            JOptionPane.showMessageDialog(null,"Erreur lors de la suppression","Erreur",JOptionPane.ERROR_MESSAGE);
                        }

                    }


                }
            }
            catch (ConnectionException ex)
            {
                JOptionPane.showMessageDialog(null,"Erreur de connexion : " + ex.toString(),"Exception",JOptionPane.ERROR_MESSAGE);
                System.out.println("Erreur de connexion : " + ex);
            }

        }
    }
}
