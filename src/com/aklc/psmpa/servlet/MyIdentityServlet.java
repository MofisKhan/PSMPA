package com.aklc.psmpa.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.aklc.psmpa.pojo.Patient;
import com.aklc.psmpa.service.PatientService;

public class MyIdentityServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		try {
			String email = (String) req.getSession().getAttribute("email");
			String pid = (String) req.getSession().getAttribute("pid");
			PatientService ps = new PatientService();
			System.out.println("pid .. "+pid);
			System.out.println("mail .. "+email);
			
			Patient patient = ps.readPatientIdentity(pid, email);
			req.setAttribute("patient", patient);
			req.getRequestDispatcher("myindentity.jsp").forward(req, resp);
		} catch (Exception e) {
			resp.sendRedirect("myidentity.jsp?msg=Something went wrong. Please try again later");
		}

	}

}
