package ModelPackage;

import javax.swing.table.AbstractTableModel;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class SearchTableModel extends AbstractTableModel {

    private ArrayList<SearchModel> searchArray;
    private ArrayList<String> columnLabel;

    public SearchTableModel(ArrayList<SearchModel> searchArray)
    {
        searchArray.forEach((searchModel) -> {

        });


        //Nomination des colonnes
        this.searchArray = searchArray;
        columnLabel = new ArrayList <String>();
        columnLabel.add("Date de Transport"); //0
        columnLabel.add("Numéro de vélo");
        columnLabel.add("Station émettrice de l'ordre");
        columnLabel.add("Rue de la station");
        columnLabel.add("Coordonnée GPS de la station");
        columnLabel.add("Localité de la station");
        columnLabel.add("Code postal de la localité"); //6


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
        SearchModel searchModel = searchArray.get(ligne);
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        String dateTransport = dateFormat.format(searchModel.getTransportOrderModel().getDateTransport().getTime());

        switch (col)
        {
            //En fonction de la colonne selectionnée retourne la valeur
            case 0: return dateTransport;
            case 1: return searchModel.getBikeModel().getIdBike();
            case 2: return searchModel.getStationModel().getLabelStation();
            case 3: return searchModel.getStationModel().getStreet();
            case 4: return searchModel.getStationModel().getCoordGPS();
            case 5: return searchModel.getLocalityModel().getLabelLocality();
            case 6: return searchModel.getLocalityModel().getPostalCode();

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

            default: c= String.class;
        }
        return c;
    }
    //Retourne le nombre de ligne
    public int getRowCount()
    {
        return searchArray.size();
    }
}
