package controller;

import dao.CategoriaDAO;
import dao.ProdutoDAO;
import model.Categoria;
import model.Produto;

import javax.ejb.EJB;
import javax.persistence.criteria.CriteriaBuilder;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;


/**
 * Servlet implementation class InserirCategoria
 */
@WebServlet("/inserir-produto")
public class InserirProduto extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	@EJB
	private ProdutoDAO dao = new ProdutoDAO();
	@EJB
	private CategoriaDAO catDao = new CategoriaDAO();

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {
		List<Categoria> categorias = catDao.listar();
		request.setAttribute("categorias", categorias);
		RequestDispatcher dispatcher = request.getRequestDispatcher("inserir_produto.jsp");
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			  throws ServletException, IOException {
		Categoria categoriaObj;

		String nome = request.getParameter("nome");
		String preco = request.getParameter("preco");
		String categoria = request.getParameter("categoria");
		try {
			categoriaObj = catDao.porCodigo(Integer.parseInt(categoria));
		} catch (Exception ex) {
			ex.printStackTrace();
			PrintWriter out = response.getWriter();
			out.print("<html>");
			out.print("<h2> Nao foi possivel encontrar a categoria!</h2>");
			out.print("<br");
			out.print("<a href='index.html'> Voltar </a>");
			out.print("</html>");
			return;
		}

		Produto produto = new Produto();
		produto.setNome(nome);
		produto.setPreco(new BigDecimal(preco));
		produto.setCategoria(categoriaObj);
		
		try {
			dao.inserir(produto);
	
			response.sendRedirect(request.getContextPath() + "/produtos");
		} catch (Exception ex) {
			ex.printStackTrace();
			PrintWriter out = response.getWriter();
			out.print("<html>");
			out.print("<h2> Nao foi possivel inserir o produto!</h2>");
			out.print("<br");
			out.print("<a href='index.html'> Voltar </a>");
			out.print("</html>");
		}			
	}
	
}
