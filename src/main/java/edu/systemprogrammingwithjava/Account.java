package edu.systemprogrammingwithjava;

public class Account {
    String holderName;
    String country;
    Integer balance;
    String type;

    public Account(String holderName, String country, Integer balance, String type){
        this.holderName = holderName;
        this.country = country;
        this.balance = balance;
        this.type = type;
    }
}
