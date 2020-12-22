import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

import javax.swing.JTable;

import net.proteanit.sql.DbUtils;

public class ReservaDAO extends BancoDeDados{
	
	/** Adiciona reserva */
	public boolean adicionaReserva(Reservas r) { 
		try {
			Statement st = conexao.createStatement();
			
			st.executeUpdate("INSERT INTO reserva VALUES (" + r.getIDReserva() + 
					", " +	r.getNumero() +
					", " +	r.getOcupacao() +  
					", '" +  r.getTipo() + 
					"', " + r.getNoites() +
					", '" + r.getdataEntrada() + 
					"', " + r.getIDQuartos() + 
					", " + r.getIDClientes() + ")");
			
			return true;
			
		} catch (SQLException e) {System.out.println(e); return false; }
	}

	/** Remove reserva por ID */
	public boolean removerReserva(int ID){ 
		try { 
			Statement st = conexao.createStatement();
			st.executeUpdate("DELETE FROM reserva WHERE idReserva = " + ID + ";");
			return true;
		} catch (SQLException e) {return false;}
	}
	
	/** Lista todas as reservas na JTable principal */
	public void listarReservas(JTable table) {
		try {
			Statement st = conexao.createStatement();
			ResultSet rs = st.executeQuery("SELECT idReserva,ocupacao,tipo,noites,dEntrada,idQuartos,idClientes FROM reserva");
			table.setModel(DbUtils.resultSetToTableModel(rs));
		}
		catch (SQLException e) {}
	}
	
	/** Verifica se o ID existe em reservas */
	public boolean verificaID(int id) {
		try { 
			Statement st = conexao.createStatement();
			ResultSet rs = st.executeQuery("SELECT * FROM reserva");
			while(rs.next()) if(id == rs.getInt(1)) return true;
			return false;
		} catch (SQLException e) {
			return false;}
		}
	
	/** Verifica se dos clientes e resevas existem */
	public boolean verificaIDQC(Vector<Integer>idQuarto,Vector<Integer> idCliente) {
		try { 
			Statement st = conexao.createStatement();
			ResultSet rs = st.executeQuery("SELECT idQuartos FROM quartos");
			
			int flag = 0;
		
			while(rs.next()) if(idQuarto.contains(rs.getInt(1))) flag++; 
			
			if(flag != idQuarto.size()) return false;
			else flag = 0;
			
			ResultSet ps = st.executeQuery("SELECT idClientes FROM clientes");
	
			while(ps.next()) if(idCliente.contains(ps.getInt(1))) flag++;
		
			if(flag != idCliente.size()) return false;
			else return true;

			
		} catch (SQLException e) {
			return false;}
	}
	
}
	
	/*
	public String dataSaida(String dataEntrada, int noites) {
		String saida = "";
		int diaReal = Integer.parseInt(dataEntrada.substring(0, 2));
		int mesReal = Integer.parseInt(dataEntrada.substring(3, 5));
		int anoReal = Integer.parseInt(dataEntrada.substring(6, 8));
		int i = 0,j = 0;
		int bufferDia = diaReal + noites;
		int bufferMes = mesReal;
		int bufferAno = anoReal;
		if(bufferDia > 30) {
			while(bufferDia > 30) {
				bufferDia -= 30;
				i++;
			}
			bufferDia = (diaReal + noites)%30;
			if(bufferDia == 0) {
				bufferDia+=1;
				i++;
			}
			bufferMes += i;
			if(bufferMes > 12) {
				while(bufferMes > 12) {
					bufferMes -= 12;
					j++;
				}
				bufferAno += j++;
			}
		}
		return saida += bufferDia + "/" + bufferMes + "/" + bufferAno;
	}
	*/
	

