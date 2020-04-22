
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
								<div class="page-header text-muted" style='background-color: cyan; padding: 10px;'>MY IDENTITY</div>
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
								Patient patient = (Patient) request.getAttribute("patient");
									if (patient != null) {
							%>
							<div class='row'>
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