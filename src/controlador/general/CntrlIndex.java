package controlador.general;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zul.Div;

import seguridad.CntrlBarraNavegacion;

public class CntrlIndex extends GenericForwardComposer{
	public Div divCuerpo;
	public CntrlBarraNavegacion cntrlBarraNavegacion;
	public Component formulario;

	public void doAfterCompose(Component comp) throws Exception {
		super.doAfterCompose(comp);
		desktop.setAttribute("controlador", this);
	}
}
