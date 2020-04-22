package com.aklc.psmpa.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.aklc.psmpa.dao.PatientDAO;
import com.aklc.psmpa.pojo.Patient;
import com.aklc.psmpa.service.PatientService;

public class PatientsIdentityServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		try {
			PatientDAO dao = new PatientDAO();
			List<String> ids = dao.getAllPatientIds();
			req.setAttribute("pids", ids);
			req.getRequestDispatcher("patientsIdentity.jsp").forward(req, resp);
		} catch (Exception e) {
			e.printStackTrace();
			resp.sendRedirect(
					"patientsIdentity.jsp?msg=Something went wrong while pulling the patients IDs. Please try again");
		}

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			PatientDAO dao = new PatientDAO();
			List<String> ids = dao.getAllPatientIds();
			req.setAttribute("pids", ids);
			PatientService ps = new PatientService();
			Patient patient = ps.readPatientIdentity(req.getParameter("pid"), (String) req.getSession().getAttribute("email"));
			req.setAttribute("patient", patient);
			req.setAttribute("pidtemp", req.getParameter("pid"));
			if (patient == null)
				req.setAttribute("signatureverify", "failed");
			req.getRequestDispatcher("patientsIdentity.jsp").forward(req, resp);
			
		} catch (Exception e) {
			e.printStackTrace();
			resp.sendRedirect(
					"patientsIdentity.jsp?msg=Something went wrong while pulling the patients data. Please try again later");
		}
	}

}
