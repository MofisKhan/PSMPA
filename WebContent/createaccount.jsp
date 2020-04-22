<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta http-equiv="content-type" content="text/html; charset=UTF-8">
<meta charset="utf-8">
<title>PSMPA - PSMPA</title>
<%@include file="loadfiles.jsp"%>
</head>
<body>
	<div class="wrapper">
		<div class="box">
			<div class="row">
				<%@include file="menu.jsp"%>

				<!-- main -->
				<div class="column col-sm-9" id="main" style='min-height: 600px;'>
					<div class="padding">
						<div class="full col-sm-9">

							<!-- content -->

							<div class="col-sm-12" id="featured">
								<div class="page-header text-muted">CREATE AN ACCOUNT</div>
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

							<!--/top story-->
							<div class="row">
								<div class='col-md-6'>

									<form action='createaccount' method=post>

										<label>User Type: </label> <select class='form-control' name='role' id='role'>
											<option value='doctor'>Doctor</option>
											<option value='consultant'>Consultant</option>
											<option value='patient'>Patient</option>
											<option value='others'>Others</option>
										</select> <br />
										<div id='plogin' style='display: none;'>
											<label>Patient ID: </label> <input type=text name='pid' placeholder='Enter patient ID' class='form-control' /> <br />
										</div>
										<label>Email ID: </label> <input type=text name='email' placeholder='Enter your email' class='form-control' /> <br /> <label>Password:
										</label> <input type=password name='pwd' placeholder='Enter your password' class='form-control' /> <br /> <input type=submit
											value='CREATE ACCOUNT' class='btn btn-primary' />
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
</html>

<script>
	$(document).ready(function() {
		$('#role').change(function() {
			role = $('#role').val();
			if (role != 'patient') {
				$('#plogin').hide();
			} else {
				$('#plogin').show();
			}
		});
	});
</script>