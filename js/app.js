(function (angular) {
    'use strict';

    angular.module('ngApp', ['ngRoute', 'indexService', 'portfolioService', 'galleryService', 'linkService', 'contactService'])

    .controller('MainController', function ($scope, $route, $routeParams, $location) {
        $scope.$route = $route;
        $scope.$location = $location;
        $scope.$routeParams = $routeParams;
        $scope.currenDate = new Date();

        $scope.isActive = function (viewLocation) {
            var active = (viewLocation === $location.path());
            return active;
        };
    })

    .controller('BiographyController', function ($scope, $log) {
        //$log.info('BiographyController');
    })

    .controller('LegalNoticeController', function ($scope, $log) {
        //$log.info('LegalNoticeController');
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
    })

    .filter('objectToArray', function () {
        return function (input) {
            var out = [];
            for (var i in input) {
                out.push(input[i]);
            }
            return out;
        }
    });

})(window.angular);
