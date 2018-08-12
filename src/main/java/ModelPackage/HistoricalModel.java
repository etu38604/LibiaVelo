package ModelPackage;

import java.util.GregorianCalendar;

public class HistoricalModel {

    private GregorianCalendar dateArrival;
    private GregorianCalendar dateLeaving;
    private StationModel stationModel;
    private BikeModel bikeModel;

    public HistoricalModel(){}

    public GregorianCalendar getDateArrival() {
        return dateArrival;
    }

    public void setDateArrival(GregorianCalendar dateArrival) {
        this.dateArrival = dateArrival;
    }

    public GregorianCalendar getDateLeaving() {
        return dateLeaving;
    }

    public void setDateLeaving(GregorianCalendar dateLeaving) {
        this.dateLeaving = dateLeaving;
    }

    public StationModel getStationModel() {
        return stationModel;
    }

    public void setStationModel(StationModel stationModel) {
        this.stationModel = stationModel;
    }

    public BikeModel getBikeModel() {
        return bikeModel;
    }

    public void setBikeModel(BikeModel bikeModel) {
        this.bikeModel = bikeModel;
    }
}
