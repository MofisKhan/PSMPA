
<%@page import="java.util.Map.Entry"%>
<%@page import="java.util.Map"%>
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
								<div class="page-header text-muted" style='background-color: cyan; padding: 10px;'>HEALTH INFORMATION TRUST STORE</div>
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
								Map<String, List<String>> healthinfotruststore = (Map<String, List<String>>) request
											.getAttribute("healthinfotruststore");
									if (healthinfotruststore != null) {
							%>

							<%
								for (Entry<String, List<String>> row : healthinfotruststore.entrySet()) {
							%>
							<div class='row'>
								<div class='col-md-1'></div>
								<div class='col-md-5'>

									<h3>
										Visit ID:
										<%=row.getKey()%>
									</h3>
								</div>
							</div>
							<div class='row'>
								<div class='col-md-1'></div>
								<div class='col-md-5'>
									<h4>Current entries in the Health information Trust store</h4>
									<table class='table'>
										<tr>
											<th>Trusted Email ID</th>
											<th align="left">Action
											</td>
										</tr>

										<%
											for (String em : row.getValue()) {
										%>
										<tr>
											<td><%=em%></td>
											<td>
												<form action='deletePatienthealthInfoTrustStore' method='post'>
													<input type=hidden name='email' value='<%=em%>' /> <input type=hidden name='vid' value='<%=row.getKey()%>' /> <input
														type=submit value='Remove from Trust store'
														<%if (em.equals("*@hospital.com") || (em.equals(session.getAttribute("email")))) {%> disabled=disabled <%}%>
														class='btn btn-danger btn-xs'/ >
												</form>
											</td>
										</tr>
										<%
											}
										%>
									</table>

								</div>

								<div class='col-md-5'>
									<h4>Add a new entry to the Identity trust store</h4>
									<form class='well' action='addhealthInfoTrustStore' method=post>
										<input type='hidden' name='vid' value='<%=row.getKey()%>' /> <label>Email:</label> <input type=text
											class='form-control' name='email' placeholder='Email' /> <br /> <input type=submit value='ADD' class='btn btn-primary' />
									</form>
								</div>


							</div>

							<hr/>
							<%
								}
							%>

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