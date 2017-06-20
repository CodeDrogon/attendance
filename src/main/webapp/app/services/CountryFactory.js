app.factory('countryFactory',function($http){    
    
 
	
return {
	getCuntryList: function(){
		return $http({ 
		 method : 'GET', 
		 url : 'http://localhost:8080/springapp/countries', 
		 data : "", 
		 headers : { 
		 'Content-Type' : 'application/json' }
		 }).success(function(data, status, headers, config) {
             console.log("getCuntryList", data);
         })
	}, 
         
         
		 
		 
		 /*.then(
				 function successCallback(response) 
			     {
				 countries = response.data; 
				 }, 
			 function errorCallback(response) {
					 
			 console.log(response.statusText); 
			 }
				 ); */
    deleteCountry: function(countryId){
		return $http({ 
			 method : 'DELETE', 
			 url : 'http://localhost:8080/springapp/country/' + countryId }
			 ).success(function(data, status, headers, config) {
	             console.log("deleteCountry", data);
	         })
    },
    addCountry: function(country){
		return $http({ 
			 method : 'POST', 
			 url : 'http://localhost:8080/springapp/addCountry/'  ,
			 data:country
		     }).success(function(data, status, headers, config) {
	             console.log("addCountry", data);
	         })
    },
    updateCountry: function(country){
		return $http({ 
			 method : 'DELETE', 
			 url : 'http://localhost:8080/springapp/country/',
			 data:country}
			 ).success(function(data, status, headers, config) {
	             console.log("updateCountry", data);
	         })
    },
    getCustomer: function(customerId){
         return $http.get('http://192.168.24.162:8080/RestWebServc/rest/RestContr/getcustomer/'+customerId);
    },RemoveCustomers: function(customerId){
        return $http.get('http://192.168.24.162:8080/RestWebServc/rest/RestContr/removeCustomer/'+customerId);
    }

}
});