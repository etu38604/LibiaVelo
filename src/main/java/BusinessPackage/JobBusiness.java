package BusinessPackage;

import DataAccessPackage.BikeDataAccess;
import DataAccessPackage.ReparationDataAccess;
import DataAccessPackage.TransportOrderDataAccess;
import ExceptionPackage.ConnectionException;
import ExceptionPackage.ValidatorException;
import ModelPackage.BikeModel;
import ModelPackage.ReparationRecordModel;
import ModelPackage.TransportOrderModel;
import ValidatorPackage.Validator;

import java.util.ArrayList;

public class JobBusiness {

    private ReparationDataAccess reparationDataAccess;
    private TransportOrderDataAccess transportOrderDataAccess;
    private BikeDataAccess bikeDataAccess;
    private Validator validator;

    public JobBusiness () throws ConnectionException
    {
      reparationDataAccess = new ReparationDataAccess();
      transportOrderDataAccess = new TransportOrderDataAccess();
      bikeDataAccess = new BikeDataAccess();
      validator = new Validator();
    }

    public ArrayList<ReparationRecordModel> reparationListing () throws ConnectionException
    {
        return reparationDataAccess.reparationListing();
    }

    public ReparationRecordModel getReparation (Integer idReparation) throws ConnectionException
    {
        return reparationDataAccess.getReparation(idReparation);
    }

    public Boolean deleteReparation (Integer idReparation) throws ConnectionException
    {
        return reparationDataAccess.deleteReparation(idReparation);
    }

    public Boolean updateReparation (ReparationRecordModel reparation) throws ConnectionException, ValidatorException
    {
        return reparationDataAccess.updateReparation(reparation);
    }

    public ArrayList<TransportOrderModel> transportOrderListing () throws ConnectionException
    {
        return transportOrderDataAccess.transportOrderListing();
    }

    public Boolean insertTransportOrder (TransportOrderModel transport) throws ConnectionException,ValidatorException
    {
        validator.controlRequiredNumber(transport.getBikeModel().getIdBike(),"Numéro du vélo");
        return transportOrderDataAccess.InsertTransportOrder(transport);
    }

    public ArrayList<BikeModel> getAllBike () throws ConnectionException
    {
        return bikeDataAccess.getAllBike();
    }

    public BikeModel getBike (Integer idBike) throws ConnectionException
    {
        return bikeDataAccess.getBike(idBike);
    }
}
