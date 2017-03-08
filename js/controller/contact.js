(function () {
	'use strict';

	angular.module('contactService', ['toaster'])

	.controller('ContactController', function ($scope, $log, $filter, $http, toaster, $location) {
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
					if (response.indexOf("success") != -1) {
						toaster.pop({
							type: 'success',
							title: 'Thanks !',
							body: 'Your message has been successfully relayed. See you soon ;)',
							onHideCallback: function () {
								$location.path('/home');
							}
						});
					} else {
						$log.error(status + " ==> " + response);
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

})(angular);
