<html>
<head>
	<title>用户登录</title>
	<#include "/Base/UsingMetronic.ftl">
</head>
<body class="login">

	<div class="logo">
		<img src="${basePath}/Resources/ThirdpartyLib/metronic/assets/img/logo-big.png" alt=""/>
	</div>
	
	<div class="content">
		<form class="login-form" action="#" method="post">
			<h3 class="form-title">登录</h3>
			<div class="alert alert-danger display-hide">
				<button class="close" data-close="alert"></button>
				<span>请输入正确的用户名和密码</span>
			</div>
			<div class="form-group">
				<!--ie8, ie9 does not support html5 placeholder, so we just show field title for that-->
				<label class="control-label visible-ie8 visible-ie9">用户名</label>
				<div class="input-icon">
					<i class="fa fa-user"></i>
					<input class="form-control placeholder-no-fix" type="text" autocomplete="off" placeholder="用户名" name="username"/>
				</div>
			</div>
			<div class="form-group">
				<label class="control-label visible-ie8 visible-ie9">密码</label>
				<div class="input-icon">
					<i class="fa fa-lock"></i>
					<input class="form-control placeholder-no-fix" type="password" autocomplete="off" placeholder="密码" name="password"/>
				</div>
			</div>
			<div class="form-group">
			<label class="control-label visible-ie8 visible-ie9">验证码</label>
				<div class="input-icon">
					<input class="form-control placeholder-no-fix" style = "width:150px;display:inline;" type="text" autocomplete="off" placeholder="验证码" name="verificationCode"/>
					<img id = "verificationCode_img" src="${basePath}/VerificationCode/img" style = "height:34px;cursor:hand" />
					<span id = "verificationCode_span" style = "cursor:pointer;color:blue;">换一张</span>
				</div>
				
			</div>
			<div class="form-actions">
				<label class="checkbox">
				<input type="checkbox" name="remember"/> 记住我 </label>
				<button id = "btn_login" type="submit" class="btn blue pull-right">
					登 录 <i class="m-icon-swapright m-icon-white"></i>
				</button>
			</div>
		</form>
	</div>
	
	<div class="copyright">
		 2015 &copy; 智能家居系统
	</div>
	
	<script src="${basePath}/Resources/ThirdpartyLib/metronic/assets/scripts/app.js" type="text/javascript"></script>
	<script type = "text/javascript">
		jQuery(document).ready(function() {
		  App.init()
		  seajs.use('View/User/UserLogin',function(UserLogin){
		  		UserLogin.init( )
		  })
		})
	</script>
</body>
</html>