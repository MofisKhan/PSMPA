
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta http-equiv="content-type" content="text/html; charset=UTF-8">
<meta charset="utf-8">
<title>PSMPA - PSMPA</title>
<%@include file="loadfiles.jsp" %>
</head>


<%
	if (role!=null) {
%>


<body>
	<div class="wrapper">
		<div class="box">
			<div class="row">
				<%@include file="menu.jsp" %>

				<!-- main -->
				<div class="column col-sm-9" id="main" style='min-height:600px;'>
					<div >
						<div class="full col-sm-9">

							<!-- content -->  

							<%@include file="welcomeuser.jsp" %>

							<div class="col-sm-12">
								<div class="page-header text-muted" style='background-color: cyan; padding:10px;'>REGISTER A NEW PATIENT</div> 
							</div>
						<%
							if (msg!=null) {
								%>
									<div class='row'>
										<div class='col-md-6'>
											<div class='alert alert-info'>
												<%=msg %>
											</div>
										</div>
									</div>
								<%
							}
						%>

							<div class='row'>
							
								<div class='col-md-6'>
									<form action="registerPatient" method=post class='well'>
										<label>Patient's First name</label>
										<input type="text" name='fn' class='form-control' placeholder='First name' />
										<br/>
										<label>Patient's Last name</label>
										<input type="text" name='ln' class='form-control' placeholder='Last name' />
										<br/>
										<label>Patient's Gender</label><br/>
										<input type=radio name='gender' value='Male'> Male
										<input type=radio name='gender' value='Female' > Female
										<br/><br/>
										<label>Patient's Date of Birth</label>
										<input type="date" name='dob' class='form-control' placeholder='Date of birth' />
										<br/>
										<label>Patient's Email</label>
										<input type="text" name='email' class='form-control' placeholder='Email' />
										<br/>
										<label>Patient's Address</label>
										<input type="text" name='address' class='form-control' placeholder='Address' />
										<br/>
										<label>Patient's Phone number</label>
										<input type="text" name='phone' class='form-control' placeholder='Phone number' />
										<br/>
										<input type=submit value='CLEAR' class='btn btn-default' />
										<input type=submit value='REGISTER' class='btn btn-primary' />
										
									</form>								
								</div>
							</div>	
						

							<hr>

							<%@include file="footer.jsp" %>
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

<% } else {
	response.sendRedirect("index.jsp?msg=Please login to continue")	;
}
%>
</html>