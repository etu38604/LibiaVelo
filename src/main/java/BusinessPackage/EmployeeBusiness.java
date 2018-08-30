package BusinessPackage;

import DataAccessPackage.*;
import ExceptionPackage.ConnectionException;
import ExceptionPackage.ValidatorException;
import ModelPackage.*;
import ValidatorPackage.Validator;

import java.util.ArrayList;

public class EmployeeBusiness {

    private EmployeeDataAccess employeeDataAccess;
    private StationDataAccess stationDataAccess;
    private ZoneDataAccess zoneDataAccess;
    private LocalityDataAccess localityDataAccess;
    private WorkShopDataAccess workShopDataAccess;
    private Validator validator;


    public EmployeeBusiness () throws ConnectionException
    {
        employeeDataAccess = new EmployeeDataAccess();
        stationDataAccess = new StationDataAccess();
        zoneDataAccess = new ZoneDataAccess();
        localityDataAccess = new LocalityDataAccess();
        workShopDataAccess = new WorkShopDataAccess();
        validator = new Validator();

    }

    public ArrayList<EmployeeModel> employeeListing () throws ConnectionException
    {
        return employeeDataAccess.employeeListing();
    }

    public boolean insertEmployee (EmployeeModel employee) throws ConnectionException, ValidatorException
    {
        validator.controlText(employee.getLastName(), "Nom de l'employé");
        validator.controlText(employee.getFirstName(),"Prénom de l'employé");
        validator.controlInitial(employee.getInitialNameSupp(), "Initials supplémentaires");
        validator.controlText(employee.getStreet(),"Rue du domicile");
        validator.controlNumber(employee.getStreetNumber(),"Numéro du domicile");
        validator.controlNumber(employee.getPhonePro(), "Téléphone Pro");
        return employeeDataAccess.insertEmployee(employee);
    }

    public boolean deleteEmployee (Integer idEmployee) throws ConnectionException
    {
        return employeeDataAccess.deleteEmployee(idEmployee);
    }

    public boolean updateEmployee (EmployeeModel employee) throws ConnectionException, ValidatorException
    {
        validator.controlText(employee.getLastName(), "Nom de l'employé");
        validator.controlText(employee.getFirstName(),"Prénom de l'employé");
        validator.controlInitial(employee.getInitialNameSupp(), "Initials supplémentaires");
        validator.controlText(employee.getStreet(),"Rue du domicile");
        validator.controlNumber(employee.getStreetNumber(),"Numéro du domicile");
        validator.controlNumber(employee.getPhonePro(), "Téléphone Pro");
        return employeeDataAccess.updateEmployee(employee);
    }

    public EmployeeModel getEmployee (Integer idEmployee) throws ConnectionException
    {
        return employeeDataAccess.getEmployee(idEmployee);
    }

    public ArrayList<EmployeeModel> transporterListing () throws ConnectionException
    {
        return employeeDataAccess.transporterListing();
    }

    public ArrayList<StationModel> stationListing () throws ConnectionException
    {
        return stationDataAccess.stationListing();
    }

    public StationModel getStation (Integer idStation) throws ConnectionException
    {
        return stationDataAccess.getStation(idStation);
    }

    public ArrayList<ZoneModel> getAllZone () throws ConnectionException
    {
        return zoneDataAccess.getAllZone();
    }

    public ZoneModel getZone (Integer idZone) throws ConnectionException
    {
        return zoneDataAccess.getZone(idZone);
    }

    public ArrayList<WorkShopModel> getAllWorkShop () throws ConnectionException
    {
        return workShopDataAccess.getAllWorkShop();
    }

    public WorkShopModel getWorkShop (Integer idWorkShop) throws ConnectionException
    {
        return workShopDataAccess.getWorkshop(idWorkShop);
    }

    public ArrayList<LocalityModel> getAllLocality () throws ConnectionException
    {
        return localityDataAccess.getAllLocality();
    }

    public LocalityModel getLocality (Integer idLocality) throws ConnectionException
    {
        return localityDataAccess.getLocality(idLocality);
    }
}
