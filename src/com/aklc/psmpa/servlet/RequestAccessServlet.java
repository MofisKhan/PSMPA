package com.aklc.psmpa.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.aklc.psmpa.dao.RequestDAO;

public class RequestAccessServlet extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		try {
			String id = req.getParameter("id");
			String email = (String) req.getSession().getAttribute("email");
			String type = req.getParameter("type");
			RequestDAO dao = new RequestDAO();
			dao.createRequest(id, email, type);
			resp.sendRedirect(req.getParameter("redirect"));
		} catch (Exception e) {
			e.printStackTrace();
			resp.sendRedirect("patientsIdentity.jsp?msg=Something went wrong. Please try again");
		}
	}

}
