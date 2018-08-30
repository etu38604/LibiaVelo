package DataAccessPackage;

import ExceptionPackage.ConnectionException;
import ModelPackage.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.GregorianCalendar;

public class EmployeeDataAccess {

    private Connection connection;

    public EmployeeDataAccess() throws ConnectionException {

        connection = ConnectionDataAccess.getInstance();
    }

    public ArrayList<EmployeeModel> employeeListing() throws ConnectionException {

        String sql = "SELECT e.id, e.lastName, e.firstName, e.initialNameSupp,e.dateHiring,e.phonePrivate," +
                " e.phonePro,e.mail,e.birthday,e.isPartTimeWork,e.street,e.streetNumber,e.workType," +
                "e.isDriverSpecialLicense,e.isLeader,e.id_Locality,l.id as idLocality,l.label as labelLocality,l.postalCode as postalLocality," +
                "e.id_WorkShop,w.id as idWork,w.place as placeWork,e.id_Zone,z.id as idZone,z.label as labelZone,e.id_Station,s.id as idStation,s.label as labelStation," +
                "e.inCharge_Employee,er.id as idResponsable,er.lastName as lastNameResponsable,er.firstName as firstNameResponsable," +
                "er.workType as workTypeResponsable,er.isLeader as isLeaderResponsable FROM employee e " +
                "    LEFT JOIN zone z ON e.id_zone = z.id" +
                "    LEFT JOIN workshop w ON e.id_workshop = w.id " +
                "    LEFT JOIN station s ON e.id_station = s.id " +
                "    LEFT JOIN locality l ON e.id_locality = l.id" +
                "    LEFT JOIN employee er ON e.inCharge_Employee = er.id" +
                "    ORDER BY e.id;";

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
                ZoneModel zone = new ZoneModel();
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

                locality.setIdLocality(data.getInt("idLocality"));
                locality.setLabelLocality(data.getString("labelLocality"));
                locality.setPostalCode(data.getInt("postalLocality"));
                employee.setLocalityModel(locality);

                employee.setStreet(data.getString("street"));
                employee.setStreetNumber(data.getInt("streetNumber"));
                employee.setWorkType(data.getString("workType"));
                employee.setDriverSpecialLicense(data.getBoolean("isDriverSpecialLicense"));
                employee.setLeader(data.getBoolean("isLeader"));


                workShop.setIdWorkShop(data.getInt("idWork"));
                workShop.setPlace(data.getString("placeWork"));
                employee.setWorkShopModel(workShop);

                station.setIdStation(data.getInt("idStation"));
                station.setLabelStation(data.getString("labelStation"));
                employee.setStationModel(station);

                zone.setIdZone(data.getInt("idZone"));
                zone.setLabelZone(data.getString("labelZone"));
                employee.setZoneModel(zone);

                inCharge.setIdEmployee(data.getInt("idResponsable"));
                inCharge.setLastName(data.getString("lastNameResponsable"));
                inCharge.setFirstName(data.getString("firstNameResponsable"));
                inCharge.setWorkType(data.getString("workTypeResponsable"));
                inCharge.setLeader(data.getBoolean("isLeaderResponsable"));
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

        String sql = "SELECT e.id, e.lastName, e.firstName, e.initialNameSupp,e.dateHiring,e.phonePrivate," +
                " e.phonePro,e.mail,e.birthday,e.isPartTimeWork,e.street,e.streetNumber,e.workType," +
                "e.isDriverSpecialLicense,e.isLeader,e.id_Locality,l.id as idLocality,l.label as labelLocality,l.postalCode as postalLocality," +
                "e.id_WorkShop,w.id as idWork,w.place as placeWork,e.id_Zone,z.id as idZone,z.label as labelZone,e.id_Station,s.id as idStation,s.label as labelStation," +
                "e.inCharge_Employee,er.id as idResponsable,er.lastName as lastNameResponsable,er.firstName as firstNameResponsable," +
                "er.workType as workTypeResponsable,er.isLeader as isLeaderResponsable FROM employee e " +
                "    LEFT JOIN zone z ON e.id_zone = z.id" +
                "    LEFT JOIN workshop w ON e.id_workshop = w.id " +
                "    LEFT JOIN station s ON e.id_station = s.id " +
                "    LEFT JOIN locality l ON e.id_locality = l.id" +
                "    LEFT JOIN employee er ON e.inCharge_Employee = er.id" +
                "    WHERE e.id = ?;";

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
                ZoneModel zone = new ZoneModel();
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

                locality.setIdLocality(data.getInt("idLocality"));
                locality.setLabelLocality(data.getString("labelLocality"));
                locality.setPostalCode(data.getInt("postalLocality"));
                employee.setLocalityModel(locality);

                employee.setStreet(data.getString("street"));
                employee.setStreetNumber(data.getInt("streetNumber"));
                employee.setWorkType(data.getString("workType"));
                employee.setDriverSpecialLicense(data.getBoolean("isDriverSpecialLicense"));
                employee.setLeader(data.getBoolean("isLeader"));


                workShop.setIdWorkShop(data.getInt("idWork"));
                workShop.setPlace(data.getString("placeWork"));
                employee.setWorkShopModel(workShop);

                station.setIdStation(data.getInt("idStation"));
                station.setLabelStation(data.getString("labelStation"));
                employee.setStationModel(station);

                zone.setIdZone(data.getInt("idZone"));
                zone.setLabelZone(data.getString("labelZone"));
                employee.setZoneModel(zone);

                inCharge.setIdEmployee(data.getInt("idResponsable"));
                inCharge.setLastName(data.getString("lastNameResponsable"));
                inCharge.setFirstName(data.getString("firstNameResponsable"));
                inCharge.setWorkType(data.getString("workTypeResponsable"));
                inCharge.setLeader(data.getBoolean("isLeaderResponsable"));
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

        String sql = "DELETE e FROM employee e INNER JOIN employee er ON  e.inCharge_Employee = er.id WHERE e.id = ?";

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

        String sql ="INSERT INTO employee (lastName,firstName,initialNameSupp,dateHiring,phonePrivate,phonePro,mail, " +
                        "birthday,isPartTimeWork,street,streetNumber,workType,isDriverSpecialLicense,isLeader,id_Workshop, " +
                        "id_Station,id_Locality,id_Zone,inCharge_Employee) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

        try
        {
            PreparedStatement statement = connection.prepareStatement(sql);
            Date dateHiring;
            Date dateBirthday;

            //statement.setInt(1,employee.getIdEmployee());
            statement.setString(1,employee.getLastName());
            statement.setString(2,employee.getFirstName());

            // Management optional

            if (employee.getInitialNameSupp() != null)
            {
                statement.setString(3,employee.getInitialNameSupp());
            } else
            {
                statement.setNull(3, Types.VARCHAR);
            }

            // Conversion Gregorian Calendar to Date

            dateHiring = new Date(employee.getDateHiring().getTimeInMillis());
            statement.setDate(4,dateHiring);

            if (employee.getPhonePrivate() != null)
            {
                statement.setInt(5,employee.getPhonePrivate());
            } else
            {
                statement.setNull(5, Types.INTEGER);
            }

            statement.setInt(6,employee.getPhonePro());

            if (employee.getMail() != null)
            {
                statement.setString(7,employee.getMail());

            } else
            {
                statement.setNull(7,Types.VARCHAR);
            }

            //Conversion Gregorian Calendar to Date
            dateBirthday = new Date(employee.getBirthday().getTimeInMillis());
            statement.setDate(8, dateBirthday);

            statement.setBoolean(9,employee.getPartTimeWork());
            statement.setString(10,employee.getStreet());
            statement.setInt(11,employee.getStreetNumber());
            statement.setString(12,employee.getWorkType());

            if (employee.getDriverSpecialLicense() != null)
            {
                statement.setBoolean(13,employee.getDriverSpecialLicense());

            } else
            {
                statement.setNull(13,Types.BOOLEAN);
            }

            if ((employee.getLeader() != null))
            {
                statement.setBoolean(14,employee.getLeader());
            } else
            {
                statement.setNull(14,Types.BOOLEAN);
            }

            if (employee.getWorkShopModel() != null)
            {
                statement.setInt(15,employee.getWorkShopModel().getIdWorkShop());
            } else
            {
                statement.setNull(15,Types.INTEGER);
            }

            if (employee.getStationModel() != null)
            {
                statement.setInt(16,employee.getStationModel().getIdStation());

            } else
            {
                statement.setNull(16,Types.INTEGER);
            }
            statement.setInt(17,employee.getLocalityModel().getIdLocality());

            if (employee.getZoneModel() != null)
            {
                statement.setInt(18,employee.getZoneModel().getIdZone());
            } else
            {
                statement.setNull(18,Types.INTEGER);
            }

            if (employee.getInCharge() != null)
            {
                statement.setInt(19,employee.getInCharge().getIdEmployee());
            } else
            {
                statement.setNull(19,Types.INTEGER);
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

        String sql ="UPDATE employee SET lastName = ?,firstName = ?,initialNameSupp = ?,dateHiring = ?,phonePrivate = ?, " +
                    "phonePro = ?,mail = ?,birthday = ?,isPartTimeWork = ?,street = ?,streetNumber = ?,workType = ?,isDriverSpecialLicense = ?, " +
                    "isLeader = ?,id_Workshop = ?,id_Station = ?,id_Locality = ?,id_Zone = ?,inCharge_Employee = ? " +
                    " WHERE id = ? ;";

        try
        {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(20,employee.getIdEmployee());
            Date dateHiring,dateBirthday;


            statement.setString(1,employee.getLastName());
            statement.setString(2,employee.getFirstName());

            if (employee.getInitialNameSupp() != null)
            {
                statement.setString(3,employee.getInitialNameSupp());
            } else
            {
                statement.setNull(3, Types.VARCHAR);
            }

            dateHiring = new Date(employee.getDateHiring().getTimeInMillis());
            statement.setDate(4,dateHiring);

            if (employee.getPhonePrivate() != null)
            {
                statement.setInt(5,employee.getPhonePrivate());
            } else
            {
                statement.setNull(5, Types.INTEGER);
            }

            statement.setInt(6,employee.getPhonePro());


            if (employee.getMail() != null)
            {
                statement.setString(7,employee.getMail());

            } else
            {
                statement.setNull(7,Types.VARCHAR);
            }

            dateBirthday = new Date(employee.getBirthday().getTimeInMillis());
            statement.setDate(8, dateBirthday);

            statement.setBoolean(9,employee.getPartTimeWork());
            statement.setString(10,employee.getStreet());
            statement.setInt(11,employee.getStreetNumber());
            statement.setString(12,employee.getWorkType());

            if (employee.getDriverSpecialLicense() != null)
            {
                statement.setBoolean(13,employee.getDriverSpecialLicense());

            } else
            {
                statement.setNull(13,Types.BOOLEAN);
            }

            if ((employee.getLeader() != null))
            {
                statement.setBoolean(14,employee.getLeader());
            } else
            {
                statement.setNull(14,Types.BOOLEAN);
            }

            if (employee.getWorkShopModel() != null)
            {
                statement.setInt(15,employee.getWorkShopModel().getIdWorkShop());
            } else
            {
                statement.setNull(15,Types.INTEGER);
            }

            if (employee.getStationModel() != null)
            {
                statement.setInt(16,employee.getStationModel().getIdStation());

            } else
            {
                statement.setNull(16,Types.INTEGER);
            }


            statement.setInt(17,employee.getLocalityModel().getIdLocality());

            if (employee.getZoneModel() != null)
            {
                statement.setInt(18,employee.getZoneModel().getIdZone());
            } else
            {
                statement.setNull(18,Types.INTEGER);
            }

            if (employee.getInCharge() != null)
            {
                statement.setInt(19,employee.getInCharge().getIdEmployee());
            } else
            {
                statement.setNull(19,Types.INTEGER);
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

    public ArrayList<EmployeeModel> transporterListing() throws ConnectionException {

        String sql = "SELECT * FROM employee WHERE workType = 'Transporteur' ";

        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet data = statement.executeQuery();
            ArrayList<EmployeeModel> arrayEmployee = new ArrayList<>();


            while (data.next()) {

                EmployeeModel employee = new EmployeeModel();

                employee.setIdEmployee(data.getInt("id"));
                employee.setLastName(data.getString("lastName"));
                employee.setFirstName(data.getString("firstName"));

                employee.setWorkType(data.getString("workType"));
                employee.setDriverSpecialLicense(data.getBoolean("isDriverSpecialLicense"));


                arrayEmployee.add(employee);


            }


            statement.close();
            data.close();
            return arrayEmployee;
        } catch (SQLException e) {
            throw new ConnectionException(e.toString());
        }
    }

}
