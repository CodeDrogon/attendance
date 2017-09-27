var app = angular.module('productApp', ['ngRoute']);
app.config(function($routeProvider){
	$routeProvider.when('/',{
	controller:'productController',
	templateUrl:'app/views/product.html'
	}).when('/saveProduct',{
	controller:'productController',
	templateUrl:'app/views/product.html'
	}).
	otherwise({redirectTo:'/'});
	});
