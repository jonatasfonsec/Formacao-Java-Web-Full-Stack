package enviando.email;

import java.util.Properties;

import javax.mail.Address;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.junit.Test;

/**
 * Unit test for simple App.
 */
public class AppTest 
{
	private String userName="jonatas.fonsec@outlook.com";
	private String senha="gangrel86";
	
	
	@Test
	public void testeEmail() {
		try {
	
		/*Olhe as configurações de smtp*/
		//
	    //j.jonatas.abreu@bol.com.br
		//J12345678
		
		Properties properties = new Properties();
		properties.put("mail.smtp.auth","true");/*Autorização*/
		properties.put("mail.smtp.starttls.enable","true");/*Autenticação*/
		properties.put("mail.smtp.host","smtp-mail.outlook.com"); /*Servidor Gmail*/
		properties.put("mail.smtp.port","587");/*porta do servidor*/
		properties.put("mail.smtp.socketFactory.port","587");/*Porta conectada pelo Socket*/
		properties.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");/*Classe Socket de conexão ao SMTP*/
		
		Session session = Session.getInstance(properties,new Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(userName,senha);
			}
			
		});
		
		
		//System.out.println(session);
		
		Address[] toUser = InternetAddress.parse("jonatas.fonsec@outlook.com,jonatas.fonsec@gmail.com");
		
		Message message = new MimeMessage(session);
		message.setFrom(new InternetAddress(userName)); /*Quem esta enviando*/
		message.setRecipients(Message.RecipientType.TO, toUser);/*Emails de destinatarios*/
		message.setSubject("Chegou email de teste de java");/*Assunto do email*/
		message.setText("Olá Programador, vc acaba de receber um email do seu código fonte");
		
		Transport.send(message);
		
		System.out.println("Mensagem enviada!");
		
		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
	}
}
