package ModelPackage;

import java.util.GregorianCalendar;

public class ReparationRecordModel {

    private Integer idReparation;
    private GregorianCalendar dateBegin;
    private GregorianCalendar dateEnd;
    private String note;
    private String workOrder;
    private Boolean isDownGraded;
    private BikeModel bikeModel;
    private WorkShopModel workShopModel;

    public ReparationRecordModel () {}

    public Integer getIdReparation() {
        return idReparation;
    }

    public void setIdReparation(Integer idReparation) {
        this.idReparation = idReparation;
    }

    public GregorianCalendar getDateBegin() {
        return dateBegin;
    }

    public void setDateBegin(GregorianCalendar dateBegin) {
        this.dateBegin = dateBegin;
    }

    public GregorianCalendar getDateEnd() {
        return dateEnd;
    }

    public void setDateEnd(GregorianCalendar dateEnd) {
        this.dateEnd = dateEnd;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getWorkOrder() {
        return workOrder;
    }

    public void setWorkOrder(String workOrder) {
        this.workOrder = workOrder;
    }

    public Boolean getDownGraded() {
        return isDownGraded;
    }

    public void setDownGraded(Boolean downGraded) {
        isDownGraded = downGraded;
    }

    public BikeModel getBikeModel() {
        return bikeModel;
    }

    public void setBikeModel(BikeModel bikeModel) {
        this.bikeModel = bikeModel;
    }

    public WorkShopModel getWorkShopModel() {
        return workShopModel;
    }

    public void setWorkShopModel(WorkShopModel workShopModel) {
        this.workShopModel = workShopModel;
    }
}
