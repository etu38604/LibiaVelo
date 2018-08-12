package ModelPackage;

import javax.swing.table.AbstractTableModel;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class TableModel extends AbstractTableModel {

    private ArrayList<EmployeeModel> employeeArray;
    private ArrayList<String> columnLabel;

    public TableModel(ArrayList<EmployeeModel> employeeArray)
    {
        employeeArray.forEach((employeeModel) -> {

        });


        //Nomination des colonnes
        this.employeeArray = employeeArray;
        columnLabel = new ArrayList <String>();
        columnLabel.add("ID");
        columnLabel.add("Nom");
        columnLabel.add("Prénom");
        columnLabel.add("Initial prénom supp");
        columnLabel.add("Date embauche");
        columnLabel.add("Téléphonne Privé");
        columnLabel.add("Téléphonne Professionnel");
        columnLabel.add("Email");
        columnLabel.add("Date naissance");
        columnLabel.add("Temps partiel");
        columnLabel.add("Localite");
        columnLabel.add("Rue");
        columnLabel.add("Numéro de rue");
        columnLabel.add("Fonction");
        columnLabel.add("Permi plateau");
        columnLabel.add("EstResponsable");
        columnLabel.add("Numéro de Atelier");
        columnLabel.add("Station attribuée");
        columnLabel.add("Est sous la responsabilité de");

    }

    //Retourne le nombre de colonnne
    public int getColumnCount()
    {
        return columnLabel.size();
    }

    //Retourne le nom de la colonne
    public String getColumnName (int column)
    {
        return columnLabel.get(column);
    }

    public Object getValueAt(int ligne, int col)
    {
        EmployeeModel employeeModel = employeeArray.get(ligne);
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        String dateHiring = dateFormat.format(employeeModel.getDateHiring().getTime());
        String birthday = dateFormat.format(employeeModel.getBirthday().getTime());

        switch (col)
        {
            //En fonction de la colonne selectionnée retourne la valeur
            case 0: return employeeModel.getIdEmployee();
            case 1: return employeeModel.getLastName();
            case 2: return employeeModel.getFirstName();
            case 3: return employeeModel.getInitialNameSupp();
            case 4: return dateHiring;
            case 5: return employeeModel.getPhonePrivate();
            case 6: return employeeModel.getPhonePro();
            case 7: return employeeModel.getMail();
            case 8: return birthday;
            case 9: return employeeModel.getPartTimeWork()? "oui" : "non";
            case 10 : return employeeModel.getLocalityModel().getLabelLocality();
            case 11: return employeeModel.getStreet();
            case 12 : return employeeModel.getStreetNumber();
            case 13 : return employeeModel.getWorkType();
            case 14 : return employeeModel.getDriverSpecialLicense()? "oui" : "non";
            case 15 : return employeeModel.getZoneInCharge()? "oui" : "non";
            case 16 : return employeeModel.getWorkShopModel().getPlace();
            case 17 : return employeeModel.getStationModel().getLabelStation();
            case 18 : return employeeModel.getInCharge().getLastName() + " "+ employeeModel.getInCharge().getFirstName();
            default : return null;
        }
    }
    //Retourne la classe de la colonne selectionnée
    public Class getColumnClass(int col)
    {
        Class c;
        switch(col)
        {
            case 0: c = Integer.class;
                break;
            case 1: c = String.class;
                break;
            case 2: c = String.class;
                break;
            case 3: c = String.class;
                break;
            case 4: c = String.class;
                break;
            case 5: c = Integer.class;
                break;
            case 6: c = Integer.class;
                break;
            case 7: c = String.class;
                break;
            case 8: c = String.class;
                break;
            case 9: c = String.class;
                break;
            case 10: c = String.class;
                break;
            case 11: c = String.class;
                break;
            case 12: c = String.class;
                break;
            case 13: c = String.class;
                break;
            case 14: c = String.class;
                break;
            case 15: c = String.class;
                break;
            case 16: c = String.class;
                break;
            case 17: c = String.class;
                break;
            case 18: c = String.class;
                break;
            default: c= String.class;
        }
        return c;
    }
    //Retourne le nombre de ligne
    public int getRowCount()
    {
        return employeeArray.size();
    }
}
