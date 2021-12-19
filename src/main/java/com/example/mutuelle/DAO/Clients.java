package com.example.mutuelle.DAO;

import com.example.mutuelle.HelloApplication;
import com.example.mutuelle.controllers.Client;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.IOException;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.HashMap;

public class Clients {
    public void Store(Client client) {
        ConnectionClass connectionClass = new ConnectionClass();

        try {
            String sql = "INSERT INTO client (work_badge,company_name,hire_date, firstname, lastname, cin, passport, phone, email, address) VALUES (?,?,?,?,?,?,?,?,?,?)";
            PreparedStatement statement = connectionClass.getConnection().prepareStatement(sql);
            statement.setString(1, client.getBadge());
            statement.setString(2, client.getName());
            statement.setDate(3, Date.valueOf(client.getHire_date().toString()));
            statement.setString(4, client.getFirstname());
            statement.setString(5, client.getLastname());
            statement.setString(6, client.getCin());
            statement.setString(7, client.getPassport());
            statement.setString(8, client.getPhone());
            statement.setString(9, client.getEmail());
            statement.setString(10, client.getAddress());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ObservableList<Client> getClients(){

        ConnectionClass connectionClass = new ConnectionClass();
        ObservableList<Client> clients = FXCollections.observableArrayList();
        try {
            String sql = "select * from Client";
            PreparedStatement statement = connectionClass.getConnection().prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                clients.add(new Client(
                        resultSet.getString("address"),
                        resultSet.getString("work_badge"),
                        resultSet.getString("firstname"),
                        resultSet.getDate("hire_date"),
                        resultSet.getString("lastname"),
                        resultSet.getString("email"),
                        resultSet.getString("phone"),
                        resultSet.getString("cin"),
                        resultSet.getString("passport"),
                        resultSet.getString("company_name")
                ));
            }
            if (resultSet.next()) {
                return clients;
            }else{
                return clients;
            }
        }catch (SQLException e) {
            e.printStackTrace();
            return clients;
        }
    }

    public ObservableList<Client> filter(String search){

        ConnectionClass connectionClass = new ConnectionClass();
        ObservableList<Client> clients = FXCollections.observableArrayList();
        try {
            /* String sql = "select * from Client WHERE company_name = '" + s_company + "'"; */
            String sql = "SELECT * FROM client WHERE cin LIKE'%" + search+ "%' OR firstname LIKE '%" + search + "%' OR lastname LIKE '%" + search + "%' OR email LIKE '%" + search + "%'";
            PreparedStatement statement = connectionClass.getConnection().prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                clients.add(new Client(
                        resultSet.getString("address"),
                        resultSet.getString("work_badge"),
                        resultSet.getString("firstname"),
                        resultSet.getDate("hire_date"),
                        resultSet.getString("lastname"),
                        resultSet.getString("email"),
                        resultSet.getString("phone"),
                        resultSet.getString("cin"),
                        resultSet.getString("passport"),
                        resultSet.getString("company_name")
                ));
            }
            if (resultSet.next()) {
                return clients;
            }else{
                return clients;
            }
        }catch (SQLException e) {
            e.printStackTrace();
            return clients;
        }
    }

    public ObservableList<Client> filterByCompany(String s_company){

        ConnectionClass connectionClass = new ConnectionClass();
        ObservableList<Client> clients = FXCollections.observableArrayList();
        try {
            String sql = "select * from Client WHERE company_name = '" + s_company + "'";
            PreparedStatement statement = connectionClass.getConnection().prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                clients.add(new Client(
                        resultSet.getString("address"),
                        resultSet.getString("work_badge"),
                        resultSet.getString("firstname"),
                        resultSet.getDate("hire_date"),
                        resultSet.getString("lastname"),
                        resultSet.getString("email"),
                        resultSet.getString("phone"),
                        resultSet.getString("cin"),
                        resultSet.getString("passport"),
                        resultSet.getString("company_name")
                ));
            }
            if (resultSet.next()) {
                return clients;
            }else{
                return clients;
            }
        }catch (SQLException e) {
            e.printStackTrace();
            return clients;
        }
    }

    public ObservableList<String> getCompanies(){

        ConnectionClass connectionClass = new ConnectionClass();
        ObservableList<String> company = FXCollections.observableArrayList();
        try {
            String sql = "SELECT DISTINCT company_name FROM Client";
            PreparedStatement statement = connectionClass.getConnection().prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                company.add(resultSet.getString("company_name"));
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return company;
    }

    public HashMap<String, Integer> clientsByDate(){

        ConnectionClass connectionClass = new ConnectionClass();
        HashMap<String, Integer> statistic = new HashMap<>();
        try {
            String sql = "select date(created_at),count(work_badge) from client group by date(created_at)";
            PreparedStatement statement = connectionClass.getConnection().prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                statistic.put(resultSet.getString(1) , resultSet.getInt(2));
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return statistic;
    }
}
