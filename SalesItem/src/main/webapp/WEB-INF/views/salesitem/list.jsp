<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my"%>
<c:url var="R" value="/" />
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<script src="${R}res/common.js"></script>
<link rel="stylesheet" href="${R}res/common.css">
</head>
<body>
	<div class="container">
		<h1>판매목록</h1>
		<div class="mb5  pull-right">
			<a class="btn btn-primary" href="create?pg=${ pagination.pg }"> <i
				class="glyphicon glyphicon-plus"></i> 판매등록
			</a>
		</div>
		<table class="table table-bordered">
			<thead>
				<tr>
					<th>id</th>
					<th>제품명</th>
					<th>타입</th>
					<th>가격</th>
					<th>재고</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="salesitem" items="${ list }">
					<tr data-url="edit?id=${salesitem.id}&pg=${ pagination.pg }">
						<td>${ salesitem.id }</td>
						<td>${ salesitem.itemName }</td>
						<td>${ salesitem.itemtype.typeName }</td>
						<td>${ salesitem.unitPrice }</td>
						<td>${ salesitem.count }</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		<my:pagination pageSize="${ pagination.sz }"
			recordCount="${ pagination.recordCount }" />
	</div>
</body>
</html>
