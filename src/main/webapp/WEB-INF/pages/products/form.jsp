<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<!DOCTYPE html>
<html>
	<head>
		<c:set value="${pageContext.servletContext.contextPath}" var="contexto" />
		<meta charset="UTF-8">
		<title>Cadastro de Livro</title>
	</head>
	<body>
		<c:if test="${not empty message_error}">
			<h1>${message_error}</h1>
		</c:if>
		<form:form action="${contexto}/produtos" commandName="product" method="POST" enctype="multipart/form-data">
			<fieldset>
				<legend>Cadastro de Livros</legend>
				
				<div>
					<label for="title">Título</label>
					<form:input id="title" path="title" placeholder="Product title"/>
					<form:errors path="title" />
				</div>
				
				<div>
					<label for="description">Descrição</label>
					<form:textarea path="description" rows="5" cols="80" placeholder="Product description"/>
					<form:errors path="description" />
				</div>
				
				<div>
					<label for="releaseDate">Data de Liberação</label>
					<form:input type="text" id="releaseDate" path="releaseDate" placeholder="Release Date"/>
					<form:errors path="releaseDate" />
				</div>
				
				<div>
					<label for="pages">Número de Páginas</label>
					<form:input id="pages" path="pages" placeholder="Product pages"/>
					<form:errors path="pages" />
					
					<!-- 
					<spring:hasBindErrors name="product">
						<c:forEach items="${errors.getFieldErrors('pages')}" var="error">
							<span style="color: #ff0000; display: block;">
								<spring:message code="${error.code}" text="${error.defaultMessage}"></spring:message>
							</span>
						</c:forEach>
					</spring:hasBindErrors>
					-->
				</div>
				
				<div>
					<label for="summary">Sumário do livro</label>
					<input type="file" id="summary" name="summary"/>
					<form:errors path="summaryPath" />
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
		</form:form>
	</body>
</html>