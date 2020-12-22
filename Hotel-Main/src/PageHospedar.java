import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.Vector;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Image;
import java.awt.SystemColor;

public class PageHospedar extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JButton Voltar;
	private JTable table_1;
	private JTable table_2;
	private JTextField tipo;
	private JLabel lblNewLabel_1;
	private JLabel lblNewLabel_2;
	private JTextField entrada;
	private JTextField dias;
	private JLabel qtdpessoas;
	private JTextField pessoas;
	private JTextField IdClientes;
	private JLabel lblNewLabel_3;
	private JLabel lblNewLabel_4;
	private JButton btnNewButton_1;
	private JTextField ID;
	private JButton btnNewButton_2;
	private JLabel lblNewLabel_6;
	private JTextField idQuarto;
	private JLabel lblNewLabel_7;
	private JLabel lblNewLabel_8;
	private JLabel lblNewLabel_9;
	private JLabel lblNewLabel_10;
	private JLabel lblNewLabel_11;
	private JLabel lblNewLabel_12;
	private JTextField NumQuartos;
	private int iteradorReserva;
	private JLabel lblNewLabel_13;

	/*
	
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PageHospedar frame = new PageHospedar();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public PageHospedar() {
		setForeground(Color.WHITE);
		setBackground(Color.WHITE);
		setTitle("Hospedar");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1100, 800);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		Image hotelIcon = new ImageIcon(this.getClass().getResource("/hotelIcon.png")).getImage();
		setIconImage(hotelIcon);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(392, 23, 682, 378);
		contentPane.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		ReservaDAO reservas = new ReservaDAO();
		reservas.listarReservas(table);
		
		Voltar = new JButton("Voltar");
		Voltar.setBackground(SystemColor.controlHighlight);
		Voltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
				Menu menu = new Menu();
				menu.setVisible(true);
			}
		});
		Voltar.setBounds(10, 20, 89, 23);
		contentPane.add(Voltar);
		
		JScrollPane tabelaClientes = new JScrollPane();
		tabelaClientes.setBounds(392, 412, 345, 338);
		contentPane.add(tabelaClientes);
		
		table_1 = new JTable();
		tabelaClientes.setViewportView(table_1);
		
		JScrollPane tabelaQuartos = new JScrollPane();
		tabelaQuartos.setBounds(747, 412, 327, 338);
		contentPane.add(tabelaQuartos);
		
		table_2 = new JTable();
		tabelaQuartos.setViewportView(table_2);
		
		QuartosDAO quartosdao = new QuartosDAO();
		ClientesDAO clientesdao = new ClientesDAO();
		
		quartosdao.listarQuartos(table_1);
		clientesdao.listarClientesPH(table_2);
		
		tipo = new JTextField();
		tipo.setBounds(104, 90, 237, 20);
		contentPane.add(tipo);
		tipo.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Tipo:");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel.setBounds(10, 90, 65, 17);
		contentPane.add(lblNewLabel);
		
		lblNewLabel_1 = new JLabel("Dias: ");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_1.setBounds(10, 234, 46, 14);
		contentPane.add(lblNewLabel_1);
		
		lblNewLabel_2 = new JLabel("Entrada:");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_2.setBounds(10, 160, 65, 14);
		contentPane.add(lblNewLabel_2);
		
		entrada = new JTextField();
		entrada.setBounds(104, 159, 237, 20);
		contentPane.add(entrada);
		entrada.setColumns(10);
		
		dias = new JTextField();
		dias.setBounds(104, 233, 237, 20);
		contentPane.add(dias);
		dias.setColumns(10);
		
		qtdpessoas = new JLabel("Qtd.Pessoas:");
		qtdpessoas.setFont(new Font("Tahoma", Font.PLAIN, 15));
		qtdpessoas.setBounds(10, 361, 89, 19);
		contentPane.add(qtdpessoas);
		
		pessoas = new JTextField();
		pessoas.setBounds(104, 362, 237, 20);
		contentPane.add(pessoas);
		pessoas.setColumns(10);
		
		IdClientes = new JTextField();
		IdClientes.setBounds(104, 485, 237, 20);
		contentPane.add(IdClientes);
		IdClientes.setColumns(10);
		
		lblNewLabel_3 = new JLabel("ID(Clientes):");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_3.setBounds(10, 486, 98, 19);
		contentPane.add(lblNewLabel_3);
		
		lblNewLabel_4 = new JLabel("Separado por v\u00EDrgulas!");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.PLAIN, 9));
		lblNewLabel_4.setBounds(10, 505, 98, 14);
		contentPane.add(lblNewLabel_4);
		
		ID =  new JTextField();
		ID.setBounds(102, 640, 239, 20);
		contentPane.add(ID);
		ID.setColumns(10);
		
		Auxiliar auxiliador = new Auxiliar();
		iteradorReserva = auxiliador.AchaIterador();
		
		
		JLabel lblNewLabel_5 = new JLabel("ID: ");
		lblNewLabel_5.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_5.setBounds(73, 641, 26, 14);
		contentPane.add(lblNewLabel_5);
		
		btnNewButton_1 = new JButton("Excluir");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				boolean IDs = (ID.getText() != null && ID.getText().matches("[0-9]*") && ID.getText().equals("") == false);
				if(IDs == true) {
					if(reservas.verificaID(Integer.parseInt(ID.getText())) == true){
						reservas.removerReserva(Integer.parseInt(ID.getText()));
						reservas.listarReservas(table);
					}else JOptionPane.showMessageDialog(contentPane, "ERRO: RESERVA NÃO ENCONTRADA!");
				}else JOptionPane.showMessageDialog(contentPane, "ERRO: ENTRADA INVÁLIDA!");
			}
		});
		btnNewButton_1.setBackground(SystemColor.controlHighlight);
		btnNewButton_1.setBounds(252, 671, 89, 30);
		contentPane.add(btnNewButton_1);
		

		lblNewLabel_6 = new JLabel("ID(Quartos):");
		lblNewLabel_6.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_6.setBounds(10, 424, 89, 23);
		contentPane.add(lblNewLabel_6);
		
		idQuarto = new JTextField();
		idQuarto.setBounds(104, 427, 237, 20);
		contentPane.add(idQuarto);
		idQuarto.setColumns(10);

		lblNewLabel_12 = new JLabel("N.Quartos:");
		lblNewLabel_12.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_12.setBounds(10, 295, 91, 23);
		contentPane.add(lblNewLabel_12);
		
		NumQuartos = new JTextField();
		NumQuartos.setBounds(104, 298, 237, 20);
		contentPane.add(NumQuartos);
		NumQuartos.setColumns(10);

		UIManager.getDefaults().put("OptionPane.background",SystemColor.white);
		UIManager.put("Panel.background", SystemColor.white);
		UIManager.put("Button.background",SystemColor.controlHighlight);
		
		
		JButton btnNewButton = new JButton("Reservar");
		btnNewButton.setBackground(SystemColor.controlHighlight);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				boolean days = (dias.getText() != null && dias.getText().matches("[0-9]*") && dias.getText().equals("") == false);
				boolean bolqtdpessoas = (pessoas.getText() != null && pessoas.getText().matches("[0-9]*") && pessoas.getText().equals("") == false);
				boolean bolnquartos = (NumQuartos.getText() != null && NumQuartos.getText().matches("[0-9]*") && NumQuartos.getText().equals("") == false);
				boolean bolidQuarts = (idQuarto.getText() != null && idQuarto.getText().equals("") == false);
				boolean bolids = (IdClientes.getText() != null && IdClientes.getText().equals("") == false);
				if(quartosdao.verificaTipo(tipo.getText().toLowerCase()) == true) {
					String idQuartoauxiliar = auxiliador.retornaAux(idQuarto.getText());
					String idClientesauxiliar = auxiliador.retornaAux(IdClientes.getText());
					if(auxiliador.verificaData(entrada.getText()) == true && days == true && bolqtdpessoas == true && bolnquartos == true && bolidQuarts == true && bolids == true && idQuarto.getText().charAt(0) != '.' && IdClientes.getText().charAt(0) != '.'
							&& idQuartoauxiliar.equals("") == false && idClientesauxiliar.equals("") == false) {		
						Vector<Integer> reservados = new Vector<Integer>();
							String auxString = "";
							
							
							for(int i = 0; i < idQuartoauxiliar.length(); i++){
								if(Character.isDigit(idQuartoauxiliar.charAt(i)) == true) auxString += idQuartoauxiliar.charAt(i);
								else {
									if(auxString.matches("[0-9]*")) reservados.add(Integer.parseInt(auxString));
									auxString = "";
								}
							}
							reservados.add(Integer.parseInt(auxString));
							  //resolver 10;
							auxString = "";
							Vector<Integer> ids = new Vector<Integer>();
							for(int i = 0; i < idClientesauxiliar.length(); i++){
								if(Character.isDigit(idClientesauxiliar.charAt(i)) == true) auxString += idClientesauxiliar.charAt(i);
								else {
									if(auxString.matches("[0-9]*")) ids.add(Integer.parseInt(auxString));
									auxString = "";
								}
							}
							
							ids.add(Integer.parseInt(auxString));
							
							if(reservas.verificaIDQC(reservados,ids) == true){
								if(quartosdao.verificaIDQC(tipo.getText().toLowerCase(),entrada.getText() , Integer.parseInt(dias.getText()), Integer.parseInt(NumQuartos.getText())) == true) {
										if(quartosdao.quartosDisponiveis(reservados)){
											if(reservados.size() == Integer.parseInt(NumQuartos.getText()) && ids.size() == Integer.parseInt(pessoas.getText())){
											
												int qtdQuartos = reservados.size();
												int numPessoas = ids.size();
												if(qtdQuartos <= numPessoas) {
													
												int preco = 0;
												int adicional = 0;
												int capacidade = 0;
												if(tipo.getText().toLowerCase().equals("single")){
													preco = 100;
													adicional = 20;
													capacidade = 2;
												}
												else if(tipo.getText().toLowerCase().equals("double")){
													preco = 150;
													adicional = 30;
													capacidade = 4;
												}
												else if(tipo.getText().toLowerCase().equals("suite")){
													preco = 200;
													adicional = 40;
													capacidade = 6;
												}
												
												if(capacidade*qtdQuartos >= numPessoas) {
													
														
														int Qtdreservas[] = new int[qtdQuartos];
														for(int i = 0; i < qtdQuartos; i++){
															Qtdreservas[i] = 0;
														}
														
														int flag = 0;
														int index = 0;
														while(flag < numPessoas) {
															Qtdreservas[index]+=1;										
															index++;
															flag++;
															if(index >= qtdQuartos) {
																index = 0;
															}
														}
														
														int j = 0;
														boolean sinalize = true;
														for(int i = 0; i < Qtdreservas.length; i++) {
															sinalize = sinalize && quartosdao.quartosExistem(tipo.getText(), reservados.get(i));;
														}
														j = 0;
														
														if(sinalize == true) {
															for(int i = 0; i < Qtdreservas.length; i++){ 
																Reservas reserva = new Reservas(iteradorReserva,
																		tipo.getText(),
																		Qtdreservas[i], 
																		Integer.parseInt(dias.getText()), 
																		entrada.getText(), 
																		reservados.get(i),
																		ids.get(j));
																reservas.adicionaReserva(reserva);
																j+=Qtdreservas[i];
															}
															reservas.listarReservas(table);
															int restante = capacidade*qtdQuartos%numPessoas;
															int precoInicial = preco*qtdQuartos*Integer.parseInt(dias.getText());
															int precoAdicional = 0;
															precoAdicional = adicional*restante*Integer.parseInt(dias.getText());
															
							
															int precoFinal = precoInicial+precoAdicional;
															JOptionPane.showMessageDialog(contentPane, "O VALOR FOI R$" + precoFinal);
															iteradorReserva++;
															
														}else JOptionPane.showMessageDialog(contentPane, "ERRO: IDS NÃO ENCONTRADOS");
														
													}else JOptionPane.showMessageDialog(contentPane, "ERRO: HÁ PESSOAS DEMAIS!");
														
												}else JOptionPane.showMessageDialog(contentPane, "ERRO: NO MÍNIMO UMA PESSOA POR QUARTO!");
										
											}else JOptionPane.showMessageDialog(contentPane, "ERRO: NÚMERO PESSOAS OU QUARTOS NAO COMPATÍVEIS");
										
									}else JOptionPane.showMessageDialog(contentPane, "ERRO: QUARTO(S) ESCOLHIDOS NÃO ESTA(O) LIVRE(S) !");
									
								}else JOptionPane.showMessageDialog(contentPane, "ERRO: NÃO HÁ QUARTOS SUFICIENTES!");
								
							}else JOptionPane.showMessageDialog(contentPane, "ERRO: IDQUARTO,CLIENTES OU INVALIDOS!");
						
						}else  JOptionPane.showMessageDialog(contentPane, "ERRO: ENTRADA INVÁLIDA!");
							
				}else JOptionPane.showMessageDialog(contentPane, "ERRO: TIPO NÃO EXISTE!");
	
			}
		});
		
		btnNewButton.setBounds(104, 542, 98, 30);
		contentPane.add(btnNewButton);
		
		btnNewButton_2 = new JButton("Filtrar");
		btnNewButton_2.setBackground(SystemColor.controlHighlight);
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0){
				if(quartosdao.verificaTipo(tipo.getText().toLowerCase())) {
						boolean dataentrada = auxiliador.verificaData(entrada.getText());
						boolean days = (dias.getText() != null && dias.getText().matches("[0-9]*") && dias.getText().equals("") == false);
						boolean bolnquartos = (NumQuartos.getText() != null && NumQuartos.getText().matches("[0-9]*") && NumQuartos.getText().equals("") == false);
						if(dataentrada && days && bolnquartos) {
						if(quartosdao.verificaIDQC(tipo.getText().toLowerCase(),entrada.getText() , Integer.parseInt(dias.getText()), Integer.parseInt(NumQuartos.getText()))) {		
							quartosdao.quartosDisponiveis(table_1,tipo.getText().toLowerCase(),entrada.getText(), Integer.parseInt(dias.getText()));
						}else{
							quartosdao.listarQuartos(table_1);
							JOptionPane.showMessageDialog(contentPane, "ERRO: NÃO HÁ QUARTOS O SUFICENTES NESSA DATA!");
						}
					}else JOptionPane.showMessageDialog(contentPane, "ERRO: PROBLEMA NA ENTRADA!");
				}else JOptionPane.showMessageDialog(contentPane, "ERRO: TIPO NÃO EXISTE!");
			}
		});
		btnNewButton_2.setBounds(235, 542, 106, 30);
		contentPane.add(btnNewButton_2);
		
		lblNewLabel_7 = new JLabel("Separado por v\u00EDrgulas!");
		lblNewLabel_7.setFont(new Font("Tahoma", Font.PLAIN, 9));
		lblNewLabel_7.setBounds(10, 443, 98, 14);
		contentPane.add(lblNewLabel_7);
		
		lblNewLabel_8 = new JLabel("FILTRO");
		lblNewLabel_8.setFont(new Font("Tahoma", Font.PLAIN, 9));
		lblNewLabel_8.setBounds(10, 106, 46, 14);
		contentPane.add(lblNewLabel_8);
		
		lblNewLabel_9 = new JLabel("FILTRO");
		lblNewLabel_9.setFont(new Font("Tahoma", Font.PLAIN, 9));
		lblNewLabel_9.setBounds(10, 174, 46, 14);
		contentPane.add(lblNewLabel_9);
		
		lblNewLabel_10 = new JLabel("FILTRO");
		lblNewLabel_10.setFont(new Font("Tahoma", Font.PLAIN, 9));
		lblNewLabel_10.setBounds(10, 248, 46, 14);
		contentPane.add(lblNewLabel_10);
		
		lblNewLabel_11 = new JLabel("FILTRO");
		lblNewLabel_11.setFont(new Font("Tahoma", Font.PLAIN, 9));
		lblNewLabel_11.setBounds(10, 316, 46, 14);
		contentPane.add(lblNewLabel_11);
		
		lblNewLabel_13 = new JLabel("dd/mm/aa");
		lblNewLabel_13.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel_13.setBounds(169, 186, 113, 14);
		contentPane.add(lblNewLabel_13);
		
		
		
	}
}
