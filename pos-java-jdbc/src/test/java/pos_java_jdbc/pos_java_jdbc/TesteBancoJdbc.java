package pos_java_jdbc.pos_java_jdbc;

import java.sql.SQLException;
import java.util.List;

import org.junit.Test;

import conexaojdbc.SingleConnection;
import dao.UserPosDAO;
import model.Telefone;
import model.Userposjava;

public class TesteBancoJdbc {
	
	@Test
	public void initBanco() {
		//SingleConnection.getConnection();
		UserPosDAO userPosDAO = new UserPosDAO();
		Userposjava userposjava = new Userposjava();
		
		//suserposjava.setId(5L);
		userposjava.setNome("Teste");
		userposjava.setEmail("teste@gmail.com");
		
		userPosDAO.salvar(userposjava);
	}
	
	
	public void initList() {
		UserPosDAO dao = new UserPosDAO();
		try {
			List<Userposjava> list = dao.listar();
			
			for (Userposjava userposjava : list) {
				System.out.println(userposjava);
				System.out.println("--------------------------------------");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void initBuscar() {
		UserPosDAO dao = new UserPosDAO();
		try {
		Userposjava userposjava = dao.buscar(4L);
		System.out.println(userposjava);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void initAtualizar() {
		
		try {
			UserPosDAO dao = new UserPosDAO();
			Userposjava objetoBanco = dao.buscar(5L);
			objetoBanco.setNome("Nome mudado com metodo atualizar");
			dao.atualizar(objetoBanco);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	@Test
	public void initDeletar() {
		try {
			UserPosDAO dao = new UserPosDAO();
			dao.deletar(7L);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testeInsertTelefone() {
		Telefone telefone = new Telefone();
		telefone.setNumero("(85) 8888-8888");
		telefone.setTipo("Casa");
		telefone.setUsuario(3L);
		
		UserPosDAO  dao = new UserPosDAO();
		dao.salvarTelefone(telefone);
		
	}

}
