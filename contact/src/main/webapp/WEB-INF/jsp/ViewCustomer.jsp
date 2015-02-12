<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="viewInitializer.ViewCustomerInitializer"%>
<%
	String json = ViewCustomerInitializer.initialize(request);
%>
<html lang="en">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">
<link rel="icon" href="../favicon.ico">
<link rel="stylesheet" href="//code.jquery.com/qunit/qunit-1.16.0.css">

<meta name="_csrf" content="${_csrf.token}"/>    
<meta name="_csrf_header" content="${_csrf.headerName}"/>

<title>View Customer</title>

<!-- Bootstrap core CSS -->
<link href="../dist/css/bootstrap.min.css" rel="stylesheet">

<!-- Custom styles for this template -->
<link href="../css/AddCustomer.css" rel="stylesheet">

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
					<span class="sr-only">Toggle navigation</span> <span
						class="icon-bar"></span> <span class="icon-bar"></span> <span
						class="icon-bar"></span>
				</button>
			</div>
			<div id="navbar" class="navbar-collapse collapse">
				<ul class="nav navbar-nav">
					<li><a href="/">Search</a></li>
					<li><a href="/AddCustomer">Add Customer</a></li>
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
		<div id='navTab'>
			<ul class="nav nav-tabs">
				<li role="presentation" class="active tab" id='nameTabElement'><a href="" id='nameTab'>Name</a></li>
				<li role="presentation" class='tab' id='emailTabElement'><a href="" id='emailTab'>Email</a></li>
				<li role="presentation" class='tab' id='phoneTabElement'><a href="" id='phoneTab'>Phone</a></li>
				<li role="presentation" class='tab' id='addressTabElement'><a href="" id='addressTab'>Address</a></li>
			</ul>
			<div id='nameData' class="form-horizontal tabData">
				<div class="form-group">
					<label for="customer_name" class="col-sm-2 control-label">Name</label>
					<div class="col-sm-10">
						<input type="text" class="form-control name" id="customer_name"
							placeholder="Name">
					</div>
				</div>
				<div class="form-group">
					<label for="last_name" class="col-sm-2 control-label"> Last
						Name </label>
					<div class="col-sm-10">
						<input type="text" class="form-control lastName" id="last_name"
							placeholder="Last Name">
					</div>
				</div>
			</div>
			<div id='emailData' class="form-horizontal tabData">
				<div class="col-sm-12" id="addDivEmail">
					<span class="badge add addData" id="addEmail">add</span>
				</div>
			</div>
			<div id='phoneData' class="form-horizontal tabData">
				<div class="col-sm-12" id="addDivPhoneNumber">
					<span class="badge add addData" id="addPhoneNumber">add</span>
				</div>				
			</div>
			<div id='addressData' class="form-horizontal tabData">
				<div class="col-sm-12" id="addDivAddress">
					<span class="badge add addData" id="addAddress">add</span>
				</div>				
			</div>
		</div>
	</div>
	<!-- /container -->

	<!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
	<script src="../dist/assets/js/ie10-viewport-bug-workaround.js"></script>

	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
	<script src="../dist/js/bootstrap.min.js"></script>
	
	<script src='../js/data/DataManager.js'></script>		
	<script src='../js/data/NewDataManager.js'></script>	
	<script src='../js/data/UpdateDataManager.js'></script>
	<script src='../js/data/CustomerDataManager.js'></script>	

	<script src='../js/view/ViewManager.js'></script>
	<script src='../js/edit/AddManager.js'></script>
	<script src='../js/edit/EditManager.js'></script>
	
	<script src='../js/validation/Regex.js'></script>	
	<script src='../js/Utilities.js'></script>
		
	<script>
		var editManager = new EditManager()
	
		$(
			function() {
				event.preventDefault()
	
				var json = <%=json%>
				if(json != null){
					editManager.initialize(json)
				}
				else{
					window.location.href = "/";		
				}
			},			
	
			$('.tab').click(function(event) {
				event.preventDefault()
	
				AddManager.prototype.tab.call(editManager, this)									
			}),			
			
			$('body').on('click', '.addData', function(event){
				event.preventDefault()
				
				AddManager.prototype.addData.call(editManager, this)									
			}),		
			
			$('body').on('click', '.deleteElement', function(event){
				event.preventDefault()

				AddManager.prototype.deleteElement.call(editManager, this)									
			}),
			
			$('body').on('change', 'input', function(event){
				event.preventDefault()

				AddManager.prototype.inputValidate.call(editManager, this)									
			}),
			
			$(window).keydown(function(event){
			    if(event.keyCode == 13) {
			      event.preventDefault();
			      return false;
			    }
			  }),
			
			$('body').on('click', '.changeButton', function(event){
				event.preventDefault()

				editManager.saveData()
			}),
			
			$('body').on('click', '.deleteButton', function(event){
				event.preventDefault()

				editManager.deleteData()
			})
		);
	</script>
	
</body>
</html>
