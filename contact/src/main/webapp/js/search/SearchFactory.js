

var SearchQueryParsed = function SearchQueryParsed() {

	this.nameWords = []
	this.lastNameWords = []
	this.emailWords = []
	this.phoneWords = []
	this.streetWords = []
	this.cityWords = []
	this.stateWords = []
	this.zipWords = []
	
	this.from = 0
	this.displayLimit = 10
}

mainApp.factory('Search', function($http, PageManager, ViewManager) {	
	return {
		getCustomers : function($scope, currentPage) {
			
			var searchQuery = $scope.searchQuery.toLowerCase();
			
			var queryWords = searchQuery.split(' ');
				
			var sqp = new SearchQueryParsed();
			for(var wordIndex in queryWords){
				var word = queryWords[wordIndex]
				
				if(Regex.validate(word, 'name').valid)
					sqp.nameWords.push(word);
				
				if(Regex.validate(word, 'lastName').valid)
					sqp.lastNameWords.push(word);
				
				if(Regex.validate(word, 'email').valid)
					sqp.emailWords.push(word);
				
				if(Regex.validate(word, 'phone_number').valid)
					sqp.phoneWords.push(word);
				
				if(Regex.validate(word, 'street').valid)
					sqp.streetWords.push(word);
				
				if(Regex.validate(word, 'city').valid)
					sqp.cityWords.push(word);
				
				if(Regex.validate(word, 'state').valid)
					sqp.stateWords.push(word);
				
				if(Regex.validate(word, 'zip_code').valid)
					sqp.zipWords.push(word);
			}
			
			sqp.from = (currentPage - 1) * $scope.displayLimit
			sqp.displayLimit = $scope.displayLimit
			
			var url = "http://localhost:8181/Search"		
			
			$http({
				url : url,
				method : "GET",
				params : {
					sqpJson : JSON.stringify(sqp)
				}
			}).success(					
				function(searchResult) {		
					if(searchResult.customersFound > 0){					
						$scope.customers = searchResult.customers
						
						PageManager.managePage(
							$scope, currentPage, searchResult.customersFound)						
					}else{
						$scope.customers = []
					}					
					ViewManager.manageView($scope)					
				}					
			)
			.error(function() {
					$scope.customers = []
					ViewManager.manageView($scope)
				}
			)
		}
	}
});
