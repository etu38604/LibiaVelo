package ViewPackage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SearchPanel extends JPanel {

    private Container container;
    private JLabel startLabel,endLabel,nameLabel,textLabel,blankLabel;
    private JSpinner startSpinner,endSpinner;
    private JComboBox nameCombo;
    private JButton cancelButton,searchButton;
    private JPanel panelButton,informationsPanel,textPanel;
    private LayoutWindow layoutWindow;

    public SearchPanel(LayoutWindow layoutWindow){

        this.layoutWindow = layoutWindow;
        container = layoutWindow.getContentPane();
        container.removeAll();

        // Boutons
        panelButton = new JPanel();
        panelButton.setLayout(new FlowLayout());
        cancelButton = new JButton("Annuler");
        searchButton = new JButton("Rechercher");

        ButtonListener listener = new ButtonListener();
        cancelButton.addActionListener(listener);
        searchButton.addActionListener(listener);

        panelButton.setLayout(new GridLayout(1,2,100,100));
        panelButton.add(cancelButton);
        panelButton.add(searchButton);

        // Informations panel

        informationsPanel = new JPanel();
        informationsPanel.setLayout(new GridLayout(3,4,10,120));

        startLabel = new JLabel("Date de début : ");
        startLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        informationsPanel.add(startLabel);
        startSpinner = new JSpinner();
        startSpinner.setModel(new SpinnerDateModel());
        JSpinner.DateEditor editorStart = new JSpinner.DateEditor(startSpinner,"dd/MM/yyyy");
        startSpinner.setEditor(editorStart);
        informationsPanel.add(startSpinner);

        endLabel = new JLabel("Date de fin : ");
        endLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        informationsPanel.add(endLabel);
        endSpinner = new JSpinner();
        endSpinner.setModel(new SpinnerDateModel());
        JSpinner.DateEditor editorEnd = new JSpinner.DateEditor(endSpinner,"dd/MM/yyyy");
        endSpinner.setEditor(editorEnd);
        informationsPanel.add(endSpinner);

        nameLabel = new JLabel("Nom du livreur : ");
        nameLabel.setHorizontalAlignment(SwingConstants.RIGHT);  //ComboBox livreur
        informationsPanel.add(nameLabel);
        String [] values = {"Pierre","Paul"};
        nameCombo = new JComboBox(values);
        nameCombo.setSelectedItem("Pierre");
        nameCombo.setMaximumRowCount(5);
        nameCombo.setAutoscrolls(true);
        informationsPanel.add(nameCombo);


        //text Panel

        textPanel = new JPanel();
        textPanel.setLayout(new FlowLayout());

        textLabel = new JLabel(
                "<html><body><div color='black'><center>Recherche : Livraison </center><br/" +
                        "<p>Cette recherche permet d'afficher toutes les informations connues </p><br"+
                        "<p>sur des vélos transportés par un transporteur entre deux dates données </p><br"+
                        "<p></p><br</div></body></html>");

        textLabel.setHorizontalAlignment(SwingConstants.CENTER);
        textPanel.add(textLabel);

        container.add(textPanel,BorderLayout.NORTH);
        container.add(informationsPanel,BorderLayout.CENTER);
        container.add(panelButton,BorderLayout.SOUTH);

        layoutWindow.getContentPane().repaint();
        layoutWindow.setVisible(true);

    }

    private class ButtonListener implements ActionListener{


        public void actionPerformed(ActionEvent e) {

            if (e.getSource() == cancelButton){
                WelcomePanel welcomePanel = new WelcomePanel(layoutWindow);
            }
        }
    }
}
