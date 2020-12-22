import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.Window;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.SystemColor;
import java.awt.Font;

public class Menu extends JFrame {

	private JPanel contentPane;


	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Menu frame = new Menu();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public Menu() {
		setBackground(Color.WHITE);
		setTitle("Menu");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 330);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		
		Image hotelIcon = new ImageIcon(this.getClass().getResource("/hotelIcon.png")).getImage();
		setIconImage(hotelIcon);
		
		
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton Reservas = new JButton("Reservas");
		Reservas.setFont(new Font("Tahoma", Font.PLAIN, 14));
		Reservas.setBackground(SystemColor.controlHighlight);
		Image resicon = new ImageIcon(this.getClass().getResource("/reservaIcon.png")).getImage();
		Reservas.setIcon(new ImageIcon(resicon));
		
		Reservas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
				PageHospedar hospedar = new PageHospedar();
				hospedar.setVisible(true);
			}
		});
		Reservas.setBounds(28, 84, 147, 41);
		contentPane.add(Reservas);
		
		JButton Funcionarios = new JButton("Funcionarios");
		Funcionarios.setFont(new Font("Tahoma", Font.PLAIN, 14));
		Funcionarios.setBackground(SystemColor.controlHighlight);
		Image funcicon = new ImageIcon(this.getClass().getResource("/funcPagIcon.png")).getImage();
		Funcionarios.setIcon(new ImageIcon(funcicon));
		
		Funcionarios.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
				PageFuncionarios pagefuncionarios = new PageFuncionarios();
				pagefuncionarios.setVisible(true);
			}
		});
		Funcionarios.setBounds(254, 166, 147, 41);
		contentPane.add(Funcionarios);
		
		JButton Clientes = new JButton("Clientes");
		Clientes.setFont(new Font("Tahoma", Font.PLAIN, 14));
		Clientes.setBackground(SystemColor.controlHighlight);
		Image clicon = new ImageIcon(this.getClass().getResource("/clientIcon.png")).getImage();
		Clientes.setIcon(new ImageIcon(clicon));
		
		Clientes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
				PageClientes pageclientes = new PageClientes();
				pageclientes.setVisible(true);
			}
		});
		Clientes.setBounds(28, 166, 147, 41);
		contentPane.add(Clientes);
		
		JButton Quartos = new JButton("Quartos");
		Image quarticon = new ImageIcon(this.getClass().getResource("/quartosIcon.png")).getImage();
		Quartos.setIcon(new ImageIcon(quarticon));
		
		Quartos.setFont(new Font("Tahoma", Font.PLAIN, 14));
		Quartos.setBackground(SystemColor.controlHighlight);
		Quartos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
				PageQuartos pagequartos = new PageQuartos();
				pagequartos.setVisible(true);
			}
		});
		Quartos.setBounds(254, 84, 147, 41);
		contentPane.add(Quartos);
		
		JButton Logout = new JButton("Logout");
		Image logicon = new ImageIcon(this.getClass().getResource("/logoutIcon.png")).getImage();
		Logout.setIcon(new ImageIcon(logicon));
		Logout.setFont(new Font("Tahoma", Font.PLAIN, 14));
		Logout.setBackground(SystemColor.controlHighlight);
		
		Logout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
				Main window = new Main();
				window.frmLogu.setVisible(true);
			}
		});
		Logout.setBounds(168, 244, 114, 36);
		contentPane.add(Logout);
		
		JLabel lblNewLabel = new JLabel("Menu");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 40));
		lblNewLabel.setBounds(168, 11, 102, 49);
		contentPane.add(lblNewLabel);
	}
}
