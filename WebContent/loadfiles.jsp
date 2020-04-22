<!-- script references -->
<script src="js/jquery.js"></script>
<script src="js/bootstrap.min.js"></script>
<link href="css/bootstrap.min.css" rel="stylesheet">
<!--[if lt IE 9]>
			<script src="//html5shim.googlecode.com/svn/trunk/html5.js"></script>
		<![endif]-->
<link href="css/styles.css" rel="stylesheet">



<%
	String role = (String) session.getAttribute("role");
	String email = (String) session.getAttribute("email");
	String msg = request.getParameter("msg");
%>
