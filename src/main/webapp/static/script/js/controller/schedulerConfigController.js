'use strict';
clientApp.controller('schedulerConfigController', ['$scope', '$location','CommonService','$q','$log','$routeParams',function ($scope, $location,CommonService,$q,$log, $routeParams) {
	$scope.schedulerConfig = {onlineSchedulerURL:'',database:'',calendarBlockTime:'',dayStartTime:'',dayEndTime:'',callCenterLogic:false,userName:'',password:'',dbTemplate:''};
	$scope.errorMessage = '';
	
	//$scope.hrs = [01,02,03,04,05,06,07];
	$scope.reset = function() {
		$scope.schedulerConfig={onlineSchedulerURL:'',database:'',calendarBlockTime:'',dayStartTime:'',dayEndTime:'',callCenterLogic:false,userName:'',password:'',dbTemplate:''};
		$scope.errorMessage='';
	};
	
	var paddingZero = function pad(num, size) {
	    var s = num+"";
	    while (s.length < size) s = "0" + s;
	    return s;
	}
	
	
	$scope.hrs = [1,2,3,4,5,6,7,8,9,10,11,12];
	$scope.mins = [5,10,15,20,30,35,40,45,50,55,60];
	$scope.schedulerConfig.clientId=parseInt($routeParams.clientId);
	$scope.confirm = function(schedulerConfig) {
	    	
	    	$log.info($scope.startHrs);
	    	$log.info($scope.endHrs);
	    	
	    	$scope.schedulerConfig.dayStartTime = paddingZero($scope.startHrs,2)+":"+paddingZero($scope.startMins,2)+" "+$scope.startTimeTypeStr;
	    	$scope.schedulerConfig.dayEndTime = paddingZero($scope.endHrs)+":"+paddingZero($scope.endMins)+" "+$scope.endTimeTypeStr;
	    	
	    	$log.info(schedulerConfig);
	    	var responseData = CommonService.addSchedulerConfig(schedulerConfig);
	    	$q.all(responseData).then(
	    			responseData.then(
	    	        function(response) {
	    	        	console.log(response);
	    	        	if(response.status == 201) {
	    	        		$log.info("Saved schedulerConfig.");
	    	        		$location.path('/confirm');
	    	        	}
	    	        },
	    	        function(reason) {
	    				$log.error("Error while saving the scheduler configuration");
	    				$scope.errorMessage ="Failed to save scheduler config";
	    	        }
	    	     )
	          );
	        } 
}]);