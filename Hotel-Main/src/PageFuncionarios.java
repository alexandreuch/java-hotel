import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Image;

import javax.swing.JTextField;
import javax.swing.UIManager;

import java.awt.Color;
import java.awt.SystemColor;

public class PageFuncionarios extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JTextField funcID;
	private JTextField funcUSER;
	private JTextField funcKEY;
	private JTextField funcNome;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PageFuncionarios frame = new PageFuncionarios();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}


	public PageFuncionarios() {
		setBackground(Color.WHITE);
		setTitle("Funcion\u00E1rios");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1000, 650);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		Image hotelIcon = new ImageIcon(this.getClass().getResource("/hotelIcon.png")).getImage();
		setIconImage(hotelIcon);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(426, 64, 535, 513);
		contentPane.add(scrollPane);
		
		table = new JTable();
		table.setBackground(Color.WHITE);
		scrollPane.setViewportView(table);
		FuncionariosDAO funcionarios = new FuncionariosDAO();
		funcionarios.listarFuncionarios(table);
		
		JButton Voltar = new JButton("Voltar");
		Voltar.setBackground(SystemColor.controlHighlight);
		Voltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
				Menu menu = new Menu();
				menu.setVisible(true);
			}
		});
		Voltar.setBounds(10, 11, 89, 23);
		contentPane.add(Voltar);
		
		JLabel LABELID = new JLabel("ID");
		LABELID.setFont(new Font("Tahoma", Font.PLAIN, 16));
		LABELID.setBounds(191, 452, 26, 14);
		contentPane.add(LABELID);
		
		JLabel LABELUSER = new JLabel("Usu\u00E1rio");
		LABELUSER.setFont(new Font("Tahoma", Font.PLAIN, 16));
		LABELUSER.setBounds(27, 120, 75, 14);
		contentPane.add(LABELUSER);
		
		JLabel LABELSENHA = new JLabel("Senha");
		LABELSENHA.setFont(new Font("Tahoma", Font.PLAIN, 16));
		LABELSENHA.setBounds(27, 207, 75, 14);
		contentPane.add(LABELSENHA);
		
		JLabel LABELNOME = new JLabel("Nome");
		LABELNOME.setFont(new Font("Tahoma", Font.PLAIN, 16));
		LABELNOME.setBounds(27, 299, 46, 16);
		contentPane.add(LABELNOME);
		
		funcID = new JTextField();
		funcID.setBounds(219, 450, 157, 23);
		contentPane.add(funcID);
		funcID.setColumns(10);
		
		funcUSER = new JTextField();
		funcUSER.setBounds(112, 118, 264, 23);
		contentPane.add(funcUSER);
		funcUSER.setColumns(10);
		
		funcKEY = new JTextField();
		funcKEY.setBounds(112, 205, 264, 23);
		contentPane.add(funcKEY);
		funcKEY.setColumns(10);
		
		funcNome = new JTextField();
		funcNome.setBounds(112, 298, 264, 23);
		contentPane.add(funcNome);
		funcNome.setColumns(10);
		
		UIManager.getDefaults().put("OptionPane.background",SystemColor.white);
		UIManager.put("Panel.background", SystemColor.white);
		UIManager.put("Button.background",SystemColor.controlHighlight);
		
		JButton Adicionar = new JButton("Adicionar");
		Adicionar.setBackground(SystemColor.controlHighlight);
		Adicionar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				boolean user = (funcUSER.getText() != null && funcUSER.getText().equals("") == false);
				boolean key = (funcKEY.getText() != null && funcKEY.getText().equals("") == false);
				boolean nome = (funcNome.getText() != null && funcNome.getText().equals("") == false);
				
				if(user && key && nome) {
					if(funcionarios.verificaADD(funcUSER.getText(),funcKEY.getText(),funcNome.getText()) == true) {
						funcionarios.listarFuncionarios(table);
					}else JOptionPane.showMessageDialog(contentPane, "ERRO: INFO UNICAS DO FUNCIONARIO JA EXISTEM!");
				}else JOptionPane.showMessageDialog(contentPane, "ERRO: COM A ENTRADA!");		
			}
		});
		Adicionar.setBounds(287, 351, 89, 23);
		contentPane.add(Adicionar);
		
		JButton Remover = new JButton("Remover");
		Remover.setBackground(SystemColor.controlHighlight);
		Remover.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int resultRemove = 9;
				boolean id = (funcID.getText() != null && funcID.getText().matches("[0-9]*") && funcID.getText().equals("") == false);
				boolean user = (funcUSER.getText() != null && funcUSER.getText().equals("") == false);
				boolean key = (funcKEY.getText() != null && funcKEY.getText().equals("") == false);
				boolean nome = (funcNome.getText() != null && funcNome.getText().equals("") == false);
				if((id || user || nome) && key) {
					
					if(id == true) {
						resultRemove = funcionarios.verificaREMOVE(funcID.getText(), funcKEY.getText(), 2);
						if(resultRemove == 2) funcionarios.listarFuncionarios(table);
						else if(resultRemove == 1) JOptionPane.showMessageDialog(contentPane, "ERRO: SENHA ERRADA!");
						else JOptionPane.showMessageDialog(contentPane, "ERRO: ID NAO ENCONTRADO OU TENTATIVA DE EXCLUIR ADM");
					
					}else if(user == true) {
						resultRemove = funcionarios.verificaREMOVE(funcUSER.getText(), funcKEY.getText(), 0);
						if(resultRemove == 2) funcionarios.listarFuncionarios(table);
						else if(resultRemove == 1) JOptionPane.showMessageDialog(contentPane, "ERRO: SENHA ERRADA!");
						else JOptionPane.showMessageDialog(contentPane, "ERRO: USUARIO NAO ENCONTRADO OU TENTATIVA DE EXCLUIR AD");
					
					}else if(nome == true) {
						resultRemove = funcionarios.verificaREMOVE(funcNome.getText(), funcKEY.getText(), 1);
						if(resultRemove == 2) funcionarios.listarFuncionarios(table);
						else if(resultRemove == 1) JOptionPane.showMessageDialog(contentPane, "ERRO: SENHA ERRADA!");
						else JOptionPane.showMessageDialog(contentPane, "ERRO: NOME NAO ENCONTRADO OU TENTATIVA DE EXCLUIR ADM");
					}
				
				}else JOptionPane.showMessageDialog(contentPane, "ERRO: COM A ENTRADA!");
			}
		});
		Remover.setBounds(287, 498, 89, 23);
		contentPane.add(Remover);
		
		JLabel lblNewLabel = new JLabel("* = Campos \u00FAnicos");
		lblNewLabel.setBounds(27, 586, 157, 14);
		contentPane.add(lblNewLabel);
		
		JButton btnNewButton = new JButton("Senhas");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				funcionarios.listarFuncionariosSenhas(table);
			}
		});
		btnNewButton.setBounds(679, 21, 89, 23);
		contentPane.add(btnNewButton);
		
		JLabel lblNewLabel_1 = new JLabel("*");
		lblNewLabel_1.setBounds(27, 139, 46, 14);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("*");
		lblNewLabel_1_1.setBounds(27, 319, 46, 14);
		contentPane.add(lblNewLabel_1_1);
	}
}
