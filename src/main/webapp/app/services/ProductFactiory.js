app.factory('productFactory', function($http) {

    return {
    	saveProduct: function(productData, cb) {
    		var url = 'http://localhost:8080/RestWebServc/rest/RestContr/saveProduct';
    		var stringProductData = JSON.stringify(productData);
    		alert("send data "+stringProductData);
    		 $http.post(url,productData)
             .success(function(data, status, headers, config) {
                 console.log("savedproduct", data);
                 cb("success");
                 alert("success");
             })
             .error(function(data, status, header, config) {
            	 alert("error");
             });
    	 }
    };

});

