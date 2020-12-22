
public class Clientes {

	private String nome;
	private String endereco;
	private String telefone;
	
	public Clientes(String nome, String endereco, String telefone) {
		this.nome = nome;
		this.endereco = endereco;
		this.telefone = telefone;
	}
	
	public String getNome(){
		return nome;
	}
	
	public String getEndereco(){
		return endereco;
	}
	
	public String getTelefone(){
		return telefone;
	}

}

