package com.aklc.psmpa.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.aklc.psmpa.dao.PatientDAO;
import com.aklc.psmpa.pojo.Patient;
import com.aklc.psmpa.service.PatientService;
import com.aklc.psmpa.service.Visit;

public class MyhealthinfoServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			String email = (String) req.getSession().getAttribute("email");
			String pid = (String) req.getSession().getAttribute("pid");

			PatientService ps = new PatientService();
			PatientDAO dao = new PatientDAO();
			List<String> visitIDs = dao.getAllVisitIds(pid);
			List<Visit> visits = new ArrayList<>();
			for (String v : visitIDs) {
				visits.add(ps.readVisitDetails(v, email));
			}
			req.setAttribute("visits", visits);
			req.getRequestDispatcher("myhealthinfo.jsp").forward(req, resp);
		} catch (Exception e) {
			resp.sendRedirect("myhealthinfo.jsp?msg=Something went wrong. Please try again later");
		}

	}

}
