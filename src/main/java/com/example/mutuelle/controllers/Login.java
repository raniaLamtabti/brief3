package com.example.mutuelle.controllers;

import com.example.mutuelle.DAO.ConnectionClass;
import com.example.mutuelle.DAO.Official;
import com.example.mutuelle.HelloApplication;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class Login {
    public Login() {

    }

    @FXML
    private Button button;

    @FXML
    private TextField email;

    @FXML
    private TextField password;

    @FXML
    private Label message;

    public void checkLogin(){
        HelloApplication m = new HelloApplication();
        String email=this.email.getText();
        String password=this.password.getText();

        try
        {
            ConnectionClass c=new ConnectionClass();
            c.getConnection();

            Official official=new Official();


                if((this.email.getText().isEmpty() || this.password.getText().isEmpty())){
                    message.setText("Please fill all the fields");

                } else if (official.Login(email,password)){
                    message.setText("Success!");
                    m.changeScene("Home-view.fxml");

                } else {
                    message.setText("Wrong email or password");
                }



        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
