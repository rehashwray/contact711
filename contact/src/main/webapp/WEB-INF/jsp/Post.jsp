<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<!--meta name="_csrf" content="${_csrf.token}" /-->
<!--meta name="_csrf_header" content="${_csrf.headerName}" /-->

<title>Signin Template for Bootstrap</title>

<!-- Bootstrap core CSS -->
<link href="../../dist/css/bootstrap.min.css" rel="stylesheet">

<!-- Custom styles for this template -->
<link href="../../css/Signin.css" rel="stylesheet">

</head>
<body>

	<div class="container">

		<form class="form-horizontal" action="http://localhost:8181/post" method="post">
			<input type="hidden" name="${_csrf.parameterName}"
				value="${_csrf.token}" />

			<div class="form-group">
				<label for="lastName" class="col-sm-2 control-label"> Last
					Name </label>
				<div class="col-sm-10">
					<input type="text" class="form-control" name="lastName" id="lastName"
						placeholder="Last Name">
				</div>
			</div>

			<div class="form-group">
				<button type="submit" class="btn btn-lg btn-primary btn-block">Post</button>
			</div>
		</form>

	</div>

	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
	<script>
		$($('#send').click(function(event) {
			event.preventDefault()

			var url = 'http://localhost:8181/post'

			var token = $("meta[name='_csrf']").attr("content");
			var header = $("meta[name='_csrf_header']").attr("content");

			$.ajax({
				type : 'POST',
				data : 'p=p',
				beforeSend : function(request) {
					request.setRequestHeader(header, token);
				},
				url : url,
				success : function(response) {
					alert(response)
				}
			});
		}))
	</script>
</body>
</html>