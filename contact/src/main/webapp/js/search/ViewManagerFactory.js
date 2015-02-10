

mainApp.factory('ViewManager', function() {

	return {
		manageView : function($scope) {

			if ($scope.customers.length != 0
					&& $scope.customers.length != undefined) {
				$scope.showTableGroup = true
				$scope.nothingFound = false
			} else {
				$scope.showTableGroup = false
				$scope.nothingFound = true
			}
			$scope.waiting = false
		}
	}
});