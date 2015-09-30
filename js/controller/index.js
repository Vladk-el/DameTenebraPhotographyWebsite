(function () {
    'use strict';
    
    angular.module('indexService', [])
    
    .controller('IndexController', function ($scope, $http, $log) {
       $log.info('IndexController');
        
        $scope.photos = [];

        $http.get('php/services/photo/photos.php?display=true')
            .success(function (data, status, headers, config) {
                var temp = [];
                for(var key in data) {
                    //$log.debug("key", key, "data[key]", data[key]);
                    if(data[key].photo_width > data[key].photo_height && data[key].active == "1") {
                        temp.push(data[key]);
                    }
                }
                var index = 0;
                for(var i = temp.length - 1; i > temp.length - 6; i--) {
                    $scope.photos.push(temp[i]);
                    temp[i]["index"] = index;
                    index++;
                }   
                $log.debug("photos ==>", $scope.photos);
            })
            .error(function (data, status, headers, config) {
                $log.error(status);
            });
    });
    
})(angular);