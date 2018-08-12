package ModelPackage;


import java.util.GregorianCalendar;

public class BikeModel {

    private Integer idBike;
    private Boolean idDamaged;
    private GregorianCalendar datePurchase;
    private EntrepriseModel entrepriseModel;
    private StationModel stationModel;

    public BikeModel () {}


    public Integer getIdBike() {
        return idBike;
    }

    public void setIdBike(Integer idBike) {
        this.idBike = idBike;
    }

    public Boolean getIdDamaged() {
        return idDamaged;
    }

    public void setIdDamaged(Boolean idDamaged) {
        this.idDamaged = idDamaged;
    }

    public GregorianCalendar getDatePurchase() {
        return datePurchase;
    }

    public void setDatePurchase(GregorianCalendar datePurchase) {
        this.datePurchase = datePurchase;
    }

    public EntrepriseModel getEntrepriseModel() {
        return entrepriseModel;
    }

    public void setEntrepriseModel(EntrepriseModel entrepriseModel) {
        this.entrepriseModel = entrepriseModel;
    }

    public StationModel getStationModel() {
        return stationModel;
    }

    public void setStationModel(StationModel stationModel) {
        this.stationModel = stationModel;
    }
}
