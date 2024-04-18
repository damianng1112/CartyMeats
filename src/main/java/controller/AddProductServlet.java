package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.*;

/**
 * Servlet implementation class AddProductServlet
 */
@WebServlet("/AddProductServlet")
public class AddProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddProductServlet() {
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
		// Retrieve client data from request parameters
        String prodName = request.getParameter("prodName");
        String prodPic = request.getParameter("prodPic");
        Category category = Category.valueOf(request.getParameter("category").toUpperCase());
        String description = request.getParameter("description");        
        int stock = Integer.parseInt(request.getParameter("stock"));
        
        // Create a Products object
        Products product = new Products(prodName, prodPic, category, description, stock);
        
        try {
            // Call createClient method in ClientsDAO to add the client to the database
            ProductsDAO.instance.createProduct(product);

            // Send a success response back to the client
            response.setStatus(HttpServletResponse.SC_CREATED);
            response.getWriter().println("Product created successfully");
        } catch (Exception e) {
            e.printStackTrace();
            // Send an error response back to the client
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.sendRedirect("error.jsp");
        }
	}

}
