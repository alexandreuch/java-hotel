
public class Reservas{

	private int idReserva;
	private int ocupacao;
	private int noites;
	private String dataEntrada;
	private String tipo;
	private int idQuartos;
	private int idClientes;
	private String numero;

	public Reservas(int idReserva, String tipo, int ocupacao, int noites, String dataEntrada, int idQuartos ,int idClientes){
		this.idReserva = idReserva;
		this.ocupacao = ocupacao;
		this.noites = noites;
		this.dataEntrada = dataEntrada;
		this.tipo = tipo;
		this.idQuartos = idQuartos;
		this.numero = null;
		this.idClientes = idClientes;
	}
	
	public int getIDReserva () {
		return idReserva;
	}
	
	public String getNumero(){
		return numero;
	}
	
	public int getOcupacao() {
		return ocupacao;
	}
	public int getNoites() {
		return noites;
	}
	public String getdataEntrada() {
		return dataEntrada;
	}
	public String getTipo() {
		return tipo;
	}
	public int getIDQuartos() {
		return idQuartos;
	}
	public int getIDClientes() {
		return idClientes;
	}
	
	public int calculaValorQuarto(){
		int valor = 1, adicional = 1,extras = 0;

		if(tipo.equals("single")) {
			valor = 100;
			adicional = 50;
			if(ocupacao > 1) {
				extras = ocupacao - 1;
			}
		}
		else if(tipo.equals("double")) {
			valor = 120;
			adicional = 60;
			if(ocupacao > 2) {
				extras = ocupacao - 2;
			}
		}
		else if(tipo.equals("suite")) {
			valor = 200;
			adicional = 100;
			if(ocupacao > 3) {
				extras = ocupacao - 3;
			}
		}
		return noites * (valor + (extras*adicional));
	}
	

}
