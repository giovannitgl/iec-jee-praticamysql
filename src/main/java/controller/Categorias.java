package controller;

import dao.CategoriaDAO;
import model.Categoria;

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

@WebServlet("/categorias")
public class Categorias extends HttpServlet{
	@EJB
    private CategoriaDAO dao = new CategoriaDAO();
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			listaCategoria(request, response);					
		} catch (SQLException ex) {
			throw new ServletException(ex);
		}
	}
    private void listaCategoria(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		List<Categoria> listaCategoria = dao.listar();
		request.setAttribute("listaCategoria", listaCategoria);
		RequestDispatcher dispatcher = request.getRequestDispatcher("categorias.jsp");
		dispatcher.forward(request, response);
	}
}
