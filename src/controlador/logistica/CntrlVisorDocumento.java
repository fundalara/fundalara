package controlador.logistica;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLConnection;

import org.zkoss.util.media.AMedia;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zul.Iframe;
import org.zkoss.zul.Window;

/**
 * Clase controladora de los eventos de la vista de igual nombre y visualizacion
 * de archivos.
 * Recibe dos parametros: 
 * -archivo: byte[] o AMedia.
 * -orientacion: 'vertical'(por defecto), 'horizontal'
 * 
 * @author Robert A
 * @author German L
 * @version 0.1 01/02/2012
 * 
 * */
public class CntrlVisorDocumento extends GenericForwardComposer {
	private Component formulario;
	private Iframe iframeDocumento;
	private Window winVisorDocumento;
	byte[] archivo = null;
	AMedia amedia = null;
	private static String ANCHO = "600px";
	private static String ALTO = "800px";

	@Override
	public void doAfterCompose(Component comp) throws Exception {
		super.doAfterCompose(comp);
		comp.setVariable("controller", this, false);
		formulario = comp;
	}

	public void onCreate$winVisorDocumento() {
		Object file = formulario.getVariable("archivo", false);
		String orientacion = (String) formulario.getVariable("orientacion",
				false);

		ajustarVentana(orientacion);
		if (file instanceof byte[]) {
			byte[] archivo = (byte[]) file;
			if (archivo != null) {
				InputStream mediais = new ByteArrayInputStream(archivo);
				String mime = "";
				try {
					mime = URLConnection.guessContentTypeFromStream(mediais);
				} catch (IOException e) {
					e.printStackTrace();
				}
				String[] formato = new String[2];
				if (mime == null) {
					formato[0] = "";
					formato[1] = "pdf";
					mime = "application/pdf";
				} else {
					formato = mime.split("/");
				}
				amedia = new AMedia("archivo." + formato[1], formato[1], mime,
						mediais);
				iframeDocumento.setContent(amedia);
			}
		} else if (file instanceof AMedia) {
			amedia = (AMedia) file;
			if (amedia != null) {
				iframeDocumento.setContent(amedia);
				winVisorDocumento.setTitle("Olimpo");
			}
		}
	}

	private void ajustarVentana(String orientacion) {
		if (orientacion != null) {
			if (orientacion.equalsIgnoreCase("HORIZONTAL")) {
				iframeDocumento.setWidth(ALTO);
				iframeDocumento.setHeight(ANCHO);
				winVisorDocumento.setHeight(ANCHO);
				winVisorDocumento.setWidth(ALTO);
			}
			// Vertical: Orientacion por defecto
		}
	}

	public void onClick$btnSalir() {
		onClose$winVisorDocumento();
	}

	public void onClose$winVisorDocumento() {
		amedia = null;
		iframeDocumento= new Iframe();
		winVisorDocumento.detach();
	}
}