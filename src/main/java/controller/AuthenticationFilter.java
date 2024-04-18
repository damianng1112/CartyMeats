package controller;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebFilter("/home.jsp") 
public class AuthenticationFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    	
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        
        HttpSession session = httpRequest.getSession(false); // Do not create a new session if it doesn't exist
        if (session != null && session.getAttribute("username") != null) {
            // User is authenticated, allow access to the home page
            chain.doFilter(request, response);
        } else {
            // User is not authenticated, redirect to the login page
            httpResponse.sendRedirect("login.jsp"); // Adjust the login page URL as needed
        }
    }

    @Override
    public void destroy() {
        // Cleanup code, if needed
    }
}
