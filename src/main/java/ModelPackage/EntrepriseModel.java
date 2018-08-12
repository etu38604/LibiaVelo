package ModelPackage;

public class EntrepriseModel {

    private Integer idEntreprise;
    private String labelEntreprise;
    private String location;

    public EntrepriseModel () {}

    public Integer getIdEntreprise() {
        return idEntreprise;
    }

    public void setIdEntreprise(Integer idEntreprise) {
        this.idEntreprise = idEntreprise;
    }

    public String getLabelEntreprise() {
        return labelEntreprise;
    }

    public void setLabelEntreprise(String labelEntreprise) {
        this.labelEntreprise = labelEntreprise;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
