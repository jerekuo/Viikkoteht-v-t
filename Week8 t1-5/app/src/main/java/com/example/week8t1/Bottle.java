package com.example.week8t1;

public class Bottle {
    private String name;
    private String manufacturer;
    private double total_energy;
    private double size;
    private double price;

    public Bottle() {
        name = "Pepsi Max";
        manufacturer = "Pepsi";
        total_energy = 0.3;
        size = 0.5;
        price = 1.80;
    }

    @Override
    public String toString() {
        return name+ " " + size + "l " + price + "€";
    }

    public Bottle(String nimi, String manuf, int totE, double siz, double pric) {
        name = nimi;
        manufacturer = manuf;
        total_energy = totE;
        size = siz;
        price = pric;

    }

    public void setPrice(float newPrice) {
        price = newPrice;
    }

    public double getSize() {
        return size;
    }

    public double getPrice() {
        return price;
    }

    public String getName() {
        return name;
    }

    public String getManufacturer() {
        return manufacturer;

    }

    public double getEnergy() {
        return total_energy;

    }

}
