package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.*;

/**
 * Servlet implementation class UpdateUserServlet
 */
@WebServlet("/UpdateUserServlet")
public class UpdateUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateUserServlet() {
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
		String fullname = request.getParameter("fullname");
        String username = request.getParameter("userName");
        String password = request.getParameter("password");
        String email = request.getParameter("email");
        String contact = request.getParameter("contact");
        String prevUsername = request.getParameter("prevUsername");

        Employee employee = new Employee();
        employee.setFullname(fullname);
        employee.setUsername(username);
        employee.setPassword(password);
        employee.setEmail(email);
        employee.setContact(contact);

        EmployeeDAO dao = EmployeeDAO.instance;
        try {
            dao.updateEmployee(employee, prevUsername);
            // Redirect to a success page after updating the user information
            response.sendRedirect("login.jsp");
        } catch (Exception e) {
            e.printStackTrace();
            // Redirect back to the update page with an error message
            response.sendRedirect("home.jsp?error=1");
        }
	}

}
