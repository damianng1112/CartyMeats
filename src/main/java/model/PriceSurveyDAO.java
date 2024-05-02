package model;

import java.sql.*;
import java.util.*;


public enum PriceSurveyDAO {
	instance;

    private final String host = "127.0.0.1:3306";
    private final String user = "root";
    private final String password = "";
    
    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection("jdbc:mysql://" + host + "/cartymeats?" + "user=" + user + "&password=" + password);
    }
    
    public List<PriceSurvey> getAllSurveys() throws SQLException {
        List<PriceSurvey> surveys = new ArrayList<>();
        try (Connection connection = getConnection();
        	Statement statement = connection.createStatement();
        	ResultSet resultSet = statement.executeQuery("SELECT * FROM priceSurvey")) {
            while (resultSet.next()) {
                PriceSurvey survey = new PriceSurvey();
                survey.setPsId(resultSet.getInt("ps_id"));
                survey.setLocation(resultSet.getString("location"));
                survey.setProduct(resultSet.getString("product"));
                survey.setPrice(resultSet.getDouble("price"));
                survey.setBarcode(resultSet.getString("barcode"));
                surveys.add(survey);
            }
        }
        return surveys;
    }
    
}
