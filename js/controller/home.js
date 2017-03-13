angular.module('home', ['ngAnimate', 'ui.bootstrap'])

.config(function config($stateProvider) {
	$stateProvider
		.state('home', {
			parent: '',
			url: '/home',
			views: {
				'main@': {
					controller: 'HomeController',
					templateUrl: 'partials/home.tpl.html'
				}
			},
			data: {
				pageTitle: 'Home'
			},
			resolve: {
				photos: function ($http) {
					return $http.get('php/services/photo/photos.php?home');
				}
			}
		});
})

.controller('HomeController', function ($scope, $http, $log, photos) {
	//$log.info('IndexController');

	$scope.photos = photos.data;

});
