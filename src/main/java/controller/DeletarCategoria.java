package controller;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import business.CategoriaService;
import dao.CategoriaDAO;

@WebServlet(urlPatterns = "/delete-categoria")
public class DeletarCategoria extends HttpServlet {
	@EJB
    private CategoriaDAO dao = new CategoriaDAO();
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			Integer codigo = Integer.parseInt(request.getParameter("id"));
            dao.deletar(codigo);
            response.sendRedirect(request.getContextPath() + "/index.html");
		} catch (Exception ex) {
			throw new ServletException(ex);
		}
	}
}
