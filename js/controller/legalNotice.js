angular.module('legalNotice', [])

.config(function config($stateProvider) {
	$stateProvider
		.state('legalNotice', {
			parent: '',
			url: '/legalNotice',
			views: {
				'main@': {
					controller: 'LegalNoticeController',
					templateUrl: 'partials/legalNotice.tpl.html'
				}
			},
			data: {
				pageTitle: 'Legal Notice'
			},
			resolve: {}
		});
})

.controller('LegalNoticeController', function ($scope, $http, $log) {
	//$log.info('IndexController');
});
