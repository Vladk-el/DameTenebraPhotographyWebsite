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

    .controller('IndexController', function ($scope) {
        console.log('IndexController');
    })

    .controller('PortfolioController', function ($scope, $http, $window, $location) {
        console.log('PortfolioController');

        $scope.screenSize = $window.innerHeight;
        $scope.categories = [];

        $http.get('php/services/categories.php')
            .success(function (data, status, headers, config) {
                $scope.categories = data;
                console.log(data);
            })
            .error(function (data, status, headers, config) {
                console.log(status);
                console.log("Error occured");
            });

        $scope.go = function (path) {
            $location.path(path);
        }

        // @Deprecated ==> make request from server, not client
        $scope.getLastPhotoForCategory = function (category_id) {
            $http({
                    url: 'php/services/last_photo_by_category.php?category=' + category_id,
                    header: {
                        'Content-Type': 'application/json; charset=UTF-8'
                    }
                })
                .then(function (response) {
                    return response['photo_link'];
                });
        }
    })

    .controller('GalleryController', function ($scope, $http, $routeParams) {
        console.log('GalleryController');
        console.log($routeParams);

        $scope.category_id = $routeParams.category_id;
        $scope.category = {};
        $scope.photos = [];

        $http.get('php/services/category.php?category=' + $scope.category_id)
            .success(function (data, status, headers, config) {
                $scope.category = data;
                console.log(data);
            })
            .error(function (data, status, headers, config) {
                console.log(status);
                console.log("Error occured");
            });

        $http.get('php/services/photos.php?category=' + $scope.category_id)
            .success(function (data, status, headers, config) {
                $scope.photos = data;
                console.log(data);
            })
            .error(function (data, status, headers, config) {
                console.log(status);
                console.log("Error occured");
            });

    })

    .controller('ContactController', function ($scope) {
        console.log('ContactController');
    })

    .directive('fancybox', function () {
        return {
            link: function (scope, element, attrs) {
                $('.fancybox').fancybox();
            }
        }
    })
    
    .controller('LinksController', function ($scope, $http) {
        console.log('LinksController');
        
        $scope.owners = [];
        
        $http.get('php/services/owners.php')
            .success(function (data, status, headers, config) {
                $scope.owners = data;
                console.log(data);
            })
            .error(function (data, status, headers, config) {
                console.log(status);
                console.log("Error occured");
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
    
    .controller('BiographyController', function ($scope) {
        console.log('BiographyController');
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
            .otherwise({
                redirectTo: '/'
            });

        //$locationProvider.html5Mode(true);
    });

})(window.angular);
