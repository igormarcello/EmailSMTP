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
public class Create extends JFrame {

	private JPanel contentPane;
	private JTextField txtUser;
	private JTextField txtPass;
	private JTextField txtMail;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Create frame = new Create();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Create() {
		setBounds(100, 100, 270, 300);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setLocationRelativeTo(null);
		getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(10, 11, 244, 80);
		panel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		getContentPane().add(panel);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(10, 102, 244, 136);
		panel_1.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblUsurio = new JLabel("Usu\u00E1rio");
		lblUsurio.setHorizontalAlignment(SwingConstants.CENTER);
		lblUsurio.setBounds(10, 30, 74, 14);
		panel_1.add(lblUsurio);
		
		JLabel lblSenha = new JLabel("Senha");
		lblSenha.setHorizontalAlignment(SwingConstants.CENTER);
		lblSenha.setBounds(10, 55, 74, 14);
		panel_1.add(lblSenha);
		
		txtUser = new JTextField();
		txtUser.setText("");
		txtUser.setBounds(94, 27, 140, 20);
		panel_1.add(txtUser);
		txtUser.setColumns(10);
		
		txtPass = new JTextField();
		txtPass.setText("");
		txtPass.setBounds(94, 52, 140, 20);
		panel_1.add(txtPass);
		txtPass.setColumns(10);
		
		JLabel lblEmail = new JLabel("E-mail");
		lblEmail.setHorizontalAlignment(SwingConstants.CENTER);
		lblEmail.setBounds(10, 80, 74, 14);
		panel_1.add(lblEmail);
		
		txtMail = new JTextField();
		txtMail.setText("");
		txtMail.setBounds(94, 77, 140, 20);
		panel_1.add(txtMail);
		txtMail.setColumns(10);
		
		JButton button = new JButton("Entrar");
		button.setBounds(73, 102, 89, 23);
		panel_1.add(button);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnLogin = new JMenu("Login");
		menuBar.add(mnLogin);
		
		JMenuItem mntmEntrar = new JMenuItem("Entrar");
		mntmEntrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Login frame = new Login();
				frame.setVisible(true);
				dispose();
			}
		});
		mnLogin.add(mntmEntrar);
		
		JMenuItem mntmCriarConta = new JMenuItem("Criar Conta");
		mnLogin.add(mntmCriarConta);
	}

}
