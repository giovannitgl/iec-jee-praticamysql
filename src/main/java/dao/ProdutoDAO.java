package dao;

import model.Categoria;
import model.Produto;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ProdutoDAO {
	
	public String inserir(Produto produto) {
		String retorno = "falha";
		Conexao conn = new Conexao();
		try {
			Statement stmt = (Statement) conn.getConn().createStatement();
			String sql = "INSERT INTO produto (nome, preco, categoriaID)" +
					" VALUES ('" + produto.getNome() + "', '" + produto.getPreco().toString() + "'" +
					", " + produto.getCategoria().getCodigo() + ");";
			stmt.execute(sql);
			retorno = "sucesso";
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			conn.fecharConexao();
		}		
		return retorno;
	}
	
	public List<Produto> listar(){
		List<Produto> produtos = new ArrayList<Produto>();
		Conexao conn = new Conexao();
		try {
			Statement stmt = (Statement) conn.getConn().createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * from produto");
			while (rs.next()) {
				Produto produto = new Produto();
				produto.setCodigo(rs.getInt("codigo"));
				produto.setNome(rs.getString("nome"));
				produto.setPreco(rs.getBigDecimal("preco"));
				Categoria categoria = new Categoria();
				categoria.setCodigo(rs.getInt("categoriaID"));
				produto.setCategoria(categoria);
				produtos.add(produto);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			conn.fecharConexao();
		}
		return produtos;
	}

	public List<Produto> listarFiltro(int categoriaCodigo, String produtoNome){
		List<Produto> produtos = new ArrayList<Produto>();
		Conexao conn = new Conexao();
		try {
			Statement stmt = (Statement) conn.getConn().createStatement();
			String sql = "SELECT * from produto ";

			int filters = 0;
			if (categoriaCodigo != 0) {
				filters++;
			}
			if (produtoNome.length() != 0) {
				filters++;
			}
			if (filters > 0) {
				sql += " WHERE ";
				if (categoriaCodigo != 0) {
					sql += "categoriaID = " + categoriaCodigo;
					if (filters == 2) {
						sql += " AND ";
					}
				}
				if (produtoNome.length() != 0) {
					sql += "nome like '%" + produtoNome + "%'";
				}
			}
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				Produto produto = new Produto();
				produto.setCodigo(rs.getInt("codigo"));
				produto.setNome(rs.getString("nome"));
				produto.setPreco(rs.getBigDecimal("preco"));
				Categoria categoria = new Categoria();
				categoria.setCodigo(rs.getInt("categoriaID"));
				produto.setCategoria(categoria);
				produtos.add(produto);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			conn.fecharConexao();
		}
		return produtos;
	}

	public Produto porCodigo(Integer id) throws Exception {
        Conexao conn = new Conexao();
		Produto produto = new Produto();
		try {
			Statement stmt = (Statement) conn.getConn().createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * from produto where codigo = " + id);
			if (rs.next()) {
				produto.setCodigo(rs.getInt("codigo"));
				produto.setNome(rs.getString("nome"));
		
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			conn.fecharConexao();
		}
		return produto;
    }

    public void editar(Produto produto) throws Exception {
		Conexao conn = new Conexao();
		try {
			Statement stmt = (Statement) conn.getConn().createStatement();
			final var update = "update produto set nome = '" + produto.getNome() + "' where codigo = " + produto.getCodigo();
			stmt.executeUpdate(update);
			
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			conn.fecharConexao();
		}
    }

	public void deletar(Integer codigo) throws Exception {
		Conexao conn = new Conexao();
		try {
			Statement stmt = (Statement) conn.getConn().createStatement();
			final var update = "delete from produto where codigo = " + codigo;
			stmt.executeUpdate(update);
			
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			conn.fecharConexao();
		}

	}
}
