
<%
	if (role != null && role.equals("doctor")) {
%>
<div class="column col-sm-3" id="sidebar">
	<a class="logo" style='color: black;' href="#">HOSPITAL PORTAL</a>
	<ul class="nav">
		<li class="active"><a href="home.jsp">HOME</a></li>
		<li><a href="registerNewPatient.jsp">REGISTER NEW PATIENT</a></li>
		<li><a href="patientsIdentity">PATIENTS IDENTITY </a></li>
		<li><a href="patientsHealthInfo">PATIENTS HEALTH INFO </a></li>
		<li><a href="registerNewVisit.jsp">REGISTER NEW VISIT</a></li>
	</ul>
	<ul class="nav hidden-xs" id="sidebar-footer">
		<li><a href="">Final year project work 2016</a></li>
	</ul>
</div>
<%
	} else if (role != null && role.equals("patient")) {
%>
<div class="column col-sm-3" id="sidebar">
	<a class="logo" style='color: black;' href="#">PATIENTS PORTAL</a>
	<ul class="nav">
		<li class="active"><a href="home.jsp">HOME</a></li>
		<li><a href="myidentity">MY IDENTITY</a></li>
		<li><a href="myhealthinfo">MY HEALTH_INFO</a></li>
		<li><a href="identitytruststore">IDENTITY TRUST STORE</a></li>
		<li><a href="healthinfotruststore">HEALTH INFO TRUST STORE</a></li>
	</ul>
	<ul class="nav hidden-xs" id="sidebar-footer">
		<li><a href="">Final year project work 2016</a></li>
	</ul>
</div>
<%
	} else if (role!=null && role.equals("consultant")) {
%>
<div class="column col-sm-3" id="sidebar">
	<a class="logo" style='color: black;' href="#">CONSULTANT PORTAL</a>
	<ul class="nav">
		<li class="active"><a href="home.jsp">HOME</a></li>
		<li><a href="patientsIdentity">PATIENTS IDENTIY</a></li>
		<li><a href="patientsHealthInfo">PATIENTS HEALTH_INFO</a></li>
		<li><a href="service">PROVIDE EXPERT SERVICE</a></li>
		<li><a href="requestsView">REQUESTS</a></li>
	</ul>
	<ul class="nav hidden-xs" id="sidebar-footer">
		<li><a href="">Final year project work 2016</a></li>
	</ul>
</div>
		
<%
	}	
	else {
%>
<div class="column col-sm-3" id="sidebar">
	<a class="logo" style='color: black;' href="#">HOSPITAL PORTAL</a>
	<ul class="nav">
		<li class="active"><a href="">ABOUT US</a></li>
		<li><a href="index.jsp">LOGIN</a></li>
		<li><a href="createaccount.jsp">CREATE ACCOUNT</a></li>
		<li><a href="">DEVELOPERS</a></li>
	</ul>
	<ul class="nav hidden-xs" id="sidebar-footer">
		<li><a href="">Final year project work 2016</a></li>
	</ul>
</div>
<%
	}
%>
