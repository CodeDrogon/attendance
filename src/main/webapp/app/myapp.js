var app = angular.module('myApp', ['ngRoute']);
app.config(function($routeProvider){
$routeProvider.when('/',{
controller:'MyCustomerController',
templateUrl:'app/views/myCustomer.html'
}).when('/orders/:customerId',{
controller:'MyOrderController',
templateUrl:'app/views/myOrder.html'
}).when('/remove/:customerId',{
	controller:'MyCustomerController',
	templateUrl:'app/views/myCustomer.html'
	}).
otherwise({redirectTo:'/'});
});


