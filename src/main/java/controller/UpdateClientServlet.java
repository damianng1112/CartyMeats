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

import model.Clients;
import model.ClientsDAO;

/**
 * Servlet implementation class UpdateClientServlet
 */
@WebServlet("/UpdateClientServlet")
public class UpdateClientServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public UpdateClientServlet() {
        super();
    }

    protected void doPut(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Extract client details from request body
        BufferedReader reader = request.getReader();
        StringBuilder requestBody = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            requestBody.append(line);
        }
        reader.close();

        // Convert request body JSON to a Java object
        Gson gson = new Gson();
        Clients updatedClient = gson.fromJson(requestBody.toString(), Clients.class);
        
        // Set content type and character encoding
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        try {
            // Call updateClient method in ClientsDAO to update the client in the database
            ClientsDAO.instance.updateClient(updatedClient);
            
            // Construct a JSON response indicating success or failure
            JsonObject jsonResponse = new JsonObject();
            jsonResponse.addProperty("status", "success"); // or "error" if there's an issue
            
            // Convert the JSON response to a string and send it back to the client
            PrintWriter out = response.getWriter();
            out.print(jsonResponse.toString());
            out.flush();

            // Send a success response back to the client
            response.setStatus(HttpServletResponse.SC_OK);
        } catch (SQLException e) {
            e.printStackTrace();
            // Send an error response back to the client
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.getWriter().println("Error updating client data: " + e.getMessage());
        }
    }
}
