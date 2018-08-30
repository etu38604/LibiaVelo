package DataAccessPackage;

import ExceptionPackage.ConnectionException;
import ModelPackage.BikeModel;
import ModelPackage.EntrepriseModel;
import ModelPackage.StationModel;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.GregorianCalendar;

public class BikeDataAccess {

    private Connection connection;

    public BikeDataAccess () throws ConnectionException {

        connection = ConnectionDataAccess.getInstance();
    }

    public ArrayList<BikeModel> getAllBike() throws ConnectionException {

        String sql = "SELECT b.id, b.isDamaged,b.datePurchase,s.id as idStation, e.id as idEntreprise FROM bike b " +
                        "LEFT JOIN station s ON b.id_Station = s.id " +
                        "LEFT JOIN entreprise e ON b.id_Entreprise = e.id " +
                            "ORDER BY b.id ;";

        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet data = statement.executeQuery();
            ArrayList<BikeModel> allBikeArray = new ArrayList<>();

            while (data.next()) {

                BikeModel bike = new BikeModel();
                GregorianCalendar datePurchase = new GregorianCalendar();
                StationModel station = new StationModel();
                EntrepriseModel entreprise = new EntrepriseModel();

                bike.setIdBike(data.getInt("id"));
                bike.setIsDamaged(data.getBoolean("isDamaged"));
                //Conversion Date to Gregorian
                datePurchase.setTime(data.getDate("datePurchase"));
                bike.setDatePurchase(datePurchase);

                station.setIdStation(data.getInt("idStation"));
                bike.setStationModel(station);

                entreprise.setIdEntreprise(data.getInt("idEntreprise"));
                bike.setEntrepriseModel(entreprise);

                allBikeArray.add(bike);

            }
            statement.close();
            data.close();
            return allBikeArray;

        }

        catch (SQLException e)
        {
            throw new ConnectionException(e.toString());
        }

    }

    public BikeModel getBike(Integer idBike) throws ConnectionException {

        String sql = "SELECT b.id, b.isDamaged,b.datePurchase,s.id as idStation, e.id as idEntreprise FROM bike b " +
                "LEFT JOIN station s ON b.id_Station = s.id " +
                "LEFT JOIN entreprise e ON b.id_Entreprise = e.id " +
                "WHERE b.id = ? ;";

        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1,idBike);
            ResultSet data = statement.executeQuery();
            BikeModel bike = new BikeModel();

            while (data.next()) {


                GregorianCalendar datePurchase = new GregorianCalendar();
                StationModel station = new StationModel();
                EntrepriseModel entreprise = new EntrepriseModel();

                bike.setIdBike(data.getInt("id"));
                bike.setIsDamaged(data.getBoolean("isDamaged"));
                //Conversion Date to Gregorian
                datePurchase.setTime(data.getDate("datePurchase"));
                bike.setDatePurchase(datePurchase);

                station.setIdStation(data.getInt("idStation"));
                bike.setStationModel(station);

                entreprise.setIdEntreprise(data.getInt("idEntreprise"));
                bike.setEntrepriseModel(entreprise);

            }
            statement.close();
            data.close();
            return bike;

        }

        catch (SQLException e)
        {
            throw new ConnectionException(e.toString());
        }

    }
}
