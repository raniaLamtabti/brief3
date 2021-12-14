package com.example.mutuelle.DAO;
import java.sql.*;

public class Official {

    public boolean Login(String email, String password) {
        ConnectionClass connectionClass = new ConnectionClass();

        try {
            String sql = "SELECT * FROM officials WHERE email ='" + email +"' and password = '" + password + "'";
            PreparedStatement statement = connectionClass.getConnection().prepareStatement(sql);

            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return true;
            }else{
                return false;
            }
        }catch (SQLException e){
            e.printStackTrace();
            return false;
        }
    }
}
