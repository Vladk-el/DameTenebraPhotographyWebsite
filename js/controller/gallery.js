(function () {
	'use strict';

	angular.module('galleryService', ['bootstrapLightbox'])

	.config(function (LightboxProvider) {
		// set a custom template
		LightboxProvider.templateUrl = 'partials/templates/lightbox.html';
	})

	.controller('GalleryController', function ($scope, $http, $routeParams, $log, $filter, Lightbox) {
		//$log.info('GalleryController');
		//$log.debug("$routeParams", $routeParams);

		$scope.category_id = $routeParams.category_id;
		$scope.category = {};
		$scope.photos = [];
		$scope.images = [];

		$http.get('php/services/category/category.php?category=' + $scope.category_id)
			.then(function (response) {
				$scope.category = response;
				//$log.debug(response);
			})
			.catch(function (error) {
				$log.error(error);
			});

		$http.get('php/services/photo/photos_by_category.php?category=' + $scope.category_id)
			.then(function (response) {
				$scope.photos = response;
				for (var i in $scope.photos) {
					var img = {};
					img.url = "img/full/" + $scope.photos[i].photo_link;
					img.title = $scope.photos[i].photo_name;
					img.caption = $scope.photos[i].photo_description;
					$scope.images.push(img);
				}
			})
			.catch(function (error) {
				$log.error(error);
			});

		$scope.openLightboxModal = function (index) {
			Lightbox.openModal($scope.images, index);
		};

	});

})(angular);
