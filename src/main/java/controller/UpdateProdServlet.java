package controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import model.*;

/**
 * Servlet implementation class UpdateProdServlet
 */
@WebServlet("/UpdateProdServlet")
public class UpdateProdServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateProdServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
	protected void doPut(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Extract product details from request body
        BufferedReader reader = request.getReader();
        StringBuilder requestBody = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            requestBody.append(line);
        }
        reader.close();

        // Convert request body JSON to a Java object
        Gson gson = new Gson();
        JsonObject jsonObject = gson.fromJson(requestBody.toString(), JsonObject.class);
        int prodId = jsonObject.get("prodId").getAsInt();
        String prodName = jsonObject.get("prodName").getAsString();
        String prodPic = jsonObject.get("prodPic").getAsString();
        String categoryString = jsonObject.get("category").getAsString();
        String description = jsonObject.get("description").getAsString();
        int stock = jsonObject.get("stock").getAsInt();
        double price = jsonObject.get("price").getAsDouble();

        // Map the string value of category to the corresponding enum value
        Category category = Category.valueOf(categoryString.toUpperCase()); 
        
        // Construct a Products object with the updated values
        Products updatedProduct = new Products(prodId, prodName, prodPic, category, description, stock, price);
        
        // Set content type and character encoding
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        try {
            // Call updateProduct method in ProductsDAO to update the product in the database
            ProductsDAO.instance.updateProduct(updatedProduct);
            
            // Construct a JSON response indicating success or failure
            JsonObject jsonResponse = new JsonObject();
            jsonResponse.addProperty("status", "success"); // or "error" if there's an issue
            
            // Convert the JSON response to a string and send it back to the client
            PrintWriter out = response.getWriter();
            out.print(jsonResponse.toString());
            out.flush();

            // Send a success response back to the client
            response.setStatus(HttpServletResponse.SC_OK);
        } catch (Exception e) {
            e.printStackTrace();
            // Send an error response back to the client
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.getWriter().println("Error updating product data: " + e.getMessage());
        }
    }

}
