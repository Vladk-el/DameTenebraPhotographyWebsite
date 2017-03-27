angular.module('home', ['ngAnimate', 'ngTouch', 'ui.bootstrap'])

.config(function config($stateProvider) {
	$stateProvider
		.state('home', {
			parent: '',
			url: '/home',
			views: {
				'main@': {
					controller: 'HomeController',
					templateUrl: 'partials/home.tpl.html'
				}
			},
			data: {
				pageTitle: 'Home'
			},
			resolve: {
				photos: function ($http) {
					return $http.get('php/services/photo/photos.php?home');
				}
			}
		});
})

.controller('HomeController', function ($scope, $http, $log, photos) {
	//$log.info('IndexController');

	$scope.photos = photos.data;

	$scope.previous = function () {
		$(".carousel").carousel('prev');
	}

	$scope.next = function () {
		$(".carousel").carousel('next');
	}

	var keys = {
		37: {
			code: 37,
			action: function () {
				$scope.previous();
			}
		},
		39: {
			code: 39,
			action: function () {
				$scope.next();
			}
		}
	};

	$scope.$on('keyup', function (msg, obj) {
		if (keys[obj.code]) {
			keys[obj.code].action();
		}
	});

});
