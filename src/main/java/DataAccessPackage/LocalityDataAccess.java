package DataAccessPackage;

import ExceptionPackage.ConnectionException;
import ModelPackage.LocalityModel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class LocalityDataAccess {

    private Connection connection;

    public LocalityDataAccess () throws ConnectionException {

        connection = ConnectionDataAccess.getInstance();
    }

    public ArrayList<LocalityModel> getAllLocality() throws ConnectionException {

        String sql = "SELECT * FROM locality ORDER BY id";

        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet data = statement.executeQuery();
            ArrayList<LocalityModel> allLocalityArray = new ArrayList<>();

            while (data.next()) {

                LocalityModel locality = new LocalityModel();

                locality.setIdLocality(data.getInt("id"));
                locality.setLabelLocality(data.getString("label"));
                locality.setPostalCode(data.getInt("postalCode"));
                allLocalityArray.add(locality);

            }
            statement.close();
            data.close();
            return allLocalityArray;

        }

        catch (SQLException e)
        {
            throw new ConnectionException(e.toString());
        }

    }

    public LocalityModel getLocality(Integer idLocality) throws ConnectionException {

        String sql = "SELECT * FROM locality l WHERE l.id = ? ";

        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1,idLocality);
            ResultSet data = statement.executeQuery();
            LocalityModel locality = new LocalityModel();

            while (data.next()) {


                locality.setIdLocality(data.getInt("id"));
                locality.setLabelLocality(data.getString("label"));
                locality.setPostalCode(data.getInt("postalCode"));


            }
            statement.close();
            data.close();
            return locality;

        }

        catch (SQLException e)
        {
            throw new ConnectionException(e.toString());
        }

    }

}
