package DataAccessPackage;

import ExceptionPackage.ConnectionException;
import ModelPackage.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.GregorianCalendar;

public class TransportOrderDataAccess {

    private Connection connection;

    public TransportOrderDataAccess() throws ConnectionException {

        connection = ConnectionDataAccess.getInstance();
    }

    public ArrayList<TransportOrderModel> transportOrderListing() throws ConnectionException {

        String sql = "SELECT t.id,t.dateTransport,e.id as idEmployee,e.lastName as lastNameEmployee, e.firstName as firstNameEmployee, " +
                        "b.id as idBike, b.isDamaged, si.id as idStationIssuance, si.label as labelStationIssuance, " +
                        "so.id as idStationOrigin,so.label as labelStationOrigin, sd.id as idStationDestination,sd.label as labelStationDestination " +
                        "w.id as idWorkshop, w.place as placeWorkshop " +
                            "FROM transport_order t " +
                                "LEFT JOIN employee e ON t.id_Employee = e.id " +
                                "LEFT JOIN bike b ON t.id_bike = b.id " +
                                "LEFT JOIN station si ON t.id_Station_issuance = si.id " +
                                "LEFT JOIN station so ON t.id_Station_origin = so.id " +
                                "LEFT JOIN station sd ON t.id_Station_destination = sd.id " +
                                "LEFT JOIN workshop w ON t.id_Workshop = w.id ;";

        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet data = statement.executeQuery();
            ArrayList<TransportOrderModel> arrayTransport = new ArrayList<>();


            while (data.next()) {

                TransportOrderModel transport = new TransportOrderModel();
                GregorianCalendar dateTransport = new GregorianCalendar();
                BikeModel bike = new BikeModel();
                EmployeeModel employee = new EmployeeModel();
                StationModel stationIssuance = new StationModel();
                StationModel stationOrigin = new StationModel();
                StationModel stationDestination = new StationModel();
                WorkShopModel workShop = new WorkShopModel();

                transport.setIdTransport(data.getInt("id"));

                //Conversion Date en GregorianCalendar
                dateTransport.setTime(data.getDate("dateTransport"));
                transport.setDateTransport(dateTransport);


                bike.setIdBike(data.getInt("idBike"));
                bike.setIsDamaged(data.getBoolean("isDamaged"));
                transport.setBikeModel(bike);

                employee.setIdEmployee(data.getInt("idEmployee"));
                employee.setLastName(data.getString("lastNameEmployee"));
                employee.setFirstName(data.getString("firstNameEmployee"));
                employee.setWorkType(data.getString("workTypeEmployee"));
                transport.setEmployeeModel(employee);

                stationIssuance.setIdStation(data.getInt("idStationIssuance"));
                stationIssuance.setLabelStation(data.getString("labelStationIssuance"));
                transport.setStationIssuance(stationIssuance);

                stationOrigin.setIdStation(data.getInt("idStationOrigin"));
                stationOrigin.setLabelStation(data.getString("labelStationOrigin"));
                transport.setStationOrigin(stationOrigin);

                stationDestination.setIdStation(data.getInt("idStationDestination"));
                stationDestination.setLabelStation(data.getString("labelStationDestination"));
                transport.setStationDestination(stationDestination);

                workShop.setIdWorkShop(data.getInt("idWorkshop"));
                workShop.setPlace(data.getString("placeWorkshop"));
                transport.setWorkShopModel(workShop);

                arrayTransport.add(transport);

            }

            statement.close();
            data.close();
            return arrayTransport;
        } catch (SQLException e) {
            throw new ConnectionException(e.toString());
        }


    }

    public boolean InsertTransportOrder (TransportOrderModel transport) throws ConnectionException {

        String sql ="INSERT INTO transport_order (dateTransport,id_Bike,id_Employee,id_Station_issuance,id_Station_origin,id_Station_destination,id_Workshop) " +
                        "VALUES (?,?,?,?,?,?,?) ;";

        try
        {
            PreparedStatement statement = connection.prepareStatement(sql);
            Date dateTransport;


            dateTransport = new Date(transport.getDateTransport().getTimeInMillis());
            statement.setDate(1,dateTransport);

            statement.setInt(2,transport.getBikeModel().getIdBike());
            statement.setInt(3,transport.getEmployeeModel().getIdEmployee());
            statement.setInt(4,transport.getStationIssuance().getIdStation());


            if (transport.getStationOrigin() != null)
            {
                statement.setInt(5,transport.getStationOrigin().getIdStation());
            } else
            {
                statement.setNull(5,Types.INTEGER);
            }

            if (transport.getStationDestination() != null)
            {
                statement.setInt(6,transport.getStationDestination().getIdStation());
            } else
            {
                statement.setNull(6,Types.INTEGER);
            }

            if (transport.getWorkShopModel() != null)
            {
                statement.setInt(7,transport.getWorkShopModel().getIdWorkShop());
            } else
            {
                statement.setNull(7,Types.INTEGER);
            }


            statement.executeUpdate();
            statement.close();
            return true;
        }
        catch (SQLException e)
        {
            throw new ConnectionException(e.toString());
        }
    }
}
