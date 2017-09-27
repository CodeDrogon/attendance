app.controller('productController',function($scope,$routeParams,productFactory){
	
	
	$scope.saveProduct = function() {
		alert("saveUser");
	productFactory.saveProduct($scope.product, function(resp){
    });
}
});