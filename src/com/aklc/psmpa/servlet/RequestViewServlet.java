package com.aklc.psmpa.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.aklc.psmpa.dao.RequestDAO;
import com.aklc.psmpa.pojo.Request;

public class RequestViewServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			String email = (String) req.getSession().getAttribute("email");
			RequestDAO dao = new RequestDAO();
			List<Request> requests = dao.getAllRequestsForUser(email);
			req.setAttribute("requests", requests);
			req.getRequestDispatcher("requestsView.jsp").forward(req, resp);
		} catch (Exception e) {
			e.printStackTrace();
			resp.sendRedirect("requestsView.jsp?msg=Something went wrong. Please try again");
		}

	}
}

