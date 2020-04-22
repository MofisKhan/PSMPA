package com.aklc.psmpa.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.aklc.psmpa.dao.PatientDAO;
import com.aklc.psmpa.dao.RequestDAO;

public class IdentitytruststoreServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		try {
			String pid = (String) req.getSession().getAttribute("pid");
			String email = (String) req.getSession().getAttribute("email");
			PatientDAO dao = new PatientDAO();
			List<String> patientIdentityTruststore = dao.getPatientIdentityTrustStore(pid);
			req.setAttribute("patientIdentityTruststore", patientIdentityTruststore);
			RequestDAO dao2 = new RequestDAO();
			req.setAttribute("identityrequests", dao2.getPatientIdentityRequests(pid));
			req.getRequestDispatcher("identitytruststore.jsp").forward(req, resp);
		} catch (Exception e) {
			e.printStackTrace();
			resp.sendRedirect("identitytruststore.jsp?msg=Something went wrong. Please try again later");
		}

	}

}
