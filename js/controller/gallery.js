angular.module('gallery', ['ngAnimate', 'ngSanitize', 'ui.bootstrap'])

.config(function config($stateProvider) {
	$stateProvider
		.state('gallery', {
			parent: '',
			url: '/gallery/:category_id',
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

.controller('GalleryController', function ($scope, $http, $log, $uibModal, $document, category, photos) {
	//$log.info('GalleryController');

	$scope.category = category.data;
	$scope.photos = photos.data;
	/*$scope.photo_id = $routeParams.photo_id;*/

	var enrichPhotos = function (photos) {
		var imgs = [];
		for (var i in $scope.photos) {
			var img = {};
			img.url = "img/full/" + photos[i].photo_link;
			img.title = photos[i].photo_name;
			img.caption = photos[i].photo_description;
			imgs.push(img);
		}
		return imgs;
	}

	$scope.imgs = enrichPhotos(photos.data);

	$scope.show = function (photo, parentSelector) {
		/*$state.go('.', { photo_id: 1 }, { notify: false });*/
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

	$scope.next = function () {

	}

	$scope.previous = function () {

	}
})

;
