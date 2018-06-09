package client;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.Socket;
import java.util.StringTokenizer;

/* Abra uma conexão SMTP a um servidor de e-mail e envia um e-mail.*/
public class SMTPConexao {
	/* O Socket para o servidor */
	private Socket conexao;
	/* Fluxos para ler e escrever o Socket */
	private BufferedReader deServer;
	private DataOutputStream paraServer;

	private static final int SMTP_PORTA = 25;

	/* Variável irá ser usada para verificar se aconectado ao servido */
	private boolean Conectado = false;

	/* Iniciando a conexão */

	public SMTPConexao(CorpoEmail corpoEmail) throws IOException {
		conexao = new Socket(corpoEmail.DestHost, SMTP_PORTA);

		deServer = new BufferedReader(new InputStreamReader(conexao.getInputStream()));
		paraServer = new DataOutputStream(conexao.getOutputStream());

		/*
		 * Leia a linha do servidor e verifica se o código de resposta é 220.
		 * Se não, chama o IOException.throw.
		 */
		int respServidor = this.analiseresp(deServer.readLine());
		if (respServidor != 220) {
			throw new IOException("Resposta inesperada do servidor");
		}

		/*
		 * "Olha" o nome da máquina local. Envia o comando adequado de SMTP.Send the
		 */
		String localhost = InetAddress.getLocalHost().getHostName();
		envidarComando("HELO " + localhost, 250);

		Conectado = true;
	}

	/*
	 * Envia a mensagem. Escreve os comandos SMTP corretos na ordem correta. não ha
	 * nenhuma verificação de erros.
	 */
	public void enviar(CorpoEmail corpoEmail) throws IOException {
		/*
		 * Envia todos os comandos necessários para enviar uma mensagem. Chama
		 * envidarComando().
		 */
		this.envidarComando("MAIL FROM: <" + corpoEmail.Remetente + ">", 250);
		this.envidarComando("RCPT TO: <" + corpoEmail.Destinatario + ">", 250);
		this.envidarComando("DATA", 354);
		this.envidarComando(corpoEmail.Mensagem.Cabecalho + corpoEmail.Mensagem.Corpo + "\r\n.", 250);
	}

	/* Termina a conexão. Primeiro, termina o SMTP e fecha o sockete */
	public void close() {
		Conectado = false;
		try {
			envidarComando("QUIT", 221);
			conexao.close();
		} catch (IOException e) {
			System.out.println("Não é possível fechar a conexão: " + e);
			Conectado = true;
		}
	}

	/*
	 * Envie um comando SMTP para o servidor. Verifique se o código de resposta
	 * esta de acordo com o RFC 821.
	 */
	private void envidarComando(String comando, int rc) throws IOException {

		/* Escreve comando para o servidor e lá a resposta do servidor. */
		paraServer.writeBytes(comando + "\r\n");
		/*
		 * Verifique se o código de resposta do servidor é o mesmo que o do parâmetro
		 * rc. Se não, lance uma IOException.
		 */
		String resposta = deServer.readLine();
		System.out.println(resposta);
		int intResposta = analiseresp(resposta);
		if (intResposta != rc) {
			throw new IOException("Erro na comunicacão com o servidor.");
		}
	}

	/* Analisa a linha resp do servidor. Retorna o código resp. */
	private int analiseresp(String resp) {
		StringTokenizer st = new StringTokenizer(resp, " ");
		int retVal;
		retVal = Integer.parseInt(st.nextToken());
		return retVal;
	}

	/* Destructor. Fecha a conexão se algo de ruim acontece. */
	protected void finalize() throws Throwable {
		if (Conectado) {
			close();
		}
//		super.finalize();
	}

}