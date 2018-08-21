<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<!-- jquery -->
<script src="https://code.jquery.com/jquery-3.1.1.min.js"></script>


<!DOCTYPE html>
<head>
<meta http-equiv="content-type" content="text/html; charset=euc-kr">
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="">
<meta name="author" content="">

<title>Full Slider - Start Bootstrap Template</title>

<!-- Bootstrap core CSS -->
<link
	href="bootstrap/startbootstrap-full-slider-gh-pages/vendor/bootstrap/css/bootstrap.min.css"
	rel="stylesheet">

<!-- Custom styles for this template -->
<link
	href="bootstrap/startbootstrap-full-slider-gh-pages/css/full-slider.css"
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


	<!-- Navigation -->
	<!-- 
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
					
				</ul>
			</div>
		</div>
	</nav>
 -->
	<header>
		<div id="carouselExampleIndicators" class="carousel slide"
			data-ride="carousel">
			<ol class="carousel-indicators">
				<li data-target="#carouselExampleIndicators" data-slide-to="0"
					class="active"></li>
				<li data-target="#carouselExampleIndicators" data-slide-to="1"></li>
				<li data-target="#carouselExampleIndicators" data-slide-to="2"></li>
			</ol>
			<div class="carousel-inner" role="listbox">

				<!-- Slide One - Set the background image for this slide in the line below -->
				<div class="carousel-item active"
					style="background-image: url('mapinfo/${sessionScope.playerInfo.selectedMapId}/1900title.jpg')">

				</div>

				<!-- Slide Two - Set the background image for this slide in the line below -->
				<div class="carousel-item"
					style="background-image: url('mapinfo/${sessionScope.playerInfo.selectedMapId}/1900outlinemap.jpg')"></div>

				<!-- Slide Three - Set the background image for this slide in the line below -->
				<div class="carousel-item"
					style="background-image: url('http://placehold.it/1900x1080')">

				</div>

			</div>
			<a class="carousel-control-prev" href="#carouselExampleIndicators"
				role="button" data-slide="prev"> <span
				class="carousel-control-prev-icon" aria-hidden="true"></span> <span
				class="sr-only">Previous</span>
			</a> <a class="carousel-control-next" href="#carouselExampleIndicators"
				role="button" data-slide="next"> <span
				class="carousel-control-next-icon" aria-hidden="true"></span> <span
				class="sr-only">Next</span>
			</a>
		</div>
	</header>

	<!-- Page Content -->
	<center>
		<button type="button" class="btn btn-info btn-lg"
			OnClick="javascript:goBack();">뒤로가기</button>
		<button type="button" class="btn btn-info btn-lg" data-toggle="modal"
			data-target="#myModal" OnClick="javascript:startfieldchange()">적용하기</button>

	</center>

	<section class="py-5">
		<div class="container">
			<h1>Full Slider by Start Bootstrap</h1>
			<p>
				The background images for the slider are set directly in the HTML
				using inline CSS. The rest of the styles for this template are
				contained within the
				<code>full-slider.css</code>
				file.
			</p>
		</div>
	</section>



	<!-- 로딩팝업 모달창 -->
	<script>
		$(function() {
			//when loading..
			show_loading_popup();

		});

		function show_loading_popup() {
			console.log("로딩모달 표시");

			// Show the Modal on load
			$("#myModal99").modal("show");

		}

		function hide_loading_popup() {
			console.log("로딩모달 숨김");
			$("#myModal99").modal("hide");

		}
	</script>

	<!-- Modal -->
	<div class="modal fade" id="myModal99" role="dialog">
		<div class="modal-dialog">

			<!-- Modal content-->
			<div class="modal-content">
				<div class="modal-header">
					<h4 class="modal-title">장치 연결 확인 중</h4>
				</div>
				<div class="modal-body">
					<p>잠시만 기다려 주세요. 연결이 확인되면 팝업은 자동으로 사라집니다.</p>
				</div>
			</div>

		</div>
	</div>
	</div>
	<!-- 로딩팝업 모달창 끝 -->





	<!-- 모달팝업 시작 -->

	<!-- 모달창 프로그래스바 제어 -->
	<script>
		var valeur = 0;
		$(function() {
			//when loading..
			valeur = 0;

			//적용하기 모달창 종료시
			$('#myModal').on('hidden.bs.modal', function() {
				console.log("modal exit");
				stopfieldchange();

			})
		});

		function progressbar_reset() {
			valeur = 0;
			$('.progress-bar').css('width', valeur + '%').attr('aria-valuenow',
					valeur);
		}
		function progressbar_plus10() {
			valeur = valeur + 10;
			$('.progress-bar').css('width', valeur + '%').attr('aria-valuenow',
					valeur);

			if (valeur >= 100) {
				//$('#myModal').modal('hide');

				window.history.go(0);
			}
		}

		function progressbar_force(forcevalue) {
			valeur = forcevalue;
			$('.progress-bar').css('width', valeur + '%').attr('aria-valuenow',
					valeur);

		}
	</script>
	<!-- MQTT 수신 제어 -->
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/paho-mqtt/1.0.1/mqttws31.min.js"
		type="text/javascript"></script>
	<script>
		// Create a client instance
		client = new Paho.MQTT.Client("broker.hivemq.com", Number(8000),
				"clientId_${sessionScope.playerInfo.loginId}");
		console
				.log("Topic : /jyp/rpicontrol/${sessionScope.playerInfo.deviceId}/");

		// set callback handlers
		client.onConnectionLost = onConnectionLost;
		client.onMessageArrived = onMessageArrived;

		// connect the client
		client.connect({
			onSuccess : onConnect
		});

		// called when the client connects
		function onConnect() {
			// Once a connection has been made, make a subscription and send a message.
			console.log("onConnect");
			client
					.subscribe("/jyp/rpicontrol/${sessionScope.playerInfo.deviceId}/");
			message = new Paho.MQTT.Message("Hello");
			message.destinationName = "/jyp/rpicontrol/${sessionScope.playerInfo.deviceId}/";
			client.send(message);
		}

		function startfieldchange() {
			progressbar_reset();
			message = new Paho.MQTT.Message(
					"startfieldchange.mapid=${sessionScope.playerInfo.selectedMapId}");
			message.destinationName = "/jyp/rpicontrol/${sessionScope.playerInfo.deviceId}/";
			client.send(message);
		}

		function stopfieldchange() {
			progressbar_reset();
			message = new Paho.MQTT.Message("stopfieldchange");
			message.destinationName = "/jyp/rpicontrol/${sessionScope.playerInfo.deviceId}/";
			client.send(message);
		}

		// called when the client loses its connection
		function onConnectionLost(responseObject) {
			if (responseObject.errorCode !== 0) {
				console.log("onConnectionLost:" + responseObject.errorMessage);
			}
		}

		// called when a message arrives
		function onMessageArrived(message) {
			console.log("onMessageArrived:" + message.payloadString);
			if (message.payloadString == 'prog') {
				progressbar_force(0)
			} else if (message.payloadString == 'prog00'
					|| message.payloadString == 'prog0') {
				progressbar_force(0)
			} else if (message.payloadString.indexOf('startfieldchange') != -1) {
				progressbar_force(0)
			} else if (message.payloadString == 'prog10') {
				progressbar_force(20)
			} else if (message.payloadString == 'prog20') {
				progressbar_force(20)
			} else if (message.payloadString == 'prog30') {
				progressbar_force(30)
			} else if (message.payloadString == 'prog40') {
				progressbar_force(40)
			} else if (message.payloadString == 'prog50') {
				progressbar_force(50)
			} else if (message.payloadString == 'prog60') {
				progressbar_force(60)
			} else if (message.payloadString == 'prog70') {
				progressbar_force(70)
			} else if (message.payloadString == 'prog80') {
				progressbar_force(80)
			} else if (message.payloadString == 'prog90') {
				progressbar_force(90)
			} else if (message.payloadString == 'prog100') {
				window.history.go(0);
			} else if (message.payloadString == 'Hi') {
				console.log("RPi로 부터 Hi메세지 응답수신");
				hide_loading_popup();
			} else {
				console.log("MQTT msg 무시 : " + message.payloadString);
			}
		}
	</script>


	<!-- Modal -->
	<div class="modal fade" id="myModal" role="dialog">
		<div class="modal-dialog">

			<!-- Modal content-->
			<div class="modal-content">
				<!-- 	<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">&times;</button>		</div> -->
				<div class="modal-body">
					<p></p>
					<h2>필드설정 변경 중</h2>
					<p></p>
					<p>잠시만 기다려주세요</p>
					<p>진행상황:</p>
					<div class="progress">
						<div class="progress-bar progress-bar-striped active"
							role="progressbar" aria-valuenow="40" aria-valuemin="0"
							aria-valuemax="100" style="width: 00%"></div>
					</div>
				</div>


			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
			</div>


		</div>

	</div>
	</div>

	<!-- 모달팝업 끝 -->

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
		src="bootstrap/startbootstrap-full-slider-gh-pages/vendor/jquery/jquery.min.js"></script>
	<script
		src="bootstrap/startbootstrap-full-slider-gh-pages/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

</body>

</html>
