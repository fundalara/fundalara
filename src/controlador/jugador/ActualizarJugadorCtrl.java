package controlador.jugador;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import org.zkoss.util.media.Media;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zul.*;

import comun.FileLoader;



public class ActualizarJugadorCtrl extends GenericForwardComposer {

	private Image img;
	
	

	
	public void onClick$btnLoad() {
		new FileLoader().cargarImagen(img);
		
		
	
	}

	

	

}