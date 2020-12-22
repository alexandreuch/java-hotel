import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Auxiliar extends BancoDeDados{

	public boolean verificaData(String data){ /** Verifica se a data é válida */
		if(data.length() == 8) {
			String dia = data.substring(0, 2);
			String mes = data.substring(3, 5);
			String ano = data.substring(6, 8);
			
			boolean bdia = (dia != null && dia.matches("[0-9]*") && dia.equals("") == false);
			boolean bmes = (mes != null && mes.matches("[0-9]*") && mes.equals("") == false);
			boolean bano = (ano != null && ano.matches("[0-9]*") && ano.equals("") == false);
			
			if((bdia && bmes && bano)== true) {
				int vdia = Integer.parseInt(dia);
				int vmes = Integer.parseInt(mes);
				int vano = Integer.parseInt(ano);
				if(vdia <= 30 && vmes <= 12 && vano <= 99) return true;
				else return false;
			}else return false;
		}else return false;
	}
	
	public String retornaAux(String buffer){ /**Trata a String ID(QAURTOS), ID(CLIENTES) tirando , e possíveis erros*/
		if(buffer.equals("")) return buffer;
		else {
			String resultado = buffer.replaceAll("( +)","").trim();
			resultado = resultado.replaceAll("[A-z.]", "");
			
			if(resultado.charAt(0) == ',') {
				for(int i=0 ; i < resultado.length();i++){
					if(resultado.charAt(i)==',');
					else{
						resultado = resultado.substring(i,resultado.length());
					};
				}
			}
			
			if(resultado.length() > 2) {
				char ini = resultado.charAt(0);
				char next = ' '; 
				String novos = "";
				for(int i = 1; i < resultado.length() ; i++) {
					next = resultado.charAt(i);
					if(ini == ',' && next == ',');
					else {
						novos+= ini;
						ini = next;
					}
				}
				resultado = novos+ini;
			}
				
			
			if(resultado.charAt(resultado.length()-1) == ',') return resultado.substring(0,resultado.length()-1);
			else return resultado;
		}
	}

	public int AchaIterador() { /**Retorna o contador do idReserva */
		try {
			Statement st = conexao.createStatement();
			ResultSet rs = st.executeQuery("SELECT * FROM reserva");
			int id = 0;
			while (rs.next()) {
				id = rs.getInt(1);
			}
			
			return id+1;
			
		}catch (SQLException e) {return 1;}
	}

}
