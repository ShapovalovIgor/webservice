package ru.shapovalov.WebService.Model;

public class User {
    private String name;
    private String password;
    private Double balance;

    public User() {
    }

    public User(String name, String password, double balance) {
        this.name = name;
        this.password = password;
        this.balance = balance;
    }

    public User(String name, String password) {
        this.name = name;
        this.password = password;
        this.balance = 0.0;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public Double getBalance() {
        return balance;
    }
}
