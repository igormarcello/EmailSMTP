package client;

import java.net.*;
import java.io.*;
import java.util.*;
import java.text.*;
@SuppressWarnings("all")
public class Mensagem {
	/* Os cabeçalhos e o corpo da mensagem. */
	public String Cabecalho;
	public String Corpo;

	/*
	 * Remetente e destinatário. Com isso, não precisamos extrai-los do
	 * cabeçalho
	 */
	private String From;
	private String To;
	private String Subject;
	// Essas variáveis mantém o nome do host e o ID do usuário local para formar
	// fromField e e mail se a caixa de seleção usrName estiver marcada.
	private String hostSvrlocal, nomeusuariolocal;

	/* Melhora a aparencia */
	private static final String CRLF = "\r\n";

	/*
	 * Crie o objeto de mensagem inserindo os cabeçalhos necessários de RFC 822
	 * (De, Para, Data).
	 */
	public Mensagem(String de, String para, String assunto, String texto, boolean situacao, String localsvr)
			throws IOException {
		/* Remover espaço em branco */
		// Pega host nome e o usuário local
		InetAddress inet;
		inet = InetAddress.getByName(localsvr);
		hostSvrlocal = inet.getHostName();
		nomeusuariolocal = System.getProperty("user.name");// nome do usuario local

		// Se a caixa de seleção usrName estiver marcada,
		// gera o endereço de e-mail (coloca o "@")
		if (situacao == true) {
			From = nomeusuariolocal + "@" + hostSvrlocal;
		} else // caso contrário, use o digitado no campo De
		{
			From = de.trim();
		}
		To = para.trim();
		Subject = assunto.trim();
		
		Cabecalho = "From:" + From + CRLF;
//		Cabecalho += "to:" + To + CRLF;
		Cabecalho += "Subject:" + Subject + CRLF;

		/* A aproximação do formato exigido de data */
//		SimpleDateFormat formato = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss 'GMT'");
//		String data = formato.format(new Date());
//		Cabecalho += "Data: " + data + CRLF;
		Corpo = texto;
	}

	/* Duas funções para acessar o remetente e o destinatário. */
	public String getDe() {
		return From;
	}

	public String getPara() {
		return To;
	}
	public String getAssunto() {
		return Subject;
	}
	/* Para imprimir a mensagem. */
	@Override
	public String toString() {
		String res;

		res = Cabecalho + CRLF;
		res += Corpo;
		return res;
	}
}