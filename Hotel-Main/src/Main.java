import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Main {

	JFrame frmLogu;
	private JPasswordField passwordField;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Main window = new Main();
					window.frmLogu.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public Main() {
		initialize();
	}
	
	private void initialize() {
		frmLogu = new JFrame();
		frmLogu.getContentPane().setBackground(Color.WHITE);
		frmLogu.setBackground(Color.WHITE);
		frmLogu.setBounds(100, 100, 450, 330);
		frmLogu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	
		frmLogu.setTitle("Login");
		frmLogu.getContentPane().setLayout(null);
		frmLogu.setIconImage(new ImageIcon(this.getClass().getResource("/hotelIcon.png")).getImage());
		
		JLabel lblNewLabel = new JLabel("Usuario");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel.setBounds(84, 105, 64, 22);
		frmLogu.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Senha");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel_1.setBounds(91, 174, 57, 21);
		frmLogu.getContentPane().add(lblNewLabel_1);
		
		JTextField User = new JTextField("");
		User.setForeground(Color.BLACK);
		User.setBounds(150, 105, 234, 29);
		frmLogu.getContentPane().add(User);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(150, 173, 234, 29);
		frmLogu.getContentPane().add(passwordField);
		
		JLabel lblNewLabel_2 = new JLabel("Login");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 40));
		lblNewLabel_2.setBounds(173, 0, 101, 77);
		frmLogu.getContentPane().add(lblNewLabel_2);
		
		UIManager.getDefaults().put("OptionPane.background",SystemColor.white);
		UIManager.put("Panel.background", SystemColor.white);
		UIManager.put("Button.background",SystemColor.controlHighlight);
		
		JButton btnNewButton = new JButton("Login");
		btnNewButton.setBackground(SystemColor.controlHighlight);
		btnNewButton.setForeground(new Color(51, 51, 51));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				FuncionariosDAO funcionarios = new FuncionariosDAO();
				if(funcionarios.verificaLogin(User.getText(), passwordField.getText())){
					JOptionPane.showMessageDialog(frmLogu, "Login efetuado!");
					frmLogu.dispose();
					Menu menu = new Menu();
					menu.setVisible(true);
				}else JOptionPane.showMessageDialog(frmLogu, "Erro no login");
		
			}
		});
		btnNewButton.setBounds(173, 233, 101, 22);
		frmLogu.getContentPane().add(btnNewButton);
		
		JLabel UserImage = new JLabel("");
		Image UserIcon = new ImageIcon(this.getClass().getResource("/userloginIcon.png")).getImage();
		UserImage.setIcon(new ImageIcon(UserIcon));
		UserImage.setBounds(43, 92, 31, 43);
		frmLogu.getContentPane().add(UserImage);
		
		JLabel KeyImage = new JLabel("");
		Image keyIcon = new ImageIcon(this.getClass().getResource("/keyloginIcon.png")).getImage();
		KeyImage.setIcon(new ImageIcon(keyIcon));
		KeyImage.setBounds(45, 162, 36, 35);
		frmLogu.getContentPane().add(KeyImage);
	
		
		
	}
}