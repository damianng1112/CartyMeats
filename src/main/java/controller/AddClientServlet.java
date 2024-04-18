package controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Clients;
import model.ClientsDAO;

/**
 * Servlet implementation class AddClientServlet
 */
@WebServlet("/AddClientServlet")
public class AddClientServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddClientServlet() {
        super();
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Retrieve client data from request parameters
        String clientName = request.getParameter("clientName");
        String address = request.getParameter("clientAddress");
        String facing = request.getParameter("clientFacing");

        // Create a Clients object
        Clients client = new Clients(clientName, address, facing);

        try {
            // Call createClient method in ClientsDAO to add the client to the database
            ClientsDAO.instance.createClient(client);

            // Send a success response back to the client
            response.setStatus(HttpServletResponse.SC_CREATED);
            response.getWriter().println("Client created successfully");
        } catch (SQLException e) {
            e.printStackTrace();
            // Send an error response back to the client
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.sendRedirect("error.jsp");
        }
    }
}
