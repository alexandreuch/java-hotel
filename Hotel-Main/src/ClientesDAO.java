import java.sql.*;

import javax.swing.JTable;

import net.proteanit.sql.DbUtils;

public class ClientesDAO extends BancoDeDados{

	/** Adiciona cliente*/
	public boolean adicionarCliente(Clientes c) {
		try {
			Statement st = conexao.createStatement();
			st.executeUpdate("INSERT INTO clientes VALUES (NULL, '" + c.getNome() + "'," + " '" + c.getEndereco() + "', '" + c.getTelefone() + "')");
			return true;
			
		} catch (SQLException e) {System.out.println(e); return false; }
	}
	
	/** Ve se as infos únicas de um cliente ja foram cadastradas */
	public boolean procuraClientes(String nome, String telefone){
		try {
			Statement st = conexao.createStatement();
			ResultSet rs = st.executeQuery("SELECT * FROM clientes");
			while (rs.next()) {	
				if(rs.getString(2).equals(nome) || rs.getString(4).equals(telefone)) return true;
			}return false;
		}
		catch (SQLException e) {return false;}
	}

	/** Lista clientes pelo id e senha*/
	public void listarClientesPH(JTable table) {
		try {
			Statement st = conexao.createStatement();
			ResultSet rs = st.executeQuery("SELECT idClientes, nome FROM clientes");
			table.setModel(DbUtils.resultSetToTableModel(rs));
		}
		catch (SQLException e) {}
	}
	
	/**Lista todos os clientes na tabela*/
	public void listarClientes(JTable table) {
		try {
			Statement st = conexao.createStatement();
			ResultSet rs = st.executeQuery("SELECT * FROM clientes");
			table.setModel(DbUtils.resultSetToTableModel(rs));
		}
		catch (SQLException e) {}
	}

	/** Procura clientes por ID*/
	public boolean procurarClientesID(String id) {
		try {
			Statement st = conexao.createStatement();
			ResultSet rs = st.executeQuery("SELECT * FROM clientes");
			while (rs.next()) {	
				if(rs.getString(1).equals(id)) return true;
			
			}return false;
		}
		catch (SQLException e) {
			System.out.println(e);
			return false;}
	}
	
	/** Procura clientes por Nome*/
	public boolean procurarClientesNome(String nome) {
		try {
			Statement st = conexao.createStatement();
			ResultSet rs = st.executeQuery("SELECT * FROM clientes");
			while (rs.next()) {	
				if(rs.getString(2).equals(nome)) return true;
			
			}return false;
		}
		catch (SQLException e) {
			System.out.println(e);
			return false;}
	}

	/** Procura clientes por cel*/
	public boolean procurarClientesTel(String tel) {
		try {
			Statement st = conexao.createStatement();
			ResultSet rs = st.executeQuery("SELECT * FROM clientes");
			while (rs.next()) {	
				if(rs.getString(4).equals(tel)) return true;
			
			}return false;
		}
		catch (SQLException e) {
			System.out.println(e);
			return false;}
	}
	
	/** Remove clientes por ID*/
	public void removerClienteID(int id){
		try {
			Statement st = conexao.createStatement();
			st.executeUpdate("DELETE FROM clientes WHERE idClientes = " + id + ";");
		} catch (SQLException e) {}
	}

	/** Remove clientes por Nome*/
	public void removerClienteUser(String nome){
		try {
			Statement st = conexao.createStatement();
			ResultSet rs = st.executeQuery("SELECT * FROM clientes");
			
			int id = -999;
			while (rs.next()) {
				if(nome.equals(rs.getString(2))) id = rs.getInt(1);
				break;
			}
				st.executeUpdate("DELETE FROM clientes WHERE idClientes = " + id + ";");
			
		} catch (SQLException e) {}
	}

	/** Remove clientes por tel*/
	public void removerClienteTel(String tel){
		try {
			Statement st = conexao.createStatement();
			ResultSet rs = st.executeQuery("SELECT * FROM clientes");
			
			int id = -999;
			while (rs.next()) {
				if(tel.equals(rs.getString(4))) id = rs.getInt(1);
				break;
			}
			st.executeUpdate("DELETE FROM clientes WHERE idClientes = " + id + ";");
			
		} catch (SQLException e) {}
	}
	
	/** Remove clientes. Sua entrada depende de uma flag onde (0 -> nome, 1 -> numero, 2..n -> ID). */
	public boolean procuraReservado(String buffer, int flag) { 
		int id = -999;
		if(flag == 0) {
				try {
					Statement st = conexao.createStatement();
					ResultSet rs = st.executeQuery("SELECT * FROM clientes");
					while (rs.next()) {	
						if(rs.getString(2).equals(buffer)) id = rs.getInt(1);
					}
				}
				catch (SQLException e) {}}
		else if(flag == 1) {
				try {
					Statement st = conexao.createStatement();
					ResultSet rs = st.executeQuery("SELECT * FROM clientes");
					while (rs.next()) {	
						if(rs.getString(4).equals(buffer)) id = rs.getInt(1);
					}
				}
				catch (SQLException e) {}}
		else id = Integer.parseInt(buffer);

		
		try {
			Statement st = conexao.createStatement();
			ResultSet rs = st.executeQuery("SELECT idClientes FROM reserva");
			while(rs.next()) {
				if(rs.getInt(1) == id) return true;
			}return false;
		}
		catch (SQLException e) {return false;}
	}

}
