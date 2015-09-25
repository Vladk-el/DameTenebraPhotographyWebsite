(function (angular) {
    'use strict';

    angular.module('ngApp', ['ngRoute'])

    .controller('MainController', function ($scope, $route, $routeParams, $location) {
        $scope.$route = $route;
        $scope.$location = $location;
        $scope.$routeParams = $routeParams;

        $scope.isActive = function (viewLocation) {
            var active = (viewLocation === $location.path());
            return active;
        };
    })

    .controller('IndexController', function ($scope, $http, $log) {
       $log.info('IndexController');
        
        $scope.photos = [];

        $http.get('php/services/photo/photos.php')
            .success(function (data, status, headers, config) {
                var temp = [];
                for(var key in data) {
                    //$log.debug("key", key, "data[key]", data[key]);
                    if(data[key].photo_width > data[key].photo_height) {
                        temp.push(data[key]);
                    }
                }
                var index = 0;
                for(var i = temp.length - 1; i > temp.length - 6; i--) {
                    $scope.photos.push(temp[i]);
                    temp[i]["index"] = index;
                    index++;
                }   
                $log.debug("photos ==>", $scope.photos);
            })
            .error(function (data, status, headers, config) {
                $log.error(status);
            });
    })

    .controller('PortfolioController', function ($scope, $http, $location, $log) {
        $log.info('PortfolioController');

        $scope.categories = [];

        $http.get('php/services/category/categories.php?display=true')
            .success(function (data, status, headers, config) {
                $scope.categories = data;
                $log.debug(data);
            })
            .error(function (data, status, headers, config) {
                $log.error(status);
            });

        $scope.go = function (path) {
            $location.path(path);
        }
        
    })

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

    })

    .controller('ContactController', function ($scope, $log) {
        $log.info('ContactController');
    })

    .directive('fancybox', function () {
        return {
            link: function (scope, element, attrs) {
                $('.fancybox').fancybox();
            }
        }
    })
    
    .controller('LinksController', function ($scope, $http, $log) {
        $log.info('LinksController');
        
        $scope.owners = [];
        
        $http.get('php/services/owner/owners.php?display=true')
            .success(function (data, status, headers, config) {
                $scope.owners = data;
                $log.debug(data);
            })
            .error(function (data, status, headers, config) {
                $log.error(status);
            });
        
        function resizeDivLinks() {
            var maxSize = null;
            $(".divlinks").each(function () {
                if(maxSize == null || $(this).height() > maxSize) {
                    maxSize = $(this).height();
                }
            });
            $(".divlinks").each(function () {
                $(this).height(maxSize);
            });
            
        }
        
        resizeDivLinks();
    })
    
    .controller('BiographyController', function ($scope, $log) {
        $log.info('BiographyController');
    })
    
    .controller('LegalNoticeController', function ($scope, $log) {
        $log.info('LegalNoticeController');
    })

    .config(function ($routeProvider, $locationProvider) {
        $routeProvider
            .when('/', {
                templateUrl: 'includes/index.html',
                controller: 'IndexController'
            })
            .when('/portfolio', {
                templateUrl: 'includes/portfolio.html',
                controller: 'PortfolioController'
            })
            .when('/gallery/:category_id', {
                templateUrl: 'includes/gallery.html',
                controller: 'GalleryController'
            })
            .when('/contact', {
                templateUrl: 'includes/contact.html',
                controller: 'ContactController'
            })
            .when('/links', {
                templateUrl: 'includes/links.html',
                controller: 'LinksController'
            })
            .when('/biography', {
                templateUrl: 'includes/biography.html',
                controller: 'BiographyController'
            })
            .when('/legal_notice', {
                templateUrl: 'includes/legal_notice.html',
                controller: 'LegalNoticeController'
            })
            .otherwise({
                redirectTo: '/'
            });

        //$locationProvider.html5Mode(true);
    });

})(window.angular);
