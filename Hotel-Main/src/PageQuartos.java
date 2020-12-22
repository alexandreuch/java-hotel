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

public class PageQuartos extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JTextField idQuarto;
	private JTextField tipoQuarto;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PageQuartos frame = new PageQuartos();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public PageQuartos() {
		setBackground(Color.WHITE);
		setTitle("Quartos");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1000, 650);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		Image hotelIcon = new ImageIcon(this.getClass().getResource("/hotelIcon.png")).getImage();
		setIconImage(hotelIcon);
		
		UIManager.getDefaults().put("OptionPane.background",SystemColor.white);
		UIManager.put("Panel.background", SystemColor.white);
		UIManager.put("Button.background",SystemColor.controlHighlight);
		
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
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(399, 82, 526, 458);
		contentPane.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		QuartosDAO startQuartos = new QuartosDAO();
		startQuartos.listarQuartos(table);
		
		JButton Adicionar = new JButton("Adicionar");
		Adicionar.setBackground(SystemColor.controlHighlight);
		Adicionar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
					if(startQuartos.verificaTipo(tipoQuarto.getText().toLowerCase()) == true) {
						startQuartos.adicionarQuartos(new Quartos(tipoQuarto.getText().toLowerCase()));
						startQuartos.listarQuartos(table);
					}else JOptionPane.showMessageDialog(contentPane, "ERRO: TIPO DE QUARTO NAO ENCONTRADO!");
			}
		});
		Adicionar.setBounds(228, 241, 89, 23);
		contentPane.add(Adicionar);
		
		idQuarto = new JTextField();
		idQuarto.setBounds(91, 372, 226, 30);
		contentPane.add(idQuarto);
		idQuarto.setColumns(10);
		
		tipoQuarto = new JTextField();
		tipoQuarto.setBounds(91, 200, 227, 30);
		contentPane.add(tipoQuarto);
		tipoQuarto.setColumns(10);
		
		JLabel IDQuarto = new JLabel("ID:");
		IDQuarto.setFont(new Font("Tahoma", Font.PLAIN, 16));
		IDQuarto.setBounds(39, 370, 60, 30);
		contentPane.add(IDQuarto);
		
		JLabel lblNewLabel = new JLabel("Tipo:");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel.setBounds(39, 198, 60, 30);
		contentPane.add(lblNewLabel);
		
		JButton Remover = new JButton("Remover");
		Remover.setBackground(SystemColor.controlHighlight);
		Remover.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(idQuarto.getText() != null && idQuarto.getText().matches("[0-9]*") && idQuarto.getText().equals("") == false) {	
					if(startQuartos.procuraQuarto(Integer.parseInt(idQuarto.getText())) == true) {
						if(startQuartos.verificaReservaQuarto(Integer.parseInt(idQuarto.getText())) == false){
							startQuartos.removerQuartosID(Integer.parseInt(idQuarto.getText()));
							startQuartos.listarQuartos(table);
						}else JOptionPane.showMessageDialog(contentPane, "ERRO: QUARTO RESERVADO!");
					}else JOptionPane.showMessageDialog(contentPane, "ERRO: QUARTO NAO ENCONTRADO!");
					
				}else JOptionPane.showMessageDialog(contentPane, "ERRO: COM A ENTRADA!");
			}
		});
		Remover.setBounds(228, 413, 89, 23);
		contentPane.add(Remover);
	}
}
