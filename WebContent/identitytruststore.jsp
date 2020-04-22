
<%@page import="com.aklc.psmpa.pojo.Request"%>
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
								<div class="page-header text-muted" style='background-color: cyan; padding: 10px;'>IDENTITY TRUST STORE</div>
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
								List<String> patientIdentityTruststore = (List<String> ) request.getAttribute("patientIdentityTruststore");
								if (patientIdentityTruststore != null) {
							%>
							<div class='row'>
								<div class='col-md-1'></div>
								<div class='col-md-5'>
									<h4>Current entries in the Identity trust store</h4>		

									<table class='table'>
										<tr>
											<th>Trusted Email ID</th>
											<th align="left">Action</td>
										</tr>

										<%
											for (String em : patientIdentityTruststore) {
												%>
													<tr>
														<td> <%=em %></td>
														<td>
															<form action='deletePatientIdentityTruststore' method='post'>
																<input type=hidden name='email' value='<%=em %>' />
																<input type=hidden name='pid' value='<%=session.getAttribute("pid") %>' />
																<input type=submit value='Remove from Trust store' 
																	<% if (em.equals("*@hospital.com") || (em.equals(session.getAttribute("email")))) {%> disabled=disabled  <% } %>																
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
									<form class='well' action='addPatientIdentityTrustStore' method=post>
										<input type='hidden' name='pid' value='<%=session.getAttribute("pid") %>' />
										<label>Email:</label>
										<input type=text class='form-control' name='email' placeholder='Email' />
										<br/>
										<input type=submit value='ADD' class='btn btn-primary' />
									</form>	
									<br/>
									<br/>
									<%
										List<Request> requests = (List<Request>) request.getAttribute("identityrequests");
									%>
									<h4>Pending Requests to access this trust store</h4>
									<table class='table'>
										<tr>
											<th> Email </th>
											<th> Approve</th>
											<th> Reject</th>
										</tr>
										<%
											for (Request r: requests) {
												%>
													<tr>
														<td> <%=r.getEmail() %></td>
														<td> 
														<form action='updateRequest' method="post">
															<input type=hidden name='type' value='<%=r.getType() %>' />
															<input type=hidden name='id' value='<%=r.getId() %>' />
															<input type=hidden name='email' value='<%=r.getEmail() %>' />
															<input type=hidden name='status' value='APPROVED' />																														
															<input type=hidden name='redirect' value='identitytruststore' />																														
															<input type=submit value='APPROVE' class='btn btn-primary btn-xs'/>
														</form> 

															
														</td>
														<td>
															<form action='rejectRequest' method="post">
															<input type=hidden name='type' value='<%=r.getType() %>' />
															<input type=hidden name='id' value='<%=r.getId() %>' />
															<input type=hidden name='email' value='<%=r.getEmail() %>' />
															<input type=hidden name='status' value='REJECTED' />		
															<input type=hidden name='redirect' value='identitytruststore' />																														
															<input type=submit value='REJECT' class='btn btn-danger btn-xs'/>
															
														</form> 
														
														</td>
													</tr>
												<%
											}
										%>
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