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
	//$log.info('LinksController');

	$scope.owners = owners.data;

	/*
	function resizeDivLinks() {
		var maxSize = null;
		$(".divlinks").each(function () {
			if (maxSize == null || $(this).height() > maxSize) {
				maxSize = $(this).height();
			}
		});
		$(".divlinks").each(function () {
			$(this).height(maxSize);
		});
	}
	resizeDivLinks();
	*/
});
