
public class Funcionarios {
	private String usuario;
	private String senha;
	private String nome;
	
	public Funcionarios(String usuario, String senha, String nome) {
		this.usuario = usuario;
		this.senha = senha;
		this.nome = nome;
	}

	public String getUsuario() {return usuario;}
	public String getSenha() {return senha;}	
	public String getNome() {return nome;}
	
}
