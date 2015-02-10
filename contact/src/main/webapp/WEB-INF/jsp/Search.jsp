<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE>    
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>

<!-- Bootstrap core CSS -->
<link href="../dist/css/bootstrap.min.css" type="text/css" rel="stylesheet" />

<!-- Custom styles for this template -->
<link href="../css/HomeAngular.css" type="text/css" rel="stylesheet"/>

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
					<li class="active"><a href="#">Search</a></li>
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

	<div id='page' class="container" ng-app="mainApp" ng-controller="customersController" ng-cloak class="ng-cloak">
		<div class="row">
			<br> <br>
		</div>
		<div class="row">
			<div class="col-lg-12">
				<div id="searchGroup" class="input-group">
					<input type="text" id="searchBar" class="form-control input-lg" placeholder="Search for..." ng-model="searchQuery"> 
					<span class="input-group-btn">
						<button type="button" class="btn btn-default btn-lg" id="searchButton" 
								ng-click="getCustomers(1)">Search</button>
					</span>
				</div>
			</div>

		</div>

		<div id='tableGroup' ng-show="showTableGroup" ng-cloak class="ng-cloak">
			<table class="table">
				<thead>
					<tr ng-cloak class="ng-cloak">
						<th> Name </th>
						<th> Last Name </th>
					</tr>
				</thead>
				<tbody>	
					<tr ng-repeat="customer in customers" ng-cloak class="ng-cloak">
						<td> {{ customer.customer_name }} </td>
						<td> {{ customer.last_name }} 
							<a ng-href="/ViewCustomer?id={{customer.customer_id | encodeURIComponent}}">
								<span class="badge">View</span>								
							</a>
						</td>
					</tr>
				</tbody>
			</table>						
			
			<nav id="pager">
				<ul class="pagination"> 
					<li ng-class="pageSetting.state" ng-repeat="pageSetting in pageModel.pageSettings" ng-cloak class="ng-cloak">
						<a href="#" class="pageChanger" ng-click="getCustomers(pageSetting.pageNumber)"> 
							{{pageSetting.pageNumber}}
						</a>
					</li>
				</ul>
			</nav>								
		</div>
		
		<div id='emptyResult' class='well' ng-show="nothingFound">Nothing Found</div>

		<div id='waiting' class='well' ng-show="waiting">Waiting for search input...</div>
	</div>

	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
	<script src="../dist/js/bootstrap.min.js"></script>
	
	<script src="http://ajax.googleapis.com/ajax/libs/angularjs/1.2.15/angular.min.js"></script>
	<script src="../js/search/MainApp.js"></script>
	<script src="../js/search/CustomersController.js"></script>

	<script src="../js/search/SearchFactory.js"></script>	
	<script src="../js/search/PageManagerFactory.js"></script>
	<script src="../js/search/ViewManagerFactory.js"></script>
	
	<script src='../js/validation/Regex.js'></script>	
</body>
</html>