package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.*;

/**
 * Servlet implementation class EmployeeLoginServlet
 */
@WebServlet("/EmployeeLoginServlet")
public class EmployeeLoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EmployeeLoginServlet() {
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
	    // Retrieve form data
		HttpSession s = request.getSession();
	    String username = request.getParameter("username");
	    String password = request.getParameter("password");

	    // Call the login method in the EmployeesDAO to authenticate the user
	    EmployeeDAO dao = EmployeeDAO.instance;
	    try {
	        Employee employee = dao.loginEmployee(username, password);
	        if (employee != null) {
	            // Authentication successful, redirect to a success page or perform further actions
				s.setAttribute("employeeInfo", employee);
				request.getRequestDispatcher("home.jsp").forward(request, response);	        
				} 
	        else {
	            // Authentication failed, redirect back to the login page with an error message
	            response.sendRedirect("login.jsp?error=1");
	        }
	    } catch (Exception e) {
	        // Handle any exceptions
	        e.printStackTrace();
	        // Redirect back to the login page with an error message
	        response.sendRedirect("login.jsp?error=1");
	    }
	}


}
