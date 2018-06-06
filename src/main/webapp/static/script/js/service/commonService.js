'use strict';

clientApp.factory('CommonService', ['$http', '$q', '$log', function($http, $q, $log) {	
	return {
		authenticateUser: function(loginInfo) {
      		 var promise = $http.get('/clientsetupRestService/clientsetup/authenticate?userName='+loginInfo.username+'&password='+loginInfo.password);
      		 var deferObject =  $q.defer();
              promise.then(function(response){
                deferObject.resolve(response);
              },
              function(reason){
                deferObject.reject(reason);
              });
           	  return deferObject.promise;
           },
      
	     getClientsLite: function() {
    		 var promise = $http.get('/clientsetupRestService/clientsetup/getClientsLite');
    		 var deferObject =  $q.defer();
            promise.then(function(response){
              deferObject.resolve(response);
            },
            function(reason){
              deferObject.reject(reason);
            });
         	return deferObject.promise;
         },
    
	     getClientById: function(clientId) {
			 var promise = $http.get('/clientsetupRestService/clientsetup/client/'+clientId);
			 var deferObject =  $q.defer();
		        promise.then(function(response){
		          deferObject.resolve(response);
		        },
		        function(reason){
		          deferObject.reject(reason);
		        });
		     	return deferObject.promise;
		},
		addClient: function(clientData) {
			 var promise = $http.post('/clientsetupRestService/clientsetup/addClient',clientData);
			 var deferObject =  $q.defer();
		        promise.then(function(response){
		          deferObject.resolve(response);
		        },
		        function(reason){
		          deferObject.reject(reason);
		        });
		     	return deferObject.promise;
		},
		addListThingsBring: function(listOfThingsBring) {
			 var promise = $http.post('/clientsetupRestService/clientsetup/addListThingsBring',listOfThingsBring);
			 var deferObject =  $q.defer();
		        promise.then(function(response){
		          deferObject.resolve(response);
		        },
		        function(reason){
		          deferObject.reject(reason);
		        });
		     	return deferObject.promise;
		},
		addLocations: function(locations) {
			 var promise = $http.post('/clientsetupRestService/clientsetup/addLocations',locations);
			 var deferObject =  $q.defer();
		        promise.then(function(response){
		          deferObject.resolve(response);
		        },
		        function(reason){
		          deferObject.reject(reason);
		        });
		     	return deferObject.promise;
		},
		addServices: function(services) {
			 var promise = $http.post('/clientsetupRestService/clientsetup/addServices',services);
			 var deferObject =  $q.defer();
		        promise.then(function(response){
		          deferObject.resolve(response);
		        },
		        function(reason){
		          deferObject.reject(reason);
		        });
		     	return deferObject.promise;
		},
		
		addSchedulerConfig: function(schedulerConfig) {
			 var promise = $http.post('/clientsetupRestService/clientsetup/addSchedulerConfig',schedulerConfig);
			 var deferObject =  $q.defer();
		        promise.then(function(response){
		          deferObject.resolve(response);
		        },
		        function(reason){
		          deferObject.reject(reason);
		        });
		     	return deferObject.promise;
		},
		uploadFile : function(request) {
			// SEND THE FILES.
	        $http(request)
	            .success(function (d) {
	                alert(d);
	                $log.info(d);
	            })
	            .error(function () {
	            });
	    	}
 	}  
}]);