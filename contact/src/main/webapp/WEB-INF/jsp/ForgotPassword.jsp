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
                             
        <input type="email" id="username" class="form-control" placeholder="Email" required autofocus>
        
        <a href='' class="btn btn-lg btn-primary btn-block" id='forgot'>Send Password to Email</a>
        
      </div>

    </div> <!-- /container -->
    
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
    <script src="../dist/js/bootstrap.min.js"></script>
    
    <script src='../js/view/ViewManager.js'></script>
	
	<script src='../js/validation/Regex.js'></script>
	
	<script>
		var viewManager = new ViewManager()
		
		$(							
			$('body').on('change', 'input', function(event){
				event.preventDefault()

				var username = $('#username').val()//email
										
				var url = 'http://localhost:8181/recoverPassword?'
				var data = 'username=' + encodeURIComponent(username)
				
				$('#forgot').attr('href', url + data)	
			})		
		);
	</script>
</body>
</html>