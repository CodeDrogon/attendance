app.controller('MyOrderController',function ($scope, $routeParams, customFactory) {
    var customerId = $routeParams.customerId;
    $scope.customer = null;
    
    customFactory.getCustomer(customerId).success(function(customer){
        $scope.orderDetails = customer.listOrderDetails;
        
    });     
    
    /*function init(){
        customerFactory.getCustomer(customerId)
        .success(function(customer){
            $scope.customer = customer;
        })
        .error(function(data, status, headers, config){
            // error handling
        });
    }
     
    init();*/
});