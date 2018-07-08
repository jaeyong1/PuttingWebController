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

<title>HS Golf - Lesson</title>

<!-- Bootstrap core CSS -->
<link
	href="bootstrap/startbootstrap-1-col-portfolio-gh-pages/vendor/bootstrap/css/bootstrap.min.css"
	rel="stylesheet">

<!-- Custom styles for this template -->
<link
	href="bootstrap/startbootstrap-1-col-portfolio-gh-pages/css/1-col-portfolio.css"
	rel="stylesheet">

</head>

<body>

	<!-- Navigation -->
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
				</ul>
			</div>
		</div>
	</nav>

	<!-- Page Content -->
	<div class="container">

		<!-- Page Heading -->
		<h1 class="my-4">
			Lesson<small>차곡차곡 경험을 쌓아보세요</small>
		</h1>

		<!-- DB 데이터 채움 (클래스 변수사용) -->
		<c:forEach var="i" items="${items}" varStatus="status">

			<!-- Project One -->
			<div class="row">
				<div class="col-md-7">
					<a href="#"> <!-- <img class="img-fluid rounded mb-3 mb-md-0"	src="http://placehold.it/700x300" alt="">  -->
						<img class="img-fluid rounded mb-3 mb-md-0"
						src="mapinfo/${i.id}/700lesson.jpg" alt="">
					</a>
				</div>
				<div class="col-md-5">
					<!-- CC이름 Hole이름 -->
					<h3>${i.ccname}${i.holename}</h3>
					<!-- 난이도별+설명 -->
					<p>${i.desc}</p>
					<a class="btn btn-primary" href="fieldselect">자세히 보기</a> <a
						class="btn btn-primary" href="fieldselect">필드적용</a>
				</div>
			</div>
			<!-- /.row -->

			<hr>
			</form>
		</c:forEach>




		<!-- Pagination -->
		<ul class="pagination justify-content-center">
			<li class="page-item"><a class="page-link" href="#"
				aria-label="Previous"> <span aria-hidden="true">&laquo;</span> <span
					class="sr-only">Previous</span>
			</a></li>
			<li class="page-item"><a class="page-link" href="#">1</a></li>
			<li class="page-item"><a class="page-link" href="#">2</a></li>
			<li class="page-item"><a class="page-link" href="#">3</a></li>
			<li class="page-item"><a class="page-link" href="#"
				aria-label="Next"> <span aria-hidden="true">&raquo;</span> <span
					class="sr-only">Next</span>
			</a></li>
		</ul>

	</div>
	<!-- /.container -->

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
		src="bootstrap/startbootstrap-1-col-portfolio-gh-pages/vendor/jquery/jquery.min.js"></script>
	<script
		src="bootstrap/startbootstrap-1-col-portfolio-gh-pages/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

</body>

</html>