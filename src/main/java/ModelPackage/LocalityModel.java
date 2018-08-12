package ModelPackage;

public class LocalityModel {

    private Integer idLocality;
    private Integer postalCode;
    private String labelLocality;

    public LocalityModel(){}

    public Integer getIdLocality() {
        return idLocality;
    }

    public void setIdLocality(Integer idLocality) {
        this.idLocality = idLocality;
    }

    public Integer getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(Integer postalCode) {
        this.postalCode = postalCode;
    }

    public String getLabelLocality() {
        return labelLocality;
    }

    public void setLabelLocality(String labelLocality) {
        this.labelLocality = labelLocality;
    }
}
