angular.module('gallery', ['ngAnimate', 'ngSanitize', 'ui.bootstrap'])

.config(function config($stateProvider) {
	$stateProvider
		.state('gallery', {
			parent: '',
			url: '/gallery/:category_id?:photo_id',
			views: {
				'main@': {
					controller: 'GalleryController',
					templateUrl: 'partials/gallery.tpl.html'
				}
			},
			data: {
				pageTitle: 'Gallery'
			},
			resolve: {
				category: function ($stateParams, $http) {
					return $http.get('php/services/category/category.php?category=' + $stateParams.category_id);
				},
				photos: function ($stateParams, $http) {
					return $http.get('php/services/photo/photos_by_category.php?category=' + $stateParams.category_id)
				}
			}
		});
})

.controller('GalleryController', function ($scope, $http, $log, $uibModal, $document, $state, $stateParams, category, photos) {
	//$log.info('GalleryController');

	$scope.category = category.data;
	$scope.h = [];
	$scope.v = [];

	// use it for the viewer
	var enrichPhotos = function (photos) {
		photos.forEach(function (photo) {
			if (photo.active === '1') {
				var img = {};
				img.id = photo.photo_id;
				img.url = "img/full/" + photo.photo_link;
				img.title = photo.photo_name;
				img.caption = photo.photo_description;
				if (photo.photo_width > photo.photo_height) {
					img.h = true;
					$scope.h.push(img);
				} else {
					img.v = true;
					$scope.v.push(img);
				}
			}
		});

		var imgs = {};
		var first = null;
		var last = null;
		$scope.h.forEach(function (img) {
			if (last && last.id) {
				img.previous = last.id;
				imgs[last.id].next = img.id;
			} else {
				first = img.id;
			}
			imgs[img.id] = img;
			last = img;
		});
		$scope.v.forEach(function (img) {
			if (last && last.id) {
				img.previous = last.id;
				imgs[last.id].next = img.id;
			} else {
				first = img.id;
			}
			imgs[img.id] = img;
			last = img;
		});
		imgs[last.id].next = first;
		imgs[first].previous = last.id;
		return imgs;
	}

	$scope.show = function (id, parentSelector) {
		$log.debug("show photo", id);
		$state.go('.', {
			category_id: $scope.category.category_id,
			photo_id: id
		}, {
			notify: false
		});
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
				photos: function () {
					return $scope.photos;
				},
				id: function () {
					return id;
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

	$scope.photos = enrichPhotos(photos.data);

	/*if ($stateParams.photo_id) {
		$scope.show($scope.photos[$stateParams.photo_id]);
	}*/

})

.controller('ViewerModalCtrl', function ($scope, $log, $uibModalInstance, photos, id) {

	$log.debug("Enter ViewerModalCtrl with photo", photos[id]);

	$scope.photo = photos[id];

	$scope.closeModalDialog = function () {
		$uibModalInstance.close();
	}

	$scope.cancelModalDialog = function () {
		$uibModalInstance.dismiss('cancel');
	}

	$scope.next = function () {
		$scope.photo = photos[$scope.photo.next];
	}

	$scope.previous = function () {
		$scope.photo = photos[$scope.photo.previous];
	}
})

;
