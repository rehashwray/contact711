
mainApp.factory('PageManager', function() {	
	
	return {	
		 managePage : function($scope, currentPage, customersFound) {	
	
			var pageModel = {}

			var minPage = currentPage - 1
			var maxPage = currentPage + 1
			
			if(minPage < 2){
				minPage = 1
				
				if(minPage == currentPage){
					maxPage++
				}
			}
			
			var totalPages = Math.ceil(customersFound / $scope.displayLimit)
				
			if(maxPage >= totalPages){
				maxPage = totalPages
				
				if(maxPage == currentPage && minPage > 1){
					minPage--		
				}
			}					
			
			var pageSettings = []
			for(var pageNumber = minPage; pageNumber <= maxPage; pageNumber++){										
					
				var state = ''
				if(pageNumber == currentPage)
					state = 'active'				
				pageSettings.push({pageNumber : pageNumber, state : state})				
			}						
			pageModel.pageSettings = pageSettings						
			
			pageModel.currentPage = currentPage
						
			$scope.pageModel = pageModel			
		}
	}
});