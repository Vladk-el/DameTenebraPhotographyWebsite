(function () {
	'use strict';

	angular.module('linkService', [])

	.controller('LinksController', function ($scope, $http, $log) {
		//$log.info('LinksController');

		$scope.owners = [];

		$http.get('php/services/owner/owners.php?display=true')
			.then(function (response) {
				$scope.owners = response;
				//$log.debug(response);
			})
			.catch(function (error) {
				$log.error(error);
			});

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
	});

})(angular);
