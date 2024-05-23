package model;

import java.util.*;
import java.sql.*;
import java.sql.Date;

public enum FeedbacksDAO {
    instance;

    private final String host = "127.0.0.1:3306";
    private final String user = "root";
    private final String password = "";
    
    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection("jdbc:mysql://" + host + "/cartymeats?" + "user=" + user + "&password=" + password);
    }
    
 // Method to add a new feedback
    public void addFeedback(Feedbacks feedback) {
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement("INSERT INTO feedback (title, description, client_id, product_id) VALUES (?, ?, ?, ?)")) {

            statement.setString(1, feedback.getTitle());
            statement.setString(2, feedback.getDescription());
            statement.setInt(3, feedback.getClientId());
            statement.setInt(4, feedback.getProductId());

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    // Method to retrieve all feedbacks
    public List<Feedbacks> getAllFeedbacks() {
        List<Feedbacks> feedbacks = new ArrayList<>();
        try (Connection connection = getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT f.feed_id, f.title, f.description, f.feed_date," +
                                                            "c.client_name, p.prod_name " +
                                                            "FROM feedback f " +
                                                            "INNER JOIN clients c ON f.client_id = c.client_id " +
                                                            "INNER JOIN product p ON f.prod_id = p.prod_id")) {

            while (resultSet.next()) {
                int feedbackId = resultSet.getInt("feed_id");
                String title = resultSet.getString("title");
                String description = resultSet.getString("description");
                Date date = resultSet.getDate("feed_date");
                String clientName = resultSet.getString("client_name");
                String productName = resultSet.getString("prod_name");

                Feedbacks feedback = new Feedbacks(feedbackId, title, description, date, clientName, productName);
                feedbacks.add(feedback);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return feedbacks;
    }
    
    // Method to update an existing feedback
    public void updateFeedback(Feedbacks feedback) {
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement("UPDATE feedback SET title=?, description=?, client_id=?, product_id=? WHERE feed_id=?")) {

            statement.setString(1, feedback.getTitle());
            statement.setString(2, feedback.getDescription());
            statement.setInt(3, feedback.getClientId());
            statement.setInt(4, feedback.getProductId());
            statement.setInt(5, feedback.getFeedbackId());

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    // Method to delete a feedback
    public void deleteFeedback(int feedbackId) {
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement("DELETE FROM feedbacks WHERE feed_id=?")) {

            statement.setInt(1, feedbackId);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
