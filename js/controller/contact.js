angular.module('contact', ['toaster'])

.config(function config($stateProvider) {
	$stateProvider
		.state('contact', {
			parent: '',
			url: '/contact',
			views: {
				'main@': {
					controller: 'ContactController',
					templateUrl: 'partials/contact.tpl.html'
				}
			},
			data: {
				pageTitle: 'Contact'
			},
			resolve: {}
		});
})

.controller('ContactController', function ($scope, $log, $filter, $http, toaster, $state) {
	//$log.info('ContactController');

	$scope.submit = function () {
		var form = $scope.fields;

		form.date = $filter('date')(new Date(), 'yyyy-MM-dd HH:mm:ss');
		form.name = $scope.escapeQuotes(form.name);
		form.website = form.website != null ? $scope.escapeQuotes(form.website) : "";
		form.subject = $scope.escapeQuotes(form.subject);
		form.message = $scope.escapeQuotes(form.message);

		//$log.debug(form);

		$http.post('php/services/message/message_insert.php', form)
			.then(function (response) {
				//$log.debug(response);
				if (response.data.indexOf("success") != -1) {
					toaster.pop({
						type: 'success',
						title: 'Thanks !',
						body: 'Your message has been successfully relayed. See you soon ;)',
						onHideCallback: function () {
							$state.go('home');
						}
					});
				} else {
					$log.error(response.status + " ==> " + response);
					toaster.pop('error', 'Error', 'A problem has been detected, please try again later.');
				}
			})
			.catch(function (error) {
				$log.error(error);
				toaster.pop('error', 'Error', 'A problem has been detected, please try again later.');
			});
	}

	$scope.escapeQuotes = function (str) {
		return str.replace(/"/g, '\\"');
	}

});
