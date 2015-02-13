<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import = "viewInitializer.ViewErrorsInitializer"%>
<% 
	String errors = ViewErrorsInitializer.initialize();
%>
<!DOCTYPE>    
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>System Logs</title>

<!-- Bootstrap core CSS -->
<link href="../dist/css/bootstrap.min.css" type="text/css" rel="stylesheet" />

<!-- Custom styles for this template -->
<link href="../css/SystemLogs.css" type="text/css" rel="stylesheet"/>

</head>
<body>
	<nav class="navbar navbar-default navbar-static-top">
		<div class="container">
			<div class="navbar-header">
				<button type="button" class="navbar-toggle collapsed"
					data-toggle="collapse" data-target="#navbar" aria-expanded="false"
					aria-controls="navbar">
					<span class="sr-only">Toggle navigation</span> <span
						class="icon-bar"></span> <span class="icon-bar"></span> <span
						class="icon-bar"></span>
				</button>
			</div>
			<div id="navbar" class="navbar-collapse collapse">
				<ul class="nav navbar-nav">
					<li class="active"><a href="#">System Logs</a></li>
				</ul>
				<ul class="nav navbar-nav navbar-right">
					<li><a href="/EditAccount">Edit Account</a></li>
					<li><a href="/logout">Log out</a></li>
				</ul>
			</div>
			<!--/.nav-collapse -->
		</div>
	</nav>

	<div id='page' class="container">	
		
	</div>

	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
	<script src="../dist/js/bootstrap.min.js"></script>
		
	<script>
		$(
			function(){
				var errors = <%=errors%>
				
				if(errors == null || errors.length == 0){
					$('#page').append("<div id='emptyResult' class='well'>Nothing Found</div>")
				}
				else{
					for(var error in errors){
						var line = errors[error]
						$('#page').append('<span>' + line + '</span><br/>')
					}
				}
			}
		)
	</script>
</body>
</html>