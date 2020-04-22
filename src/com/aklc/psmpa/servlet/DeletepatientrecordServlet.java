package com.aklc.psmpa.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.aklc.psmpa.dao.PatientDAO;

public class DeletepatientrecordServlet extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			PatientDAO dao = new PatientDAO();
			String pid = req.getParameter("pid");
			dao.deletePatientRecord(pid);
			List<String> ids = dao.getAllPatientIds();
			req.setAttribute("pids", ids);
			req.getRequestDispatcher("patientsIdentity.jsp?msg=Deleted the patient entry " + pid).forward(req, resp);
		} catch (Exception e) {
			e.printStackTrace();
			resp.sendRedirect(
					"patientsIdentity.jsp?msg=Something went wrong while pulling the patients IDs. Please try again");
		}

	}

}
