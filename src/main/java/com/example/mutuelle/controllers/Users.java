package com.example.mutuelle.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.Objects;
import java.util.PropertyPermission;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Users implements Initializable {
    public Users() {

    }


    @FXML
    private ChoiceBox<String> country_list = new ChoiceBox<String>();

    @FXML
    private TextField address;

    @FXML
    private TextField phone;

    @FXML
    private TextField firstname;

    @FXML
    private RadioButton passport;

    @FXML
    private RadioButton cin;

    @FXML
    private ToggleGroup identifient;

    @FXML
    private DatePicker date;

    @FXML
    private TextField name;

    @FXML
    private TextField lastname;

    @FXML
    private TextField number;

    @FXML
    private TextField email;

    @FXML
    private Label eName;

    @FXML
    private Label eFname;

    @FXML
    private Label eLname;

    @FXML
    private Label eEmail;

    @FXML
    private Label eNumber;

    @FXML
    private Label ePhone;

    @FXML
    private Label eDate;

    @FXML
    private Label eAddress;

    @FXML
    private TableView<User> table_users;

    @FXML
    private TableColumn<User, String> c_address;

    @FXML
    private TableColumn<User, LocalDate> c_date;

    @FXML
    private TableColumn<User, String> c_email;

    @FXML
    private TableColumn<User, String> c_fname;

    @FXML
    private TableColumn<User, String> c_lname;

    @FXML
    private TableColumn<User, String> c_name;

    @FXML
    private TableColumn<User, String> c_number;

    @FXML
    private TableColumn<User, String> c_phone;

    ObservableList<User> list = FXCollections.observableArrayList();

    public void create(){
        User user = new User();

        String regexPhone = "[0-9]+";
        String regexEmail = "^(.+)@(\\S+)$";
        String regexCin = "[a-zA-Z]{2}\\d{6}";
        String regexPassport = "[a-zA-Z]{2}\\d{7}";

        Pattern p = Pattern.compile(regexPhone);
        Pattern e = Pattern.compile(regexEmail);
        Pattern c = Pattern.compile(regexCin);
        Pattern ps = Pattern.compile(regexPassport);

        Matcher mp = p.matcher(this.phone.getText());
        Matcher me = e.matcher(this.email.getText());
        Matcher mc = c.matcher(this.number.getText());
        Matcher mps = ps.matcher(this.number.getText());

        RadioButton selectedRadioButton = (RadioButton) identifient.getSelectedToggle();
        String toogleGroupValue = selectedRadioButton.getText();

        boolean error = false;

        if (this.name.getLength() > 50 || this.name.getText().isEmpty() ){
            this.eName.setText("*should not be empty should be less than 50 character");
            error = true;
        }else{
            this.eName.setText("");
            user.setName(this.name.getText());
        }

        if (this.firstname.getLength() > 50 || this.firstname.getText().isEmpty()){
            this.eFname.setText("*should not be empty should be less than 50 character");
            error = true;
        }else{
            this.eFname.setText("");
            user.setFirstname(this.firstname.getText());
        }

        if (this.lastname.getLength() > 50 || this.lastname.getText().isEmpty()){
            this.eLname.setText("*should not be empty should be less than 50 character");
            error = true;
        }else{
            this.eLname.setText("");
            user.setLastname(this.lastname.getText());
        }

        if (this.phone.getText().isEmpty() || this.phone.getLength() < 9 || !mp.matches()){
            this.ePhone.setText("*should not be empty should be less than 9 character and only containe numbers");
            error = true;
        }else{
            this.ePhone.setText("");
            user.setPhone(this.country_list.getSelectionModel().getSelectedItem() +""+ this.phone.getText());
        }

        if (this.email.getText().isEmpty() || !me.matches()){
            this.eEmail.setText("*should not be empty should be an email");
            error = true;
        }else{
            this.eEmail.setText("");
            user.setEmail(this.email.getText());
        }

        if (Objects.equals(toogleGroupValue, "CIN")){
            if (this.number.getText().isEmpty() || !mc.matches()){
                this.eNumber.setText("*should containe two alphabit and 6 digit");
                error = true;
            }else{
                user.setNumber("CIN: "+this.number.getText());
                this.eNumber.setText("");
            }
        }else if(Objects.equals(toogleGroupValue, "Passport")){
            if (this.number.getText().isEmpty() || !mps.matches()){
                this.eNumber.setText("*should containe two alphabit and 7 digit");
                error = true;
            }else{
                user.setNumber("Passport: "+this.number.getText());
                this.eNumber.setText("");
            }
        }

        if (this.address.getText().isEmpty()){
            this.eAddress.setText("*should not be empty");
            error = true;
        }else{
            this.eAddress.setText("");
            user.setAddress(this.address.getText());
        }

        if (this.date.getValue() == null){
            this.eDate.setText("*should not be empty");
            error = true;
        }else{
            this.eDate.setText("");
            user.setDate(this.date.getValue());
        }

        if (!error) {
            list.add(user);
        }
        System.out.println(list);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        c_name.setCellValueFactory(new PropertyValueFactory<User, String>("name"));
        c_number.setCellValueFactory(new PropertyValueFactory<User, String>("number"));
        c_phone.setCellValueFactory(new PropertyValueFactory<User, String>("phone"));
        c_lname.setCellValueFactory(new PropertyValueFactory<User, String>("lastname"));
        c_fname.setCellValueFactory(new PropertyValueFactory<User, String>("firstname"));
        c_address.setCellValueFactory(new PropertyValueFactory<User, String>("address"));
        c_email.setCellValueFactory(new PropertyValueFactory<User, String>("email"));
        c_date.setCellValueFactory(new PropertyValueFactory<User, LocalDate>("date"));

        table_users.setItems(list);


        JSONParser jsonParser = new JSONParser();
        try (FileReader reader = new FileReader("C:\\Users\\adm\\IdeaProjects\\Mutuelle\\src\\main\\resources\\com\\example\\mutuelle\\code.json"))
        {
            //Read JSON file
            Object obj = jsonParser.parse(reader);

            JSONArray country = (JSONArray) obj;

            for (Object o : country) {
                JSONObject country_obj = (JSONObject) o;
                String country_code = (String) country_obj.get("dial_code");

                country_list.getItems().add(country_code);
            }


        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
    }
}
