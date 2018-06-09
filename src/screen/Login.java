package screen;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;
import javax.swing.JButton;

@SuppressWarnings("all")
public class Login extends JFrame {

	private JPanel contentPane;
	private JTextField txtUser;
	private JTextField txtPass;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login frame = new Login();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}	

	/**
	 * Initialize the contents of the frame.
	 */
	public Login() {
		setBounds(100, 100, 270, 300);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		setResizable(false);
		setLocationRelativeTo(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel.setBounds(10, 11, 244, 80);
		getContentPane().add(panel);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel_1.setBounds(10, 102, 244, 136);
		getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblUsurio = new JLabel("Usu\u00E1rio");
		lblUsurio.setHorizontalAlignment(SwingConstants.CENTER);
		lblUsurio.setBounds(10, 54, 74, 14);
		panel_1.add(lblUsurio);
		
		JLabel lblSenha = new JLabel("Senha");
		lblSenha.setHorizontalAlignment(SwingConstants.CENTER);
		lblSenha.setBounds(10, 79, 74, 14);
		panel_1.add(lblSenha);
		
		txtUser = new JTextField();
		txtUser.setText("");
		txtUser.setBounds(94, 51, 140, 20);
		panel_1.add(txtUser);
		txtUser.setColumns(10);
		
		txtPass = new JTextField();
		txtPass.setText("");
		txtPass.setBounds(94, 76, 140, 20);
		panel_1.add(txtPass);
		txtPass.setColumns(10);
		
		JButton btnEntrar = new JButton("Entrar");
		btnEntrar.setBounds(77, 102, 89, 23);
		panel_1.add(btnEntrar);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnLogin = new JMenu("Login");
		menuBar.add(mnLogin);
		
		JMenuItem mntmEntrar = new JMenuItem("Entrar");
		mnLogin.add(mntmEntrar);
		
		JMenuItem mntmCriarConta = new JMenuItem("Criar Conta");
		mntmCriarConta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Create frame = new Create();
				frame.setVisible(true);
				dispose();
			}
		});
		mnLogin.add(mntmCriarConta);
	}
}
