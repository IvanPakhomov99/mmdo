<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<!-- Latest compiled and minified CSS -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"
	integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u"
	crossorigin="anonymous">

<!-- Optional theme -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css"
	integrity="sha384-rHyoN1iRsVXV4nD0JutlnGaslCJuC7uwjduW9SVrLvRYooPp2bWYgmgJQIXwl/Sp"
	crossorigin="anonymous">

<!-- Latest compiled and minified JavaScript -->
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"
	integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa"
	crossorigin="anonymous"></script>
</head>
<body><div class="container-fluid">
	<h1>Решение T задачи</h1>
	<form action="TransportProblemServlet" method="post">
		<table class="table table-hover">
			<caption>Укажите, сколько тонн муки производит каждый
				хлебокомбинат:</caption>
			<tr>
				<c:forEach begin="1" end="3" var="i">
					<th><c:out value="${i}" />-ый хлебокомбинат</th>
				</c:forEach>
			</tr>
			<tr>
				<c:forEach begin="1" end="3" var="i">
					<td><input type="number" class="form-control"
						name="bakery${i}" required></td>
				</c:forEach>
			</tr>
		</table>
		<table class="table table-hover">
			<caption>Укажите, сколько тонн муки необходимо каждому
				хлебзаводу:</caption>
			<c:forEach begin="1" end="4" var="i">
				<th><c:out value="${i}" />-ый хлебзавод</th>
			</c:forEach>
			</tr>
			<tr>
				<c:forEach begin="1" end="4" var="i">
					<td><input type="number" class="form-control"
						name="factory${i}" required></td>
				</c:forEach>
		</table>
		<table class="table table-hover">
		<tr>
			<caption>Укажите, тарифы перевозок для 1 т муки :</caption>
			<th>/<th>
			<c:forEach begin="1" end="4" var="i">
				<th><c:out value="${i}" />-ый хлебзавод</th>
			</c:forEach>
			<tr>
			<c:forEach begin="1" end="3" var="i"><tr>
			<td><th><c:out value="${i}" />-ый хлебкомбинат</th></td>
				<c:forEach begin="1" end="4" var="j">
				<td>	<input type="number" class="form-control"
						name="${i}${j}" required></td>
				</c:forEach>
				</tr>
			</c:forEach>
		</table>
		<label><input type = "radio" class="form-control" name = "method" value = "leastCostRule">Метод наименьших затрат</label>
	<label>	<input type = "radio" class="form-control" name = "method" value = "northWestCorner">Метод северо-западного угла</label>
	 <input type="submit" value="Найти решение" class="btn btn-info">
	</form>
	</div>
</body>
</html>