angular.module('indexService', ['ngAnimate', 'ui.bootstrap'])

.config(function config($stateProvider) {
	$stateProvider
		.state('home', {
			parent: '',
			url: '/home',
			views: {
				'main@': {
					controller: 'IndexController',
					templateUrl: 'partials/index.html'
				}
			},
			data: {
				pageTitle: 'Home'
			},
			resolve: {}
		});
})

.controller('IndexController', function ($scope, $http, $log) {
	//$log.info('IndexController');

	$scope.photos = [];

	// URGENT - make this in BACK 
	$http.get('php/services/photo/photos.php?display=true')
		.then(function (response) {
			var data = response.data;
			var temp = [];
			for (var key in data) {
				//$log.debug("key", key, "data[key]", data[key]);
				if (data[key].photo_width > data[key].photo_height && data[key].active == "1") {
					temp.push(data[key]);
				}
			}
			var index = 0;
			for (var i = temp.length - 1; i > temp.length - 6; i--) {
				$scope.photos.push(temp[i]);
				temp[i]["index"] = index;
				index++;
			}
			//$log.debug("photos ==>", $scope.photos);
		})
		.catch(function (error) {
			$log.error(error);
		});
});
