<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
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

      <form class="form-signin" action="/login" method="post">
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>         
                      
        <input type="email" id="username" name="username" class="form-control" placeholder="Email" required autofocus>
        
        <input type="password" id="password" name="password" class="form-control" placeholder="Password" required>
        
        <button class="btn btn-lg btn-primary btn-block" type="submit">Sign in</button>
        
        <a href="/viewRegister" id='reg'>Register</a>
        
      </form>

    </div> <!-- /container -->
</body>
</html>