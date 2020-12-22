/*
import java.sql.*;

public class testeMain extends BancoDeDados{
	public static void main(String[] args){
		try {
			Statement st = conexao.createStatement();
			ResultSet rs = st.executeQuery("SELECT * FROM quartos WHERE idQuartos = "+ 22 +";");
			ResultSet lt = st.executeQuery("SELECT * FROM clientes WHERE idClientes = "+ 1 +";");
			Reservas reserva = new Reservas(1, "single",1, 1, 3, "10/20/20", rs.getInt(1) , lt.getInt(1));
			ReservaDAO r = new ReservaDAO();
			r.adicionaReserva(reserva);
		}catch (SQLException e) {System.out.println(e);}
	}
}
*/