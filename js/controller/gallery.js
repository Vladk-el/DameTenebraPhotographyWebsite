(function () {
	'use strict';

	angular.module('galleryService', ['ngAnimate', 'ngSanitize', 'ui.bootstrap'])

	.controller('GalleryController', function ($scope, $http, $routeParams, $log, $filter, $uibModal, $document) {
		//$log.info('GalleryController');
		//$log.debug("$routeParams", $routeParams);

		$scope.category_id = $routeParams.category_id;
		$scope.category = {};
		$scope.photos = [];
		$scope.images = [];

		$http.get('php/services/category/category.php?category=' + $scope.category_id)
			.then(function (response) {
				$scope.category = response.data;
				$log.debug(response);
			})
			.catch(function (error) {
				$log.error(error);
			});

		$http.get('php/services/photo/photos_by_category.php?category=' + $scope.category_id)
			.then(function (response) {
				$scope.photos = response.data;
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

		$scope.show = function (photo, parentSelector) {
			var parentElem = parentSelector ?
				angular.element($document[0].querySelector('.gallery-container ' + parentSelector)) : undefined;
			var modal = $uibModal.open({
				animation: true,
				ariaLabelledBy: 'modal-title',
				ariaDescribedBy: 'modal-body',
				templateUrl: 'partials/templates/modals/viewer.tpl.html',
				size: 'lg',
				controller: 'ViewerModalCtrl',
				appendTo: parentElem,
				resolve: {
					photo: function () {
						return photo;
					}
				}
			});

			return modal.result
				.then(function () {
					// success
				}, function () {
					// error or cancel
					$log.info('modal-component dismissed at: ' + new Date());
				});
		}

	})

	.controller('ViewerModalCtrl', function ($scope, $log, $uibModalInstance, photo) {

		$log.debug("Enter ViewerModalCtrl with photo", photo);

		$scope.photo = photo;

		$scope.closeModalDialog = function () {
			$uibModalInstance.close();
		}

		$scope.cancelModalDialog = function () {
			$uibModalInstance.dismiss('cancel');
		}
	})

	;

})(angular);
