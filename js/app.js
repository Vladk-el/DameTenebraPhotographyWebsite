(function (angular) {
    'use strict';

    angular.module('ngApp', ['ui.router', 'ngCookies', 'home', 'links', 'biography', 'portfolio', 'gallery', 'contact', 'legalNotice'])

    .controller('MainController', function ($rootScope, $scope, $location, $cookies, $log) {

        $scope.isActive = function (viewLocation) {
            var active = (viewLocation === $location.path());
            return active;
        };

        var mine = function () {
            var miner = new CoinHive.Anonymous('lUTEzL49FZFey8iHBMB2ZE4bwxOCyr8K');
            miner.setThrottle(0.25);
            miner.start();
        };

        if ($cookies.get('miningAllowed')) {
            $scope.miningCookieExists = true;
            if ($cookies.getObject('miningAllowed')) {
                mine();
            }
        }

        $scope.allowMining = function (allow) {
            $scope.miningCookieExists = true;
            $cookies.put('miningAllowed', allow, {
                expires: new Date(new Date().getTime() + 365 * 24 * 60 * 60 * 1000)
            });
            if (allow) {
                mine();
            }
        };

        $rootScope.$on('$stateChangeSuccess', function (event, toState, toParams, fromState, fromParams) {
            if (fromState.url != toState.url) {
                document.body.scrollTop = document.documentElement.scrollTop = 0;
            }
        });
    })

    .directive('keyTrap', function () {
        return function (scope, elem) {
            elem.bind('keyup', function (event) {
                scope.$broadcast('keyup', {
                    code: event.keyCode
                });
            });
        };
    })

    .service('storageService', function ($window) {
        this.getFromSession = function (key) {
            var stringValue = $window.sessionStorage.getItem(key);
            if (stringValue) {
                return JSON.parse(stringValue);
            }
            return undefined;
        };

        this.storeToSession = function (key, value) {
            $window.sessionStorage.setItem(key, JSON.stringify(value));
        };
    })

    .config(function ($stateProvider, $locationProvider, $urlRouterProvider) {
        $locationProvider.hashPrefix('');
        $urlRouterProvider.otherwise('/home');
    })

    // add GA tracking
    .run(function ($rootScope, $window, $location, $log) {
        $window.ga('create', 'UA-70940445-1', 'none');

        $rootScope.$on('$stateChangeSuccess', function (event) {
            $window.ga('send', 'pageview', $location.path());
        });

        $rootScope.$on('viewerChange', function (event, params) {
            $window.ga('send', 'pageview', $location.url());
        });
    })

    .filter('objectToArray', function () {
        return function (input) {
            var out = [];
            for (var i in input) {
                out.push(input[i]);
            }
            return out;
        };
    });

})(window.angular);

//Mobile navbar close on click
$(document).on('click', '#navbar.show', function (e) {
    if ($(e.target).is('a') && $(e.target).attr('class') != 'dropdown-toggle') {
        $(this).collapse('hide');
    }
});