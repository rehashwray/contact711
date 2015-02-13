<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import = "viewInitializer.UserInitializer"%>

<% 
	String userProfile = UserInitializer.initialize();
%>
<html lang="en">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">
<link rel="icon" href="../favicon.ico">

<title>Edit Account</title>

<!-- Bootstrap core CSS -->
<link href="../dist/css/bootstrap.min.css" rel="stylesheet">

<!-- Custom styles for this template -->
<link href="../css/EditAccount.css" rel="stylesheet">

<!-- Just for debugging purposes. Don't actually copy these 2 lines! -->
<!--[if lt IE 9]><script src="../assets/js/ie8-responsive-file-warning.js"></script><![endif]-->
<script src="../dist/assets/js/ie-emulation-modes-warning.js"></script>

<!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
<!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
      <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
</head>
<body>

	<nav class="navbar navbar-default navbar-static-top">
		<div class="container">
			<div class="navbar-header">
				<button type="button" class="navbar-toggle collapsed"
					data-toggle="collapse" data-target="#navbar" aria-expanded="false"
					aria-controls="navbar">
					<span class="sr-only">Toggle navigation</span> 
					<span class="icon-bar"></span> 
					<span class="icon-bar"></span> 
					<span class="icon-bar"></span>
				</button>
			</div>
			<div id="navbar" class="navbar-collapse collapse">
				<ul class="nav navbar-nav">
					<li><a href="/" class="user">Search</a></li>
					<li><a href="/AddCustomer" class="user">Add Customer</a></li>
					<li><a href="SystemLogs" class="admin">System Logs</a></li>										
				</ul>
				<ul class="nav navbar-nav navbar-right">
					<li class="active"><a href="#">Edit Account</a></li>
					<li><a href="/logout">Log out</a></li>
				</ul>
			</div>
			<!--/.nav-collapse -->
		</div>
	</nav>
	<div id='page' class="container">	
		<div id='accountData' class="form-horizontal">		
			<div class="form-group">
				<label for="username1" class="col-sm-2 control-label">Email</label>
				<div class="col-sm-10">
					<input type="email" class="form-control" id="username1"
						placeholder="Email">
				</div>
			</div>
			<div class="form-group">
				<label for="username2" class="col-sm-2 control-label">Confirm
					Email</label>
				<div class="col-sm-10">
					<input type="email" class="form-control" id="username2"
						placeholder="Email">																			
				</div>
			</div>
			<div class="form-group">
				<label for="password1" class="col-sm-2 control-label">Password</label>
				<div class="col-sm-10">
					<input type="password" class="form-control" id="password1"
						placeholder="Password">
				</div>
			</div>
			<div class="form-group">
				<label for="password2" class="col-sm-2 control-label">Confirm
					Password</label>
				<div class="col-sm-10">
					<input type="password" class="form-control" id="password2"
						placeholder="Password">
				</div>
			</div>
			<div class="form-group">
				<div class="col-sm-offset-2 col-sm-10">
					<a href='' class="btn btn-default" id='send'>Save Changes</a>
					<a href='/DeleteAccount' class="btn btn-default user" id='delete'>Delete</a>
					<a href='/' class="btn btn-default" id='cancel'>Cancel</a>									
				</div>
			</div>
		</div>
	</div>
	<!-- /container -->

	<!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
	<script src="../dist/assets/js/ie10-viewport-bug-workaround.js"></script>

	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
	<script src="../dist/js/bootstrap.min.js"></script>

	<script src='../js/account/AccountManager.js'></script>
	
	<script src='../js/view/ViewManager.js'></script>
	
	<script src='../js/validation/Regex.js'></script>
	<script>
		var account = null
		$(				
			function(){
				var userProfile = <%=userProfile%>

				if(userProfile == null)
					window.location.href = "/";
				
				if(userProfile.role == 'ROLE_ADMIN'){
					$('.admin').show()
					$('.user').hide()					
				}
				else{
					$('.admin').hide()
					$('.user').show()					
				}
				
				var user = userProfile.user
				
				$('#username1').val(user.username)
				$('#username2').val(user.username)
				
				$('#password1').val(user.userPassword)				
				$('#password2').val(user.userPassword)				
				
				account = new AccountManager('UpdateAccount', user.userId)
			},
			
			$('body').on('change', 'input', function(event){
				event.preventDefault()

				account.validate(this)							
			})
		);
	</script>
</body>
</html>