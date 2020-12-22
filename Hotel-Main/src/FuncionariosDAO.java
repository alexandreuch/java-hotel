import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JTable;

import net.proteanit.sql.DbUtils;

public class FuncionariosDAO extends BancoDeDados{

	/** Adiciona comentarios ao BD */
	public boolean adicionarFuncionarios(Funcionarios f) {
		try {
			Statement st = conexao.createStatement();
			st.executeUpdate("INSERT INTO funcionarios VALUES (NULL , '" + f.getUsuario() + "'," + " '" + f.getSenha() + "', '" + f.getNome() + "')");
			return true;
			
		} catch (SQLException e) {System.out.println("Não foi possivel adicionar"); return false; }
	}
	
	/** Lista todos os funcionários sem senha do BD */
	public void listarFuncionarios(JTable table) {
		try {
			Statement st = conexao.createStatement();
			ResultSet rs = st.executeQuery("SELECT idFuncionarios,usuario,nome FROM funcionarios");
			table.setModel(DbUtils.resultSetToTableModel(rs));
		}
		catch (SQLException e) {}
	}

	/** Lista todos os funcionários com senha do BD */
	public void listarFuncionariosSenhas(JTable table) {
		try {
			Statement st = conexao.createStatement();
			ResultSet rs = st.executeQuery("SELECT * FROM funcionarios");
			table.setModel(DbUtils.resultSetToTableModel(rs));
		}
		catch (SQLException e) {}
	}
	
	/** Verifica se o login foi realizado com sucesso*/
	public boolean verificaLogin(String user,String senha) {
		try {
			Statement st = conexao.createStatement();
			ResultSet rs = st.executeQuery("SELECT * FROM funcionarios");
			while (rs.next()) {	
				if(rs.getString(2).equals(user) && rs.getString(3).equals(senha)) return true;
			
			}return false;
		}
		catch (SQLException e) {
			System.out.println(e);
			return false;}
	}
	
	/** Adiciona funcionário se ele não já não teve uma de suas informações únicas adicionadas*/
	public boolean verificaADD(String usuario ,String senha, String nome) {
		try {
			Statement st = conexao.createStatement();
			ResultSet rs = st.executeQuery("SELECT * FROM funcionarios");
			while (rs.next()) {	
				if(rs.getString(2).equals(usuario) || rs.getString(3).equals(nome)) return false;
			}
			adicionarFuncionarios(new Funcionarios(usuario,senha,nome));	
			return true;
		}catch (SQLException e) {return false;}}
	
	/** Remove funcionários, exceto adm. Sua entrada depende de uma flag onde (0 -> usuario, 1 -> nome, 2..n -> ID). Retornará um número:3 para buffer não encontrada ,2 para Sucess, 1 Senha errada.*/
	public int verificaREMOVE(String buffer,String senha, int flag) { 
		int id = -999;
		int secflag = 0;
		
		if(flag == 0) {
				try {
					Statement st = conexao.createStatement();
					ResultSet rs = st.executeQuery("SELECT * FROM funcionarios");
					while (rs.next()) {	
						if(rs.getString(2).equals(buffer) && !(rs.getString(2).equals("admin"))) {
							secflag += 1;
							if(rs.getString(3).equals(senha)) {
								id = rs.getInt(1);
								secflag += 1;
							}else return secflag;
						}
					}if (secflag == 0) return secflag;
				}catch (SQLException e) {}}
		
		else if(flag == 1) {
				try {
					Statement st = conexao.createStatement();
					ResultSet rs = st.executeQuery("SELECT * FROM funcionarios");
					while (rs.next()) {	
						if(rs.getString(4).equals(buffer) && !(rs.getString(2).equals("admin"))) {
							secflag += 1;
							if(rs.getString(3).equals(senha)) {
								id = rs.getInt(1);
								secflag += 1;
							}else return secflag;
						}
					}if (secflag == 0) return secflag;
				}catch (SQLException e) {}}
		
		else {
			if(Integer.parseInt(buffer) != 11){
			try {
				Statement st = conexao.createStatement();
				ResultSet rs = st.executeQuery("SELECT * FROM funcionarios");
				while (rs.next()) {	
					if(rs.getInt(1) == Integer.parseInt(buffer)) {
						secflag += 1;
						if(rs.getString(3).equals(senha)) {
							id = rs.getInt(1);
							secflag += 1;
						}else return secflag;
					}
				}if (secflag == 0) return secflag;
			}catch (SQLException e) {}
			
			}else return -1; }
		
			try {
			Statement st = conexao.createStatement();
			st.executeUpdate("DELETE FROM funcionarios WHERE idFuncionarios = " + id + ";");
			return 2;
		
		}catch (SQLException e) {return -1;}}

}
	

