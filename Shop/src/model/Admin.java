package model;

public class Admin extends User{
    protected String role;
    public Admin(String username, String password, String email){
        this.username = username;
        this.password = password;
        this.email = email;
    }
}
