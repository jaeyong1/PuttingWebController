<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<!doctype html>
<html lang="en">
<head>
<!-- Required meta tags -->
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">

<!-- Bootstrap CSS -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
	integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm"
	crossorigin="anonymous">

<title>타석사용현황</title>
</head>
<body>
	<h3></h3>

	<!-- Optional JavaScript -->
	<!-- jQuery first, then Popper.js, then Bootstrap JS -->
	<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"
		integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN"
		crossorigin="anonymous"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"
		integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q"
		crossorigin="anonymous"></script>
	<script
		src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"
		integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl"
		crossorigin="anonymous"></script>




	<table class="table table-striped">
		<thead>
			<tr>
				<th scope="col" class="text-center">타석</th>
				<th scope="col" class="text-center">현재상태</th>
				<th scope="col" class="text-center">남은시간</th>
				<th scope="col" class="text-center">대기수</th>
			</tr>
		</thead>

		<tbody>
			<!-- DB 데이터 채움 (클래스 변수사용) -->
			<c:forEach var="i" items="${items}" varStatus="status">
				<tr>
					<th scope="row" class="text-center">${i.reservedRoomNumber}</th>

					<c:choose>
						<c:when test="${('미사용' == i.reservedState) }">
							<td class="table-success text-center">${i.reservedState}</td>
						</c:when>
					</c:choose>
					<c:choose>
						<c:when test="${('사용중' == i.reservedState) }">
							<td class="table-danger text-center">${i.reservedState}</td>
						</c:when>
					</c:choose>
					<c:choose>
						<c:when test="${('정리중' == i.reservedState) }">
							<td class="table-warning text-center">${i.reservedState}</td>
						</c:when>
					</c:choose>


					<td class="text-center">${i.reservedEndTime}</td>
					<td class="text-center">${i.waiting}명</td>

				</tr>
			</c:forEach>
		</tbody>

	</table>
	<p>&nbsp;</p>
</body>
</html>