package com.example.week9;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
//CLASS FOR SINGLE POST AUTOMATS
public class Post {
    //Attributes of single post automat
    String country = "";
    String address;
    String name;
    String availability;
    Date[][] openingdates = new Date[7][2]; //Matrix for opening and closing hour from mon to sun
    String city;
    String postalcode;

    //GETTERS AND SETTERS
    public Date[][] getOpeningdates() {
        return openingdates;
    }
    public void setOpeningdates(Date[][] openingdates) {
        this.openingdates = openingdates;
    }
    public String getPostalcode() {
        return postalcode;
    }
    public void setPostalcode(String postalcode) {
        this.postalcode = postalcode;
    }
    public String getCity() {
        return city;
    }
    public void setCity(String city) {
        this.city = city;
    }
    public String getCountry() {
        return country;
    }
    public void setCountry(String country) {
        this.country = country;
    }
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getAvailability() {
        return availability;
    }
    public void setAvailability(String availability) {
        this.availability = availability;
    }

    //CONSTRUCTOR GETS PARAMS STRAIGHT FROM XML
    Post(String name, String address, String country, String availability, String city, String postalcode) throws ParseException {
        this.country = country;
        this.address = address;
        this.name = name;
        this.availability = availability;
        this.city = city;
        this.postalcode = postalcode;
    }


    //SPINNNER override
    @Override
    public String toString() {
        return name + "  " + city;
    }






}
