import java.sql.*;
import java.util.Vector;

import javax.swing.JTable;

import net.proteanit.sql.DbUtils;

public class QuartosDAO extends BancoDeDados{
		
	Vector<Integer> reservados = new Vector<Integer>();
	Vector<Integer> naoReservados = new Vector<Integer>();
	
	/** Adiciona quartos */
	public boolean adicionarQuartos(Quartos q) {
		try {
			Statement st = conexao.createStatement();
			st.executeUpdate("INSERT INTO quartos VALUES (NULL , '"+ q.getTipo() + "', 'no' )");

			return true;
			
		} catch (SQLException e) {System.out.println(e); return false; }
	}
	
	/** Lista todos os quartos na JTable*/
	public void listarQuartos(JTable table) {
		try {
			Statement st = conexao.createStatement();
			ResultSet rs = st.executeQuery("SELECT idQuartos,tipo FROM quartos");
			table.setModel(DbUtils.resultSetToTableModel(rs));
		}
		catch (SQLException e) {}
	}
	
	/** Verifica se o quarto está reservado*/
	public boolean verificaReservaQuarto(int id) {
		try {
			Statement st = conexao.createStatement();
			ResultSet rs = st.executeQuery("SELECT idQuartos FROM reserva");
			while(rs.next()) if(rs.getInt(1) == id) return true;
			return false;
		}
		catch (SQLException e) {return false;}
	}
	
	/** Procura se o quarto está no BD*/
	public boolean procuraQuarto(int id){
		try {
			Statement st = conexao.createStatement();
			ResultSet rs = st.executeQuery("SELECT * FROM quartos");
			while(rs.next()) {
				if(rs.getInt(1) == id) return true;
			}return false;
		}catch (SQLException e) {return false;}
	}
	
	/** Verifica se o tipo de quarto existe*/
	public boolean verificaTipo(String tipo) {
		if(tipo.equals("single") || tipo.equals("double") || tipo.equals("suite")) return true;
		else return false;
	}

	/** Remove quartos por ID*/
	public boolean removerQuartosID(int ID){
		try {
			Statement st = conexao.createStatement();
			st.executeUpdate("DELETE FROM quartos WHERE idQuartos = " + ID + ";");
			return true;
			
		} catch (SQLException e) {return false;}
	}
	
	/** Converte a String dataEntrada para um int*/
	public int diaReserva(String dataEntrada) {
		int diaReal = Integer.parseInt(dataEntrada.substring(0, 2));
		int mesReal = Integer.parseInt(dataEntrada.substring(3, 5));
		int anoReal = Integer.parseInt(dataEntrada.substring(6, 8));
		mesReal *= 30;
		anoReal *= 365;
		return diaReal+mesReal+anoReal; 
	}
	
	/** Função auxiliar para mudar o campo ocupped para 'yes' do quarto de ID especificado */
	public boolean attQuartos(int ID){
		try {
			Statement st = conexao.createStatement();
			st.executeUpdate("UPDATE quartos SET ocupped='yes' WHERE idQuartos = " + ID + ";");
			return true;
			
		} catch (SQLException e) {return false;}
	}
	
	/** Função auxiliar para mudar o campo ocupped para 'no' do quarto de ID especificado */
	public boolean rmmQuartos(int ID){
		try {
			Statement st = conexao.createStatement();
			st.executeUpdate("UPDATE quartos SET ocupped='no' WHERE idQuartos = " + ID + ";");
			return true;
			
		} catch (SQLException e) {return false;}
	}

	/** Adiciona numa lista de Integer os quartos livres, se esses forem menores que os necessários, essa lista é limpada */
	public boolean verificaIDQC(String tipo, String dataEntrada, int dias, int quartos) {
		try {
			Statement st = conexao.createStatement();
			ResultSet rs = st.executeQuery("SELECT * FROM reserva WHERE tipo = '" + tipo + "' ;");
			int reserva = diaReserva(dataEntrada);
			int buffer = 0;
			while(rs.next()) {
				buffer = diaReserva(rs.getString(6));
				if(reserva >=  buffer+rs.getInt(5) || buffer >= reserva+dias);
				else {
					reservados.add(rs.getInt(7));
					attQuartos(rs.getInt(7));
				}
			}
			
			ResultSet ts = st.executeQuery("SELECT idQuartos,tipo FROM quartos WHERE tipo = '" + tipo + "' AND ocupped='no';");
			 
			int cont = 0;
			
			while(ts.next()) cont++; 
			
			
			if(cont >= quartos) return true;
			else {
				for(int i = 0; i < reservados.size(); i++) rmmQuartos(reservados.elementAt(i));
				reservados.removeAllElements();
				return false;
			}
					
			
		}catch (SQLException e) {return false;}
	}
	
	/** Usado com a lista gerada da função quartosDisponiveis para verificar a disponibilidade dos quartos */
	public void quartosDisponiveis(JTable table,String tipo, String dataEntrada, int dias) {
		try {
			Statement st = conexao.createStatement();
			table.setModel(DbUtils.resultSetToTableModel(st.executeQuery(("SELECT idQuartos,tipo FROM quartos WHERE tipo = '" + tipo + "' AND ocupped='no';"))));
			
			for(int i = 0; i < reservados.size(); i++) rmmQuartos(reservados.elementAt(i));
			reservados.removeAllElements();
			
		}catch (SQLException e) {}
	}

	/** Usado com a lista gerada da função quartosDisponiveis para verificar a disponibilidade da lista dos quartos */
	public boolean quartosDisponiveis(Vector<Integer>idQuarto) {
		
		try {
			Statement st = conexao.createStatement();
			ResultSet rs = st.executeQuery("SELECT * FROM quartos");
			while(rs.next()) {
				if(!reservados.contains(rs.getInt(1))) {
					naoReservados.add(rs.getInt(1));
				}
			}
			
			
			boolean result = naoReservados.containsAll(idQuarto);
			
			for(int i = 0; i < reservados.size(); i++) rmmQuartos(reservados.elementAt(i));
			naoReservados.removeAllElements();
			reservados.removeAllElements();
			
			return result;
		
		}catch (SQLException e) {return false;}
	}
	
	/** Verifica se os quartos existem */
	public boolean quartosExistem(String tipo, int id){
		try {
			Statement st = conexao.createStatement();
			ResultSet rs = st.executeQuery("SELECT * FROM quartos");
			while(rs.next()) if(rs.getInt(1) == id && rs.getString(2).equals(tipo)) return true;
			return false;
		}catch (SQLException e) {return false;}
	}
	
}
