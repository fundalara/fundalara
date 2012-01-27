package controlador.competencia;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.util.GenericForwardComposer;

public class CntrlResultadosIndividuales extends GenericForwardComposer {
	
	
	Component formulario;
	@Override
	public void doAfterCompose(Component comp) throws Exception {
		// TODO Auto-generated method stub
		super.doAfterCompose(comp);
		formulario = comp;
	}

}
