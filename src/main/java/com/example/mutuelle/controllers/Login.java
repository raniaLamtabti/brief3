package com.example.mutuelle.controllers;

import at.favre.lib.crypto.bcrypt.BCrypt;
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
import org.apache.log4j.*;



public class Login {
    static final Logger log = Logger.getLogger(Login.class.getName());
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
    Official officials=new Official();
    public boolean decrypt(String email,String password) {

        //String password = "5B2yubZU";
        try {
            //String bcryptHashString = BCrypt.withDefaults().hashToString(12, password.toCharArray());
            BCrypt.Result result = BCrypt.verifyer().verify(password.toCharArray(), officials.getPassword(email));
            // result.verified == true
            System.out.println(result.verified);
           // System.out.println(bcryptHashString);
            return result.verified;

        }catch (Exception e) {
            e.printStackTrace();
            return false;
        }


    }


    public void checkLogin(){
        HelloApplication m = new HelloApplication();
        String email=this.email.getText();
        String password=this.password.getText();
        decrypt(email,password);


        try
        {
            ConnectionClass c=new ConnectionClass();
            c.getConnection();

            Official official=new Official();


                if((this.email.getText().isEmpty() || this.password.getText().isEmpty())){
                    message.setText("Please fill all the fields");

                } else if (decrypt(email,password)){
                    message.setText("Success!");
                    m.changeScene("Home-view.fxml");
                    log.info("Logged in");

                } else {
                    message.setText("Wrong email or password");
                    log.error("Wrong email or password");
                }



        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
