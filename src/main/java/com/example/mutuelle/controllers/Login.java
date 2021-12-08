package com.example.mutuelle.controllers;

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
        JSONParser jsonParser = new JSONParser();
        try (FileReader reader = new FileReader("C:\\Users\\adm\\IdeaProjects\\Mutuelle\\src\\main\\resources\\com\\example\\mutuelle\\func.json"))
        {
            //Read JSON file
            Object obj = jsonParser.parse(reader);

            JSONArray employeeList = (JSONArray) obj;
            System.out.println(employeeList);

            for(int i = 0; i < employeeList.size(); i++) {
                JSONObject employee = (JSONObject) employeeList.get(i);
                String email = (String) employee.get("email");
                String password = (String) employee.get("password");

                if((this.email.getText().isEmpty() || this.password.getText().isEmpty())){
                    message.setText("Please fill all the fields");
                    break;
                } else if (email.equals(this.email.getText()) && password.equals(this.password.getText())){
                    message.setText("Success!");
                    m.changeScene("Home-view.fxml");
                    break;
                } else {
                    message.setText("Wrong email or password");
                }
            }


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}
