package controlador.entrenamiento;

import org.python.modules.cmath;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Listcell;

public class ListenerCombo implements EventListener{
	
	private Listbox lista;
	
	public ListenerCombo(Listbox lista) {
		// TODO Auto-generated constructor stub
		this.lista=lista;
	}

	@Override
	public void onEvent(Event arg0) throws Exception {
		// TODO Auto-generated method stub
		
		Combobox combo = (Combobox)arg0.getTarget();
		int index = Integer.parseInt(combo.getId());
		for (int i = 0; i < lista.getItemCount(); i++) {
			Listcell cell = (Listcell) lista.getItemAtIndex(i).getChildren()
					.get(index);
			Combobox c = (Combobox) cell.getChildren().get(0);
			c.setValue(combo.getSelectedItem().getLabel());
		}
		
	}

}
