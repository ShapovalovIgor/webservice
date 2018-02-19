package ru.shapovalov.WebService.Model;

public class User {
    private String name;
    private String password;
    private double sallary;

    public User() {
    }

    public User(String name, String password, double sallary) {
        this.name = name;
        this.password = password;
        this.sallary = sallary;
    }

    public User(String login, String password) {
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public double getSallary() {
        return sallary;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setSallary(double sallary) {
        this.sallary = sallary;
    }
}
