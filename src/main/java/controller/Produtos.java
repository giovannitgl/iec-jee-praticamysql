package controller;

import dao.CategoriaDAO;
import dao.ProdutoDAO;
import model.Categoria;
import model.Produto;

import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/produtos")
public class Produtos extends HttpServlet{
	@EJB
    private ProdutoDAO dao = new ProdutoDAO();
	@EJB
	private CategoriaDAO catDao = new CategoriaDAO();

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			listaProdutos(request, response);
		} catch (SQLException ex) {
			throw new ServletException(ex);
		}
	}
    private void listaProdutos(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		List<Produto> listaProdutos = dao.listar();
		for (Produto prod: listaProdutos) {
			try {
				Categoria cat = catDao.porCodigo(prod.getCategoria().getCodigo());
				prod.setCategoria(cat);
			} catch (Exception ex) {
				throw new ServletException(ex);
			}
		}
		List<Categoria> listaCategorias = catDao.listar();

		request.setAttribute("listaProduto", listaProdutos);
		request.setAttribute("listaCategorias", listaCategorias);
		RequestDispatcher dispatcher = request.getRequestDispatcher("produtos.jsp");
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			listaProdutosFiltrado(request, response);
		} catch (SQLException ex) {
			throw new ServletException(ex);
		}
	}

	private void listaProdutosFiltrado(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		String nome = request.getParameter("nome");
		String categoria = request.getParameter("categoria");
		List<Produto> listaProdutos = dao.listarFiltro(Integer.parseInt(categoria), nome);
		for (Produto prod: listaProdutos) {
			try {
				Categoria cat = catDao.porCodigo(prod.getCategoria().getCodigo());
				prod.setCategoria(cat);
			} catch (Exception ex) {
				throw new ServletException(ex);
			}
		}
		List<Categoria> listaCategorias = catDao.listar();

		request.setAttribute("listaProduto", listaProdutos);
		request.setAttribute("listaCategorias", listaCategorias);
		RequestDispatcher dispatcher = request.getRequestDispatcher("produtos.jsp");
		dispatcher.forward(request, response);
	}
}
