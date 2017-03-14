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
			reloadOnSearch: false,
			resolve: {
				stored: function (galleryService) {
					return galleryService.getCurrentCategory() ? true : false;
				},
				category: function ($stateParams, $http, stored) {
					return stored ? null : $http.get('php/services/category/category.php?category=' + $stateParams.category_id);
				},
				photos: function ($stateParams, $http, stored) {
					return stored ? null : $http.get('php/services/photo/photos_by_category.php?category=' + $stateParams.category_id)
				}
			}
		});
})

.controller('GalleryController', function ($scope, $http, $log, $uibModal, $document, $state, $stateParams, galleryService, category, photos) {
	$log.info('GalleryController');

	var init = function () {
		var stored = galleryService.getCurrentCategory();
		$scope.h = [];
		$scope.v = [];

		if (stored) {
			$log.debug("Already stored !");
			$scope.category = stored;
			$scope.photos = enrichPhotos(galleryService.getPhotos());
		} else {
			$scope.category = category.data;
			galleryService.storeCurrentCategory($scope.category);
			$scope.photos = enrichPhotos(photos.data);
			galleryService.storePhotos(photos.data);
		}

		if ($stateParams.photo_id) {
			$log.debug("$stateParams.photo_id seems like not null : ", $stateParams.photo_id);
			//$scope.show($scope.photos[$stateParams.photo_id]);
		}
	}

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

	var viewer = function (id, parentElem) {
		var modal = $uibModal.open({
			animation: true,
			ariaLabelledBy: 'modal-title',
			ariaDescribedBy: 'modal-body',
			templateUrl: 'partials/templates/modals/viewer.tpl.html',
			size: 'lg',
			controller: 'ViewerCtrl',
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

		var cleanState = function () {
			$state.go('.', {
				category_id: $scope.category.category_id
			}, {
				notify: true,
				inherit: false
			});
		}

		return modal.result
			.then(function () {
				cleanState();
			}, function () {
				// error or cancel
				cleanState();
				$log.info('modal-component dismissed at: ' + new Date());
			});
	}

	$scope.show = function (id, parentSelector) {
		$log.debug("show photo", id);
		if (!$stateParams.photo_id) {
			$state.go('.', {
				category_id: $scope.category.category_id,
				photo_id: id
			}, {
				notify: true
			});
		}
		var parentElem = parentSelector ?
			angular.element($document[0].querySelector('.gallery-container ' + parentSelector)) : undefined;
		viewer(id, parentElem);
	}

	init();

})

;
