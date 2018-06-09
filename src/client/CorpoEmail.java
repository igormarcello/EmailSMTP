package client;

import java.net.*;
import java.util.*;

public class CorpoEmail {
	/* SMTP- Remetente do email apos envia a mensagem */
	public String Remetente;

	/* SMTP-recipient, or contents of To-header. */
	public String Destinatario;
	public String DestHost; /* Target MX-host */
	public InetAddress DestAddr; /* Destination Server address */

	/* A mensagem atual */
	public Mensagem Mensagem;

	/* Criando o corpo do email */
	public CorpoEmail(Mensagem mensagem, String localServer) throws UnknownHostException {
		/* pega o remetente, destinatário */
		Remetente = mensagem.getDe();
		Destinatario = mensagem.getPara();
		/*
		 * Receba mensagem. Devemos fugir da mensagem para garantir que não haja
		 * períodos isolados em uma linha. Isso iria atrapalhar o envio do e-mail.
		 */
		Mensagem = escapeMensagem(mensagem);

		/*
		 * Pega o noem do servido logal de mailserver e o coloca como o servido local.
		 */
		DestHost = localServer;
		try {
			DestAddr = InetAddress.getByName(DestHost);
		} catch (UnknownHostException e) {
			System.out.println("Host não encontrado: " + DestHost);
			System.out.println(e);
			throw e;
		}
		return;
	}

	/* Escape da mensagem dobrando todos os períodos no início de uma linha. */
	private Mensagem escapeMensagem(Mensagem mensagem) {
		String escapedBody = "";
		String token;
		StringTokenizer analisador = new StringTokenizer(mensagem.Corpo, "\n", true);

		while (analisador.hasMoreTokens()) {
			token = analisador.nextToken();
			if (token.startsWith(".")) {
				token = "." + token;
			}
			escapedBody += token;
		}
		mensagem.Corpo = escapedBody;
		return mensagem;
	}

	/* Monstra o corpo do email */
	public String toString() {
		String res = "From: " + Remetente + '\n';
		res += "To: " + Destinatario + '\n';
		res += "MX-host: " + DestHost + ", E-mail: " + DestAddr + '\n';
		res += "Mensagem:" + '\n';
		res += Mensagem.toString();

		return res;
	}
}