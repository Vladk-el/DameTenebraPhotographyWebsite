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
			reloadOnSearch: true,
			resolve: {
				stored: function ($stateParams, galleryService) {
					var category = galleryService.getCurrentCategory();
					return category && (category.category_id == $stateParams.category_id || category.category_id && !$stateParams.category_id) ? true : false;
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

.controller('GalleryController', function ($scope, $http, $log, $uibModal, $document, $state, $stateParams, galleryService, stored, category, photos) {
	$log.info('GalleryController');

	var init = function () {

		if (stored) {
			$log.debug("Already stored !");
			$scope.category = galleryService.getCurrentCategory();
			$scope.photos = galleryService.getPhotos();
		} else {
			$scope.category = category.data;
			galleryService.storeCurrentCategory($scope.category);
			$scope.photos = enrichPhotos(photos.data);
			galleryService.storePhotos($scope.photos);
		}

		if (!$stateParams.category_id) {
			if (stored) {
				$state.go('.', {
					category_id: $scope.category.category_id
				}, {
					notify: false,
					inherit: false
				});
			} else {
				$state.go('portfolio');
			}
		}

		if ($stateParams.photo_id) {
			$log.debug("$stateParams.photo_id seems like not null : ", $stateParams.photo_id);
			viewer($stateParams.photo_id);
		}
	}

	// use it for the viewer
	var enrichPhotos = function (photos) {
		var h = [];
		var v = [];
		photos.forEach(function (photo) {
			if (photo.active === '1') {
				var img = {};
				img.id = photo.photo_id;
				img.url = "img/full/" + photo.photo_link;
				img.title = photo.photo_name;
				img.caption = photo.photo_description;
				if (photo.photo_width > photo.photo_height) {
					img.h = true;
					h.push(img);
				} else {
					img.v = true;
					v.push(img);
				}
			}
		});

		var imgs = {};
		var first = null;
		var last = null;
		var cpt = 0;
		h.forEach(function (img) {
			img.position = cpt++;
			if (last && last.id) {
				img.previous = last.id;
				imgs[last.id].next = img.id;
			} else {
				first = img.id;
			}
			imgs[img.id] = img;
			last = img;
		});
		v.forEach(function (img) {
			img.position = cpt++
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
				notify: false
			});
		}
		var parentElem = parentSelector ?
			angular.element($document[0].querySelector('.gallery-container ' + parentSelector)) : undefined;
		viewer(id, parentElem);
	}

	init();

})

;
