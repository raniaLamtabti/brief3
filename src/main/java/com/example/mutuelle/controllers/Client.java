package com.example.mutuelle.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.MenuButton;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;

import java.sql.Date;


public class Client {
    private String address;
    private String phone;
    private String firstname;
    private Date hire_date;
    private String name;
    private String lastname;
    private String cin;
    private String passport;
    private String email;
    private String badge;

    public Client() {
    }

    public Client(String address, String phone, String firstname, Date hire_date, String name, String lastname, String cin, String passport, String badge, String email) {
        this.address = address;
        this.phone = phone;
        this.firstname = firstname;
        this.hire_date = hire_date;
        this.name = name;
        this.lastname = lastname;
        this.cin = cin;
        this.passport = passport;
        this.badge = badge;
        this.email = email;
    }




    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public Date getHire_date() {
        return hire_date;
    }

    public void setHire_date(Date hire_date) {
        this.hire_date = hire_date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getCin() {
        return cin;
    }

    public void setCin(String cin) {
        this.cin = cin;
    }

    public String getPassport() {
        return passport;
    }

    public void setPassport(String passport) {
        this.passport = passport;
    }

    public String getBadge() {
        return badge;
    }

    public void setBadge(String badge) {
        this.badge = badge;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "User{" +
                "address='" + address + '\'' +
                ", phone='" + phone + '\'' +
                ", firstname='" + firstname + '\'' +
                ", hire_date=" + hire_date +
                ", name='" + name + '\'' +
                ", lastname='" + lastname + '\'' +
                ", cin='" + cin + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
