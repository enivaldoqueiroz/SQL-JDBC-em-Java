package dao;

import java.beans.Statement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import conexaojdbc.SingleConnection;
import model.BeanUserFone;
import model.Telefone;
import model.Userposjava;

public class UserPosDAO {
	
	private Connection connection;
	
	public UserPosDAO() {
		connection = SingleConnection.getConnection();
	}
	
	public void salvar(Userposjava userposjava) {
		
		try {
			String sql = "insert into userposjava ( nome, email) values (?,?)";
			PreparedStatement insert = connection.prepareStatement(sql);
			insert.setString(1, userposjava.getNome());
			insert.setString(2,userposjava.getEmail());
			insert.execute();
			connection.commit();//salva no banco
			
		} catch (Exception e) {
			try {
				connection.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			e.printStackTrace();
		}
	
	}
	
	public void salvarTelefone(Telefone telefone) {
		try {
			String sql = "insert into telefoneuser (numero, tipo, usuariopessoa) values (?,?,?);";
			PreparedStatement insert = connection.prepareStatement(sql);
			insert.setString(1, telefone.getNumero());
			insert.setString(2, telefone.getTipo());
			insert.setLong(3, telefone.getUsuario());
			insert.execute();
			connection.commit();
		} catch (Exception e) {
			try {
				connection.rollback();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
			e.printStackTrace();
		}
	}
	
	public List<Userposjava> listar() throws SQLException{
		List<Userposjava> list = new ArrayList<Userposjava>();
		
		String sql = "select * from userposjava";
		
		PreparedStatement statement = connection.prepareStatement(sql);
		ResultSet resultado = statement.executeQuery();
		
		while(resultado.next()) {
			Userposjava userposjava = new Userposjava();
			userposjava.setId(resultado.getLong("id"));
			userposjava.setNome(resultado.getString("nome"));
			userposjava.setEmail(resultado.getString("email"));
			
			list.add(userposjava);
		}
		
		return list;
		
	}
	
	public Userposjava buscar(Long id) throws SQLException{
		Userposjava retorno = new Userposjava();
		
		String sql = "select * from userposjava where id = " + id;
		
		PreparedStatement statement = connection.prepareStatement(sql);
		ResultSet resultado = statement.executeQuery();
		
		while(resultado.next()) { //retorna apenas um ou nenhum
			
			retorno.setId(resultado.getLong("id"));
			retorno.setNome(resultado.getString("nome"));
			retorno.setEmail(resultado.getString("email"));
			
		}
		
		return retorno;
		
	}
	
	public List<BeanUserFone> listBeanUserFone (Long idUser){
		
		List<BeanUserFone> beanUserFones = new ArrayList<BeanUserFone>();
		
		String sql = "select nome, numero, email from telefoneuser as fone ";
		sql += " inner join userposjava as userp ";
		sql += " on fone.usuariopessoa = userp.id ";
		sql += " where userp.id = " + idUser;
		try {
			PreparedStatement consultarDuasTabelas = connection.prepareStatement(sql);
			ResultSet resultSet = consultarDuasTabelas.executeQuery();
			
			while(resultSet.next()) {
				BeanUserFone userFone = new BeanUserFone();
				userFone.setEmail(resultSet.getString("email"));
				userFone.setNome(resultSet.getString("nome"));
				userFone.setNumero(resultSet.getString("numero"));
				beanUserFones.add(userFone);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		
		return beanUserFones;
	}
	
	public void atualizar (Userposjava userposjava) {
		
		try {
			String sql = "update userposjava set nome = ? where id = " + userposjava.getId();
			
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1,userposjava.getNome());
			
			statement.execute();
			connection.commit();
		} catch (Exception e) {
			
			try {
				connection.rollback();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
			
			e.printStackTrace();
		}
	
	}
	
	public void deletar(Long id) {
		try {
			String sql = "delete from userposjava where id = " + id;
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.execute();
			connection.commit();
		} catch (Exception e) {
			try {
				connection.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		}
	}
	

}
