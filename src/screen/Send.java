package screen;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.border.BevelBorder;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JTextArea;
import java.awt.Color;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

@SuppressWarnings("all")
public class Send extends JFrame {

	private JPanel contentPane;
	private JTextField txtTo;
	private JTextField txtSubject;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Send frame = new Send();
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
	public Send() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		setResizable(false);
		setLocationRelativeTo(null);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JMenu mnLogin = new JMenu("Login");
		menuBar.add(mnLogin);
		
		JMenuItem mntmEnviar = new JMenuItem("Enviar");
		mnLogin.add(mntmEnviar);
		
		JMenuItem mntmCaixaDeEntrada = new JMenuItem("Caixa de entrada");
		mntmCaixaDeEntrada.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Receive frame = new Receive();
				frame.setVisible(true);
				dispose();
			}
		});
		mnLogin.add(mntmCaixaDeEntrada);
		
		JMenuItem mntmSair = new JMenuItem("Sair");
		mntmSair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Login frame = new Login();
				frame.setVisible(true);
				dispose();
			}
		});
		mnLogin.add(mntmSair);
		
		txtTo = new JTextField();
		txtTo.setText("");
		txtTo.setBounds(96, 11, 338, 20);
		contentPane.add(txtTo);
		txtTo.setColumns(10);
		
		txtSubject = new JTextField();
		txtSubject.setText("");
		txtSubject.setBounds(96, 42, 338, 20);
		contentPane.add(txtSubject);
		txtSubject.setColumns(10);
		
		JLabel lblRcpt = new JLabel("Para");
		lblRcpt.setHorizontalAlignment(SwingConstants.CENTER);
		lblRcpt.setBounds(10, 14, 76, 14);
		contentPane.add(lblRcpt);
		
		JLabel lblSubject = new JLabel("Assunto");
		lblSubject.setHorizontalAlignment(SwingConstants.CENTER);
		lblSubject.setBounds(10, 45, 76, 14);
		contentPane.add(lblSubject);
		
		JLabel lblMail = new JLabel("Mensagem");
		lblMail.setHorizontalAlignment(SwingConstants.CENTER);
		lblMail.setBounds(10, 79, 76, 14);
		contentPane.add(lblMail);
		
		JTextArea txtrMail = new JTextArea();
		txtrMail.setForeground(new Color(0, 0, 0));
		txtrMail.setText("");
		txtrMail.setBounds(96, 74, 338, 164);
		contentPane.add(txtrMail);
	}
}
