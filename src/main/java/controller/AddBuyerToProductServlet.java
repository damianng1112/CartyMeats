package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.ProductsDAO;

/**
 * Servlet implementation class AddBuyerToProductServlet
 */
@WebServlet("/AddBuyerToProductServlet")
public class AddBuyerToProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddBuyerToProductServlet() {
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
		//Get id of buyer and product
		String buyerId = request.getParameter("buyerId");
		String productId = request.getParameter("productId");
		
		try {
			// Calling method for adding buyer
			ProductsDAO.instance.addBuyer(buyerId, productId);
			// Send a success response back to the client
            response.setStatus(HttpServletResponse.SC_CREATED);
            response.getWriter().println("Buyer added successfully");
		}catch(Exception e) {
			//Send a error response back to the client
			e.printStackTrace();
			response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		}
		
	}

}
