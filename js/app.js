(function (angular) {
	'use strict';

	angular.module('ngApp', ['ui.router', 'home', 'biography'])
		// , 'indexService', 'portfolioService', 'galleryService', 'linkService', 'contactService'

	.controller('MainController', function ($scope, $location) {
		/*$scope.$route = $route;
		$scope.$location = $location;
		$scope.$routeParams = $routeParams;*/
		$scope.currenDate = new Date();

		/*$scope.isActive = function (viewLocation) {
			var active = (viewLocation === $location.path());
			return active;
		};*/
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

	/*.config(function ($routeProvider, $locationProvider) {
		$locationProvider.hashPrefix('');
		$routeProvider
			.when('/', {
				templateUrl: 'partials/index.html',
				controller: 'IndexController'
			})
			.when('/portfolio', {
				templateUrl: 'partials/portfolio.html',
				controller: 'PortfolioController'
			})
			.when('/gallery/:category_id', {
				templateUrl: 'partials/gallery.html',
				controller: 'GalleryController'
			})
			.when('/contact', {
				templateUrl: 'partials/contact.html',
				controller: 'ContactController'
			})
			.when('/links', {
				templateUrl: 'partials/links.html',
				controller: 'LinksController'
			})
			.when('/biography', {
				templateUrl: 'partials/biography.html',
				controller: 'BiographyController'
			})
			.when('/legal_notice', {
				templateUrl: 'partials/legal_notice.html',
				controller: 'LegalNoticeController'
			})
			.otherwise({
				redirectTo: '/'
			});
	})*/

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
