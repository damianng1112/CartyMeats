package model;

import java.util.*;
import java.sql.*;

public enum ClientsDAO {
    instance;

    private final String host = "127.0.0.1:3306";
    private final String user = "root";
    private final String password = "";

    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection("jdbc:mysql://" + host + "/cartymeats?" + "user=" + user + "&password=" + password);
    }

    public void createClient(Clients client) throws SQLException {
        try (Connection con = getConnection();
             PreparedStatement pstmt = con.prepareStatement("INSERT INTO clients" +
                     "  (client_name, client_address, facing) VALUES " +
                     " (?, ?, ?)")) {

            pstmt.setString(1, client.getClient_name());
            pstmt.setString(2, client.getClient_address());
            pstmt.setString(3, client.getFacing());

            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
    }

    public List<Clients> fetchClients() throws SQLException {
        List<Clients> clients = new ArrayList<>();
        try (Connection con = getConnection();
             PreparedStatement pstmt = con.prepareStatement("SELECT * FROM clients");
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                String id = rs.getString("client_id");
                String name = rs.getString("client_name");
                String address = rs.getString("client_address");
                String facing = rs.getString("facing");

                Clients client = new Clients(id, name, address, facing);
                clients.add(client);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
        return clients;
    }
    
    public Clients fetchClientById(String id) throws SQLException {
        try (Connection con = getConnection();
             PreparedStatement pstmt = con.prepareStatement("SELECT * FROM clients WHERE client_id = ?")){
        	 pstmt.setString(1, id);
             ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                String client_id = rs.getString("client_id");
                String name = rs.getString("client_name");
                String address = rs.getString("client_address");
                String facing = rs.getString("facing");

                Clients client = new Clients(client_id, name, address, facing);
                return client;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
        return null;
    }
    
    public void updateClient(Clients client) throws SQLException {
        try (Connection con = getConnection();
             PreparedStatement pstmt = con.prepareStatement("UPDATE clients SET client_name = ?, client_address = ?, facing = ? WHERE client_id = ?")) {

        	pstmt.setString(1, client.getClient_name());
            pstmt.setString(2, client.getClient_address());
            pstmt.setString(3, client.getFacing());
            pstmt.setString(4, client.getId());

            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
    }

    public void deleteClient(String id) throws SQLException {
        try (Connection con = getConnection();
             PreparedStatement pstmt = con.prepareStatement("DELETE FROM clients WHERE client_id = ?")) {

            pstmt.setString(1, id);

            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
    } 

}
