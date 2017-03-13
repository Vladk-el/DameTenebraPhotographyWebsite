(function (angular) {
	'use strict';

	angular.module('ngApp', ['ui.router', 'home', 'links', 'biography', 'portfolio', 'gallery', 'contact', 'legalNotice'])
		// , 'indexService', 'portfolioService', 'galleryService', 'linkService', 'contactService'

	.controller('MainController', function ($scope, $location) {
		$scope.$location = $location;
		$scope.currenDate = new Date();

		$scope.isActive = function (viewLocation) {
			var active = (viewLocation === $location.path());
			return active;
		};
	})

	.controller('BiographyController', function ($scope, $log) {
		//$log.info('BiographyController');
	})

	.controller('LegalNoticeController', function ($scope, $log) {
		//$log.info('LegalNoticeController');
	})

	.config(function ($stateProvider, $locationProvider, $urlRouterProvider) {
		$locationProvider.hashPrefix('');
		$urlRouterProvider.otherwise('/home');
	})

	.filter('objectToArray', function () {
		return function (input) {
			var out = [];
			for (var i in input) {
				out.push(input[i]);
			}
			return out;
		}
	});

})(window.angular);

//Mobile navbar close on click
$(document).on('click', '#navbar.show', function (e) {
	if ($(e.target).is('a') && $(e.target).attr('class') != 'dropdown-toggle') {
		$(this).collapse('hide');
	}
});
