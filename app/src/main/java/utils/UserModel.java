package utils;

public class UserModel {
    String name;
    String email;
    String date;
    String uid;

    public UserModel(String name, String email, String date,String uid) {
        this.name = name;
        this.email = email;
        this.date = date;
        this.uid = uid;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
