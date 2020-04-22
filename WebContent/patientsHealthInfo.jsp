
<%@page import="java.util.Map.Entry"%>
<%@page import="com.aklc.psmpa.service.Visit"%>
<%@page import="java.util.Map"%>
<%@page import="com.aklc.psmpa.pojo.Patient"%>
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
								<div class="page-header text-muted" style='background-color: cyan; padding: 10px;'>PATIENTS HEALTH INFORMATION</div>
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
								List<String> pids = (List<String>) request.getAttribute("pids");
									Map<String, Visit> visits = (Map<String, Visit>) request.getAttribute("visits");
									String signatureverify = (String) request.getAttribute("signatureverify");
									if (pids != null && pids.size() > 0) {
							%>
							<div class='row'>
								<div class='col-md-5'>
									<table class='table'>
										<tr>
											<th>PATIENT IDs</th>
											<th>ACTION</th>
										</tr>

										<%
											for (String id : pids) {
										%>
										<tr>
											<td><%=id%></td>
											<td>
												<form action='patientsHealthInfo' method=post>
													<input type=hidden name='pid' value='<%=id%>' /><input type=submit class='btn btn-warning btn-xs'
														value='View HEALTH INFORMATION' />
												</form>
											</td>
										</tr>
										<%
											}
										%>
									</table>
								</div>

								<%
									if (signatureverify != null && signatureverify.equals("failed")) {
								%>
								<div class='col-md-1'></div>
								<div class='col-md-5'>

									<table class='table'>
										<tr>
											<th>Patient ID:</th>
											<td align="left">[SIGNATURE VERIFICATION FAILED]</td>
										</tr>

										<tr>
											<th>Visit ID:</th>
											<td align="left">[SIGNATURE VERIFICATION FAILED]</td>
										</tr>

										<tr>
											<th>Visit Details</th>
											<td align="left">[SIGNATURE VERIFICATION FAILED]</td>
										</tr>



									</table>
								</div>
							</div>

							<%
								}
							%>
							<%
								if (visits != null) {
							%>
							<div class='col-md-1'></div>
							<div class='col-md-5'>

								<%
									for (Entry<String, Visit> entry : visits.entrySet()) {
													Visit visit = entry.getValue();
								%>
								<h3>
									Visit ID:
									<%=entry.getKey()%></h3>
								<table class='table'>
									<tr>
										<th>Patient ID:</th>
										<td><%=visit.getPatientID()%></td>
									</tr>

									<tr>
										<th>Disease name:</th>
										<td><%=visit.getDiseaseName()%></td>
									</tr>


									<tr>
										<th>Observation:</th>
										<td><%=visit.getObservation()%></td>
									</tr>


									<tr>
										<th>Prescription:</th>
										<td><%=visit.getPrescription()%></td>
									</tr>


									<tr>
										<th>Entry Time:</th>
										<td><%=visit.getEntryTime()%></td>
									</tr>

									<tr>
										<th>Update Time:</th>
										<td><%=visit.getUpdateTime()%></td>
									</tr>

								</table>
								<%
									}
								%>


							</div>
						</div>

						<%
							}
						%>
						<%
							} else {
						%>
						<div class='row'>
							<div class='col-md-6'>
								<h3>No patients has been registered yet.</h3>
							</div>
						</div>
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