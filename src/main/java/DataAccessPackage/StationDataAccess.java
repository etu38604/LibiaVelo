package DataAccessPackage;

import ExceptionPackage.ConnectionException;
import ModelPackage.LocalityModel;
import ModelPackage.StationModel;
import ModelPackage.ZoneModel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.GregorianCalendar;

public class StationDataAccess {

    private Connection connection;

    public StationDataAccess() throws ConnectionException {

        connection = ConnectionDataAccess.getInstance();
    }

    public ArrayList<StationModel> stationListing() throws ConnectionException {

        String sql = "SELECT * FROM station s LEFT OUTER JOIN zone z ON s.id_zone = z.id LEFT OUTER JOIN  locality l ON s.id_locality = l.id  ORDER BY s.id";

        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet data = statement.executeQuery();
            ArrayList<StationModel> arrayStation = new ArrayList<>();


            while (data.next()) {

                GregorianCalendar dateCreation = new GregorianCalendar();
                LocalityModel locality = new LocalityModel();
                StationModel station = new StationModel();
                ZoneModel zone = new ZoneModel();
                station.setIdStation(data.getInt("id"));
                station.setLabelStation(data.getString("label"));
                station.setNbBikeMinWarn(data.getInt("nbBikeMinWarn"));
                station.setNbBikeMaxWarn(data.getInt("nbBikeMaxWarn"));
                station.setNbBikeMinContr(data.getInt("nbBikeMinContr"));
                station.setNbBikeMaxContr(data.getInt("nbBikeMaxContr"));
                //Conversion Date en GregorianCalendar
                dateCreation.setTime(data.getDate("dateCreation"));
                station.setDateCreation(dateCreation);
                station.setCover(data.getBoolean("isCover"));
                station.setCoordGPS(data.getString("coordGPS"));
                station.setStreet(data.getString("street"));

                locality.setIdLocality(data.getInt("id"));
                locality.setLabelLocality(data.getString("label"));
                locality.setPostalCode(data.getInt("postalCode"));
                station.setLocalityModel(locality);


                zone.setIdZone(data.getInt("id"));
                zone.setLabelZone(data.getString("label"));
                station.setZoneModel(zone);


                arrayStation.add(station);


            }


            statement.close();
            data.close();
            return arrayStation;
        } catch (SQLException e) {
            throw new ConnectionException(e.toString());
        }
    }

    public StationModel getStation(Integer idStation) throws ConnectionException {

        String sql = "SELECT * FROM station s LEFT JOIN locality l ON s.id_locality = l.id LEFT JOIN zone z ON s.id_zone = z.id WHERE s.id = ?";

        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1,idStation);
            ResultSet data = statement.executeQuery();
            StationModel station = new StationModel();

            while (data.next()) {


                GregorianCalendar dateCreation = new GregorianCalendar();
                LocalityModel locality = new LocalityModel();
                ZoneModel zone = new ZoneModel();
                station.setIdStation(data.getInt("id"));
                station.setLabelStation(data.getString("label"));
                station.setNbBikeMinWarn(data.getInt("nbBikeMinWarn"));
                station.setNbBikeMaxWarn(data.getInt("nbBikeMaxWarn"));
                station.setNbBikeMinContr(data.getInt("nbBikeMinContr"));
                station.setNbBikeMaxContr(data.getInt("nbBikeMaxContr"));
                //Conversion Date en GregorianCalendar
                dateCreation.setTime(data.getDate("dateCreation"));
                station.setDateCreation(dateCreation);
                station.setCover(data.getBoolean("isCover"));
                station.setCoordGPS(data.getString("coordGPS"));
                station.setStreet(data.getString("street"));

                locality.setIdLocality(data.getInt("id"));
                locality.setLabelLocality(data.getString("label"));
                locality.setPostalCode(data.getInt("postalCode"));
                station.setLocalityModel(locality);


                zone.setIdZone(data.getInt("id"));
                zone.setLabelZone(data.getString("label"));
                station.setZoneModel(zone);



            }
            statement.close();
            data.close();
            return station;

        }

        catch (SQLException e)
        {
            throw new ConnectionException(e.toString());
        }

    }

}
