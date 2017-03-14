angular.module('gallery')

.service('galleryService', function (storageService) {
	var keys = {
		category: 'galleryCurrentCategory',
		photos: 'galleryPhotos'
	};

	this.getCurrentCategory = function () {
		return storageService.getFromSession(keys.category);
	}

	this.storeCurrentCategory = function (category) {
		storageService.storeToSession(keys.category, category)
	}

	this.getPhotos = function () {
		return storageService.getFromSession(keys.photos);
	}

	this.storePhotos = function (photos) {
		storageService.storeToSession(keys.photos, photos)
	}
})

.controller('ViewerCtrl', function ($scope, $log, $uibModalInstance, $state, $stateParams, photos, id) {

	$log.debug("Enter ViewerCtrl with photo id ", id);

	$scope.photo = photos[id];

	$scope.closeModalDialog = function () {
		$uibModalInstance.close();
	}

	$scope.cancelModalDialog = function () {
		$uibModalInstance.dismiss('cancel');
	}

	$scope.next = function () {
		$scope.photo = photos[$scope.photo.next];
		updateState($scope.photo.id);
	}

	$scope.previous = function () {
		$scope.photo = photos[$scope.photo.previous];
		updateState($scope.photo.id);
	}

	var updateState = function (id) {
		var options = {
			category_id: $stateParams.category_id,
			photo_id: id
		};
		$log.debug("updateState with options", options);
		$state.go('.', options, {
			notify: false
		});
		$stateParams.photo_id = id;
	}
})

;
