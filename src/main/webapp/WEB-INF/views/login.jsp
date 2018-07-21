<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<!-- jquery -->
<script src="https://code.jquery.com/jquery-3.1.1.min.js"></script>


<!DOCTYPE html>
<head>
<meta http-equiv="content-type" content="text/html; charset=euc-kr">
<title>필드선택</title>
<meta name="" content="">


<!-- Bootstrap core CSS -->
<link href="bootstrap/basicstyle/css/bootstrap.min.css" rel="stylesheet">

<!-- Custom styles for this template  -->
<link href="css/login.css" rel="stylesheet">


</head>

<body oncontextmenu='return false'>
	<!--  HEADER  -->
	<script language="javascript">
		$(function() {
			//when loading..
			if ('${popupclosemsg}' != '') {
				confirm(' ${popupclosemsg} ');
			}

			window.opener.location.href = '${requestedURL}';
			window.close();

		});

		function applyFields() {
			//newuserform		
			var nm = document.forms["loginform"].elements["user-Id"].value;
			//do yes task
			document.forms["loginform"].method = "post"; //type
			document.forms["loginform"].action = "login" //url
			document.forms["loginform"].submit();
		}
	</script>



	<!-- 
	*****************************
	로그인 폼
	*****************************
	 -->

	<center>
		<img src="images/logo.jpg" class="img-rounded" alt="Cinque Terre">
	</center>
	<form class="form-signin" name="loginform">

		<h2 class="form-signin-heading">HS Golf</h2>




		<!-- ID -->
		<label for="exampleInputEmail1">연결할 장치 고유번호</label> <input type="text"
			id="user-Id" name='user-Id' class="form-control" placeholder="장치번호"
			required autofocus>
		<p>
			<!-- Password -->
			<label for="exampleInputPassword1">Password</label> <input
				type="password" id="login-pw" name='login-pw' autocomplete='off'
				class="form-control" placeholder="비밀번호" required>

			<!-- button -->
			<br>
			<button type="button" class="btn btn-lg btn-primary btn-block"
				OnClick="javascript:applyFields();">연결하기</button>

			<!-- <img alt="" src="resources/image/a.jpg"> 이미지 사용 샘플 -->
	</form>


	<!-- FOOTER -->
	<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
	<!-- Include all compiled plugins (below), or include individual files as needed -->
	<script
		src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>
</body>

</html>
