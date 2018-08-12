package DataAccessPackage;

import ExceptionPackage.ConnectionException;
import ModelPackage.EmployeeModel;
import ModelPackage.LocalityModel;
import ModelPackage.StationModel;
import ModelPackage.WorkShopModel;
import java.sql.*;
import java.util.ArrayList;
import java.util.GregorianCalendar;

public class EmployeeDataAccess {

    private Connection connection;

    public EmployeeDataAccess() throws ConnectionException {

        connection = ConnectionDataAccess.getInstance();
    }

    public ArrayList<EmployeeModel> employeeListing() throws ConnectionException {

        String sql = "SELECT * FROM employee e LEFT JOIN locality l ON e.id_locality = l.id LEFT JOIN workshop w ON e.id_workshop = w.id LEFT JOIN station s ON e.id_station = s.id ";

        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet data = statement.executeQuery();
            ArrayList<EmployeeModel> arrayEmployee = new ArrayList<>();


            while (data.next()) {

                EmployeeModel employee = new EmployeeModel();
                GregorianCalendar dateHiring = new GregorianCalendar();
                GregorianCalendar birthday = new GregorianCalendar();
                LocalityModel locality = new LocalityModel();
                StationModel station = new StationModel();
                WorkShopModel workShop = new WorkShopModel();
                EmployeeModel inCharge = new EmployeeModel();
                employee.setIdEmployee(data.getInt("id"));
                employee.setLastName(data.getString("lastName"));
                employee.setFirstName(data.getString("firstName"));
                employee.setInitialNameSupp(data.getString("initialNameSupp"));
                //Conversion Date en GregorianCalendar
                dateHiring.setTime(data.getDate("dateHiring"));
                employee.setDateHiring(dateHiring);
                employee.setPhonePrivate(data.getInt("phonePrivate"));
                employee.setPhonePro(data.getInt("phonePro"));
                employee.setMail(data.getString("mail"));
                //Conversion Date en GregorianCalendar
                birthday.setTime(data.getDate("birthday"));
                employee.setBirthday(birthday);
                employee.setPartTimeWork(data.getBoolean("isPartTimeWork"));

                locality.setIdLocality(data.getInt("id"));
                locality.setLabelLocality(data.getString("label"));
                locality.setPostalCode(data.getInt("postalCode"));
                employee.setLocalityModel(locality);

                employee.setStreet(data.getString("street"));
                employee.setStreetNumber(data.getInt("streetNumber"));
                employee.setWorkType(data.getString("workType"));
                employee.setDriverSpecialLicense(data.getBoolean("isDriverSpecialLicense"));
                employee.setZoneInCharge(data.getBoolean("isZoneInCharge"));


                workShop.setIdWorkShop(data.getInt("id"));
                workShop.setPlace(data.getString("place"));
                employee.setWorkShopModel(workShop);

                station.setIdStation(data.getInt("id"));
                station.setLabelStation(data.getString("label"));
                employee.setStationModel(station);

                inCharge.setIdEmployee(data.getInt("id"));
                inCharge.setLastName(data.getString("lastName"));
                inCharge.setFirstName(data.getString("firstName"));
                employee.setInCharge(inCharge);

                arrayEmployee.add(employee);


            }


            statement.close();
            data.close();
            return arrayEmployee;
        } catch (SQLException e) {
            throw new ConnectionException(e.toString());
        }
    }

    public EmployeeModel getEmployee(Integer idEmployee) throws ConnectionException {

        String sql = "SELECT * FROM employee e LEFT JOIN locality l ON e.id_locality = l.id LEFT JOIN workshop w ON e.id_workshop = w.id LEFT JOIN station s ON e.id_station = s.id WHERE e.id = ? ";

        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1,idEmployee);
            ResultSet data = statement.executeQuery();
            EmployeeModel employee = new EmployeeModel();

            while (data.next()) {


                GregorianCalendar dateHiring = new GregorianCalendar();
                GregorianCalendar birthday = new GregorianCalendar();
                LocalityModel locality = new LocalityModel();
                StationModel station = new StationModel();
                WorkShopModel workShop = new WorkShopModel();
                EmployeeModel inCharge = new EmployeeModel();
                employee.setIdEmployee(data.getInt("id"));
                employee.setLastName(data.getString("lastName"));
                employee.setFirstName(data.getString("firstName"));
                employee.setInitialNameSupp(data.getString("initialNameSupp"));
                //Conversion Date en GregorianCalendar
                dateHiring.setTime(data.getDate("dateHiring"));
                employee.setDateHiring(dateHiring);
                employee.setPhonePrivate(data.getInt("phonePrivate"));
                employee.setPhonePro(data.getInt("phonePro"));
                employee.setMail(data.getString("mail"));
                //Conversion Date en GregorianCalendar
                birthday.setTime(data.getDate("birthday"));
                employee.setBirthday(birthday);
                employee.setPartTimeWork(data.getBoolean("isPartTimeWork"));

                locality.setIdLocality(data.getInt("id"));
                locality.setLabelLocality(data.getString("label"));
                locality.setPostalCode(data.getInt("postalCode"));
                employee.setLocalityModel(locality);

                employee.setStreet(data.getString("street"));
                employee.setStreetNumber(data.getInt("streetNumber"));
                employee.setWorkType(data.getString("workType"));
                employee.setDriverSpecialLicense(data.getBoolean("isDriverSpecialLicense"));
                employee.setZoneInCharge(data.getBoolean("isZoneInCharge"));


                workShop.setIdWorkShop(data.getInt("id"));
                workShop.setPlace(data.getString("place"));
                employee.setWorkShopModel(workShop);

                station.setIdStation(data.getInt("id"));
                station.setLabelStation(data.getString("label"));
                employee.setStationModel(station);

                inCharge.setIdEmployee(data.getInt("id"));
                inCharge.setLastName(data.getString("lastName"));
                inCharge.setFirstName(data.getString("firstName"));
                employee.setInCharge(inCharge);



            }
            statement.close();
            data.close();
            return employee;

            }

        catch (SQLException e)
        {
            throw new ConnectionException(e.toString());
        }

    }

    public boolean deleteEmployee (int idEmployee) throws ConnectionException{

        String sql = "DELETE FROM employee WHERE id = ?";

        try
        {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, idEmployee);
            statement.executeUpdate();
            statement.close();
            return true;
        }
        catch (SQLException e) {
            throw new ConnectionException(e.toString());
        }
    }

    public boolean insertEmployee (EmployeeModel employee) throws ConnectionException {

        String sql ="INSERT INTO employee (id,lastName,firstName,initialNameSupp,dateHiring,phonePrivate,phonePro,mail,birthday,isPartTimeWork,street,streetNumber,workType,isDriverSpecialLicense,isZoneInCharge,id_Workshop,id_Station,id_Locality,inCharge) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

        try
        {
            PreparedStatement statement = connection.prepareStatement(sql);
            java.sql.Date dateHiring,dateBirthday;

            statement.setInt(1,employee.getIdEmployee());
            statement.setString(2,employee.getLastName());
            statement.setString(3,employee.getFirstName());

            // Management optional

            if (employee.getInitialNameSupp() != null)
            {
                statement.setString(4,employee.getInitialNameSupp());
            } else
            {
                statement.setNull(4, Types.VARCHAR);
            }

            // Conversion Gregorian Calendar to Date
            dateHiring = new java.sql.Date(employee.getDateHiring().getTimeInMillis());
            statement.setDate(5,dateHiring);

            if (employee.getPhonePrivate() != null)
            {
                statement.setInt(6,employee.getPhonePrivate());
            } else
            {
                statement.setNull(6, Types.VARCHAR);
            }

            statement.setInt(7,employee.getPhonePro());
            if (employee.getMail() != null)
            {
                statement.setString(8,employee.getMail());

            } else
            {
                statement.setNull(8,Types.VARCHAR);
            }

            //Conversion Gregorian Calendar to Date
            dateBirthday = new java.sql.Date(employee.getBirthday().getTimeInMillis());
            statement.setDate(9, dateBirthday);

            statement.setBoolean(10,employee.getPartTimeWork());
            statement.setString(11,employee.getStreet());
            statement.setInt(12,employee.getStreetNumber());
            statement.setString(13,employee.getWorkType());

            if (employee.getDriverSpecialLicense() != null)
            {
                statement.setBoolean(14,employee.getDriverSpecialLicense());

            } else
            {
                statement.setNull(14,Types.VARCHAR);
            }

            if ((employee.getZoneInCharge() != null))
            {
                statement.setBoolean(15,employee.getZoneInCharge());
            } else
            {
                statement.setNull(15,Types.VARCHAR);
            }

            if (employee.getWorkShopModel() != null)
            {
                statement.setInt(16,employee.getWorkShopModel().getIdWorkShop());
            } else
            {
                statement.setNull(16,Types.VARCHAR);
            }

            if (employee.getStationModel() != null)
            {
                statement.setInt(17,employee.getStationModel().getIdStation());

            } else
            {
                statement.setNull(17,Types.VARCHAR);
            }
            statement.setInt(18,employee.getLocalityModel().getIdLocality());

            if (employee.getInCharge() != null)
            {
                statement.setInt(19,employee.getInCharge().getIdEmployee());
            } else
            {
                statement.setNull(19,Types.VARCHAR);
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

    public boolean updateEmployee (EmployeeModel employee) throws ConnectionException {

        String sql ="UPDATE employee SET (id,lastName,firstName,initialNameSupp,dateHiring,phonePrivate,phonePro,mail,birthday,isPartTimeWork,street,streetNumber,workType,isDriverSpecialLicense,isZoneInCharge,id_Workshop,id_Station,id_Locality,inCharge) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

        try
        {
            PreparedStatement statement = connection.prepareStatement(sql);
            java.sql.Date dateHiring,dateBirthday;

            statement.setInt(1,employee.getIdEmployee());
            statement.setString(2,employee.getLastName());
            statement.setString(3,employee.getFirstName());

            if (employee.getInitialNameSupp() != null)
            {
                statement.setString(4,employee.getInitialNameSupp());
            } else
            {
                statement.setNull(4, Types.VARCHAR);
            }

            dateHiring = new java.sql.Date(employee.getDateHiring().getTimeInMillis());
            statement.setDate(5,dateHiring);

            if (employee.getPhonePrivate() != null)
            {
                statement.setInt(6,employee.getPhonePrivate());
            } else
            {
                statement.setNull(6, Types.VARCHAR);
            }

            statement.setInt(7,employee.getPhonePro());
            if (employee.getMail() != null)
            {
                statement.setString(8,employee.getMail());

            } else
            {
                statement.setNull(8,Types.VARCHAR);
            }

            dateBirthday = new java.sql.Date(employee.getBirthday().getTimeInMillis());
            statement.setDate(9, dateBirthday);

            statement.setBoolean(10,employee.getPartTimeWork());
            statement.setString(11,employee.getStreet());
            statement.setInt(12,employee.getStreetNumber());
            statement.setString(13,employee.getWorkType());

            if (employee.getDriverSpecialLicense() != null)
            {
                statement.setBoolean(14,employee.getDriverSpecialLicense());

            } else
            {
                statement.setNull(14,Types.VARCHAR);
            }

            if ((employee.getZoneInCharge() != null))
            {
                statement.setBoolean(15,employee.getZoneInCharge());
            } else
            {
                statement.setNull(15,Types.VARCHAR);
            }

            if (employee.getWorkShopModel() != null)
            {
                statement.setInt(16,employee.getWorkShopModel().getIdWorkShop());
            } else
            {
                statement.setNull(16,Types.VARCHAR);
            }

            if (employee.getStationModel() != null)
            {
                statement.setInt(17,employee.getStationModel().getIdStation());

            } else
            {
                statement.setNull(17,Types.VARCHAR);
            }
            statement.setInt(18,employee.getLocalityModel().getIdLocality());

            if (employee.getInCharge() != null)
            {
                statement.setInt(19,employee.getInCharge().getIdEmployee());
            } else
            {
                statement.setNull(19,Types.VARCHAR);
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
