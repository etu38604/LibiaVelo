package ModelPackage;

import javax.swing.tree.TreeNode;
import java.util.GregorianCalendar;

public class TransportOrderModel {

    private Integer idTransport;
    private GregorianCalendar dateTransport;
    private BikeModel bikeModel;
    private StationModel stationModel;
    private EmployeeModel employeeModel;
    private StationModel stationIssuance;
    private StationModel stationOrigin;
    private StationModel stationDestination;

    public TransportOrderModel () {}

    public Integer getIdTransport() {
        return idTransport;
    }

    public void setIdTransport(Integer idTransport) {
        this.idTransport = idTransport;
    }

    public GregorianCalendar getDateTransport() {
        return dateTransport;
    }

    public void setDateTransport(GregorianCalendar dateTransport) {
        this.dateTransport = dateTransport;
    }

    public BikeModel getBikeModel() {
        return bikeModel;
    }

    public void setBikeModel(BikeModel bikeModel) {
        this.bikeModel = bikeModel;
    }

    public StationModel getStationModel() {
        return stationModel;
    }

    public void setStationModel(StationModel stationModel) {
        this.stationModel = stationModel;
    }

    public EmployeeModel getEmployeeModel() {
        return employeeModel;
    }

    public void setEmployeeModel(EmployeeModel employeeModel) {
        this.employeeModel = employeeModel;
    }

    public StationModel getStationIssuance() {
        return stationIssuance;
    }

    public void setStationIssuance(StationModel stationIssuance) {
        this.stationIssuance = stationIssuance;
    }

    public StationModel getStationOrigin() {
        return stationOrigin;
    }

    public void setStationOrigin(StationModel stationOrigin) {
        this.stationOrigin = stationOrigin;
    }

    public StationModel getStationDestination() {
        return stationDestination;
    }

    public void setStationDestination(StationModel stationDestination) {
        this.stationDestination = stationDestination;
    }
}
