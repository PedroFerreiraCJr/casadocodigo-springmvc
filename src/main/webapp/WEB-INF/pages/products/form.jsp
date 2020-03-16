<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
	<head>
		<c:set value="${pageContext.servletContext.contextPath}" var="contexto" />
		<meta charset="UTF-8">
		<title>Cadastro de Livro</title>
	</head>
	<body>
		<form action="${contexto}/produtos" method="POST">
			<fieldset>
				<legend>Cadastro de Livros</legend>
				<div>
					<label for="titulo">Título</label>
					<input type="text" id="title" name="title" value="" />
				</div>
				
				<div>
					<label for="description">Descrição</label>
					<textarea id="description" name="description" rows="5" cols="80"></textarea>
				</div>
				
				<div>
					<label for="pages">Número de Páginas</label>
					<input type="number" id="pages" name="pages" value="" />
				</div>
				
				<c:forEach items="${types}" var="type" varStatus="status">
					<div>
						<label for="price_${type}">${type}</label>
						<input type="text" id="price_${type}" name="prices[${status.index}].value" />
						<input type="hidden" name="prices[${status.index}].type" value="${type}" />
					</div>
				</c:forEach>
				
				<input type="submit" value="Cadastrar"/>
			</fieldset>
		</form>
	</body>
</html>