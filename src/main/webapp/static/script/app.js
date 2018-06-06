var clientApp = angular.module('clientSetup',['ngRoute'],function ($locationProvider) {
	    $locationProvider.html5Mode(false);
	});

clientApp.config(['$routeProvider',
  function($routeProvider) {
    $routeProvider.
      when('/index', {
        templateUrl: 'static/html/login.html',
        controller: 'loginController'
      }).
      when('/clientForm/:userName/:clientId', {
        templateUrl: 'static/html/admin1.html',
        controller: 'clientController'
      }).
      when('/clientForm/:clientId', {
          templateUrl: 'static/html/admin1.html',
          controller: 'clientController'
      }).
      when('/schdulerForm/:clientId', {
          templateUrl: 'static/html/admin2.html',
          controller: 'schedulerConfigController'
      }).
      
      when('/confirm', {
          templateUrl: 'static/html/confirmation.html',
          controller: 'schedulerConfigController'
      }).
      otherwise({
        redirectTo: '/index'
      });
  }]);


clientApp.run(['$route', function ($route) {
	console.log("reload ");
	$route.reload();
}]);


