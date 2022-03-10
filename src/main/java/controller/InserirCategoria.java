package controller;
import java.io.IOException;
import java.io.PrintWriter;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import business.CategoriaService;
import dao.CategoriaDAO;
import model.Categoria;


/**
 * Servlet implementation class InserirCategoria
 */
@WebServlet("/inserir-categoria")
public class InserirCategoria extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	@EJB
	private CategoriaDAO dao = new CategoriaDAO();
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			  throws ServletException, IOException {
		
		String nome = request.getParameter("nome");
		
		Categoria categoria = new Categoria();		
		categoria.setNome(nome);		
		
		try {
			dao.inserir(categoria);
	
			response.sendRedirect(request.getContextPath() + "/index.html");
		} catch (Exception ex) {
			ex.printStackTrace();
			PrintWriter out = response.getWriter();
			out.print("<html>");
			out.print("<h2> Nao foi possivel inserir a categoria!</h2>");
			out.print("<br");
			out.print("<a href='index.html'> Voltar </a>");
			out.print("</html>");
		}			
	}
	
}
