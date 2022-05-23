package healthinspectorproject.healthinspectorr;

public class personInfoModel {
    String username,name,surname,phone,adress,email;

    public personInfoModel(String username, String name, String surname, String phone, String adress, String email) {
        this.username=username;
        this.name = name;
        this.surname = surname;
        this.phone = phone;
        this.adress = adress;
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getPhone() {
        return phone;
    }

    public String getAdress() {
        return adress;
    }

    public String getEmail() {
        return email;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
