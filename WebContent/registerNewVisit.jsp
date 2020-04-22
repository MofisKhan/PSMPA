
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
								<div class="page-header text-muted" style='background-color: cyan; padding: 10px;'>REGISTER NEW VISIT</div>
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
								<div class='col-md-6'>
									<form class='well' action='registerVisit' method=post>
										<label>Patient ID</label>
										<input type=text name='pid' class='form-control' placeholder='Patient ID' />
										<br/>
										<label>Patient Email</label>
										<input type=text name='email' class='form-control' placeholder='Email ID' />
										<br/>
										<label>Disease Name</label>
										<input type=text name='dname' class='form-control' placeholder='Disease Name' />
										<br/>
										<label>Observation</label>
										<textarea name='observation' class='form-control' rows=5 cols=50 placeholder='Observations'></textarea>
										<br/>
										<label>Prescription</label>
										<textarea name='prescription' class='form-control' rows=5 cols=50 placeholder='Prescription'></textarea>
										<br/>
										<input type=reset class='btn btn-default' value='CLEAR' />
										<input type=submit class='btn btn-primary' value='REGISTER' />
										

																				
									</form>
								</div>
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