<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<title>Signin Template for Bootstrap</title>

<!-- Bootstrap core CSS -->
<link href="../../dist/css/bootstrap.min.css" rel="stylesheet">

<!-- Custom styles for this template -->
<link href="../../css/Signin.css" rel="stylesheet">

</head>
<body>
	
	<div class="container">
      <div class="form-signin">                             
        <input type="email" id="username1" class="form-control" placeholder="Email" required autofocus>
        <input type="email" id="username2" class="form-control" placeholder="Email" required>
        
        <input type="password" id="password1" class="form-control" placeholder="Password" required>
        <input type="password" id="password2" class="form-control" placeholder="Password" required>
        
        <a href='' class="btn btn-lg btn-primary btn-block" id='send'>Register</a>
        <a href='/login' class="btn btn-lg btn-primary btn-block" id='cancel'>Cancel</a>									        
      </div>
    </div> <!-- /container -->
    
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
    <script src="../dist/js/bootstrap.min.js"></script>
    
    <script src='../js/account/AccountManager.js'></script>  
    
    <script src='../js/view/ViewManager.js'></script>
	
	<script src='../js/validation/Regex.js'></script>
	
	<script>
		var account = new AccountManager('register')
	
		$(							
			$('body').on('change', 'input', function(event){
				event.preventDefault()

				account.validate(this)				
			})		
		);
	</script>
</body>
</html>