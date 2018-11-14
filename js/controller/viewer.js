angular.module('gallery')

  .service('galleryService', function (storageService) {
    var keys = {
      category: 'galleryCurrentCategory',
      photos: 'galleryPhotos'
    };

    this.getCurrentCategory = function () {
      return storageService.getFromSession(keys.category);
    };

    this.storeCurrentCategory = function (category) {
      category.lastStored = new Date().getTime();
      storageService.storeToSession(keys.category, category);
    };

    this.getPhotos = function () {
      return storageService.getFromSession(keys.photos);
    };

    this.storePhotos = function (photos) {
      storageService.storeToSession(keys.photos, photos);
    };
  })

  .controller('ViewerCtrl', function ($rootScope, $scope, $log, $uibModalInstance, $state, $stateParams, photos, id) {

    $scope.photo = photos[id];

    $scope.closeModalDialog = function () {
      $uibModalInstance.close();
    };

    $scope.cancelModalDialog = function () {
      $uibModalInstance.dismiss('cancel');
    };

    $scope.next = function () {
      var lastId = $scope.photo.id;
      $scope.photo = photos[$scope.photo.next];
      updateState(lastId, $scope.photo.id);
    };

    $scope.previous = function () {
      var lastId = $scope.photo.id;
      $scope.photo = photos[$scope.photo.previous];
      updateState(lastId, $scope.photo.id);
    };

    var updateState = function (lastId, newId) {
      var options = {
        category_id: $stateParams.category_id,
        photo_id: newId
      };
      $state.go('.', options, {
        notify: false
      });
      $stateParams.photo_id = newId;
      $rootScope.$emit('viewerChange', {
        lastId: lastId,
        newId: newId
      });
    };

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