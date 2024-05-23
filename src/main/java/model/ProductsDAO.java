package model;

import java.sql.*;
import java.util.*;

public enum ProductsDAO {
    instance;
    static Connection con = null;
    static Statement stmt = null;
    static ResultSet rs = null;

    final private String host = "127.0.0.1:3306";
    final private String user = "root";
    final private String password = "";

    public Connection getConnection() throws Exception {

        // Load MySQL Driver
        Class.forName("com.mysql.cj.jdbc.Driver");
        // Setup the connection with the DB
        con = DriverManager.getConnection("jdbc:mysql://" + host + "/cartymeats?" + "user=" + user + "&password=" + password);
        return con;

    }

    public void createProduct(Products product) throws Exception {
        Connection con = getConnection();

        // Create the MySQL statement for inserting the product
        try {
            PreparedStatement pstmt = con.prepareStatement("INSERT INTO product" +
                    "  (prod_name, prod_pic, category, description, prod_stock, price) VALUES " +
                    " (?, ?, ?, ?, ?);");

            pstmt.setString(1, product.getProdName());
            pstmt.setString(2, product.getProdPic());
            pstmt.setString(3, product.getCategory().name());
            pstmt.setString(4, product.getDescription());
            pstmt.setInt(5, product.getProdStock());
            pstmt.setDouble(6, product.getPrice());

            pstmt.executeUpdate();

        } catch (Exception e) {
            // process sql exception
            e.printStackTrace();
        }
    }

    public List<Products> fetchProducts() throws Exception {
        List<Products> products = new ArrayList<>();
        try (Connection con = getConnection();
             PreparedStatement pstmt = con.prepareStatement("SELECT p.*, GROUP_CONCAT(c.client_name) AS client_names " +
                     "FROM product p " +
                     "LEFT JOIN product_client pc ON p.prod_id = pc.prod_id " +
                     "LEFT JOIN clients c ON pc.client_id = c.client_id GROUP " +
                     "BY p.prod_id");
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                int prod_id = rs.getInt("prod_id");
                String prod_name = rs.getString("prod_name");
                String prod_pic = rs.getString("prod_pic");
                Category category = Category.valueOf(rs.getString("category").toUpperCase());
                String description = rs.getString("description");
                int prod_stock = rs.getInt("prod_stock");
                double price = rs.getDouble("price");
                String clientNames = rs.getString("client_names");

                // Initialize an empty list for client names
                List<String> clientNameList = new ArrayList<>();
                // Check if clientNames is not null before splitting
                if (clientNames != null && !clientNames.isEmpty()) {
                    // Split the client names into a list
                    clientNameList = Arrays.asList(clientNames.split(","));
                }

                Products product = new Products(prod_id, prod_name, prod_pic, category, description, prod_stock, price, clientNameList);
                products.add(product);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
        return products;
    }
    
    public Products fetchProdById(int id) throws Exception {
        try (Connection con = getConnection();
             PreparedStatement pstmt = con.prepareStatement("SELECT * FROM product WHERE prod_id = ?")){
        	 pstmt.setInt(1, id);
             ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
            	int prod_id = rs.getInt("prod_id");
                String prod_name = rs.getString("prod_name");
                String prod_pic = rs.getString("prod_pic");
                Category category = Category.valueOf(rs.getString("category").toUpperCase());
                String description = rs.getString("description");
                int prod_stock = rs.getInt("prod_stock");
                double price = rs.getDouble("price");

                Products product = new Products(prod_id, prod_name, prod_pic, category, description, prod_stock, price);
                return product;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
        return null;
    }

    public void updateProduct(Products product) throws Exception {
        try (Connection con = getConnection();
             PreparedStatement pstmt = con.prepareStatement("UPDATE product SET prod_name = ?, prod_pic = ?, category = ?, description = ?, prod_stock = ? WHERE prod_id = ?")) {

            pstmt.setString(1, product.getProdName());
            pstmt.setString(2, product.getProdPic());
            pstmt.setString(3, product.getCategory().name());
            pstmt.setString(4, product.getDescription());
            pstmt.setInt(5, product.getProdStock());
            pstmt.setInt(6, product.getProdId());

            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
    }

    public void deleteProduct(int productId) throws Exception {
        try (Connection con = getConnection();
             PreparedStatement pstmt = con.prepareStatement("DELETE FROM product WHERE prod_id = ?")) {

            pstmt.setInt(1, productId);

            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
    }
    
    public void addBuyer(String buyerId, String productId) throws Exception {
    	try (Connection con = getConnection();
    		PreparedStatement pstmt = con.prepareStatement("INSERT INTO product_client VALUES (?, ?)")){
    		pstmt.setString(1, productId);
    		pstmt.setString(2, buyerId);
    		
            pstmt.executeUpdate();

    	} catch (SQLException e) {
    		e.printStackTrace();
    		throw e;
    	}
    }

}
