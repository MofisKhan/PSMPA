package com.aklc.psmpa.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.aklc.psmpa.dao.PatientDAO;
import com.aklc.psmpa.service.PatientService;
import com.aklc.psmpa.service.Visit;

public class ServiceServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		try {
			PatientService ps = new PatientService();
			PatientDAO dao = new PatientDAO();
			List<String> visitids = dao.getAllVisitIds();
			List<Visit> allVisits = new ArrayList<>();
			for (String v : visitids) {
				allVisits.add(ps.readVisitDetails(v, (String) req.getSession().getAttribute("email")));
			}

			req.setAttribute("allVisits", allVisits);
			req.getRequestDispatcher("service.jsp").forward(req, resp);
		} catch (Exception e) {
			e.printStackTrace();
			resp.sendRedirect("service.jsp?msg=Something went wrong. Please try again later.");
		}

	}

}
