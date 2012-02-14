package seguridad;

import modelo.Usuario;

import org.springframework.security.core.Authentication;
import org.zkoss.spring.security.SecurityUtil;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zul.Div;
import org.zkoss.zul.Label;
import org.zkoss.zul.Window;

import controlador.general.CntrlIndex;

import servicio.implementacion.ServicioUsuario;

public class CntrlBarraNavegacion extends GenericForwardComposer {
	Div divCuerpo;
	Window winBarraNavegacion;
	Label lblNombreUsuario, lblDesconectarUsario;
	Authentication authentication;
	Usuario usuario = null;
	CntrlIndex cntrlIndex;
	ServicioUsuario servicioUsuario;

	@Override
	public void doAfterCompose(Component c) throws Exception {
		super.doAfterCompose(c);		
//		System.out.println((String)arg.get("padre")+"----");
		cntrlIndex = new CntrlIndex();
		String nick = SecurityUtil.getAuthentication().getName();
		usuario = servicioUsuario.buscarPorNombre(nick);
		lblNombreUsuario
				.setValue(usuario.getPersonal().getPersonaNatural()
						.getPrimerNombre()
						+ " "
						+ usuario.getPersonal().getPersonaNatural()
								.getPrimerApellido());
	}
	
	public void onClick$lblNombreUsuario(){
		cntrlIndex = (CntrlIndex)desktop.getAttribute("controlador");
		Window win = (Window)execution.createComponents("/Seguridad/frmActualizarUsuario.zul", null,null);
		cntrlIndex.divCuerpo.appendChild(win);
	}
	
	public Label getLblNombreUsuario() {
		return lblNombreUsuario;
	}

	public void setLblNombreUsuario(Label lblNombreUsuario) {
		this.lblNombreUsuario = lblNombreUsuario;
	}

}
