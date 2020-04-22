package com.aklc.psmpa.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.aklc.psmpa.service.PatientService;
import com.aklc.psmpa.service.Visit;

public class RegisterVisitServlet extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		try {
			Visit visit = new Visit();
			visit.setPatientID(req.getParameter("pid"));
			visit.setDiseaseName(req.getParameter("dname"));
			visit.setObservation(req.getParameter("observation"));
			String presc = req.getParameter("prescription");
			if (presc==null || presc.trim().length()==0)
				presc = " - ";
			
			visit.setPrescription(presc);
			PatientService ps = new PatientService();
			List<String> initialTrusts = new ArrayList<>();
			initialTrusts.add("*@hospital.com");
			initialTrusts.add("*@consultant.com");
			ps.registerNewVisitDetails(visit, initialTrusts, req.getParameter("email"));
			resp.sendRedirect("registerNewVisit.jsp?msg=Successfully Registered a new visit");
		} catch (Exception e) {
			e.printStackTrace();
			resp.sendRedirect("registerNewVisit.jsp?msg=Something went wrong. Please check all the fields and try again");
		}

	}

}
