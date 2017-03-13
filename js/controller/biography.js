angular.module('biography', [])

.config(function config($stateProvider) {
	$stateProvider
		.state('biography', {
			parent: '',
			url: '/biography',
			views: {
				'main@': {
					controller: 'BiographyController',
					templateUrl: 'partials/biography.tpl.html'
				}
			},
			data: {
				pageTitle: 'Biography'
			},
			resolve: {}
		});
})

.controller('BiographyController', function ($scope, $http, $log) {
	//$log.info('IndexController');
});
