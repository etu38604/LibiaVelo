package ViewPackage;

import javax.swing.*;
import java.awt.*;

public class WelcomePanel extends JPanel {

    private JLabel introductionLabel;
    private LayoutWindow layoutWindow;
    private Container container;

    public WelcomePanel(LayoutWindow layoutWindow){

        this.layoutWindow = layoutWindow;
        container = layoutWindow.getContentPane();
        container.removeAll();

        introductionLabel = new JLabel(
                "<html><body><div color='black'><center>Bienvenue sur l'application de gestion des employés de \"Li Bia Vélo\" </center><br/" +
                        "<p>Cette dernière permet :</p>" +
                        "<ul>" +
                        "<li>d'ajouter un employé</li>" +
                        "<li>de modifier les informations d'un employé</li>" +
                        "<li>de supprimer un employé</li>" +
                        "<li>de lister les différents employés de l'entreprise</li>" +
                        "</ul>" +
                        "<p>Ainsi que d'effectuer une recherche concernant les transporteurs</p></div></body></html>");



        this.setLayout(new FlowLayout());
        introductionLabel.setHorizontalAlignment(SwingConstants.CENTER);
        this.add(introductionLabel);

        container.add(introductionLabel,BorderLayout.NORTH);
        layoutWindow.getContentPane().repaint();
        layoutWindow.setVisible(true);

    }
}
