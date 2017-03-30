angular.module('portfolio', [])

.config(function config($stateProvider) {
	$stateProvider
		.state('portfolio', {
			parent: '',
			url: '/portfolio',
			views: {
				'main@': {
					controller: 'PortfolioController',
					templateUrl: 'partials/portfolio.tpl.html'
				}
			},
			data: {
				pageTitle: 'Portfolio'
			},
			resolve: {
				categories: function ($http) {
					return $http.get('php/services/category/categories.php?display=true');
				}
			}
		});
})

.controller('PortfolioController', function ($scope, $log, categories) {

	$scope.categories = categories.data;

});
