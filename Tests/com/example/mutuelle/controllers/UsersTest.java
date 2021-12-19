package com.example.mutuelle.controllers;

import com.example.mutuelle.DAO.Clients;
import javafx.fxml.FXML;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.sql.Date;

import static org.junit.jupiter.api.Assertions.*;

class UsersTest {

    @Test
    void create() {
      Client client=new Client("safi","1233456789","rania",new Date(12-12-2020),"youcode","lamtabti","HH123456",null,"1234899","s@g.com");
      Clients c=new Clients();
        c.Store(client);
        Assertions.assertEquals(312,c.getClients().size());

    }

    @Test
    void search() {
        Clients c=new Clients();
        Assertions.assertEquals(1,c.filter("ET900812").size());
    }

    @Test
    void getAll(){
        Clients c=new Clients();
        Assertions.assertEquals(308,c.getClients().size());
    }
}