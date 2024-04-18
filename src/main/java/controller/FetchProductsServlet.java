package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import model.*;

/**
 * Servlet implementation class FetchProductsServlet
 */
@WebServlet("/FetchProductsServlet")
public class FetchProductsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FetchProductsServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {
			//Fetch list of product from database
			List<Products> products = ProductsDAO.instance.fetchProducts();
			
			// Convert clients list to JSON
            Gson gson = new Gson();
            String json = gson.toJson(products);

            // Set content type and character encoding
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");

            // Write JSON data to response
            PrintWriter out = response.getWriter();
            out.print(json);
            out.flush();
		} catch (Exception e) {
            e.printStackTrace();
            // Handle any errors
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.getWriter().println("Error fetching product data from the database: " + e.getMessage());
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
