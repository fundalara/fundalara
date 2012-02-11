package controlador.logistica;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.util.GenericForwardComposer;


public class CntrlAsignarPersonal extends GenericForwardComposer {
	

	
	public void doAfterCompose(Component comp)throws Exception{
		super.doAfterCompose(comp);
		comp.setVariable("cntrl",this, true);

	}

	
	public void onClick$btnAgregar() {
		
	}

	
	
}
