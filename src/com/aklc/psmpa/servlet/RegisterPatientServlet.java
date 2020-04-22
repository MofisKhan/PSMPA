package com.aklc.psmpa.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.aklc.psmpa.pojo.Patient;
import com.aklc.psmpa.service.PatientService;

public class RegisterPatientServlet extends HttpServlet  {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		
		try {
			Patient patient = new Patient();
			patient.setAddress(req.getParameter("address"));
			patient.setDob(req.getParameter("dob"));
			patient.setEmail(req.getParameter("email"));
			patient.setFirstName(req.getParameter("fn"));
			patient.setLastName(req.getParameter("ln"));
			patient.setGender(req.getParameter("gender"));
			patient.setPhone(req.getParameter("phone"));
			
			PatientService ps = new PatientService();
			ps.registerNewPatient(patient, "*@hospital.com");

			resp.sendRedirect("registerNewPatient.jsp?msg=Successfully registered a new Patient");			
		}
		catch (Exception e) {
			resp.sendRedirect("registerNewPatient.jsp?msg=Something went wrong. Please verify all the fields and try again");
		}
	
	}

}
