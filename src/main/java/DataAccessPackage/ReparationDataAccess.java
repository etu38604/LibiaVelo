package DataAccessPackage;

import ExceptionPackage.ConnectionException;
import ModelPackage.BikeModel;
import ModelPackage.ReparationRecordModel;
import ModelPackage.WorkShopModel;

import java.sql.*;
import java.util.ArrayList;
import java.util.GregorianCalendar;

public class ReparationDataAccess {

    private Connection connection;

    public ReparationDataAccess() throws ConnectionException {

        connection = ConnectionDataAccess.getInstance();
    }

    public ArrayList<ReparationRecordModel> reparationListing() throws ConnectionException {

        String sql = "SELECT r.id, r.isValidate, r.dateBegin, r.dateEnd, r.note, r.workOrder, r.isDownGraded, " +
                        "w.id as idWorkShop, w.place as placeWorkShop, b.id as idBike,b.datePurchase as dateBikePurchase, " +
                        "b.isDamaged FROM reparation_record r " +
                            "LEFT JOIN workshop w ON r.id_Workshop = w.id " +
                            "LEFT JOIN bike b ON r.id_bike = b.id " +
                            "WHERE r.isValidate = false " +
                            "ORDER BY r.id ;" ;

        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet data = statement.executeQuery();
            ArrayList<ReparationRecordModel> arrayReparation = new ArrayList<>();


            while (data.next()) {

                ReparationRecordModel reparation = new ReparationRecordModel();
                GregorianCalendar dateBegin = new GregorianCalendar();
                GregorianCalendar dateEnd = new GregorianCalendar();
                GregorianCalendar datePurchase = new GregorianCalendar();
                BikeModel bike = new BikeModel();
                WorkShopModel workShop = new WorkShopModel();

                reparation.setIdReparation(data.getInt("id"));
                reparation.setValidate(data.getBoolean("isValidate"));

                //Conversion Date en GregorianCalendar
                dateBegin.setTime(data.getDate("dateBegin"));
                reparation.setDateBegin(dateBegin);

                //Conversion Date en GregorianCalendar
                dateEnd.setTime(data.getDate("dateEnd"));
                reparation.setDateEnd(dateEnd);

                reparation.setNote(data.getString("note"));
                reparation.setWorkOrder(data.getString("workOrder"));
                reparation.setDownGraded(data.getBoolean("isDownGraded"));

                bike.setIdBike(data.getInt("idBike"));
                bike.setIsDamaged(data.getBoolean("IsDamaged"));
                datePurchase.setTime(data.getDate("dateBikePurchase"));
                bike.setDatePurchase(datePurchase);
                reparation.setBikeModel(bike);


                workShop.setIdWorkShop(data.getInt("idWorkShop"));
                workShop.setPlace(data.getString("placeWorkShop"));
                reparation.setWorkShopModel(workShop);

                arrayReparation.add(reparation);

            }


            statement.close();
            data.close();
            return arrayReparation;
        } catch (SQLException e) {
            throw new ConnectionException(e.toString());
        }
    }

    public ReparationRecordModel getReparation (Integer idReparation) throws ConnectionException {

        String sql = "SELECT r.id,r.isValidate ,r.dateBegin,r.dateEnd, r.note, r.workOrder, r.isDownGraded, " +
                "w.id as idWorkShop, w.place as placeWorkShop, b.id as idBike,b.datePurchase as dateBikePurchase, " +
                "b.isDamaged FROM reparation_record r " +
                "LEFT JOIN workshop w ON r.id_Workshop = w.id " +
                "LEFT JOIN bike b ON r.id_bike = b.id  " +
                "WHERE r.id = ? ;";

        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1,idReparation);
            ResultSet data = statement.executeQuery();
            ReparationRecordModel reparation = new ReparationRecordModel();


            while (data.next()) {

                GregorianCalendar dateBegin = new GregorianCalendar();
                GregorianCalendar dateEnd = new GregorianCalendar();
                GregorianCalendar datePurchase = new GregorianCalendar();
                BikeModel bike = new BikeModel();
                WorkShopModel workShop = new WorkShopModel();

                reparation.setIdReparation(data.getInt("id"));
                reparation.setValidate(data.getBoolean("isValidate"));

                //Conversion Date en GregorianCalendar
                dateBegin.setTime(data.getDate("dateBegin"));
                reparation.setDateBegin(dateBegin);

                //Conversion Date en GregorianCalendar
                dateEnd.setTime(data.getDate("dateEnd"));
                reparation.setDateEnd(dateEnd);

                reparation.setNote(data.getString("note"));
                reparation.setWorkOrder(data.getString("workOrder"));
                reparation.setDownGraded(data.getBoolean("isDownGraded"));

                bike.setIdBike(data.getInt("idBike"));
                bike.setIsDamaged(data.getBoolean("IsDamaged"));
                datePurchase.setTime(data.getDate("dateBikePurchase"));
                bike.setDatePurchase(datePurchase);
                reparation.setBikeModel(bike);


                workShop.setIdWorkShop(data.getInt("idWorkShop"));
                workShop.setPlace(data.getString("placeWorkShop"));
                reparation.setWorkShopModel(workShop);

            }


            statement.close();
            data.close();
            return reparation;
        } catch (SQLException e) {
            throw new ConnectionException(e.toString());
        }
    }

    public boolean deleteReparation (Integer idReparation) throws ConnectionException{

        String sql = "DELETE r FROM reparation_record r WHERE r.id = ?";

        try
        {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, idReparation);
            statement.executeUpdate();
            statement.close();

            return true;
        }
        catch (SQLException e) {
            throw new ConnectionException(e.toString());
        }
    }

    public boolean updateReparation (ReparationRecordModel reparation) throws ConnectionException {

        String sql ="UPDATE reparation_record SET dateBegin = ?,dateEnd = ?,note = ?,workOrder = ?,isDownGraded = ?, " +
                "id_Bike = ?, id_Workshop = ?,isValidate = ? " +
                " WHERE id = ? ;";

        try
        {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(9,reparation.getIdReparation());
            Date dateBegin,dateEnd;


            dateBegin = new Date(reparation.getDateBegin().getTimeInMillis());
            statement.setDate(1,dateBegin);

            if (reparation.getDateEnd() != null)
            {
                dateEnd = new Date(reparation.getDateEnd().getTimeInMillis());
                statement.setDate(2,dateEnd);
            } else
            {
                statement.setDate(2,null);
            }



            if (reparation.getNote() != null)
            {
                statement.setString(3,reparation.getNote());

            } else
            {
                statement.setNull(3,Types.VARCHAR);
            }


            statement.setString(4,reparation.getWorkOrder());
            statement.setBoolean(5,reparation.getDownGraded());


            if (reparation.getBikeModel() != null)
            {
                statement.setInt(6,reparation.getBikeModel().getIdBike());

            } else
            {
                statement.setNull(6,Types.INTEGER);
            }


            if (reparation.getWorkShopModel() != null)
            {
                statement.setInt(7,reparation.getWorkShopModel().getIdWorkShop());
            } else
            {
                statement.setNull(7,Types.INTEGER);
            }

            statement.setBoolean(8,reparation.getValidate());


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
