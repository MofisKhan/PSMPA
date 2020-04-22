
<%@page import="com.aklc.psmpa.pojo.Request"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta http-equiv="content-type" content="text/html; charset=UTF-8">
<meta charset="utf-8">
<title>PSMPA - PSMPA</title>
<%@include file="loadfiles.jsp"%>
</head>


<%
	if (role != null) {
%>


<body>
	<div class="wrapper">
		<div class="box">
			<div class="row">
				<%@include file="menu.jsp"%>

				<!-- main -->
				<div class="column col-sm-9" id="main" style='min-height: 600px;'>
					<div>
						<div class="full col-sm-9">

							<!-- content -->

							<%@include file="welcomeuser.jsp"%>

							<div class="col-sm-12">
								<div class="page-header text-muted" style='background-color: cyan; padding: 10px;'>REQUESTS STATUS</div>
							</div>
							<%
								if (msg != null) {
							%>
							<div class='row'>
								<div class='col-md-6'>
									<div class='alert alert-info'>
										<%=msg%>
									</div>
								</div>
							</div>
							<%
								}
							%>

							<div class='row'>
								<div class='col-md-1'></div>
								<div class='col-md-5'>
									<%
										List<Request> requests = (List<Request>) request.getAttribute("requests");
									%>
									<table class='table'>
										<tr>
											<th>Request Type</th>
											<th>ID</th>
											<th>Status</th>
										</tr>
										<%
											if (requests != null)
													for (Request r : requests) {
										%>
										<tr>
											<td><%=r.getType()%></td>
											<td><%=r.getId()%></td>
											<td><%=r.getStatus()%></td>

										</tr>
										<%
											}
										%>
									</table>
								</div>
								<div class='col-md-1'></div>
							</div>


							<hr>

							<%@include file="footer.jsp"%>
							<hr>


						</div>
						<!-- /col-9 -->
					</div>
					<!-- /padding -->
				</div>
				<!-- /main -->
			</div>
		</div>
	</div>
</body>

<%
	} else {
		response.sendRedirect("index.jsp?msg=Please login to continue");
	}
%>
</html>