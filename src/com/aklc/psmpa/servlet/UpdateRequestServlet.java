package com.aklc.psmpa.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.aklc.psmpa.dao.RequestDAO;
import com.aklc.psmpa.dao.TrustStoreDAO;
import com.aklc.psmpa.pojo.Request;

public class UpdateRequestServlet extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		try {
			Request r = new Request();
			r.setEmail(req.getParameter("email"));
			r.setId(req.getParameter("id"));
			r.setType(req.getParameter("type"));
			r.setStatus(req.getParameter("status"));

			if (r.getStatus().equals("APPROVED")) {
				TrustStoreDAO dao2 = new TrustStoreDAO();
				if (r.getType().equals("patient_identity"))
					dao2.addIdentityTrustStore((String) req.getSession().getAttribute("pid"), r.getEmail());
				else if (r.getType().equals("health_info"))
					dao2.addHealthInfoTrustStore(req.getParameter("visit_id"), r.getEmail());
			}

			RequestDAO dao = new RequestDAO();
			dao.updateRequest(r);

			resp.sendRedirect(req.getParameter("redirect"));
		} catch (Exception e) {
			resp.sendRedirect("error.jsp");
		}

	}

}
