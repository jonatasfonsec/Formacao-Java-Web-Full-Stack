package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import conexaojdbc.SingleConnection;
import model.UserPosJava;

public class UserPosDAO {

	private Connection connection;

	public UserPosDAO() {
		connection = SingleConnection.getConnection();
	}

	public void salvar(UserPosJava userposjava) {
		try {
			String sql = "insert into userposjava (id,nome,email) values (?,?,?)";
			PreparedStatement insert = connection.prepareStatement(sql);
			insert.setLong(1, userposjava.getId());
			insert.setString(2, userposjava.getNome());
			insert.setString(3, userposjava.getEmail());
			insert.execute();
			connection.commit();

		} catch (Exception e) {
			try {
				connection.rollback();
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		}
	}
	
	public List<UserPosJava> listar() throws Exception{
		List<UserPosJava> list = new ArrayList<UserPosJava>();
		
		String sql = "select * from userposjava";
		
			PreparedStatement statement = connection.prepareStatement(sql);
			ResultSet resultado = statement.executeQuery();
			
			while(resultado.next()) {
				UserPosJava userposjava = new UserPosJava();
				
				userposjava.setId(resultado.getLong("id"));
				userposjava.setNome(resultado.getString("nome"));
				userposjava.setEmail(resultado.getString("email"));
				
				list.add(userposjava);
			}
		return list;
	}
	
	
	
	public UserPosJava buscar(Long id) throws Exception{
		UserPosJava retorno = new UserPosJava();
		
		String sql = "select * from userposjava where id = " + id;
		
			PreparedStatement statement = connection.prepareStatement(sql);
			ResultSet resultado = statement.executeQuery();
			
			while(resultado.next()) { // retorna apenas um ou nenhum.
				
				retorno.setId(resultado.getLong("id"));
				retorno.setNome(resultado.getString("nome"));
				retorno.setEmail(resultado.getString("email"));
				
			}
		return retorno;
	}
	
	public void atualizar (UserPosJava userposjava) {
		
		try {
			String sql = "update userposjava set nome = ? where id = " + userposjava.getId();
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, userposjava.getNome());
			
			statement.execute();
			connection.commit();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			
			try {
				connection.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			
			e.printStackTrace();
			
		}
	}
	
	
	
	
}
