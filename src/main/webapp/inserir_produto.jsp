<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"   prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Inserir Produto</title>
	<style>
		.form {
			display: flex;
			flex-direction: column;
			align-items: center;
		}
		.input-entry {
			display: flex;
			flex-direction: row;
			min-width: 300px;
		}
		.input-label {
			min-width: 100px;
		}
	</style>
</head>
<body>
    <center>
		<h1>Inserir Categoria</h1>
		<form action="inserir-produto" method="POST">
			<div class="form">
				<div class="input-entry">
					<div class="input-label">
						Nome:
					</div>
					<input type="text" name="nome" id="nome">
				</div>
				<div class="input-entry">
					<div class="input-label">
						Preco:
					</div>
					<input type="number" min="1" step="any" name="preco" id="preco" />
				</div>
				<div class="input-entry">
					<div class="input-label">
						Categoria:
					</div>
					<select name="categoria">
						<c:forEach items="${categorias}" var="categoria">
							<option value="${categoria.codigo}">${categoria.nome}</option>
						</c:forEach>
					</select>

				</div>
				<div class="input-entry">
					<input type="submit" value="Inserir">
				</div>
			</div>
		</form>
		<a href="index.html"> Voltar </a>
	</center>
</body>
</html>