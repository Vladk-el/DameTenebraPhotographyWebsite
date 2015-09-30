(function () {
    'use strict';
    
    angular.module('linkService', [])
    
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
    });
    
})(angular);