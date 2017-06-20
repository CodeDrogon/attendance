var taskManagerModule = angular.module('springapp', ['ngRoute','ngDialog']);
taskManagerModule.config(['$routeProvider','ngDialogProvider',function($routeProvider,ngDialogProvider){
	$routeProvider.when('/',{
	controller:'taskManagerController',
	templateUrl:'app/views/home.html'
	}).
	/*$routeProvider.when('/',{
	controller:'timesheetCntr',
	templateUrl:'app/views/home.html'
	}).
	*/
	otherwise({redirectTo:'/'});
	
	
	
	 ngDialogProvider.setDefaults({
                className: 'ngdialog-theme-default',
                plain: false,
                showClose: true,
                closeByDocument: true,
                closeByEscape: true,
                appendTo: false,
                preCloseCallback: function () {
                    console.log('default pre-close callback');
                }
            });
	}]);
