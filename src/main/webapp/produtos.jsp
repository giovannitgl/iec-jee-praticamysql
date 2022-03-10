<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"   prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Categorias</title>
</head>
<body>
	<center>
		<h1>Produtos</h1>
        <h2>
        	<a href="inserir-produto">Inserir Novo Produto</a>
        	&nbsp;&nbsp;&nbsp;       	        	
        </h2>
	</center>
    <div align="center">
        <form action="produtos" method="POST">
            Busca Categoria
            <select name="categoria">
                <option value="0"></option>
                <c:forEach items="${listaCategorias}" var="categoria">
                    <option value="${categoria.codigo}">${categoria.nome}</option>
                </c:forEach>
            </select>
            Buscar: <input type="text" name="nome" id="nome">
            <input type="submit" value="Filtrar">
        </form>
        <table border="1" cellpadding="5">
            <caption><h2>Lista de Produtos</h2></caption>
            <tr>
                <th>Codigo</th>
                <th>Nome</th>
                <th>Preco</th>
                <th>Categoria</th>
            </tr>
            <c:forEach var="produto" items="${listaProduto}">
                <tr>
                    <td><c:out value="${produto.codigo}" /></td>
                    <td><c:out value="${produto.nome}" /></td>
                    <td><c:out value="${produto.preco}" /></td>
                    <td><c:out value="${produto.categoria.nome}" /></td>
                </tr>
            </c:forEach>
        </table>
        <a href="index.html"> Voltar </a>
    </div>
</body>

</html>