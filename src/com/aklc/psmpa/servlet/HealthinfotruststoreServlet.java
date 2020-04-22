package com.aklc.psmpa.servlet;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.aklc.psmpa.dao.PatientDAO;

public class HealthinfotruststoreServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		try {
			String pid = (String) req.getSession().getAttribute("pid");
			String email = (String) req.getSession().getAttribute("email");
			PatientDAO dao = new PatientDAO();
			Map<String, List<String>> healthinfotruststore = dao.getPatientHealthInfoTrustStore(pid);
			req.setAttribute("healthinfotruststore", healthinfotruststore);
			req.getRequestDispatcher("healthinfotruststore.jsp").forward(req, resp);
		} catch (Exception e) {
			e.printStackTrace();
			resp.sendRedirect("healthinfotruststore.jsp?msg=Something went wrong. Please try again later");
		}

	}

}
