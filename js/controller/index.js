(function () {
	'use strict';

	angular.module('indexService', [])

	.controller('IndexController', function ($scope, $http, $log) {
		//$log.info('IndexController');

		$scope.photos = [];

		$http.get('php/services/photo/photos.php?display=true')
			.then(function (response) {
				var temp = [];
				for (var key in response) {
					//$log.debug("key", key, "response[key]", response[key]);
					if (response[key].photo_width > response[key].photo_height && response[key].active == "1") {
						temp.push(response[key]);
					}
				}
				var index = 0;
				for (var i = temp.length - 1; i > temp.length - 6; i--) {
					$scope.photos.push(temp[i]);
					temp[i]["index"] = index;
					index++;
				}
				//$log.debug("photos ==>", $scope.photos);
			})
			.catch(function (error) {
				$log.error(error);
			});
	});

})(angular);
