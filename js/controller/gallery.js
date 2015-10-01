(function () {
    'use strict';

    angular.module('galleryService', ['bootstrapLightbox'])

    .config(function (LightboxProvider) {
        // set a custom template
        LightboxProvider.templateUrl = 'includes/templates/lightbox.html';
    })

    .controller('GalleryController', function ($scope, $http, $routeParams, $log, Lightbox) {
        $log.info('GalleryController');
        $log.debug("$routeParams", $routeParams);

        $scope.category_id = $routeParams.category_id;
        $scope.category = {};
        $scope.photos = [];
        $scope.images = [];

        $http.get('php/services/category/category.php?category=' + $scope.category_id)
            .success(function (data, status, headers, config) {
                $scope.category = data;
                //$log.debug(data);
            })
            .error(function (data, status, headers, config) {
                $log.error(status);
            });

        $http.get('php/services/photo/photos_by_category.php?category=' + $scope.category_id)
            .success(function (data, status, headers, config) {
                $scope.photos = data;
                //$log.debug(data);
                for (var i in data) {
                    var img = {};
                    img.url = "img/full/" + data[i].photo_link;
                    img.title = data[i].photo_name;
                    img.caption = data[i].photo_description;
                    $scope.images.push(img);
                }
                $log.debug("$scope.images", $scope.images);
            })
            .error(function (data, status, headers, config) {
                $log.error(status);
            });

        $scope.openLightboxModal = function (index) {
            Lightbox.openModal($scope.images, index);
        };

    });

})(angular);
