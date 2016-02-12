define('System/Utils/PathUtils',function(require, exports, module){
	
	var StringUtils = require( 'System/Utils/StringUtils' )
	
	/**获取网站根路径
	 * @return 网站根路径
	 * */
	var getWebRoot = function( ){
		return window._baseDir
	}
	
	/**
	 * 合并路径
	 * */
	var combine = function(  ){
		if( !arguments || arguments.length <= 0 )
			return
			
			var pathArray = [ ]
			for( var i = 0; i < arguments.length; i++ ){
				if( pathArray.length > 0 && !StringUtils.endWith( pathArray[ pathArray.length - 1 ], '/' ) && !StringUtils.startWith( arguments[i],'/' ) )
					pathArray.push( '/' )
				
				pathArray.push( arguments[i] )
			}
		
		return pathArray.join( '' )
	}
	
	exports.combine		= combine
	exports.getWebRoot  = getWebRoot
	
})