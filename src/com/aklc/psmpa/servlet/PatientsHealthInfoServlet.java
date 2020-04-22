package com.aklc.psmpa.servlet;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.aklc.psmpa.dao.PatientDAO;
import com.aklc.psmpa.pojo.Patient;
import com.aklc.psmpa.service.PatientService;
import com.aklc.psmpa.service.Visit;

public class PatientsHealthInfoServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		try {
			PatientDAO dao = new PatientDAO();
			List<String> ids = dao.getAllPatientIds();
			req.setAttribute("pids", ids);
			req.getRequestDispatcher("patientsHealthInfo.jsp").forward(req, resp);
		} catch (Exception e) {
			e.printStackTrace();
			resp.sendRedirect(
					"patientsHealthInfo.jsp?msg=Something went wrong while pulling the patients IDs. Please try again");
		}

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			PatientDAO dao = new PatientDAO();
			List<String> ids = dao.getAllPatientIds();
			req.setAttribute("pids", ids);
			PatientService ps = new PatientService();
			String pid = req.getParameter("pid");

			List<String> vids = dao.getAllVisitIds(pid);
			req.setAttribute("vids", vids);
			Map<String, Visit> visits = new HashMap<>();
			String requester = (String) req.getSession().getAttribute("email");
			for (String vid : vids) {
				Visit visit = ps.readVisitDetails(vid, requester);
				if (visit == null) {
					req.setAttribute("signatureverify", "failed");
					break;
				}
				visits.put(vid, visit);
			}
			req.setAttribute("visits", visits);
			req.getRequestDispatcher("patientsHealthInfo.jsp").forward(req, resp);

		} catch (Exception e) {
			e.printStackTrace();
			resp.sendRedirect(
					"patientsHealthInfo.jsp?msg=Something went wrong while pulling the patients data. Please try again later");
		}
	}

}
