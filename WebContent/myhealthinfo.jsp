
<%@page import="com.aklc.psmpa.service.Visit"%>
<%@page import="java.util.List"%>
<%@page import="com.aklc.psmpa.pojo.Patient"%>
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
								<div class="page-header text-muted" style='background-color: cyan; padding: 10px;'>MY HEALTH INFORMATION</div>
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


							<%
								List<Visit> visits = (List<Visit>) request.getAttribute("visits");
									if (visits != null) {

										for (Visit v : visits) {
							%>
							<div class='row'>
								<div class='col-md-1'></div>
								<div class='col-md-5'>
									<h3>
										Visit ID:
										<%=v.getVisitID()%></h3>
									<table class='table'>
										<tr>
											<th>Disease Name:</th>
											<td align="left"><%=v.getDiseaseName()%></td>
										</tr>

										<tr>
											<th>Observation:</th>
											<td align="left"><%=v.getObservation()%></td>
										</tr>

										<tr>
											<th>Prescription:</th>
											<td align="left"><%=v.getPrescription()%></td>
										</tr>

										<tr>
											<th>Entry time:</th>
											<td align="left"><%=v.getEntryTime()%></td>
										</tr>

										<tr>
											<th>Update time:</th>
											<td align="left"><%=v.getUpdateTime()%></td>
										</tr>


									</table>
								</div>
							</div>

							<%
								}
									} else {
							%>
							<h3>SORRY, NOTHING FOUND</h3>
							<%
								}
							%>

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