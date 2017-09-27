taskManagerModule.service('taskmanagerFactory',function($http){
	
	this.getEmployee= function(employeeId){
	
return $http({

	            method: 'get',
	            url: 'http://192.168.50.33:8080/getEmployee/'+employeeId
	            
	        });

}
});