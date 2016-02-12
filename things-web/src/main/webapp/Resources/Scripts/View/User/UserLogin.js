define('View/User/UserLogin',function(require, exports, module){
	
	var PathUtils 	 = require( 'System/Utils/PathUtils' )
	var StringUtils  = require( 'System/Utils/StringUtils' )
	var CookieUtils = require( 'System/Utils/CookieUtils' )
	
	var cookieUserName = '__cookie__userName'
	
	exports.init = function () {
		//检查cookie
		var userName = CookieUtils.getCookie( cookieUserName )
		if(userName){
			$("input[name='username']").val(userName);
		}
		
        handleLogin( )
        $('#verificationCode_img').bind( 'click', bindVerificationCode )
        $('#verificationCode_span').bind( 'click', bindVerificationCode )
        var webRoot =  PathUtils.combine( PathUtils.getWebRoot(), 'Resources', 'ThirdpartyLib', 'metronic' )
       	$.backstretch([
       	    PathUtils.combine( webRoot, 'assets', 'img', 'bg', '1.jpg' ),
       	    PathUtils.combine( webRoot, 'assets', 'img', 'bg', '2.jpg' ),
       	    PathUtils.combine( webRoot, 'assets', 'img', 'bg', '3.jpg' ),
       	    PathUtils.combine( webRoot, 'assets', 'img', 'bg', '4.jpg' )
	        ], {
	          fade: 1000,
	          duration: 8000
	    })
    }
	
	var handleLogin = function() {
		$('.login-form').validate({
	            errorElement: 'span', //default input error message container
	            errorClass: 'help-block', // default input error message class
	            onfocusin: function(element) {
	            	//$(element).valid();
	            },
	            onfocusout: function(element) { 
	            	$(element).valid(); 
	            },
	            onclick: function(element) { 
	            	//$(element).valid();
	            },
	            onkeyup: function(element) {
	            	var name = $(element).attr('name')
	            	if(name === 'verificationCode' && $(element).val().length === 4){//验证码长度为4
	            		$(element).valid( )
	            	}
	            	//$(element).valid(); 
	            },
	            rules: {
	                username: {
	                    required: true
	                    /*remote : {
	                        type: "post",
	                        url:  PathUtils.combine( PathUtils.getWebRoot(), 'login', 'verifyUserExist' ),
	                        //dataType: "json",
	                        data: {
	                            username: function() {
	                                return $("input[name='username']").val();
	                            }
	                        }
	                    }*/
	                },
	                verificationCode : {
	                	required: true,
	                	rangelength:[ 3, 5 ],//长度必须是 4  3~5之间
	                	remote : {
	                        type: "post",
	                        url:  PathUtils.combine( PathUtils.getWebRoot(), 'login', 'verificationCode' ),
	                        data: {
	                        	verificationCode: function() {
	                                return $("input[name='verificationCode']").val();
	                            }
	                        }
	                    }
	                },
	                password: {
	                    required: true
	                },
	                remember: {
	                    required: false
	                }
	            },

	            messages: {
	                username : {
	                    required : '用户名不能为空!',
	                    remote   : '用户名不存在!'
	                },
	                password : {
	                    required : '密码不能为空!'
	                },
	                verificationCode : {
	                	required : '验证码不能为空!',
	                	rangelength : '验证码长度为4',
	                	remote : '验证码错误!'
	                }
	            },

	            invalidHandler: function (event, validator) { //display error alert on form submit   
	                $('.alert-danger', $('.login-form')).show();
	            },

	            highlight: function (element) { // hightlight error inputs
	                $(element).closest('.form-group').addClass('has-error'); // set error class to the control group
	            },

	            success: function (label) {
	            	 //验证码加上成功标签
	                if($(label).attr('for') === 'verificationCode'){
	                	if(label.closest('.form-group').children('span[for="verificationCode_ok"]').length === 0)
	                		label.closest('.form-group').append('<span for="verificationCode_ok" style = "color:green;">验证通过!</span>')
	                }
	                label.closest('.form-group').removeClass('has-error');
	                label.remove();
	            },

	            errorPlacement: function (error, element) {
	                error.insertAfter(element.closest('.input-icon'));
	                if(element.closest('.form-group').children('span[for="verificationCode_ok"]').length === 1){
	                	element.closest('.form-group').children('span[for="verificationCode_ok"]').remove( )
	                }
	            },

	            submitHandler : function (form) {
	            	var remember = $('input[name=remember]').attr('checked');
	            	if( remember === 'checked' ){
		            	//记录 cookie
		            	var userName = $("input[name='username']").val( )
		            	if(userName)
		            		CookieUtils.setCookie(cookieUserName,userName);
	            	}
	            	
	            	var pwd 	 =  $("input[name='password']").val()
	            	var vcode  =  $("input[name='verificationCode']").val( )
	            	
	            	var param = { }
	            	param.username 			 = userName
	            	param.password 			 = pwd
	            	param.verificationCode  = vcode
	            	
	            	 $.ajax({
	                     type : 'POST',
	                     url : PathUtils.combine( PathUtils.getWebRoot(), 'login', 'userLogin' ),
	                     data : param,
	                     dataType : 'json',
	                     success: function(data){
	                    	 var status = data.status
	                    	 var  message = data.message
	                    	 
	                    	 if(status === 'OK' && message === '0'){
	                    		 window.location.href= PathUtils.combine( PathUtils.getWebRoot(), 'home.htm' )
	                    	 }else{
	                    		 //登录失败
	                    		 $('.alert-danger', $('.login-form')).show();
	                    	 }
	                     }
	                 })
	            }
	        })
	}
	
	/**
	 * 绑定验证码事件
	 * */
	var  bindVerificationCode = function( ){
		$('#verificationCode_img').attr('src',PathUtils.combine( PathUtils.getWebRoot(), 'VerificationCode', 'img?_temp=' + (new Date()).valueOf() ))
	}
	
})