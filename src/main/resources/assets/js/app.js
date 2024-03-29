angular.module('slogrApp',
    [
        'ngRoute'
    ])

    .config(function($routeProvider) {
        $routeProvider
            .when('/', {
                controller: 'DemoControllerCustom',
                templateUrl: 'views/home.html'
            })
            .when('/login', {
                templateUrl: 'views/login.html'
            })
            .otherwise({
              redirectTo:'/'
            });
    })

    // TODO: Remove the following controller
    .controller('DemoControllerCustom', function($scope) {
        $scope.appName = 'SlogR';
    })

    .controller('TopBarController', function($scope, $location) {
        /*
            Set the location to the given path
        */
        $scope.setPath = function(path) {
            $location.path("");
            $location.path(path);
        };
    });