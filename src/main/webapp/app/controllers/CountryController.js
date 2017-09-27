 app.controller("CountryController", function($scope,countryFactory) {
 
	 
	 $scope.countries=countryFactory.getCuntryList().success(function(data) {
		 $scope.countries=data;
	 });
	 
	 
	 
	 $scope.deleteCountry = function(country) {
		 console.log(country);
		 countryFactory.deleteCountry(country.id).success(function(data) {
			 $scope.countries=countryFactory.getCuntryList().success(function(response) {
				 $scope.countries=response;
			 });
		 });
	 };
	 
	 $scope.addCountry = function(country) {
		 $scope.countryForm.id=5;
		 console.log("country form data "+$scope.countryForm.id);
		 console.log("country form data "+$scope.countryForm.countryName);
		 console.log("country form data "+$scope.countryForm.population);
		 countryFactory.addCountry($scope.countryForm).success(function(data) {
			 
			 $scope.countries=countryFactory.getCuntryList().success(function(response) {
				 $scope.countries=response;
			 });
		 });
			 
		 }
		 /*$scope.countryForm.countryName = country.countryName; 
		 $scope.countryForm.population = country.population; 
		 $scope.countryForm.id = country.id; */
	 
	 
	 $scope.updateCountry=function() {
		 
		 console.log($scope.countryForm);
		 countryFactory.updateCountry($scope.countryForm).success(function(data) {
			 $scope.countries=data;
		 });
	 }
		
	 
	/* $scope.countries = []; 
	 $scope.countryForm = { id : -1, countryName : "", population : "" }; //Now load the data from server
	 _refreshCountryData(); //HTTP POST/PUT methods for add/edit country // with the help of id, we are going to find out whether it is put or post operation $scope.submitCountry = function() {
	
	 	 
		 function  _refreshCountryData() {
	 $http({ 
	 method : 'GET', 
	 url : 'http://localhost:8080/springapp/countries'
	 }).then(
	function successCallback(response) 
	     {
		 $scope.countries = response.data; 
		 }, 
	 function errorCallback(response) {
			 
	 console.log(response.statusText); 
	 }
		 );
	 }
	 */
	 
	 
	 
	 
	 
	 
	 /*$scope.countries = []; 
	 $scope.countryForm = { id : -1, countryName : "", population : "" }; //Now load the data from server
	 _refreshCountryData(); //HTTP POST/PUT methods for add/edit country // with the help of id, we are going to find out whether it is put or post operation $scope.submitCountry = function() {
	 var method = ""; 
	 var url = ""; 
	 console.log("before if ");
	 if ($scope.countryForm.id == -1) {
		 console.log("id=-1");
	 //Id is absent in form data, it is create new country operation 
	 method = "GET"; 
	 url = 'http://localhost:8080/springapp/countries'; 
	 } else { //Id is present in form data, it is edit country operation 
		 console.log("not = -1");
	 method = "PUT"; 
	 url = 'http://localhost:8080/springapp/countries'; 
	 } 
	 
	 $http({ 
	 method : method, 
	 url : url, 
	 data : angular.toJson($scope.countryForm), 
	 headers : { 
	 'Content-Type' : 'application/json' }
	 }).then(_success, _error); 
	 
	  $scope.deleteCountry = function(country) {
	 $http({ 
	 method : 'DELETE', 
	 url : 'http://localhost:8080/springapp/country/' + country.id }
	 ).then(_success, _error); }; // In case of edit, populate form fields and assign form.id with country id 
	 
	 $scope.editCountry = function(country) { 
	 $scope.countryForm.countryName = country.countryName; 
	 $scope.countryForm.population = country.population; 
	 $scope.countryForm.id = country.id; 
	 } 
	 function  _refreshCountryData() {
	 $http({ 
	 method : 'GET', 
	 url : 'http://localhost:8080/springapp/countries'
	 }).then(
	function successCallback(response) 
	     {
		 $scope.countries = response.data; 
		 }, 
	 function errorCallback(response) {
			 
	 console.log(response.statusText); 
	 }
		 );
	 }
	 
	 function _success(response) { 
	 _refreshCountryData(); 
	 _clearFormData() 
	 } 
	 function _error(response) { 
	 console.log(response.statusText); 
	 } //Clear the form 
	 
	 function _clearFormData() { 
	 $scope.countryForm.id = -1;
	 $scope.countryForm.countryName = ""; 
	 $scope.countryForm.population = ""; 
	 };  
	 */
 });