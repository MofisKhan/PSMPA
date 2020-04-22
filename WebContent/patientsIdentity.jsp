
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
								<div class="page-header text-muted" style='background-color: cyan; padding: 10px;'>PATIENTS IDENTITY</div>
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
									Patient patient = (Patient) request.getAttribute("patient");
									String signatureverify = (String) request.getAttribute("signatureverify");
									if (pids != null && pids.size() > 0) {
							%>
							<div class='row'>
								<div class='col-md-5'>
									<table class='table'>
										<tr>
											<th>PATIENT IDs</th>
											<th>DELETE</th>
											<th>VIEW</th>
										</tr>

										<%
											for (String id : pids) {
										%>
										<tr>
											<td><%=id%></td>
											<td>

												<form action='deletepatientrecord' method=post>
													<input type=hidden name='pid' value='<%=id%>' /><input type=submit class='btn btn-danger btn-xs'
														<%if (((String) session.getAttribute("role")).equals("consultant")) {%> disabled=disabled <%}%>
														value='Delete Record' />
												</form>

											</td>
											<td>
												<form action='patientsIdentity' method=post>
													<input type=hidden name='pid' value='<%=id%>' /><input type=submit class='btn btn-warning btn-xs' value='View Identity' />
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
											<td align="left"><%=(String)request.getAttribute("pidtemp") %></td>
										</tr>

										<tr>
											<th>Patient First Name:</th>
											<td align="left">[SIGNATURE VERIFICATION FAILED]</td>
										</tr>

										<tr>
											<th>Patient Last Name:</th>
											<td align="left">[SIGNATURE VERIFICATION FAILED]</td>
										</tr>

										<tr>
											<th>Patient Gender:</th>
											<td align="left">[SIGNATURE VERIFICATION FAILED]</td>
										</tr>

										<tr>
											<th>Patient Date of Birth:</th>
											<td align="left">[SIGNATURE VERIFICATION FAILED]</td>
										</tr>

										<tr>
											<th>Patient Email:</th>
											<td align="left">[SIGNATURE VERIFICATION FAILED]</td>
										</tr>


										<tr>
											<th>Patient Address:</th>
											<td align="left">[SIGNATURE VERIFICATION FAILED]</td>
										</tr>


										<tr>
											<th>Patient Phone:</th>
											<td align="left">[SIGNATURE VERIFICATION FAILED]</td>
										</tr>

										<tr>
											<th>Request Access:</th>
											<td align="left">
											<form action='requestAccess' method='post'>
												<input type=hidden name='id' value='<%=(String)request.getAttribute("pidtemp") %>' />
												<input type=hidden name='type' value='patient_identity' />
												<input type=hidden name='redirect' value='patientsIdentity' />
												<input type=submit value='REQUEST ACCESS' class='btn btn-primary btn-xs' />
											</form>
											</td>
										</tr>


									</table>
								</div>
							</div>

							<%
								}
							%>
							<%
								if (patient != null) {
							%>
							<div class='col-md-1'></div>
							<div class='col-md-5'>

								<table class='table'>
									<tr>
										<th>Patient ID:</th>
										<td align="left"><%=patient.getPatientID()%></td>
									</tr>

									<tr>
										<th>Patient First Name:</th>
										<td align="left"><%=patient.getFirstName()%></td>
									</tr>

									<tr>
										<th>Patient Last Name:</th>
										<td align="left"><%=patient.getLastName()%></td>
									</tr>

									<tr>
										<th>Patient Gender:</th>
										<td align="left"><%=patient.getGender()%></td>
									</tr>

									<tr>
										<th>Patient Date of Birth:</th>
										<td align="left"><%=patient.getDob()%></td>
									</tr>

									<tr>
										<th>Patient Email:</th>
										<td align="left"><%=patient.getEmail()%></td>
									</tr>


									<tr>
										<th>Patient Address:</th>
										<td align="left"><%=patient.getAddress()%></td>
									</tr>


									<tr>
										<th>Patient Phone:</th>
										<td align="left"><%=patient.getPhone()%></td>
									</tr>


								</table>
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