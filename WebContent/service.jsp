
<%@page import="com.aklc.psmpa.service.Visit"%>
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
								<div class="page-header text-muted" style='background-color: cyan; padding: 10px;'>PROVIDE EXPERT SERVICE</div>
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
								<div class='col-md-5'>
								<%
									List<Visit> allVisits = (List<Visit> ) request.getAttribute("allVisits");
									int i=0;
									for (Visit v: allVisits) {
										i++;
										%>
										
											  <button type="button" class="btn btn-info" data-toggle="collapse" data-target="#data<%=i%>">Visit ID: <%=v.getVisitID() %></button>
											  <div style='padding-top:20px; padding-left:20px;' id="data<%=i%>" class="collapse">
														<form class='well' action='updateHealthInfo' method="post">
															<label>Patient ID</label>
															<input type=text readonly=readonly value='<%=v.getPatientID() %>' class='form-control' name='pid' />
															<input type=hidden readonly=readonly value='<%=v.getVisitID() %>' class='form-control' name='vid' />
															<br/>
															<label>Disease name</label>
															<textarea rows=5 cols=30 class='form-control' name='disease' ><%=v.getDiseaseName() %></textarea>
															<br/>
															<label>Observation</label>
															<textarea rows=5 cols=30 class='form-control' name='observation' ><%=v.getDiseaseName() %></textarea>
															<br/>
															<label>Prescription</label>
															<textarea rows=5 cols=30 class='form-control' name='prescription' ><%=v.getPrescription() %></textarea>
															<br/>
															<input type=submit value='UPDATE' class='btn btn-default' />
														</form>
											  </div>
											  
											  <br/><br/>
										
										<%
									}
								%>
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