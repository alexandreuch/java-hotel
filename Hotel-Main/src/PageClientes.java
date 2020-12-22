import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Image;
import java.awt.Color;
import java.awt.SystemColor;

public class PageClientes extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JTextField idUser;
	private JTextField nomeUser;
	private JTextField userEnd;
	private JTextField userTel;


	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PageClientes frame = new PageClientes();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public PageClientes() {
		setBackground(Color.WHITE);
		setTitle("Clientes");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1000, 650);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		Image hotelIcon = new ImageIcon(this.getClass().getResource("/hotelIcon.png")).getImage();
		setIconImage(hotelIcon);
		
		JButton Voltar = new JButton("Voltar");
		Voltar.setBackground(SystemColor.controlHighlight);
		Voltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
				Menu menu = new Menu();
				menu.setVisible(true);
			}
		});
		
		Voltar.setBounds(11, 11, 89, 23);
		contentPane.add(Voltar);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(367, 33, 607, 552);
		contentPane.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		ClientesDAO clientesdao = new ClientesDAO();
		clientesdao.listarClientes(table);
		
		JLabel ID = new JLabel("ID");
		ID.setFont(new Font("Tahoma", Font.PLAIN, 16));
		ID.setBounds(73, 462, 46, 14);
		contentPane.add(ID);
		
		JLabel Nome = new JLabel("Nome");
		Nome.setFont(new Font("Tahoma", Font.PLAIN, 16));
		Nome.setBounds(23, 133, 46, 14);
		contentPane.add(Nome);
		
		JLabel Endereco = new JLabel("Endere\u00E7o");
		Endereco.setFont(new Font("Tahoma", Font.PLAIN, 16));
		Endereco.setBounds(23, 209, 89, 23);
		contentPane.add(Endereco);
		
		JLabel Telefone = new JLabel("Telefone");
		Telefone.setFont(new Font("Tahoma", Font.PLAIN, 16));
		Telefone.setBounds(23, 294, 77, 14);
		contentPane.add(Telefone);
		
		idUser = new JTextField();
		idUser.setBounds(110, 461, 233, 20);
		contentPane.add(idUser);
		idUser.setColumns(10);
		
		nomeUser = new JTextField();
		nomeUser.setBounds(110, 132, 233, 20);
		contentPane.add(nomeUser);
		nomeUser.setColumns(10);
		
		userEnd = new JTextField();
		userEnd.setBounds(110, 212, 233, 20);
		contentPane.add(userEnd);
		userEnd.setColumns(10);
		
		userTel = new JTextField();
		userTel.setBounds(110, 293, 233, 20);
		contentPane.add(userTel);
		userTel.setColumns(10);
		
		UIManager.getDefaults().put("OptionPane.background",SystemColor.white);
		UIManager.put("Panel.background", SystemColor.white);
		UIManager.put("Button.background",SystemColor.controlHighlight);
		
		JButton Adicionar = new JButton("Adicionar");
		Adicionar.setBackground(SystemColor.controlHighlight);
		Adicionar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				boolean end = (userEnd.getText() != null && userEnd.getText().equals("") == false);
				boolean tel = (userTel.getText() != null && userTel.getText().matches("[0-9]*") && userTel.getText().equals("") == false);
				boolean nome = (nomeUser.getText() != null && nomeUser.getText().equals("") == false);
				if(nome && end && tel) {
					if(clientesdao.procuraClientes(nomeUser.getText(),userTel.getText()) == false){
						clientesdao.adicionarCliente(new Clientes(nomeUser.getText(),userEnd.getText(),userTel.getText()));
						clientesdao.listarClientes(table);
					}else JOptionPane.showMessageDialog(contentPane, "INFO UNICAS DO USER JA EXISTEM!","Erro",1);
				}else JOptionPane.showMessageDialog(contentPane, "ERRO: COM A ENTRADA!","Erro",1);
			}
		});
		Adicionar.setBounds(254, 337, 89, 23);
		contentPane.add(Adicionar);
		
		JButton Remover = new JButton("Remover");
		Remover.setBackground(SystemColor.controlHighlight);
		Remover.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				boolean id = (idUser.getText() != null && idUser.getText().matches("[0-9]*") && idUser.getText().equals("") == false);
				boolean tel = (userTel.getText() != null && userTel.getText().matches("[0-9]*") && userTel.getText().equals("") == false);
				boolean nome = (nomeUser.getText() != null && nomeUser.getText().equals("") == false);
				
				if(id || nome || tel) {
					
					if(id == true){
						if(clientesdao.procurarClientesID(idUser.getText()) == true) {
							if(clientesdao.procuraReservado(idUser.getText(),2) == false){
								clientesdao.removerClienteID(Integer.parseInt(idUser.getText()));
								clientesdao.listarClientes(table);
							}else JOptionPane.showMessageDialog(contentPane,"USUARIO COM RESERVA!");
						}else JOptionPane.showMessageDialog(contentPane,"USUARIO NAO CADASTRADO!");
					}
					
					else if(nome == true) {
						if(clientesdao.procurarClientesNome(nomeUser.getText()) == true) {
							if(clientesdao.procuraReservado(nomeUser.getText(),0) == false) {	
								clientesdao.removerClienteUser(nomeUser.getText());
								clientesdao.listarClientes(table);
							}else JOptionPane.showMessageDialog(contentPane,"USUARIO COM RESERVA!");
						}else JOptionPane.showMessageDialog(contentPane,"USUARIO NAO CADASTRADO!");
				
				}else if(tel == true) {
						if(clientesdao.procurarClientesTel(userTel.getText()) == true) {
							if(clientesdao.procuraReservado(userTel.getText(),1) == false) {
								clientesdao.removerClienteTel(userTel.getText());
								clientesdao.listarClientes(table);
							}else JOptionPane.showMessageDialog(contentPane ,"USUARIO COM RESERVA!");
						}else JOptionPane.showMessageDialog(contentPane,"USUARIO NAO CADASTRADO!");
					}
				}else JOptionPane.showMessageDialog(contentPane,"ERRO: COM A ENTRADA!");
			}
		});
		Remover.setBounds(254, 504, 89, 23);
		contentPane.add(Remover);
		
		JLabel lblNewLabel = new JLabel("*");
		lblNewLabel.setBounds(23, 149, 46, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("*");
		lblNewLabel_1.setBounds(23, 307, 46, 14);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("*  = Campos \u00FAnicos");
		lblNewLabel_2.setBounds(11, 571, 129, 14);
		contentPane.add(lblNewLabel_2);
	}
}
