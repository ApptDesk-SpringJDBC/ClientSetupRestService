clientApp.controller('clientController', ['$scope','CommonService','$log','$routeParams','$location', '$q', function($scope,CommonService, $log, $routeParams, $location, $q) {
	$scope.timeZones =['US/Alaska','US/Aleutian','US/Arizona', 'US/Central','US/East-Indiana', 'US/Eastern', 'US/Hawaii', 'US/Indiana-Starke', 'US/Michigan', 'US/Mountain', 'US/Pacific', 'US/Pacific-New', 'US/Samoa', 'Canada/Atlantic', 'Canada/Central', 'Canada/East-Saskatchewan', 'Canada/Eastern', 'Canada/Mountain', 'Canada/Newfoundland', 'Canada/Pacific', 'Canada/Saskatchewan', 'Canada/Yukon'];
	$scope.states=["AL", "AK", "AZ", "AR", "CA", "CO", "CT", "DE", "DC", "FL", "GA", "HI", "ID", "IL", "IN", "IA", "KS", "KY", "LA", "ME", "MD", "MA",
		"MI", "MN", "MS", "MO", "MT", "NE", "NV", "NH", "NJ", "NM", "NY", "NC", "ND", "OH", "OK", "OR", "PA", "RI", "SC", "SD", "TN", "TX", "UT", "VT", "VA", "WA", "WV", "WI",
	    "WY"];
	
	$scope.errorMessage = "";
	$scope.clientDataModel = {clientName:'', clientCode:'', clientLogoFilePath:'', contactPerson:'',contactPhone:'',contactTitle:'',contactEmail:'',
			clientWebsite:'',clientAddress:'',clientCity:'',clientState:'', clientZip:'', clientTimeZone:'',
			billingName:'',billingEmail:'',billingCCEmail:'',hideSchedulerInput:false,hideRemindersInput:false, hideLocationInput:false, hideServiceInput:false,
			hideListOfDocsToBring:false,onlineScheduler:false,phoneScheduler:false,preferredAreaCode:'',preferredCity:'',preferredCountry:'',
				phoneReminder:'N', textReminder:'N',emailReminder:'N',status:1};
    
    $scope.locationModel = [{name:'',address:'', city:'',state: '', zip:'', timeZone:''}];
    $scope.serviceModel = [{name:'',duration:'',allowDuplicateAppts: 'Y'}];
    
    $scope.formData = {
    		clientData: $scope.clientDataModel,
    		listOfDocs: '',
    		locations : $scope.locationModel,
    		services : $scope.serviceModel	 
    }
    
    var formdata = new FormData();
    $scope.getTheFiles = function ($files) {
    	console.log($files);
    	angular.forEach($files, function (value, key) {
            formdata.append(key, value);
        });
    };
    
    // NOW UPLOAD THE FILES.
    var uploadFile = function () {
        var request = {
            method: 'POST',
            url: 'http://localhost:8085/clientsetupRestService/Upload/upload',
            data: formdata,
            headers: {
                'Content-Type': undefined
            }
        };
        CommonService.uploadFile(request);
	};
     

    $scope.listThingsBring = {};
    $scope.clientId = 0;
	$scope.save = function (formData) {
	    $log.debug("ClientData:");
	    $log.debug(formData.clientData);
	    $log.debug("ListOfDocuments:")
	    $log.debug(formData.listOfDocs);
	    $log.debug("Locations:")
	    $log.debug(formData.locations);
	    $log.debug("Services:")
	    $log.debug(formData.services);  
	   
		$scope.listThingsBring.listOfDocs = formData.listOfDocs;
		$scope.services = {"services":formData.services};
		$scope.locations = {"locations":formData.locations};
	   
	    // clientData saved to backend
	    var responseData = CommonService.addClient(formData.clientData);
		$q.all(responseData).then(
				responseData.then(
		        function(response) {
		        	console.log(response);
		        	if(response.status == 201) {
		        		var clientId = response.data.data.clientId;
		        		$scope.services.clientId = clientId;
		        		$scope.locations.clientId = clientId;
		        		$scope.clientId = clientId;
		        		$scope.listThingsBring.clientId = clientId;
		        		addListThingsBring();
		        		addLocations($scope.locations);
		        		addServices($scope.services);
		        		//uploadFile();
		        	}
		        },
		        function(reason) {
		        	$log.error("ClientData save Failed!");
					$scope.errorMessage ="Failed to save data";
		        }
		     )
	      );
	}
    
    var addListThingsBring = function() {
		responseData = CommonService.addListThingsBring($scope.listThingsBring);
		$q.all(responseData).then(
				responseData.then(
		        function(response) {
		        	console.log(response);
		        	if(response.status == 201) {
		        		$log.info("Saved to addListThingsBring.");
		        	}
		        },
		        function(reason) {
					$log.error("Error while saving the addListThingsBrings.");
					$scope.errorMessage ="Failed to save data";
		        }
		     )
	      );
    } 
    
    var addLocations = function(locations) {
	$log.info(locations);
	responseData = CommonService.addLocations(locations);
	$q.all(responseData).then(
			responseData.then(
	        function(response) {
	        	console.log(response);
	        	if(response.status == 201) {
	        		$log.info("Saved locations.");
	        	}
	        },
	        function(reason) {
				$log.error("Error while saving the addLocations.");
				$scope.errorMessage ="Failed to save data";
	        }
	     )
      );
    } 
    
    
    var addServices = function(services) {
    	$log.info(services);
    	responseData = CommonService.addServices(services);
    	$q.all(responseData).then(
    			responseData.then(
    	        function(response) {
    	        	console.log(response);
    	        	if(response.status == 201) {
    	        		$log.info("Saved serivices.");
    	        	}
    	        },
    	        function(reason) {
    				$log.error("Error while saving the addServices");
    				$scope.errorMessage ="Failed to save data";
    	        }
    	     )
          );
        } 

	$scope.reset = function () {
		$scope.errorMessage='';
	    $scope.clientDataModel = {clientName:'', clientCode:'', clientLogoFilePath:'', contactPerson:'',contactPhone:'',contactTitle:'',contactEmail:'',
				clientWebsite:'',clientAddress:'',clientCity:'',clientState:'', clientZip:'', clientTimeZone:'',
				billingName:'',billingEmail:'',billingCCEmail:'',hideSchedulerInput:false,hideRemindersInput:false, hideLocationInput:false, hideServiceInput:false,
				hideListOfDocsToBring:false,onlineScheduler:false,phoneScheduler:false,preferredAreaCode:'',preferredCity:'',preferredCountry:'',
					phoneReminder:'N', textReminder:'N',emailReminder:'N'};
	}
	
	$scope.next = function(formData) {
		$scope.save(formData);
		$log.info("::::"+$scope.errorMessage);
		if($scope.errorMessage === '') {
			$location.path("/schdulerForm/"+$scope.clientId);
		}
	}

	var init = function () {
		$scope.isAdmin = angular.isUndefined($routeParams.userName) ? false : true;	
	}
	init();
	
	
	$scope.locRows = [0];
	$scope.fields= ['name','address','city','state','zip','timeZone'];
	
	$scope.counter=1;
	$scope.addLocationRow = function() {
		$scope.locRows.push($scope.counter);
		$scope.counter++;
	}
	
	$scope.deleteLocationRow = function(index) {
		$scope.locRows.splice(index, 1);
	}
	
	$scope.serviceRows = [0];
	$scope.serCounter=1;
	$scope.addServiceRow = function() {
		$scope.serviceRows.push($scope.serCounter);
		$scope.serCounter++;
	}
	
	$scope.deleteServiceRow = function(index) {
		$scope.serviceRows.splice(index, 1);
	}
}]);