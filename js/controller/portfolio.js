(function () {
	'use strict';

	angular.module('portfolioService', [])

	.controller('PortfolioController', function ($scope, $http, $location, $log) {
		//$log.info('PortfolioController');

		$scope.categories = [];

		$http.get('php/services/category/categories.php?display=true')
			.then(function (response) {
				$scope.categories = response;
				//$log.debug(data);
			})
			.catch(function (error) {
				$log.error(error);
			});

		$scope.go = function (path) {
			$location.path(path);
		}

	});

})(angular);
