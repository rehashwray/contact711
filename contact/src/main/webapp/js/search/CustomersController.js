

mainApp.controller("customersController", 
		function($scope, Search) {

	$scope.searchQuery = ''

	$scope.customers = []	
	
	$scope.waiting = true
	$scope.showTableGroup = false
	$scope.nothingFound = false

	$scope.pageModel = {}	
	$scope.displayLimit = 1
	
	$scope.getCustomers = function(currentPage) {
		Search.getCustomers($scope, currentPage)
	}	
});

mainApp.filter('encodeURIComponent', function() {
    return window.encodeURIComponent;
});



