<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<!-- jquery -->
<script src="https://code.jquery.com/jquery-3.1.1.min.js"></script>


<!DOCTYPE html>
<head>
<meta http-equiv="content-type" content="text/html; charset=euc-kr">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="">
<meta name="author" content="">

<title>HS Golf - 시작하세요</title>

<!-- Bootstrap core CSS -->
<link
	href="bootstrap/startbootstrap-heroic-features-gh-pages/vendor/bootstrap/css/bootstrap.min.css"
	rel="stylesheet">

<!-- Custom styles for this template -->
<link
	href="bootstrap/startbootstrap-heroic-features-gh-pages/css/heroic-features.css"
	rel="stylesheet">

</head>

<body>

	<!-- Navigation -->
	<script>
		function logout() {
			var response = confirm("로그아웃 하시겠습니까?")
			if (response) {
				//do yes task
				window.location.href = 'login';
			} else {
				//do no task
			}

		}
		function login() {
			window.location.href = 'login';
		}
	</script>
	<nav class="navbar navbar-expand-lg navbar-dark bg-dark fixed-top">
		<div class="container">

			<a class="navbar-brand" href="#">Start Bootstrap</a>
			<button class="navbar-toggler" type="button" data-toggle="collapse"
				data-target="#navbarResponsive" aria-controls="navbarResponsive"
				aria-expanded="false" aria-label="Toggle navigation">
				<span class="navbar-toggler-icon"></span>
			</button>
			<div class="collapse navbar-collapse" id="navbarResponsive">
				<ul class="navbar-nav ml-auto">
					<li class="nav-item active"><a class="nav-link" href="#">Home
							<span class="sr-only">(current)</span>
					</a></li>
					<li class="nav-item"><a class="nav-link" href="#">About</a></li>
					<li class="nav-item"><a class="nav-link" href="#">Services</a>
					</li>
					<li class="nav-item"><a class="nav-link" href="#">Contact</a>
					</li>
					<!-- 로그인 정보 -->
					<c:choose>
						<c:when test="${not empty sessionScope.playerInfo }">
							<li class="nav-item"><a class="nav-link"
								OnClick="javascript:logout();"> <c:out
										value="${sessionScope.playerInfo.locationName} - ${sessionScope.playerInfo.deviceId}" />호기
									연결중
							</a></li>
						</c:when>
						<c:otherwise>


							<li class="nav-item"><a class="nav-link"
								OnClick="javascript:login();"> 로그인 </a></li>


						</c:otherwise>
					</c:choose>
					<!-- ./ 로그인 정보 -->
				</ul>
			</div>
		</div>
	</nav>

	<!-- 비디오 정리 스크립트 (main menu에 한정) -->
	<script>
		function stopVideo() {
			player.stopVideo();
		}
	</script>
	<!-- Page Content -->
	<div class="container">

		<!-- Jumbotron Header -->
		<header class="jumbotron my-4">
			<h1 class="display-3">A Warm Welcome!</h1>
			<p class="lead">반갑습니다. HS Golf 입니다. 다양한 지형에서 골프퍼팅을 경험하세요. 국내외 유명
				골프장의 지형정보를 눈앞에!! 가족, 친구, 동료들과 퍼팅 대결은 어떠한가요? HS Golf와 함께 즐거운 시간 보내세요.</p>


			<button class="btn btn-primary btn-lg" data-toggle="modal"
				data-target="#myModalHorizontal">처음오셨나요?(영상)</button>
		</header>


		<!-- Page Features -->
		<div class="row text-center">



			<div class="col-lg-3 col-md-6 mb-4">
				<div class="card">
					<img class="card-img-top" src="http://placehold.it/500x325" alt="">
					<div class="card-body">
						<h4 class="card-title">레슨</h4>
						<p class="card-text">지형 레벨에 맞추어 연습을 할 수 있습니다. 차곡차곡 난이도를 올려가며
							경험을 쌓아보세요.</p>
					</div>
					<div class="card-footer">
						<a href="lesson" class="btn btn-primary">시작하기</a>
					</div>
				</div>
			</div>

			<div class="col-lg-3 col-md-6 mb-4">
				<div class="card">
					<img class="card-img-top" src="http://placehold.it/500x325" alt="">
					<div class="card-body">
						<h4 class="card-title">필드선택</h4>
						<p class="card-text">필드를 선택하고 연습을 시작합니다. 전체지형에 대한 정보가 주어지며 다양한
							필드를 직접 바꿔가며 퍼팅을 즐길 수 있습니다.</p>
					</div>
					<div class="card-footer">
						<a href="fieldselect" class="btn btn-primary">시작하기</a>
					</div>
				</div>
			</div>

			<div class="col-lg-3 col-md-6 mb-4">
				<div class="card">
					<img class="card-img-top" src="http://placehold.it/500x325" alt="">
					<div class="card-body">
						<h4 class="card-title">같이하기</h4>
						<p class="card-text">소중한 사람들과 함께 가상의 필드에서 퍼팅을 즐겨보세요. 주어지는 미션을
							교대로 수행하며 서로의 실력을 겨루어 볼 수 있습니다.</p>
					</div>
					<div class="card-footer">
						<a href="#" class="btn btn-primary">시작하기</a>
					</div>
				</div>
			</div>

			<div class="col-lg-3 col-md-6 mb-4">
				<div class="card">
					<img class="card-img-top" src="http://placehold.it/500x325" alt="">
					<div class="card-body">
						<h4 class="card-title">Card title</h4>
						<p class="card-text">Lorem ipsum dolor sit amet, consectetur
							adipisicing elit. Explicabo magni sapiente, tempore debitis
							beatae culpa natus architecto.</p>
					</div>
					<div class="card-footer">
						<a href="#" class="btn btn-primary">Find Out More!</a>
					</div>
				</div>
			</div>

		</div>
		<!-- /.row -->

	</div>
	<!-- /.container -->


	<!-- 영상팝업 시작 -->
	<!-- Modal -->
	<div class="modal fade" id="myModalHorizontal" tabindex="-1"
		role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">


		<div class="modal-dialog modal-lg">

			<center>
				<div class="modal-content">
					<!-- Modal Body -->
					<!-- 모달창에  Youtube 영상 준비. -->
					<div class="modal-body">
						<div id="ytplayer"></div>
						<script>
							// Load the IFrame Player API code asynchronously.
							var tag = document.createElement('script');
							tag.src = "https://www.youtube.com/player_api";
							var firstScriptTag = document
									.getElementsByTagName('script')[0];
							firstScriptTag.parentNode.insertBefore(tag,
									firstScriptTag);

							// Replace the 'ytplayer' element with an <iframe> and
							// YouTube player after the API code downloads.
							var player;
							function onYouTubePlayerAPIReady() {
								player = new YT.Player('ytplayer', {
									height : '315',
									width : '560',
									videoId : 'Mm48RLcri7I'
								});
							}
							function playVideo() {
								player.playVideo();
							}
							function stopVideo() {
								player.stopVideo();
							}
						</script>
					</div>


					<!-- Modal Footer -->
					<div class="modal-footer">
						<button type="button" class="btn btn-default"
							OnClick="javascript:stopVideo();" data-dismiss="modal">닫기</button>
					</div>
				</div>
		</div>
	</div>

	<!-- 모달창 나타나고 사라질때 동영상 시작/끝 스크립트-->
	<script>
		$(document).ready(function() {
			$('#myModalHorizontal').on('shown.bs.modal', function() {
				//alert('모달창 열림. 동영상재생시작');
				playVideo();
			})

			$("#myModalHorizontal").on('hide.bs.modal', function() {
				//alert('모달창 닫힘. 동영상정지');
				stopVideo();

			});
		});
	</script>

	<!-- 영상팝업 끝 -->

	<!-- Footer -->
	<footer class="py-5 bg-dark">
		<div class="container">
			<p class="m-0 text-center text-white">Copyright &copy; Your
				Website 2018</p>
		</div>
		<!-- /.container -->
	</footer>

	<!-- Bootstrap core JavaScript -->
	<script
		src="bootstrap/startbootstrap-heroic-features-gh-pages/vendor/jquery/jquery.min.js"></script>
	<script
		src="bootstrap/startbootstrap-heroic-features-gh-pages/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

</body>

</html>