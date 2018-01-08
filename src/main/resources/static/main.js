var spmit = angular.module('spmit',['ngRoute','angularUtils.directives.dirPagination','ngMaterialDatePicker','ngAria','ngMessages','ui.bootstrap','ngSanitize','ngTable','ngAnimate','ngMaterial','ui.select' ]);

spmit.config(function ($routeProvider) {
    $routeProvider
     .when('/package',
         {
             controller: 'PackageController',
             templateUrl: 'package.html'
         })
        .when('/warehouse',
            {
                controller: 'WarehouseController',
                templateUrl: 'warehouse.html'
            })
        .when('/user',
        {
            controller: 'UserController',
            templateUrl: 'user.html'
        })
        .when('/route',
            {
                controller: 'RouteController',
                templateUrl: 'route.html'
            })
        .when('/transport',
            {
                controller: 'TransportController',
                templateUrl: 'transport.html'
            })
        .when('/addWarehouse',
            {
                controller: 'WarehouseController',
                templateUrl: 'addWarehouse.html'
            })
        .when('/addUser',
            {
                controller: 'UserController',
                templateUrl: 'addUser.html'
            })
        .when('/addRoute',
            {
                controller: 'RouteController',
                templateUrl: 'addRoute.html'
            })
        .when('/addTransport',
            {
                controller: 'TransportController',
                templateUrl: 'addTransport.html'
            })
        .when('/packageInWarehouse',
            {
                controller: 'WarehouseController',
                templateUrl: 'packageInWarehouse.html'
            })
        .when('/addPackage',
            {
                controller: 'PackageController',
                templateUrl: 'addPackage.html'
            })
        .when('/routeOptymalize',
            {
                controller: 'RouteController',
                templateUrl: 'routeOptymalize.html'
            })
        .when('/editPackage/:id',
            {
                controller: 'PackageEditController',
                templateUrl: 'editPackage.html'
            })
        .when('/editWarehouse/:id',
            {
                controller: 'WarehouseEditController',
                templateUrl: 'editWarehouse.html'
            })
        .when('/editUser/:id',
            {
                controller: 'UserEditController',
                templateUrl: 'editUser.html'
            })
        .when('/editTransport/:id',
            {
                controller: 'TransportEditController',
                templateUrl: 'editTransport.html'
            })
        .when('/packageInWarehouse/:id',
            {
                controller: 'packageInWarehouseController',
                templateUrl: 'packageInWarehouse.html'
            })
        .otherwise({redirectTo: '/package'});
});


spmit.controller('TransportEditController', function ($scope, $http, $location, $routeParams) {
    $scope.editTransport = {};
    $http({
        method : 'GET',
        url : '/api/transport/'+$routeParams.id
    }).then(function successCallback(response){
            $scope.editTransport = response.data;

        },
        function errorCallback(response) {
        });

    $scope.save = function () {
        var request = {
            method: 'POST',
            url: '/api/transport/create',
            data: $scope.editTransport
        };
        $http(request)
            .then(function successCallback(response){
                    $location.path('/transport');
                },
                function errorCallback(response) {
                });
    };
});


spmit.controller('TransportController', function ($scope, $window, $http,NgTableParams,$modal, $log, $location) {
    $scope.currentPage = 1;
    $scope.pageSize = {
        "1":"1", "2":"2", "10":"10","25":"25","50":"50"
    };
    $scope.sort = function(keyname){
        $scope.sortKey = keyname;
        $scope.reverse = !$scope.reverse;
    };

    $scope.transfer = {};
    $http
        .get('/api/transport/all')
        .then(function (response) {
            $scope.transfer = response.data;
        });

    var vm = this;
    vm.usersTable = new NgTableParams({
        page: 1,
        count: 5
    }, {
        total: $scope.transfer.length,

        getData:function(params) {
            var deferred = $q.defer();
            $scope.data = $scope.transfer.slice((params.page() - 1) * params.count(), params.page() * params.count());
            deferred.resolve($scope.data);
        }
    });

    $scope.save = function () {
        var request = {
            method: 'POST',
            url: '/api/transport/create',
            //   headers: {
            //     'Content-Type': 'application/x-www-form-urlencoded'
            // },
            data: $scope.credentials
        };
        $http(request)
            .then(function successCallback(response){
                    $location.path('/transport');
                },
                function errorCallback(response) {
                });

        $scope.credentials = {};
    };

    $scope.edit = function(transport) {
        $location.path("/editTransport/"+transport['id']);
    };
    $scope.deleteTransport = function(transport) {
        console.log(transport);

        $http({
            method : 'DELETE',
            url : '/api/transport/remove/'+transport['id']
        }).then(function successCallback(response){
                $location.path('/transport');
                $window.location.reload();
                vm.usersTable.reload();
            },
            function errorCallback(response) {
            });
    };

});


spmit.controller('RouteController', function ($scope, $window, $http,NgTableParams,$modal, $log, $location) {

    $scope.transfer = {};
    $scope.transport = {};
    $scope.warehouses = {};
    //$scope.packages = {};
    $scope.selected = [];
    $scope.accpetedPackage = {};
    $scope.noacceptedPackage = {};
    $scope.currentPage = 1;
    $scope.pageSize = {
        "1":"1", "2":"2", "10":"10","25":"25","50":"50"
    };
    $scope.selectedPackages = {};
    $scope.sort = function(keyname){
        $scope.sortKey = keyname;
        $scope.reverse = !$scope.reverse;
    };
    $http
        .get('/api/warehouse/all')
        .then(function (response) {
            $scope.transfer = response.data;
        });


    $http
        .get('/api/warehouse/all')
        .then(function (response) {
            $scope.warehouses = response.data;
            $scope.warehouses.forEach(function (warehouse) {
                $http.get('/api/warehouse/all/'+warehouse['id'])
                    .then(function (response) {
                        warehouse['packages'] = response.data;
                    });
            })
        });




    var e = document.getElementById("startId");

    $scope.selected = [];
    $scope.toggle = function (item, list) {
        var idx = list.indexOf(item);
        if (idx > -1) {
            list.splice(idx, 1);
        }
        else {
            list.push(item);
        }
    };
    $scope.exists = function (item, list) {
        return list.indexOf(item) > -1;
    };


    $http
        .get('/api/package/all')
        .then(function (response) {
            $scope.accpetedPackage = response.data;
        });

    var vm = this;
    vm.accepted = new NgTableParams({
        page: 1,
        count: 5
    }, {
        total: $scope.accpetedPackage.length,

        getData:function(params) {
            var deferred = $q.defer();
            $scope.data = $scope.accpetedPackage.slice((params.page() - 1) * params.count(), params.page() * params.count());
            deferred.resolve($scope.data);
        }
    });



    $http
        .get('/api/package/all')
        .then(function (response) {
            $scope.noacceptedPackage = response.data;
        });


    vm.noaccepted = new NgTableParams({
        page: 1,
        count: 5
    }, {
        total: $scope.noacceptedPackage.length,

        getData:function(params) {
            var deferred = $q.defer();
            $scope.data = $scope.noacceptedPackage.slice((params.page() - 1) * params.count(), params.page() * params.count());
            deferred.resolve($scope.data);
        }
    });


    $http
        .get('/api/route/all')
        .then(function (response) {
            $scope.transfer = response.data;
        });

    //var vm = this;
    vm.usersTable = new NgTableParams({
        page: 1,
        count: 5
    }, {
        total: $scope.transfer.length,

        getData:function(params) {
            var deferred = $q.defer();
            $scope.data = $scope.transfer.slice((params.page() - 1) * params.count(), params.page() * params.count());
            deferred.resolve($scope.data);
        }
    });

    $scope.solution = {};
    $scope.optimise = function(){
        $scope.selectedPackages = $scope.selected;
        $scope.solution = {
            "transport": $scope.credentials.transport,
            "packages": $scope.selectedPackages
        };
        var request = {
            method: 'POST',
            url: '/api/route/optimise',
            data:  $scope.solution };

           $http(request)
            .then(function successCallback(response){
                    $location.path('/routeOptymalize');
                },
                function errorCallback(response) {
                });
    };

    $scope.save = function () {
        console.log( $scope.selected );
        var request = {
            method: 'POST',
            url: '/api/route/create',
            //   headers: {
            //     'Content-Type': 'application/x-www-form-urlencoded'
            // },
            data: $scope.credentials
        };
        $http(request)
            .then(function successCallback(response){
                    $location.path('/route');
                     console.log(request);
                },
                function errorCallback(response) {
                });

        $scope.credentials = {};
    };


    $http
        .get('/api/transport/all')
        .then(function (response) {
            $scope.transport = response.data;
        });


    vm.someGroupFn = function (item){

        if (item.name[0] >= 'A' && item.name[0] <= 'M')
            return 'From A - M';

        if (item.name[0] >= 'N' && item.name[0] <= 'Z')
            return 'From N - Z';

    };
    vm.firstLetterGroupFn = function (item){
        return item.name[0];
    };

    vm.reverseOrderFilterFn = function(groups) {
        return groups.reverse();
    };
    vm.counter = 0;
    vm.onSelectCallback = function (item, model){
        vm.counter++;
        vm.eventResult = {item: item, model: model};
    };

    vm.tagTransform = function (newTag) {
        var item = {
            name: newTag,
           address: newTag.toLowerCase()+'@email.com',

        };
        return item;
    };

    $scope.deleteRoute = function(route) {
        console.log(route);

        $http({
            method : 'DELETE',
            url : '/api/route/remove/'+route['id'],
        }).then(function successCallback(response){
                $location.path('/route');
                // $window.location.reload();
                vm.usersTable.reload();
            },
            function errorCallback(response) {
            });
    };


});

spmit.controller('UserEditController', function ($scope, $http, $location, $routeParams) {
    $scope.editUser = {};
    $http({
        method : 'GET',
        url : '/api/user/'+$routeParams.id
    }).then(function successCallback(response){
            $scope.editUser = response.data;

        },
        function errorCallback(response) {
        });

    $scope.save = function () {
        var request = {
            method: 'POST',
            url: '/api/user/create',
            data: $scope.editUser
        };
        $http(request)
            .then(function successCallback(response){
                    $location.path('/user');
                },
                function errorCallback(response) {
                });
    };
});


spmit.controller('UserController', function ($scope,$q, $window, $http,NgTableParams,$modal, $log, $location) {
    $scope.transfer = {};
    $scope.error = false;
    $scope.currentPage = 1;
    $scope.pageSize = {
        "1":"1", "2":"2", "10":"10","25":"25","50":"50"
    };
    $scope.sort = function(keyname){
        $scope.sortKey = keyname;
        $scope.reverse = !$scope.reverse;
    };


    $http
        .get('/api/user/all')
        .then(function (response) {
            $scope.transfer = response.data;
        });

    var vm = this;
    vm.usersTable = new NgTableParams({
        page: 1,
        count: 5
    }, {
        total: $scope.transfer.length,

        getData:function(params) {
            var deferred = $q.defer();
            $scope.data = $scope.transfer.slice((params.page() - 1) * params.count(), params.page() * params.count());
            deferred.resolve($scope.data);
        }
    });

    $scope.save = function () {
        var request = {
            method: 'POST',
            url: '/api/user/create',
            data: $scope.credentials
        };
        $http(request)
            .then(function successCallback(response){
                    $location.path('/user');
                },
                function errorCallback(response) {
                });

        $scope.credentials = {};
    };

    $scope.deleteUser = function(user) {
        console.log(user);

        $http({
            method : 'DELETE',
            url : '/api/user/remove/'+user['id']
        }).then(function successCallback(response){
                $location.path('/user');
                $window.location.reload();
                vm.usersTable.reload();
            },
            function errorCallback(response) {
            });
    };

    $scope.edit = function(user) {
        $location.path("/editUser/"+user['id']);
    };
});


spmit.controller('PackageEditController', function ($scope, $http, $location, $routeParams) {

    $scope.transfer = {};
    $scope.users = {};
    $http
        .get('/api/warehouse/all')
        .then(function (response) {
            $scope.transfer = response.data;
        });

    $http
        .get('/api/user/all')
        .then(function (response) {
            $scope.users = response.data;
        });

    $scope.editPackage = {};
    console.log($routeParams.id);
    $http({
        method : 'GET',
        url : '/api/package/'+$routeParams.id
    }).then(function successCallback(response){

            $scope.editPackage = response.data;
        },
        function errorCallback(response) {
        });

    $scope.save = function () {
        var request = {
            method: 'POST',
            url: '/api/package/create',
            data: $scope.editPackage
        };
        $http(request)
            .then(function successCallback(response){
                    $location.path('/package');
                },
                function errorCallback(response) {
                });

        $scope.credentials = {};
    };

    var vm = this;
    vm.someGroupFn = function (item){

        if (item.name[0] >= 'A' && item.name[0] <= 'M')
            return 'From A - M';

        if (item.name[0] >= 'N' && item.name[0] <= 'Z')
            return 'From N - Z';

    };
    vm.firstLetterGroupFn = function (item){
        return item.name[0];
    };

    vm.reverseOrderFilterFn = function(groups) {
        return groups.reverse();
    };
    vm.counter = 0;
    vm.onSelectCallback = function (item, model){
        vm.counter++;
        vm.eventResult = {item: item, model: model};
    };

});

spmit.controller('PackageController', function ($scope, $window ,$q,$http,NgTableParams, $log, $location) {
    $scope.package = {};
    $scope.error = false;
    $scope.credentials = {};
    $scope.transfer = {};
    $scope.users = {};
    $scope.currentPage = 1;
    $scope.editPackage = {};
    $scope.pageSize = {
        "1":"1", "2":"2", "10":"10","25":"25","50":"50"
    };

    $scope.sort = function(keyname){
        $scope.sortKey = keyname;
        $scope.reverse = !$scope.reverse;
    };
    $scope.sort1 = function(keyname){
        $scope.sortKey = keyname;
        $scope.reverse = !$scope.reverse;
    };

    $http
        .get('/api/user/all')
        .then(function (response) {
            $scope.users = response.data;
        });

    $http
        .get('/api/warehouse/all')
        .then(function (response) {
            $scope.transfer = response.data;
        });

    var vm = this;
    vm.someGroupFn = function (item){

        if (item.name[0] >= 'A' && item.name[0] <= 'M')
            return 'From A - M';

        if (item.name[0] >= 'N' && item.name[0] <= 'Z')
            return 'From N - Z';

    };
    vm.firstLetterGroupFn = function (item){
        return item.name[0];
    };

    vm.reverseOrderFilterFn = function(groups) {
        return groups.reverse();
    };
    vm.counter = 0;
    vm.onSelectCallback = function (item, model){
        vm.counter++;
        vm.eventResult = {item: item, model: model};
    };

    $http
        .get('/api/package/all')
        .then(function (response) {
            $scope.package = response.data;
        });

    $scope.save = function () {
        var request = {
            method: 'POST',
            url: '/api/package/create',
            data: $scope.credentials
        };
        $http(request)
            .then(function successCallback(response){
                    $location.path('/package');
                },
                function errorCallback(response) {
                });

        $scope.credentials = {};
    };

    $scope.edit = function(package) {
        $location.path("/editPackage/"+package['id']);
    };

    vm.usersTable = new NgTableParams({
        page: 1,
        count: 2,
        sorting: { name: "asc" }
    }, {
        total: $scope.package.length,

        getData:function($defer,params) {
            $scope.nowy = $scope.package.slice((params.page() - 1) * params.count(), params.page() * params.count());
         }
    });

    $scope.deletePack = function(pack) {
        console.log(pack);

        $http({
            method : 'DELETE',
            url : '/api/package/remove/'+pack['id']
        }).then(function successCallback(response){
                $location.path('/package');
                $window.location.reload();
                vm.usersTable.reload();
            },
            function errorCallback(response) {
            });
    };
});

spmit.controller('WarehouseEditController', function ($scope, $http, $location, $routeParams) {
    $scope.editWarehouse = {};
    $http({
        method : 'GET',
        url : '/api/warehouse/'+$routeParams.id
    }).then(function successCallback(response){
            $scope.editWarehouse = response.data;

        },
        function errorCallback(response) {
        });

    $scope.save = function () {
        var request = {
            method: 'POST',
            url: '/api/warehouse/create',
            data: $scope.editWarehouse
        };
        $http(request)
            .then(function successCallback(response){
                    $location.path('/warehouse');
                },
                function errorCallback(response) {
                });
    };
});

spmit.controller('packageInWarehouseController', function ($scope, $http, $location, $routeParams, $log) {
    $scope.packageInWarehouse = {};
    $scope.currentPage = 1;
    $scope.pageSize = {
        "1":"1", "2":"2", "10":"10","25":"25","50":"50"
    };
    $scope.sort = function(keyname){
        $scope.sortKey = keyname;
        $scope.reverse = !$scope.reverse;
    };

    $http({
        method : 'GET',
        url : '/api/warehouse/all/'+$routeParams.id
    }).then(function successCallback(response){
            $scope.packageInWarehouse = response.data;
            console.log($scope.packageInWarehouse);
        },
        function errorCallback(response) {
        });



});

spmit.controller('WarehouseController', function ($scope,$q, $window, $http, NgTableParams, $modal, $log, $location,$mdDialog) {
    $scope.transfer = {};
    $scope.error = false;
    $scope.packageInWarehouse = {};
    $scope.currentPage = 1;
    $scope.pageSize = {
        "1":"1", "2":"2", "10":"10","25":"25","50":"50"
    };
    $scope.sort = function(keyname){
        $scope.sortKey = keyname;
        $scope.reverse = !$scope.reverse;
    };

    $http
        .get('/api/warehouse/all')
        .then(function (response) {
            $scope.transfer = response.data;
        });

    var vm = this;
    vm.usersTable = new NgTableParams({
        page: 1,
        count: 5
    }, {
        total: $scope.transfer.length,

        getData:function(params) {
            $scope.data = $scope.transfer.slice((params.page() - 1) * params.count(), params.page() * params.count());
        }
    });

    $scope.save = function () {
        var request = {
            method: 'POST',
            url: '/api/warehouse/create',
            data: $scope.credentials
        };
        $http(request)
            .then(function successCallback(response){
                    $location.path('/warehouse');
                },
                function errorCallback(response) {
                });

        $scope.credentials = {};
    };

    $scope.deleteWarehouse = function(warehouse) {
        console.log(warehouse);

        $http({
            method : 'DELETE',
            url : '/api/warehouse/remove/'+warehouse['id']
        }).then(function successCallback(response){
                $location.path('/packageInWarehouse');
                // $window.location.reload();
                vm.usersTable.reload();
            },
            function errorCallback(response) {
            });
    };

    $scope.edit = function(transfer) {
        $location.path("/editWarehouse/"+transfer['id']);
    };

    $scope.showPackages = function(warehouse) {
        $location.path("/packageInWarehouse/"+warehouse['id']);

    };


});

spmit.filter('propsFilter', function() {
    return function(items, props) {
        var out = [];

        if (angular.isArray(items)) {
            var keys = Object.keys(props);

            items.forEach(function(item) {
                var itemMatches = false;

                for (var i = 0; i < keys.length; i++) {
                    var prop = keys[i];
                    var text = props[prop].toLowerCase();
                    var cos = item[prop].toString().toLowerCase().indexOf(text);
                    if (cos !== -1) {
                        itemMatches = true;
                        break;
                    }
                }
                if (itemMatches) {
                    out.push(item);
                }
            });
        } else {
            out = items;
        }

        return out;
    };
});