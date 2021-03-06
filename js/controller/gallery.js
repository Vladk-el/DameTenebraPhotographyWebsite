angular.module('gallery', ['ngAnimate', 'ngTouch', 'ngSanitize', 'ui.bootstrap'])

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
                    return category && (category.category_id == $stateParams.category_id || category.category_id && !$stateParams.category_id) && (!category.lastStored || category.lastStored + (2 * 60 * 1000) > new Date().getTime()) ? true : false;
                },
                category: function ($stateParams, $http, stored) {
                    return stored ? null : $http.get('php/services/category/category.php?category=' + $stateParams.category_id);
                },
                photos: function ($stateParams, $http, stored) {
                    return stored ? null : $http.get('php/services/photo/photos_by_category.php?category=' + $stateParams.category_id);
                }
            }
        });
})

.controller('GalleryController', function ($rootScope, $scope, $http, $log, $uibModal, $document, $state, $stateParams, galleryService, stored, category, photos) {

    var init = function () {

        var toRevert = [1, 21];

        if (stored) {
            $scope.category = galleryService.getCurrentCategory();
            $scope.photos = enrichPhotos(galleryService.getPhotos());
        } else {
            $scope.category = category.data;
            galleryService.storeCurrentCategory($scope.category);
            $scope.photos = enrichPhotos(photos.data);
            galleryService.storePhotos(photos.data);
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
            viewer($stateParams.photo_id);
        }

        $scope.inverted = toRevert.includes(parseInt($scope.category.category_id));
    };

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

        var imgs = {},
            first = null,
            last = null,
            cpt = 0;

        var interDisplay = [],
            interCpt = 0,
            interArray = [];

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

            interArray.push(img);
            interCpt++;
            if (interCpt === 3) {
                interCpt = 0;
                interDisplay.push(angular.copy(interArray));
                interArray = [];
            }
        });
        if (interCpt > 0) {
            interDisplay.push(angular.copy(interArray));
            interArray = [];
        }
        $scope.hDisplay = angular.copy(interDisplay);
        interCpt = 0;
        interDisplay = [];

        v.forEach(function (img) {
            img.position = cpt++;
            if (last && last.id) {
                img.previous = last.id;
                imgs[last.id].next = img.id;
            } else {
                first = img.id;
            }
            imgs[img.id] = img;
            last = img;

            interArray.push(img);
            interCpt++;
            if (interCpt === 4) {
                interCpt = 0;
                interDisplay.push(angular.copy(interArray));
                interArray = [];
            }
        });
        if (interCpt > 0) {
            interDisplay.push(angular.copy(interArray));
        }
        $scope.vDisplay = angular.copy(interDisplay);

        imgs[last.id].next = first;
        imgs[first].previous = last.id;
        return imgs;
    };

    var viewer = function (id, parentElem) {
        var modal = $uibModal.open({
            animation: true,
            ariaLabelledBy: 'modal-title',
            ariaDescribedBy: 'modal-body',
            templateUrl: 'partials/modals/viewer.tpl.html',
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
        };

        return modal.result
            .then(function () {
                cleanState();
            }, function () {
                // error or cancel
                cleanState();
            });
    };

    $scope.show = function (id, parentSelector) {
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
    };

    // scroll top if visitor come from portfolio
    $rootScope.$on('$stateChangeSuccess', function (ev, to, toParams, from, fromParams) {
        if (from.name === 'portfolio') {
            $('body').scrollTop(0);
        }
    });

    init();

})

;