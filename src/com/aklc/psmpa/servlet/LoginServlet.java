package com.aklc.psmpa.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.aklc.psmpa.dao.UserDAO;

public class LoginServlet extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		try {
			String role = req.getParameter("role");
			String email = req.getParameter("email");
			String pwd = req.getParameter("pwd");
			UserDAO dao = new UserDAO();
			if (dao.isValidUser(email, pwd, role)) {
				// if (role.equals("patient")) {
				// req.getSession().setAttribute("pid",
				// req.getParameter("pid"));
				// }
				req.getSession().setAttribute("role", role);
				req.getSession().setAttribute("email", email);

				resp.sendRedirect("home.jsp");

			} else {
				resp.sendRedirect("index.jsp?msg=Invalid Credentials");
			}
		} catch (Exception e) {
			resp.sendRedirect("index.jsp?msg=Something went wrong while validating the credentials");
		}
	}

}
