package ModelPackage;

import java.util.GregorianCalendar;
import java.util.List;

public class EmployeeModel {

    private Integer idEmployee;
    private Integer phonePrivate;
    private Integer phonePro;
    private Integer streetNumber;
    private String lastName;
    private String firstName;
    private String initialNameSupp;
    private String mail;
    private String street;
    private String workType;
    private GregorianCalendar dateHiring;
    private GregorianCalendar birthday;
    private Boolean isPartTimeWork;
    private Boolean isDriverSpecialLicense;
    private Boolean isZoneInCharge;
    private LocalityModel localityModel;
    private StationModel stationModel;
    private WorkShopModel workShopModel;
    private EmployeeModel inCharge;

    public EmployeeModel () {}

    public Integer getIdEmployee() {
        return idEmployee;
    }

    public void setIdEmployee(Integer idEmployee) {
        this.idEmployee = idEmployee;
    }

    public Integer getPhonePrivate() {
        return phonePrivate;
    }

    public void setPhonePrivate(Integer phonePrivate) {
        this.phonePrivate = phonePrivate;
    }

    public Integer getPhonePro() {
        return phonePro;
    }

    public void setPhonePro(Integer phonePro) {
        this.phonePro = phonePro;
    }

    public Integer getStreetNumber() {
        return streetNumber;
    }

    public void setStreetNumber(Integer streetNumber) {
        this.streetNumber = streetNumber;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getInitialNameSupp() {
        return initialNameSupp;
    }

    public void setInitialNameSupp(String initialNameSupp) {
        this.initialNameSupp = initialNameSupp;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }


    public GregorianCalendar getDateHiring() {
        return dateHiring;
    }

    public void setDateHiring(GregorianCalendar dateHiring) {
        this.dateHiring = dateHiring;
    }

    public GregorianCalendar getBirthday() {
        return birthday;
    }

    public void setBirthday(GregorianCalendar birthday) {
        this.birthday = birthday;
    }

    public Boolean getPartTimeWork() {
        return isPartTimeWork;
    }

    public void setPartTimeWork(Boolean partTimeWork) {
        isPartTimeWork = partTimeWork;
    }

    public Boolean getDriverSpecialLicense() {
        return isDriverSpecialLicense;
    }

    public void setDriverSpecialLicense(Boolean driverSpecialLicense) {
        isDriverSpecialLicense = driverSpecialLicense;
    }

    public Boolean getZoneInCharge() {
        return isZoneInCharge;
    }

    public void setZoneInCharge(Boolean zoneInCharge) {
        isZoneInCharge = zoneInCharge;
    }


    public LocalityModel getLocalityModel() {
        return localityModel;
    }

    public void setLocalityModel(LocalityModel LocalityModel) {
        this.localityModel = LocalityModel;
    }

    public StationModel getStationModel() {
        return stationModel;
    }

    public void setStationModel(StationModel stationModel) {
        this.stationModel = stationModel;
    }

    public WorkShopModel getWorkShopModel() {
        return workShopModel;
    }

    public void setWorkShopModel(WorkShopModel workShopModel) {
        this.workShopModel = workShopModel;
    }

    public String getWorkType() {
        return workType;
    }

    public void setWorkType(String workType) {
        this.workType = workType;
    }


    public EmployeeModel getInCharge() {
        return inCharge;
    }

    public void setInCharge(EmployeeModel inCharge) {
        this.inCharge = inCharge;
    }
}
