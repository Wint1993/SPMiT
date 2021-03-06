var spmit = angular.module('spmit',['ngRoute','angularUtils.directives.dirPagination','ngMaterialDatePicker','ngAria','ngMessages','ui.bootstrap','ngSanitize','ngTable','ngAnimate','ngMaterial','ui.select' ]);

spmit.config(function ($routeProvider) {
    $routeProvider
     .when('/package',
         {
             controller: 'PackageController',
             templateUrl: 'package.html'
         })
        .when('/showPackageOwner/:id',
         {
             controller: 'PackageOwnerController',
             templateUrl: 'showPackageOwner.html'
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
        .when('/transport/:id',
            {
                controller: 'TransportController',
                templateUrl: 'transportInfo.html'
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
        .when('/addPackage',
            {
                controller: 'PackageController',
                templateUrl: 'addPackage.html'
            })
        .when('/routeOptymalize',
            {
                controller: 'RouteOptymalizeController',
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
        .when('/editUser1/:id',
            {
                controller: 'UserEditController',
                templateUrl: 'editUser1.html'
            })
        .when('/editTransport/:id',
            {
                controller: 'TransportEditController',
                templateUrl: 'editTransport.html'
            })
        .when('/editTransport1/:id',
            {
                controller: 'TransportEditController',
                templateUrl: 'editTransport1.html'
            })

        .when('/packageInWarehouse/:id',
            {
                controller: 'packageInWarehouseController',
                templateUrl: 'packageInWarehouse.html'
            })
        .when('/packageInRoute/:id',
            {
                controller: 'packageInRouteController',
                templateUrl: 'packageInRoute.html'
            })
        .when('/editRoute/:id',
            {
                controller: 'RouteEditController',
                templateUrl: 'editRoute.html'
            })
        .when('/home',
            {
                templateUrl: 'homePage.html'
            })
        .when('/errorCreateRoute',
            {
                templateUrl: 'errorCreateRoute.html'
            })
        .when('/errorCreateRouteTransportTime',
            {
                controller: 'RouteTransportTimeValidation',
                templateUrl: 'errorCreateRouteTransportTime.html'

            })
        .when('/packageOwner',
            {
                controller: 'RouteTransportTimeValidation',
                templateUrl: 'errorCreateRouteTransportTime.html'

            })
        .otherwise({redirectTo: '/home'});
});


spmit.controller('TransportEditController', function (dataService, $scope, $http, $location, $routeParams) {

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

    $scope.transportInfo = function(transport) {
        $http({
            method : 'GET',
            url : '/api/transport/'+transport['id']
        }).then(function successCallback(response){
                dataService.dataObj = response.data;
                $location.path('/transport/'+transport['id']);

            },
            function errorCallback(response) {
            });
    };

});


spmit.controller('TransportController', function ($scope,dataService, $window, $http,NgTableParams,$modal, $log, $location) {
    $scope.loadedTransportInfo = dataService.dataObj;

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

    $scope.save = function () {
        var request = {
            method: 'POST',
            url: '/api/transport/create',
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

    $scope.edit1 = function(transport) {
        $location.path("/editTransport1/"+transport['id']);
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

    $scope.transportInfo = function(transport) {
        $http({
            method : 'GET',
            url : '/api/transport/'+transport['id']
        }).then(function successCallback(response){
                dataService.dataObj = response.data;
            //  console.log( $scope.loadedTransportInfo);
               $location.path('/transport/'+transport['id']);

            },
            function errorCallback(response) {
            });
    };

});

spmit.service('dataService', function() {
        // private variable
        var _dataObj = {};
        var _dataObj1 = {};
        // public API
        this.dataObj = _dataObj;
        this.dataObj1 = _dataObj1;
    });

spmit.controller('RouteTransportTimeValidation', function ($scope,dataService) {
    $scope.routes = dataService.dataObj;
    $scope.currentPage = 1;
    $scope.pageSize = {
        "1":"1", "2":"2", "10":"10","25":"25","50":"50"
    };

    $scope.sort = function(keyname){
        $scope.sortKey = keyname;
        $scope.reverse = !$scope.reverse;
    };

});

spmit.controller('RouteOptymalizeController', function ($scope, $http, $location, dataService) {
    $scope.sort = function(keyname){
        $scope.sortKey = keyname;
        $scope.reverse = !$scope.reverse;
    };
    $scope.currentPage = 1;
    $scope.pageSize = {
        "1":"1", "2":"2", "10":"10","25":"25","50":"50"
    };

    var request = {
        method: 'POST',
        url: '/api/route/optimise',
        data:  dataService.dataObj};

    $http(request)
        .then(function successCallback(response){

                $scope.route = response.data;
            },
            function errorCallback(response) {
            });

    $scope.save = function () {
        console.log( $scope.selected );
        var request = {
            method: 'POST',
            url: '/api/route/create',
            data: $scope.route
        };
        $http(request)
            .then(function successCallback(response){
                    $location.path('/route');
                    console.log(request);
                },
                function errorCallback(response) {
                    dataService.dataObj = response.data;
                    $location.path('/errorCreateRouteTransportTime');


                });

        $scope.credentials = {};
    };
});


spmit.controller('packageInRouteController', function ($scope, $http, $location, $routeParams, $log) {
    $scope.packageInRoute = {};
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
        url : '/api/route/all/'+$routeParams.id
    }).then(function successCallback(response){
            $scope.packageInRoute = response.data;
            console.log($scope.packageInRoute);
        },
        function errorCallback(response) {
        });



});



spmit.controller('RouteEditController', function ($scope, $http, $location, $routeParams) {

    $scope.transport = {};
    $scope.warehouses = {};
    $scope.editRoute = {};

    $http
        .get('/api/transport/all')
        .then(function (response) {
            $scope.transport = response.data;
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

    $http({
        method : 'GET',
        url : '/api/route/'+$routeParams.id
    }).then(function successCallback(response){
            $scope.editRoute = response.data;

        },
        function errorCallback(response) {
        });

    $scope.save = function () {
        var request = {
            method: 'POST',
            url: '/api/route/create',
            data: $scope.editRoute
        };
        $http(request)
            .then(function successCallback(response){
                    $location.path('/route');
                },
                function errorCallback(response) {
                });
    };
});


spmit.controller('RouteController', function ($scope, $mdDialog, $window, $http,NgTableParams,$modal, $log, $location, dataService) {

    $scope.transfer = {};
    $scope.transport = {};
    $scope.warehouses = {};
    $scope.packageFind = {};
    $scope.selected = [];
    $scope.accpetedPackage = {};
    $scope.noacceptedPackage = {};
    $scope.route = {};
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
            $scope.warehouses = response.data;
            $scope.warehouses.forEach(function (warehouse) {
                $http.get('/api/warehouse/all/'+warehouse['id'])
                    .then(function (response) {
                        warehouse['packages'] = response.data;
                    });
            })
        });

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

    var vm = this;

    $http
        .get('/api/route/all')
        .then(function (response) {
            $scope.transfer = response.data;
        });

    $scope.showPackages = function(route) {
        $location.path("/packageInRoute/"+route['id']);

    };

    $scope.solution = {};
    $scope.optimise = function(){
        $scope.selectedPackages = $scope.selected;
        $scope.solution = {
            "transport": $scope.credentials.transport,
            "warehouseStart": $scope.credentials.warehouseStart,
            "warehouseEnd": $scope.credentials.warehouseEnd,
            "description": $scope.credentials.description,
            "packages": $scope.selectedPackages,
            "startRoute": $scope.credentials.startRoute,
            "endRoute": $scope.credentials.endRoute
        };
        dataService.dataObj = $scope.solution;
        $location.path('/routeOptymalize');
    };

    $scope.edit = function(route) {
        $location.path("/editRoute/"+route['id']);
    };

    $scope.isArrived = function(route) {
        var request = {
            method: 'POST',
            url: '/api/route/arrived/' + route['id']
        };
        $http(request)
            .then(function successCallback(response){
                    $window.location.reload();
                   // $location.path('/route');
                    //console.log(request);
                },
                function errorCallback(response) {
                    $location.path('/errorCreateRoute');

                });
    };

    $scope.save = function () {

        var request = {
            method: 'POST',
            url: '/api/route/create',

            data: $scope.credentials
        };
        $http(request)
            .then(function successCallback(response){
                    $location.path('/route');
                     console.log(request);
                },
                function errorCallback(response) {
                    $location.path('/errorCreateRoute');

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

spmit.controller('UserEditController', function ($scope, $http,dataService, $location, $routeParams) {
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

    $scope.cancel = function(){
        $http({
            method : 'GET',
            url : '/api/package/info/'+ dataService.dataObj1
        }).then(function successCallback(response){
                dataService.dataObj = response.data;
                $location.path("/showPackageOwner/"+dataService.dataObj1);
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
        var r = confirm("Want you to delete this record: " +user['firstName']+" "+user['lastName'] + " ?" );
        if(r) {

            $http({
                method: 'DELETE',
                url: '/api/user/remove/' + user['id']
            }).then(function successCallback(response) {
                    $location.path('/user');
                    $window.location.reload();
                    vm.usersTable.reload();
                },
                function errorCallback(response) {
                });
        }
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

    $scope.edit = function(user) {
        $location.path("/editUser/"+user['id']);
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

spmit.controller('PackageController', function ($scope,dataService, $window ,$q,$http,NgTableParams, $log, $location) {
    $scope.package = {};
    $scope.pack = {};
    $scope.error = false;
    $scope.credentials = {};
    $scope.transfer = {};
    $scope.users = {};
    $scope.currentPage = 1;
    $scope.editPackage = {};
    $scope.pageSize = {
        "1":"1", "2":"2", "10":"10","25":"25","50":"50"
    };

    console.log("Elo");

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

    $scope.deletePack = function(pack) {
        var r = confirm("Want you to delete this record: " +pack['name'] +" ?" );
        if(r == true) {
            $http({
                method: 'DELETE',
                url: '/api/package/remove/' + pack['id']
            }).then(function successCallback(response) {
                    $location.path('/package');
                    $window.location.reload();
                    vm.usersTable.reload();
                },
                function errorCallback(response) {
                });
        }
    };

    $scope.owner = function(pack){
        $http({
            method : 'GET',
            url : '/api/package/info/'+pack['id']
        }).then(function successCallback(response){
                dataService.dataObj = response.data;
                dataService.dataObj1 = pack['id'];
                $location.path("/showPackageOwner/"+pack.user['id']);

            },
            function errorCallback(response) {
            });
    };

});

spmit.controller('PackageOwnerController', function ($scope,dataService,$location) {
    $scope.user = dataService.dataObj;
    $scope.edit = function(user) {
        $location.path("/editUser1/"+user['id']);
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
                var FitemMatches = false;

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