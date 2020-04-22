package com.aklc.psmpa.servlet;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.aklc.psmpa.dao.PatientDAO;
import com.aklc.psmpa.dao.TrustStoreDAO;

public class AddhealthInfoTrustStoreServlet extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		try {
			String email = req.getParameter("email");
			String visit_id = req.getParameter("vid");
			TrustStoreDAO dao = new TrustStoreDAO();
			dao.addHealthInfoTrustStore(visit_id, email);

			String pid = (String) req.getSession().getAttribute("pid");
			String email2 = (String) req.getSession().getAttribute("email");
			PatientDAO dao2 = new PatientDAO();
			Map<String, List<String>> healthinfotruststore = dao2.getPatientHealthInfoTrustStore(pid);
			req.setAttribute("healthinfotruststore", healthinfotruststore);
			req.getRequestDispatcher(
					"healthinfotruststore.jsp?msg=Added " + email + " to health information trust store")
					.forward(req, resp);

		} catch (Exception e) {
			e.printStackTrace();
			resp.sendRedirect("healthinfotruststore.jsp?msg=Something went wrong. Please try again later");
		}

	}

}
