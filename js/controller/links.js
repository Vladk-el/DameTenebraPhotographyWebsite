angular.module('links', [])

.config(function config($stateProvider) {
	$stateProvider
		.state('links', {
			parent: '',
			url: '/links',
			views: {
				'main@': {
					controller: 'LinksController',
					templateUrl: 'partials/links.tpl.html'
				}
			},
			data: {
				pageTitle: 'Links'
			},
			resolve: {
				owners: function ($http) {
					return $http.get('php/services/owner/owners.php?display=true');
				}
			}
		});
})

.controller('LinksController', function ($scope, $http, $log, owners) {

	$scope.owners = owners.data;

});
