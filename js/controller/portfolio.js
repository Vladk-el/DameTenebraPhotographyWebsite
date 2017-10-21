angular.module('portfolio', [])

.config(function config($stateProvider) {
    $stateProvider
        .state('portfolio', {
            parent: '',
            url: '/portfolio',
            views: {
                'main@': {
                    controller: 'PortfolioController',
                    templateUrl: 'partials/portfolio.tpl.html'
                }
            },
            data: {
                pageTitle: 'Portfolio'
            },
            resolve: {
                categories: function ($http) {
                    return $http.get('php/services/category/categories.php?display=true');
                }
            }
        });
})

.controller('PortfolioController', function ($scope, $log, categories) {

    var init = function () {

        $scope.hDisplay = [];
        $scope.vDisplay = [];
        var cpt_h = 0,
            array_h = [],
            cpt_v = 0,
            array_v = [];

        categories.data.forEach(function (c) {
            if (c.active == 1) {
                if (c.photo_height < c.photo_width) {
                    cpt_h++;
                    array_h.push(c);
                    if (cpt_h === 3) {
                        $scope.hDisplay.push(array_h);
                        cpt_h = 0;
                        array_h = [];
                    }
                } else {
                    cpt_v++;
                    array_v.push(c);
                    if (cpt_v === 4) {
                        $scope.vDisplay.push(array_v);
                        cpt_v = 0;
                        array_v = [];
                    }
                }
            }
        });

        if (cpt_h > 0) {
            $scope.hDisplay.push(array_h);
        }
        if (cpt_v > 0) {
            $scope.vDisplay.push(array_v);
        }

    };

    init();

});