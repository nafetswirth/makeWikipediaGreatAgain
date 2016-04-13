(function() {
    'use strict';

    angular.module('wiki')
        .controller('ArticleDetailCtrl', [
        	'$scope',
            'article',
            'Resource',
            ArticleDetailCtrl
        ]);



    function ArticleDetailCtrl($scope, article, Resource) {
        var vm = this;
           
       	vm.appendToContent = appendToContent;
       	vm.allArticles = [];
        vm.article = article;
        vm.generateWikiLinkFor = generateWikiLinkFor;
        vm.isEdit = (Object.keys(article).length === 0) ? true : false;
        vm.noContent = noContent;
        vm.saveArticle = saveArticle;

        lazyLoadAllArticles();

        function lazyLoadAllArticles() {
        	Resource.articles.query().$promise.then(function(articles) {
        		vm.allArticles = articles;
        		console.log(vm.allArticles);
        	}).catch(function(err) {
        		console.error(err);
        	});
        }

        function generateWikiLinkFor(article) {
        	console.log('wikilinkgenerator')
        	var stringToAppend = '[' + article.name + '](#/' + article.id + ')';
        	appendToContent(stringToAppend);
        }

        function noContent() {
        	return vm.article.content === undefined || vm.article.content.length === 0; 
        }

        function appendToContent(element) {
        	if (!noContent()) {
        		element = '\n' + element;
        	}
        	vm.article.content += element;
        }

        function saveArticle(article) {
        	Resource.articles.save({articleId: article.id, name: article.name, content: article.content}, article).$promise.then(function() {
    			vm.isEdit = !vm.isEdit;
        	}).catch(function(err){
        		console.error(err);
        	});
        }




    }

}());