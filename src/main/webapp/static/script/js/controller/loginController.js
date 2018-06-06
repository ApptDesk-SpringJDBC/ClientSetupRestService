'use strict';
clientApp.controller('loginController', ['$scope', '$location','CommonService','$q','$log',function ($scope, $location,CommonService,$q,$log) {
	$scope.loginInfo={username:'',password:'',clientId:-1};
	$scope.loginErrorMessage='';
	$scope.clients = [];
	$scope.login = function(loginInfo) {
		var responseData = CommonService.authenticateUser(loginInfo);
		$q.all(responseData).then(
				responseData.then(
		        function(response) {
		        	if(response.status == 200) {
		        		$location.path('/clientForm/'+loginInfo.username+'/'+loginInfo.clientId);
		        	}
		        },
		        function(reason) {
					$scope.loginErrorMessage = "Username or Password is incorrect";
		        }
		     )
	      );
	};
	
	$scope.loadClients = function () {
		var responseData = CommonService.getClientsLite();
		$q.all(responseData).then(
				responseData.then(
		        function(response) {
		        	console.log(response);
		        	if(response.status == 200) {
		        		$log.info(response.data);
		        		$scope.clients = response.data.data;
		        	}
		        },
		        function(reason) {
		        	$log.error("Error while fetching clients!");
					$scope.loginErrorMessage = "System failure.";
		        }
		     )
	      );
	}


	$scope.reset = function() {
		$scope.loginInfo={username:'',password:''};
		$scope.loginErrorMessage='';
	};
}]);