(function() {

  'use strict';

  angular
    .module('wiki', [
      'ngAnimate',
      'ngCookies',
      'ngResource',
      'ngSanitize',
      'ui.router',
      'smart-table',
      'ngTouch',
      'btford.markdown'
    ])
    .config(['$urlRouterProvider', '$stateProvider', function ($urlRouterProvider, $stateProvider) {
      $urlRouterProvider.otherwise('/');

      $stateProvider
        .state('articles', {
          url: '/',
          templateUrl: '/views/articles.html',
          controller: 'ArticlesCtrl as vm',
          resolve: {
            articles: ['Resource', function(Resource) {
              return Resource.articles.query().$promise;
            }]
            //   articles: ['$q', function ($q) {
            //       var articles = [
            //           {
            //               'name': 'test',
            //               'content': 'test',
            //               'id':   1
            //           },
            //           {
            //               'name': 'wuat',
            //               'content': 'asd',
            //               'id':   2
            //           }
            //       ];
            //       return $q.when(articles);
            //   }]
          }
        })
        .state('newArticle', {
          url: '/new',
          templateUrl: '/views/articleDetail.html',
          controller: 'ArticleDetailCtrl as vm',
          resolve: {
            article: ['$q', function($q) {
              return $q.when({});
            }]
          }
        })
        .state('articleDetail', {
          url: '/:articleId',
          templateUrl: '/views/articleDetail.html',
          controller: 'ArticleDetailCtrl as vm',
          resolve: {
            article: ['$stateParams', 'Resource', function($stateParams, Resource) {
              var articleId = $stateParams.articleId;
              return Resource.articles.get({articleId: articleId}).$promise;
            }]
          }
        })
    }]);

})();
