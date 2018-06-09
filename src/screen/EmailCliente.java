package screen;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Checkbox;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.Panel;
import java.awt.TextArea;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.UnknownHostException;
//estava dando um erro tive que acrescentar essas bibliotecas
import java.util.logging.Level;
import java.util.logging.Logger;

import client.CorpoEmail;
import client.Mensagem;
import client.SMTPConexao;
@SuppressWarnings("all")
public class EmailCliente extends Frame {
	/* O material para a GUI. */
	private Button btEnviar = new Button("Enviar");
	private Button btLimpar = new Button("Limpar");
	private Button btSair = new Button("Sair");
//	private Label serverLabel = new Label("Local mailserver:");
	private TextField serverField = new TextField("localhost", 40);
	private Label deLabel = new Label("De:");
	private TextField deField = new TextField("", 40);
	private Label paraLabel = new Label("Para:");
	private TextField paraField = new TextField("", 40);

	/*
	 * Caixa de seleção para indicar se você deseja gerar automaticamente o nome
	 * de usuário é o nome e o ID do usuário local são usados.
	 * 
	 * CUIDADO: Este e-mail não serão enviado a menos que seu nome de usuário
	 * local seja o nome de usuário da conta de e-mail e você coloque o nome
	 * correto do servidor de e-mail que possa identificar seu nome de usuário.
	 */
	private Checkbox usrNome = new Checkbox("Usuário Local?");
	private Label assuntotLabel = new Label("Assunto:");
	private TextField assuntoField = new TextField("", 40);
	private Label mensagemLabel = new Label("Mensagem:");
	private TextArea mensagemText = new TextArea(10, 40);

	/**
	 * Crie uma nova janela MailClient com campos para inserir todas as
	 * informações relevantes (De, Para, Assunto e mensagem).
	 */
	public EmailCliente() {
		super("SMTP E-Mail Cliente");
		setBounds(100,100,420,350);
		setLocationRelativeTo(null);
		/*
		 * Crie painéis para manter os campos. Para torná-lo bonito, crie um painel
		 * extra para manter todos os painéis filhos.
		 */
		Panel serverPanel = new Panel(new BorderLayout());
		Panel dePanel = new Panel(new BorderLayout());
		Panel paraPanel = new Panel(new BorderLayout());
		

		// add usrPanel to GUI
		Panel usrPanel = new Panel(new BorderLayout());

		Panel assuntoPanel = new Panel(new BorderLayout());
		Panel mensagemPanel = new Panel(new BorderLayout());
		mensagemPanel.add(mensagemText, BorderLayout.CENTER);
		Panel fieldPanel = new Panel(new GridLayout(0, 1));
		fieldPanel.add(serverPanel);
		serverPanel.add(deField, BorderLayout.CENTER);
		serverPanel.add(deLabel, BorderLayout.WEST);
		fieldPanel.add(dePanel);
		dePanel.add(paraField, BorderLayout.CENTER);
		dePanel.add(paraLabel, BorderLayout.WEST);
		fieldPanel.add(paraPanel);
		paraPanel.add(usrNome, BorderLayout.WEST);
		usrNome.setState(true);
		fieldPanel.add(usrPanel);
		usrPanel.add(assuntoField, BorderLayout.CENTER);
		usrPanel.add(assuntotLabel, BorderLayout.WEST);
		fieldPanel.add(assuntoPanel);
		assuntoPanel.add(mensagemLabel, BorderLayout.WEST);

		/* Crie um painel para os botões e adicione ouvintes aos botões. */
		Panel buttonPanel = new Panel(new GridLayout(1, 0));
		btEnviar.addActionListener(new EnviarMensagem());
		btLimpar.addActionListener(new Limpa());
		btSair.addActionListener(new Sair());
		buttonPanel.add(btEnviar);
		buttonPanel.add(btLimpar);
		buttonPanel.add(btSair);

		/* Add, pack, e show. */
		add(fieldPanel, BorderLayout.NORTH);
		add(mensagemPanel, BorderLayout.CENTER);
		add(buttonPanel, BorderLayout.SOUTH);
		pack();
		setVisible(true);
	}

	static public void main(String argv[]) {
		new EmailCliente();
	}

	/* Handler for the Send-button. */
	class EnviarMensagem implements ActionListener {
		public void actionPerformed(ActionEvent event) {
			// tive que acrescentar esse try pois não parava de dar erro.
			try {
				System.out.println("Enviando email...");
				CorpoEmail envelope; // instancia o metodo para ser utilizado
				/*
				 * Verifique se o campo do servidor de correio esta preenchido com servidor
				 * local
				 */
				if ((serverField.getText()).equals("")) {
					System.out.println("Precisa de nome do servidor local!");
					return;
				}

				/*
				 * Verifica se campo do remetente esta preenchido. Além disso, antes de enviar
				 * verifica se deField estiver em branco e usrNome checkbox está checado.
				 */
				if ((deField.getText()).equals("") && !usrNome.getState()) {
					System.out.println("Precisa de remetente!");
					return;
				}
				if ((paraField.getText()).equals("")) {
					System.out.println("Precisa de destinatário!");
					return;
				}

				if (usrNome.getState())
					System.out.println("Verificado.");
				/* Escrevendo a mensagem */
				Mensagem mailMessage = new Mensagem(deField.getText(), paraField.getText(), assuntoField.getText(),
						mensagemText.getText(), usrNome.getState(), serverField.getText());
				try {
					envelope = new CorpoEmail(mailMessage, serverField.getText());
				} catch (UnknownHostException e) {
					/* Verifica se houver não a erros */
					return;
				}

				try {
					SMTPConexao connection = new SMTPConexao(envelope);

					connection.enviar(envelope);
					connection.close();
				} catch (IOException error) {
					System.out.println("O envio falhou: " + error);
					return;
				}
				System.out.println("Mail enviado com sucesso!");
				// tive que acrescentar esse catch pois não parava de dar erro.
			} catch (IOException ex) {
				Logger.getLogger(EmailCliente.class.getName()).log(Level.SEVERE, null, ex);
			}
			deField.setText("");
			paraField.setText("");
			assuntoField.setText("");
			mensagemText.setText("");
		}
	}

	/* Limpar os campos na GUI. */
	class Limpa implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			System.out.println("Limpo!");
			deField.setText("");
			paraField.setText("");
			assuntoField.setText("");
			mensagemText.setText("");
		}
	}

	/* Saida. */
	class Sair implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			System.exit(0);
		}
	}
}