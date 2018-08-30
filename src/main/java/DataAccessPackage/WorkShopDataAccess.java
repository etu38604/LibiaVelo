package DataAccessPackage;

import ExceptionPackage.ConnectionException;
import ModelPackage.WorkShopModel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class WorkShopDataAccess {

    private Connection connection;

    public WorkShopDataAccess () throws ConnectionException {

        connection = ConnectionDataAccess.getInstance();
    }

    public ArrayList<WorkShopModel> getAllWorkShop() throws ConnectionException {

        String sql = "SELECT * FROM workshop ORDER BY id";

        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet data = statement.executeQuery();
            ArrayList<WorkShopModel> allWorkshopArray = new ArrayList<>();

            while (data.next()) {

                WorkShopModel workshop = new WorkShopModel();

                workshop.setIdWorkShop(data.getInt("id"));
                workshop.setPlace(data.getString("place"));
                allWorkshopArray.add(workshop);

            }
            statement.close();
            data.close();
            return allWorkshopArray;

        }

        catch (SQLException e)
        {
            throw new ConnectionException(e.toString());
        }

    }

    public WorkShopModel getWorkshop(Integer idWorkshop) throws ConnectionException {

        String sql = "SELECT * FROM workshop w WHERE w.id = ? ";

        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1,idWorkshop);
            ResultSet data = statement.executeQuery();
            WorkShopModel workshop = new WorkShopModel();

            while (data.next()) {


                workshop.setIdWorkShop(data.getInt("id"));
                workshop.setPlace(data.getString("place"));

            }
            statement.close();
            data.close();
            return workshop;

        }

        catch (SQLException e)
        {
            throw new ConnectionException(e.toString());
        }

    }


}
