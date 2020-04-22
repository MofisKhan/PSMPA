package com.aklc.psmpa.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.aklc.psmpa.dao.UserDAO;

public class CreateAccountServlet extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		try {
			String role = req.getParameter("role");
			String email = req.getParameter("email");
			String pwd = req.getParameter("pwd");
			UserDAO dao = new UserDAO();
			dao.createAccount(email, pwd, role);
			resp.sendRedirect("createaccount.jsp?msg=Registration Successful");

		} catch (Exception e) {
			resp.sendRedirect("createaccount.jsp?msg=Something went wrong while registering an user");
		}

	}

}
