package com.aklc.psmpa.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.aklc.psmpa.dao.PatientDAO;
import com.aklc.psmpa.service.PatientService;
import com.aklc.psmpa.service.Visit;

public class UpdateHealthInfoServlet extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		try {
			Visit visit = new Visit();
			visit.setPatientID(req.getParameter("pid"));
			visit.setVisitID(req.getParameter("vid"));
			visit.setDiseaseName(req.getParameter("disease"));
			visit.setObservation(req.getParameter("observation"));
			String presc = req.getParameter("prescription");
			if (presc == null || presc.trim().length() == 0)
				presc = " - ";

			visit.setPrescription(presc);

			PatientService ps = new PatientService();
			ps.updateVisitDetails(visit);

			PatientDAO dao = new PatientDAO();
			List<String> visitids = dao.getAllVisitIds();
			List<Visit> allVisits = new ArrayList<>();
			for (String v : visitids) {
				allVisits.add(ps.readVisitDetails(v, (String) req.getSession().getAttribute("email")));
			}

			req.setAttribute("allVisits", allVisits);
			req.getRequestDispatcher("service.jsp?msg=Updated the visit id: " + visit.getVisitID()).forward(req, resp);

		} catch (Exception e) {
			e.printStackTrace();
			resp.sendRedirect("service.jsp?msg=Something went wrong. Please try again later");
		}
	}

}
