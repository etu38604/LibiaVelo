package ModelPackage;

import java.util.GregorianCalendar;

public class StationModel {

    private Integer idStation;
    private Integer nbBikeMinWarn;
    private Integer nbBikeMinContr;
    private Integer nbBikeMaxWarn;
    private Integer nbBikeMaxContr;
    private String labelStation;
    private String coordGPS;
    private String street;
    private String zone;
    private GregorianCalendar dateCreation;
    private Boolean isCover;
    private LocalityModel localityModel;

    public StationModel () {}

    public Integer getIdStation() {
        return idStation;
    }

    public void setIdStation(Integer idStation) {
        this.idStation = idStation;
    }

    public Integer getNbBikeMinWarn() {
        return nbBikeMinWarn;
    }

    public void setNbBikeMinWarn(Integer nbBikeMinWarn) {
        this.nbBikeMinWarn = nbBikeMinWarn;
    }

    public Integer getNbBikeMinContr() {
        return nbBikeMinContr;
    }

    public void setNbBikeMinContr(Integer nbBikeMinContr) {
        this.nbBikeMinContr = nbBikeMinContr;
    }

    public Integer getNbBikeMaxWarn() {
        return nbBikeMaxWarn;
    }

    public void setNbBikeMaxWarn(Integer nbBikeMaxWarn) {
        this.nbBikeMaxWarn = nbBikeMaxWarn;
    }

    public Integer getNbBikeMaxContr() {
        return nbBikeMaxContr;
    }

    public void setNbBikeMaxContr(Integer nbBikeMaxContr) {
        this.nbBikeMaxContr = nbBikeMaxContr;
    }

    public String getLabelStation() {
        return labelStation;
    }

    public void setLabelStation(String labelStation) {
        this.labelStation = labelStation;
    }

    public String getCoordGPS() {
        return coordGPS;
    }

    public void setCoordGPS(String coordGPS) {
        this.coordGPS = coordGPS;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public GregorianCalendar getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(GregorianCalendar dateCreation) {
        this.dateCreation = dateCreation;
    }

    public Boolean getCover() {
        return isCover;
    }

    public void setCover(Boolean cover) {
        isCover = cover;
    }

    public LocalityModel getLocalityModel() {
        return localityModel;
    }

    public void setLocalityModel(LocalityModel LocalityModel) {
        this.localityModel = LocalityModel;
    }

    public String getZone() {
        return zone;
    }

    public void setZone(String zone) {
        this.zone = zone;
    }
}
