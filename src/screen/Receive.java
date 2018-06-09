package screen;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JMenuBar;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
@SuppressWarnings("all")
public class Receive extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Receive frame = new Receive();
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
	public Receive() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		setResizable(false);
		setLocationRelativeTo(null);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnLogin = new JMenu("Login");
		menuBar.add(mnLogin);
		
		JMenuItem mntmEnviar = new JMenuItem("Enviar");
		mntmEnviar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Send frame = new Send();
				frame.setVisible(true);
				dispose();
			}
		});
		mnLogin.add(mntmEnviar);
		
		JMenuItem mntmCaixaDeEntrada = new JMenuItem("Caixa de entrada");
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
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 40, 414, 188);
		contentPane.add(scrollPane);
		
		JList list = new JList();
		scrollPane.setViewportView(list);
		
		JLabel lblMail = new JLabel("Mail");
		lblMail.setBounds(10, 15, 46, 14);
		lblMail.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(lblMail);
		
		JLabel lblData = new JLabel("Data");
		lblData.setBounds(378, 15, 46, 14);
		lblData.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(lblData);
		
		JLabel lblRemetente = new JLabel("Remetente");
		lblRemetente.setBounds(293, 15, 66, 14);
		lblRemetente.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(lblRemetente);
	}
}
