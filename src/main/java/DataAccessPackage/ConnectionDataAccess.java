package DataAccessPackage;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import ExceptionPackage.ConnectionException;

public class ConnectionDataAccess {

    private static Connection connection;

    public static Connection getInstance() throws ConnectionException
    {
        if (connection == null)
        {
            GetConnexion();
        }
        else {
            try
            {
                if (connection.isClosed()) GetConnexion();
            }
            catch (SQLException erreurOuverture)
            {
                throw new ConnectionException("Erreur de connexion : " + erreurOuverture);
            }
        }
        return connection;
    }
    public static void closeConnection () throws ConnectionException
    {
        if (connection != null)
        {
            try
            {
                connection.close();
            }
            catch (SQLException erreurFermeture)
            {
                throw new ConnectionException(erreurFermeture.toString());
            }
        }
    }
    private static void GetConnexion() throws ConnectionException
    {
        try
        {
            String host = "jdbc:mysql://localhost:3305/libiavelo";
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(host,"ChefAtelier","Libiavelo12");
        }
        catch (SQLException | ClassNotFoundException erreurOuverture)
        {
            throw new ConnectionException("Erreur de connexion : " + erreurOuverture);
        }
    }
}
