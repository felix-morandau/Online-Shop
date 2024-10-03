package model;

import repo.ClientProductRepo;

public class Client extends User{
    protected String name;
    protected int age;
    protected String phoneNr;

    public Client(String username, String password, String email, String name, int age, String phoneNr) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.name = name;
        this.age = age;
        this.phoneNr = phoneNr;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getPhoneNr() {
        return phoneNr;
    }

    public int getId(){
        return ClientProductRepo.getClientId(this.username);
    }
}
