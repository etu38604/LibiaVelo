package ViewPackage;

import DataAccessPackage.ConnectionDataAccess;
import DataAccessPackage.EmployeeDataAccess;
import ExceptionPackage.ConnectionException;
import ModelPackage.EmployeeModel;

import java.util.ArrayList;
import java.util.Date;

public class Main {

    public static void main(String[] args) throws ConnectionException {

       /* ArrayList<EmployeeModel> employeeArray = new ArrayList<>();
        EmployeeDataAccess employeeDataAccess = new EmployeeDataAccess();
        employeeArray = employeeDataAccess.employeeListing();
        Date date = new Date();

        System.out.println(employeeArray.size());
        for (EmployeeModel employee : employeeArray){
            date = new java.sql.Date(employee.getBirthday().getTimeInMillis());
            System.out.println(date);
        } */





        LayoutWindow libiavelo = new LayoutWindow();


    }
}
