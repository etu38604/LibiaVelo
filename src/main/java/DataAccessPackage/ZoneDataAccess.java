package DataAccessPackage;

import ExceptionPackage.ConnectionException;
import ModelPackage.ZoneModel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ZoneDataAccess {

    private Connection connection;

    public ZoneDataAccess () throws ConnectionException {

        connection = ConnectionDataAccess.getInstance();
    }

    public ArrayList<ZoneModel> getAllZone() throws ConnectionException {

        String sql = "SELECT * FROM zone ORDER BY id";

        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet data = statement.executeQuery();
            ArrayList<ZoneModel> allZoneyArray = new ArrayList<>();

            while (data.next()) {

                ZoneModel zone = new ZoneModel();

                zone.setIdZone(data.getInt("id"));
                zone.setLabelZone(data.getString("label"));
                allZoneyArray.add(zone);

            }
            statement.close();
            data.close();
            return allZoneyArray;

        }

        catch (SQLException e)
        {
            throw new ConnectionException(e.toString());
        }

    }

    public ZoneModel getZone(Integer idZone) throws ConnectionException {

        String sql = "SELECT * FROM zone z WHERE z.id = ? ";

        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1,idZone);
            ResultSet data = statement.executeQuery();
            ZoneModel zone = new ZoneModel();

            while (data.next()) {


                zone.setIdZone(data.getInt("id"));
                zone.setLabelZone(data.getString("label"));

            }
            statement.close();
            data.close();
            return zone;

        }

        catch (SQLException e)
        {
            throw new ConnectionException(e.toString());
        }

    }
}
