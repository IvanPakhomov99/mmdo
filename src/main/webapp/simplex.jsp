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
	<h1>Решение задачи симплекс-методом(by Пахомов И.Ю. ITKH 16-8)</h1>
	<hr>
	<div class="alert alert-info">
		<strong>Заполните формы!</strong> Заполните таблицу для нахождения
		решения
	</div>
	<div class="container-fluid">
	<form action="SimplexServlet" method="post">
		<table class="table table-hover">
			<tr>
				<th>.</th>
				<th>Прибыль (грн)</th>
				<th>Затраты удобрения</th>
				<th>Ограничение по площади</th>
				<th>Ограничение по объему продаж</th>
			</tr>
			<tr>
				<td>гречка</td>
				<td><input type="number" class="form-control" name="profit1"></td>
				<td><input type="number" class="form-control"
					name="fertilizer1"></td>
				<td><input type="number" class="form-control" name="square1"></td>
				<td><input type="number" class="form-control" name="amount1"></td>
			</tr>
			<tr>
				<td>ячмень</td>
				<td><input type="number" class="form-control" name="profit2"></td>
				<td><input type="number" class="form-control"
					name="fertilizer2"></td>
				<td><input type="number" class="form-control" name="square2"></td>
				<td><input type="number" class="form-control" name="amount2"></td>
			</tr>
			<tr>
				<td>просо</td>
				<td><input type="number" class="form-control" name="profit3"></td>
				<td><input type="number" class="form-control"
					name="fertilizer3"></td>
				<td><input type="number" class="form-control" name="square3"></td>
				<td><input type="number" class="form-control" name="amount3"></td>
			</tr>
			<tr>
				<td>картофель</td>
				<td><input type="number" class="form-control" name="profit4"></td>
				<td><input type="number" class="form-control"
					name="fertilizer4"></td>
				<td><input type="number" class="form-control" name="square4"></td>
				<td><input type="number" class="form-control" name="amount4"></td>
			</tr>
			<tr>
				<td>ВСЕГО</td>
				<td>-</td>
				<td><input type="number" class="form-control"
					name="fertilizer_all"></td>
				<td><input type="number" class="form-control" name="square_all"></td>
				<td><input type="number" class="form-control" name="amount_all"></td>
			</tr>
		</table>
		<input type="submit" value="Найти решение" class="btn btn-info">
	</form>
	</div>
</body>
</html>
