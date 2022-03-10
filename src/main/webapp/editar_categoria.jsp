<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Editar Categoria</title>
</head>
<body>
    <center>
		<h1>Editar Categoria</h1>
		<form action="edit" method="POST">
            <input type="hidden" name="codigo" value="${categoria.codigo}" />
			Nome: <input type="text" name="nome" id="nome" value="${categoria.nome}">
			<input type="submit" value="Editar">
		</form>
		<a href="index.html"> Voltar </a>
	</center>
</body>
</html>