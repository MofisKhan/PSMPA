package com.aklc.psmpa.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.aklc.psmpa.dao.PatientDAO;
import com.aklc.psmpa.dao.TrustStoreDAO;

public class AddPatientIdentityTrustStoreServlet extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		try {
			String email = req.getParameter("email");
			String pid = req.getParameter("pid");
			TrustStoreDAO dao = new TrustStoreDAO();
			dao.addIdentityTrustStore(pid, email);

			String pid2 = (String) req.getSession().getAttribute("pid");
			PatientDAO dao2 = new PatientDAO();
			List<String> patientIdentityTruststore = dao2.getPatientIdentityTrustStore(pid2);
			req.setAttribute("patientIdentityTruststore", patientIdentityTruststore);
			req.getRequestDispatcher("identitytruststore.jsp?msg=Added " + email + " to the identity trust store")
					.forward(req, resp);

		} catch (Exception e) {
			e.printStackTrace();
			resp.sendRedirect("identitytruststore.jsp?msg=Something went wrong. Please try again later");
		}

	}

}
