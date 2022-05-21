package healthinspectorproject.healthinspectorr;

public class PropertySearchModel {

    Integer propertyID;
    String managerUsername,companyName,propertyName,adress,phoneNumber,status,description;

    public PropertySearchModel(Integer propertyID, String managerUsername, String companyName, String propertyName, String adress, String phoneNumber, String status, String description) {
        this.propertyID = propertyID;
        this.managerUsername = managerUsername;
        this.companyName = companyName;
        this.propertyName = propertyName;
        this.adress = adress;
        this.phoneNumber = phoneNumber;
        this.status = status;
        this.description = description;
    }

    public Integer getPropertyID() {
        return propertyID;
    }

    public String getManagerUsername() {
        return managerUsername;
    }

    public String getCompanyName() {
        return companyName;
    }

    public String getPropertyName() {
        return propertyName;
    }

    public String getAdress() {
        return adress;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getStatus() {
        return status;
    }

    public String getDescription() {
        return description;
    }

    public void setPropertyID(Integer propertyID) {
        this.propertyID = propertyID;
    }

    public void setManagerUsername(String managerUsername) {
        this.managerUsername = managerUsername;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public void setPropertyName(String propertyName) {
        this.propertyName = propertyName;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
