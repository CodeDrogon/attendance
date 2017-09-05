taskManagerModule.controller('taskManagerController', function ($scope,$rootScope,$http,taskmanagerFactory,ngDialog) {
	
	  $scope.checkin=function(){
	
	taskmanagerFactory.getEmployee($scope.employeeId).then(function(response) {
      
      alert(response);
    });
	 
}
	  
});
