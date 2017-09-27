app.controller('MyCustomerController',function($scope,$routeParams,customFactory){
/*$scope.sortBy="name";
$scope.reverse=false;*/


var customerId = $routeParams.customerId;
if( typeof(customerId) !== "undefined" && customerId !== null){
	$scope.customers=customFactory.RemoveCustomers(customerId).success(function(data) {
	    $scope.customers = data;
	});
}else{
	$scope.customers = [];
	$scope.customers=customFactory.getCustomers().success(function(data) {
	    $scope.customers = data;
	});
}
}
);