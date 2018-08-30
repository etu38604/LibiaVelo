package ControllerPackage;

import BusinessPackage.EmployeeBusiness;
import BusinessPackage.JobBusiness;
import BusinessPackage.SearchBusiness;
import ExceptionPackage.ConnectionException;
import ExceptionPackage.ValidatorException;
import ModelPackage.*;
import ValidatorPackage.Validator;

import java.util.ArrayList;
import java.util.GregorianCalendar;

public class Controller {

    private EmployeeBusiness employeeBusiness;
    private SearchBusiness searchBusiness;
    private JobBusiness jobBusiness;
    private Validator validator;

    public Controller () throws ConnectionException
    {
        employeeBusiness = new EmployeeBusiness();
        searchBusiness = new SearchBusiness();
        jobBusiness = new JobBusiness();
        validator = new Validator();
    }

    public ArrayList<EmployeeModel> employeeListing () throws ConnectionException
    {
        return employeeBusiness.employeeListing();
    }

    public Boolean insertEmployee (EmployeeModel employee) throws ConnectionException, ValidatorException
    {
        validator.controlText(employee.getLastName(), "Nom de l'employé");
        validator.controlText(employee.getFirstName(),"Prénom de l'employé");
        validator.controlInitial(employee.getInitialNameSupp(), "Initials supplémentaires");
        validator.controlText(employee.getStreet(),"Rue du domicile");
        validator.controlNumber(employee.getStreetNumber(),"Numéro du domicile");
        validator.controlNumber(employee.getPhonePro(), "Téléphone Pro");
        return employeeBusiness.insertEmployee(employee);
    }

    public Boolean updateEmployee (EmployeeModel employee) throws ConnectionException,ValidatorException
    {
        validator.controlText(employee.getLastName(), "Nom de l'employé");
        validator.controlText(employee.getFirstName(),"Prénom de l'employé");
        validator.controlInitial(employee.getInitialNameSupp(), "Initials supplémentaires");
        validator.controlText(employee.getStreet(),"Rue du domicile");
        validator.controlNumber(employee.getStreetNumber(),"Numéro du domicile");
        validator.controlNumber(employee.getPhonePro(), "Téléphone Pro");
        return employeeBusiness.updateEmployee(employee);
    }

    public Boolean deleteEmployee (Integer idEmployee) throws ConnectionException
    {
        return employeeBusiness.deleteEmployee(idEmployee);
    }

    public EmployeeModel getEmployee (Integer idEmployee) throws ConnectionException
    {
        return employeeBusiness.getEmployee(idEmployee);
    }

    public ArrayList<EmployeeModel> transporterListing () throws ConnectionException
    {
        return employeeBusiness.transporterListing();
    }

    public ArrayList<StationModel> stationListing () throws ConnectionException
    {
        return employeeBusiness.stationListing();
    }

    public StationModel getStation (Integer idStation) throws ConnectionException
    {
        return employeeBusiness.getStation(idStation);
    }

    public ArrayList<ZoneModel> getAllZone () throws ConnectionException
    {
        return employeeBusiness.getAllZone();
    }

    public ZoneModel getZone (Integer idZone) throws ConnectionException
    {
        return employeeBusiness.getZone(idZone);
    }

    public ArrayList<WorkShopModel> getAllWorkShop () throws ConnectionException
    {
        return employeeBusiness.getAllWorkShop();
    }

    public WorkShopModel getWorkShop (Integer idWorkShop) throws ConnectionException
    {
        return employeeBusiness.getWorkShop(idWorkShop);
    }

    public ArrayList<LocalityModel> getAllLocality () throws ConnectionException
    {
        return employeeBusiness.getAllLocality();
    }

    public LocalityModel getLocality (Integer idLocality) throws ConnectionException
    {
        return employeeBusiness.getLocality(idLocality);
    }

    public ArrayList<SearchModel> searchListing (Integer idEmployee, GregorianCalendar dateBegin,GregorianCalendar dateEnd) throws ConnectionException
    {
        return searchBusiness.searchListing(idEmployee,dateBegin,dateEnd);
    }

    public ArrayList<ReparationRecordModel> reparationListing () throws ConnectionException
    {
        return jobBusiness.reparationListing();
    }

    public ReparationRecordModel getReparation (Integer idReparation) throws ConnectionException
    {
        return jobBusiness.getReparation(idReparation);
    }

    public Boolean deleteReparation (Integer idReparation) throws ConnectionException
    {
        return jobBusiness.deleteReparation(idReparation);
    }

    public Boolean updateReparation (ReparationRecordModel reparation) throws ConnectionException, ValidatorException
    {
        return jobBusiness.updateReparation(reparation);
    }

    public ArrayList<TransportOrderModel> transportOrderListing () throws ConnectionException
    {
        return jobBusiness.transportOrderListing();
    }

    public Boolean insertTransportOrder (TransportOrderModel transport) throws ConnectionException,ValidatorException
    {
        validator.controlRequiredNumber(transport.getBikeModel().getIdBike(),"Numéro du vélo");
        return jobBusiness.insertTransportOrder(transport);
    }

    public ArrayList<BikeModel> getAllBike () throws ConnectionException
    {
        return jobBusiness.getAllBike();
    }

    public BikeModel getBike (Integer idBike) throws ConnectionException
    {
        return jobBusiness.getBike(idBike);
    }
}
