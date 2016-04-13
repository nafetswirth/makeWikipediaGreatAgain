(function() {
    'use strict';

    angular.module('wiki')
        .controller('ArticlesCtrl', [
            'articles',
            'Resource',
            ArticlesCtrl
        ]);



    function ArticlesCtrl(articles, Resource) {
        var vm = this;
           
        console.log(articles);
        vm.articles = articles;
        vm.deleteArticle = deleteArticle;

        function deleteArticle(article) {
            if(!confirm("Are you sure?")) {
                return false;
            }

            Resource.articles.delete({articleId: article.id}).$promise.then(function(deletedArticle) {
                var deletedArticleId = deletedArticle.id;
                vm.articles = vm.articles.filter(function(article) {
                    return article.id !== deletedArticleId;
                });
            });
        }
    }

}());