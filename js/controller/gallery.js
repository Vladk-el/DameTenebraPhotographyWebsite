(function () {
    'use strict';
    
    angular.module('galleryService', [])
    
    .controller('GalleryController', function ($scope, $http, $routeParams, $log) {
        $log.info('GalleryController');
        $log.debug($routeParams);

        $scope.category_id = $routeParams.category_id;
        $scope.category = {};
        $scope.photos = [];

        $http.get('php/services/category/category.php?category=' + $scope.category_id)
            .success(function (data, status, headers, config) {
                $scope.category = data;
                $log.debug(data);
            })
            .error(function (data, status, headers, config) {
                $log.error(status);
            });

        $http.get('php/services/photo/photos_by_category.php?category=' + $scope.category_id)
            .success(function (data, status, headers, config) {
                $scope.photos = data;
                $log.debug(data);
            })
            .error(function (data, status, headers, config) {
                $log.error(status);
            });

    });
    
})(angular);