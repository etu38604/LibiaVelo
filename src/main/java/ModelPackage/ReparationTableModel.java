package ModelPackage;

import javax.swing.table.AbstractTableModel;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class ReparationTableModel extends AbstractTableModel {

    private ArrayList<ReparationRecordModel> reparationArray;
    private ArrayList<String> columnLabel;

    public ReparationTableModel(ArrayList<ReparationRecordModel> reparationArray)
    {
        reparationArray.forEach((reparationRecordModel) -> {

        });


        //Nomination des colonnes
        this.reparationArray = reparationArray;
        columnLabel = new ArrayList <String>();
        columnLabel.add("ID"); //0
        columnLabel.add("Confirmé");
        columnLabel.add("Date de début");
        columnLabel.add("Date de fin");
        columnLabel.add("Remarques");
        columnLabel.add("Ordre de travail"); //5
        columnLabel.add("Déclassé");
        columnLabel.add("Numéro du vélo");
        columnLabel.add("Atelier"); //8

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
        ReparationRecordModel reparationRecordModel = reparationArray.get(ligne);
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        String dateBegin = dateFormat.format(reparationRecordModel.getDateBegin().getTime());
        String dateEnd = dateFormat.format(reparationRecordModel.getDateEnd().getTime());

        switch (col)
        {
            //En fonction de la colonne selectionnée retourne la valeur
            case 0: return reparationRecordModel.getIdReparation();
            case 1: return reparationRecordModel.getValidate() ? "oui" : "non";
            case 2: return dateBegin;
            case 3: return dateEnd;
            case 4: return reparationRecordModel.getNote();
            case 5: return reparationRecordModel.getWorkOrder();
            case 6: return reparationRecordModel.getDownGraded() ? "oui" : "non";
            case 7: return reparationRecordModel.getBikeModel().getIdBike();
            case 8: return reparationRecordModel.getWorkShopModel().getPlace();

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
            case 6: c = String.class;
                break;
            case 7: c = Integer.class;
                break;
            case 8: c = String.class;
                break;

            default: c= String.class;
        }
        return c;
    }
    //Retourne le nombre de ligne
    public int getRowCount()
    {
        return reparationArray.size();
    }


}
