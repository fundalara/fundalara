package controlador.general;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.util.GenericForwardComposer;

public class CntrlFrmAgendaLogistica extends CntrlFrmAgendaGeneral{
     
	@Override
	public void doAfterCompose(Component component) throws Exception {
		// TODO Auto-generated method stub
		super.doAfterCompose(component);
		System.out.println("controlador agenda logistica");
	}

}
