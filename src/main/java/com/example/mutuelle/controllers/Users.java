package com.example.mutuelle.controllers;
import javax.mail.internet.*;
import java.util.Properties;
import javax.mail.*;
import com.example.mutuelle.DAO.Clients;
import com.example.mutuelle.DAO.ConnectionClass;
import com.example.mutuelle.HelloApplication;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.util.Objects;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Users implements Initializable {
    public Users() {

    }


    @FXML private ChoiceBox<String> country_list = new ChoiceBox<String>();

    @FXML private TextField address;

    @FXML private TextField phone;

    @FXML private TextField firstname;

    @FXML private RadioButton passport;

    @FXML private RadioButton cin;

    @FXML private ToggleGroup identifient;

    @FXML private DatePicker date;

    @FXML private TextField name;

    @FXML private TextField lastname;

    @FXML private TextField number;

    @FXML private TextField email;

    @FXML private TextField badge;

    @FXML private Label eName;

    @FXML private Label eFname;

    @FXML private Label eLname;

    @FXML private Label eEmail;

    @FXML private Label eNumber;

    @FXML private Label ePhone;

    @FXML private Label eDate;

    @FXML private Label eAddress;

    @FXML private Label eBadge;

    @FXML private TableView<Client> table_users;

    @FXML private TableColumn<Client, String> c_address;

    @FXML private TableColumn<Client, Date> c_date;

    @FXML private TableColumn<Client, String> c_email;

    @FXML private TableColumn<Client, String> c_fname;

    @FXML private TableColumn<Client, String> c_lname;

    @FXML private TableColumn<Client, String> c_name;

    @FXML private TableColumn<Client, String> c_cin;

    @FXML private TableColumn<Client, String> c_phone;

    @FXML private TableColumn<Client, String> c_passport;

    @FXML private TableColumn<Client, String> c_badge;

    @FXML private ComboBox<String> companies_list;

    @FXML private TextField s_value;

    public void create(){
        Client client = new Client();

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
            client.setName(this.name.getText());
        }

        if (this.firstname.getLength() > 50 || this.firstname.getText().isEmpty()){
            this.eFname.setText("*should not be empty should be less than 50 character");
            error = true;
        }else{
            this.eFname.setText("");
            client.setFirstname(this.firstname.getText());
        }

        if (this.lastname.getLength() > 50 || this.lastname.getText().isEmpty()){
            this.eLname.setText("*should not be empty should be less than 50 character");
            error = true;
        }else{
            this.eLname.setText("");
            client.setLastname(this.lastname.getText());
        }

        if (this.phone.getText().isEmpty() || this.phone.getLength() < 9 || !mp.matches()){
            this.ePhone.setText("*should not be empty should be less than 9 character and only containe numbers");
            error = true;
        }else{
            this.ePhone.setText("");
            client.setPhone(this.country_list.getSelectionModel().getSelectedItem() +""+ this.phone.getText());
        }

        if (this.email.getText().isEmpty() || !me.matches()){
            this.eEmail.setText("*should not be empty should be an email");
            error = true;
        }else{
            this.eEmail.setText("");
            client.setEmail(this.email.getText());
        }

        if (Objects.equals(toogleGroupValue, "CIN")){
            if (this.number.getText().isEmpty() || !mc.matches()){
                this.eNumber.setText("*should containe two alphabit and 6 digit");
                error = true;
            }else{
                client.setPassport("");
                client.setCin(this.number.getText());
                this.eNumber.setText("");
            }
        }else if(Objects.equals(toogleGroupValue, "Passport")){
            if (this.number.getText().isEmpty() || !mps.matches()){
                this.eNumber.setText("*should containe two alphabit and 7 digit");
                error = true;
            }else{
                client.setCin("");
                client.setPassport(this.number.getText());
                this.eNumber.setText("");
            }
        }

        if (this.address.getText().isEmpty()){
            this.eAddress.setText("*should not be empty");
            error = true;
        }else{
            this.eAddress.setText("");
            client.setAddress(this.address.getText());
        }

        if (this.date.getValue() == null){
            this.eDate.setText("*should not be empty");
            error = true;
        }else{
            this.eDate.setText("");
            client.setHire_date(Date.valueOf(this.date.getValue().toString()));
        }

        if (this.badge.getText() == null){
            this.eBadge.setText("*should not be empty");
            error = true;
        }else{
            this.eBadge.setText("");
            client.setBadge(this.badge.getText());
        }

        ConnectionClass cn=new ConnectionClass();
        cn.getConnection();

        Clients newClient=new Clients();

        if (!error) {
            newClient.Store(client);
            this.address.setText("");
            this.number.setText("");
            this.name.setText("");
            this.phone.setText("");
            this.passport.setText("");
            this.lastname.setText("");
            this.firstname.setText("");
            this.firstname.setText("");
            this.email.setText("");
            this.date.setValue(null);
            this.country_list.setValue(null);
            this.badge.setText("");

            Mail.send(
                            "raniatest429@gmail.com",
                            "test@555",
                            "rania.lamtabti@gmail.com",
                            "Bienvenu sur WayToLearnX",
                            "mail de test!"
                    );
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Clients clients = new Clients();

        c_badge.setCellValueFactory(new PropertyValueFactory<Client, String>("badge"));
        c_name.setCellValueFactory(new PropertyValueFactory<Client, String>("name"));
        c_cin.setCellValueFactory(new PropertyValueFactory<Client, String>("cin"));
        c_passport.setCellValueFactory(new PropertyValueFactory<Client, String>("passport"));
        c_phone.setCellValueFactory(new PropertyValueFactory<Client, String>("phone"));
        c_lname.setCellValueFactory(new PropertyValueFactory<Client, String>("lastname"));
        c_fname.setCellValueFactory(new PropertyValueFactory<Client, String>("firstname"));
        c_address.setCellValueFactory(new PropertyValueFactory<Client, String>("address"));
        c_email.setCellValueFactory(new PropertyValueFactory<Client, String>("email"));
        c_date.setCellValueFactory(new PropertyValueFactory<Client, Date>("hire_date"));

        table_users.setItems(clients.getClients());

        ObservableList<String> companies = FXCollections.observableArrayList();
        companies = clients.getCompanies();

        this.companies_list.setItems(companies);

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

    public void search(){
        String searchValue = this.s_value.getText();
        Clients clients = new Clients();
        table_users.getItems().clear();
        table_users.setItems(clients.filter(searchValue));
    }


    public void findByCompany(){
        String companyName = this.companies_list.getSelectionModel().getSelectedItem();
        Clients clients = new Clients();
        table_users.getItems().clear();
        table_users.setItems(clients.filterByCompany(companyName));
    }

    public void goToChart() throws IOException {
        HelloApplication m = new HelloApplication();
        m.changeScene("Chart-view.fxml");
    }



}
