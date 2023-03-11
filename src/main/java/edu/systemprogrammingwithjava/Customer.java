package edu.systemprogrammingwithjava;

public class Customer {
    String name;
    String country;
    Integer age;
    String tier;
    Integer balance;

    public Customer(String name, String country, Integer age, Integer balance) {
        this.name = name;
        this.country = country;
        this.age = age;
        this.tier = "Tier 10";
        this.balance = balance;
    }

    public void setTier(String tier) {
        this.tier = tier;
    }

    @Override
    public String toString(){
        return "{ Name: "+name+", Country: "+country+", Age: "+age+", Tier: "+tier+", Balance: "+balance+"}";
    }
}
