(function (angular) {
	'use strict';

	angular.module('ngApp', ['ui.router', 'home', 'links', 'biography', 'portfolio', 'gallery', 'contact', 'legalNotice'])

	.controller('MainController', function ($scope, $location) {
		$scope.$location = $location;
		$scope.currenDate = new Date();

		$scope.isActive = function (viewLocation) {
			var active = (viewLocation === $location.path());
			return active;
		};
	})

	.directive('keyTrap', function () {
		return function (scope, elem) {
			elem.bind('keyup', function (event) {
				scope.$broadcast('keyup', {
					code: event.keyCode
				});
			});
		};
	})

	.service('storageService', function ($window) {
		this.getFromSession = function (key) {
			var stringValue = $window.sessionStorage.getItem(key);
			if (stringValue) {
				return JSON.parse(stringValue);
			}
			return undefined;
		};

		this.storeToSession = function (key, value) {
			$window.sessionStorage.setItem(key, JSON.stringify(value));
		};
	})

	.config(function ($stateProvider, $locationProvider, $urlRouterProvider) {
		$locationProvider.hashPrefix('');
		$urlRouterProvider.otherwise('/home');
	})

	// add GA tracking
	.run(function ($rootScope, $window, $location, $log) {
		$window.ga('create', 'UA-70940445-1', 'none');

		$rootScope.$on('$stateChangeSuccess', function (event) {
			$window.ga('send', 'pageview', $location.path());
		});

		$rootScope.$on('viewerChange', function (event) {
			$window.ga('send', 'pageview', $location.url());
		});
	})

	.filter('objectToArray', function () {
		return function (input) {
			var out = [];
			for (var i in input) {
				out.push(input[i]);
			}
			return out;
		};
	});

})(window.angular);

//Mobile navbar close on click
$(document).on('click', '#navbar.show', function (e) {
	if ($(e.target).is('a') && $(e.target).attr('class') != 'dropdown-toggle') {
		$(this).collapse('hide');
	}
});
