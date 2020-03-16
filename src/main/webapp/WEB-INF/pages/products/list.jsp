<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
	<head>
		<c:url value="${pageContext.servletContext.contextPath}" var="contexto" />
		<meta charset="UTF-8">
		<title>Listagem de Livros</title>
	</head>
	<body>
		<h2>${message}</h2>
		<table>
			<thead>
				<tr>
					<th>Titulo</th>
					<th>Valores</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${products}" var="product">
					<tr>
						<td>${product.title}</td>
						<td>
							<c:forEach items="${product.prices}" var="price">
								<span>${price.type} - ${price.value}</span>
							</c:forEach>
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</body>
</html>