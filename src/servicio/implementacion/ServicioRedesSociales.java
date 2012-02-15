package servicio.implementacion;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.social.OperationNotPermittedException;
import org.springframework.social.facebook.api.Facebook;
import org.springframework.social.facebook.api.FacebookProfile;
import org.springframework.social.facebook.api.impl.FacebookTemplate;
import org.springframework.social.twitter.api.CursoredList;
import org.springframework.social.twitter.api.InvalidMessageRecipientException;
import org.springframework.social.twitter.api.Twitter;
import org.springframework.social.twitter.api.TwitterProfile;
import org.springframework.social.twitter.api.impl.TwitterTemplate;

public class ServicioRedesSociales {
	private Properties properties;
	private String ruta;
	
	
	public String getRuta() {
		return ruta;
	}

	public void setRuta(String ruta) {
		this.ruta = ruta;
	}

	public void cargarProperties() {
		try {
			properties= new Properties();
			properties.load(new FileInputStream( new File(ruta)));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public  void enviarFacebook(String  mensaje) {
		cargarProperties();
		Facebook facebook = new FacebookTemplate(properties.getProperty("accessTokenFacebook"));

		 facebook.feedOperations().updateStatus(mensaje);
	}
	
	public void enviarTwitter(List<String> amigos, String mensaje){
		cargarProperties();
		Twitter twitter = new TwitterTemplate(properties.getProperty("consumerKey"),properties.getProperty("consumerSecret") , properties.getProperty("accessTokenTwitter"), properties.getProperty("accessTokenSecret"));
		for (String nombre : amigos) {
			try{
				twitter.friendOperations().follow(nombre);
			}catch (OperationNotPermittedException e) {
			}
		}
		
		for (TwitterProfile amigo :twitter.friendOperations().getFriends())
			try{
				twitter.directMessageOperations().sendDirectMessage(amigo.getId(), mensaje);
				} catch (InvalidMessageRecipientException e) {
					System.out.println(amigo.getScreenName());
					twitter.timelineOperations().updateStatus(mensaje+" "+amigo.getScreenName());
				}
		for (TwitterProfile amigo : twitter.friendOperations().getFollowers()) {
			twitter.directMessageOperations().sendDirectMessage(amigo.getId(), mensaje);		
		}
		twitter.timelineOperations().updateStatus(mensaje);
	}
	
	public void  enviarEmail(List<String> destinatarios,String asunto,String mensaje) {
		try{
			cargarProperties();
			Properties props = new Properties();
            props.setProperty("mail.smtp.host", "smtp.gmail.com");
            props.setProperty("mail.smtp.starttls.enable", "true");
            props.setProperty("mail.smtp.port", "587");
            props.setProperty("mail.smtp.user", properties.getProperty("correo"));
            props.setProperty("mail.smtp.auth", "true");
            Session session = Session.getDefaultInstance(props);
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(properties.getProperty("correo")));
            for (String to : destinatarios) {
            	message.addRecipient(Message.RecipientType.TO,new InternetAddress(to));
			}
            message.setSubject(asunto);
            message.setText(mensaje);
            Transport t = session.getTransport("smtp");
            t.connect(properties.getProperty("correo"), properties.getProperty("clave"));
            t.sendMessage(message, message.getAllRecipients());
            t.close();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
		
	
}
