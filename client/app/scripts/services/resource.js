/*
* @Author: rudi
* @Date:   2016-04-12 14:12:58
* @Last Modified by:   rudi
* @Last Modified time: 2016-04-13 14:47:23
*/
(function(){
    "use strict";
    
    angular
    	.module('wiki')
    	.factory('Resource', [
    		'$resource',
    		Resource
		]);

	function Resource($resource) {
		var URL = 'http://localhost:4567/';

		return {
			articles: $resource(
				URL + 'articles/:articleId',
				{articleId: '@articleId'},
				{}
			)
		};
	}

}());