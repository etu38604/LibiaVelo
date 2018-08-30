package DataAccessPackage;

import ExceptionPackage.ConnectionException;
import ModelPackage.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.Date;
import java.util.GregorianCalendar;

public class SearchDataAccess {

    private Connection connection;

    public SearchDataAccess() throws ConnectionException {

        connection = ConnectionDataAccess.getInstance();
    }

    public ArrayList<SearchModel> searchListing(Integer idEmployee, GregorianCalendar beginGreg, GregorianCalendar endGreg ) throws ConnectionException {

        String sql = "SELECT t.dateTransport as dateTransport,b.id as idBike,s.label as labelStation,s.street as streetStation," +
                    "s.coordGPS,l.label as labelLocality,l.postalCode FROM transport_order t " +
                        "LEFT JOIN station s ON t.id_Station_issuance = s.id " +
                            "LEFT JOIN locality l ON s.id_Locality = l.id " +
                        "LEFT JOIN employee e ON t.id_Employee = e.id " +
                        "LEFT JOIN bike b ON t.id_bike = b.id " +
                    "WHERE (e.id = ?) " +
                "AND (t.dateTransport BETWEEN ? AND ?);";

        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1,idEmployee);
            Date beginDate = new Date(beginGreg.getTimeInMillis());
            statement.setDate(2,beginDate);
            Date endDate = new Date(endGreg.getTimeInMillis());
            statement.setDate(3,endDate);
            ResultSet data = statement.executeQuery();
            ArrayList<SearchModel> arrayTransport = new ArrayList<>();


            while (data.next()) {

                SearchModel search = new SearchModel();
                GregorianCalendar dateTransport = new GregorianCalendar();
                LocalityModel locality = new LocalityModel();
                StationModel station = new StationModel();
                HistoricalModel historical = new HistoricalModel();
                BikeModel bike = new BikeModel();
                EmployeeModel employee = new EmployeeModel();
                TransportOrderModel transport = new TransportOrderModel();


                //Conversion Date en GregorianCalendar
                dateTransport.setTime(data.getDate("dateTransport"));
                transport.setDateTransport(dateTransport);
                search.setTransportOrderModel(transport);

                locality.setLabelLocality(data.getString("labelLocality"));
                locality.setPostalCode(data.getInt("postalCode"));
                search.setLocalityModel(locality);

                station.setStreet(data.getString("streetStation"));
                station.setLabelStation(data.getString("labelStation"));
                station.setCoordGPS(data.getString("coordGPS"));
                search.setStationModel(station);

                bike.setIdBike(data.getInt("idBike"));
                search.setBikeModel(bike);


                arrayTransport.add(search);

            }


            statement.close();
            data.close();
            return arrayTransport;
        } catch (SQLException e) {
            throw new ConnectionException(e.toString());
        }
    }
}
