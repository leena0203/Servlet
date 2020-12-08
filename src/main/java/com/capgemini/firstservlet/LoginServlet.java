package com.capgemini.firstservlet;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * Servlet implementation class LoginServlet
 */
@WebServlet(description = "Login Servlet Testing", urlPatterns = { "/LoginServlet" }, initParams = {
		@WebInitParam(name = "user", value = "Leena"), @WebInitParam(name = "password", value = "Open@123") })
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String user = request.getParameter("user");
		String pwd = request.getParameter("pwd");
		String userID = getServletConfig().getInitParameter("user");
		String password = getServletConfig().getInitParameter("password");
		if (validateUserName(user) && validatePassword(pwd) && userID.equals(user) && password.equals(pwd)) {
			request.setAttribute("user", user);
			request.getRequestDispatcher("LoginSuccess.jsp").forward(request, response);
		} else {
			RequestDispatcher rd = getServletContext().getRequestDispatcher("/login.html");
			PrintWriter out = response.getWriter();
			out.println("<font color=red>Either user name or password is wrong.</font>");
			rd.include(request, response);
		}
	}
	private boolean validatePassword(String pwd) {
		String regex = "^(?=.*[A-Z])(?=.*[0-9])(?=.*[@$!%*?&])[A-Za-z0-9@$!%*?&]{8,}$";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(pwd);
		if(matcher.find()) {
			return true;
		}
		return false;
	}
	private boolean validateUserName(String name) {
		String regex = "^([A-Z][A-Za-z]{2,})$";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(name);
		if (matcher.find()) {
			return true;
		}
		return false;
	}


}

