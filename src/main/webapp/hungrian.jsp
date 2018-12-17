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
<body>
	<div class="container-fluid">
		<h1>Решение задачи о назначениях</h1>
		<form action="HungrianServlet" method="post">
			<table class="table table-hover">
				<tr>
					<caption>Укажите, командировочные расходы для
						сотрудников:</caption>
					<th>Менеджеры</th>
					<th>Харьков</th>
					<th>Днепр</th>
					<th>Львов</th>
					<th>Киев</th>
					<th>Мерефа</th>
				</tr>	
				<tr>
					<c:forEach begin="0" end="4" var="i">
						<tr>
							<c:if test="${i == 0}">
								<th>По финансам</th>
							</c:if>
							<c:if test="${i == 1}">
								<th>По маркетингу</th>
							</c:if>
							<c:if test="${i == 2}">
								<th>По производству</th>
							</c:if>
							<c:if test="${i == 3}">
								<th>По персоналу</th>
							</c:if>
							<c:if test="${i == 4}">
								<th>По инвестициям</th>
							</c:if>
							<c:forEach begin="0" end="4" var="j">
								<td><input type="number" class="form-control"
									name="${i}${j}" required></td>
							</c:forEach>
						</tr>
					</c:forEach>
				</tr>
			</table>
			<input type="submit" value="Найти решение" class="btn btn-info">
		</form>
	</div>
</body>
</html>